/*    */ package Test;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ import javaFace.FaceRecognition;
/*    */ import javaFace.FileUtils;
/*    */ import javaFace.MatchResult;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecgonizeNewData
/*    */ {
/*    */   public static void main(String[] args) {
/* 16 */     BufferedImage image = FileUtils.loadImage("C:\\ImgsCopy\\3.png");
/* 17 */     System.out.println(RecgonizeNewDataBufferedImage(image));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void RecgonizeNewDataimagewithFilePath(String imgFilePath) {
/* 28 */     int numEFs = 0;
/*    */     
/* 30 */     long startTime = System.currentTimeMillis();
/*    */     
/* 32 */     FaceRecognition fr = new FaceRecognition(numEFs);
/* 33 */     MatchResult result = fr.match(imgFilePath);
/*    */     
/* 35 */     if (result == null) {
/* 36 */       System.out.println("No match found");
/*    */     } else {
/* 38 */       System.out.println();
/* 39 */       System.out.print("Matches image in " + result.getMatchFileName());

/* 40 */       

               System.out.printf("; distance  = %.4f\n", new Object[] { Double.valueOf(result.getMatchDistance()) });
/* 41 */       System.out.println("Matched name: " + result.getName());
/*    */     } 
/* 43 */     System.out.println("Total time taken: " + (
/* 44 */     System.currentTimeMillis() - startTime) + " ms");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static String RecgonizeNewDataBufferedImage(BufferedImage image) {
/* 50 */     int numEFs = 0;
/*    */     
/* 52 */     FaceRecognition fr = new FaceRecognition(numEFs);
/* 53 */     MatchResult result = fr.match(image);
/* 54 */     Double Threshold = new Double("0.6");
/* 55 */     if (result.getMatchDistance() > Threshold.doubleValue()) {
/* 56 */       System.out.println(result.getMatchDistance());
/* 57 */       return null;
/*    */     } 
/*    */     
/* 60 */     String matchFileIndex = result.getMatchFileName().replace(".png", "");
/* 61 */     matchFileIndex = matchFileIndex.replace("C:\\Imgs\\", "");
/* 62 */     System.out.print("Matches image in " + result.getMatchFileName());
/* 63 */     System.out.printf("; distance sus = %.4f\n", new Object[] { Double.valueOf(result.getMatchDistance()) });
/* 64 */     
             
            //System.out.println("Matched name: " + result.getName());
/* 65 */     return matchFileIndex;
           // return String.valueOf(result.getMatchFileName());
/*    */   }



/* Location:              C:\Users\Susara\Documents\NetBeansProjects\FaceRecognization_JADE_BASED\Jars\DetectionAndRecgonzionJAR.jar!\Test\RecgonizeNewData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.2
 */