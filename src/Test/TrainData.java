/*    */ package Test;
/*    */ 
/*    */ import javaFace.BuildEigenFaces;
/*    */ 
/*    */ 
/*    */ public class TrainData
/*    */ {
/*    */   public static void TrainData() {
/*  9 */    int numEFs = 0;
/*    */     
/* 11 */     long startTime = System.currentTimeMillis();
/* 12 */     BuildEigenFaces.build(numEFs);
/* 13 */     System.out.println("Total time taken: " + (
/* 14 */         System.currentTimeMillis() - startTime) + " ms");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 20 */   public static void main(String[] args) { TrainData(); }
/*    */ }


/* Location:              C:\Users\Susara\Documents\NetBeansProjects\FaceRecognization_JADE_BASED\Jars\DetectionAndRecgonzionJAR.jar!\Test\TrainData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.2
 */