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
import java.awt.Color;
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
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import static mmd.MaterialMakerv2.callMenu;
import rpmxc.Start;

public class EmissiveSection extends javax.swing.JFrame {

    public static MaterialMakerv2 foo = new MaterialMakerv2();
    public static WindowFrame wf = new WindowFrame();

    //String EmissiveScale = getEmissive();
    String EmissiveLoop = String.valueOf(getEmissiveLoop());
    String EmissiveBlink = String.valueOf(getEmissiveBlink());
    String EmissiveIntensity = String.valueOf(getEmissiveIntensity());
    ////////////////////////////////////////////////////////////////////
    //String lastEmissiveScale = getEmissive();
    String lastEmissiveLoop = String.valueOf(getEmissiveLoop());
    String lastEmissiveBlink = String.valueOf(getEmissiveBlink());
    String lastEmissiveIntensity = String.valueOf(getEmissiveIntensity());

    /*Emissive*/
    private static int CatchEmissiveMapFrom;
    private static int CatchEmissiveEnable;
    private static int CatchEmissiveMapUVFlip;
    private static int CatchEmissiveMapApplyMorphIntensity;
    private static int CatchEmissiveMapApplyBlink;

    private static int CatchEmissiveMapApplyScale;
    private static int CatchEmissiveMapApplyMorphColor;
    private static String CatchEmissiveMapFile = null;
    private static String CatchEmissiveScale;
    private static float CatchEmissiveLoop;
    private static float CatchEmissiveBlink;
    private static float CatchEmissiveIntensity;
    public int errors = 0;
    JFrame ErrorWindow = new JFrame();

    public void SomethingWentWrong() {
        if (errors == 1) {
            JLabel ErrorWindowText = new JLabel();
            //ErrorWindowText.
            ErrorWindowText.setText("<HTML><div style='padding-left:30px;'>Something went wrong while trying to load <i>Emissive Section</i>...<br><br>"
                    + "Please make sure the file you are trying to open doesn't <b>exceed the limit for each parameter</b>, usually this happens when you are trying to open "
                    + "a .fx file where some of it's parameters has <b>higher values</b> than supposed to be<br><br>"
                    + "<b>Limits: </b><br>"
                    + "<ul>"
                    + "<li>EmissiveEnable: 0 - 1</li>"
                    + "<li>EmissiveMapFrom: 0 - 8</li>"
                    + "<li>EmissiveMapUVFlip: 0 - 3</li>"
                    + "<li>EmissiveMapApplyScale: 0 - 2</li>"
                    + "<li>EmissiveMapApplyIntensity: 0 - 3</li>"
                    + "<li>EmissiveMapApplyMorphColor: 0 - 3</li>"
                    + "<li>EmissiveMapApplyBlink: 0 - 2</li>"
                    + "<br>"
                    + "</ul></div></HTML>");
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

    public int getEmissiveMapApplyBlink() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());

            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define EMISSIVE_MAP_APPLY_BLINK")) {

                    CatchEmissiveMapApplyBlink = Integer.parseInt(s.replaceAll("[\\D]", ""));
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
        return CatchEmissiveMapApplyBlink;
    }

    public int getEmissiveMapApplyMorphColor() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());

            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define EMISSIVE_MAP_APPLY_MORPH_COLOR")) {

                    CatchEmissiveMapApplyMorphColor = Integer.parseInt(s.replaceAll("[\\D]", ""));
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
        return CatchEmissiveMapApplyMorphColor;
    }

    public int getEmissiveMapFrom() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());

            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define EMISSIVE_MAP_FROM")) {

                    CatchEmissiveMapFrom = Integer.parseInt(s.replaceAll("[\\D]", ""));
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
        return CatchEmissiveMapFrom;
    }

    public int getEmissiveEnable() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define EMISSIVE_ENABLE")) {
                    CatchEmissiveEnable = Integer.parseInt(s.replaceAll("[\\D]", ""));

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
        return CatchEmissiveEnable;
    }

    public int getEmissiveMapUVFlip() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define EMISSIVE_MAP_UV_FLIP")) {
                    CatchEmissiveMapUVFlip = Integer.parseInt(s.replaceAll("[\\D]", ""));
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

        return CatchEmissiveMapUVFlip;
    }

    public int getEmissiveMapApplyMorphIntensity() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define EMISSIVE_MAP_APPLY_MORPH_INTENSITY")) {
                    CatchEmissiveMapApplyMorphIntensity = Integer.parseInt(s.replaceAll("[\\D]", ""));
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

        return CatchEmissiveMapApplyMorphIntensity;
    }

    public int getEmissiveMapApplyScale() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define EMISSIVE_MAP_APPLY_SCALE")) {
                    CatchEmissiveMapApplyScale = Integer.parseInt(s.replaceAll("[\\D]", ""));
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

        return CatchEmissiveMapApplyScale;
    }

    public String getEmissiveMapFile() {
        BufferedReader AlbedotoEdit_Br = null;

        try {

            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define EMISSIVE_MAP_FILE")) {
                    CatchEmissiveMapFile = "";
                    for (int i = s.indexOf('"') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';') {

                        } else {
                            CatchEmissiveMapFile += s.charAt(i);
                        }
                    }

                    CatchEmissiveMapFile = CatchEmissiveMapFile.replace("null", "");
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
        return CatchEmissiveMapFile;
    }

    public String getEmissive() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("emissive =")) {
                    String txt = "";
                    for (int i = s.indexOf('=') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';') {

                        } else {
                            txt += s.charAt(i);
                        }
                    }
                    CatchEmissiveScale = (txt);

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
        return CatchEmissiveScale;
    }

    public float getEmissiveBlink() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("emissiveBlink =")) {
                    String txt = "";
                    for (int i = s.indexOf('=') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';') {

                        } else {
                            txt += s.charAt(i);
                        }
                    }
                    CatchEmissiveBlink = Float.parseFloat(txt);
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
        return CatchEmissiveBlink;
    }

    public float getEmissiveIntensity() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("emissiveIntensity =")) {
                    String txt = "";
                    for (int i = s.indexOf('=') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';') {

                        } else {
                            txt += s.charAt(i);
                        }
                    }
                    CatchEmissiveIntensity = Float.parseFloat(txt);
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
        return CatchEmissiveIntensity;
    }

    public float getEmissiveLoop() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("emissiveMapLoopNum =")) {
                    String txt = "";
                    for (int i = s.indexOf('=') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';') {

                        } else {
                            txt += s.charAt(i);
                        }
                    }
                    CatchEmissiveLoop = Float.parseFloat(txt);
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
        return CatchEmissiveLoop;
    }

    /**
     * Creates new form WindowFrame
     */
    public EmissiveSection() {//Constructor

        initComponents();//Generated by the GUI mostly
        myInitComponents(); //Emissive Loop
        myInitComponents2(); //EmissiveBlink
        myInitComponents4(); //Emissive Intensity

    }

    public void myInitComponents() {

        final DecimalFormat df = new DecimalFormat("0.####");
        final JTextField text = new JTextField(20);

        final DoubleJSlider slider = new DoubleJSlider(-10000, 10000, 0, 100);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                try {

                    text.setText(df.format(slider.getScaledValue()));
                    EmissiveLoop = text.getText();
                    if (!slider.getValueIsAdjusting() && !EmissiveLoop.equalsIgnoreCase(lastEmissiveLoop)) {
                        lastEmissiveLoop = EmissiveLoop;
                        BufferedReader br = null;
                        try {
                            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                            String oldtext = "";
                            String line = br.readLine();
                            String catchOld = "";
                            String catchNew = "";
                            while (line != null) {
                                if (line.contains("emissiveMapLoopNum")) {
                                    catchOld = line; //auxiliar line
                                    String aux = "";
                                    for (int i = catchOld.indexOf('=') + 1; i < catchOld.length() - 1; i++) {
                                        aux += catchOld.charAt(i);
                                    }
                                    catchOld = aux;
                                    if (!catchOld.equalsIgnoreCase(EmissiveLoop)) {
                                        EmissiveLoop = EmissiveLoop.replace(",", ".");
                                        catchNew = EmissiveLoop; //catchnewdigit
                                        line = "const float2 emissiveMapLoopNum = " + catchNew + ";";
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
                        } catch (Exception ex) {

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
                EmissiveLoop = text.getText();
            }
        });
        float catchvalue;
        catchvalue = Float.parseFloat("" + getEmissiveLoop()) * slider.scale;
        slider.setPaintTrack(true);
        slider.setPaintLabels(true);
        slider.setBounds(578, 332, 200, 30);
        slider.setPaintTicks(true);
        slider.setValue((int) catchvalue);
        text.setBounds(788, 332, 50, 20);

        add(text);
        add(slider);
    }

    public void myInitComponents2() {

        final DecimalFormat df = new DecimalFormat("0.####");
        final JTextField text = new JTextField(20);

        final DoubleJSlider slider = new DoubleJSlider(0, 1000, 0, 100);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    text.setText(df.format(slider.getScaledValue()));
                    EmissiveBlink = text.getText();
                    if (!slider.getValueIsAdjusting() && !EmissiveBlink.equalsIgnoreCase(lastEmissiveBlink)) {
                        lastEmissiveBlink = EmissiveBlink;
                        BufferedReader br = null;
                        try {
                            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                            String oldtext = "";
                            String line = br.readLine();
                            String catchOld = "";
                            String catchNew = "";
                            while (line != null) {
                                if (line.contains("emissiveBlink =")) {
                                    catchOld = line; //auxiliar line
                                    String aux = "";
                                    for (int i = catchOld.indexOf('=') + 1; i < catchOld.length() - 1; i++) {
                                        aux += catchOld.charAt(i);
                                    }
                                    catchOld = aux;
                                    if (!catchOld.equalsIgnoreCase(EmissiveBlink)) {
                                        EmissiveBlink = EmissiveBlink.replace(",", ".");
                                        catchNew = EmissiveBlink; //catchnewdigit
                                        line = "const float3 emissiveBlink = " + catchNew + ";";
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
                        } catch (Exception ex) {

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
                EmissiveBlink = text.getText();
            }
        });
        float catchvalue;
        catchvalue = Float.parseFloat("" + getEmissiveBlink()) * slider.scale;
        slider.setPaintTrack(true);
        slider.setPaintLabels(true);
        slider.setBounds(578, 203, 200, 30);
        slider.setPaintTicks(true);
        slider.setValue((int) catchvalue);
        text.setBounds(788, 203, 50, 20);

        add(text);
        add(slider);
    }

    public void myInitComponents4() {

        final DecimalFormat df = new DecimalFormat("0.####");
        final JTextField text = new JTextField(20);

        final DoubleJSlider slider = new DoubleJSlider(0, 10000, 0, 100);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                try {

                    text.setText(df.format(slider.getScaledValue()));
                    EmissiveIntensity = text.getText();
                    if (!slider.getValueIsAdjusting() && !EmissiveIntensity.equalsIgnoreCase(lastEmissiveIntensity)) {
                        lastEmissiveIntensity = EmissiveIntensity;
                        BufferedReader br = null;
                        try {
                            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                            String oldtext = "";
                            String line = br.readLine();
                            String catchOld = "";
                            String catchNew = "";
                            while (line != null) {
                                if (line.contains("emissiveIntensity =")) {
                                    catchOld = line; //auxiliar line
                                    String aux = "";
                                    for (int i = catchOld.indexOf('=') + 1; i < catchOld.length() - 1; i++) {
                                        aux += catchOld.charAt(i);
                                    }
                                    catchOld = aux;
                                    if (!catchOld.equalsIgnoreCase(EmissiveIntensity)) {
                                        EmissiveIntensity = EmissiveIntensity.replace(",", ".");
                                        catchNew = EmissiveIntensity; //catchnewdigit
                                        line = "const float  emissiveIntensity = " + catchNew + ";";
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
                        } catch (Exception ex) {

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
                EmissiveIntensity = text.getText();
            }
        });
        float catchvalue;
        catchvalue = Float.parseFloat("" + getEmissiveIntensity()) * slider.scale;
        slider.setPaintTrack(true);
        slider.setPaintLabels(true);
        slider.setBounds(578, 265, 200, 30);
        slider.setPaintTicks(true);
        slider.setValue((int) catchvalue);
        text.setBounds(788, 265, 50, 20);

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
        EmissiveMapFrom = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        changeFile = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        EmissiveMapUVFlip = new javax.swing.JComboBox<>();
        AlbedoMapHelp = new javax.swing.JButton();
        AlbedoMapUVFlipHelp = new javax.swing.JButton();
        AlbedoMapFileHelp = new javax.swing.JButton();
        albedoHelp = new javax.swing.JButton();
        AlbedoMapLoopHelp = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        EmissiveEnable = new javax.swing.JComboBox<>();
        AlbedoMapHelp3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        AlbedoMapUVFlipHelp1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        albedoHelp1 = new javax.swing.JButton();
        AlbedoMapLoopHelp1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        EmissiveMapApplyScale = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        EmissiveMapApplyMorphColor = new javax.swing.JComboBox<>();
        EmissiveMapApplyMorphIntensity = new javax.swing.JComboBox<>();
        EmissiveMapApplyBlink = new javax.swing.JComboBox<>();
        AlbedoMapUVFlipHelp2 = new javax.swing.JButton();
        AlbedoMapUVFlipHelp3 = new javax.swing.JButton();
        AlbedoMapUVFlipHelp4 = new javax.swing.JButton();
        EmissiveMapFile = new javax.swing.JTextField();
        back1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        EmissiveScale = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Edit Emissive of "+foo.getFileToEdit().getName());
        setAutoRequestFocus(false);
        setResizable(false);
        setSize(new java.awt.Dimension(960, 549));

        jLabel1.setText("<html><b>EMISSIVE MAP FROM</b></html>");

        EmissiveMapFrom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8" }));
        try{
            EmissiveMapFrom.setSelectedIndex(getEmissiveMapFrom());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        EmissiveMapFrom.setToolTipText("");
        EmissiveMapFrom.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                EmissiveMapFromItemStateChanged(evt);
            }
        });
        EmissiveMapFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmissiveMapFromActionPerformed(evt);
            }
        });

        jLabel2.setText("<html><b>EMISSIVE MAP UV FLIP</b></html>");

        jLabel6.setText("<html><b>EMISSIVE MAP FILE</b></html>");

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

        jLabel8.setText("<html><b>EMISSIVE MAP SCALE</b></html>");

        jLabel9.setText("<html><b>EMISSIVE MAP LOOP</b></html>");

        EmissiveMapUVFlip.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        try{
            EmissiveMapUVFlip.setSelectedIndex(getEmissiveMapUVFlip());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        EmissiveMapUVFlip.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                EmissiveMapUVFlipItemStateChanged(evt);
            }
        });
        EmissiveMapUVFlip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmissiveMapUVFlipActionPerformed(evt);
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

        jLabel3.setText("<html><b>EMISSIVE MAP ENABLE</b></html>");

        EmissiveEnable.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1" }));
        try{
            EmissiveEnable.setSelectedIndex(getEmissiveEnable());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        EmissiveEnable.setToolTipText("");
        EmissiveEnable.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                EmissiveEnableItemStateChanged(evt);
            }
        });
        EmissiveEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmissiveEnableActionPerformed(evt);
            }
        });

        AlbedoMapHelp3.setText("Help");
        AlbedoMapHelp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapHelp3ActionPerformed(evt);
            }
        });

        jLabel5.setText("<html><b>EMISSIVE MAP APPLY SCALE</b></html>");

        AlbedoMapUVFlipHelp1.setText("Help");
        AlbedoMapUVFlipHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapUVFlipHelp1ActionPerformed(evt);
            }
        });

        jLabel10.setText("<html><b>EMISSIVE MAP BLINK</b></html>");

        jLabel11.setText("<html><b>EMISSIVE MAP INTENSITY</b></html>");

        albedoHelp1.setText("Help");
        albedoHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albedoHelp1ActionPerformed(evt);
            }
        });

        AlbedoMapLoopHelp1.setText("Help");
        AlbedoMapLoopHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapLoopHelp1ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bg/tip.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setDefaultCapable(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        EmissiveMapApplyScale.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        try{
            EmissiveMapApplyScale.setSelectedIndex(getEmissiveMapApplyScale());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        EmissiveMapApplyScale.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                EmissiveMapApplyScaleItemStateChanged(evt);
            }
        });
        EmissiveMapApplyScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmissiveMapApplyScaleActionPerformed(evt);
            }
        });

        jLabel7.setText("<html><b>EMISSIVE MAP APPLY MORPH COLOR</b></html>");

        jLabel12.setText("<html><b>EMISSIVE MAP APPLY MORPH INTENSITY</b></html>");

        jLabel13.setText("<html><b>EMISSIVE MAP APPLY BLINK</b></html>");

        EmissiveMapApplyMorphColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        try{
            EmissiveMapApplyMorphColor.setSelectedIndex(getEmissiveMapApplyMorphColor());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        EmissiveMapApplyMorphColor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                EmissiveMapApplyMorphColorItemStateChanged(evt);
            }
        });
        EmissiveMapApplyMorphColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmissiveMapApplyMorphColorActionPerformed(evt);
            }
        });

        EmissiveMapApplyMorphIntensity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        try{
            EmissiveMapApplyMorphIntensity.setSelectedIndex(getEmissiveMapApplyMorphIntensity());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        EmissiveMapApplyMorphIntensity.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                EmissiveMapApplyMorphIntensityItemStateChanged(evt);
            }
        });
        EmissiveMapApplyMorphIntensity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmissiveMapApplyMorphIntensityActionPerformed(evt);
            }
        });

        EmissiveMapApplyBlink.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2" }));
        try{
            EmissiveMapApplyBlink.setSelectedIndex(getEmissiveMapApplyBlink());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        EmissiveMapApplyBlink.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                EmissiveMapApplyBlinkItemStateChanged(evt);
            }
        });
        EmissiveMapApplyBlink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmissiveMapApplyBlinkActionPerformed(evt);
            }
        });

        AlbedoMapUVFlipHelp2.setText("Help");
        AlbedoMapUVFlipHelp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapUVFlipHelp2ActionPerformed(evt);
            }
        });

        AlbedoMapUVFlipHelp3.setText("Help");
        AlbedoMapUVFlipHelp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapUVFlipHelp3ActionPerformed(evt);
            }
        });

        AlbedoMapUVFlipHelp4.setText("Help");
        AlbedoMapUVFlipHelp4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapUVFlipHelp4ActionPerformed(evt);
            }
        });

        try{
            EmissiveMapFile.setEditable(false);
            EmissiveMapFile.setText(getEmissiveMapFile());
        }catch(Exception e){

        }

        back1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bg/back.png"))); // NOI18N
        back1.setBorder(null);
        back1.setContentAreaFilled(false);
        back1.setFocusable(false);
        back1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back1ActionPerformed(evt);
            }
        });

        jButton3.setText("Preview");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        try{
            EmissiveScale.setText(String.valueOf(getEmissive())
            );
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        EmissiveScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmissiveScaleActionPerformed(evt);
            }
        });
        EmissiveScale.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                EmissiveScalePropertyChange(evt);
            }
        });
        EmissiveScale.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                EmissiveScaleKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                EmissiveScaleKeyTyped(evt);
            }
        });

        jButton2.setText("Pick Colour");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(EmissiveMapFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AlbedoMapHelp))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(EmissiveEnable, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AlbedoMapHelp3))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(EmissiveMapFile, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(EmissiveMapApplyMorphIntensity, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AlbedoMapUVFlipHelp3))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(EmissiveMapApplyScale, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AlbedoMapUVFlipHelp1))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(EmissiveMapApplyMorphColor, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(AlbedoMapUVFlipHelp2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(EmissiveMapUVFlip, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(AlbedoMapUVFlipHelp))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(EmissiveMapApplyBlink, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(changeFile, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(AlbedoMapFileHelp)
                                            .addComponent(AlbedoMapUVFlipHelp4))))
                                .addGap(134, 134, 134)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(AlbedoMapLoopHelp))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(albedoHelp1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(AlbedoMapLoopHelp1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(albedoHelp))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(EmissiveScale, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2)))))))
                .addGap(101, 101, 101))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EmissiveEnable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AlbedoMapHelp3)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AlbedoMapHelp)
                            .addComponent(EmissiveMapFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(albedoHelp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EmissiveScale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(EmissiveMapUVFlip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(AlbedoMapUVFlipHelp)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(albedoHelp1)))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AlbedoMapUVFlipHelp1)
                    .addComponent(EmissiveMapApplyScale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AlbedoMapLoopHelp1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AlbedoMapLoopHelp))
                        .addGap(161, 161, 161))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EmissiveMapApplyMorphColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AlbedoMapUVFlipHelp2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EmissiveMapApplyMorphIntensity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AlbedoMapUVFlipHelp3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EmissiveMapApplyBlink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AlbedoMapUVFlipHelp4)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(changeFile)
                            .addComponent(AlbedoMapFileHelp)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EmissiveMapFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(119, 119, 119))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EmissiveMapFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmissiveMapFromActionPerformed

    }//GEN-LAST:event_EmissiveMapFromActionPerformed

    private void EmissiveMapUVFlipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmissiveMapUVFlipActionPerformed

    }//GEN-LAST:event_EmissiveMapUVFlipActionPerformed

    private void changeFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeFileActionPerformed
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new java.io.File("../../Materials"));//The directory we are looking for is the Materials folder from ray
        fileChooser.setDialogTitle("Choose new Emissive Map File");
        //Filtering by ImageFiles
        FileFilter imageFilter = new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp", "tga", "targa", "dds", "tif", "tiff", "jpeg", "pcd");
        fileChooser.setFileFilter(imageFilter);

        try {
            int selection = fileChooser.showOpenDialog(this);
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            filePath = filePath.replace("\\", "/");

            if (selection == 0) {
                String relative = toRelative.convertToRelativePath(foo.getFileToEdit().getParent(), filePath);
                EmissiveMapFile.setText(relative);
                if (EmissiveMapFrom.getSelectedIndex() != 1) {
                    EmissiveMapFrom.setSelectedIndex(1);
                }
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                    String oldtext = "";
                    String line = br.readLine();
                    String catchOld = "";
                    String catchNew = "";
                    while (line != null) {
                        if (line.contains("#define EMISSIVE_MAP_FILE")) {
                            catchOld = line; //auxiliar line
                            String aux = "";
                            for (int i = catchOld.indexOf('"') + 1; i < line.length() - 1; i++) {
                                if (catchOld.charAt(i) == '"') {
                                    break;
                                } else if (catchOld.charAt(i) == ';' || catchOld.charAt(i) == '"') {

                                } else {
                                    aux += catchOld.charAt(i);
                                }
                            }
                            catchOld = aux;
                            if (!catchOld.equalsIgnoreCase(EmissiveMapFile.getText())) {
                                catchNew = EmissiveMapFile.getText(); //catchnewdigit
                                line = "#define EMISSIVE_MAP_FILE " + '"' + catchNew + '"';
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
                } catch (Exception ex) {

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
                + "    <li><strike>9 : Params fetch from Specular Power from the pmx. (this option can only be used for specular)</strike>, doesn't work on Emissive</li></ul></pre><br><br></HTML>");
        AlbedoMapHelp.setLayout(new BorderLayout());
        AlbedoMapHelp.setSize(700, 550);
        AlbedoMapHelp.setLocationRelativeTo(this);
        AlbedoMapHelp.setResizable(true);
        AlbedoMapHelp.setVisible(true);
        AlbedoMapHelp.add(AlbedoMapHelpText);
        AlbedoMapHelp.setTitle("Emissive Map From Help");

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
        help.setTitle("Emissive Map UV Flip Help");

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
        help.setTitle("Emissive Map Filep Help");

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
        helptext.setText("<html>between 0 ~ 1</html>");
        help.setLayout(new BorderLayout());
        help.setSize(300, 70);
        help.setLocationRelativeTo(this);
        help.setResizable(true);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Emissive Map Scale Help");

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
        help.setTitle("Emissive Map Loop Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapLoopHelpActionPerformed

    private void EmissiveEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmissiveEnableActionPerformed

    }//GEN-LAST:event_EmissiveEnableActionPerformed

    private void AlbedoMapHelp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapHelp3ActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        helptext.setText("<html>0 in order to  Disable, 1 for Enabling </html>");
        help.setLayout(new BorderLayout());
        help.setSize(400, 70);
        help.setLocationRelativeTo(this);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setResizable(true);
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Emissive Map Enable Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapHelp3ActionPerformed

    private void AlbedoMapUVFlipHelp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapUVFlipHelp1ActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        helptext.setText("<HTML><center>You can apply color from const float3 albedo = 1.0; to change colors in your texture by set code to the ALBEDO_MAP_APPLY_SCALE</center><br><br>"
                + "<ul><li><b>1 : map values * albedo;</li>"
                + "<li><b>2 : map values ^ albedo;</li>"
                + "</ul></HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(500, 160);
        help.setLocationRelativeTo(this);
        help.setResizable(true);
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Emissive Map Apply Scale Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapUVFlipHelp1ActionPerformed

    private void albedoHelp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albedoHelp1ActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        helptext.setText("<HTML>between 0 ~ 10</HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(300, 70);
        help.setLocationRelativeTo(this);
        help.setResizable(true);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Emissive Map Blink Help");

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
        helptext.setText("<HTML>between 0 ~ 100</HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(300, 70);
        help.setLocationRelativeTo(this);
        help.setResizable(true);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Emissive Map Intensity Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapLoopHelp1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");

        helptext.setText("<html><b>Tips </b>: You can add a light source in MMD (PointLight or others)<br><br>"
                + "And key it as part of emissive of the model (In other words : set it to follow the bone of model)<br>"
                + "And same color set it to your light source and emissive color<br></html>");
        help.setLayout(new BorderLayout());
        help.setSize(700, 250);
        help.setLocationRelativeTo(this);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setResizable(true);
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Emissive Tips");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void EmissiveMapApplyScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmissiveMapApplyScaleActionPerformed

    }//GEN-LAST:event_EmissiveMapApplyScaleActionPerformed

    private void EmissiveMapApplyMorphColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmissiveMapApplyMorphColorActionPerformed

    }//GEN-LAST:event_EmissiveMapApplyMorphColorActionPerformed

    private void EmissiveMapApplyMorphIntensityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmissiveMapApplyMorphIntensityActionPerformed

    }//GEN-LAST:event_EmissiveMapApplyMorphIntensityActionPerformed

    private void EmissiveMapApplyBlinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmissiveMapApplyBlinkActionPerformed

    }//GEN-LAST:event_EmissiveMapApplyBlinkActionPerformed

    private void AlbedoMapUVFlipHelp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapUVFlipHelp2ActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        helptext.setText("<HTML>Texture colors to multiply with color from the morph <b>controller</b> (R+/G+/B+)...</HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(500, 160);
        help.setLocationRelativeTo(this);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setResizable(true);
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Emissive Map Apply Morph Color Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapUVFlipHelp2ActionPerformed

    private void AlbedoMapUVFlipHelp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapUVFlipHelp3ActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        helptext.setText("<HTML>Texture colors to multiply with color from the morph <b>controller</b> (R+/G+/B+)...</HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(500, 160);
        help.setLocationRelativeTo(this);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setResizable(true);
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Emissive Map Apply Morph Intensity Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapUVFlipHelp3ActionPerformed

    private void AlbedoMapUVFlipHelp4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapUVFlipHelp4ActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        helptext.setText("<HTML>You can set the blink using the following code.<br><br>"
                + ""
                + "<ul><li>1 : colors to multiply with frequency from emissiveBlink. like : const float3 emissiveBlink = float3(1.0, 2.0, 3.0);</li>"
                + "<li>2 : colors to multiply with frequency from morph controller, see Blink morph inside PointLight.pmx</li></ul></HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(500, 160);
        help.setLocationRelativeTo(this);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setResizable(true);
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Emissive Map Apply Blink Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapUVFlipHelp4ActionPerformed
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

    private void EmissiveEnableItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_EmissiveEnableItemStateChanged
        // TODO add your handling code here:
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define EMISSIVE_ENABLE")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = EmissiveEnable.getSelectedItem().toString(); //catchnewdigit
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
        } catch (Exception ex) {

        }

    }//GEN-LAST:event_EmissiveEnableItemStateChanged

    private void EmissiveMapFromItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_EmissiveMapFromItemStateChanged
        // TODO add your handling code here:
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define EMISSIVE_MAP_FROM")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = EmissiveMapFrom.getSelectedItem().toString(); //catchnewdigit
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
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_EmissiveMapFromItemStateChanged

    private void EmissiveMapUVFlipItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_EmissiveMapUVFlipItemStateChanged
        // TODO add your handling code here:
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define EMISSIVE_MAP_UV_FLIP")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = EmissiveMapUVFlip.getSelectedItem().toString(); //catchnewdigit
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
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_EmissiveMapUVFlipItemStateChanged

    private void EmissiveMapApplyScaleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_EmissiveMapApplyScaleItemStateChanged
        // TODO add your handling code here:
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define EMISSIVE_MAP_APPLY_SCALE")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = EmissiveMapApplyScale.getSelectedItem().toString(); //catchnewdigit
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
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_EmissiveMapApplyScaleItemStateChanged

    private void EmissiveMapApplyMorphColorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_EmissiveMapApplyMorphColorItemStateChanged
        // TODO add your handling code here:
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define EMISSIVE_MAP_APPLY_MORPH_COLOR")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = EmissiveMapApplyMorphColor.getSelectedItem().toString(); //catchnewdigit
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
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_EmissiveMapApplyMorphColorItemStateChanged

    private void EmissiveMapApplyMorphIntensityItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_EmissiveMapApplyMorphIntensityItemStateChanged
        // TODO add your handling code here:
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define EMISSIVE_MAP_APPLY_MORPH_INTENSITY")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = EmissiveMapApplyMorphIntensity.getSelectedItem().toString(); //catchnewdigit
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
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_EmissiveMapApplyMorphIntensityItemStateChanged

    private void EmissiveMapApplyBlinkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_EmissiveMapApplyBlinkItemStateChanged
        // TODO add your handling code here:
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define EMISSIVE_MAP_APPLY_BLINK")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = EmissiveMapApplyBlink.getSelectedItem().toString(); //catchnewdigit
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
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_EmissiveMapApplyBlinkItemStateChanged
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
        previewImg(EmissiveMapFile.getText());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void EmissiveScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmissiveScaleActionPerformed

        float f = Float.parseFloat(EmissiveScale.getText());
    }//GEN-LAST:event_EmissiveScaleActionPerformed

    private void EmissiveScalePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_EmissiveScalePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_EmissiveScalePropertyChange

    private void EmissiveScaleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EmissiveScaleKeyReleased
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("emissive =")) {
                    catchOld = line; //auxiliar line

                    catchNew = EmissiveScale.getText(); //catchnewdigit
                    catchNew = catchNew.replaceAll(" ", "");
                    line = ("const float3 emissive = " + catchNew + ";");
<<<<<<< HEAD
                    
=======
                    System.out.println("" + catchNew);
>>>>>>> 29c2b9324a955f83e5bc4c8af8e1d0ae353c16e4
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
    }//GEN-LAST:event_EmissiveScaleKeyReleased

    private void EmissiveScaleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EmissiveScaleKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_EmissiveScaleKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        JColorChooser cc = new JColorChooser();

        AbstractColorChooserPanel defaultPanels[] = cc.getChooserPanels();
        cc.removeChooserPanel(defaultPanels[3]);
        cc.removeChooserPanel(defaultPanels[2]);
        cc.removeChooserPanel(defaultPanels[0]);
        cc.removeChooserPanel(defaultPanels[4]);
        ImageIcon ico = new ImageIcon(getClass().getResource("/icon/colorchooser.png"));
        int ColorPicker = JOptionPane.showConfirmDialog(this, cc, "Choose a Colour", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, ico);

        Color color = cc.getColor();
        if (ColorPicker == JOptionPane.OK_OPTION) {
            String codeColor = "" + color.getRed() + "," + color.getGreen() + "," + color.getBlue();
            EmissiveScale.setText("float3(" + codeColor + ")/255");
            try {
                BufferedReader br = null;
                br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                String oldtext = "";
                String line = br.readLine();
                String catchOld = "";
                String catchNew = "";
                while (line != null) {
                    if (line.contains("emissive =")) {
                        catchOld = line; //auxiliar line

                        catchNew = EmissiveScale.getText(); //catchnewdigit
                        catchNew = catchNew.replaceAll(" ", "");
                        line = ("const float3 emissive = " + catchNew + ";");
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
            EmissiveMapFrom.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
    private javax.swing.JButton AlbedoMapHelp;
    private javax.swing.JButton AlbedoMapHelp3;
    private javax.swing.JButton AlbedoMapLoopHelp;
    private javax.swing.JButton AlbedoMapLoopHelp1;
    private javax.swing.JButton AlbedoMapUVFlipHelp;
    private javax.swing.JButton AlbedoMapUVFlipHelp1;
    private javax.swing.JButton AlbedoMapUVFlipHelp2;
    private javax.swing.JButton AlbedoMapUVFlipHelp3;
    private javax.swing.JButton AlbedoMapUVFlipHelp4;
    private javax.swing.JComboBox<String> EmissiveEnable;
    private javax.swing.JComboBox<String> EmissiveMapApplyBlink;
    private javax.swing.JComboBox<String> EmissiveMapApplyMorphColor;
    private javax.swing.JComboBox<String> EmissiveMapApplyMorphIntensity;
    private javax.swing.JComboBox<String> EmissiveMapApplyScale;
    private javax.swing.JTextField EmissiveMapFile;
    private javax.swing.JComboBox<String> EmissiveMapFrom;
    private javax.swing.JComboBox<String> EmissiveMapUVFlip;
    private javax.swing.JTextField EmissiveScale;
    private javax.swing.JButton albedoHelp;
    private javax.swing.JButton albedoHelp1;
    private javax.swing.JButton back1;
    private javax.swing.JButton changeFile;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    // End of variables declaration//GEN-END:variables
}
