/*     */ import java.awt.Dimension;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class AutoMowerSimulation extends java.applet.Applet implements Runnable, AutoMowerConstants
/*     */ {
/*     */   private JTextField myBounceDelta;
/*     */   private JTextField myTotalBounces;
/*     */   private JTextField myArea;
/*     */   private JTextField myCost;
/*     */   private JTextField myTotalCost;
/*     */   private JTextField myMowerLocationField;
/*     */   private JTextField myHeadingField;
/*     */   private JTextField myTotalDistanceField;
/*     */   private JTextField myCurrentBouncesField;
/*     */   private JButton myStartButton;
/*     */   private JButton myStopButton;
/*     */   private JButton myResetButton;
/*     */   private JCheckBox myFastForwardSwitch;
/*     */   private AutoMowerConstants.AutoMowerDefaults mySettings;
/*     */   private Field myField;
/*     */   private DeflectingParticle myMower;
/*     */   private Rectangle myMowerBox;
/*     */   private volatile Thread myThread;
/*     */   private boolean myInsideRun;
/*     */   
/*     */   public AutoMowerSimulation()
/*     */   {
/*  37 */     this(new AutoMowerConstants.AutoMowerDefaults());
/*     */   }
/*     */   
/*     */ 
/*     */   public AutoMowerSimulation(AutoMowerConstants.AutoMowerDefaults paramAutoMowerDefaults)
/*     */   {
/*  43 */     this.mySettings = paramAutoMowerDefaults;
/*  44 */     this.myField = new Field(this.mySettings.getInt("FieldWidth").intValue(), this.mySettings.getInt("FieldHeight").intValue());
/*  45 */     this.myMower = new DeflectingParticle();
/*  46 */     this.myMowerBox = ((Rectangle)((Rectangle)this.mySettings.getObject("MowerBox")).clone());
/*  47 */     this.myThread = null;
/*  48 */     this.myInsideRun = false;
/*  49 */     freeBorder();
/*     */     
/*  51 */     this.myBounceDelta = new JTextField("30");
/*  52 */     this.myTotalBounces = new JTextField("6");
/*  53 */     this.myArea = new JTextField();
/*  54 */     this.myArea.setEditable(false);
/*  55 */     this.myCost = new JTextField();
/*  56 */     this.myCost.setEditable(false);
/*  57 */     this.myTotalCost = new JTextField();
/*  58 */     this.myTotalCost.setEditable(false);
/*  59 */     this.myMowerLocationField = new JTextField();
/*  60 */     this.myMowerLocationField.setEditable(false);
/*  61 */     this.myHeadingField = new JTextField();
/*  62 */     this.myHeadingField.setEditable(false);
/*  63 */     this.myTotalDistanceField = new JTextField();
/*  64 */     this.myTotalDistanceField.setEditable(false);
/*  65 */     this.myCurrentBouncesField = new JTextField();
/*  66 */     this.myCurrentBouncesField.setEditable(false);
/*     */     
/*  68 */     this.myStartButton = new JButton("Start");
/*  69 */     this.myStartButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/*  71 */         AutoMowerSimulation.this.start();
/*     */       }
/*  73 */     });
/*  74 */     this.myStopButton = new JButton("Stop");
/*  75 */     this.myStopButton.setEnabled(false);
/*  76 */     this.myStopButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/*  78 */         AutoMowerSimulation.this.stop();
/*     */       }
/*  80 */     });
/*  81 */     this.myResetButton = new JButton("Reset");
/*  82 */     this.myResetButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/*  84 */         AutoMowerSimulation.this.reset();
/*     */       }
/*  86 */     });
/*  87 */     this.myFastForwardSwitch = new JCheckBox("(FF)", false);
/*     */     
/*     */ 
/*  90 */     JPanel localJPanel1 = new JPanel();
/*  91 */     localJPanel1.setLayout(new java.awt.FlowLayout(1));
/*  92 */     localJPanel1.add(new JLabel("Deflection:"));
/*  93 */     localJPanel1.add(this.myBounceDelta);
/*  94 */     localJPanel1.add(new JLabel("Total Passes:"));
/*  95 */     localJPanel1.add(this.myTotalBounces);
/*  96 */     localJPanel1.add(javax.swing.Box.createHorizontalStrut(50));
/*  97 */     localJPanel1.add(this.myFastForwardSwitch);
/*  98 */     localJPanel1.add(this.myStartButton);
/*  99 */     localJPanel1.add(this.myStopButton);
/* 100 */     localJPanel1.add(this.myResetButton);
/* 101 */     this.myBounceDelta.setPreferredSize(new Dimension(75, 20));
/* 102 */     this.myTotalBounces.setPreferredSize(new Dimension(75, 20));
/*     */     
/* 104 */     JPanel localJPanel2 = new JPanel();
/* 105 */     localJPanel2.setLayout(new java.awt.FlowLayout(1));
/*     */     
/* 107 */     JPanel localJPanel3 = new JPanel();
/* 108 */     localJPanel3.setLayout(new BoxLayout(localJPanel3, 1));
/* 109 */     localJPanel3.add(new JLabel("Mower Status:"));
/* 110 */     localJPanel3.add(this.myMowerLocationField);
/* 111 */     localJPanel3.add(this.myHeadingField);
/* 112 */     localJPanel3.add(this.myCurrentBouncesField);
/* 113 */     localJPanel2.add(localJPanel3);
/*     */     
/* 115 */     localJPanel3 = new JPanel();
/* 116 */     localJPanel3.setLayout(new BoxLayout(localJPanel3, 1));
/* 117 */     localJPanel3.add(new JLabel("Mower distance ($" + this.mySettings.getObject("MowerCost") + "/unit):"));
/* 118 */     localJPanel3.add(this.myTotalDistanceField);
/* 119 */     localJPanel3.add(new JLabel("Remaining ($" + this.mySettings.getObject("HumanCost") + "/unit):"));
/* 120 */     localJPanel3.add(this.myArea);
/* 121 */     localJPanel2.add(localJPanel3);
/*     */     
/* 123 */     localJPanel3 = new JPanel();
/* 124 */     localJPanel3.setLayout(new BoxLayout(localJPanel3, 1));
/* 125 */     localJPanel3.add(new JLabel("Costs:"));
/* 126 */     localJPanel3.add(this.myCost);
/* 127 */     localJPanel3.add(new JLabel("Total Cost:"));
/* 128 */     localJPanel3.add(this.myTotalCost);
/* 129 */     localJPanel2.add(localJPanel3);
/*     */     
/*     */ 
/* 132 */     setLayout(new java.awt.BorderLayout());
/* 133 */     localJPanel3 = new JPanel();
/* 134 */     localJPanel3.add(new Field.FieldPanel(this.myField, (java.awt.Color)this.mySettings.getObject("UncoveredColor"), (java.awt.Color)this.mySettings.getObject("CoveredColor")));
/* 135 */     localJPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(0));
/* 136 */     add(localJPanel3, "Center");
/* 137 */     add(localJPanel1, "North");
/* 138 */     add(localJPanel2, "South");
/*     */     
/* 140 */     localJPanel3 = new JPanel();
/* 141 */     localJPanel3.setPreferredSize(new Dimension(50, 50));
/* 142 */     add(localJPanel3, "East");
/* 143 */     localJPanel3 = new JPanel();
/* 144 */     localJPanel3.setPreferredSize(new Dimension(50, 50));
/* 145 */     add(localJPanel3, "West");
/*     */     
/* 147 */     recalculateTotals();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void recalculateTotals()
/*     */   {
/* 154 */     int k = this.myField.getMaximumMarks();
/* 155 */     int i = this.myField.getTotalMarks();
/* 156 */     int j = k - i;
/* 157 */     int m = (int)this.myMower.getTotalDistance();
/* 158 */     this.myArea.setText(j + " units");
/* 159 */     this.myCost.setText("$" + (int)(m * ((Double)this.mySettings.getObject("MowerCost")).doubleValue()) + " + $" + (int)(j * ((Double)this.mySettings.getObject("HumanCost")).doubleValue()));
/* 160 */     this.myTotalCost.setText("$" + (int)(m * ((Double)this.mySettings.getObject("MowerCost")).doubleValue() + j * ((Double)this.mySettings.getObject("HumanCost")).doubleValue()));
/* 161 */     this.myMowerLocationField.setText("[" + (int)this.myMower.getX() + "," + (int)this.myMower.getY() + "]");
/* 162 */     this.myHeadingField.setText("" + (int)this.myMower.getHeadingDegrees());
/* 163 */     this.myTotalDistanceField.setText("" + (int)this.myMower.getTotalDistance() + " units");
/* 164 */     this.myCurrentBouncesField.setText(this.myMower.getDeflectionCount() + " passes");
/*     */   }
/*     */   
/*     */   public void freeBorder() {
/*     */     int j;
/* 169 */     for (int i = 0; i < this.myField.getWidth(); i++) {
/* 170 */       for (j = 0; j < 5; j++) {
/* 171 */         this.myField.mark(i, j);
/* 172 */         this.myField.mark(i, (int)this.myField.getHeight() - j);
/*     */       }
/*     */     }
/* 175 */     for (i = 0; i < this.myField.getHeight(); i++) {
/* 176 */       for (j = 0; j < 5; j++) {
/* 177 */         this.myField.mark(j, i);
/* 178 */         this.myField.mark((int)this.myField.getWidth() - j, i);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void reset() {
/* 184 */     this.myInsideRun = false;
/* 185 */     this.myField.reset();
/* 186 */     this.myMower.reset();
/* 187 */     this.myMowerBox = ((Rectangle)((Rectangle)this.mySettings.getObject("MowerBox")).clone());
/* 188 */     recalculateTotals();
/* 189 */     this.myBounceDelta.setEditable(true);
/* 190 */     this.myTotalBounces.setEditable(true);
/* 191 */     freeBorder();
/* 192 */     repaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void start()
/*     */   {
/* 199 */     if (this.myThread != null) {
/* 200 */       return;
/*     */     }
/*     */     try {
/* 203 */       Integer.parseInt(this.myTotalBounces.getText());
/* 204 */       Double.parseDouble(this.myBounceDelta.getText());
/*     */     } catch (NumberFormatException localNumberFormatException) {
/* 206 */       return;
/*     */     }
/*     */     
/* 209 */     this.myThread = new Thread(this);
/* 210 */     this.myThread.start();
/*     */   }
/*     */   
/*     */   public synchronized void stop() {
/* 214 */     this.myThread = null;
/*     */   }
/*     */   
/*     */ 
/* 218 */   private Object myLock = new Object();
/* 219 */   private boolean isBusy = false;
/*     */   
/*     */ 
/*     */ 
/*     */   public void run()
/*     */   {
/* 225 */     synchronized (this.myLock) {
/* 226 */       if (this.isBusy) {
/* 227 */         return;
/*     */       }
/* 229 */       this.isBusy = true;
/*     */     }
/* 231 */     this.myBounceDelta.setEditable(false);
/* 232 */     this.myTotalBounces.setEditable(false);
/* 233 */     this.myStartButton.setEnabled(false);
/* 234 */     this.myStopButton.setEnabled(true);
/* 235 */     this.myResetButton.setEnabled(false);
/* 236 */     this.myFastForwardSwitch.setEnabled(false);
/*     */     
/* 238 */     int i = Math.min(this.mySettings.getInt("MaxPasses").intValue(), Integer.parseInt(this.myTotalBounces.getText()));
/* 239 */     this.myTotalBounces.setText("" + i);
/* 240 */     this.myMower.setDeflectionDegrees(Double.parseDouble(this.myBounceDelta.getText()));
/* 241 */     if (!this.myInsideRun) {
/* 242 */       this.myMower.setHeading(this.myMower.getDeflection());
/* 243 */       this.myMowerBox.setLocation((int)(this.myMower.getX() - this.mySettings.getInt("MowerWidth").intValue() / 2.0D), (int)(this.myMower.getY() - this.mySettings.getInt("MowerHeight").intValue() / 2.0D));
/* 244 */       this.myField.markRectangle(this.myMowerBox, this.myMower.getHeading() - 1.5707963267948966D);
/*     */     }
/* 246 */     long l = this.myFastForwardSwitch.isSelected() ? 0 : this.mySettings.getInt("SlowDelay").intValue();
/*     */     
/* 248 */     this.myInsideRun = true;
/* 249 */     while ((this.myThread != null) && (this.myMower.getDeflectionCount() <= i)) {
/* 250 */       this.myMower.move(this.mySettings.getInt("MowerHeight").intValue() * 0.5D);
/* 251 */       this.myMowerBox.setLocation((int)(this.myMower.getX() - this.mySettings.getInt("MowerWidth").intValue() / 2.0D), (int)(this.myMower.getY() - this.mySettings.getInt("MowerHeight").intValue() / 2.0D));
/* 252 */       this.myField.markRectangle(this.myMowerBox, this.myMower.getHeading() - 1.5707963267948966D);
/* 253 */       if (this.myMower.reflect(this.myField)) {
/* 254 */         this.myMowerBox.setLocation((int)(this.myMower.getX() - this.mySettings.getInt("MowerWidth").intValue() / 2.0D), (int)(this.myMower.getY() - this.mySettings.getInt("MowerHeight").intValue() / 2.0D));
/* 255 */         this.myField.markRectangle(this.myMowerBox, this.myMower.getHeading() - 1.5707963267948966D);
/*     */       }
/* 257 */       recalculateTotals();
/*     */       try {
/* 259 */         Thread.sleep(l);
/*     */       }
/*     */       catch (Exception localException) {}
/*     */     }
/* 263 */     this.myStartButton.setEnabled(true);
/* 264 */     this.myStopButton.setEnabled(false);
/* 265 */     this.myResetButton.setEnabled(true);
/* 266 */     this.myFastForwardSwitch.setEnabled(true);
/* 267 */     this.myThread = null;
/* 268 */     this.isBusy = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 277 */     AutoMowerConstants.AutoMowerDefaults localAutoMowerDefaults = new AutoMowerConstants.AutoMowerDefaults();
/* 278 */     JFrame localJFrame = new JFrame();
/* 279 */     localJFrame.setTitle("Auto-Mower Simulation");
/* 280 */     localJFrame.setMinimumSize((Dimension)localAutoMowerDefaults.getObject("ScreenSize"));
/* 281 */     localJFrame.setPreferredSize((Dimension)localAutoMowerDefaults.getObject("ScreenSize"));
/* 282 */     localJFrame.setDefaultCloseOperation(3);
/* 283 */     localJFrame.setContentPane(new AutoMowerSimulation());
/* 284 */     localJFrame.setVisible(true);
/*     */   }
/*     */ }


/* Location:              /Users/lo/Desktop/presentation/LawnMover/AutoMower.jar!/AutoMowerSimulation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */