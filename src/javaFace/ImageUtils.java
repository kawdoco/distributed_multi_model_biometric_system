/*     */ package javaFace;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RasterFormatException;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ImageUtils
/*     */ {
/*     */   public static BufferedImage createImFromArr(double[] imData, int width) {
/*  14 */     BufferedImage im = null;
/*     */     try {
/*  16 */       im = new BufferedImage(width, imData.length / width, 10);
/*  17 */       Raster rast = im.getData();
/*  18 */       WritableRaster wr = rast.createCompatibleWritableRaster();
/*  19 */       double maxVal = Double.MIN_VALUE;
/*  20 */       double minVal = Double.MAX_VALUE;
/*     */       
/*  22 */       for (int i = 0; i < imData.length; i++) {
/*  23 */         maxVal = Math.max(maxVal, imData[i]);
/*  24 */         minVal = Math.min(minVal, imData[i]);
/*     */       } 
/*     */       
/*  27 */       for (int j = 0; j < imData.length; j++)
/*  28 */         imData[j] = (imData[j] - minVal) * 255.0D / (maxVal - minVal); 
/*  29 */       wr.setPixels(0, 0, width, imData.length / width, imData);
/*  30 */       im.setData(wr);
/*     */     }
/*  32 */     catch (Exception e) {
/*  33 */       System.out.println(e);
/*     */     } 
/*  35 */     return im;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double[] createArrFromIm(BufferedImage im) {
/*  42 */     im = toScaledGray(im, 1.0D);
/*  43 */     int imWidth = im.getWidth();
/*  44 */     int imHeight = im.getHeight();
/*     */     
/*  46 */     double[] imArr = new double[imWidth * imHeight];
/*  47 */     im.getData().getPixels(0, 0, imWidth, imHeight, imArr);
/*  48 */     return imArr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage toScaledGray(BufferedImage im, double scale) {
/*  57 */     int imWidth = im.getWidth();
/*  58 */     int imHeight = im.getHeight();
/*     */     
/*  60 */     int nWidth = (int)Math.round(imWidth * scale);
/*  61 */     int nHeight = (int)Math.round(imHeight * scale);
/*     */ 
/*     */     
/*  64 */     BufferedImage grayIm = new BufferedImage(nWidth, nHeight, 
/*  65 */         10);
/*  66 */     Graphics2D g2 = grayIm.createGraphics();
/*  67 */     g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
/*  68 */         RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/*  69 */     g2.drawImage(im, 0, 0, nWidth, nHeight, 0, 0, imWidth, imHeight, null);
/*  70 */     g2.dispose();
/*     */     
/*  72 */     return grayIm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage clipToRectangle(BufferedImage im, int x, int y, int width, int height) {
/*  79 */     BufferedImage clipIm = null;
/*     */     try {
/*  81 */       clipIm = im.getSubimage(x, y, width, height);
/*     */     }
/*  83 */     catch (RasterFormatException e) {
/*  84 */       System.out.println("Could not clip the image");
/*  85 */       clipIm = im;
/*     */     } 
/*  87 */     return clipIm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void checkImSizes(ArrayList<String> fNms, BufferedImage[] ims) {
/*  96 */     int imWidth = ims[0].getWidth();
/*  97 */     int imHeight = ims[0].getHeight();
/*  98 */     System.out.println("Image (w,h): (" + imWidth + ", " + imHeight + ")");
/*     */     
/* 100 */     for (int i = 1; i < ims.length; i++) {
/* 101 */       if (ims[i].getHeight() != imHeight || 
/* 102 */         ims[i].getWidth() != imWidth) {
/* 103 */         System.out.println("All images should have be the same size; " + 
/* 104 */             (String)fNms.get(i) + " is a different size");
/* 105 */         System.exit(1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Susara\Documents\NetBeansProjects\FaceRecognization_JADE_BASED\Jars\DetectionAndRecgonzionJAR.jar!\javaFace\ImageUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.2
 */