/*     */ package javaFace;
/*     */ 
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.colt.matrix.impl.DenseDoubleMatrix2D;
/*     */ import cern.jet.math.Functions;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Matrix2D
/*     */   extends DenseDoubleMatrix2D
/*     */ {
/*  12 */   public Matrix2D(double[][] data) { super(data); }
/*     */ 
/*     */   
/*  15 */   public Matrix2D(DoubleMatrix2D dmat) { super(dmat.toArray()); }
/*     */ 
/*     */   
/*  18 */   public Matrix2D(int rows, int cols) { super(rows, cols); }
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix2D(double[] data, int rows) {
/*  23 */     super(rows, (rows != 0) ? (data.length / rows) : 0);
/*  24 */     int columns = (rows != 0) ? (data.length / rows) : 0;
/*  25 */     if (rows * columns != data.length) {
/*  26 */       throw new IllegalArgumentException("Array length must be a multiple of " + rows);
/*     */     }
/*  28 */     double[][] vals = new double[rows][columns];
/*  29 */     for (int i = 0; i < rows; i++) {
/*  30 */       for (int j = 0; j < columns; j++)
/*  31 */         vals[i][j] = data[i + j * rows]; 
/*     */     } 
/*  33 */     assign(vals);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   public Matrix2D getSubMatrix(int rows) { return new Matrix2D(viewPart(0, 0, rows, columns()).copy()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void fitToUnitLength(double[] data) {
/*  45 */     double max = max(data);
/*  46 */     for (int i = 0; i < data.length; i++) {
/*  47 */       data[i] = data[i] / max;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  53 */   public void subtractMean() { subtractFromEachRow(getAverageOfEachColumn()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getAverageOfEachColumn() {
/*  59 */     double[][] data = toArray();
/*     */     
/*  61 */     double[] avgValues = new double[this.columns];
/*     */     
/*  63 */     for (int col = 0; col < this.columns; col++) {
/*  64 */       double total = 0.0D;
/*  65 */       for (int row = 0; row < this.rows; row++)
/*  66 */         total += data[row][col]; 
/*  67 */       avgValues[col] = total / this.rows;
/*     */     } 
/*  69 */     return avgValues;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void replaceRowsWithArray(double[] data) {
/*  76 */     if (this.columns != data.length) {
/*  77 */       throw new RuntimeException(
/*  78 */           "matrix columns not matching number of input array elements");
/*     */     }
/*  80 */     for (int row = 0; row < this.rows; row++) {
/*  81 */       for (int col = 0; col < this.columns; col++) {
/*  82 */         set(row, col, data[col]);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void normalise() {
/*  90 */     double[][] temp = toArray();
/*  91 */     double[] mvals = new double[temp.length];
/*     */     
/*  93 */     for (int i = 0; i < temp.length; i++) {
/*  94 */       mvals[i] = max(temp[i]);
/*     */     }
/*  96 */     for (int i = 0; i < temp.length; i++) {
/*  97 */       for (int j = 0; j < temp[0].length; j++)
/*  98 */         temp[i][j] = temp[i][j] / mvals[i]; 
/*     */     } 
/* 100 */     assign(temp);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static double max(double[] arr) {
/* 107 */     double max = Double.MIN_VALUE;
/* 108 */     for (int i = 0; i < arr.length; i++)
/* 109 */       max = Math.max(max, arr[i]); 
/* 110 */     return max;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 115 */   public void subtract(Matrix2D mat) { assign(mat, Functions.minus); }
/*     */ 
/*     */ 
/*     */   
/* 119 */   public void add(Matrix2D mat) { assign(mat, Functions.plus); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void subtractFromEachRow(double[] oneDArray) {
/* 124 */     double[][] denseArr = toArray();
/* 125 */     for (int i = 0; i < denseArr.length; i++) {
/* 126 */       for (int j = 0; j < denseArr[0].length; j++)
/* 127 */         denseArr[i][j] = denseArr[i][j] - oneDArray[j]; 
/*     */     } 
/* 129 */     assign(denseArr);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 134 */   public Matrix2D multiply(Matrix2D mat) { return new Matrix2D(zMult(mat, null)); }
/*     */ 
/*     */ 
/*     */   
/* 138 */   public void multiplyElementWise(Matrix2D mat) { assign(mat, Functions.mult); }
/*     */ 
/*     */ 
/*     */   
/* 142 */   public Matrix2D transpose() { return new Matrix2D(viewDice()); }
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] flatten() {
/* 147 */     double[] res = new double[this.rows * this.columns];
/* 148 */     int i = 0;
/* 149 */     for (int row = 0; row < this.rows; row++) {
/* 150 */       for (int col = 0; col < this.columns; col++)
/* 151 */         res[i++] = get(row, col); 
/*     */     } 
/* 153 */     return res;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static double norm(double[] arr) {
/* 159 */     double val = 0.0D;
/* 160 */     for (int i = 0; i < arr.length; i++)
/* 161 */       val += arr[i] * arr[i]; 
/* 162 */     return val;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void subtract(double[] inputFace, double[] avgFace) {
/* 168 */     for (int i = 0; i < inputFace.length; i++) {
/* 169 */       inputFace[i] = inputFace[i] - avgFace[i];
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 174 */   public EigenvalueDecomp getEigenvalueDecomp() { return new EigenvalueDecomp(this); }
/*     */ }


/* Location:              C:\Users\Susara\Documents\NetBeansProjects\FaceRecognization_JADE_BASED\Jars\DetectionAndRecgonzionJAR.jar!\javaFace\Matrix2D.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.2
 */