/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Rectangle;
/*    */ import java.util.ListResourceBundle;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract interface AutoMowerConstants
/*    */ {
/*    */   public static final String SCREEN_WIDTH = "ScreenWidth";
/*    */   public static final String SCREEN_HEIGHT = "ScreenHeight";
/*    */   public static final String SCREEN_SIZE = "ScreenSize";
/*    */   public static final String FIELD_WIDTH = "FieldWidth";
/*    */   public static final String FIELD_HEIGHT = "FieldHeight";
/*    */   public static final String FIELD_SIZE = "FieldSize";
/*    */   public static final String MOWER_WIDTH = "MowerWidth";
/*    */   public static final String MOWER_HEIGHT = "MowerHeight";
/*    */   public static final String MOWER_BOX = "MowerBox";
/*    */   public static final String MOWER_COST = "MowerCost";
/*    */   public static final String HUMAN_COST = "HumanCost";
/*    */   public static final String MAX_PASSES = "MaxPasses";
/*    */   public static final String UNCOVERED_COLOR = "UncoveredColor";
/*    */   public static final String COVERED_COLOR = "CoveredColor";
/*    */   public static final String SLOW_DELAY = "SlowDelay";
/*    */   
/*    */   public static class AutoMowerDefaults
/*    */     extends ListResourceBundle
/*    */   {
/*    */     public Object[][] getContents()
/*    */     {
/* 35 */       return this.contents;
/*    */     }
/*    */     
/*    */     public boolean setValue(String paramString, Object paramObject)
/*    */     {
/* 40 */       if ((paramString == null) || (paramObject == null)) {
/* 41 */         return false;
/*    */       }
/* 43 */       for (int i = 0; i < this.contents.length; i++) {
/* 44 */         if (this.contents[i][0].equals(paramString)) {
/* 45 */           if (this.contents[i][1].getClass().isInstance(paramObject)) {
/* 46 */             this.contents[i] = new Object[]{ paramString, paramObject };
/* 47 */             resetDimensions();
/* 48 */             return true;
/*    */           }
/* 50 */           return false;
/*    */         }
/*    */       }
/*    */       
/* 54 */       return false;
/*    */     }
/*    */     
/*    */ 
/*    */ 
/*    */     public String[] getKeySet()
/*    */     {
/* 61 */       String[] arrayOfString = new String[this.contents.length];
/* 62 */       for (int i = 0; i < this.contents.length; i++) {
/* 63 */         arrayOfString[i] = ((String)this.contents[i][0]);
/*    */       }
/* 65 */       return arrayOfString;
/*    */     }
/*    */     
/*    */     private void resetDimensions()
/*    */     {
/* 70 */       this.contents[0] = new Object[]{ "ScreenSize", new Dimension(getInt("ScreenWidth").intValue(), getInt("ScreenHeight").intValue()) };
/* 71 */       this.contents[1] = new Object[]{ "FieldSize", new Dimension(getInt("FieldWidth").intValue(), getInt("FieldHeight").intValue()) };
/* 72 */       this.contents[2] = new Object[]{ "MowerBox", new Rectangle(0, 0, getInt("MowerWidth").intValue(), getInt("MowerHeight").intValue()) };
/*    */     }
/*    */     
/*    */     public Integer getInt(String paramString)
/*    */     {
/* 77 */       for (int i = 0; i < this.contents.length; i++) {
/* 78 */         if (this.contents[i][0].equals(paramString)) {
/* 79 */           return (Integer)this.contents[i][1];
/*    */         }
/*    */       }
/* 82 */       return null;
/*    */     }
/*    */     
/* 85 */     private Object[][] contents = { { "ScreenSize", new Dimension(700, 400) }, { "FieldSize", new Dimension(500, 200) }, { "MowerBox", new Rectangle(0, 0, 20, 60) }, { "ScreenWidth", Integer.valueOf(700) }, { "ScreenHeight", Integer.valueOf(400) }, { "FieldWidth", Integer.valueOf(500) }, { "FieldHeight", Integer.valueOf(200) }, { "MowerWidth", Integer.valueOf(20) }, { "MowerHeight", Integer.valueOf(60) }, { "MaxPasses", Integer.valueOf(40) }, { "MowerCost", Double.valueOf(0.03D) }, { "HumanCost", Double.valueOf(0.08D) }, { "UncoveredColor", Color.GREEN }, { "CoveredColor", Color.GRAY }, { "SlowDelay", Integer.valueOf(100) } };
/*    */   }
/*    */ }


/* Location:              /Users/lo/Desktop/presentation/LawnMover/AutoMower.jar!/AutoMowerConstants.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */