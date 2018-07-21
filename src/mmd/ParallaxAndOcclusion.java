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
import java.awt.Component;
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
import static mmd.MaterialMakerv2.callMenu;

public class ParallaxAndOcclusion extends javax.swing.JFrame {

    public static MaterialMakerv2 foo = new MaterialMakerv2();
    public static WindowFrame wf = new WindowFrame();

    String ParallaxScale = getParallax();
    String ParallaxLoop = String.valueOf(getParallaxLoop());
    String Occlusion = getOcclusion();
    String OcclusionLoop = String.valueOf(getOcclusionLoop());
    ///////////////////////////////////////////////////////////
    String lastParallaxScale = getParallax();
    String lastParallaxLoop = String.valueOf(getParallaxLoop());
    String lastOcclusion = getOcclusion();
    String lastOcclusionLoop = String.valueOf(getOcclusionLoop());

    final static int Yaxis = 335;

    /*Parallax*/
    private static int CatchParallaxMapFrom;
    private static int CatchParallaxMapType;
    private static int CatchParallaxMapUVFlip;
    private static int CatchParallaxMapSwizzle;
    private static int CatchParallaxMapApplyScale;
    private static String CatchParallaxMapFile = null;
    private static String CatchParallaxScale;
    private static float CatchParallaxLoop;

    /*Occlusion*/
    private static int CatchOcclusionMapFrom;
    private static int CatchOcclusionMapType;
    private static int CatchOcclusionMapUVFlip;
    private static int CatchOcclusionMapSwizzle;
    private static int CatchOcclusionMapApplyScale;
    private static String CatchOcclusionMapFile = null;
    private static String CatchOcclusionScale;
    private static float CatchOcclusionLoop;
    public int errors = 0;
    JFrame ErrorWindow = new JFrame();

    public void SomethingWentWrong() {
        if (errors == 1) {
            JLabel ErrorWindowText = new JLabel();
            //ErrorWindowText.
            ErrorWindowText.setText("<HTML><div style='padding-left:30px;'>Something went wrong while trying to load <i>ParallaxAndOcclussion Section</i>...<br><br>"
                    + "Please make sure the file you are trying to open doesn't <b>exceed the limit for each parameter</b>, usually this happens when you are trying to open "
                    + "a .fx file where some of it's parameters has <b>higher values</b> than supposed to be<br><br>"
                    + "<b>Limits for Parallax: </b><br>"
                    + "<ul>"
                    + "<li>ParallaxMapFrom: 0 - 8</li>"
                    + "<li>ParallaxMapType: 0 - 1</li>"
                    + "<li>ParallaxMapUVFlip: 0 - 3</li>"
                    + "<li>ParallaxMapApplySwizzle: 0 - 3</li>"
                    + "<br>"
                    + "</ul>"
                    + "<b>Limits for Occlusion:</b>"
                    + "<ul>"
                    + "<li>OcclusionMapFrom: 0 - 8</li>"
                    + "<li>OcclusionMapType: 0 - 1</li>"
                    + "<li>OcclusionMapUVFlip: 0 - 3</li>"
                    + "<li>OcclusionMapApplySwizzle: 0 - 3</li>"
                    + "<li>OcclusionMapApplyScale: 0 - 2</li>"
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

    public int getParallaxMapFrom() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());

            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define PARALLAX_MAP_FROM")) {

                    CatchParallaxMapFrom = Integer.parseInt(s.replaceAll("[\\D]", ""));
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
        return CatchParallaxMapFrom;
    }

    public int getParallaxMapType() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define PARALLAX_MAP_TYPE")) {
                    CatchParallaxMapType = Integer.parseInt(s.replaceAll("[\\D]", ""));

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
        return CatchParallaxMapType;
    }

    public int getParallaxMapUVFlip() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define PARALLAX_MAP_UV_FLIP")) {
                    CatchParallaxMapUVFlip = Integer.parseInt(s.replaceAll("[\\D]", ""));
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

        return CatchParallaxMapUVFlip;
    }

    public int getParallaxMapSwizzle() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define PARALLAX_MAP_SWIZZLE")) {
                    CatchParallaxMapSwizzle = Integer.parseInt(s.replaceAll("[\\D]", ""));
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

        return CatchParallaxMapSwizzle;
    }

    public int getParallaxMapApplyScale() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define PARALLAX_MAP_APPLY_SCALE")) {
                    CatchParallaxMapApplyScale = Integer.parseInt(s.replaceAll("[\\D]", ""));
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

        return CatchParallaxMapApplyScale;
    }

    public String getParallaxMapFile() {
        BufferedReader AlbedotoEdit_Br = null;

        try {

            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define PARALLAX_MAP_FILE")) {
                    CatchParallaxMapFile = "";
                    for (int i = s.indexOf('"') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';') {

                        } else {
                            CatchParallaxMapFile += s.charAt(i);
                        }
                    }

                    CatchParallaxMapFile = CatchParallaxMapFile.replace("null", "");
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
        return CatchParallaxMapFile;
    }

    public String getParallax() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("parallaxMapScale")) {
                    String txt = "";
                    for (int i = s.indexOf('=') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';' || s.charAt(i) == ' ') {

                        } else {
                            txt += s.charAt(i);
                        }
                    }
                    CatchParallaxScale = (txt);

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
        return CatchParallaxScale;
    }

    public float getParallaxLoop() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains(" parallaxMapLoopNum")) {
                    String txt = "";
                    for (int i = s.indexOf('=') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';' || s.charAt(i) == ' ') {

                        } else {
                            txt += s.charAt(i);
                        }
                    }
                    CatchParallaxLoop = Float.parseFloat(txt);
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
        return CatchParallaxLoop;
    }

    //Occlusion
    public int getOcclusionMapFrom() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());

            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define OCCLUSION_MAP_FROM")) {

                    CatchOcclusionMapFrom = Integer.parseInt(s.replaceAll("[\\D]", ""));

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
        return CatchOcclusionMapFrom;
    }

    public int getOcclusionMapUVFlip() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define OCCLUSION_MAP_UV_FLIP")) {
                    CatchOcclusionMapUVFlip = Integer.parseInt(s.replaceAll("[\\D]", ""));
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

        return CatchOcclusionMapUVFlip;
    }

    public int getOcclusionMapSwizzle() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define OCCLUSION_MAP_SWIZZLE")) {
                    CatchOcclusionMapSwizzle = Integer.parseInt(s.replaceAll("[\\D]", ""));
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

        return CatchOcclusionMapSwizzle;
    }

    public int getOcclusionMapType() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define OCCLUSION_MAP_APPLY_SCALE")) {
                    CatchOcclusionMapType = Integer.parseInt(s.replaceAll("[\\D]", ""));
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

        return CatchOcclusionMapType;
    }

    public int getOcclusionMapApplyScale() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define OCCLUSION_MAP_APPLY_SCALE")) {
                    CatchOcclusionMapApplyScale = Integer.parseInt(s.replaceAll("[\\D]", ""));
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

        return CatchOcclusionMapApplyScale;
    }

    public String getOcclusionMapFile() {
        BufferedReader AlbedotoEdit_Br = null;

        try {

            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define OCCLUSION_MAP_FILE")) {
                    CatchOcclusionMapFile = "";
                    for (int i = s.indexOf('"') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';') {

                        } else {
                            CatchOcclusionMapFile += s.charAt(i);
                        }
                    }

                    CatchOcclusionMapFile = CatchOcclusionMapFile.replace("null", "");
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
        return CatchOcclusionMapFile;
    }

    public String getOcclusion() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("occlusion ")) {
                    String txt = "";
                    for (int i = s.indexOf('=') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';' || s.charAt(i) == ' ') {

                        } else {
                            txt += s.charAt(i);
                        }
                    }
                    CatchOcclusionScale = (txt);

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
        return CatchOcclusionScale;
    }

    public float getOcclusionLoop() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains(" occlusionMapLoopNum")) {
                    String txt = "";
                    for (int i = s.indexOf('=') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';' || s.charAt(i) == ' ') {

                        } else {
                            txt += s.charAt(i);
                        }
                    }
                    CatchOcclusionLoop = Float.parseFloat(txt);
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
        return CatchOcclusionLoop;
    }

    /**
     * Creates new form WindowFrame
     */
    public ParallaxAndOcclusion() {//Constructor

        initComponents();//Generated by the GUI mostly
        myInitComponents(); //Parallax Loop
        myInitComponents2(); //Occlusion Loop
        myInitComponents3(); //Parallax Scale
        myInitComponents4(); //Occlusion Scale
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
                    ParallaxLoop = text.getText();
                    if (!slider.getValueIsAdjusting() && !ParallaxLoop.equalsIgnoreCase(lastParallaxLoop)) {
                        lastParallaxLoop = ParallaxLoop;
                        try {
                            BufferedReader br = null;
                            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                            String oldtext = "";
                            String line = br.readLine();
                            String catchOld = "";
                            String catchNew = "";
                            while (line != null) {
                                if (line.contains("parallaxMapLoopNum")) {
                                    catchOld = line; //auxiliar line
                                    String aux = "";
                                    for (int i = catchOld.indexOf('=') + 1; i < catchOld.length() - 1; i++) {
                                        aux += catchOld.charAt(i);
                                    }
                                    catchOld = aux;
                                    if (!catchOld.equalsIgnoreCase(ParallaxLoop)) {
                                        ParallaxLoop = ParallaxLoop.replace(",", ".");
                                        catchNew = ParallaxLoop; //catchnewdigit
                                        line = "const float parallaxMapLoopNum = " + catchNew + ';';
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
                ParallaxLoop = text.getText();
            }
        });
        float catchvalue;
        catchvalue = Float.parseFloat("" + getParallaxLoop()) * slider.scale;
        slider.setPaintTrack(true);
        slider.setPaintLabels(true);
        slider.setBounds(48, 399, 200, 30);
        slider.setPaintTicks(true);
        slider.setValue((int) catchvalue);
        text.setBounds(256, 399, 50, 20);

        add(text);
        add(slider);
    }

    public void myInitComponents2() {

        final DecimalFormat df = new DecimalFormat("0.####");
        final JTextField text = new JTextField(20);

        final DoubleJSlider slider = new DoubleJSlider(-6400, 6400, 0, 100);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                try {

                    text.setText(df.format(slider.getScaledValue()));
                    OcclusionLoop = text.getText();
                    if (!slider.getValueIsAdjusting() && !OcclusionLoop.equalsIgnoreCase(lastOcclusionLoop)) {
                        lastOcclusionLoop = OcclusionLoop;
                        try {
                            BufferedReader br = null;
                            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                            String oldtext = "";
                            String line = br.readLine();
                            String catchOld = "";
                            String catchNew = "";
                            while (line != null) {
                                if (line.contains("occlusionMapLoopNum")) {
                                    catchOld = line; //auxiliar line
                                    String aux = "";
                                    for (int i = catchOld.indexOf('=') + 1; i < catchOld.length() - 1; i++) {
                                        aux += catchOld.charAt(i);
                                    }
                                    catchOld = aux;
                                    if (!catchOld.equalsIgnoreCase(OcclusionLoop)) {
                                        OcclusionLoop = OcclusionLoop.replace(",", ".");
                                        catchNew = OcclusionLoop; //catchnewdigit
                                        line = "const float occlusionMapLoopNum = " + catchNew + ';';
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
                /*
                if(!typed.matches("\\d+(\\.\\d*)?")) {
                    return;
                }*/
                double value = Double.parseDouble(typed) * slider.scale;
                slider.setValue((int) value);
                OcclusionLoop = text.getText();
            }
        });
        float catchvalue;
        catchvalue = Float.parseFloat("" + getOcclusionLoop()) * slider.scale;
        slider.setPaintTrack(true);
        slider.setPaintLabels(true);
        slider.setBounds(505, 399, 200, 30);
        slider.setPaintTicks(true);
        slider.setValue((int) catchvalue);
        text.setBounds(711, 399, 50, 20);

        add(text);
        add(slider);
    }

    public void myInitComponents3() {

        final DecimalFormat df = new DecimalFormat("0.####");
        final JTextField text = new JTextField(20);

        final DoubleJSlider slider = new DoubleJSlider(-10000, 10000, 0, 100);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                try {

                    text.setText(df.format(slider.getScaledValue()));
                    ParallaxScale = text.getText();
                    if (!slider.getValueIsAdjusting() && !ParallaxScale.equalsIgnoreCase(lastParallaxScale)) {
                        lastParallaxScale = ParallaxScale;
                        try {
                            BufferedReader br = null;
                            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                            String oldtext = "";
                            String line = br.readLine();
                            String catchOld = "";
                            String catchNew = "";
                            while (line != null) {
                                if (line.contains("parallaxMapScale")) {
                                    catchOld = line; //auxiliar line
                                    String aux = "";
                                    for (int i = catchOld.indexOf('=') + 1; i < catchOld.length() - 1; i++) {
                                        aux += catchOld.charAt(i);
                                    }
                                    catchOld = aux;
                                    if (!catchOld.equalsIgnoreCase(ParallaxScale)) {
                                        ParallaxScale = ParallaxScale.replace(",", ".");
                                        catchNew = ParallaxScale; //catchnewdigit
                                        line = "const float parallaxMapScale = " + catchNew + ';';
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
                /*
                if(!typed.matches("\\d+(\\.\\d*)?")) {
                    return;
                }*/
                double value = Double.parseDouble(typed) * slider.scale;
                slider.setValue((int) value);
                ParallaxScale = text.getText();
            }
        });
        float catchvalue;
        catchvalue = Float.parseFloat("" + getParallax()) * slider.scale;
        slider.setPaintTrack(true);
        slider.setPaintLabels(true);
        slider.setBounds(48, Yaxis, 200, 30);
        slider.setPaintTicks(true);
        slider.setValue((int) catchvalue);
        text.setBounds(258, Yaxis, 50, 20);

        add(text);
        add(slider);
    }

    public void myInitComponents4() {

        final DecimalFormat df = new DecimalFormat("0.####");
        final JTextField text = new JTextField(20);

        final DoubleJSlider slider = new DoubleJSlider(0, 100, 0, 100);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                try {

                    text.setText(df.format(slider.getScaledValue()));
                    Occlusion = text.getText();
                    if (!slider.getValueIsAdjusting() && !Occlusion.equalsIgnoreCase(lastOcclusion)) {
                        lastOcclusion = Occlusion;
                        try {
                            BufferedReader br = null;
                            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                            String oldtext = "";
                            String line = br.readLine();
                            String catchOld = "";
                            String catchNew = "";
                            while (line != null) {
                                if (line.contains("occlusion ")) {
                                    catchOld = line; //auxiliar line
                                    String aux = "";
                                    for (int i = catchOld.indexOf('=') + 1; i < catchOld.length() - 1; i++) {
                                        aux += catchOld.charAt(i);
                                    }
                                    catchOld = aux;
                                    if (!catchOld.equalsIgnoreCase(Occlusion)) {
                                        Occlusion = Occlusion.replace(",", ".");
                                        catchNew = Occlusion; //catchnewdigit
                                        line = "const float occlusion = " + catchNew + ';';
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
                /*
                if(!typed.matches("\\d+(\\.\\d*)?")) {
                    return;
                }*/
                double value = Double.parseDouble(typed) * slider.scale;
                slider.setValue((int) value);
                Occlusion = text.getText();
            }
        });
        float catchvalue;
        catchvalue = Float.parseFloat("" + getOcclusion()) * slider.scale;
        slider.setPaintTrack(true);
        slider.setPaintLabels(true);
        slider.setBounds(505, Yaxis, 200, 30);
        slider.setPaintTicks(true);
        slider.setValue((int) catchvalue);
        text.setBounds(711, Yaxis, 50, 20);

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
        ParallaxMapFrom = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        changeFile = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        ParallaxMapUVFlip = new javax.swing.JComboBox<>();
        AlbedoMapHelp = new javax.swing.JButton();
        AlbedoMapUVFlipHelp = new javax.swing.JButton();
        AlbedoMapFileHelp = new javax.swing.JButton();
        albedoHelp = new javax.swing.JButton();
        AlbedoMapLoopHelp = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        ParallaxMapType = new javax.swing.JComboBox<>();
        AlbedoMapHelp3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        ParallaxMapSwizzle = new javax.swing.JComboBox<>();
        AlbedoMapUVFlipHelp2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        OcclusionMapUVFlip = new javax.swing.JComboBox<>();
        AlbedoMapHelp1 = new javax.swing.JButton();
        AlbedoMapUVFlipHelp3 = new javax.swing.JButton();
        AlbedoMapFileHelp1 = new javax.swing.JButton();
        albedoHelp1 = new javax.swing.JButton();
        AlbedoMapLoopHelp1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        OcclusionMapFrom = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        changeFile1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        OcclusionMapSwizzle = new javax.swing.JComboBox<>();
        AlbedoMapUVFlipHelp4 = new javax.swing.JButton();
        AlbedoMapUVFlipHelp5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        OcclusionMapType = new javax.swing.JComboBox<>();
        AlbedoMapHelp4 = new javax.swing.JButton();
        OcclusionMapApplyScale = new javax.swing.JComboBox<>();
        ParallaxMapFile = new javax.swing.JTextField();
        OcclusionMapFile = new javax.swing.JTextField();
        back1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Edit Parallax and Occlusion");
        setAutoRequestFocus(false);
        setResizable(false);
        setSize(new java.awt.Dimension(960, 540));

        jLabel1.setText("<html><b>PARALLAX MAP FROM</b></html>");

        ParallaxMapFrom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8" }));
        try{
            ParallaxMapFrom.setSelectedIndex(getParallaxMapFrom());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        ParallaxMapFrom.setToolTipText("");
        ParallaxMapFrom.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ParallaxMapFromItemStateChanged(evt);
            }
        });
        ParallaxMapFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ParallaxMapFromActionPerformed(evt);
            }
        });

        jLabel2.setText("<html><b>PARALLAX MAP UV FLIP</b></html>");

        jLabel6.setText("<html><b>PARALLAX MAP FILE</b></html>");

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

        jLabel8.setText("<html><b>PARALLAX MAP SCALE</b></html>");

        jLabel9.setText("<html><b>PARALLAX MAP LOOP</b></html>");

        ParallaxMapUVFlip.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        try{
            ParallaxMapUVFlip.setSelectedIndex(getParallaxMapUVFlip());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        ParallaxMapUVFlip.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ParallaxMapUVFlipItemStateChanged(evt);
            }
        });
        ParallaxMapUVFlip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ParallaxMapUVFlipActionPerformed(evt);
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

        jLabel3.setText("<html><b>PARALLAX MAP TYPE</b></html>");

        ParallaxMapType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1" }));
        try{
            ParallaxMapType.setSelectedIndex(getParallaxMapType());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        ParallaxMapType.setToolTipText("");
        ParallaxMapType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ParallaxMapTypeItemStateChanged(evt);
            }
        });
        ParallaxMapType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ParallaxMapTypeActionPerformed(evt);
            }
        });

        AlbedoMapHelp3.setText("Help");
        AlbedoMapHelp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapHelp3ActionPerformed(evt);
            }
        });

        jLabel4.setText("<html><b>PARALLAX MAP SWIZZLE</b></html>");

        ParallaxMapSwizzle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        try{
            ParallaxMapSwizzle.setSelectedIndex(getParallaxMapSwizzle());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        ParallaxMapSwizzle.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ParallaxMapSwizzleItemStateChanged(evt);
            }
        });
        ParallaxMapSwizzle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ParallaxMapSwizzleActionPerformed(evt);
            }
        });

        AlbedoMapUVFlipHelp2.setText("Help");
        AlbedoMapUVFlipHelp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapUVFlipHelp2ActionPerformed(evt);
            }
        });

        jLabel10.setText("<html><b>OCCLUSION MAP SCALE</b></html>");

        jLabel11.setText("<html><b>OCCLUSION MAP LOOP</b></html>");

        OcclusionMapUVFlip.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        try{
            OcclusionMapUVFlip.setSelectedIndex(getOcclusionMapUVFlip());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        OcclusionMapUVFlip.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                OcclusionMapUVFlipItemStateChanged(evt);
            }
        });
        OcclusionMapUVFlip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OcclusionMapUVFlipActionPerformed(evt);
            }
        });

        AlbedoMapHelp1.setText("Help");
        AlbedoMapHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapHelp1ActionPerformed(evt);
            }
        });

        AlbedoMapUVFlipHelp3.setText("Help");
        AlbedoMapUVFlipHelp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapUVFlipHelp3ActionPerformed(evt);
            }
        });

        AlbedoMapFileHelp1.setText("Help");
        AlbedoMapFileHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapFileHelp1ActionPerformed(evt);
            }
        });

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

        jLabel12.setText("<html><b>OCCLUSION MAP FROM</b></html>");

        OcclusionMapFrom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8" }));
        try{
            OcclusionMapFrom.setSelectedIndex(getOcclusionMapFrom());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        OcclusionMapFrom.setToolTipText("");
        OcclusionMapFrom.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                OcclusionMapFromItemStateChanged(evt);
            }
        });
        OcclusionMapFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OcclusionMapFromActionPerformed(evt);
            }
        });

        jLabel13.setText("<html><b>OCCLUSION MAP UV FLIP</b></html>");

        jLabel14.setText("<html><b>OCCLUSION MAP FILE</b></html>");

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

        jLabel15.setText("<html><b>OCCLUSION MAP SWIZZLE</b></html>");

        jLabel16.setText("<html><b>OCCLUSION MAP APPLY SCALE</b></html>");

        OcclusionMapSwizzle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        try{
            OcclusionMapSwizzle.setSelectedIndex(getOcclusionMapSwizzle());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        OcclusionMapSwizzle.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                OcclusionMapSwizzleItemStateChanged(evt);
            }
        });
        OcclusionMapSwizzle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OcclusionMapSwizzleActionPerformed(evt);
            }
        });

        AlbedoMapUVFlipHelp4.setText("Help");
        AlbedoMapUVFlipHelp4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapUVFlipHelp4ActionPerformed(evt);
            }
        });

        AlbedoMapUVFlipHelp5.setText("Help");
        AlbedoMapUVFlipHelp5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapUVFlipHelp5ActionPerformed(evt);
            }
        });

        jLabel7.setText("<html><b>OCCLUSION MAP TYPE</b></html>");

        OcclusionMapType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1" }));
        try{
            OcclusionMapType.setSelectedIndex(getOcclusionMapType());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        OcclusionMapType.setToolTipText("");
        OcclusionMapType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                OcclusionMapTypeItemStateChanged(evt);
            }
        });
        OcclusionMapType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OcclusionMapTypeActionPerformed(evt);
            }
        });

        AlbedoMapHelp4.setText("Help");
        AlbedoMapHelp4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapHelp4ActionPerformed(evt);
            }
        });

        OcclusionMapApplyScale.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1" }));
        try{
            OcclusionMapApplyScale.setSelectedIndex(getOcclusionMapApplyScale());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        OcclusionMapApplyScale.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                OcclusionMapApplyScaleItemStateChanged(evt);
            }
        });
        OcclusionMapApplyScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OcclusionMapApplyScaleActionPerformed(evt);
            }
        });

        try{
            ParallaxMapFile.setEditable(false);
            ParallaxMapFile.setText(getParallaxMapFile());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }

        try{
            OcclusionMapFile.setEditable(false);
            OcclusionMapFile.setText(getOcclusionMapFile());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }

        back1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bg/back.png"))); // NOI18N
        back1.setBorder(null);
        back1.setContentAreaFilled(false);
        back1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bg/tip.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setDefaultCapable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Preview");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Preview");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(albedoHelp)
                                .addGap(252, 252, 252))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AlbedoMapLoopHelp)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 298, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(256, 256, 256)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(ParallaxMapSwizzle, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ParallaxMapUVFlip, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ParallaxMapType, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ParallaxMapFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(ParallaxMapFile, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(changeFile, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton3)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AlbedoMapFileHelp)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(AlbedoMapHelp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(AlbedoMapUVFlipHelp)
                                        .addComponent(AlbedoMapHelp3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(AlbedoMapUVFlipHelp2)))))
                        .addGap(60, 60, 60)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(changeFile1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AlbedoMapFileHelp1))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(95, 95, 95)
                                                    .addComponent(OcclusionMapFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(OcclusionMapType, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addComponent(OcclusionMapUVFlip, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(OcclusionMapSwizzle, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(OcclusionMapApplyScale, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(AlbedoMapHelp1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(AlbedoMapUVFlipHelp3)
                                    .addComponent(AlbedoMapUVFlipHelp4)
                                    .addComponent(AlbedoMapUVFlipHelp5)
                                    .addComponent(AlbedoMapHelp4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(54, 54, 54))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(OcclusionMapFile, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(albedoHelp1)
                                    .addGap(1, 1, 1))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(AlbedoMapLoopHelp1))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(back1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ParallaxMapFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(AlbedoMapHelp)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(OcclusionMapFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(AlbedoMapHelp1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ParallaxMapType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapHelp3)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OcclusionMapType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapHelp4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ParallaxMapUVFlip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AlbedoMapUVFlipHelp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ParallaxMapSwizzle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AlbedoMapUVFlipHelp2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(changeFile)
                            .addComponent(AlbedoMapFileHelp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ParallaxMapFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(OcclusionMapUVFlip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AlbedoMapUVFlipHelp3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(OcclusionMapSwizzle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AlbedoMapUVFlipHelp5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AlbedoMapUVFlipHelp4)
                            .addComponent(OcclusionMapApplyScale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(changeFile1)
                            .addComponent(AlbedoMapFileHelp1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(OcclusionMapFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(albedoHelp))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AlbedoMapLoopHelp)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(albedoHelp1))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AlbedoMapLoopHelp1))))
                .addContainerGap(77, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ParallaxMapFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ParallaxMapFromActionPerformed

    }//GEN-LAST:event_ParallaxMapFromActionPerformed

    private void ParallaxMapUVFlipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ParallaxMapUVFlipActionPerformed

    }//GEN-LAST:event_ParallaxMapUVFlipActionPerformed

    private void changeFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeFileActionPerformed

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new java.io.File("../../Materials"));//The directory we are looking for is the Materials folder from ray
        fileChooser.setDialogTitle("Choose new Parallax Map File");
        //Filtering by ImageFiles
        FileFilter imageFilter = new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp", "tga", "targa", "dds", "tif", "tiff", "jpeg", "pcd");
        fileChooser.setFileFilter(imageFilter);

        Component areaTexto = null;

        try {
            int selection = fileChooser.showOpenDialog(areaTexto);
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            filePath = filePath.replace("\\", "/");

            if (selection == 0) {
                String relative = toRelative.convertToRelativePath(foo.getFileToEdit().getParent(), filePath);
                ParallaxMapFile.setText(relative);
                if (ParallaxMapFrom.getSelectedIndex() != 1) {
                    ParallaxMapFrom.setSelectedIndex(1);
                }
                try {
                    BufferedReader br = null;
                    br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                    String oldtext = "";
                    String line = br.readLine();
                    String catchOld = "";
                    String catchNew = "";
                    while (line != null) {
                        if (line.contains("#define PARALLAX_MAP_FILE")) {
                            catchOld = line; //auxiliar line
                            String aux = "";
                            for (int i = catchOld.indexOf('"') + 1; i < line.length() - 1; i++) {
                                aux += catchOld.charAt(i);
                            }
                            catchOld = aux;
                            if (!catchOld.equalsIgnoreCase(ParallaxMapFile.getText())) {
                                catchNew = ParallaxMapFile.getText(); //catchnewdigit
                                line = "#define PARALLAX_MAP_FILE " + '"' + catchNew + '"';
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
        AlbedoMapHelpText.setText("<HTML><div><pre style='font-family: Arial;'><center><b>In the DX9, the parallax map does not work with vertex displacement</b><br><br><br>"
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
                + "    <li>8 : Params fetch from Parallax Color from the pmx.</li><br>"
                + "    <li><strike>9 : Params fetch from Parallax Power from the pmx. (this option can only be used for parallax)</strike>, doesn't work on Parallax</li></ul></pre><br><br></HTML>");
        AlbedoMapHelp.setLayout(new BorderLayout());
        AlbedoMapHelp.setSize(700, 550);
        AlbedoMapHelp.setLocationRelativeTo(this);
        AlbedoMapHelp.setResizable(true);
        AlbedoMapHelp.setVisible(true);
        AlbedoMapHelp.add(AlbedoMapHelpText);
        AlbedoMapHelp.setTitle("Parallax Map From Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            AlbedoMapHelp.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
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
        help.setTitle("Parallax Map UV Flip Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
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
        help.setTitle("Parallax Map File Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }//GEN-LAST:event_AlbedoMapFileHelpActionPerformed

    private void albedoHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albedoHelpActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");

        helptext.setText("<html>between 0 ~ inf</html>");
        help.setLayout(new BorderLayout());
        help.setSize(300, 70);
        help.setLocationRelativeTo(this);
        help.setResizable(true);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Parallax Map Scale Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }//GEN-LAST:event_albedoHelpActionPerformed

    private void AlbedoMapLoopHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapLoopHelpActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");

        helptext.setText("<HTML>Why increase number of parallaxMapLoopNum will increase the loops/tile/number of albedo, normals, etc<br>"
                + "Bacause parallax coordinates can be calculated from height map <br>"
                + "That are then used to access textures with albedo, normals, smoothness, metalness, etc<br>"
                + "In other words like fetched data (albedo, normals, etc) from parallax coordinates * parallaxMapLoopNum * albedo/normal/MapLoopNum<br></HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(600, 200);
        help.setLocationRelativeTo(this);
        help.setResizable(true);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Parallax Map Loop Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }//GEN-LAST:event_AlbedoMapLoopHelpActionPerformed

    private void ParallaxMapTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ParallaxMapTypeActionPerformed

    }//GEN-LAST:event_ParallaxMapTypeActionPerformed

    private void AlbedoMapHelp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapHelp3ActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        helptext.setText("<html><b> Other parameter types for Parallax</b><br><br> "
                + "<ul><li><b>0</b> : calculate without transparency</li>"
                + "<li><b>1</b> : calculate parallax occlusion with transparency and best SSDO</li></ul></html>");
        help.setLayout(new BorderLayout());
        help.setSize(700, 250);
        help.setLocationRelativeTo(this);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));

        help.setResizable(true);
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Parallax Map Type Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }//GEN-LAST:event_AlbedoMapHelp3ActionPerformed

    private void ParallaxMapSwizzleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ParallaxMapSwizzleActionPerformed

    }//GEN-LAST:event_ParallaxMapSwizzleActionPerformed

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
        help.setTitle("Parallax Map Swizzle Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }//GEN-LAST:event_AlbedoMapUVFlipHelp2ActionPerformed

    private void OcclusionMapUVFlipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OcclusionMapUVFlipActionPerformed

    }//GEN-LAST:event_OcclusionMapUVFlipActionPerformed

    private void AlbedoMapHelp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapHelp1ActionPerformed
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
                + "    <li>8 : Params fetch from Parallax Color from the pmx.</li><br>"
                + "    <li><strike>9 : Params fetch from Parallax Power from the pmx. (this option can only be used for parallax)</strike>, doesn't work on Occlusion</li></ul></pre><br><br></HTML>");
        AlbedoMapHelp.setLayout(new BorderLayout());
        AlbedoMapHelp.setSize(700, 550);
        AlbedoMapHelp.setLocationRelativeTo(this);

        AlbedoMapHelp.setResizable(true);
        AlbedoMapHelp.setVisible(true);
        AlbedoMapHelp.add(AlbedoMapHelpText);
        AlbedoMapHelp.setTitle("Occlusion Map From Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            AlbedoMapHelp.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
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
        help.setTitle("Occlusion Map UV Flip Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
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
        help.setTitle("Occlusion Map File Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
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
        help.setTitle("Occlusion Map Scale Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
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
        help.setTitle("Occlusion Map Loop Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }//GEN-LAST:event_AlbedoMapLoopHelp1ActionPerformed

    private void OcclusionMapFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OcclusionMapFromActionPerformed

    }//GEN-LAST:event_OcclusionMapFromActionPerformed

    private void changeFile1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_changeFile1StateChanged

    }//GEN-LAST:event_changeFile1StateChanged

    private void changeFile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeFile1ActionPerformed

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new java.io.File("../../Materials"));//The directory we are looking for is the Materials folder from ray
        fileChooser.setDialogTitle("Choose new Occlusion Map File");
        //Filtering by ImageFiles
        FileFilter imageFilter = new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp", "tga", "targa", "dds", "tif", "tiff", "jpeg", "pcd");
        fileChooser.setFileFilter(imageFilter);

        Component areaTexto = null;

        try {
            int selection = fileChooser.showOpenDialog(areaTexto);
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            filePath = filePath.replace("\\", "/");

            if (selection == 0) {
                String relative = toRelative.convertToRelativePath(foo.getFileToEdit().getParent(), filePath);
                OcclusionMapFile.setText(relative);

                if (OcclusionMapFrom.getSelectedIndex() != 1) {
                    OcclusionMapFrom.setSelectedIndex(1);
                }
                try {
                    BufferedReader br = null;
                    br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                    String oldtext = "";
                    String line = br.readLine();
                    String catchOld = "";
                    String catchNew = "";
                    while (line != null) {
                        if (line.contains("#define OCCLUSION_MAP_FILE")) {
                            catchOld = line; //auxiliar line
                            String aux = "";
                            for (int i = catchOld.indexOf('"') + 1; i < line.length() - 1; i++) {
                                aux += catchOld.charAt(i);
                            }
                            catchOld = aux;
                            if (!catchOld.equalsIgnoreCase(OcclusionMapFile.getText())) {
                                catchNew = OcclusionMapFile.getText(); //catchnewdigit
                                line = "#define OCCLUSION_MAP_FILE " + '"' + catchNew + '"';
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

    private void OcclusionMapSwizzleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OcclusionMapSwizzleActionPerformed

    }//GEN-LAST:event_OcclusionMapSwizzleActionPerformed

    private void AlbedoMapUVFlipHelp4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapUVFlipHelp4ActionPerformed
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
        help.setTitle("Occlusion Map Apply Scale Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
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
        help.setTitle("Occlusion Map Swizzle Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }//GEN-LAST:event_AlbedoMapUVFlipHelp5ActionPerformed

    private void OcclusionMapTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OcclusionMapTypeActionPerformed

    }//GEN-LAST:event_OcclusionMapTypeActionPerformed

    private void AlbedoMapHelp4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapHelp4ActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");

        helptext.setText("<html>Other parameter types for occlusion<br><br>"
                + "<ul>"
                + "<li>0 : Fetch ambient occlusion from linear color-space</li>"
                + "<li>1 : Fetch ambient occlusion from sRGB   color-space</li></ul></html>");
        help.setLayout(new BorderLayout());
        help.setSize(700, 250);
        help.setLocationRelativeTo(this);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));

        help.setResizable(true);
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Occlusion Map Type Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }//GEN-LAST:event_AlbedoMapHelp4ActionPerformed

    private void OcclusionMapApplyScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OcclusionMapApplyScaleActionPerformed

    }//GEN-LAST:event_OcclusionMapApplyScaleActionPerformed
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
        WindowFrame w = new WindowFrame();
        w.setLocation(this.getLocation());
        ErrorWindow.dispose();
        this.dispose();
        w.setSize(960, 549);
        w.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        w.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                callMenu();
                w.dispose();
            }
        });
        w.setAlwaysOnTop(true);
        w.setAlwaysOnTop(false);
        w.setResizable(false);
        w.setLayout(new BorderLayout());

        ImageIcon img = new ImageIcon(getClass().getResource("/icon/ico.png"));
        w.setIconImage(img.getImage());
        w.setVisible(true);
    }//GEN-LAST:event_back1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        helptext.setText("<html><b>Parallax Tips :</b> It has no effect on metalness > 0 and CUSTOM_ENABLE > 0.<br><br>"
                + "The parallax maps are not HDR/environment maps, only modifies the color of basic reflection of the model<br>"
                + "Used to change the colors of the environment reflect.<br>"
                + "When the diffuse brighter than parallax reflect,<br>"
                + "It will be only a very small contribution to change the colors of environment reflect.<br>"
                + "So you can set the zero to the 'const float3 parallax = 0.0;', if you dot‘t want that model to reflect the parallax color of environment.<br><br><br>"
                + ""
                + "<b>Occlusion Tips :</b> The ambient occlusion (AO) is an effect that approximates the attenuation of environment light due to occlusion.<br>"
                + "Bacause sky lighting from many directions, cannot simply to calculating shadows in the real-time.<br>"
                + "A simply way able to replaced by using occlusion map and SSAO.<br>"
                + "So you can set the zero to the 'const float occlusion = 0.0;', if you dot‘t want that model to calculating the diffuse & specular color of environment.<br></html>");
        help.setLayout(new BorderLayout());
        help.setSize(700, 250);
        help.setLocationRelativeTo(this);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setResizable(true);
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Parallax & Occlusion Map Tips");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ParallaxMapFromItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ParallaxMapFromItemStateChanged
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define PARALLAX_MAP_FROM")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = ParallaxMapFrom.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_ParallaxMapFromItemStateChanged

    private void ParallaxMapTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ParallaxMapTypeItemStateChanged
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define PARALLAX_MAP_TYPE")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = ParallaxMapType.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_ParallaxMapTypeItemStateChanged

    private void ParallaxMapUVFlipItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ParallaxMapUVFlipItemStateChanged
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define PARALLAX_MAP_UV_FLIP")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = ParallaxMapUVFlip.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_ParallaxMapUVFlipItemStateChanged

    private void ParallaxMapSwizzleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ParallaxMapSwizzleItemStateChanged
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define PARALLAX_MAP_SWIZZLE")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = ParallaxMapSwizzle.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_ParallaxMapSwizzleItemStateChanged

    private void OcclusionMapFromItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_OcclusionMapFromItemStateChanged
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define OCCLUSION_MAP_FROM")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = OcclusionMapFrom.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_OcclusionMapFromItemStateChanged

    private void OcclusionMapTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_OcclusionMapTypeItemStateChanged
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define OCCLUSION_MAP_TYPE")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = OcclusionMapType.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_OcclusionMapTypeItemStateChanged

    private void OcclusionMapUVFlipItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_OcclusionMapUVFlipItemStateChanged
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define OCCLUSION_MAP_UV_FLIP")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = OcclusionMapUVFlip.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_OcclusionMapUVFlipItemStateChanged

    private void OcclusionMapSwizzleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_OcclusionMapSwizzleItemStateChanged
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define OCCLUSION_MAP_SWIZZLE")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = OcclusionMapSwizzle.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_OcclusionMapSwizzleItemStateChanged

    private void OcclusionMapApplyScaleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_OcclusionMapApplyScaleItemStateChanged
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define OCCLUSION_MAP_APPLY_SCALE")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = OcclusionMapApplyScale.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_OcclusionMapApplyScaleItemStateChanged
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
        previewImg(ParallaxMapFile.getText());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        previewImg(OcclusionMapFile.getText());
    }//GEN-LAST:event_jButton4ActionPerformed

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
    private javax.swing.JButton AlbedoMapUVFlipHelp2;
    private javax.swing.JButton AlbedoMapUVFlipHelp3;
    private javax.swing.JButton AlbedoMapUVFlipHelp4;
    private javax.swing.JButton AlbedoMapUVFlipHelp5;
    private javax.swing.JComboBox<String> OcclusionMapApplyScale;
    private javax.swing.JTextField OcclusionMapFile;
    private javax.swing.JComboBox<String> OcclusionMapFrom;
    private javax.swing.JComboBox<String> OcclusionMapSwizzle;
    private javax.swing.JComboBox<String> OcclusionMapType;
    private javax.swing.JComboBox<String> OcclusionMapUVFlip;
    private javax.swing.JTextField ParallaxMapFile;
    private javax.swing.JComboBox<String> ParallaxMapFrom;
    private javax.swing.JComboBox<String> ParallaxMapSwizzle;
    private javax.swing.JComboBox<String> ParallaxMapType;
    private javax.swing.JComboBox<String> ParallaxMapUVFlip;
    private javax.swing.JButton albedoHelp;
    private javax.swing.JButton albedoHelp1;
    private javax.swing.JButton back1;
    private javax.swing.JButton changeFile;
    private javax.swing.JButton changeFile1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    // End of variables declaration//GEN-END:variables
}
