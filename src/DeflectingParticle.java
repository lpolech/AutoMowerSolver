/*     */ import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
/*     */ import java.awt.geom.RectangularShape;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DeflectingParticle
/*     */   extends Point2D.Double
/*     */ {
/*     */   public static final double RAD_DEG = 57.29577951308232D;
/*     */   public static final double DEFAULT_HEADING = 0.7853981633974483D;
/*     */   private double myHeading;
/*     */   private double myDeflection;
/*     */   private double myTotalDistance;
/*     */   private int myDeflectionCount;
/*     */   
/*     */   public DeflectingParticle()
/*     */   {
/*  20 */     this(0.0D, 0.0D, 0.7853981633974483D, 0.0D);
/*     */   }
/*     */   
/*     */   public DeflectingParticle(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
/*  24 */     super(paramDouble1, paramDouble2);
/*  25 */     this.myHeading = paramDouble3;
/*  26 */     this.myDeflection = paramDouble4;
/*  27 */     this.myTotalDistance = 0.0D;
/*  28 */     this.myDeflectionCount = 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public double getHeading()
/*     */   {
/*  34 */     return this.myHeading;
/*     */   }
/*     */   
/*     */   public double getHeadingDegrees() {
/*  38 */     return this.myHeading * 57.29577951308232D;
/*     */   }
/*     */   
/*     */   public void setHeading(double paramDouble) {
/*  42 */     this.myHeading = paramDouble;
/*     */   }
/*     */   
/*     */   public void setHeadingDegrees(double paramDouble) {
/*  46 */     this.myHeading = (paramDouble / 57.29577951308232D);
/*     */   }
/*     */   
/*     */ 
/*     */   public double getDeflection()
/*     */   {
/*  52 */     return this.myDeflection;
/*     */   }
/*     */   
/*     */   public double getDeflectionDegrees() {
/*  56 */     return this.myDeflection * 57.29577951308232D;
/*     */   }
/*     */   
/*     */   public void setDeflection(double paramDouble) {
/*  60 */     this.myDeflection = paramDouble;
/*     */   }
/*     */   
/*     */   public void setDeflectionDegrees(double paramDouble) {
/*  64 */     this.myDeflection = (paramDouble / 57.29577951308232D);
/*     */   }
/*     */   
/*     */ 
/*     */   public double getTotalDistance()
/*     */   {
/*  70 */     return this.myTotalDistance;
/*     */   }
/*     */   
/*     */   public void resetTotalDistance() {
/*  74 */     this.myTotalDistance = 0.0D;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getDeflectionCount()
/*     */   {
/*  80 */     return this.myDeflectionCount;
/*     */   }
/*     */   
/*     */   public void resetDeflectionCount() {
/*  84 */     this.myDeflectionCount = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void move(double paramDouble)
/*     */   {
/*  92 */     this.myTotalDistance += paramDouble;
/*  93 */     double d1 = Math.cos(getHeading()) * paramDouble;
/*  94 */     double d2 = Math.sin(getHeading()) * paramDouble;
/*  95 */     setLocation(getX() + d1, getY() + d2);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean move(double paramDouble, RectangularShape paramRectangularShape)
/*     */   {
/* 101 */     move(paramDouble);
/* 102 */     return reflect(paramRectangularShape);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean reflect(RectangularShape paramRectangularShape)
/*     */   {
/* 109 */     if (paramRectangularShape.contains(this)) {
/* 110 */       return false;
/*     */     }
/*     */     
/* 113 */     int i = this.myDeflectionCount;
/* 114 */     trace("Bounce: " + getX() + "," + getY() + "@" + getHeadingDegrees() + " in " + paramRectangularShape.getBounds());
/* 115 */     if (getY() >= paramRectangularShape.getMaxY()) {
/* 116 */       if (this.myHeading <= 1.5707963267948966D) {
/* 117 */         trace("SE -> NE: " + getHeadingDegrees());
/* 118 */         this.myHeading = (6.283185307179586D - this.myHeading);
/* 119 */         this.myDeflectionCount += 1;
/* 120 */       } else if (this.myHeading <= 3.141592653589793D) {
/* 121 */         trace("SW -> NW: " + getHeadingDegrees());
/* 122 */         this.myHeading = (6.283185307179586D - this.myHeading);
/* 123 */         this.myDeflectionCount += 1;
/*     */       }
/* 125 */     } else if (getY() < 0.0D) {
/* 126 */       if (this.myHeading >= 4.71238898038469D) {
/* 127 */         trace("NE -> SE: " + getHeadingDegrees());
/* 128 */         this.myHeading = (6.283185307179586D - this.myHeading);
/* 129 */         this.myDeflectionCount += 1;
/* 130 */       } else if (this.myHeading >= 3.141592653589793D) {
/* 131 */         trace("NW -> SW: " + getHeadingDegrees());
/* 132 */         this.myHeading = (6.283185307179586D - this.myHeading);
/* 133 */         this.myDeflectionCount += 1;
/*     */       }
/* 135 */     } else if (getX() >= paramRectangularShape.getMaxX()) {
/* 136 */       if (this.myHeading <= 1.5707963267948966D) {
/* 137 */         trace("SE -> SW: " + getHeadingDegrees());
/* 138 */         this.myHeading = (3.141592653589793D - this.myHeading);
/* 139 */         this.myDeflectionCount += 1;
/* 140 */       } else if (this.myHeading >= 4.71238898038469D) {
/* 141 */         trace("NE -> NW: " + getHeadingDegrees());
/* 142 */         this.myHeading = (9.42477796076938D - this.myHeading);
/* 143 */         this.myDeflectionCount += 1;
/*     */       }
/* 145 */     } else if (getX() < 0.0D) {
/* 146 */       if ((this.myHeading >= 1.5707963267948966D) && (this.myHeading <= 3.141592653589793D)) {
/* 147 */         trace("SW -> SE: " + getHeadingDegrees());
/* 148 */         this.myHeading = (3.141592653589793D - this.myHeading);
/* 149 */         this.myDeflectionCount += 1;
/* 150 */       } else if ((this.myHeading >= 3.141592653589793D) && (this.myHeading <= 4.71238898038469D)) {
/* 151 */         trace("NW -> NE: " + getHeadingDegrees());
/* 152 */         this.myHeading = (9.42477796076938D - this.myHeading);
/* 153 */         this.myDeflectionCount += 1;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 158 */     while (this.myHeading < 0.0D) {
/* 159 */       this.myHeading += 6.283185307179586D;
/*     */     }
/* 161 */     while (this.myHeading > 6.283185307179586D) {
/* 162 */       this.myHeading -= 6.283185307179586D;
/*     */     }
/*     */     
/* 165 */     return i != this.myDeflectionCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void trace(String paramString) {}
/*     */   
/*     */ 
/*     */   public void reset()
/*     */   {
/* 175 */     setLocation(0.0D, 0.0D);
/* 176 */     setHeading(getDeflection());
/* 177 */     resetDeflectionCount();
/* 178 */     resetTotalDistance();
/*     */   }
/*     */ }


/* Location:              /Users/lo/Desktop/presentation/LawnMover/AutoMower.jar!/DeflectingParticle.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */