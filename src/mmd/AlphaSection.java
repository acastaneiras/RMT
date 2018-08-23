/*
 * The MIT License
 *
 * Copyright 2018 RedEyedJealousy.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
/**
 *
 * @author Syloid/RedEyedJealousy
 */
package mmd;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AlphaSection extends javax.swing.JFrame {

    public static MaterialMakerv2 foo = new MaterialMakerv2();
    public static WindowFrame wf = new WindowFrame();

    String AlphaLoop = String.valueOf(getAlphaMapLoop());
    String AlbedoMapFile1;
    String lastAlphaLoop = String.valueOf(getAlphaMapLoop());

    private static int CatchAlphaMapFrom;
    private static int CatchAlphaMapUVFlip;
    private static int CatchAlphaMapSwizzle;

    private static String CatchAlphaMapFile = null;
    private static String Catchalpha;
    private static float CatchalphaMapLoop;
    public int errors = 0;
    JFrame ErrorWindow = new JFrame();

    public void SomethingWentWrong() {
        if (errors == 1) {
            JLabel ErrorWindowText = new JLabel();
            //ErrorWindowText.
            ErrorWindowText.setText("<HTML><div style='padding-left:30px;'>Something went wrong while trying to load <i>Alpha Section</i>...<br><br>"
                    + "Please make sure the file you are trying to open doesn't <b>exceed the limit for each parameter</b>, usually this happens when you are trying to open "
                    + "a .fx file where some of it's parameters has <b>higher values</b> than supposed to be<br><br>"
                    + "<b>Limits: </b><br>"
                    + "<ul><li>AlphaMapFrom: 0 - 8</li>"
                    + "<li>AlphaMapUVFlip: 0 - 3</li>"
                    + "<li>AlphaMapApplySwizzle: 0 - 3</li>"
                    + "<br>"
                    + "</ul></div></HTML>");
            ErrorWindow.setLayout(new BorderLayout());
            ErrorWindow.setSize(700, 350);
            ErrorWindow.setLocationRelativeTo(this);
            ErrorWindow.setResizable(true);
            ErrorWindow.setAlwaysOnTop(true);
            ErrorWindow.setVisible(true);
            ErrorWindow.setName("help");
            ErrorWindow.add(ErrorWindowText);
            ErrorWindow.setTitle("Something went wrong");
            ErrorWindow.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            ErrorWindow.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent we) {
                    ErrorWindow.dispose();
                }
            });

            try {
                InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
                BufferedImage myImg = ImageIO.read(imgStream);
                ErrorWindow.setIconImage(myImg);
            } catch (IOException ex) {
                Logger.getLogger(AlbedoSection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int getAlphaMapFrom() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());

            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define ALPHA_MAP_FROM")) {

                    CatchAlphaMapFrom = Integer.parseInt(s.replaceAll("[\\D]", ""));

                }
                s = AlbedotoEdit_Br.readLine();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(WindowFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WindowFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                AlbedotoEdit_Br.close();
            } catch (IOException ex) {
                Logger.getLogger(WindowFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return CatchAlphaMapFrom;
    }

    public int getAlphaMapUVFlip() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define ALPHA_MAP_UV_FLIP")) {
                    CatchAlphaMapUVFlip = Integer.parseInt(s.replaceAll("[\\D]", ""));
                }
                s = AlbedotoEdit_Br.readLine();
            }

        } catch (Exception e) {

        } finally {
            try {
                AlbedotoEdit_Br.close();
            } catch (IOException ex) {
                Logger.getLogger(WindowFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return CatchAlphaMapUVFlip;
    }

    public int getAlbedoMapSwizzle() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define ALPHA_MAP_SWIZZLE")) {
                    CatchAlphaMapSwizzle = Integer.parseInt(s.replaceAll("[\\D]", ""));

                }
                s = AlbedotoEdit_Br.readLine();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(WindowFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WindowFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                AlbedotoEdit_Br.close();
            } catch (Exception e) {

            }
        }
        return CatchAlphaMapSwizzle;
    }

    public String getAlphaMapFile() {
        BufferedReader AlbedotoEdit_Br = null;

        try {

            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define ALPHA_MAP_FILE")) {
                    CatchAlphaMapFile = "";
                    for (int i = s.indexOf('"') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';') {

                        } else {
                            CatchAlphaMapFile += s.charAt(i);
                        }

                    }

                    CatchAlphaMapFile = CatchAlphaMapFile.replace("null", "");

                }
                s = AlbedotoEdit_Br.readLine();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(WindowFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WindowFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                AlbedotoEdit_Br.close();
            } catch (Exception e) {

            }
        }
        return CatchAlphaMapFile;
    }

    public String getalpha() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("alpha =")) {
                    String txt = "";
                    for (int i = s.indexOf('=') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';' || s.charAt(i) == ' ') {

                        } else {
                            txt += s.charAt(i);
                        }
                    }
                    Catchalpha = (txt);
                }
                s = AlbedotoEdit_Br.readLine();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(WindowFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WindowFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                AlbedotoEdit_Br.close();
            } catch (Exception e) {

            }
        }
        return Catchalpha;
    }

    public float getAlphaMapLoop() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("alphaMapLoopNum")) {
                    String txt = "";
                    for (int i = s.indexOf('=') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';' || s.charAt(i) == ' ') {

                        } else {
                            txt += s.charAt(i);
                        }
                    }
                    CatchalphaMapLoop = Float.parseFloat(txt);
                }
                s = AlbedotoEdit_Br.readLine();

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(WindowFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WindowFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                AlbedotoEdit_Br.close();
            } catch (Exception e) {

            }
        }
        return CatchalphaMapLoop;
    }

    /**
     * Creates new form WindowFrame
     */
    public AlphaSection() {//Constructor

        initComponents();
        myInitComponents();
    }

    private static void Nimbus() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
    }

    public void myInitComponents() {

        final DecimalFormat df = new DecimalFormat("0.####");
        final JTextField text = new JTextField(20);

        final DoubleJSlider slider = new DoubleJSlider(-6400, 6400, 0, 100);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                try {

                    text.setText(df.format(slider.getScaledValue()));
                    AlphaLoop = text.getText();
                    if (!slider.getValueIsAdjusting() && !AlphaLoop.equalsIgnoreCase(lastAlphaLoop)) {
                        lastAlphaLoop = AlphaLoop;
                        BufferedReader br = null;

                        try {
                            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                            String oldtext = "";
                            String line = br.readLine();
                            String catchOld = "";
                            String catchNew = "";
                            while (line != null) {
                                if (line.contains("alphaMapLoopNum")) {
                                    catchOld = line; //auxiliar line
                                    String aux = "";
                                    for (int i = catchOld.indexOf('=') + 1; i < catchOld.length() - 1; i++) {
                                        if (catchOld.charAt(i) == ' ' || catchOld.charAt(i) == ';' || catchOld.charAt(i) == '"') {

                                        } else {
                                            aux += catchOld.charAt(i);
                                        }
                                    }
                                    catchOld = aux;
                                    if (!catchOld.equalsIgnoreCase(AlphaLoop)) {
                                        AlphaLoop = AlphaLoop.replace(",", ".");
                                        catchNew = AlphaLoop; //catchnewdigit
                                        line = "const float alphaMapLoopNum = " + catchNew + ";";
                                    }
                                }
                                oldtext += line + "\r\n";

                                line = br.readLine();
                            }
                            String newtext = oldtext;

                            FileWriter writer = new FileWriter(foo.getFileToEdit());
                            writer.write(newtext);
                            writer.close();
                        } catch (Exception ex) {

                        } finally {
                            try {
                                br.close();
                            } catch (Exception ea) {

                            }
                        }
                    }
                } catch (Exception ex) {
                }
            }
        }
        );
        text.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke
            ) {
                String typed = text.getText();

                if (!typed.matches("\\d+(\\.\\d*)?")) {
                    return;
                }
                double value = Double.parseDouble(typed) * slider.scale;
                slider.setValue((int) value);
                AlphaLoop = text.getText();
            }
        }
        );
        float catchvalue;
        catchvalue = Float.parseFloat("" + getAlphaMapLoop()) * slider.scale;

        slider.setPaintTrack(true);
        slider.setPaintLabels(true);
        slider.setBounds(271, 336, 200, 30);
        slider.setPaintTicks(true);
        slider.setValue((int) catchvalue
        );
        text.setBounds(474, 336, 50, 26);
        text.setFont(new Font("Arial", Font.PLAIN, 11));

        add(text);

        add(slider);
    }

    public class DoubleJSlider extends JSlider {

        final int scale;

        public DoubleJSlider(int min, int max, int value, int scale) {
            super(min, max, value);
            this.scale = scale;
        }

        public double getScaledValue() {
            return ((double) super.getValue()) / this.scale;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jLabel1 = new javax.swing.JLabel();
        AlphaMapFrom = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        changeFile = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        Alpha = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        AlphaMapUVFlip = new javax.swing.JComboBox<>();
        AlphaMapSwizzle = new javax.swing.JComboBox<>();
        back = new javax.swing.JButton();
        AlbedoMapHelp = new javax.swing.JButton();
        AlbedoMapUVFlipHelp = new javax.swing.JButton();
        AlbedoMapApplyScaleHelp = new javax.swing.JButton();
        AlbedoMapFileHelp = new javax.swing.JButton();
        albedoHelp = new javax.swing.JButton();
        AlbedoMapLoopHelp = new javax.swing.JButton();
        AlphaMapFile = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Edit Alpha of "+foo.getFileToEdit().getName());
        setAutoRequestFocus(false);
        setResizable(false);
        setSize(new java.awt.Dimension(960, 540));

        jLabel1.setText("<html><b>ALPHA MAP FROM</b></html>");

        AlphaMapFrom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8" }));
        try{
            AlphaMapFrom.setSelectedIndex(getAlphaMapFrom());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        AlphaMapFrom.setToolTipText("");
        AlphaMapFrom.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                AlphaMapFromItemStateChanged(evt);
            }
        });
        AlphaMapFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlphaMapFromActionPerformed(evt);
            }
        });

        jLabel2.setText("<html><b>ALPHA MAP UV FLIP</b></html>");

        jLabel3.setText("<html><b>ALPHA MAP APPLY SWIZZLE</b></html>");

        jLabel6.setText("<html><b>ALPHA MAP FILE</b></html>");

        changeFile.setText("...");
        changeFile.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                changeFileStateChanged(evt);
            }
        });
        changeFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeFileActionPerformed(evt);
            }
        });

        jLabel8.setText("<html><b>ALPHA</b></html>");

        Alpha.setText(String.valueOf(getalpha())
        );
        Alpha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlphaActionPerformed(evt);
            }
        });
        Alpha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                AlphaKeyReleased(evt);
            }
        });

        jLabel9.setText("<html><b>ALPHA MAP LOOP</b></html>");

        AlphaMapUVFlip.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        try{
            AlphaMapUVFlip.setSelectedIndex(getAlphaMapUVFlip());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        AlphaMapUVFlip.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                AlphaMapUVFlipItemStateChanged(evt);
            }
        });
        AlphaMapUVFlip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlphaMapUVFlipActionPerformed(evt);
            }
        });

        AlphaMapSwizzle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        try{
            AlphaMapSwizzle.setSelectedIndex(getAlbedoMapSwizzle());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        AlphaMapSwizzle.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                AlphaMapSwizzleItemStateChanged(evt);
            }
        });
        AlphaMapSwizzle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlphaMapSwizzleActionPerformed(evt);
            }
        });

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bg/back.png"))); // NOI18N
        back.setBorder(null);
        back.setContentAreaFilled(false);
        back.setFocusable(false);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        AlbedoMapHelp.setText("Help");
        AlbedoMapHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapHelpActionPerformed(evt);
            }
        });

        AlbedoMapUVFlipHelp.setText("Help");
        AlbedoMapUVFlipHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapUVFlipHelpActionPerformed(evt);
            }
        });

        AlbedoMapApplyScaleHelp.setText("Help");
        AlbedoMapApplyScaleHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapApplyScaleHelpActionPerformed(evt);
            }
        });

        AlbedoMapFileHelp.setText("Help");
        AlbedoMapFileHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapFileHelpActionPerformed(evt);
            }
        });

        albedoHelp.setText("Help");
        albedoHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albedoHelpActionPerformed(evt);
            }
        });

        AlbedoMapLoopHelp.setText("Help");
        AlbedoMapLoopHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapLoopHelpActionPerformed(evt);
            }
        });

        try{
            AlphaMapFile.setEditable(false);
            AlphaMapFile.setText(getAlphaMapFile());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }

        jButton3.setText("Preview");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(AlphaMapFile, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(AlbedoMapLoopHelp))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(Alpha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(albedoHelp)
                                            .addGap(11, 11, 11)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(AlphaMapSwizzle, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(128, 128, 128)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(AlphaMapFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(AlphaMapUVFlip, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(changeFile, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(AlbedoMapHelp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(AlbedoMapUVFlipHelp)
                                    .addComponent(AlbedoMapApplyScaleHelp)
                                    .addComponent(AlbedoMapFileHelp))))))
                .addContainerGap(259, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlphaMapFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapHelp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlphaMapUVFlip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapUVFlipHelp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlphaMapSwizzle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapApplyScaleHelp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeFile)
                    .addComponent(AlbedoMapFileHelp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AlphaMapFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Alpha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(albedoHelp))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapLoopHelp))
                .addContainerGap(158, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AlphaMapFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlphaMapFromActionPerformed

    }//GEN-LAST:event_AlphaMapFromActionPerformed

    private void AlphaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlphaActionPerformed

    }//GEN-LAST:event_AlphaActionPerformed

    private void AlphaMapUVFlipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlphaMapUVFlipActionPerformed

    }//GEN-LAST:event_AlphaMapUVFlipActionPerformed

    private void AlphaMapSwizzleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlphaMapSwizzleActionPerformed

    }//GEN-LAST:event_AlphaMapSwizzleActionPerformed

    private void changeFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeFileActionPerformed

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new java.io.File("../../Materials"));//The directory we are looking for is the Materials folder from ray
        fileChooser.setDialogTitle("Choose new Alpha Map File");
        //Filtering by ImageFiles
        FileFilter imageFilter = new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp", "tga", "targa", "dds", "tif", "tiff", "jpeg", "pcd");
        fileChooser.setFileFilter(imageFilter);

        try {
            int selection = fileChooser.showOpenDialog(this);
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            filePath = filePath.replace("\\", "/");

            if (selection == 0) {
                String relative = toRelative.convertToRelativePath(foo.getFileToEdit().getParent(), filePath);
                AlphaMapFile.setText(relative);
                if (AlphaMapFrom.getSelectedIndex() != 1) {
                    AlphaMapFrom.setSelectedIndex(1);
                }
                BufferedReader br = null;

                try {
                    br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                    String oldtext = "";
                    String line = br.readLine();
                    String catchOld = "";
                    String catchNew = "";
                    while (line != null) {
                        if (line.contains("#define ALPHA_MAP_FILE")) {
                            catchOld = line; //auxiliar line
                            String aux = "";
                            for (int i = catchOld.indexOf('"') + 1; i < line.length(); i++) {
                                if (catchOld.charAt(i) == '"') {
                                    break;
                                } else if (catchOld.charAt(i) == ';' || catchOld.charAt(i) == '"') {

                                } else {
                                    aux += catchOld.charAt(i);
                                }
                            }
                            catchOld = aux;
                            if (!catchOld.equalsIgnoreCase(AlphaMapFile.getText())) {
                                catchNew = AlphaMapFile.getText(); //catchnewdigit
                                line = "#define ALPHA_MAP_FILE " + '"' + catchNew + '"';
                            }
                        }
                        oldtext += line + "\r\n";

                        line = br.readLine();
                    }
                    String newtext = oldtext;

                    FileWriter writer = new FileWriter(foo.getFileToEdit());
                    writer.write(newtext);
                    writer.close();
                } catch (Exception ex) {

                } finally {
                    try {
                        br.close();
                    } catch (Exception e) {

                    }
                }
            }
        } catch (Exception e) {
            //wont happen hahaaaaaa
        }
    }//GEN-LAST:event_changeFileActionPerformed

    private void changeFileStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_changeFileStateChanged

    }//GEN-LAST:event_changeFileStateChanged
    private void closeAllDialogs() {
        Window[] windows = AlbedoSection.getWindows();

        for (Window window : windows) {
            if (window.getName().equalsIgnoreCase("help")) {
                window.dispose();
            }
        }
    }
    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        closeAllDialogs();
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_backActionPerformed

    private void AlbedoMapHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapHelpActionPerformed
        JFrame AlbedoMapHelp = new JFrame();
        JLabel AlbedoMapHelpText = new JLabel();
        AlbedoMapHelp.setName("help");

        AlbedoMapHelpText.setText("<HTML><div><pre style='font-family: Arial;'><center><b>It has no effect on opaque objects</b><br><br>"
                + "You can use a color and texture to change colors in your model by set the code to the ALBEDO_MAP_FROM.<br><br>"
                + "<b>Tips 1 :</b> The albedo is also called Base Color, default data will fetched params from texture from the pmx.<br>"
                + "<b>Tips 2 :</b> Do not enter a path with HDR file, that will be ignore the HDR and linear color-space<br>"
                + "<b>Tips 3 :</b> These files (bmp, png, jpg, tga, dds, gif, apng) must be working in a sRGB color-space<br>"
                + "<b>Tips 4 :</b> You can ignore the Tips 3 because most of the images are working in this color-gamut</center></div><br><br>"
                + "<ul>    <li>0 : Params fetch from a color from the const float3 albedo = 1.0.</li><br>"
                + "    <li>1 : You can use an image (bmp, png, jpg, tga, dds) by enter a relative and absolutely path to the ALBEDO_MAP_FILE.</li><br>"
                + "    <li>2 : You can use an animation image (gif, apng) by enter a relative and absolutely path to the ALBEDO_MAP_FILE.</li><br>"
                + "    <li>3 : Params fetch from Texture from the pmx.</li><br>"
                + "    <li>4 : Params fetch from Sphere map from the pmx.</li><br>"
                + "    <li>5 : Params fetch from Toon map from the pmx.</li><br>"
                + "    <li>6 : Params fetch from avi/screen from the DummyScreen.x inside extension folder.</li><br>"
                + "    <li>7 : Params fetch from Ambient Color from the pmx.</li><br>"
                + "    <li>8 : Params fetch from Specular Color from the pmx.</li><br>"
                + "    <li><strike>9 : Params fetch from Specular Power from the pmx. (this option can only be used for specular)</strike>, doesn't work on Alpha</li></ul></pre><br><br></HTML>");
        AlbedoMapHelp.setLayout(new BorderLayout());
        AlbedoMapHelp.setSize(700, 550);
        AlbedoMapHelp.setLocationRelativeTo(this);
        AlbedoMapHelp.setResizable(true);
        AlbedoMapHelp.setVisible(true);
        AlbedoMapHelp.add(AlbedoMapHelpText);

        AlbedoMapHelp.setTitle("Alpha Map From");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            AlbedoMapHelp.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapHelpActionPerformed

    private void AlbedoMapUVFlipHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapUVFlipHelpActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");

        helptext.setText("<HTML><center>You can flip your texture for the X and Y axis mirror by set code to the <b>ALBEDO_MAP_UV_FLIP</b></center><br><br>"
                + "<ul><li><b>1 :</b> Flip axis x</li>"
                + "<li><b>2 :</b> Flip axis y</li>"
                + "<li><b>3 :</b> Flip axis x & y</li></ul></HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(500, 160);
        help.setLocationRelativeTo(this);
        help.setResizable(true);
        help.setVisible(true);
        help.add(helptext);

        help.setTitle("Alpha Map UV Flip Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapUVFlipHelpActionPerformed

    private void AlbedoMapApplyScaleHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapApplyScaleHelpActionPerformed
        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");

        helptext.setText("<HTML>The ordering of the data fetched from a texture from the code. (R = 0, G = 1, B = 2, A = 3)</HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(500, 160);
        help.setLocationRelativeTo(this);
        help.setResizable(true);
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Alpha Map Apply Scale Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapApplyScaleHelpActionPerformed

    private void AlbedoMapFileHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapFileHelpActionPerformed
        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        helptext.setText("<HTML>If the ALBEDO_MAP_FROM is 1 or 2, you will need to enter the path to the texture resource. <br><br>"
                + "Tips : parent folder ref is '../' (in other words, using '../' instead of parent folder), and change all '\\' to '/'.<br><br>"
                + "For example : <br>"
                + "If the xxx.png and material.fx is inside same folder<br>"
                + "You can set the xxx.png to the ALBEDO_MAP_FILE like : #define ALBEDO_MAP_FILE 'xxx.png'<br>"
                + "If the xxx.png is inside parent path of the material.fx<br>"
                + "You can set the xxx.png to the ALBEDO_MAP_FILE like : #define ALBEDO_MAP_FILE '../xxx.png'<br>"
                + "If the xxx.png is inside other path from parent path of the material.fx<br>"
                + "You can set the xxx.png to the ALBEDO_MAP_FILE like : #define ALBEDO_MAP_FILE '../other path/xxx.png'<br>"
                + "If the xxx.png is inside your desktop or other disk<br>"
                + "You can set the xxx.png to the ALBEDO_MAP_FILE like : #define ALBEDO_MAP_FILE 'C:/Users/User Name/Desktop/xxx.png'</HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(600, 350);
        help.setLocationRelativeTo(this);
        help.setResizable(true);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Alpha Map File Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapFileHelpActionPerformed

    private void albedoHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albedoHelpActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        helptext.setText("<HTML>between 0 ~ 1</HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(300, 70);
        help.setLocationRelativeTo(this);
        help.setResizable(true);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Alpha Map Scale Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_albedoHelpActionPerformed

    private void AlbedoMapLoopHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapLoopHelpActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        helptext.setText("<HTML>You can tile your texture for the X and Y axis separately by change albedoMapLoopNum = float2(x, y) between float2(0, 0) ~ float2(inf, inf) </HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(600, 200);
        help.setLocationRelativeTo(this);
        help.setResizable(true);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Alpha Map Loop Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapLoopHelpActionPerformed

    private void AlphaMapFromItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_AlphaMapFromItemStateChanged
        // TODO add your handling code here:
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define ALPHA_MAP_FROM")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = AlphaMapFrom.getSelectedItem().toString(); //catchnewdigit
                    line = line.replaceAll(catchOld, catchNew); //replace old for the new one

                }
                oldtext += line + "\r\n";

                line = br.readLine();
            }
            String newtext = oldtext;

            FileWriter writer = new FileWriter(foo.getFileToEdit());
            writer.write(newtext);
            writer.close();
        } catch (Exception ex) {

        } finally {
            try {
                br.close();
            } catch (Exception e) {

            }
        }
    }//GEN-LAST:event_AlphaMapFromItemStateChanged

    private void AlphaMapUVFlipItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_AlphaMapUVFlipItemStateChanged
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define ALPHA_MAP_UV_FLIP")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = AlphaMapUVFlip.getSelectedItem().toString(); //catchnewdigit
                    line = line.replaceAll(catchOld, catchNew); //replace old for the new one

                }
                oldtext += line + "\r\n";

                line = br.readLine();
            }
            String newtext = oldtext;

            FileWriter writer = new FileWriter(foo.getFileToEdit());
            writer.write(newtext);
            writer.close();
        } catch (Exception ex) {

        } finally {
            try {
                br.close();
            } catch (Exception e) {

            }
        }
    }//GEN-LAST:event_AlphaMapUVFlipItemStateChanged

    private void AlphaMapSwizzleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_AlphaMapSwizzleItemStateChanged
        // TODO add your handling code here:
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define ALPHA_MAP_SWIZZLE")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = AlphaMapSwizzle.getSelectedItem().toString(); //catchnewdigit
                    line = line.replaceAll("" + catchOld, "" + catchNew); //replace old for the new one
                }
                oldtext += line + "\r\n";

                line = br.readLine();
            }
            String newtext = oldtext;

            FileWriter writer = new FileWriter(foo.getFileToEdit());
            writer.write(newtext);
            writer.close();
        } catch (Exception ex) {

        } finally {
            try {
                br.close();
            } catch (Exception e) {

            }
        }
    }//GEN-LAST:event_AlphaMapSwizzleItemStateChanged

    private void AlphaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlphaKeyReleased
        String typed = Alpha.getText();
        if (typed.matches("\\d+(\\.\\d*)?")) {
            BufferedReader br = null;

            try {
                br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                String oldtext = "";
                String line = br.readLine();
                String catchOld = "";
                String catchNew = "";
                while (line != null) {
                    if (line.contains("alpha ")) {
                        catchOld = line;
                        catchNew = Alpha.getText();
                        catchNew = catchNew.replaceAll(" ", "");

                        line = ("const float alpha = " + catchNew + ";");
                    }
                    oldtext += line + "\r\n";

                    line = br.readLine();
                }
                String newtext = oldtext;

                FileWriter writer = new FileWriter(foo.getFileToEdit());
                writer.write(newtext);
                writer.close();
            } catch (Exception ex) {

            } finally {
                try {
                    br.close();
                } catch (Exception e) {

                }
            }
        }
    }//GEN-LAST:event_AlphaKeyReleased
    public void previewImg(String path) {
        File f = new File(path);
        File a = new File(foo.getFilePath());
        File parentFolder = new File(a.getParent());
        File b = new File(parentFolder, path);
        String absolute = "";
        try {
            absolute = b.getCanonicalPath();
            f = new File(absolute);
        } catch (Exception e) {

        }
        if (f.exists()) {
            JDialog jf = new JDialog();
            JLabel jl = new JLabel();
            jf.setName("help");
            jf.setTitle("Map Preview");
            try {//Icon
                InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
                BufferedImage myImg = ImageIO.read(imgStream);
                jf.setIconImage(myImg);
            } catch (IOException ex) {
                Logger.getLogger(AlbedoSection.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedImage img = null;
            try {//Map
                img = ImageIO.read(new File(absolute));
            } catch (IOException e) {
                e.printStackTrace();
            }
            jf.setSize(600, 600);
            jf.setResizable(false);
            jf.setModal(true);
            jf.setLocationRelativeTo(this);
            jf.setAlwaysOnTop(true);
            jf.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            Image dimg = img.getScaledInstance(jf.getWidth(), jf.getHeight(),
                    Image.SCALE_SMOOTH);
            ImageIcon ii = new ImageIcon(dimg);
            jl.setIcon(ii);
            jf.add(jl);
            jf.setVisible(true);
        } else {
            JDialog jd = new JDialog();
            JLabel jl = new JLabel();
            jl.setText("<html><div style='padding-left: 12px;'>The Map file you are trying to preview doesn't exist.</div></html>");
            jd.setName("help");
            jd.setTitle("No such Map File");
            jd.setSize(300, 100);
            jd.setModal(true);
            jd.setResizable(false);
            jd.setLocationRelativeTo(this);
            jd.setAlwaysOnTop(true);
            jd.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            try {//Icon
                InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
                BufferedImage myImg = ImageIO.read(imgStream);
                jd.setIconImage(myImg);
            } catch (IOException ex) {
                Logger.getLogger(AlbedoSection.class.getName()).log(Level.SEVERE, null, ex);
            }
            jd.add(jl);
            jd.setVisible(true);

        }
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        previewImg(AlphaMapFile.getText());
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WindowFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WindowFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WindowFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WindowFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /*AAAAAAAAAAAAAAAAAAAAAAAA*/

 /*AAAAAAAAAAAAAAAAAAAAAAAAA*/
 /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AlbedoSection().setVisible(true);
                new AlbedoSection().setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AlbedoMapApplyScaleHelp;
    private javax.swing.JButton AlbedoMapFileHelp;
    private javax.swing.JButton AlbedoMapHelp;
    private javax.swing.JButton AlbedoMapLoopHelp;
    private javax.swing.JButton AlbedoMapUVFlipHelp;
    private javax.swing.JTextField Alpha;
    private javax.swing.JTextField AlphaMapFile;
    private javax.swing.JComboBox<String> AlphaMapFrom;
    private javax.swing.JComboBox<String> AlphaMapSwizzle;
    private javax.swing.JComboBox<String> AlphaMapUVFlip;
    private javax.swing.JButton albedoHelp;
    private javax.swing.JButton back;
    private javax.swing.JButton changeFile;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    // End of variables declaration//GEN-END:variables
}
