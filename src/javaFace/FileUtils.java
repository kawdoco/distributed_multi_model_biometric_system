package javaFace;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class FileUtils
{
  private static final String FILE_EXT = ".png";
  private static final String TRAINING_DIR = "C:\\Imgs";
  private static final String EF_CACHE = "C:\\eigenfacesCash\\eigen.cache";
  private static final String EIGENFACES_DIR = "C:\\eigenfaces";
  private static final String EIGENFACES_PREFIX = "eigen_";
  private static final String RECON_DIR = "reconstructed";
  private static final String RECON_PREFIX = "recon_";
  
  public FileUtils() {}
  
  public static ArrayList<String> getTrainingFnms()
  {
    File dirF = new File("C:\\Imgs");
    String[] fnms = dirF.list(new java.io.FilenameFilter() {
      public boolean accept(File f, String name) {
        return name.endsWith(".png");
      }
    });
    if (fnms == null) {
      System.out.println("C:\\Imgs not found");
      return null;
    }
    if (fnms.length == 0) {
      System.out.println("C:\\Imgs contains no  .png files");
      return null;
    }
    
    return getPathNms(fnms);
  }
  


  private static ArrayList<String> getPathNms(String[] fnms)
  {
    ArrayList<String> imFnms = new ArrayList();
    String[] arrayOfString = fnms;int j = fnms.length; 
for (int i = 0; i < j; i++) {
 String fnm = arrayOfString[i];
      imFnms.add("C:\\Imgs" + File.separator + fnm);
    }
    java.util.Collections.sort(imFnms);
    return imFnms;
  }
  







  public static BufferedImage[] loadTrainingIms(ArrayList<String> fnms)
  {
    BufferedImage[] ims = new BufferedImage[fnms.size()];
    BufferedImage im = null;
    int i = 0;
    System.out.println("Loading grayscale images from C:\\Imgs...");
    for (String fnm : fnms) {
      try {
        im = ImageIO.read(new File(fnm));
        System.out.println("  " + fnm);
        ims[(i++)] = ImageUtils.toScaledGray(im, 1.0D);
      }
      catch (Exception e) {
        System.out.println("Could not read image from " + fnm);
      }
    }
    System.out.println("Loading done\n");
    
    ImageUtils.checkImSizes(fnms, ims);
    return ims;
  }
  



  public static BufferedImage loadImage(String imFnm)
  {
    BufferedImage image = null;
    try {
      image = ImageIO.read(new File(imFnm));
      System.out.println("Reading image " + imFnm);
    }
    catch (Exception e) {
      System.out.println("Could not read image from " + imFnm);
    }
    return image;
  }
  



  public static void saveImage(BufferedImage im, String fnm)
  {
    try
    {
      ImageIO.write(im, "png", new File(fnm));
    }
    catch (IOException e)
    {
      System.out.println("Could not save image to " + fnm);
    }
  }
  





  public static FaceBundle readCache()
  {
    FaceBundle bundle = null;
    try {
      ObjectInputStream ois = new ObjectInputStream(
        new java.io.FileInputStream("C:\\eigenfacesCash\\eigen.cache"));
      bundle = (FaceBundle)ois.readObject();
      ois.close();
      System.out.println("Using cache: C:\\eigenfacesCash\\eigen.cache");
      return bundle;
    }
    catch (FileNotFoundException e) {
      System.out.println("Missing cache: C:\\eigenfacesCash\\eigen.cache");
    }
    catch (IOException e) {
      System.out.println("Read error for cache: C:\\eigenfacesCash\\eigen.cache");
    }
    catch (ClassNotFoundException e) {
      System.out.println(e);
    }
    return bundle;
  }
  



  public static void writeCache(FaceBundle bundle)
  {
    System.out.println("Saving eigenfaces to: C:\\eigenfacesCash\\eigen.cache ...");
    try {
      ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream("C:\\eigenfacesCash\\eigen.cache"));
      oos.writeObject(bundle);
      System.out.println("Cache save succeeded");
      oos.close();
    }
    catch (Exception e) {
      System.out.println("Cache save failed");
      System.out.println(e);
    }
  }
  






  public static void saveEFIms(Matrix2D egfaces, int imWidth)
  {
    double[][] egFacesArr = egfaces.toArray();
    makeDirectory("C:\\eigenfaces");
    
    for (int row = 0; row < egFacesArr.length; row++) {
      String fnm = "C:\\eigenfaces" + File.separator + "eigen_" + row + ".png";
      saveArrAsImage(fnm, egFacesArr[row], imWidth);
    }
  }
  



  private static void makeDirectory(String dir)
  {
    File dirF = new File(dir);
    if (dirF.isDirectory()) {
      System.out.println("Directory: " + dir + " already exists; deleting its contents");
      for (File f : dirF.listFiles()) {
        deleteFile(f);
      }
    } else {
      dirF.mkdir();
      System.out.println("Created new directory: " + dir);
    }
  }
  


  private static void deleteFile(File f)
  {
    if (f.isFile()) {
      boolean bool = f.delete();
    }
  }
  






  private static void saveArrAsImage(String fnm, double[] imData, int width)
  {
    BufferedImage im = ImageUtils.createImFromArr(imData, width);
    if (im != null) {
      try {
        ImageIO.write(im, "png", new File(fnm));
        System.out.println("  " + fnm);
      }
      catch (Exception e) {
        System.out.println("Could not save image to " + fnm);
      }
    }
  }
  






  public static void saveReconIms2(double[][] ims, int imWidth)
  {
    makeDirectory("reconstructed");
    for (int i = 0; i < ims.length; i++) {
      String fnm = "reconstructed" + File.separator + "recon_" + i + ".png";
      saveArrAsImage(fnm, ims[i], imWidth);
    }
  }
}
