package javaFace;

import java.awt.image.BufferedImage;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class BuildEigenFaces
{
  public BuildEigenFaces() {}
  
  public static void build(int numEFs)
  {
    ArrayList<String> fnms = FileUtils.getTrainingFnms();
    int numIms = fnms.size();
    if ((numEFs < 1) || (numEFs >= numIms)) {
      System.out.println("Number of eigenfaces must be in range (1-" + (numIms - 1) + ")" + 
        "; using " + (numIms - 1));
      numEFs = numIms - 1;
    }
    else {
      System.out.println("Number of eigenfaces: " + numEFs);
    }
    FaceBundle bundle = makeBundle(fnms);
    FileUtils.writeCache(bundle);
    reconstructIms(numEFs, bundle);
  }
  





  private static FaceBundle makeBundle(ArrayList<String> fnms)
  {
    BufferedImage[] ims = FileUtils.loadTrainingIms(fnms);
    
    Matrix2D imsMat = convertToNormMat(ims);
    double[] avgImage = imsMat.getAverageOfEachColumn();
    imsMat.subtractMean();
    


    Matrix2D imsDataTr = imsMat.transpose();
    Matrix2D covarMat = imsMat.multiply(imsDataTr);
    

    EigenvalueDecomp egValDecomp = covarMat.getEigenvalueDecomp();
    double[] egVals = egValDecomp.getEigenValues();
    double[][] egVecs = egValDecomp.getEigenVectors();
    
    sortEigenInfo(egVals, egVecs);
    
    Matrix2D egFaces = getNormEgFaces(imsMat, new Matrix2D(egVecs));
    
    System.out.println("\nSaving Eigenfaces as images...");
    FileUtils.saveEFIms(egFaces, ims[0].getWidth());
    System.out.println("Saving done\n");
    
    return new FaceBundle(fnms, imsMat.toArray(), avgImage, 
      egFaces.toArray(), egVals, ims[0].getWidth(), ims[0].getHeight());
  }
  







  private static Matrix2D convertToNormMat(BufferedImage[] ims)
  {
    int imWidth = ims[0].getWidth();
    int imHeight = ims[0].getHeight();
    
    int numRows = ims.length;
    int numCols = imWidth * imHeight;
    double[][] data = new double[numRows][numCols];
    for (int i = 0; i < numRows; i++) {
      ims[i].getData().getPixels(0, 0, imWidth, imHeight, data[i]);
    }
    Matrix2D imsMat = new Matrix2D(data);
    imsMat.normalise();
    return imsMat;
  }
  






  private static Matrix2D getNormEgFaces(Matrix2D imsMat, Matrix2D egVecs)
  {
    Matrix2D egVecsTr = egVecs.transpose();
    Matrix2D egFaces = egVecsTr.multiply(imsMat);
    double[][] egFacesData = egFaces.toArray();
    
    for (int row = 0; row < egFacesData.length; row++) {
      double norm = Matrix2D.norm(egFacesData[row]);
      for (int col = 0; col < egFacesData[row].length; col++)
        egFacesData[row][col] /= norm;
    }
    return new Matrix2D(egFacesData);
  }
  









  private static void sortEigenInfo(double[] egVals, double[][] egVecs)
  {
    Double[] egDvals = getEgValsAsDoubles(egVals);
    

    Hashtable<Double, double[]> table = new Hashtable();
    for (int i = 0; i < egDvals.length; i++) {
      table.put(egDvals[i], getColumn(egVecs, i));
    }
    ArrayList<Double> sortedKeyList = sortKeysDescending(table);
    updateEgVecs(egVecs, table, egDvals, sortedKeyList);
    


    Double[] sortedKeys = new Double[sortedKeyList.size()];
    sortedKeyList.toArray(sortedKeys);
    

    for (int i = 0; i < sortedKeys.length; i++) {
      egVals[i] = sortedKeys[i].doubleValue();
    }
  }
  



  private static Double[] getEgValsAsDoubles(double[] egVals)
  {
    Double[] egDvals = new Double[egVals.length];
    for (int i = 0; i < egVals.length; i++)
      egDvals[i] = new Double(egVals[i]);
    return egDvals;
  }
  




  private static double[] getColumn(double[][] vecs, int col)
  {
    double[] res = new double[vecs.length];
    for (int i = 0; i < vecs.length; i++)
      res[i] = vecs[i][col];
    return res;
  }
  




  private static ArrayList<Double> sortKeysDescending(Hashtable<Double, double[]> table)
  {
    ArrayList<Double> keyList = Collections.list(table.keys());
    Collections.sort(keyList, Collections.reverseOrder());
    return keyList;
  }
  






  private static void updateEgVecs(double[][] egVecs, Hashtable<Double, double[]> table, Double[] egDvals, ArrayList<Double> sortedKeyList)
  {
    for (int col = 0; col < egDvals.length; col++) {
      double[] egVec = (double[])table.get(sortedKeyList.get(col));
      for (int row = 0; row < egVec.length; row++) {
        egVecs[row][col] = egVec[row];
      }
    }
  }
  




  private static void reconstructIms(int numEFs, FaceBundle bundle)
  {
    System.out.println("\nReconstructing training images...");
    
    Matrix2D egFacesMat = new Matrix2D(bundle.getEigenFaces());
    Matrix2D egFacesSubMat = egFacesMat.getSubMatrix(numEFs);
    
    Matrix2D egValsMat = new Matrix2D(bundle.getEigenValues(), 1);
    Matrix2D egValsSubMat = egValsMat.transpose().getSubMatrix(numEFs);
    
    double[][] weights = bundle.calcWeights(numEFs);
    double[][] normImgs = getNormImages(weights, egFacesSubMat, egValsSubMat);
    
    double[][] origImages = addAvgImage(normImgs, bundle.getAvgImage());
    

    FileUtils.saveReconIms2(origImages, bundle.getImageWidth());
    System.out.println("Reconstruction done\n");
  }
  






  private static double[][] getNormImages(double[][] weights, Matrix2D egFacesSubMat, Matrix2D egValsSubMat)
  {
    double[] egDValsSub = egValsSubMat.flatten();
    Matrix2D tempEvalsMat = new Matrix2D(weights.length, egDValsSub.length);
    tempEvalsMat.replaceRowsWithArray(egDValsSub);
    
    Matrix2D tempMat = new Matrix2D(weights);
    tempMat.multiplyElementWise(tempEvalsMat);
    
    Matrix2D normImgsMat = tempMat.multiply(egFacesSubMat);
    return normImgsMat.toArray();
  }
  




  private static double[][] addAvgImage(double[][] normImgs, double[] avgImage)
  {
    double[][] origImages = new double[normImgs.length][normImgs[0].length];
    for (int i = 0; i < normImgs.length; i++) {
      for (int j = 0; j < normImgs[i].length; j++)
        normImgs[i][j] += avgImage[j];
    }
    return origImages;
  }
  





  public static void main(String[] args)
  {
    if (args.length > 1) {
      System.out.println("Usage: java BuildEigenFaces [numberOfEigenFaces]");
      return;
    }
    
    int numEFs = 0;
   // args.length;
    

    long startTime = System.currentTimeMillis();
    build(numEFs);
    System.out.println("Total time taken: " + (
      System.currentTimeMillis() - startTime) + " ms");
  }
}
