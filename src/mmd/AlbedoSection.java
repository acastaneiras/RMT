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

public class AlbedoSection extends javax.swing.JFrame {

    public static MaterialMakerv2 foo = new MaterialMakerv2();
    public static WindowFrame wf = new WindowFrame();

    String AlbedoLoop = String.valueOf(getalbedoMapLoop());
    String AlbedoSubLoop = String.valueOf(getalbedoSubMapLoop());
    String AlbedoMapFile1;
    ////////////////////////////////////////////////////////////////////
    String lastAlbedoLoop = String.valueOf(getalbedoMapLoop());
    String lastAlbedoSubLoop = String.valueOf(getalbedoSubMapLoop());

    private static String CatchAlbedoSubMapFile;
    private static int CatchAlbedoMapFrom;
    private static int CatchAlbedoSubEnable;
    private static int CatchAlbedoSubMapFrom;
    private static int CatchAlbedoSubMapUVFlip;
    private static int CatchAlbedoSubMapApplyScale;
    private static int CatchAlbedoMapUVFlip;
    private static int CatchAlbedoApplyScale;
    private static int CatchAlbedoApplyDiffuse;
    private static int CatchAlbedoApplyMorphColor;
    private static String CatchAlbedoMapFile = null;
    private static String Catchalbedo;
    private static String CatchalbedoSub;
    private static float CatchalbedoMapLoop;
    private static float CatchalbedoSubMapLoop;
    public int errors = 0;
    public String albedoMap;
    JFrame ErrorWindow = new JFrame();

    public void SomethingWentWrong() {
        if (errors == 1) {

            JLabel ErrorWindowText = new JLabel();
            //ErrorWindowText.
            ErrorWindowText.setText("<HTML><div style='padding-left:30px;'>Something went wrong while trying to load <i>Albedo Section</i>...<br><br>"
                    + "Please make sure the file you are trying to open doesn't <b>exceed the limit for each parameter</b>, usually this happens when you are trying to open "
                    + "a .fx file where some of it's parameters has <b>higher values</b> than supposed to be<br><br>"
                    + "<b>Limits: </b><br>"
                    + "<ul><li>AlbedoMapFrom: 0 - 8</li>"
                    + "<li>AlbedoMapUVFlip: 0 - 3</li>"
                    + "<li>AlbedoMapApplyScale: 0 - 2</li>"
                    + "<li>AlbedoMapApplyDiffuse: 0 - 2</li>"
                    + "<li>AlbedoMapApplyMorphColor: 0 - 1</li>"
                    + "<br>"
                    + "</ul>Same for AlbedoSub</div></HTML>");
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

    public int getAlbedoSubEnable() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());

            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define ALBEDO_SUB_ENABLE")) {

                    CatchAlbedoSubEnable = Integer.parseInt(s.replaceAll("[\\D]", ""));

                }
                s = AlbedotoEdit_Br.readLine();
            }

        } catch (Exception ex) {
            SomethingWentWrong();
        } finally {
            try {
                AlbedotoEdit_Br.close();
            } catch (IOException ex) {
                SomethingWentWrong();
            }
        }
        return CatchAlbedoSubEnable;
    }

    public int getAlbedoMapFrom() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());

            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define ALBEDO_MAP_FROM")) {

                    CatchAlbedoMapFrom = Integer.parseInt(s.replaceAll("[\\D]", ""));

                }
                s = AlbedotoEdit_Br.readLine();
            }

        } catch (Exception e) {
            SomethingWentWrong();

        } finally {
            try {
                AlbedotoEdit_Br.close();
            } catch (IOException ex) {
            }
        }
        return CatchAlbedoMapFrom;
    }

    public int getAlbedoSubMapUVFlip() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());

            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define ALBEDO_SUB_MAP_UV_FLIP")) {

                    CatchAlbedoSubMapUVFlip = Integer.parseInt(s.replaceAll("[\\D]", ""));

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
        return CatchAlbedoSubMapUVFlip;
    }

    public int getAlbedoSubMapFrom() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());

            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define ALBEDO_SUB_MAP_FROM")) {

                    CatchAlbedoSubMapFrom = Integer.parseInt(s.replaceAll("[\\D]", ""));

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
        return CatchAlbedoSubMapFrom;
    }

    public int getAlbedoSubMapApplyScale() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());

            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define ALBEDO_SUB_MAP_APPLY_SCALE")) {

                    CatchAlbedoSubMapApplyScale = Integer.parseInt(s.replaceAll("[\\D]", ""));

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
        return CatchAlbedoSubMapApplyScale;
    }

    public String getAlbedoSubMapFile() {
        BufferedReader AlbedotoEdit_Br = null;
        String str = "";

        try {

            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define ALBEDO_SUB_MAP_FILE")) {
                    CatchAlbedoSubMapFile = "";
                    for (int i = s.indexOf('"') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';') {

                        } else {
                            CatchAlbedoSubMapFile += s.charAt(i);
                        }
                    }

                    CatchAlbedoSubMapFile = CatchAlbedoSubMapFile.replace("null", "");

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
        return CatchAlbedoSubMapFile;
    }

    public String getalbedoSub() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("float3 albedoSub =")) {
                    String txt = "";
                    for (int i = s.indexOf('=') + 1; i < s.length() - 1; i++) {
                        txt += s.charAt(i);
                    }
                    CatchalbedoSub = (txt);
                    CatchalbedoSub = CatchalbedoSub.replaceAll(";", "");
                    CatchalbedoSub = CatchalbedoSub.replaceAll(" ", "");
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
        return CatchalbedoSub;
    }

    public float getalbedoSubMapLoop() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("float2 albedoSubMapLoopNum =")) {
                    String txt = "";
                    for (int i = s.indexOf('=') + 1; i < s.length() - 1; i++) {
                        txt += s.charAt(i);
                    }
                    txt = txt.replaceAll(";", "");
                    CatchalbedoSubMapLoop = Float.parseFloat(txt);

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
        return CatchalbedoSubMapLoop;
    }

    public int getAlbedoMapUVFlip() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define ALBEDO_MAP_UV_FLIP")) {
                    CatchAlbedoMapUVFlip = Integer.parseInt(s.replaceAll("[\\D]", ""));

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

        return CatchAlbedoMapUVFlip;
    }

    public int getAlbedoApplyScale() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define ALBEDO_MAP_APPLY_SCALE")) {
                    CatchAlbedoApplyScale = Integer.parseInt(s.replaceAll("[\\D]", ""));

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
        return CatchAlbedoApplyScale;
    }

    public int getAlbedoApplyDiffuse() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define ALBEDO_MAP_APPLY_DIFFUSE")) {
                    CatchAlbedoApplyDiffuse = Integer.parseInt(s.replaceAll("[\\D]", ""));

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
        return CatchAlbedoApplyDiffuse;
    }

    public int getAlbedoApplyMorphColor() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define ALBEDO_MAP_APPLY_MORPH_COLOR")) {
                    CatchAlbedoApplyMorphColor = Integer.parseInt(s.replaceAll("[\\D]", ""));

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
        return CatchAlbedoApplyMorphColor;
    }

    public String getAlbedoMapFile() {
        BufferedReader AlbedotoEdit_Br = null;

        try {

            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define ALBEDO_MAP_FILE")) {
                    CatchAlbedoMapFile = "";
                    for (int i = s.indexOf('"') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';') {

                        } else {
                            CatchAlbedoMapFile += s.charAt(i);
                        }

                    }

                    CatchAlbedoMapFile = CatchAlbedoMapFile.replace("null", "");

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
        return CatchAlbedoMapFile;
    }

    public String getalbedo() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("const float3 albedo =")) {
                    String txt = "";
                    for (int i = s.indexOf('=') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';') {

                        } else {
                            txt += s.charAt(i);
                        }
                    }
                    Catchalbedo = (txt);
                    Catchalbedo = Catchalbedo.replaceAll(";", "");

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
        return Catchalbedo;
    }

    public float getalbedoMapLoop() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("const float2 albedoMapLoopNum =")) {
                    String txt = "";
                    for (int i = s.indexOf('=') + 1; i < s.length() - 1; i++) {
                        txt += s.charAt(i);
                    }
                    txt = txt.replaceAll(";", "");
                    CatchalbedoMapLoop = Float.parseFloat(txt);

                }
                s = AlbedotoEdit_Br.readLine();
            }

        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        } finally {
            try {
                AlbedotoEdit_Br.close();
            } catch (Exception e) {

            }
        }
        return CatchalbedoMapLoop;
    }

    /**
     * Creates new form WindowFrame
     */
    public AlbedoSection() {
        this.AlbedoMapFile1 = getAlbedoMapFile();

        initComponents();//Generated by the GUI mostly

        myInitComponents();

        myInitComponents2();

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
                    AlbedoLoop = text.getText();
                    if (!slider.getValueIsAdjusting() && !AlbedoLoop.equalsIgnoreCase(lastAlbedoLoop)) {
                        lastAlbedoLoop = AlbedoLoop;
                        BufferedReader br = null;

                        br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                        String oldtext = "";
                        String line = br.readLine();
                        while (line != null) {
                            if (line.contains("albedoMapLoopNum")) {
                                AlbedoLoop = AlbedoLoop.replace(",", ".");
                                line = "const float2 albedoMapLoopNum = " + AlbedoLoop + ";";
                            }
                            oldtext += line + "\r\n";
                            line = br.readLine();
                        }
                        String newtext = oldtext;

                        FileWriter writer = new FileWriter(foo.getFileToEdit());
                        writer.write(newtext);
                        writer.close();

                        br.close();
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
                AlbedoLoop = text.getText();
            }
        });
        float catchvalue;

        catchvalue = Float.parseFloat("" + getalbedoMapLoop()) * slider.scale;

        slider.setPaintTrack(true);
        slider.setVisible(true);
        slider.setPaintLabels(true);
        slider.setBounds(47, 392, 200, 30);
        slider.setPaintTicks(true);
        slider.setValue((int) catchvalue);
        text.setBounds(255, 392, 50, 26);
        text.setFont(new Font("Arial", Font.PLAIN, 11));

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
                    AlbedoSubLoop = text.getText();
                    if (!slider.getValueIsAdjusting() && !AlbedoSubLoop.equalsIgnoreCase(lastAlbedoSubLoop)) {
                        lastAlbedoSubLoop = AlbedoSubLoop;
                        BufferedReader br = null;
                        br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                        String oldtext = "";
                        String line = br.readLine();

                        while (line != null) {
                            if (line.contains("albedoSubMapLoopNum")) {
                                AlbedoSubLoop = AlbedoSubLoop.replace(",", ".");
                                line = "const float2 albedoSubMapLoopNum = " + AlbedoSubLoop + ";";
                            }
                            oldtext += line + "\r\n";
                            line = br.readLine();
                        }
                        String newtext = oldtext;

                        FileWriter writer = new FileWriter(foo.getFileToEdit());
                        writer.write(newtext);
                        writer.close();

                        br.close();
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
                AlbedoSubLoop = text.getText();
            }
        });
        float catchvalue;
        catchvalue = Float.parseFloat("" + getalbedoSubMapLoop()) * slider.scale;
        slider.setPaintTrack(true);
        slider.setPaintLabels(true);
        slider.setBounds(535, 392, 200, 30);
        slider.setPaintTicks(true);
        slider.setValue((int) catchvalue);
        text.setBounds(741, 392, 50, 26);
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
        AlbedoMapFrom = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        changeFile = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        albedo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        AlbedoMapUVFlip = new javax.swing.JComboBox<>();
        AlbedoApplyScale = new javax.swing.JComboBox<>();
        AlbedoApplyDiffuse = new javax.swing.JComboBox<>();
        AlbedoApplyMorphColor = new javax.swing.JComboBox<>();
        back = new javax.swing.JButton();
        AlbedoMapHelp = new javax.swing.JButton();
        AlbedoMapUVFlipHelp = new javax.swing.JButton();
        AlbedoMapApplyScaleHelp = new javax.swing.JButton();
        AlbedoApplyDiffuseHelp = new javax.swing.JButton();
        AlbedoMorphColorHelp = new javax.swing.JButton();
        AlbedoMapFileHelp = new javax.swing.JButton();
        albedoColorPick = new javax.swing.JButton();
        AlbedoMapLoopHelp = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        AlbedoSubMapFrom = new javax.swing.JComboBox<>();
        AlbedoSubEnable = new javax.swing.JComboBox<>();
        AlbedoSubMapApplyScale = new javax.swing.JComboBox<>();
        AlbedoSubMapUVFlip1 = new javax.swing.JComboBox<>();
        changeFileSub = new javax.swing.JButton();
        albedoSub = new javax.swing.JTextField();
        AlbedoMapHelp1 = new javax.swing.JButton();
        AlbedoMapUVFlipHelp1 = new javax.swing.JButton();
        AlbedoMapApplyScaleHelp1 = new javax.swing.JButton();
        AlbedoMapFileHelp1 = new javax.swing.JButton();
        subHelp = new javax.swing.JButton();
        AlbedoMapLoopHelp1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        AlbedoMapFile = new javax.swing.JTextField();
        AlbedoSubMapFile = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        albedoSubColorPick = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Edit Albedo of "+foo.getFileToEdit().getName());
        setAutoRequestFocus(false);
        setResizable(false);
        setSize(new java.awt.Dimension(960, 549));

        jLabel1.setText("<html><b>ALBEDO MAP FROM </b></html>");

        AlbedoMapFrom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8" }));
        try{
            AlbedoMapFrom.setSelectedIndex(getAlbedoMapFrom());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        AlbedoMapFrom.setToolTipText("");
        AlbedoMapFrom.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                AlbedoMapFromItemStateChanged(evt);
            }
        });
        AlbedoMapFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapFromActionPerformed(evt);
            }
        });

        jLabel2.setText("<html><b>ALBEDO MAP UV FLIP</b></html>");

        jLabel3.setText("<html><b>ALBEDO MAP APPLY SCALE</b></html>");

        jLabel4.setText("<html><b>ALBEDO MAP APPLY DIFFUSE</b></html>");

        jLabel5.setText("<html><b>ALBEDO MAP APPLY MORPH COLOR</b></html>");

        jLabel6.setText("<html><b>ALBEDO MAP FILE</b></html>");

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

        jLabel8.setText("<html><b>ALBEDO</b></html>");

        try{
            albedo.setText(String.valueOf(getalbedo())
            );
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        albedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albedoActionPerformed(evt);
            }
        });
        albedo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                albedoPropertyChange(evt);
            }
        });
        albedo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                albedoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                albedoKeyTyped(evt);
            }
        });

        jLabel9.setText("<html><b>ALBEDO MAP LOOP</b></html>");

        AlbedoMapUVFlip.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        try{
            AlbedoMapUVFlip.setSelectedIndex(getAlbedoMapUVFlip());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        AlbedoMapUVFlip.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                AlbedoMapUVFlipItemStateChanged(evt);
            }
        });
        AlbedoMapUVFlip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapUVFlipActionPerformed(evt);
            }
        });

        AlbedoApplyScale.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2" }));
        try{
            AlbedoApplyScale.setSelectedIndex(getAlbedoApplyScale());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        AlbedoApplyScale.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                AlbedoApplyScaleItemStateChanged(evt);
            }
        });
        AlbedoApplyScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoApplyScaleActionPerformed(evt);
            }
        });

        AlbedoApplyDiffuse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2" }));
        try{
            AlbedoApplyDiffuse.setSelectedIndex(getAlbedoApplyDiffuse());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        AlbedoApplyDiffuse.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                AlbedoApplyDiffuseItemStateChanged(evt);
            }
        });
        AlbedoApplyDiffuse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoApplyDiffuseActionPerformed(evt);
            }
        });

        AlbedoApplyMorphColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1" }));
        try{
            AlbedoApplyMorphColor.setSelectedIndex(getAlbedoApplyMorphColor());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        AlbedoApplyMorphColor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                AlbedoApplyMorphColorItemStateChanged(evt);
            }
        });
        AlbedoApplyMorphColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoApplyMorphColorActionPerformed(evt);
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

        AlbedoApplyDiffuseHelp.setText("Help");
        AlbedoApplyDiffuseHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoApplyDiffuseHelpActionPerformed(evt);
            }
        });

        AlbedoMorphColorHelp.setText("Help");
        AlbedoMorphColorHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMorphColorHelpActionPerformed(evt);
            }
        });

        AlbedoMapFileHelp.setText("Help");
        AlbedoMapFileHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapFileHelpActionPerformed(evt);
            }
        });

        albedoColorPick.setText("Help");
        albedoColorPick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albedoColorPickActionPerformed(evt);
            }
        });

        AlbedoMapLoopHelp.setText("Help");
        AlbedoMapLoopHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapLoopHelpActionPerformed(evt);
            }
        });

        jLabel7.setText("<html><b>ALBEDO SUB ENABLE</b></html>");

        jLabel10.setText("<html><b>ALBEDO SUB MAP FROM</b></html>");

        jLabel11.setText("<html><b>ALBEDO SUB MAP UV FLIP</b></html>");

        jLabel12.setText("<html><b>ALBEDO SUB MAP APPLY SCALE</b></html>");

        jLabel13.setText("<html><b>ALBEDO SUB MAP FILE</b></html>");

        jLabel14.setText("<html><b>ALBEDO SUB</b></html>");

        jLabel15.setText("<html><b>ALBEDO SUB MAP LOOP</b></html>");

        AlbedoSubMapFrom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
        try{
            AlbedoSubMapFrom.setSelectedIndex(getAlbedoSubMapFrom());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        AlbedoSubMapFrom.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                AlbedoSubMapFromItemStateChanged(evt);
            }
        });

        AlbedoSubEnable.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5" }));
        try{
            AlbedoSubEnable.setSelectedIndex(getAlbedoSubEnable());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        AlbedoSubEnable.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                AlbedoSubEnableItemStateChanged(evt);
            }
        });

        AlbedoSubMapApplyScale.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        try{
            AlbedoSubMapApplyScale.setSelectedIndex(getAlbedoSubMapApplyScale());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        AlbedoSubMapApplyScale.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                AlbedoSubMapApplyScaleItemStateChanged(evt);
            }
        });

        AlbedoSubMapUVFlip1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        try{
            AlbedoSubMapUVFlip1.setSelectedIndex(getAlbedoSubMapUVFlip());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        AlbedoSubMapUVFlip1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                AlbedoSubMapUVFlip1ItemStateChanged(evt);
            }
        });

        changeFileSub.setText("...");
        changeFileSub.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                changeFileSubStateChanged(evt);
            }
        });
        changeFileSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeFileSubActionPerformed(evt);
            }
        });

        try{
            albedoSub.setText(String.valueOf(getalbedoSub())
            );
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        albedoSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albedoSubActionPerformed(evt);
            }
        });
        albedoSub.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                albedoSubKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                albedoSubKeyTyped(evt);
            }
        });

        AlbedoMapHelp1.setText("Help");
        AlbedoMapHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapHelp1ActionPerformed(evt);
            }
        });

        AlbedoMapUVFlipHelp1.setText("Help");
        AlbedoMapUVFlipHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapUVFlipHelp1ActionPerformed(evt);
            }
        });

        AlbedoMapApplyScaleHelp1.setText("Help");
        AlbedoMapApplyScaleHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapApplyScaleHelp1ActionPerformed(evt);
            }
        });

        AlbedoMapFileHelp1.setText("Help");
        AlbedoMapFileHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapFileHelp1ActionPerformed(evt);
            }
        });

        subHelp.setText("Help");
        subHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subHelpActionPerformed(evt);
            }
        });

        AlbedoMapLoopHelp1.setText("Help");
        AlbedoMapLoopHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapLoopHelp1ActionPerformed(evt);
            }
        });

        jButton1.setText("Help");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        try{
            AlbedoMapFile.setEditable(false);
            AlbedoMapFile.setText(AlbedoMapFile1);
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }

        try{
            AlbedoSubMapFile.setEditable(false);
            AlbedoSubMapFile.setText(getAlbedoSubMapFile());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }

        jButton2.setText("Pick Colour");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        albedoSubColorPick.setText("Pick Colour");
        albedoSubColorPick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albedoSubColorPickActionPerformed(evt);
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AlbedoMapFile, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(AlbedoApplyDiffuse, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(AlbedoApplyScale, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(128, 128, 128)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(AlbedoMapFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(AlbedoMapUVFlip, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(AlbedoApplyMorphColor, 0, 82, Short.MAX_VALUE)
                                            .addComponent(changeFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(AlbedoMapHelp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(AlbedoMapUVFlipHelp)
                                    .addComponent(AlbedoMapApplyScaleHelp)
                                    .addComponent(AlbedoApplyDiffuseHelp)
                                    .addComponent(AlbedoMorphColorHelp)
                                    .addComponent(AlbedoMapFileHelp)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(AlbedoMapLoopHelp))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(albedo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(albedoColorPick))
                            .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(86, 86, 86)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(AlbedoSubMapApplyScale, 0, 82, Short.MAX_VALUE)
                                    .addComponent(AlbedoSubEnable, 0, 82, Short.MAX_VALUE)
                                    .addComponent(AlbedoSubMapFrom, 0, 82, Short.MAX_VALUE)
                                    .addComponent(AlbedoSubMapUVFlip1, 0, 82, Short.MAX_VALUE)
                                    .addComponent(changeFileSub, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(AlbedoMapUVFlipHelp1)
                                            .addComponent(AlbedoMapApplyScaleHelp1)
                                            .addComponent(AlbedoMapFileHelp1))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(AlbedoMapHelp1)
                                            .addComponent(jButton1))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(albedoSub, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(albedoSubColorPick)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(subHelp))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(AlbedoMapLoopHelp1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(AlbedoSubMapFile, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton4)))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapHelp)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoSubEnable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapUVFlip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapUVFlipHelp)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoSubMapFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapHelp1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoApplyScale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapApplyScaleHelp)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoSubMapUVFlip1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapUVFlipHelp1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoApplyDiffuse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoApplyDiffuseHelp)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoSubMapApplyScale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapApplyScaleHelp1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoApplyMorphColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMorphColorHelp)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeFileSub)
                    .addComponent(AlbedoMapFileHelp1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeFile)
                    .addComponent(AlbedoMapFileHelp)
                    .addComponent(AlbedoSubMapFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AlbedoMapFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(albedo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(albedoSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subHelp)
                    .addComponent(jButton2)
                    .addComponent(albedoColorPick)
                    .addComponent(albedoSubColorPick))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapLoopHelp)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapLoopHelp1))
                .addContainerGap(89, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AlbedoMapFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapFromActionPerformed

    }//GEN-LAST:event_AlbedoMapFromActionPerformed

    private void albedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albedoActionPerformed

        float f = Float.parseFloat(albedo.getText());

    }//GEN-LAST:event_albedoActionPerformed

    private void AlbedoMapUVFlipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapUVFlipActionPerformed

    }//GEN-LAST:event_AlbedoMapUVFlipActionPerformed

    private void AlbedoApplyScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoApplyScaleActionPerformed

    }//GEN-LAST:event_AlbedoApplyScaleActionPerformed

    private void AlbedoApplyDiffuseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoApplyDiffuseActionPerformed

    }//GEN-LAST:event_AlbedoApplyDiffuseActionPerformed

    private void AlbedoApplyMorphColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoApplyMorphColorActionPerformed

    }//GEN-LAST:event_AlbedoApplyMorphColorActionPerformed

    private void changeFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeFileActionPerformed
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new java.io.File("../../Materials"));//The directory we are looking for is the Materials folder from ray
        fileChooser.setDialogTitle("Choose new Albedo Map File");
        //Filtering by ImageFiles
        FileFilter imageFilter = new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp", "tga", "targa", "dds", "tif", "tiff", "jpeg", "pcd");
        fileChooser.setFileFilter(imageFilter);

        try {
            int selection = fileChooser.showOpenDialog(this);
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            albedoMap = filePath;
            filePath = filePath.replace("\\", "/");

            if (selection == 0) {
                String relative = toRelative.convertToRelativePath(foo.getFileToEdit().getParent(), filePath);
                AlbedoMapFile.setText(relative);
                if (AlbedoMapFrom.getSelectedIndex() != 1) {
                    AlbedoMapFrom.setSelectedIndex(1);
                }

                BufferedReader br = null;
                br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                String oldtext = "";
                String line = br.readLine();
                String catchOld = "";
                String catchNew = "";
                while (line != null) {
                    if (line.contains("#define ALBEDO_MAP_FILE")) {
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
                        if (!catchOld.equalsIgnoreCase(AlbedoMapFile.getText())) {
                            catchNew = AlbedoMapFile.getText(); //catchnewdigit
                            line = "#define ALBEDO_MAP_FILE " + '"' + catchNew + '"';
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
        //AlbedoMapHelpText.
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
                + "    <li><strike>9 : Params fetch from Specular Power from the pmx. (this option can only be used for specular)</strike>, doesn't work on Albedo</li></ul></pre><br><br></HTML>");
        AlbedoMapHelp.setLayout(new BorderLayout());
        AlbedoMapHelp.setSize(700, 550);
        AlbedoMapHelp.setLocationRelativeTo(this);
        AlbedoMapHelp.setResizable(true);
        AlbedoMapHelp.setVisible(true);
        AlbedoMapHelp.add(AlbedoMapHelpText);
        AlbedoMapHelp.setTitle("Albedo Map Help");
        AlbedoMapHelp.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        AlbedoMapHelp.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                AlbedoMapHelp.dispose();
            }
        });

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            AlbedoMapHelp.setIconImage(myImg);
        } catch (IOException ex) {
            Logger.getLogger(AlbedoSection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_AlbedoMapHelpActionPerformed

    private void AlbedoMapUVFlipHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapUVFlipHelpActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        //AlbedoMapHelpText.
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
        help.setTitle("Albedo Map UV Flip Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            Logger.getLogger(AlbedoSection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_AlbedoMapUVFlipHelpActionPerformed

    private void AlbedoMapApplyScaleHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapApplyScaleHelpActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        //AlbedoMapHelpText.
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

        help.setTitle("Albedo Map Apply Scale Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            Logger.getLogger(AlbedoSection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_AlbedoMapApplyScaleHelpActionPerformed

    private void AlbedoApplyDiffuseHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoApplyDiffuseHelpActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        helptext.setText("<HTML><center>Texture colors to multiply with diffuse from the <b>PMX</b>.</center></HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(400, 100);
        help.setLocationRelativeTo(this);
        helptext.setBorder(new EmptyBorder(10, 30, 10, 10));
        help.setResizable(true);
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Albedo Map Apply Diffuse Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            Logger.getLogger(AlbedoSection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_AlbedoApplyDiffuseHelpActionPerformed

    private void AlbedoMorphColorHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMorphColorHelpActionPerformed
        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        //AlbedoMapHelpText.
        helptext.setText("<HTML>Texture colors to multiply with color from the morph <b>controller</b> (R+/G+/B+)...</HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(500, 160);
        help.setLocationRelativeTo(this);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setResizable(true);
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Albedo Map Apply Morph Color Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            Logger.getLogger(AlbedoSection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_AlbedoMorphColorHelpActionPerformed

    private void AlbedoMapFileHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapFileHelpActionPerformed
        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        //AlbedoMapHelpText.
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
        help.setTitle("Albedo Map File Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            Logger.getLogger(AlbedoSection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_AlbedoMapFileHelpActionPerformed

    private void albedoColorPickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albedoColorPickActionPerformed

        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        //AlbedoMapHelpText.
        helptext.setText("<HTML>When the ALBEDO_MAP_FROM at 0 or ALBEDO_MAP_APPLY_SCALE at 1, you will need to set a color/rgb to albedo, and color range is between 0.0 and 1.0"
                + "like const float3 albedo = float3(r, g, b) <br><br><br>"
                + "For example : <br>"
                + "if the red is normalized value, it can be set to albedo like : const float3 albedo = float3(1.0, 0.0, 0.0);<br>"
                + "if the red is unnormalized value, it can be set to albedo like : const float3 albedo = float3(255, 0.0, 0.0) / 255.0;<br>"
                + "If the color is fetched from your monitor with sRGB color-space, you will need to convert the color to linear color-space by color ^ gamma<br>"
                + "Convert srgb color-space from normalized value to linear color-space like : const float3 albedo = pow(float3(r, g, b), 2.2);<br>"
                + "Convert srgb color-space from unnormalized value to linear color-space like : const float3 albedo = pow(float3(r, g, b) / 255.0, 2.2);<br><br><br>"
                + "Tips:<br>"
                + "The Gamma is near 2.2 used most of time, but some old mac's used gamma of 1.8, About sRGB and Gamma, you can see docs for more information<br><br>"
                + "https://developer.nvidia.com/gpugems/GPUGems3/gpugems3_ch24.html<br>"
                + "https://en.wikipedia.org/wiki/SRGB<br></HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(600, 350);
        help.setLocationRelativeTo(this);
        help.setResizable(true);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setVisible(true);
        help.add(helptext);

        help.setTitle("Albedo Map Scale Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }

    }//GEN-LAST:event_albedoColorPickActionPerformed

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

        help.setTitle("Albedo Map Loop Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapLoopHelpActionPerformed

    private void changeFileSubStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_changeFileSubStateChanged

    }//GEN-LAST:event_changeFileSubStateChanged

    private void changeFileSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeFileSubActionPerformed
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new java.io.File("../../Materials"));//The directory we are looking for is the Materials folder from ray
        fileChooser.setDialogTitle("Choose new Albedo Sub Map File");
        //Filtering by ImageFiles
        FileFilter imageFilter = new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp");
        fileChooser.setFileFilter(imageFilter);

        try {
            int selection = fileChooser.showOpenDialog(this);
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            filePath = filePath.replace("\\", "/");

            if (selection == 0) {
                String relative = toRelative.convertToRelativePath(foo.getFileToEdit().getParent(), filePath);
                AlbedoSubMapFile.setText(relative);

                if (AlbedoSubMapFrom.getSelectedIndex() != 1) {
                    AlbedoSubMapFrom.setSelectedIndex(1);
                }

                BufferedReader br = null;
                br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                String oldtext = "";
                String line = br.readLine();
                String catchOld = "";
                String catchNew = "";
                while (line != null) {
                    if (line.contains("#define ALBEDO_SUB_MAP_FILE")) {
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
                        if (!catchOld.equalsIgnoreCase(AlbedoSubMapFile.getText())) {
                            catchNew = AlbedoSubMapFile.getText(); //catchnewdigit
                            line = "#define ALBEDO_SUB_MAP_FILE " + '"' + catchNew + '"';
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
            }
        } catch (Exception e) {
            //wont happen hahaaaaaa
        }
    }//GEN-LAST:event_changeFileSubActionPerformed

    private void albedoSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albedoSubActionPerformed

    }//GEN-LAST:event_albedoSubActionPerformed

    private void AlbedoMapHelp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapHelp1ActionPerformed

        JFrame AlbedoMapHelp = new JFrame();
        JLabel AlbedoMapHelpText = new JLabel();
        AlbedoMapHelp.setName("help");
        //AlbedoMapHelpText.
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
                + "    <li>9 : Params fetch from Specular Power from the pmx. (this option can only be used for smoothness)</li></ul></pre><br><br></HTML>");
        AlbedoMapHelp.setLayout(new BorderLayout());
        AlbedoMapHelp.setSize(700, 550);
        AlbedoMapHelp.setLocationRelativeTo(this);
        AlbedoMapHelp.setResizable(true);
        AlbedoMapHelp.setVisible(true);
        AlbedoMapHelp.add(AlbedoMapHelpText);

        AlbedoMapHelp.setTitle("Albedo Sub Map From Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            AlbedoMapHelp.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapHelp1ActionPerformed

    private void AlbedoMapUVFlipHelp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapUVFlipHelp1ActionPerformed
        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        //AlbedoMapHelpText.
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

        help.setTitle("Albedo Sub Map UV Flip Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapUVFlipHelp1ActionPerformed

    private void AlbedoMapApplyScaleHelp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapApplyScaleHelp1ActionPerformed
        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        //AlbedoMapHelpText.
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
        help.setTitle("Albedo Sub Map Apply Scale Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_AlbedoMapApplyScaleHelp1ActionPerformed

    private void AlbedoMapFileHelp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapFileHelp1ActionPerformed
        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        //AlbedoMapHelpText.
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

        help.setTitle("Albedo Sub Map File Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {

        }
    }//GEN-LAST:event_AlbedoMapFileHelp1ActionPerformed

    private void subHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subHelpActionPerformed
        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        //AlbedoMapHelpText.
        helptext.setText("<HTML>between 0 ~ 1</HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(300, 70);
        help.setLocationRelativeTo(this);
        help.setResizable(true);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Albedo Sub Map Scale Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {

        }
    }//GEN-LAST:event_subHelpActionPerformed

    private void AlbedoMapLoopHelp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapLoopHelp1ActionPerformed
        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        //AlbedoMapHelpText.
        helptext.setText("<HTML>You can tile your texture for the X and Y axis separately by change albedoMapLoopNum = float2(x, y) between float2(0, 0) ~ float2(inf, inf) </HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(600, 200);
        help.setLocationRelativeTo(this);
        help.setResizable(true);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Albedo Sub Map Loop Help");

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
        //AlbedoMapHelpText.
        helptext.setText("<HTML>You can apply second value for color of texture (albedo) change by change ALBEDO_SUB_ENABLE <br><br><br>"
                + "<ul>"
                + "<li>0 : None</li>"
                + "<li>1 : albedo * albedoSub</li>"
                + "<li>2 : albedo ^ albedoSub</li>"
                + "<li>3 : albedo + albedoSub</li>"
                + "<li>4 : melanin</li>"
                + "<li>5 : Alpha Blend</li>"
                + "</ul></HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(600, 200);
        help.setLocationRelativeTo(this);
        help.setResizable(true);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Albedo Sub Enable Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {

        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            albedo.setText("float3(" + codeColor + ")/255");
            try {
                BufferedReader br = null;
                br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                String oldtext = "";
                String line = br.readLine();
                String catchOld = "";
                String catchNew = "";
                while (line != null) {
                    if (line.contains("albedo =")) {
                        catchOld = line; //auxiliar line

                        catchNew = albedo.getText(); //catchnewdigit
                        catchNew = catchNew.replaceAll(" ", "");
                        line = ("const float3 albedo = " + catchNew + ";");
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
            AlbedoMapFrom.setSelectedIndex(0);

        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void albedoSubColorPickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albedoSubColorPickActionPerformed

        JColorChooser cc = new JColorChooser();

        AbstractColorChooserPanel defaultPanels[] = cc.getChooserPanels();
        cc.removeChooserPanel(defaultPanels[3]);
        cc.removeChooserPanel(defaultPanels[2]);
        cc.removeChooserPanel(defaultPanels[0]);
        cc.removeChooserPanel(defaultPanels[4]);
        ImageIcon ico = new ImageIcon("icon/colorchooser.png");
        int ColorPicker = JOptionPane.showConfirmDialog(this, cc, "Choose a Colour", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, ico);

        Color color = cc.getColor();
        if (ColorPicker == JOptionPane.OK_OPTION) {
            String codeColor = "" + color.getRed() + "," + color.getGreen() + "," + color.getBlue();
            albedoSub.setText("float3(" + codeColor + ")/255");
            try {
                BufferedReader br = null;
                br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                String oldtext = "";
                String line = br.readLine();
                String catchOld = "";
                String catchNew = "";
                while (line != null) {
                    if (line.contains("albedoSub ")) {
                        catchOld = line; //auxiliar line

                        catchNew = albedoSub.getText(); //catchnewdigit
                        catchNew = catchNew.replaceAll(" ", "");
                        line = ("const float3 albedoSub = " + catchNew + ";");
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
            AlbedoSubMapFrom.setSelectedIndex(0);
        }
    }//GEN-LAST:event_albedoSubColorPickActionPerformed

    private void albedoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_albedoKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_albedoKeyTyped

    private void albedoSubKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_albedoSubKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_albedoSubKeyTyped

    private void AlbedoMapFromItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_AlbedoMapFromItemStateChanged
        // TODO add your handling code here:
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define ALBEDO_MAP_FROM")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = AlbedoMapFrom.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_AlbedoMapFromItemStateChanged

    private void AlbedoSubEnableItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_AlbedoSubEnableItemStateChanged
        // TODO add your handling code here:
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define ALBEDO_SUB_ENABLE")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = AlbedoSubEnable.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_AlbedoSubEnableItemStateChanged

    private void AlbedoSubMapFromItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_AlbedoSubMapFromItemStateChanged
        // TODO add your handling code here:
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define ALBEDO_SUB_MAP_FROM")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = AlbedoSubMapFrom.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_AlbedoSubMapFromItemStateChanged

    private void AlbedoSubMapUVFlip1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_AlbedoSubMapUVFlip1ItemStateChanged
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define ALBEDO_SUB_MAP_UV_FLIP")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = AlbedoSubMapUVFlip1.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_AlbedoSubMapUVFlip1ItemStateChanged

    private void AlbedoSubMapApplyScaleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_AlbedoSubMapApplyScaleItemStateChanged
        // TODO add your handling code here:
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define ALBEDO_SUB_MAP_APPLY_SCALE")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = AlbedoSubMapApplyScale.getSelectedItem().toString(); //catchnewdigit
                    line = line.replaceAll("" + catchOld, "" + catchNew); //replace old for the new one
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
    }//GEN-LAST:event_AlbedoSubMapApplyScaleItemStateChanged

    private void AlbedoMapUVFlipItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_AlbedoMapUVFlipItemStateChanged
        // TODO add your handling code here:
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define ALBEDO_MAP_UV_FLIP")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = AlbedoMapUVFlip.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_AlbedoMapUVFlipItemStateChanged

    private void AlbedoApplyScaleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_AlbedoApplyScaleItemStateChanged
        // TODO add your handling code here:
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define ALBEDO_MAP_APPLY_SCALE")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = AlbedoApplyScale.getSelectedItem().toString(); //catchnewdigit
                    line = line.replaceAll("" + catchOld, "" + catchNew); //replace old for the new one
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
    }//GEN-LAST:event_AlbedoApplyScaleItemStateChanged

    private void AlbedoApplyMorphColorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_AlbedoApplyMorphColorItemStateChanged
        // TODO add your handling code here:
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define ALBEDO_MAP_APPLY_MORPH_COLOR")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = AlbedoApplyMorphColor.getSelectedItem().toString(); //catchnewdigit
                    line = line.replaceAll("" + catchOld, "" + catchNew); //replace old for the new one
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
    }//GEN-LAST:event_AlbedoApplyMorphColorItemStateChanged

    private void AlbedoApplyDiffuseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_AlbedoApplyDiffuseItemStateChanged
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define ALBEDO_MAP_APPLY_DIFFUSE")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = AlbedoApplyDiffuse.getSelectedItem().toString(); //catchnewdigit
                    line = line.replaceAll("" + catchOld, "" + catchNew); //replace old for the new one
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
    }//GEN-LAST:event_AlbedoApplyDiffuseItemStateChanged

    private void albedoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_albedoPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_albedoPropertyChange

    private void albedoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_albedoKeyReleased
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("albedo ")) {
                    catchOld = line; //auxiliar line

                    catchNew = albedo.getText(); //catchnewdigit
                    catchNew = catchNew.replaceAll(" ", "");
                    line = ("const float3 albedo = " + catchNew + ";");

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
    }//GEN-LAST:event_albedoKeyReleased

    private void albedoSubKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_albedoSubKeyReleased
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("albedoSub ")) {
                    catchOld = line; //auxiliar line

                    catchNew = albedoSub.getText(); //catchnewdigit
                    catchNew = catchNew.replaceAll(" ", "");
                    line = ("const float3 albedoSub = " + catchNew + ";");
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
    }//GEN-LAST:event_albedoSubKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        previewImg(AlbedoMapFile.getText());

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        previewImg(AlbedoSubMapFile.getText());
    }//GEN-LAST:event_jButton4ActionPerformed
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
    private javax.swing.JComboBox<String> AlbedoApplyDiffuse;
    private javax.swing.JButton AlbedoApplyDiffuseHelp;
    private javax.swing.JComboBox<String> AlbedoApplyMorphColor;
    private javax.swing.JComboBox<String> AlbedoApplyScale;
    private javax.swing.JButton AlbedoMapApplyScaleHelp;
    private javax.swing.JButton AlbedoMapApplyScaleHelp1;
    private javax.swing.JTextField AlbedoMapFile;
    private javax.swing.JButton AlbedoMapFileHelp;
    private javax.swing.JButton AlbedoMapFileHelp1;
    public javax.swing.JComboBox<String> AlbedoMapFrom;
    private javax.swing.JButton AlbedoMapHelp;
    private javax.swing.JButton AlbedoMapHelp1;
    private javax.swing.JButton AlbedoMapLoopHelp;
    private javax.swing.JButton AlbedoMapLoopHelp1;
    public javax.swing.JComboBox<String> AlbedoMapUVFlip;
    private javax.swing.JButton AlbedoMapUVFlipHelp;
    private javax.swing.JButton AlbedoMapUVFlipHelp1;
    private javax.swing.JButton AlbedoMorphColorHelp;
    private javax.swing.JComboBox<String> AlbedoSubEnable;
    private javax.swing.JComboBox<String> AlbedoSubMapApplyScale;
    private javax.swing.JTextField AlbedoSubMapFile;
    private javax.swing.JComboBox<String> AlbedoSubMapFrom;
    private javax.swing.JComboBox<String> AlbedoSubMapUVFlip1;
    private javax.swing.JTextField albedo;
    private javax.swing.JButton albedoColorPick;
    private javax.swing.JTextField albedoSub;
    private javax.swing.JButton albedoSubColorPick;
    private javax.swing.JButton back;
    private javax.swing.JButton changeFile;
    private javax.swing.JButton changeFileSub;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JButton subHelp;
    // End of variables declaration//GEN-END:variables
}
