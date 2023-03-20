/*    */ package javaFace;
/*    */ 
/*    */ import cern.colt.matrix.linalg.EigenvalueDecomposition;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EigenvalueDecomp
/*    */   extends EigenvalueDecomposition
/*    */ {
/* 10 */   public EigenvalueDecomp(Matrix2D dmat) { super(dmat); }
/*    */ 
/*    */ 
/*    */   
/* 14 */   public double[] getEigenValues() { return diag(getD().toArray()); }
/*    */ 
/*    */ 
/*    */   
/* 18 */   public double[][] getEigenVectors() { return getV().toArray(); }
/*    */ 
/*    */ 
/*    */   
/*    */   private double[] diag(double[][] m) {
/* 23 */     double[] diag = new double[m.length];
/* 24 */     for (int i = 0; i < m.length; i++)
/* 25 */       diag[i] = m[i][i]; 
/* 26 */     return diag;
/*    */   }
/*    */ }


/* Location:              C:\Users\Susara\Documents\NetBeansProjects\FaceRecognization_JADE_BASED\Jars\DetectionAndRecgonzionJAR.jar!\javaFace\EigenvalueDecomp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.2
 */