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
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SpecularAndSmoothness extends javax.swing.JFrame {

    public static MaterialMakerv2 foo = new MaterialMakerv2();
    public static WindowFrame wf = new WindowFrame();

    String SpecularScale = getSpecularScale();
    String SmoothnessScale = getSmoothnessScale();
    String SpecularLoop = String.valueOf(getSpecularLoop());
    String SmoothnessLoop = String.valueOf(getSmoothnessLoop());
    String AlbedoMapFile1;
    ////////////////////////////////////////////////////////////////
    String lastSmoothnessScale = getSmoothnessScale();
    String lastSmoothnessLoop = String.valueOf(getSmoothnessLoop());
    String lastSpecularScale = getSpecularScale();
    String lastSpecularLoop = String.valueOf(getSpecularLoop());

    /*Specular*/
    private static int CatchSpecularMapFrom;
    private static int CatchSpecularMapType;
    private static int CatchSpecularMapUVFlip;
    private static int CatchSpecularMapSwizzle;
    private static int CatchSpecularMapApplyScale;
    private static String CatchSpecularMapFile = null;
    private static String CatchSpecularScale;
    private static float CatchSpecularLoop;

    /*Smoothness*/
    private static int CatchSmoothnessMapFrom;
    private static int CatchSmoothnessMapUVFlip;
    private static int CatchSmoothnessMapSwizzle;
    private static int CatchSmoothnessMapApplyScale;
    private static String CatchSmoothnessMapFile = null;
    private static String CatchSmoothnessScale;
    private static float CatchSmoothnessLoop;
    public int errors = 0;
    JFrame ErrorWindow = new JFrame();

    public void SomethingWentWrong() {
        if (errors == 1) {
            JLabel ErrorWindowText = new JLabel();
            //ErrorWindowText.
            ErrorWindowText.setText("<HTML><div style='padding-left:30px;'>Something went wrong while trying to load <i>SpecularAndOcclussion Section</i>...<br><br>"
                    + "Please make sure the file you are trying to open doesn't <b>exceed the limit for each parameter</b>, usually this happens when you are trying to open "
                    + "a .fx file where some of it's parameters has <b>higher values</b> than supposed to be<br><br>"
                    + "<b>Limits for Specular: </b><br>"
                    + "<ul>"
                    + "<li>SpecularMapFrom: 0 - 8</li>"
                    + "<li>SpecularMapType: 0 - 1</li>"
                    + "<li>SpecularMapUVFlip: 0 - 3</li>"
                    + "<li>SpecularMapApplySwizzle: 0 - 3</li>"
                    + "<li>SpecularMapApplyScale: 0 - 2</li>"
                    + "<br>"
                    + "</ul>"
                    + "<b>Limits for Smoothness:</b>"
                    + "<ul>"
                    + "<li>SmoothnessMapFrom: 0 - 8</li>"
                    + "<li>SmoothnessMapType: 0 - 1</li>"
                    + "<li>SmoothnessMapUVFlip: 0 - 3</li>"
                    + "<li>SmoothnessMapApplySwizzle: 0 - 3</li>"
                    + "<li>SmoothnessMapApplyScale: 0 - 2</li>"
                    + "<br>"
                    + "</ul>"
                    + "</div></HTML>");
            ErrorWindow.setLayout(new BorderLayout());
            ErrorWindow.setSize(700, 350);
            ErrorWindow.setLocationRelativeTo(this);
            ErrorWindow.setResizable(true);
            ErrorWindow.setAlwaysOnTop(true);
            ErrorWindow.setVisible(true);
            ErrorWindow.add(ErrorWindowText);
            ErrorWindow.setName("help");
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

    public int getSpecularMapFrom() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());

            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();
            while (s != null) {
                if (s.contains("#define SPECULAR_MAP_FROM")) {

                    CatchSpecularMapFrom = Integer.parseInt(s.replaceAll("[\\D]", ""));

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
        return CatchSpecularMapFrom;
    }

    public int getSpecularMapType() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define SPECULAR_MAP_TYPE")) {
                    CatchSpecularMapType = Integer.parseInt(s.replaceAll("[\\D]", ""));
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
        return CatchSpecularMapType;
    }

    public int getSpecularMapUVFlip() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define SPECULAR_MAP_UV_FLIP")) {
                    CatchSpecularMapUVFlip = Integer.parseInt(s.replaceAll("[\\D]", ""));
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

        return CatchSpecularMapUVFlip;
    }

    public int getSpecularMapSwizzle() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define SPECULAR_MAP_SWIZZLE")) {
                    CatchSpecularMapSwizzle = Integer.parseInt(s.replaceAll("[\\D]", ""));
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

        return CatchSpecularMapSwizzle;
    }

    public int getSpecularMapApplyScale() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define SPECULAR_MAP_APPLY_SCALE")) {
                    CatchSpecularMapApplyScale = Integer.parseInt(s.replaceAll("[\\D]", ""));
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

        return CatchSpecularMapApplyScale;
    }

    public String getSpecularMapFile() {
        BufferedReader AlbedotoEdit_Br = null;

        try {

            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define SPECULAR_MAP_FILE")) {
                    CatchSpecularMapFile = "";
                    for (int i = s.indexOf('"') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';') {

                        } else {
                            CatchSpecularMapFile += s.charAt(i);
                        }
                    }

                    CatchSpecularMapFile = CatchSpecularMapFile.replace("null", "");
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
        return CatchSpecularMapFile;
    }

    public String getSpecularScale() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("specular ")) {
                    String txt = "";
                    for (int i = s.indexOf('=') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';' || s.charAt(i) == ' ') {

                        } else {
                            txt += s.charAt(i);
                        }
                    }
                    CatchSpecularScale = (txt);

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
        return CatchSpecularScale;
    }

    public float getSpecularLoop() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("specularMapLoopNum =")) {
                    String txt = "";
                    for (int i = s.indexOf('=') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';' || s.charAt(i) == ' ') {

                        } else {
                            txt += s.charAt(i);
                        }
                    }
                    CatchSpecularLoop = Float.parseFloat(txt);
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
        return CatchSpecularLoop;
    }

    //Smoothness
    public int getSmoothnessMapFrom() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());

            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();
            while (s != null) {
                if (s.contains("#define SMOOTHNESS_MAP_FROM")) {

                    CatchSmoothnessMapFrom = Integer.parseInt(s.replaceAll("[\\D]", ""));
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
        return CatchSmoothnessMapFrom;
    }

    public int getSmoothnessMapType() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define SMOOTHNESS_MAP_TYPE")) {
                    CatchSpecularMapType = Integer.parseInt(s.replaceAll("[\\D]", ""));
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
        return CatchSpecularMapType;
    }

    public int getSmoothnessMapUVFlip() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define SMOOTHNESS_MAP_UV_FLIP")) {
                    CatchSmoothnessMapUVFlip = Integer.parseInt(s.replaceAll("[\\D]", ""));
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

        return CatchSmoothnessMapUVFlip;
    }

    public int getSmoothnessMapSwizzle() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define SMOOTHNESS_MAP_SWIZZLE")) {
                    CatchSmoothnessMapSwizzle = Integer.parseInt(s.replaceAll("[\\D]", ""));
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

        return CatchSmoothnessMapSwizzle;
    }

    public int getSmoothnessMapApplyScale() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define SMOOTHNESS_MAP_APPLY_SCALE")) {
                    CatchSmoothnessMapApplyScale = Integer.parseInt(s.replaceAll("[\\D]", ""));
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

        return CatchSmoothnessMapApplyScale;
    }

    public String getSmoothnessMapFile() {
        BufferedReader AlbedotoEdit_Br = null;

        try {

            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define SMOOTHNESS_MAP_FILE")) {
                    CatchSmoothnessMapFile = "";
                    for (int i = s.indexOf('"') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';') {

                        } else {
                            CatchSmoothnessMapFile += s.charAt(i);
                        }
                    }

                    CatchSmoothnessMapFile = CatchSmoothnessMapFile.replace("null", "");
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
        return CatchSmoothnessMapFile;
    }

    public String getSmoothnessScale() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("smoothness =")) {
                    String txt = "";
                    for (int i = s.indexOf('=') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';' || s.charAt(i) == ' ') {

                        } else {
                            txt += s.charAt(i);
                        }
                    }
                    CatchSmoothnessScale = (txt);

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
        return CatchSmoothnessScale;
    }

    public float getSmoothnessLoop() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("smoothnessMapLoopNum")) {
                    String txt = "";
                    for (int i = s.indexOf('=') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';' || s.charAt(i) == ' ') {

                        } else {
                            txt += s.charAt(i);
                        }
                    }
                    CatchSmoothnessLoop = Float.parseFloat(txt);
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
        return CatchSmoothnessLoop;
    }

    /**
     * Creates new form WindowFrame
     */
    public SpecularAndSmoothness() {//Constructor

        initComponents();//Generated by the GUI mostly
        myInitComponents(); //Specular Loop
        myInitComponents2(); //Smoothness Loop
        myInitComponents3(); //Specular Scale
        myInitComponents4(); //Smoothness Scale
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
                    SpecularLoop = text.getText();
                    if (!slider.getValueIsAdjusting() && !SpecularLoop.equalsIgnoreCase(lastSpecularLoop)) {
                        lastSpecularLoop = SpecularLoop;
                        try {
                            BufferedReader br = null;
                            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                            String oldtext = "";
                            String line = br.readLine();
                            String catchOld = "";
                            String catchNew = "";
                            while (line != null) {
                                if (line.contains("specularMapLoopNum")) {
                                    String old = line;
                                    catchOld = line; //auxiliar line
                                    String aux = "";
                                    for (int i = line.indexOf('=') + 1; i < line.length() - 1; i++) {
                                        aux += line.charAt(i);
                                    }
                                    catchOld = aux;

                                    if (SpecularLoop == null) {
                                        SpecularLoop = String.valueOf(getSpecularLoop());
                                    }
                                    if (catchOld.equalsIgnoreCase(SpecularLoop)) {
                                        line = old;
                                    } else {
                                        SpecularLoop = SpecularLoop.replace(",", ".");
                                        catchNew = SpecularLoop; //catchnewdigit
                                        line = "const float2 specularMapLoopNum = " + catchNew + ';';
                                    }
                                }
                                oldtext += line + "\r\n";
                                line = br.readLine();
                            }
                            String newtext = oldtext;

                            FileWriter writer = new FileWriter(foo.getFileToEdit());
                            writer.write(newtext);
                            writer.close();

                            br.close();
                        } catch (Exception ea) {

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
                SpecularLoop = text.getText();
            }
        }
        );
        float catchvalue;
        catchvalue = Float.parseFloat("" + getSpecularLoop()) * slider.scale;

        slider.setPaintTrack(true);
        slider.setPaintLabels(true);
        slider.setBounds(48, 390, 200, 30);
        slider.setPaintTicks(true);
        slider.setValue((int) catchvalue);
        text.setBounds(256, 390, 50, 20);

        add(text);

        add(slider);
    }

    public void myInitComponents2() {

        final DecimalFormat df = new DecimalFormat("0.####");
        final JTextField text = new JTextField(20);

        final DoubleJSlider slider = new DoubleJSlider(-6400, 6400, 0, 100);
        float catchvalue;
        catchvalue = Float.parseFloat("" + getSmoothnessLoop()) * slider.scale;
        slider.setPaintTrack(true);
        slider.setPaintLabels(true);
        slider.setBounds(497, 390, 200, 30);
        slider.setPaintTicks(true);
        slider.setValue((int) catchvalue);
        text.setBounds(703, 390, 50, 20);
        text.setText(df.format(slider.getScaledValue()));
        add(text);
        add(slider);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                try {

                    text.setText(df.format(slider.getScaledValue()));
                    SmoothnessLoop = text.getText();
                    if (!slider.getValueIsAdjusting() && !SmoothnessLoop.equalsIgnoreCase(lastSmoothnessLoop)) {
                        lastSmoothnessLoop = SmoothnessLoop;
                        try {
                            BufferedReader br = null;
                            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                            String oldtext = "";
                            String line = br.readLine();
                            String catchOld = "";
                            String catchNew = "";
                            while (line != null) {
                                if (line.contains("smoothnessMapLoopNum")) {
                                    String old = line;
                                    catchOld = line; //auxiliar line
                                    String aux = "";
                                    for (int i = line.indexOf('=') + 1; i < line.length() - 1; i++) {
                                        aux += line.charAt(i);
                                    }
                                    catchOld = aux;

                                    if (SmoothnessLoop == null) {
                                        SmoothnessLoop = String.valueOf(getSmoothnessLoop());
                                    }
                                    if (catchOld.equalsIgnoreCase(SmoothnessLoop)) {
                                        line = old;
                                    } else {
                                        SmoothnessLoop = SmoothnessLoop.replace(",", ".");
                                        catchNew = SmoothnessLoop; //catchnewdigit
                                        line = "const float smoothnessMapLoopNum = " + catchNew + ';';
                                    }
                                }
                                oldtext += line + "\r\n";
                                line = br.readLine();
                            }
                            String newtext = oldtext;

                            FileWriter writer = new FileWriter(foo.getFileToEdit());
                            writer.write(newtext);
                            writer.close();

                            br.close();
                        } catch (Exception ea) {

                        }
                    }

                } catch (Exception ex) {
                }
            }
        });
        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                String typed = text.getText();

                if (!typed.matches("\\d+(\\.\\d*)?")) {
                    return;
                }
                double value = Double.parseDouble(typed) * slider.scale;
                slider.setValue((int) value);
                SmoothnessLoop = text.getText();
            }
        });

    }

    public void myInitComponents3() {

        final DecimalFormat df = new DecimalFormat("0.####");
        final JTextField text = new JTextField(20);

        final DoubleJSlider slider = new DoubleJSlider(0, 100, 0, 100);
        float catchvalue;
        catchvalue = Float.parseFloat("" + getSpecularScale()) * slider.scale;
        slider.setPaintTrack(true);
        slider.setPaintLabels(true);
        slider.setBounds(48, 326, 200, 30);
        slider.setPaintTicks(true);
        slider.setValue((int) catchvalue);
        text.setBounds(256, 326, 50, 20);

        add(text);
        add(slider);
        text.setText(df.format(slider.getScaledValue()));
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                try {

                    text.setText(df.format(slider.getScaledValue()));
                    SpecularScale = text.getText();
                    if (!slider.getValueIsAdjusting() && !SpecularScale.equalsIgnoreCase(lastSpecularScale)) {
                        lastSpecularScale = SpecularScale;
                        try {
                            BufferedReader br = null;
                            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                            String oldtext = "";
                            String line = br.readLine();
                            String catchOld = "";
                            String catchNew = "";
                            while (line != null) {
                                if (line.contains("float3 specular")) {
                                    String old = line;
                                    catchOld = line; //auxiliar line
                                    String aux = "";
                                    for (int i = line.indexOf('=') + 1; i < line.length() - 1; i++) {
                                        aux += line.charAt(i);
                                    }
                                    catchOld = aux;

                                    if (SpecularScale == null) {
                                        SpecularScale = getSpecularScale();
                                    }
                                    if (catchOld.equalsIgnoreCase(SpecularScale)) {
                                        line = old;
                                    } else {
                                        SpecularScale = SpecularScale.replace(",", ".");
                                        catchNew = SpecularScale; //catchnewdigit
                                        line = "const float3 specular = " + catchNew + ';';
                                    }
                                }
                                oldtext += line + "\r\n";
                                line = br.readLine();
                            }
                            String newtext = oldtext;

                            FileWriter writer = new FileWriter(foo.getFileToEdit());
                            writer.write(newtext);
                            writer.close();

                            br.close();
                        } catch (Exception ea) {

                        }
                    }

                } catch (Exception ex) {
                }
            }
        });
        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                String typed = text.getText();

                if (!typed.matches("\\d+(\\.\\d*)?")) {
                    return;
                }
                double value = Double.parseDouble(typed) * slider.scale;
                slider.setValue((int) value);
                SpecularScale = text.getText();
            }
        });

    }

    public void myInitComponents4() {

        final DecimalFormat df = new DecimalFormat("0.####");
        final JTextField text = new JTextField(20);

        final DoubleJSlider slider = new DoubleJSlider(0, 100, 0, 100);
        float catchvalue;
        catchvalue = Float.parseFloat("" + getSmoothnessScale()) * slider.scale;
        slider.setPaintTrack(true);
        slider.setPaintLabels(true);
        slider.setBounds(497, 325, 200, 30);
        slider.setPaintTicks(true);
        slider.setValue((int) catchvalue);
        text.setBounds(703, 325, 50, 20);
        text.setText(df.format(slider.getScaledValue()));

        add(text);
        add(slider);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                try {

                    text.setText(df.format(slider.getScaledValue()));
                    SmoothnessScale = text.getText();

                    if (!slider.getValueIsAdjusting() && !SmoothnessScale.equalsIgnoreCase(lastSmoothnessScale)) {
                        lastSmoothnessScale = SmoothnessScale;
                        try {
                            BufferedReader br = null;
                            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                            String oldtext = "";
                            String line = br.readLine();
                            String catchOld = "";
                            String catchNew = "";
                            while (line != null) {
                                if (line.contains("smoothness =")) {
                                    String old = line;
                                    catchOld = line; //auxiliar line
                                    String aux = "";
                                    for (int i = line.indexOf('=') + 1; i < line.length() - 1; i++) {
                                        aux += line.charAt(i);
                                    }
                                    catchOld = aux;

                                    if (SmoothnessScale == null) {
                                        SmoothnessScale = getSmoothnessScale();
                                    }
                                    if (catchOld.equalsIgnoreCase(SmoothnessScale)) {

                                        line = old;
                                    } else {
                                        SmoothnessScale = SmoothnessScale.replace(",", ".");
                                        catchNew = SmoothnessScale; //catchnewdigit
                                        line = "const float smoothness = " + catchNew + ';';
                                    }
                                }
                                oldtext += line + "\r\n";
                                line = br.readLine();
                            }
                            String newtext = oldtext;

                            FileWriter writer = new FileWriter(foo.getFileToEdit());
                            writer.write(newtext);
                            writer.close();

                            br.close();
                        } catch (Exception ea) {

                        }
                    }

                } catch (Exception ex) {
                }
            }
        });
        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                String typed = text.getText();

                if (!typed.matches("\\d+(\\.\\d*)?")) {
                    return;
                }
                double value = Double.parseDouble(typed) * slider.scale;
                slider.setValue((int) value);
                SmoothnessScale = text.getText();
            }
        });

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
        SpecularMapFrom = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        changeFile = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        SpecularMapUVFlip = new javax.swing.JComboBox<>();
        AlbedoMapHelp = new javax.swing.JButton();
        AlbedoMapUVFlipHelp = new javax.swing.JButton();
        AlbedoMapFileHelp = new javax.swing.JButton();
        albedoHelp = new javax.swing.JButton();
        AlbedoMapLoopHelp = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        SpecularMapType = new javax.swing.JComboBox<>();
        AlbedoMapHelp3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        SpecularMapSwizzle = new javax.swing.JComboBox<>();
        SpecularMapApplyScale = new javax.swing.JComboBox<>();
        AlbedoMapUVFlipHelp1 = new javax.swing.JButton();
        AlbedoMapUVFlipHelp2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        SmoothnessMapUVFlip = new javax.swing.JComboBox<>();
        AlbedoMapHelp1 = new javax.swing.JButton();
        AlbedoMapUVFlipHelp3 = new javax.swing.JButton();
        AlbedoMapFileHelp1 = new javax.swing.JButton();
        albedoHelp1 = new javax.swing.JButton();
        AlbedoMapLoopHelp1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        SmoothnessMapFrom = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        changeFile1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        SmoothnessMapSwizzle = new javax.swing.JComboBox<>();
        SmoothnessMapApplyScale = new javax.swing.JComboBox<>();
        AlbedoMapUVFlipHelp4 = new javax.swing.JButton();
        AlbedoMapUVFlipHelp5 = new javax.swing.JButton();
        SpecularMapFile = new javax.swing.JTextField();
        SmoothnessMapFile = new javax.swing.JTextField();
        back1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        SmoothnessMapType = new javax.swing.JComboBox<>();
        AlbedoMapHelp4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Edit Specular and Smoothness of "+foo.getFileToEdit().getName());
        setAutoRequestFocus(false);
        setResizable(false);
        setSize(new java.awt.Dimension(960, 540));
        getContentPane().setLayout(null);

        jLabel1.setText("<html><b>SPECULAR MAP FROM</b></html>");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(54, 90, 161, 20);

        SpecularMapFrom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8" }));
        try{
            SpecularMapFrom.setSelectedIndex(getSpecularMapFrom());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        SpecularMapFrom.setToolTipText("");
        SpecularMapFrom.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SpecularMapFromItemStateChanged(evt);
            }
        });
        SpecularMapFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SpecularMapFromActionPerformed(evt);
            }
        });
        getContentPane().add(SpecularMapFrom);
        SpecularMapFrom.setBounds(310, 90, 82, 20);

        jLabel2.setText("<html><b>SPECULAR MAP UV FLIP</b></html>");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(54, 151, 130, 14);

        jLabel6.setText("<html><b>SPECULAR MAP FILE</b></html>");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(54, 238, 111, 14);

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
        getContentPane().add(changeFile);
        changeFile.setBounds(309, 234, 83, 23);

        jLabel8.setText("<html><b>SPECULAR MAP SCALE</b></html>");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(54, 298, 122, 26);

        jLabel9.setText("<html><b>SPECULAR MAP LOOP</b></html>");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(54, 368, 117, 14);

        SpecularMapUVFlip.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        try{
            SpecularMapUVFlip.setSelectedIndex(getSpecularMapUVFlip());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        SpecularMapUVFlip.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SpecularMapUVFlipItemStateChanged(evt);
            }
        });
        SpecularMapUVFlip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SpecularMapUVFlipActionPerformed(evt);
            }
        });
        getContentPane().add(SpecularMapUVFlip);
        SpecularMapUVFlip.setBounds(310, 148, 82, 20);

        AlbedoMapHelp.setText("Help");
        AlbedoMapHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapHelpActionPerformed(evt);
            }
        });
        getContentPane().add(AlbedoMapHelp);
        AlbedoMapHelp.setBounds(398, 89, 53, 23);

        AlbedoMapUVFlipHelp.setText("Help");
        AlbedoMapUVFlipHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapUVFlipHelpActionPerformed(evt);
            }
        });
        getContentPane().add(AlbedoMapUVFlipHelp);
        AlbedoMapUVFlipHelp.setBounds(398, 147, 53, 23);

        AlbedoMapFileHelp.setText("Help");
        AlbedoMapFileHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapFileHelpActionPerformed(evt);
            }
        });
        getContentPane().add(AlbedoMapFileHelp);
        AlbedoMapFileHelp.setBounds(398, 234, 53, 23);

        albedoHelp.setText("Help");
        albedoHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albedoHelpActionPerformed(evt);
            }
        });
        getContentPane().add(albedoHelp);
        albedoHelp.setBounds(180, 300, 53, 23);

        AlbedoMapLoopHelp.setText("Help");
        AlbedoMapLoopHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapLoopHelpActionPerformed(evt);
            }
        });
        getContentPane().add(AlbedoMapLoopHelp);
        AlbedoMapLoopHelp.setBounds(181, 364, 53, 23);

        jLabel3.setText("<html><b>SPECULAR MAP TYPE</b></html>");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(54, 119, 161, 20);

        SpecularMapType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4" }));
        try{
            SpecularMapType.setSelectedIndex(getSpecularMapType());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        SpecularMapType.setToolTipText("");
        SpecularMapType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SpecularMapTypeItemStateChanged(evt);
            }
        });
        SpecularMapType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SpecularMapTypeActionPerformed(evt);
            }
        });
        getContentPane().add(SpecularMapType);
        SpecularMapType.setBounds(310, 119, 82, 20);

        AlbedoMapHelp3.setText("Help");
        AlbedoMapHelp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapHelp3ActionPerformed(evt);
            }
        });
        getContentPane().add(AlbedoMapHelp3);
        AlbedoMapHelp3.setBounds(398, 118, 53, 23);

        jLabel4.setText("<html><b>SPECULAR MAP SWIZZLE</b></html>");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(54, 180, 137, 14);

        jLabel5.setText("<html><b>SPECULAR MAP APPLY SCALE</b></html>");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(54, 209, 160, 14);

        SpecularMapSwizzle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        try{
            SpecularMapSwizzle.setSelectedIndex(getSpecularMapSwizzle());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        SpecularMapSwizzle.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SpecularMapSwizzleItemStateChanged(evt);
            }
        });
        SpecularMapSwizzle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SpecularMapSwizzleActionPerformed(evt);
            }
        });
        getContentPane().add(SpecularMapSwizzle);
        SpecularMapSwizzle.setBounds(310, 177, 82, 20);

        SpecularMapApplyScale.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2" }));
        try{
            SpecularMapApplyScale.setSelectedIndex(getSpecularMapApplyScale());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        SpecularMapApplyScale.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SpecularMapApplyScaleItemStateChanged(evt);
            }
        });
        SpecularMapApplyScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SpecularMapApplyScaleActionPerformed(evt);
            }
        });
        getContentPane().add(SpecularMapApplyScale);
        SpecularMapApplyScale.setBounds(310, 206, 82, 20);

        AlbedoMapUVFlipHelp1.setText("Help");
        AlbedoMapUVFlipHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapUVFlipHelp1ActionPerformed(evt);
            }
        });
        getContentPane().add(AlbedoMapUVFlipHelp1);
        AlbedoMapUVFlipHelp1.setBounds(398, 205, 53, 23);

        AlbedoMapUVFlipHelp2.setText("Help");
        AlbedoMapUVFlipHelp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapUVFlipHelp2ActionPerformed(evt);
            }
        });
        getContentPane().add(AlbedoMapUVFlipHelp2);
        AlbedoMapUVFlipHelp2.setBounds(398, 176, 53, 23);

        jLabel10.setText("<html><b>SMOOTHNESS MAP SCALE</b></html>");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(505, 298, 140, 26);

        jLabel11.setText("<html><b>SMOOTHNESS MAP LOOP</b></html>");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(505, 368, 135, 14);

        SmoothnessMapUVFlip.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        try{
            SmoothnessMapUVFlip.setSelectedIndex(getSmoothnessMapUVFlip());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        SmoothnessMapUVFlip.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SmoothnessMapUVFlipItemStateChanged(evt);
            }
        });
        SmoothnessMapUVFlip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SmoothnessMapUVFlipActionPerformed(evt);
            }
        });
        getContentPane().add(SmoothnessMapUVFlip);
        SmoothnessMapUVFlip.setBounds(746, 148, 82, 20);

        AlbedoMapHelp1.setText("Help");
        AlbedoMapHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapHelp1ActionPerformed(evt);
            }
        });
        getContentPane().add(AlbedoMapHelp1);
        AlbedoMapHelp1.setBounds(834, 86, 53, 23);

        AlbedoMapUVFlipHelp3.setText("Help");
        AlbedoMapUVFlipHelp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapUVFlipHelp3ActionPerformed(evt);
            }
        });
        getContentPane().add(AlbedoMapUVFlipHelp3);
        AlbedoMapUVFlipHelp3.setBounds(834, 147, 53, 23);

        AlbedoMapFileHelp1.setText("Help");
        AlbedoMapFileHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapFileHelp1ActionPerformed(evt);
            }
        });
        getContentPane().add(AlbedoMapFileHelp1);
        AlbedoMapFileHelp1.setBounds(834, 234, 53, 23);

        albedoHelp1.setText("Help");
        albedoHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albedoHelp1ActionPerformed(evt);
            }
        });
        getContentPane().add(albedoHelp1);
        albedoHelp1.setBounds(649, 300, 53, 23);

        AlbedoMapLoopHelp1.setText("Help");
        AlbedoMapLoopHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapLoopHelp1ActionPerformed(evt);
            }
        });
        getContentPane().add(AlbedoMapLoopHelp1);
        AlbedoMapLoopHelp1.setBounds(650, 364, 53, 23);

        jLabel12.setText("<html><b>SMOOTHNESS MAP FROM</b></html>");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(505, 87, 161, 20);

        SmoothnessMapFrom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
        try{
            SmoothnessMapFrom.setSelectedIndex(getSmoothnessMapFrom());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        SmoothnessMapFrom.setToolTipText("");
        SmoothnessMapFrom.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SmoothnessMapFromItemStateChanged(evt);
            }
        });
        SmoothnessMapFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SmoothnessMapFromActionPerformed(evt);
            }
        });
        getContentPane().add(SmoothnessMapFrom);
        SmoothnessMapFrom.setBounds(746, 87, 82, 20);

        jLabel13.setText("<html><b>SMOOTHNESS MAP UV FLIP</b></html>");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(505, 151, 148, 14);

        jLabel14.setText("<html><b>SMOOTHNESS MAP FILE</b></html>");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(505, 232, 129, 14);

        changeFile1.setText("...");
        changeFile1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                changeFile1StateChanged(evt);
            }
        });
        changeFile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeFile1ActionPerformed(evt);
            }
        });
        getContentPane().add(changeFile1);
        changeFile1.setBounds(746, 234, 82, 23);

        jLabel15.setText("<html><b>SMOOTHNESS MAP SWIZZLE</b></html>");
        getContentPane().add(jLabel15);
        jLabel15.setBounds(505, 180, 155, 14);

        jLabel16.setText("<html><b>SMOOTHNESS MAP APPLY SCALE</b></html>");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(505, 209, 178, 14);

        SmoothnessMapSwizzle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        try{
            SmoothnessMapSwizzle.setSelectedIndex(getSmoothnessMapSwizzle());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        SmoothnessMapSwizzle.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SmoothnessMapSwizzleItemStateChanged(evt);
            }
        });
        SmoothnessMapSwizzle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SmoothnessMapSwizzleActionPerformed(evt);
            }
        });
        getContentPane().add(SmoothnessMapSwizzle);
        SmoothnessMapSwizzle.setBounds(746, 177, 82, 20);

        SmoothnessMapApplyScale.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2" }));
        try{
            SmoothnessMapApplyScale.setSelectedIndex(getSmoothnessMapApplyScale());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        SmoothnessMapApplyScale.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SmoothnessMapApplyScaleItemStateChanged(evt);
            }
        });
        SmoothnessMapApplyScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SmoothnessMapApplyScaleActionPerformed(evt);
            }
        });
        getContentPane().add(SmoothnessMapApplyScale);
        SmoothnessMapApplyScale.setBounds(746, 206, 82, 20);

        AlbedoMapUVFlipHelp4.setText("Help");
        AlbedoMapUVFlipHelp4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapUVFlipHelp4ActionPerformed(evt);
            }
        });
        getContentPane().add(AlbedoMapUVFlipHelp4);
        AlbedoMapUVFlipHelp4.setBounds(834, 205, 53, 23);

        AlbedoMapUVFlipHelp5.setText("Help");
        AlbedoMapUVFlipHelp5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapUVFlipHelp5ActionPerformed(evt);
            }
        });
        getContentPane().add(AlbedoMapUVFlipHelp5);
        AlbedoMapUVFlipHelp5.setBounds(834, 176, 53, 23);

        try{
            SpecularMapFile.setEditable(false);
            SpecularMapFile.setText(getSpecularMapFile());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        getContentPane().add(SpecularMapFile);
        SpecularMapFile.setBounds(54, 258, 134, 20);

        try{
            SmoothnessMapFile.setEditable(false);
            SmoothnessMapFile.setText(getSmoothnessMapFile());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        getContentPane().add(SmoothnessMapFile);
        SmoothnessMapFile.setBounds(505, 258, 134, 20);

        back1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bg/back.png"))); // NOI18N
        back1.setBorder(null);
        back1.setContentAreaFilled(false);
        back1.setFocusable(false);
        back1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back1ActionPerformed(evt);
            }
        });
        getContentPane().add(back1);
        back1.setBounds(54, 0, 47, 55);

        jLabel17.setText("<html><b>SMOOTHNESS MAP TYPE</b></html>");
        getContentPane().add(jLabel17);
        jLabel17.setBounds(505, 119, 161, 20);

        SmoothnessMapType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2" }));
        try{
            SmoothnessMapType.setSelectedIndex(getSmoothnessMapType());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        SmoothnessMapType.setToolTipText("");
        SmoothnessMapType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SmoothnessMapTypeItemStateChanged(evt);
            }
        });
        SmoothnessMapType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SmoothnessMapTypeActionPerformed(evt);
            }
        });
        getContentPane().add(SmoothnessMapType);
        SmoothnessMapType.setBounds(746, 119, 82, 20);

        AlbedoMapHelp4.setText("Help");
        AlbedoMapHelp4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapHelp4ActionPerformed(evt);
            }
        });
        getContentPane().add(AlbedoMapHelp4);
        AlbedoMapHelp4.setBounds(834, 118, 53, 23);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bg/tip.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setDefaultCapable(false);
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(107, 0, 40, 55);

        jButton4.setText("Preview");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(194, 257, 71, 23);

        jButton5.setText("Preview");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(645, 257, 71, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SpecularMapFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SpecularMapFromActionPerformed

    }//GEN-LAST:event_SpecularMapFromActionPerformed

    private void SpecularMapUVFlipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SpecularMapUVFlipActionPerformed

    }//GEN-LAST:event_SpecularMapUVFlipActionPerformed

    private void changeFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeFileActionPerformed

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new java.io.File("../../Materials"));//The directory we are looking for is the Materials folder from ray
        fileChooser.setDialogTitle("Choose new Specular Map File");
        //Filtering by ImageFiles
        FileFilter imageFilter = new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp", "tga", "targa", "dds", "tif", "tiff", "jpeg", "pcd");
        fileChooser.setFileFilter(imageFilter);

        try {
            int selection = fileChooser.showOpenDialog(this);
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            filePath = filePath.replace("\\", "/");

            if (selection == 0) {
                String relative = toRelative.convertToRelativePath(foo.getFileToEdit().getParent(), filePath);
                SpecularMapFile.setText(relative);
                if (SpecularMapFrom.getSelectedIndex() != 1) {
                    SpecularMapFrom.setSelectedIndex(1);
                }

                try {
                    BufferedReader br = null;
                    br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                    String oldtext = "";
                    String line = br.readLine();
                    String catchOld = "";
                    String catchNew = "";
                    while (line != null) {
                        if (line.contains("#define SPECULAR_MAP_FILE")) {
                            catchOld = line; //auxiliar line
                            String aux = "";
                            for (int i = catchOld.indexOf('"') + 1; i < line.length() - 1; i++) {
                                aux += catchOld.charAt(i);
                            }
                            catchOld = aux;
                            if (!catchOld.equalsIgnoreCase(SpecularMapFile.getText())) {
                                catchNew = SpecularMapFile.getText(); //catchnewdigit
                                line = "#define SPECULAR_MAP_FILE " + '"' + catchNew + '"';
                            }
                        }
                        oldtext += line + "\r\n";
                        line = br.readLine();
                    }
                    String newtext = oldtext;

                    FileWriter writer = new FileWriter(foo.getFileToEdit());
                    writer.write(newtext);
                    writer.close();

                    br.close();
                } catch (Exception e) {

                }
            }
        } catch (Exception e) {
            //wont happen hahaaaaaa
        }
    }//GEN-LAST:event_changeFileActionPerformed

    private void changeFileStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_changeFileStateChanged

    }//GEN-LAST:event_changeFileStateChanged

    private void AlbedoMapHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapHelpActionPerformed

        JFrame AlbedoMapHelp = new JFrame();
        JLabel AlbedoMapHelpText = new JLabel();
        AlbedoMapHelp.setName("help");
        AlbedoMapHelpText.setText("<HTML><div><pre style='font-family: Arial;'><center>You can use a color and texture to change colors in your model by set the code to the ALBEDO_MAP_FROM.<br><br>"
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
                + "    <li><strike>9 : Params fetch from Specular Power from the pmx. (this option can only be used for specular)</strike>, doesn't work on Specular</li></ul></pre><br><br></HTML>");
        AlbedoMapHelp.setLayout(new BorderLayout());
        AlbedoMapHelp.setSize(700, 550);
        AlbedoMapHelp.setLocationRelativeTo(this);
        AlbedoMapHelp.setResizable(true);
        AlbedoMapHelp.setVisible(true);
        AlbedoMapHelp.add(AlbedoMapHelpText);
        AlbedoMapHelp.setTitle("Specular Map From Help");

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
        help.setTitle("Specular Map UV Flip Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapUVFlipHelpActionPerformed

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
        help.setTitle("Specular Map File Help");

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
        helptext.setText("<html>Default value is 0.5<br><br>"
                + "Notice : Anything less than 2% is physically impossible and is instead considered to be shadowing<br><br>"
                + "For example: The reflectance coefficient is equal to F(x) = (x - 1)^2 / (x + 1)^2<br>"
                + "Consider light that is incident upon a transparent medium with a refractive index of 1.5<br><br>"
                + "That result will be equal to (1.5 - 1)^2 / (1.5 + 1)^2 = 0.04 (or 4%).<br>"
                + "Specular to reflection coefficient is equal to F(x) = 0.08*x, if the x is equal to 0.5 the result will be 0.04.<br>"
                + "So default value is 0.5 for 0.04 coeff.<br><br><br>"
                + "<b>between 0 ~ 1</b></html>");
        help.setLayout(new BorderLayout());
        help.setSize(600, 350);
        help.setLocationRelativeTo(this);
        help.setResizable(true);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Specular Map Scale Help");

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
        help.setTitle("Specular Map Loop Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
        }
    }//GEN-LAST:event_AlbedoMapLoopHelpActionPerformed

    private void SpecularMapTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SpecularMapTypeActionPerformed

    }//GEN-LAST:event_SpecularMapTypeActionPerformed

    private void AlbedoMapHelp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapHelp3ActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        helptext.setText("<HTML><ul><li><b>0</b> : Calculate reflection coefficient from specular color by F(x) = 0.08*(x  ) (from UE4 textures)</li>"
                + "<li><b>1</b> : Calculate reflection coefficient from specular color by F(x) = 0.16*(x^2) (from Frostbite textures)</li>"
                + "<li><b>2</b> : Calculate reflection coefficient from specular grays by F(x) = 0.08*(x  ) (from UE4 textures)</li>"
                + "<li><b>3</b> : Calculate reflection coefficient from specular grays by F(x) = 0.16*(x^2) (from Frostbite textures)</li>"
                + "<li><b>4 </b>: Using reflection coefficient (0.04) instead of specular value (0.5), Available when SPECULAR_MAP_FROM at 0</li></html>");
        help.setLayout(new BorderLayout());
        help.setSize(600, 200);
        help.setLocationRelativeTo(this);
        help.setResizable(true);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Specular Map Type Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapHelp3ActionPerformed

    private void SpecularMapSwizzleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SpecularMapSwizzleActionPerformed

    }//GEN-LAST:event_SpecularMapSwizzleActionPerformed

    private void SpecularMapApplyScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SpecularMapApplyScaleActionPerformed

    }//GEN-LAST:event_SpecularMapApplyScaleActionPerformed

    private void AlbedoMapUVFlipHelp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapUVFlipHelp1ActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        helptext.setText("<HTML><center>You can apply color from const float3 albedo = 1.0; to change colors in your texture by set code to the ALBEDO_MAP_APPLY_SCALE</center><br><br>"
                + "<ul><li><b>1</b> : map values * albedo;</li>"
                + "<li><b>2</b> : map values ^ albedo;</li>"
                + "</ul></HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(500, 160);
        help.setLocationRelativeTo(this);
        help.setResizable(true);
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Specular Map Apply Scale Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapUVFlipHelp1ActionPerformed

    private void AlbedoMapUVFlipHelp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapUVFlipHelp2ActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        helptext.setText("<HTML>The ordering of the data fetched from a texture from the code. (R = 0, G = 1, B = 2, A = 3)</html>");
        help.setLayout(new BorderLayout());
        help.setSize(600, 200);
        help.setLocationRelativeTo(this);
        help.setResizable(true);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Specular Map Swizzle Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapUVFlipHelp2ActionPerformed

    private void SmoothnessMapUVFlipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SmoothnessMapUVFlipActionPerformed

    }//GEN-LAST:event_SmoothnessMapUVFlipActionPerformed

    private void AlbedoMapHelp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapHelp1ActionPerformed
        JFrame AlbedoMapHelp = new JFrame();
        JLabel AlbedoMapHelpText = new JLabel();
        AlbedoMapHelp.setName("help");
        AlbedoMapHelpText.setText("<HTML><div><pre style='font-family: Arial;'><center><b>Default data will fetch params from the SpecularPower and convert the SpecularPower to smoothness.</b>"
                + "<br><br><br>You can use a color and texture to change colors in your model by set the code to the ALBEDO_MAP_FROM.<br><br>"
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
                + "    <li>9 : Params fetch from Specular Power from the pmx. (this option can only be used for specular)</li></ul></pre><br><br></HTML>");
        AlbedoMapHelp.setLayout(new BorderLayout());
        AlbedoMapHelp.setSize(700, 550);
        AlbedoMapHelp.setLocationRelativeTo(this);
        AlbedoMapHelp.setResizable(true);
        AlbedoMapHelp.setVisible(true);
        AlbedoMapHelp.add(AlbedoMapHelpText);
        AlbedoMapHelp.setTitle("Smoothness Map From Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            AlbedoMapHelp.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapHelp1ActionPerformed

    private void AlbedoMapUVFlipHelp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapUVFlipHelp3ActionPerformed

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
        help.setTitle("Smoothness Map UV Flip Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapUVFlipHelp3ActionPerformed

    private void AlbedoMapFileHelp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapFileHelp1ActionPerformed

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
        help.setTitle("Smoothness Map File Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapFileHelp1ActionPerformed

    private void albedoHelp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albedoHelp1ActionPerformed

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
        help.setTitle("Smoothness Map Scale Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }

    }//GEN-LAST:event_albedoHelp1ActionPerformed

    private void AlbedoMapLoopHelp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapLoopHelp1ActionPerformed

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
        help.setTitle("Smoothness Map Loop Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapLoopHelp1ActionPerformed

    private void SmoothnessMapFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SmoothnessMapFromActionPerformed

    }//GEN-LAST:event_SmoothnessMapFromActionPerformed

    private void changeFile1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_changeFile1StateChanged

    }//GEN-LAST:event_changeFile1StateChanged

    private void changeFile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeFile1ActionPerformed

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new java.io.File("../../Materials"));//The directory we are looking for is the Materials folder from ray
        fileChooser.setDialogTitle("Choose new Smoothness Map File");
        //Filtering by ImageFiles
        FileFilter imageFilter = new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp", "tga", "targa", "dds", "tif", "tiff", "jpeg", "pcd");
        fileChooser.setFileFilter(imageFilter);

        try {
            int selection = fileChooser.showOpenDialog(this);
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            filePath = filePath.replace("\\", "/");

            if (selection == 0) {
                String relative = toRelative.convertToRelativePath(foo.getFileToEdit().getParent(), filePath);
                SmoothnessMapFile.setText(relative);
                if (SmoothnessMapFrom.getSelectedIndex() != 1) {
                    SmoothnessMapFrom.setSelectedIndex(1);
                }

                try {
                    BufferedReader br = null;
                    br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                    String oldtext = "";
                    String line = br.readLine();
                    String catchOld = "";
                    String catchNew = "";
                    while (line != null) {
                        if (line.contains("#define SMOOTHNESS_MAP_FILE")) {
                            catchOld = line; //auxiliar line
                            String aux = "";
                            for (int i = catchOld.indexOf('"') + 1; i < line.length() - 1; i++) {
                                aux += catchOld.charAt(i);
                            }
                            catchOld = aux;
                            if (!catchOld.equalsIgnoreCase(SmoothnessMapFile.getText())) {
                                catchNew = SmoothnessMapFile.getText(); //catchnewdigit
                                line = "#define SMOOTHNESS_MAP_FILE " + '"' + catchNew + '"';
                            }
                        }
                        oldtext += line + "\r\n";
                        line = br.readLine();
                    }
                    String newtext = oldtext;

                    FileWriter writer = new FileWriter(foo.getFileToEdit());
                    writer.write(newtext);
                    writer.close();

                    br.close();
                } catch (Exception e) {

                }
            }
        } catch (Exception e) {
            //wont happen hahaaaaaa
        }
    }//GEN-LAST:event_changeFile1ActionPerformed

    private void SmoothnessMapSwizzleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SmoothnessMapSwizzleActionPerformed

    }//GEN-LAST:event_SmoothnessMapSwizzleActionPerformed

    private void SmoothnessMapApplyScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SmoothnessMapApplyScaleActionPerformed

    }//GEN-LAST:event_SmoothnessMapApplyScaleActionPerformed

    private void AlbedoMapUVFlipHelp4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapUVFlipHelp4ActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        helptext.setText("<HTML><center>You can apply color from const float3 albedo = 1.0; to change colors in your texture by set code to the ALBEDO_MAP_APPLY_SCALE</center><br><br>"
                + "<ul><li><b>1</b> : map values * albedo;</li>"
                + "<li><b>2</b> : map values ^ albedo;</li>"
                + "</ul></HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(500, 160);
        help.setLocationRelativeTo(this);

        help.setResizable(true);
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Smoothness Map Apply Scale Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }

    }//GEN-LAST:event_AlbedoMapUVFlipHelp4ActionPerformed

    private void AlbedoMapUVFlipHelp5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapUVFlipHelp5ActionPerformed

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
        help.setTitle("Smoothness Map Swizzle Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapUVFlipHelp5ActionPerformed
    private void closeAllDialogs() {
        Window[] windows = AlbedoSection.getWindows();

        for (Window window : windows) {
            if (window.getName().equalsIgnoreCase("help")) {
                window.dispose();
            }
        }
    }

    private void back1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back1ActionPerformed
        closeAllDialogs();
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_back1ActionPerformed

    private void SpecularMapFromItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SpecularMapFromItemStateChanged
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define SPECULAR_MAP_FROM")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = SpecularMapFrom.getSelectedItem().toString(); //catchnewdigit
                    line = line.replaceAll(catchOld, catchNew); //replace old for the new one

                }
                oldtext += line + "\r\n";
                line = br.readLine();
            }
            String newtext = oldtext;

            FileWriter writer = new FileWriter(foo.getFileToEdit());
            writer.write(newtext);
            writer.close();

            br.close();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_SpecularMapFromItemStateChanged

    private void SpecularMapTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SpecularMapTypeItemStateChanged
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define SPECULAR_MAP_TYPE")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = SpecularMapType.getSelectedItem().toString(); //catchnewdigit
                    line = line.replaceAll(catchOld, catchNew); //replace old for the new one

                }
                oldtext += line + "\r\n";
                line = br.readLine();
            }
            String newtext = oldtext;

            FileWriter writer = new FileWriter(foo.getFileToEdit());
            writer.write(newtext);
            writer.close();

            br.close();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_SpecularMapTypeItemStateChanged

    private void SpecularMapUVFlipItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SpecularMapUVFlipItemStateChanged
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define SPECULAR_MAP_UV_FLIP")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = SpecularMapUVFlip.getSelectedItem().toString(); //catchnewdigit
                    line = line.replaceAll(catchOld, catchNew); //replace old for the new one

                }
                oldtext += line + "\r\n";
                line = br.readLine();
            }
            String newtext = oldtext;

            FileWriter writer = new FileWriter(foo.getFileToEdit());
            writer.write(newtext);
            writer.close();

            br.close();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_SpecularMapUVFlipItemStateChanged

    private void SpecularMapSwizzleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SpecularMapSwizzleItemStateChanged
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define SPECULAR_MAP_SWIZZLE")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = SpecularMapSwizzle.getSelectedItem().toString(); //catchnewdigit
                    line = line.replaceAll(catchOld, catchNew); //replace old for the new one

                }
                oldtext += line + "\r\n";
                line = br.readLine();
            }
            String newtext = oldtext;

            FileWriter writer = new FileWriter(foo.getFileToEdit());
            writer.write(newtext);
            writer.close();

            br.close();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_SpecularMapSwizzleItemStateChanged

    private void SpecularMapApplyScaleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SpecularMapApplyScaleItemStateChanged
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define SPECULAR_MAP_APPLY_SCALE")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = SpecularMapApplyScale.getSelectedItem().toString(); //catchnewdigit
                    line = line.replaceAll(catchOld, catchNew); //replace old for the new one

                }
                oldtext += line + "\r\n";
                line = br.readLine();
            }
            String newtext = oldtext;

            FileWriter writer = new FileWriter(foo.getFileToEdit());
            writer.write(newtext);
            writer.close();

            br.close();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_SpecularMapApplyScaleItemStateChanged

    private void SmoothnessMapFromItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SmoothnessMapFromItemStateChanged
        // TODO add your handling code here:
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define SMOOTHNESS_MAP_FROM")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = SmoothnessMapFrom.getSelectedItem().toString(); //catchnewdigit
                    line = line.replaceAll(catchOld, catchNew); //replace old for the new one

                }
                oldtext += line + "\r\n";
                line = br.readLine();
            }
            String newtext = oldtext;

            FileWriter writer = new FileWriter(foo.getFileToEdit());
            writer.write(newtext);
            writer.close();

            br.close();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_SmoothnessMapFromItemStateChanged

    private void SmoothnessMapUVFlipItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SmoothnessMapUVFlipItemStateChanged
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define SMOOTHNESS_MAP_UV_FLIP")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = SmoothnessMapUVFlip.getSelectedItem().toString(); //catchnewdigit
                    line = line.replaceAll(catchOld, catchNew); //replace old for the new one
                }
                oldtext += line + "\r\n";
                line = br.readLine();
            }
            String newtext = oldtext;

            FileWriter writer = new FileWriter(foo.getFileToEdit());
            writer.write(newtext);
            writer.close();

            br.close();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_SmoothnessMapUVFlipItemStateChanged

    private void SmoothnessMapSwizzleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SmoothnessMapSwizzleItemStateChanged
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define SMOOTHNESS_MAP_SWIZZLE")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = SmoothnessMapSwizzle.getSelectedItem().toString(); //catchnewdigit
                    line = line.replaceAll(catchOld, catchNew); //replace old for the new one

                }
                oldtext += line + "\r\n";
                line = br.readLine();
            }
            String newtext = oldtext;

            FileWriter writer = new FileWriter(foo.getFileToEdit());
            writer.write(newtext);
            writer.close();

            br.close();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_SmoothnessMapSwizzleItemStateChanged

    private void SmoothnessMapApplyScaleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SmoothnessMapApplyScaleItemStateChanged
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define SMOOTHNESS_MAP_APPLY_SCALE")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = SmoothnessMapApplyScale.getSelectedItem().toString(); //catchnewdigit
                    line = line.replaceAll(catchOld, catchNew); //replace old for the new one

                }
                oldtext += line + "\r\n";
                line = br.readLine();
            }
            String newtext = oldtext;

            FileWriter writer = new FileWriter(foo.getFileToEdit());
            writer.write(newtext);
            writer.close();

            br.close();
        } catch (Exception e) {

        }         // TODO add your handling code here:
    }//GEN-LAST:event_SmoothnessMapApplyScaleItemStateChanged

    private void SmoothnessMapTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SmoothnessMapTypeItemStateChanged
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define SMOOTHNESS_MAP_TYPE")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = SmoothnessMapType.getSelectedItem().toString(); //catchnewdigit
                    line = line.replaceAll(catchOld, catchNew); //replace old for the new one

                }
                oldtext += line + "\r\n";
                line = br.readLine();
            }
            String newtext = oldtext;

            FileWriter writer = new FileWriter(foo.getFileToEdit());
            writer.write(newtext);
            writer.close();

            br.close();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_SmoothnessMapTypeItemStateChanged

    private void SmoothnessMapTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SmoothnessMapTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SmoothnessMapTypeActionPerformed

    private void AlbedoMapHelp4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapHelp4ActionPerformed

        JFrame AlbedoMapHelp = new JFrame();
        JLabel AlbedoMapHelpText = new JLabel();
        AlbedoMapHelp.setName("help");
        AlbedoMapHelpText.setText("<HTML><ul><li>0 : Smoothness (from Frostbite / CE5 textures)</li>"
                + "<li>1 : Calculate Smoothness from roughness by 1.0 - Roughness ^ 0.5 (from UE4/GGX/SubstancePainter2 textures)</li>"
                + "<li>2 : Calculate Smoothness from roughness by 1.0 - Roughness		(from UE4/GGX/SubstancePainter2 with roughness linear roughness)</li></ul></html>");
        AlbedoMapHelp.setLayout(new BorderLayout());
        AlbedoMapHelp.setSize(700, 550);
        AlbedoMapHelp.setLocationRelativeTo(this);

        AlbedoMapHelp.setResizable(true);
        AlbedoMapHelp.setVisible(true);
        AlbedoMapHelp.add(AlbedoMapHelpText);
        AlbedoMapHelp.setTitle("Smoothness Map From Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            AlbedoMapHelp.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapHelp4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        helptext.setText("<html><b>Tips :</b> It has no effect on metalness > 0 and CUSTOM_ENABLE > 0.<br><br>"
                + "The specular maps are not HDR/environment maps, only modifies the color of basic reflection of the model<br>"
                + "Used to change the colors of the environment reflect.<br>"
                + "When the diffuse brighter than specular reflect,<br>"
                + "It will be only a very small contribution to change the colors of environment reflect.<br>"
                + "So you can set the zero to the 'const float3 specular = 0.0;', if you dot‘t want that model to reflect the specular color of environment.<br></html>");
        help.setLayout(new BorderLayout());
        help.setSize(700, 250);
        help.setLocationRelativeTo(this);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setResizable(true);
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Specular Map Tips");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_jButton2ActionPerformed
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
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        previewImg(SpecularMapFile.getText());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        previewImg(SmoothnessMapFile.getText());
    }//GEN-LAST:event_jButton5ActionPerformed

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
    private javax.swing.JButton AlbedoMapFileHelp;
    private javax.swing.JButton AlbedoMapFileHelp1;
    private javax.swing.JButton AlbedoMapHelp;
    private javax.swing.JButton AlbedoMapHelp1;
    private javax.swing.JButton AlbedoMapHelp3;
    private javax.swing.JButton AlbedoMapHelp4;
    private javax.swing.JButton AlbedoMapLoopHelp;
    private javax.swing.JButton AlbedoMapLoopHelp1;
    private javax.swing.JButton AlbedoMapUVFlipHelp;
    private javax.swing.JButton AlbedoMapUVFlipHelp1;
    private javax.swing.JButton AlbedoMapUVFlipHelp2;
    private javax.swing.JButton AlbedoMapUVFlipHelp3;
    private javax.swing.JButton AlbedoMapUVFlipHelp4;
    private javax.swing.JButton AlbedoMapUVFlipHelp5;
    private javax.swing.JComboBox<String> SmoothnessMapApplyScale;
    private javax.swing.JTextField SmoothnessMapFile;
    private javax.swing.JComboBox<String> SmoothnessMapFrom;
    private javax.swing.JComboBox<String> SmoothnessMapSwizzle;
    private javax.swing.JComboBox<String> SmoothnessMapType;
    private javax.swing.JComboBox<String> SmoothnessMapUVFlip;
    private javax.swing.JComboBox<String> SpecularMapApplyScale;
    private javax.swing.JTextField SpecularMapFile;
    private javax.swing.JComboBox<String> SpecularMapFrom;
    private javax.swing.JComboBox<String> SpecularMapSwizzle;
    private javax.swing.JComboBox<String> SpecularMapType;
    private javax.swing.JComboBox<String> SpecularMapUVFlip;
    private javax.swing.JButton albedoHelp;
    private javax.swing.JButton albedoHelp1;
    private javax.swing.JButton back1;
    private javax.swing.JButton changeFile;
    private javax.swing.JButton changeFile1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    // End of variables declaration//GEN-END:variables
}
