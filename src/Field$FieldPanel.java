/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Field$FieldPanel
/*    */   extends JPanel
/*    */ {
/*    */   private Field myParent;
/*    */   private Color myUnmarkedColor;
/*    */   private Color myMarkedColor;
/*    */   
/*    */   public Field$FieldPanel(Field paramField, Color paramColor1, Color paramColor2)
/*    */   {
/* 17 */     this.myParent = paramField;
/* 18 */     this.myUnmarkedColor = paramColor1;
/* 19 */     this.myMarkedColor = paramColor2;
/* 20 */     setMaximumSize(this.myParent.getSize());
/* 21 */     setMinimumSize(this.myParent.getSize());
/* 22 */     setPreferredSize(this.myParent.getSize());
/* 23 */     Field.access$002(this.myParent, this);
/*    */   }
/*    */   
/*    */   public void paint(Graphics paramGraphics) {
/* 27 */     this.myParent.paintMe(paramGraphics, this.myMarkedColor, this.myUnmarkedColor);
/*    */   }
/*    */   
/*    */   public void mark(int paramInt1, int paramInt2) {
/* 31 */     Graphics localGraphics = getGraphics();
/* 32 */     localGraphics.setColor(this.myMarkedColor);
/* 33 */     localGraphics.fillRect(paramInt1, paramInt2, 1, 1);
/*    */   }
/*    */ }


/* Location:              /Users/lo/Desktop/presentation/LawnMover/AutoMower.jar!/Field$FieldPanel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */