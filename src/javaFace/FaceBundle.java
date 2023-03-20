/*    */ package javaFace;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class FaceBundle
/*    */   implements Serializable {
/*    */   private static final long serialVersionUID = 4990971206693306324L;
/*    */   private double[][] imageRows;
/*    */   private ArrayList<String> imageFnms;
/*    */   private double[] avgImage;
/*    */   private double[][] eigenFaces;
/*    */   private double[] eigenValues;
/*    */   private int imageWidth;
/*    */   private int imageHeight;
/*    */   
/*    */   public FaceBundle(ArrayList<String> nms, double[][] ims, double[] avgImg, double[][] facesMat, double[] evals, int w, int h) {
/* 18 */     this.imageFnms = nms;
/* 19 */     this.imageRows = ims;
/* 20 */     this.avgImage = avgImg;
/* 21 */     this.eigenFaces = facesMat;
/* 22 */     this.eigenValues = evals;
/* 23 */     this.imageWidth = w;
/* 24 */     this.imageHeight = h;
/*    */   }
/*    */ 
/*    */   
/* 28 */   public double[][] getImages() { return this.imageRows; }
/*    */ 
/*    */ 
/*    */   
/* 32 */   public double[][] getEigenFaces() { return this.eigenFaces; }
/*    */ 
/*    */ 
/*    */   
/* 36 */   public int getNumEigenFaces() { return this.eigenFaces.length; }
/*    */ 
/*    */ 
/*    */   
/* 40 */   public double[] getAvgImage() { return this.avgImage; }
/*    */ 
/*    */ 
/*    */   
/* 44 */   public double[] getEigenValues() { return this.eigenValues; }
/*    */ 
/*    */ 
/*    */   
/* 48 */   public ArrayList<String> getImageFnms() { return this.imageFnms; }
/*    */ 
/*    */ 
/*    */   
/* 52 */   public int getImageWidth() { return this.imageWidth; }
/*    */ 
/*    */ 
/*    */   
/* 56 */   public int getImageHeight() { return this.imageHeight; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double[][] calcWeights(int numEFs) {
/* 66 */     Matrix2D imsMat = new Matrix2D(this.imageRows);
/*    */     
/* 68 */     Matrix2D facesMat = new Matrix2D(this.eigenFaces);
/* 69 */     Matrix2D facesSubMatTr = facesMat.getSubMatrix(numEFs).transpose();
/*    */     
/* 71 */     Matrix2D weights = imsMat.multiply(facesSubMatTr);
/* 72 */     return weights.toArray();
/*    */   }
/*    */ }


/* Location:              C:\Users\Susara\Documents\NetBeansProjects\FaceRecognization_JADE_BASED\Jars\DetectionAndRecgonzionJAR.jar!\javaFace\FaceBundle.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.2
 */