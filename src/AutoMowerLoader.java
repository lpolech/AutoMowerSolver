/*    */ import java.applet.Applet;
/*    */ import java.awt.Dimension;
/*    */ import java.io.PrintStream;
/*    */ import javax.swing.JFrame;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AutoMowerLoader
/*    */   extends Applet
/*    */   implements AutoMowerConstants
/*    */ {
/*    */   public void init()
/*    */   {
/* 15 */     AutoMowerConstants.AutoMowerDefaults localAutoMowerDefaults = new AutoMowerConstants.AutoMowerDefaults();
/* 16 */     String[] arrayOfString = localAutoMowerDefaults.getKeySet();
/* 17 */     for (int i = 0; i < arrayOfString.length; i++) {
/* 18 */       String str = null;
/*    */       try {
/* 20 */         str = getParameter(arrayOfString[i]);
/* 21 */         if (str != null) {
/* 22 */           System.out.println("Setting [" + arrayOfString[i] + "] to [" + str + "]");
/* 23 */           if (str.indexOf("x") != -1)
/*    */           {
/* 25 */             int j = Integer.parseInt(str.substring(0, str.indexOf("x")));
/* 26 */             int k = Integer.parseInt(str.substring(str.indexOf("x") + 1, str.length()));
/* 27 */             localAutoMowerDefaults.setValue(arrayOfString[i], new Dimension(j, k));
/* 28 */           } else if (str.indexOf(".") != -1) {
/* 29 */             localAutoMowerDefaults.setValue(arrayOfString[i], Double.valueOf(Double.parseDouble(str)));
/*    */           } else {
/* 31 */             localAutoMowerDefaults.setValue(arrayOfString[i], Integer.valueOf(Integer.parseInt(str)));
/*    */           }
/*    */         }
/*    */       }
/*    */       catch (Exception localException) {}
/*    */     }
/*    */     
/* 38 */     AutoMowerSimulation localAutoMowerSimulation = new AutoMowerSimulation(localAutoMowerDefaults);
/* 39 */     add(localAutoMowerSimulation);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 48 */     AutoMowerConstants.AutoMowerDefaults localAutoMowerDefaults = new AutoMowerConstants.AutoMowerDefaults();
/* 49 */     JFrame localJFrame = new JFrame();
/* 50 */     localJFrame.setTitle("Auto-Mower Simulation");
/* 51 */     localJFrame.setMinimumSize((Dimension)localAutoMowerDefaults.getObject("ScreenSize"));
/* 52 */     localJFrame.setDefaultCloseOperation(3);
/* 53 */     localJFrame.setContentPane(new AutoMowerSimulation());
/* 54 */     localJFrame.setVisible(true);
/*    */   }
/*    */ }


/* Location:              /Users/lo/Desktop/presentation/LawnMover/AutoMower.jar!/AutoMowerLoader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */
