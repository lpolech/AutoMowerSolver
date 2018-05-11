/*     */ import java.awt.geom.RectangularShape;
/*     */ 
/*     */ public class Field extends java.awt.Rectangle
/*     */ {
/*     */   private boolean[][] myMarkings;
/*     */   private int myTotalMarks;
/*     */   private Field.FieldPanel myPanel;
/*     */
/*     */   public static class FieldPanel extends javax.swing.JPanel
/*     */   {
/*     */     private Field myParent;
/*     */     private java.awt.Color myUnmarkedColor;
/*     */     private java.awt.Color myMarkedColor;
/*     */     
/*     */     public FieldPanel(Field paramField, java.awt.Color paramColor1, java.awt.Color paramColor2)
/*     */     {
/*  17 */       this.myParent = paramField;
/*  18 */       this.myUnmarkedColor = paramColor1;
/*  19 */       this.myMarkedColor = paramColor2;
/*  20 */       setMaximumSize(this.myParent.getSize());
/*  21 */       setMinimumSize(this.myParent.getSize());
/*  22 */       setPreferredSize(this.myParent.getSize());
/*  23 */       this.myParent.myPanel = this;
/*     */     }
/*     */     
/*     */     public void paint(java.awt.Graphics paramGraphics) {
/*  27 */       this.myParent.paintMe(paramGraphics, this.myMarkedColor, this.myUnmarkedColor);
/*     */     }
/*     */     
/*     */     public void mark(int paramInt1, int paramInt2) {
/*  31 */       java.awt.Graphics localGraphics = getGraphics();
/*  32 */       localGraphics.setColor(this.myMarkedColor);
/*  33 */       localGraphics.fillRect(paramInt1, paramInt2, 1, 1);
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Field(int paramInt1, int paramInt2)
/*     */   {
/*  45 */     super(paramInt1, paramInt2);
/*  46 */     this.myMarkings = new boolean[paramInt1 + 1][paramInt2 + 1];
/*  47 */     for (boolean[] arrayOfBoolean1 : this.myMarkings) {
/*  48 */       java.util.Arrays.fill(arrayOfBoolean1, false);
/*     */     }
/*  50 */     this.myTotalMarks = 0;
/*  51 */     this.myPanel = null;
/*     */   }
/*     */   
/*     */   public int getTotalMarks()
/*     */   {
/*  56 */     return this.myTotalMarks;
/*     */   }
/*     */   
/*     */   public int getMaximumMarks() {
/*  60 */     return (int)(getWidth() * getHeight());
/*     */   }
/*     */   
/*     */   public void reset() {
/*  64 */     for (boolean[] arrayOfBoolean1 : this.myMarkings) {
/*  65 */       java.util.Arrays.fill(arrayOfBoolean1, false);
/*     */     }
/*  67 */     this.myTotalMarks = 0;
/*     */   }
/*     */   
/*     */   public boolean isConsistant()
/*     */   {
/*  72 */     int i = 0;
/*  73 */     for (boolean[] arrayOfBoolean1 : this.myMarkings) {
/*  74 */       for (boolean i1 : arrayOfBoolean1) {
/*  75 */         if (i1) {
/*  76 */           i++;
/*     */         }
/*     */       }
/*     */     }
/*  80 */     return i == this.myTotalMarks;
/*     */   }
/*     */   
/*     */   public boolean mark(int paramInt1, int paramInt2)
/*     */   {
/*  85 */     if ((contains(paramInt1, paramInt2)) && (!this.myMarkings[paramInt1][paramInt2])) {
/*  86 */       this.myTotalMarks += 1;
/*  87 */       this.myMarkings[paramInt1][paramInt2] = true;
/*  88 */       if (this.myPanel != null) {
/*  89 */         this.myPanel.mark((int)getX() + paramInt1, (int)getY() + paramInt2);
/*     */       }
/*  91 */       return true;
/*     */     }
/*  93 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean markRectangle(RectangularShape paramRectangularShape, double paramDouble)
/*     */   {
/* 103 */     int i = this.myTotalMarks;
/*     */     
/* 105 */     double d1 = paramDouble + Math.atan2(paramRectangularShape.getMaxY() - paramRectangularShape.getCenterY(), paramRectangularShape.getMinX() - paramRectangularShape.getCenterX());
/* 106 */     double d5 = Math.pow(Math.pow(paramRectangularShape.getMaxY() - paramRectangularShape.getCenterY(), 2.0D) + Math.pow(paramRectangularShape.getMinX() - paramRectangularShape.getCenterX(), 2.0D), 0.5D);
/*     */     
/* 108 */     double d4 = paramDouble + Math.atan2(paramRectangularShape.getMaxY() - paramRectangularShape.getCenterY(), paramRectangularShape.getMaxX() - paramRectangularShape.getCenterX());
/* 109 */     double d8 = Math.pow(Math.pow(paramRectangularShape.getMaxY() - paramRectangularShape.getCenterY(), 2.0D) + Math.pow(paramRectangularShape.getMaxX() - paramRectangularShape.getCenterX(), 2.0D), 0.5D);
/*     */     
/* 111 */     double d3 = paramDouble + Math.atan2(paramRectangularShape.getMinY() - paramRectangularShape.getCenterY(), paramRectangularShape.getMaxX() - paramRectangularShape.getCenterX());
/* 112 */     double d7 = Math.pow(Math.pow(paramRectangularShape.getMinY() - paramRectangularShape.getCenterY(), 2.0D) + Math.pow(paramRectangularShape.getMaxX() - paramRectangularShape.getCenterX(), 2.0D), 0.5D);
/*     */     
/* 114 */     double d2 = paramDouble + Math.atan2(paramRectangularShape.getMinY() - paramRectangularShape.getCenterY(), paramRectangularShape.getMinX() - paramRectangularShape.getCenterX());
/* 115 */     double d6 = Math.pow(Math.pow(paramRectangularShape.getMinY() - paramRectangularShape.getCenterY(), 2.0D) + Math.pow(paramRectangularShape.getMinX() - paramRectangularShape.getCenterX(), 2.0D), 0.5D);
/*     */     
/* 117 */     java.awt.Polygon localPolygon = new java.awt.Polygon();
/* 118 */     localPolygon.addPoint((int)(d5 * Math.cos(d1)), (int)(d5 * Math.sin(d1)));
/* 119 */     localPolygon.addPoint((int)(d6 * Math.cos(d2)), (int)(d6 * Math.sin(d2)));
/* 120 */     localPolygon.addPoint((int)(d7 * Math.cos(d3)), (int)(d7 * Math.sin(d3)));
/* 121 */     localPolygon.addPoint((int)(d8 * Math.cos(d4)), (int)(d8 * Math.sin(d4)));
/* 122 */     localPolygon.translate((int)paramRectangularShape.getCenterX(), (int)paramRectangularShape.getCenterY());
/*     */     
/* 124 */     java.awt.Rectangle localRectangle = localPolygon.getBounds();
/* 125 */     for (int j = 0; j < localRectangle.getWidth(); j++) {
/* 126 */       for (int k = 0; k < localRectangle.getHeight(); k++) {
/* 127 */         int m = j + (int)localRectangle.getX();
/* 128 */         int n = k + (int)localRectangle.getY();
/* 129 */         if (localPolygon.contains(m, n)) {
/* 130 */           mark(m, n);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 135 */     return i == this.myTotalMarks;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void paintMe(java.awt.Graphics paramGraphics, java.awt.Color paramColor1, java.awt.Color paramColor2)
/*     */   {
/* 145 */     paramGraphics.setColor(paramColor2);
/* 146 */     paramGraphics.fillRect((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
/*     */     
/* 148 */     paramGraphics.setColor(paramColor1);
/* 149 */     int i = this.myTotalMarks;
/* 150 */     for (int j = 0; (j < this.myMarkings.length) && (i > 0); j++) {
/* 151 */       for (int k = 0; (k < this.myMarkings[j].length) && (i > 0); k++) {
/* 152 */         if (this.myMarkings[j][k]) {
/* 153 */           paramGraphics.fillRect((int)getX() + j, (int)getY() + k, 1, 1);
/* 154 */           i--;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/lo/Desktop/presentation/LawnMover/AutoMower.jar!/Field.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */