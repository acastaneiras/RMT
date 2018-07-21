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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JButton;
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

public class CustomEnableSection extends javax.swing.JFrame {

    public static MaterialMakerv2 foo = new MaterialMakerv2();
    public static WindowFrame wf = new WindowFrame();

    String CustomA = getCustomA();
    String CustomALoop = String.valueOf(getCustomAMapLoop());
    String CustomB = getCustomB();
    String CustomBLoop = String.valueOf(getCustomBMapLoop());
    String Skin = String.valueOf(getSSSkinTransmittance());

    String lastCustomA = getCustomA();
    String lastCustomALoop = String.valueOf(getCustomAMapLoop());
    String lastCustomBLoop = String.valueOf(getCustomBMapLoop());

    /*CustomAMap*/
    private static int CatchCustomAMapFrom;
    private static int CatchCustomAMapColorFlip;
    private static int CatchCustomAMapUVFlip;
    private static int CatchCustomAMapSwizzle;
    private static int CatchCustomAMapApplyScale;
    private static int CatchCustomEnable;
    private static String CatchCustomAMapFile = null;
    private static String CatchCustomA;
    private static float CatchCustomALoop;
    private static String CatchCustomB;
    private static String CatchSkin;
    private static float CatchCustomBLoop;
    private static int CatchCustomBMapFrom;
    private static int CatchCustomBMapColorFlip;
    private static int CatchCustomBMapUVFlip;
    private static int CatchCustomBMapSwizzle;
    private static int CatchCustomBMapApplyScale;
    private static String CatchCustomBMapFile = null;
    JFrame ErrorWindow = new JFrame();
    public int errors = 0;

    public void SomethingWentWrong() {
        if (errors == 1) {
            JLabel ErrorWindowText = new JLabel();
            //ErrorWindowText.
            ErrorWindowText.setText("<HTML><div style='padding-left:30px;'>Something went wrong while trying to load <i>Custom Section</i>...<br><br>"
                    + "Please make sure the file you are trying to open doesn't <b>exceed the limit for each parameter</b>, usually this happens when you are trying to open "
                    + "a .fx file where some of it's parameters has <b>higher values</b> than supposed to be<br><br>"
                    + "<b>Limits: </b><br>"
                    + "<ul>"
                    + "<li>CustomEnable: 0 - 7</li>"
                    + "<li>CustomMapFrom: 0 - 8</li>"
                    + "<li>CustomMapUVFlip: 0 - 3</li>"
                    + "<li>CustomMapColorFlip: 0-3</li>"
                    + "<li>CustomMapSwizzle: 0-3</li>"
                    + "<li>CustomMapApplyScale: 0 - 2</li>"
                    + "<br>"
                    + "</ul>Same for CustomA and B</div></HTML>");
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

    /*CustomASubMap*/
    public int getCustomAEnable() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());

            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define CUSTOM_ENABLE")) {

                    CatchCustomEnable = Integer.parseInt(s.replaceAll("[\\D]", ""));

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
        return CatchCustomEnable;
    }

    public int getCustomAMapFrom() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());

            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define CUSTOM_A_MAP_FROM")) {

                    CatchCustomAMapFrom = Integer.parseInt(s.replaceAll("[\\D]", ""));

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
        return CatchCustomAMapFrom;
    }

    public int getCustomAMapUVFlip() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define CUSTOM_A_MAP_UV_FLIP")) {
                    CatchCustomAMapUVFlip = Integer.parseInt(s.replaceAll("[\\D]", ""));
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

        return CatchCustomAMapUVFlip;
    }

    public int getCustomAMapColorFlip() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define CUSTOM_A_MAP_COLOR_FLIP")) {
                    CatchCustomAMapColorFlip = Integer.parseInt(s.replaceAll("[\\D]", ""));
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
        return CatchCustomAMapColorFlip;
    }

    public int getCustomAMapSwizzle() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define CUSTOM_A_MAP_SWIZZLE")) {
                    CatchCustomAMapSwizzle = Integer.parseInt(s.replaceAll("[\\D]", ""));
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

        return CatchCustomAMapSwizzle;
    }

    public int getCustomAMapApplyScale() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define CUSTOM_A_MAP_APPLY_SCALE")) {
                    CatchCustomAMapApplyScale = Integer.parseInt(s.replaceAll("[\\D]", ""));
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

        return CatchCustomAMapApplyScale;
    }

    public String getCustomAMapFile() {
        BufferedReader AlbedotoEdit_Br = null;
        String str = "";

        try {

            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define CUSTOM_A_MAP_FILE")) {
                    CatchCustomAMapFile = "";
                    for (int i = s.indexOf('"') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';') {

                        } else {
                            CatchCustomAMapFile += s.charAt(i);
                        }
                    }

                    CatchCustomAMapFile = CatchCustomAMapFile.replace("null", "");
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
        return CatchCustomAMapFile;
    }

    public String getCustomA() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("customA =")) {
                    String txt = "";
                    for (int i = s.indexOf('=') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';') {

                        } else {
                            txt += s.charAt(i);
                        }
                    }
                    CatchCustomA = (txt);
                    CatchCustomA = CatchCustomA.replaceAll(";", "");

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
        return CatchCustomA;
    }

    public float getCustomAMapLoop() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("customAMapLoopNum =")) {
                    String txt = "";
                    for (int i = s.indexOf('=') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == ' ') {

                        } else {
                            txt += s.charAt(i);
                        }
                    }
                    txt = txt.replaceAll(";", "");
                    CatchCustomALoop = Float.parseFloat(txt);

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
        return CatchCustomALoop;
    }

    public int getCustomBMapFrom() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());

            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define CUSTOM_B_MAP_FROM")) {

                    CatchCustomBMapFrom = Integer.parseInt(s.replaceAll("[\\D]", ""));

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
        return CatchCustomBMapFrom;
    }

    public int getCustomBMapUVFlip() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define CUSTOM_B_MAP_UV_FLIP")) {
                    CatchCustomBMapUVFlip = Integer.parseInt(s.replaceAll("[\\D]", ""));
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

        return CatchCustomBMapUVFlip;
    }

    public int getCustomBMapColorFlip() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define CUSTOM_B_MAP_COLOR_FLIP")) {
                    CatchCustomBMapColorFlip = Integer.parseInt(s.replaceAll("[\\D]", ""));
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

        return CatchCustomBMapColorFlip;
    }

    public int getCustomBMapSwizzle() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define CUSTOM_B_MAP_SWIZZLE")) {
                    CatchCustomBMapSwizzle = Integer.parseInt(s.replaceAll("[\\D]", ""));
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

        return CatchCustomBMapSwizzle;
    }

    public int getCustomBMapApplyScale() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define CUSTOM_B_MAP_APPLY_SCALE")) {
                    CatchCustomBMapApplyScale = Integer.parseInt(s.replaceAll("[\\D]", ""));
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

        return CatchCustomBMapApplyScale;
    }

    public String getCustomBMapFile() {
        BufferedReader AlbedotoEdit_Br = null;

        try {

            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define CUSTOM_B_MAP_FILE")) {
                    CatchCustomBMapFile = "";
                    for (int i = s.indexOf('"') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';') {

                        } else {
                            CatchCustomBMapFile += s.charAt(i);
                        }
                    }

                    CatchCustomBMapFile = CatchCustomBMapFile.replace("null", "");
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
        return CatchCustomBMapFile;
    }

    public String getCustomB() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("customB =")) {
                    String txt = "";
                    for (int i = s.indexOf('=') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';' || s.charAt(i) == ' ') {

                        } else {
                            txt += s.charAt(i);
                        }
                    }
                    CatchCustomB = (txt);
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
        return CatchCustomB;
    }

    public float getCustomBMapLoop() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("customBMapLoopNum =")) {
                    String txt = "";
                    for (int i = s.indexOf('=') + 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '"' || s.charAt(i) == ';' || s.charAt(i) == ' ') {

                        } else {
                            txt += s.charAt(i);
                        }
                    }
                    CatchCustomBLoop = Float.parseFloat(txt);
                }
                s = AlbedotoEdit_Br.readLine();

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(WindowFrame.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(WindowFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                AlbedotoEdit_Br.close();
            } catch (Exception e) {

            }
        }
        return CatchCustomBLoop;
    }

    public String getSSSkinTransmittance() {
        BufferedReader AlbedotoEdit_Br = null;
        try {
            FileReader AlbedotoEdit_fr = new FileReader(foo.getFilePath());
            AlbedotoEdit_Br = new BufferedReader(AlbedotoEdit_fr);

            String s = "";

            s = AlbedotoEdit_Br.readLine();

            while (s != null) {
                if (s.contains("#define SSS_SKIN_TRANSMITTANCE")) {
                    String txt = "";
                    for (int i = s.indexOf('('); i < s.length(); i++) {
                        txt += s.charAt(i);
                    }
                    CatchSkin = (txt);
                }
                s = AlbedotoEdit_Br.readLine();

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(WindowFrame.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(WindowFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                AlbedotoEdit_Br.close();
            } catch (Exception e) {

            }
        }
        return CatchSkin;
    }

    public String checkLoop() {
        MaterialMakerv2 m = new MaterialMakerv2();
        BufferedReader br = null;
        String check = "";
        try {
            br = new BufferedReader(new FileReader(m.getFileToEdit()));
            String line = br.readLine();

            String catchOld = "";
            String catchNew = "";

            while (line != null) {
                if (line.contains("customBMapLoopNum =")) {
                    catchOld = line; //auxiliar line
                    String aux = "";
                    for (int i = catchOld.indexOf('=') + 1; i < catchOld.length() - 1; i++) {
                        aux += catchOld.charAt(i);
                    }
                    catchOld = aux;

                    if (!catchOld.equalsIgnoreCase(CustomBLoop)) {
                        CustomBLoop = CustomBLoop.replace(",", ".");
                        catchNew = CustomBLoop; //catchnewdigit
                        line = line.replaceAll("" + catchOld, " " + catchNew); //replace old for the new one
                        check = line;
                    }
                }

                line = br.readLine();

            }

        } catch (Exception ex) {
            Logger.getLogger(AlbedoSection.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (Exception e) {

            }
        }
        return check;
    }

    public String checkSkin() {
        MaterialMakerv2 m = new MaterialMakerv2();
        BufferedReader br = null;
        String check = "";
        try {
            br = new BufferedReader(new FileReader(m.getFileToEdit()));
            String line = br.readLine();

            String catchOld = "";

            while (line != null) {
                if (line.contains("#define SSS_SKIN_TRANSMITTANCE")) {
                    catchOld = line; //auxiliar line
                    String aux = "";
                    for (int i = catchOld.indexOf('('); i < catchOld.length(); i++) {
                        aux += catchOld.charAt(i);
                    }
                    check = aux;
                }

                line = br.readLine();

            }

        } catch (Exception ex) {
            Logger.getLogger(AlbedoSection.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (Exception e) {

            }
        }
        return check;

    }

    /**
     * Creates new form WindowFrame
     */
    public CustomEnableSection() {//Constructor

        initComponents();//Generated by the GUI mostly
        myInitComponents(); //CustomA Loop
        myInitComponents2(); //CustomB Sub Loop
        myInitComponents3(); //CustomA Scale
        myInitComponents4(); //CustomB Sub Scale
        myInitComponents5();

    }

    public void myInitComponents() {
        final DecimalFormat df = new DecimalFormat("0.####");
        final JTextField text = new JTextField(20);
        final DoubleJSlider slider = new DoubleJSlider(-6400, 6400, 0, 100);

        float catchvalue;
        catchvalue = Float.parseFloat("" + getCustomAMapLoop()) * slider.scale;
        slider.setPaintTrack(true);
        slider.setPaintLabels(true);
        slider.setBounds(48, 417, 200, 30);
        slider.setPaintTicks(true);
        slider.setValue((int) catchvalue);

        text.setBounds(258, 417, 50, 26);
        text.setFont(new Font("Arial", Font.PLAIN, 11));
        text.setText(df.format(slider.getScaledValue()));

        add(text);
        add(slider);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                try {

                    text.setText(df.format(slider.getScaledValue()));
                    CustomALoop = text.getText();
                    if (!slider.getValueIsAdjusting() && !CustomALoop.equalsIgnoreCase(lastCustomALoop)) {
                        lastCustomALoop = CustomALoop;
                        try {
                            BufferedReader br = null;
                            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                            String oldtext = "";
                            String line = br.readLine();
                            String catchOld = "";
                            String catchNew = "";
                            while (line != null) {
                                if (line.contains("customAMapLoopNum")) {
                                    catchOld = line; //auxiliar line
                                    String aux = "";
                                    for (int i = catchOld.indexOf('=') + 1; i < catchOld.length() - 1; i++) {
                                        if (catchOld.charAt(i) == ' ' || catchOld.charAt(i) == ';' || catchOld.charAt(i) == '"') {

                                        } else {
                                            aux += catchOld.charAt(i);
                                        }
                                    }
                                    catchOld = aux;
                                    if (!catchOld.equalsIgnoreCase(CustomALoop)) {
                                        CustomALoop = CustomALoop.replace(",", ".");
                                        catchNew = CustomALoop; //catchnewdigit
                                        line = "const float customAMapLoopNum = " + catchNew + ";";
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
                CustomALoop = text.getText();
            }
        });

    }

    public void myInitComponents2() {
        final DecimalFormat df = new DecimalFormat("0.####");
        final JTextField text = new JTextField(20);
        final JButton help = new JButton();
        final JLabel label = new JLabel();
        final DoubleJSlider slider = new DoubleJSlider(-6400, 6400, 0, 100);
        float catchvalue;
        catchvalue = Float.parseFloat("" + getCustomBMapLoop()) * slider.scale;

        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFrame help = new JFrame();
                JLabel helptext = new JLabel();

                helptext.setText("<HTML>You can tile your texture for the X and Y axis separately by change albedoMapLoopNum = float2(x, y) between float2(0, 0) ~ float2(inf, inf) </HTML>");
                help.setLayout(new BorderLayout());
                help.setName("help");
                help.setSize(550, 80);
                help.setLocationRelativeTo(null);
                help.setResizable(true);
                helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
                help.setVisible(true);
                help.add(helptext);

                help.setTitle("Custom B Map Loop Flip Help");

                try {
                    InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
                    BufferedImage myImg = ImageIO.read(imgStream);
                    help.setIconImage(myImg);
                } catch (IOException ex) {
                    System.out.println("" + ex);
                }

            }
        });
        int fix = -5;
        help.setBounds(623, 393 + fix, 53, 23);
        help.setText("Help");
        help.setVisible(true);
        label.setText("<html><b>CUSTOM B MAP LOOP</b></html>");
        label.setVisible(true);
        label.setBounds(505, 398 + fix, 117, 14);
        slider.setPaintTrack(true);
        slider.setPaintLabels(true);
        slider.setBounds(499, 416, 200, 30);
        slider.setPaintTicks(true);
        slider.setValue((int) catchvalue);
        text.setBounds(707, 416 + fix, 50, 26);
        text.setFont(new Font("Arial", Font.PLAIN, 11));
        text.setText(df.format(slider.getScaledValue()));
        if (!checkLoop().equalsIgnoreCase("")) {
            add(help);
            add(label);
            add(text);

            add(slider);
        }

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                try {

                    text.setText(df.format(slider.getScaledValue()));
                    CustomBLoop = text.getText();
                    if (!slider.getValueIsAdjusting() && !CustomBLoop.equalsIgnoreCase(lastCustomBLoop)) {
                        lastCustomBLoop = CustomBLoop;
                        try {
                            BufferedReader br = null;
                            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                            String oldtext = "";
                            String line = br.readLine();
                            String catchOld = "";
                            String catchNew = "";
                            while (line != null) {
                                if (line.contains("customBMapLoopNum")) {
                                    catchOld = line; //auxiliar line
                                    String aux = "";
                                    for (int i = catchOld.indexOf('=') + 1; i < catchOld.length() - 1; i++) {
                                        if (catchOld.charAt(i) == ' ' || catchOld.charAt(i) == ';' || catchOld.charAt(i) == '"') {

                                        } else {
                                            aux += catchOld.charAt(i);
                                        }
                                    }
                                    catchOld = aux;
                                    if (!catchOld.equalsIgnoreCase(CustomBLoop)) {
                                        CustomBLoop = CustomBLoop.replace(",", ".");
                                        catchNew = CustomBLoop; //catchnewdigit
                                        line = "const float2 customBMapLoopNum = " + catchNew + ";";
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
                CustomBLoop = text.getText();
            }
        });

    }

    public void myInitComponents3() {
        final DecimalFormat df = new DecimalFormat("0.####");
        final JTextField text = new JTextField(20);
        final DoubleJSlider slider = new DoubleJSlider(0, 100, 0, 100);
        float catchvalue;
        catchvalue = Float.parseFloat("" + getCustomA()) * slider.scale;
        slider.setPaintTrack(true);
        slider.setPaintLabels(true);
        slider.setBounds(48, 355, 200, 30);
        slider.setPaintTicks(true);
        slider.setValue((int) catchvalue);
        text.setBounds(258, 355, 50, 26);
        text.setFont(new Font("Arial", Font.PLAIN, 11));
        text.setText(df.format(slider.getScaledValue()));

        add(text);
        add(slider);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    text.setText(df.format(slider.getScaledValue()));
                    CustomA = text.getText();
                    if (!slider.getValueIsAdjusting() && !CustomA.equalsIgnoreCase(lastCustomA)) {
                        lastCustomA = CustomA;
                        try {
                            BufferedReader br = null;
                            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                            String oldtext = "";
                            String line = br.readLine();
                            String catchOld = "";
                            String catchNew = "";
                            while (line != null) {
                                if (line.contains("customA =")) {
                                    catchOld = line;

                                    catchNew = CustomA;
                                    catchNew = catchNew.replaceAll(" ", "");
                                    catchNew = catchNew.replaceAll(",", ".");

                                    line = ("const float customA = " + catchNew + ";");
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
                CustomA = text.getText();
            }
        });

    }

    public void myInitComponents4() {
        final JLabel label = new JLabel();
        final JTextField text = new JTextField(20);
        final JButton help = new JButton();
        final JButton pick = new JButton();

        label.setText("<html><b>CUSTOM B MAP SCALE</b></html>");
        label.setBounds(502, 332 - 3, 135, 14);
        label.setVisible(true);
        text.setText(getCustomB());
        text.setBounds(632, 330 - 3, 117, 20);
        text.setFont(new Font("Arial", Font.PLAIN, 11));
        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                CustomB = text.getText();

                try {
                    BufferedReader br = null;
                    br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                    String oldtext = "";
                    String line = br.readLine();
                    String catchOld = "";
                    String catchNew = "";
                    while (line != null) {
                        if (line.contains("customB =")) {
                            catchOld = line; //auxiliar line

                            catchNew = CustomB; //catchnewdigit
                            catchNew = catchNew.replaceAll(" ", "");

                            line = ("const float3 customB = " + catchNew + ";");
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
        });

        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame help = new JFrame();
                JLabel helptext = new JLabel();
                helptext.setText("<HTML>working in linear color-space, All between 0 ~ 1</HTML>");
                help.setLayout(new BorderLayout());
                help.setName("help");
                help.setSize(450, 80);
                help.setLocationRelativeTo(null);
                help.setResizable(true);
                helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
                help.setVisible(true);
                help.add(helptext);
                help.setTitle("Custom B Map Scale Help");

                try {
                    InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
                    BufferedImage myImg = ImageIO.read(imgStream);
                    help.setIconImage(myImg);
                } catch (IOException ex) {
                    System.out.println("" + ex);
                }

            }
        });

        pick.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JColorChooser cc = new JColorChooser();

                AbstractColorChooserPanel defaultPanels[] = cc.getChooserPanels();
                cc.removeChooserPanel(defaultPanels[3]);
                cc.removeChooserPanel(defaultPanels[2]);
                cc.removeChooserPanel(defaultPanels[0]);
                cc.removeChooserPanel(defaultPanels[4]);
                ImageIcon ico = new ImageIcon("icon/colorchooser.png");
                int ColorPicker = JOptionPane.showConfirmDialog(null, cc, "Choose a Colour", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, ico);

                Color color = cc.getColor();
                if (ColorPicker == JOptionPane.OK_OPTION) {
                    String codeColor = "" + color.getRed() + "," + color.getGreen() + "," + color.getBlue();
                    CustomB = ("float3(" + codeColor + ")/255");
                    text.setText(CustomB);
                    try {
                        BufferedReader br = null;
                        br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                        String oldtext = "";
                        String line = br.readLine();
                        String catchOld = "";
                        String catchNew = "";
                        while (line != null) {
                            if (line.contains("customB =")) {
                                catchOld = line; //auxiliar line

                                catchNew = CustomB; //catchnewdigit
                                catchNew = catchNew.replaceAll(" ", "");

                                line = ("const float3 customB = " + catchNew + ";");
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

            }
        });
        pick.setBounds(752, 329 - 3, 90, 23);
        pick.setText("Pick Colour");
        help.setBounds(841, 329 - 3, 53, 23);
        help.setText("Help");
        help.setName("help");
        help.setVisible(true);
        add(pick);
        add(help);
        add(text);
        add(label);
    }

    public void myInitComponents5() {
        final JLabel label = new JLabel();
        final JTextField text = new JTextField(20);

        label.setText("<html><b>SSS SKIN TRANSMITTANCE</b></html>");
        label.setBounds(502, 294, 160, 14);
        label.setVisible(true);
        text.setText(getSSSkinTransmittance());
        text.setBounds(657, 291, 246, 22);
        text.setFont(new Font("Arial", Font.PLAIN, 11));
        text.setVisible(true);

        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                Skin = text.getText();

                try {
                    BufferedReader br = null;
                    br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                    String oldtext = "";
                    String line = br.readLine();
                    String catchOld = "";
                    String catchNew = "";
                    while (line != null) {
                        if (line.contains("#define SSS_SKIN_TRANSMITTANCE")) {
                            catchOld = line; //auxiliar line
                            String aux = "";
                            for (int i = catchOld.indexOf('('); i < catchOld.length(); i++) {
                                aux += catchOld.charAt(i);
                            }
                            catchOld = aux;
                            if (!catchOld.equalsIgnoreCase(Skin)) {
                                catchNew = Skin; //catchnewdigit
                                line = "#define SSS_SKIN_TRANSMITTANCE" + catchNew;
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
        });

        if (!checkSkin().equalsIgnoreCase("")) {

            add(label);
            add(text);

        }
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
        CustomAMapFrom = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        changeFile = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        CustomAMapUVFlip = new javax.swing.JComboBox<>();
        back = new javax.swing.JButton();
        AlbedoMapHelp = new javax.swing.JButton();
        AlbedoMapUVFlipHelp = new javax.swing.JButton();
        AlbedoMapFileHelp = new javax.swing.JButton();
        albedoHelp = new javax.swing.JButton();
        AlbedoMapLoopHelp = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        CustomAMapColorFlip = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        CustomAMapSwizzle = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        CustomAMapApplyScale = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        CustomAEnable = new javax.swing.JComboBox<>();
        AlbedoMapUVFlipHelp1 = new javax.swing.JButton();
        AlbedoMapUVFlipHelp2 = new javax.swing.JButton();
        AlbedoMapUVFlipHelp4 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        CustomBMapUVFlip = new javax.swing.JComboBox<>();
        CustomBMapColorFlip = new javax.swing.JComboBox<>();
        AlbedoMapHelp1 = new javax.swing.JButton();
        AlbedoMapUVFlipHelp6 = new javax.swing.JButton();
        AlbedoMapFileHelp1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        CustomBMapFrom = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        changeFile1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        CustomBMapApplyScale = new javax.swing.JComboBox<>();
        CustomAMapFile = new javax.swing.JTextField();
        CustomBMapFile = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Edit CustomEnable");
        setAutoRequestFocus(false);
        setResizable(false);
        setSize(new java.awt.Dimension(960, 540));

        jLabel1.setText("<html><b>CUSTOM A MAP FROM</b></html>");

        CustomAMapFrom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8" }));
        try{
            CustomAMapFrom.setSelectedIndex(getCustomAMapFrom());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        CustomAMapFrom.setToolTipText("");
        CustomAMapFrom.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CustomAMapFromItemStateChanged(evt);
            }
        });
        CustomAMapFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomAMapFromActionPerformed(evt);
            }
        });

        jLabel2.setText("<html><b>CUSTOM A MAP UV FLIP</b></html>");

        jLabel6.setText("<html><b>CUSTOM A MAP FILE</b></html>");

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

        jLabel8.setText("<html><b>CUSTOM A MAP SCALE</b></html>");

        jLabel9.setText("<html><b>CUSTOM A MAP LOOP</b></html>");

        CustomAMapUVFlip.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        try{
            CustomAMapUVFlip.setSelectedIndex(getCustomAMapUVFlip());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        CustomAMapUVFlip.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CustomAMapUVFlipItemStateChanged(evt);
            }
        });
        CustomAMapUVFlip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomAMapUVFlipActionPerformed(evt);
            }
        });

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bg/back.png"))); // NOI18N
        back.setBorder(null);
        back.setContentAreaFilled(false);
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

        jButton1.setText("Help");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("<html><b>CUSTOM A MAP COLOR FLIP</b></html>");

        CustomAMapColorFlip.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        try{
            CustomAMapColorFlip.setSelectedIndex(getCustomAMapColorFlip());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        CustomAMapColorFlip.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CustomAMapColorFlipItemStateChanged(evt);
            }
        });
        CustomAMapColorFlip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomAMapColorFlipActionPerformed(evt);
            }
        });

        jLabel4.setText("<html><b>CUSTOM A MAP SWIZZLE</b></html>");

        CustomAMapSwizzle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        try{
            CustomAMapSwizzle.setSelectedIndex(getCustomAMapSwizzle());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        CustomAMapSwizzle.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CustomAMapSwizzleItemStateChanged(evt);
            }
        });
        CustomAMapSwizzle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomAMapSwizzleActionPerformed(evt);
            }
        });

        jLabel5.setText("<html><b>CUSTOM A MAP APPLY SCALE</b></html>");

        CustomAMapApplyScale.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2" }));
        try{
            CustomAMapApplyScale.setSelectedIndex(getCustomAMapApplyScale());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        CustomAMapApplyScale.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CustomAMapApplyScaleItemStateChanged(evt);
            }
        });
        CustomAMapApplyScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomAMapApplyScaleActionPerformed(evt);
            }
        });

        jLabel7.setText("<html><b>CUSTOM ENABLE</b></html>");

        CustomAEnable.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }));
        try{
            CustomAEnable.setSelectedIndex(getCustomAEnable());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        CustomAEnable.setToolTipText("");
        CustomAEnable.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CustomAEnableItemStateChanged(evt);
            }
        });
        CustomAEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomAEnableActionPerformed(evt);
            }
        });

        AlbedoMapUVFlipHelp1.setText("Help");
        AlbedoMapUVFlipHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapUVFlipHelp1ActionPerformed(evt);
            }
        });

        AlbedoMapUVFlipHelp2.setText("Help");
        AlbedoMapUVFlipHelp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapUVFlipHelp2ActionPerformed(evt);
            }
        });

        AlbedoMapUVFlipHelp4.setText("Help");
        AlbedoMapUVFlipHelp4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapUVFlipHelp4ActionPerformed(evt);
            }
        });

        jLabel10.setText("<html><b>CUSTOM B MAP COLOR FLIP</b></html>");

        CustomBMapUVFlip.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        try{
            CustomBMapUVFlip.setSelectedIndex(getCustomBMapUVFlip());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        CustomBMapUVFlip.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CustomBMapUVFlipItemStateChanged(evt);
            }
        });
        CustomBMapUVFlip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomBMapUVFlipActionPerformed(evt);
            }
        });

        CustomBMapColorFlip.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        try{
            CustomBMapColorFlip.setSelectedIndex(getCustomBMapColorFlip());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        CustomBMapColorFlip.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CustomBMapColorFlipItemStateChanged(evt);
            }
        });
        CustomBMapColorFlip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomBMapColorFlipActionPerformed(evt);
            }
        });

        AlbedoMapHelp1.setText("Help");
        AlbedoMapHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapHelp1ActionPerformed(evt);
            }
        });

        AlbedoMapUVFlipHelp6.setText("Help");
        AlbedoMapUVFlipHelp6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapUVFlipHelp6ActionPerformed(evt);
            }
        });

        AlbedoMapFileHelp1.setText("Help");
        AlbedoMapFileHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbedoMapFileHelp1ActionPerformed(evt);
            }
        });

        jLabel12.setText("<html><b>CUSTOM B MAP FROM</b></html>");

        CustomBMapFrom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8" }));
        try{
            CustomBMapFrom.setSelectedIndex(getCustomBMapFrom());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        CustomBMapFrom.setToolTipText("");
        CustomBMapFrom.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CustomBMapFromItemStateChanged(evt);
            }
        });
        CustomBMapFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomBMapFromActionPerformed(evt);
            }
        });

        jLabel13.setText("<html><b>CUSTOM B MAP UV FLIP</b></html>");

        jLabel14.setText("<html><b>CUSTOM B MAP FILE</b></html>");

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

        jLabel17.setText("<html><b>CUSTOM B MAP APPLY SCALE</b></html>");

        CustomBMapApplyScale.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2" }));
        try{
            CustomBMapApplyScale.setSelectedIndex(getCustomBMapApplyScale());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }
        CustomBMapApplyScale.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CustomBMapApplyScaleItemStateChanged(evt);
            }
        });
        CustomBMapApplyScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomBMapApplyScaleActionPerformed(evt);
            }
        });

        try{
            CustomAMapFile.setEditable(false);
            CustomAMapFile.setText(getCustomAMapFile());
        }catch(Exception e){
            errors+=1;
            SomethingWentWrong();
        }

        try{
            CustomBMapFile.setEditable(false);
            CustomBMapFile.setText(getCustomBMapFile());
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
                        .addComponent(CustomAMapFile, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(AlbedoMapLoopHelp))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(albedoHelp)
                                    .addGap(44, 44, 44)))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(106, 106, 106)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(CustomAMapFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CustomAMapUVFlip, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CustomAMapColorFlip, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CustomAMapSwizzle, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CustomAMapApplyScale, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CustomAEnable, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(changeFile, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(AlbedoMapUVFlipHelp)
                            .addComponent(AlbedoMapHelp)
                            .addComponent(AlbedoMapUVFlipHelp1)
                            .addComponent(AlbedoMapUVFlipHelp2)
                            .addComponent(AlbedoMapFileHelp))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(CustomBMapApplyScale, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(106, 106, 106)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(CustomBMapFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CustomBMapUVFlip, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CustomBMapColorFlip, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(changeFile1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AlbedoMapUVFlipHelp4)
                                    .addComponent(AlbedoMapHelp1)
                                    .addComponent(AlbedoMapUVFlipHelp6)
                                    .addComponent(AlbedoMapFileHelp1))
                                .addContainerGap(92, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(CustomBMapFile, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CustomAEnable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CustomBMapFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapHelp1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CustomAMapFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapHelp)
                    .addComponent(jLabel13)
                    .addComponent(CustomBMapUVFlip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapUVFlipHelp6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CustomAMapUVFlip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(AlbedoMapUVFlipHelp)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CustomBMapColorFlip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CustomAMapColorFlip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CustomBMapApplyScale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapUVFlipHelp4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CustomAMapSwizzle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapUVFlipHelp1)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeFile1)
                    .addComponent(AlbedoMapFileHelp1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CustomAMapApplyScale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapUVFlipHelp2)
                    .addComponent(CustomBMapFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapFileHelp)
                    .addComponent(changeFile))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CustomAMapFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(albedoHelp))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbedoMapLoopHelp))
                .addGap(47, 47, 47))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CustomAMapFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomAMapFromActionPerformed

    }//GEN-LAST:event_CustomAMapFromActionPerformed

    private void CustomAMapUVFlipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomAMapUVFlipActionPerformed

    }//GEN-LAST:event_CustomAMapUVFlipActionPerformed

    private void changeFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeFileActionPerformed

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new java.io.File("../../Materials"));//The directory we are looking for is the Materials folder from ray
        fileChooser.setDialogTitle("Choose new CustomA Map File");
        //Filtering by ImageFiles
        FileFilter imageFilter = new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp", "tga", "targa", "dds", "tif", "tiff", "jpeg", "pcd");
        fileChooser.setFileFilter(imageFilter);

        try {
            int selection = fileChooser.showOpenDialog(this);
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            filePath = filePath.replace("\\", "/");

            if (selection == 0) {
                String relative = toRelative.convertToRelativePath(foo.getFileToEdit().getParent(), filePath);
                CustomAMapFile.setText(relative);

                if (CustomAMapFrom.getSelectedIndex() != 1) {
                    CustomAMapFrom.setSelectedIndex(1);
                }

                try {
                    BufferedReader br = null;
                    br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                    String oldtext = "";
                    String line = br.readLine();
                    String catchOld = "";
                    String catchNew = "";
                    while (line != null) {
                        if (line.contains("#define CUSTOM_A_MAP_FILE")) {
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
                            if (!catchOld.equalsIgnoreCase(CustomAMapFile.getText())) {
                                catchNew = CustomAMapFile.getText(); //catchnewdigit
                                line = "#define CUSTOM_A_MAP_FILE " + '"' + catchNew + '"';
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
    }//GEN-LAST:event_backActionPerformed

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
                + "    <li><strike>9 : Params fetch from Specular Power from the pmx. (this option can only be used for specular)</strike>, doesn't work on Custom</li></ul></pre><br><br></HTML>");
        AlbedoMapHelp.setLayout(new BorderLayout());
        AlbedoMapHelp.setSize(700, 550);
        AlbedoMapHelp.setLocationRelativeTo(this);
        AlbedoMapHelp.setResizable(true);
        AlbedoMapHelp.setVisible(true);
        AlbedoMapHelp.add(AlbedoMapHelpText);
        AlbedoMapHelp.setTitle("Custom A Map From Help");

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
        help.setTitle("Custom A Map UV Flip Help");

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
        help.setTitle("Custom A Map File Help");

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

        helptext.setText("<HTML>working in linear color-space, All between 0 ~ 1</HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(400, 70);
        help.setLocationRelativeTo(this);
        help.setResizable(true);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Custom A Map Scale Help");

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

        helptext.setText("<HTML>You can tile your texture for the X and Y axis separately by change albedoMapLoopNum = float2(x, y) between float2(0, 0) ~ float2(inf, inf) </HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(600, 200);
        help.setLocationRelativeTo(this);
        help.setResizable(true);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Custom A Map Loop Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }//GEN-LAST:event_AlbedoMapLoopHelpActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        helptext.setText("<HTML>Shading Material ID<br>"
                + "The curvature also called 'opacity'<br><br><br>"
                + ""
                + "<ul>"
                + "<li><b>0 : Default</b>            customA = invalid,    customB = invalid</li>"
                + "<li><b>1 : PreIntegrated Skin</b>   customA = curvature,  customB = transmittance color;</li>"
                + "<li><b>2 : Unlit placeholder</b>    customA = invalid,    customB = invalid</li>"
                + "<li><b>3 : Reserved</b>  </li>"
                + "</ul><br><br>"
                + "In order to make refraction work, you must set alpha value of the pmx model to less then 0.999<br>"
                + "<ul><li><b>4 : Glass </b>               customA = curvature   customB = transmittance color</li></ul><br><br>"
                + ""
                + ""
                + "<ul><li><b>5 : Cloth </b>               customA = sheen,      customB = Fuzz Color</li></ul><br>"
                + ""
                + "<ul><li><b>6 : Clear Coat </b>          customA = smoothness, customB = invalid;</li>"
                + "<li><b>7 : Subsurface</b>           customA = curvature,  customB = transmittance color;</li></ul></HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(600, 387);
        help.setLocationRelativeTo(this);
        help.setResizable(true);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Custom Enable Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void CustomAMapColorFlipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomAMapColorFlipActionPerformed

    }//GEN-LAST:event_CustomAMapColorFlipActionPerformed

    private void CustomAMapSwizzleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomAMapSwizzleActionPerformed

    }//GEN-LAST:event_CustomAMapSwizzleActionPerformed

    private void CustomAMapApplyScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomAMapApplyScaleActionPerformed

    }//GEN-LAST:event_CustomAMapApplyScaleActionPerformed

    private void CustomAEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomAEnableActionPerformed

    }//GEN-LAST:event_CustomAEnableActionPerformed

    private void AlbedoMapUVFlipHelp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapUVFlipHelp1ActionPerformed
        JFrame help = new JFrame();
        JLabel helptext = new JLabel();
        help.setName("help");
        helptext.setText("<HTML>The ordering of the data fetched from a texture from the code. (R = 0, G = 1, B = 2, A = 3)</HTML>");
        help.setLayout(new BorderLayout());
        help.setSize(600, 387);
        help.setLocationRelativeTo(this);
        help.setResizable(true);
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Custom A Map Swizzle Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }//GEN-LAST:event_AlbedoMapUVFlipHelp1ActionPerformed

    private void AlbedoMapUVFlipHelp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapUVFlipHelp2ActionPerformed

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
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setResizable(true);
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Custom A Map Apply Scale Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }//GEN-LAST:event_AlbedoMapUVFlipHelp2ActionPerformed

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
        helptext.setBorder(new EmptyBorder(10, 20, 10, 10));
        help.setResizable(true);
        help.setVisible(true);
        help.add(helptext);
        help.setTitle("Custom A Map Color Flip Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }//GEN-LAST:event_AlbedoMapUVFlipHelp4ActionPerformed

    private void CustomBMapUVFlipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomBMapUVFlipActionPerformed

    }//GEN-LAST:event_CustomBMapUVFlipActionPerformed

    private void CustomBMapColorFlipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomBMapColorFlipActionPerformed

    }//GEN-LAST:event_CustomBMapColorFlipActionPerformed

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
                + "    <li>8 : Params fetch from Specular Color from the pmx.</li><br>"
                + "    <li><strike>9 : Params fetch from Specular Power from the pmx. (this option can only be used for specular)</strike>, doesn't work on Custom</li></ul></pre><br><br></HTML>");
        AlbedoMapHelp.setLayout(new BorderLayout());
        AlbedoMapHelp.setSize(700, 550);
        AlbedoMapHelp.setLocationRelativeTo(this);
        AlbedoMapHelp.setResizable(true);
        AlbedoMapHelp.setVisible(true);
        AlbedoMapHelp.add(AlbedoMapHelpText);
        AlbedoMapHelp.setTitle("Custom B Map From Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            AlbedoMapHelp.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }//GEN-LAST:event_AlbedoMapHelp1ActionPerformed

    private void AlbedoMapUVFlipHelp6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbedoMapUVFlipHelp6ActionPerformed

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
        help.setTitle("Custom B Map UV Flip Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }//GEN-LAST:event_AlbedoMapUVFlipHelp6ActionPerformed

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
        help.setTitle("Custom B Map File Help");

        try {
            InputStream imgStream = getClass().getResourceAsStream("/icon/ico.png");
            BufferedImage myImg = ImageIO.read(imgStream);
            help.setIconImage(myImg);
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }//GEN-LAST:event_AlbedoMapFileHelp1ActionPerformed

    private void CustomBMapFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomBMapFromActionPerformed

    }//GEN-LAST:event_CustomBMapFromActionPerformed

    private void changeFile1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_changeFile1StateChanged

    }//GEN-LAST:event_changeFile1StateChanged

    private void changeFile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeFile1ActionPerformed

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new java.io.File("../../Materials"));//The directory we are looking for is the Materials folder from ray
        fileChooser.setDialogTitle("Choose new CustomB Map File");
        //Filtering by ImageFiles
        FileFilter imageFilter = new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp", "tga", "targa", "dds", "tif", "tiff", "jpeg", "pcd");
        fileChooser.setFileFilter(imageFilter);

        try {
            int selection = fileChooser.showOpenDialog(this);
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            filePath = filePath.replace("\\", "/");

            if (selection == 0) {
                String relative = toRelative.convertToRelativePath(foo.getFileToEdit().getParent(), filePath);
                CustomBMapFile.setText(relative);
                if (CustomBMapFrom.getSelectedIndex() != 1) {
                    CustomBMapFrom.setSelectedIndex(1);
                }

                try {
                    BufferedReader br = null;
                    br = new BufferedReader(new FileReader(foo.getFileToEdit()));
                    String oldtext = "";
                    String line = br.readLine();
                    String catchOld = "";
                    String catchNew = "";
                    while (line != null) {
                        if (line.contains("#define CUSTOM_B_MAP_FILE")) {
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
                            if (!catchOld.equalsIgnoreCase(CustomBMapFile.getText())) {
                                catchNew = CustomBMapFile.getText(); //catchnewdigit
                                line = "#define CUSTOM_B_MAP_FILE " + '"' + catchNew + '"';
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

    private void CustomBMapApplyScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomBMapApplyScaleActionPerformed

    }//GEN-LAST:event_CustomBMapApplyScaleActionPerformed

    private void CustomAEnableItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CustomAEnableItemStateChanged
        // TODO add your handling code here:
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define CUSTOM_ENABLE")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = CustomAEnable.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_CustomAEnableItemStateChanged

    private void CustomAMapFromItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CustomAMapFromItemStateChanged
        // TODO add your handling code here:
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define CUSTOM_A_MAP_FROM")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = CustomAMapFrom.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_CustomAMapFromItemStateChanged

    private void CustomAMapUVFlipItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CustomAMapUVFlipItemStateChanged
        // TODO add your handling code here:
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define CUSTOM_A_MAP_UV_FLIP")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = CustomAMapUVFlip.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_CustomAMapUVFlipItemStateChanged

    private void CustomAMapColorFlipItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CustomAMapColorFlipItemStateChanged
        // TODO add your handling code here:
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define CUSTOM_A_MAP_COLOR_FLIP")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = CustomAMapColorFlip.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_CustomAMapColorFlipItemStateChanged

    private void CustomAMapSwizzleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CustomAMapSwizzleItemStateChanged
        // TODO add your handling code here:
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define CUSTOM_A_MAP_SWIZZLE")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = CustomAMapSwizzle.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_CustomAMapSwizzleItemStateChanged

    private void CustomAMapApplyScaleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CustomAMapApplyScaleItemStateChanged
        // TODO add your handling code here:
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define CUSTOM_A_MAP_APPLY_SCALE")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = CustomAMapApplyScale.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_CustomAMapApplyScaleItemStateChanged

    private void CustomBMapFromItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CustomBMapFromItemStateChanged
        // TODO add your handling code here:
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define CUSTOM_B_MAP_FROM")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = CustomBMapFrom.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_CustomBMapFromItemStateChanged

    private void CustomBMapUVFlipItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CustomBMapUVFlipItemStateChanged
        // TODO add your handling code here:
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define CUSTOM_B_MAP_UV_FLIP")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = CustomBMapUVFlip.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_CustomBMapUVFlipItemStateChanged

    private void CustomBMapColorFlipItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CustomBMapColorFlipItemStateChanged
        // TODO add your handling code here:
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define CUSTOM_B_MAP_COLOR_FLIP")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = CustomBMapColorFlip.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_CustomBMapColorFlipItemStateChanged

    private void CustomBMapApplyScaleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CustomBMapApplyScaleItemStateChanged
        // TODO add your handling code here:
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(foo.getFileToEdit()));
            String oldtext = "";
            String line = br.readLine();
            String catchOld = "";
            String catchNew = "";
            while (line != null) {
                if (line.contains("#define CUSTOM_B_MAP_APPLY_SCALE")) {
                    catchOld = line; //auxiliar line
                    catchOld = catchOld.replaceAll("\\D+", ""); //extract old digit
                    catchNew = CustomBMapApplyScale.getSelectedItem().toString(); //catchnewdigit
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
    }//GEN-LAST:event_CustomBMapApplyScaleItemStateChanged
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
        previewImg(CustomAMapFile.getText());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        previewImg(CustomBMapFile.getText());
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
            java.util.logging.Logger.getLogger(WindowFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WindowFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WindowFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WindowFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton AlbedoMapLoopHelp;
    private javax.swing.JButton AlbedoMapUVFlipHelp;
    private javax.swing.JButton AlbedoMapUVFlipHelp1;
    private javax.swing.JButton AlbedoMapUVFlipHelp2;
    private javax.swing.JButton AlbedoMapUVFlipHelp4;
    private javax.swing.JButton AlbedoMapUVFlipHelp6;
    private javax.swing.JComboBox<String> CustomAEnable;
    private javax.swing.JComboBox<String> CustomAMapApplyScale;
    private javax.swing.JComboBox<String> CustomAMapColorFlip;
    private javax.swing.JTextField CustomAMapFile;
    private javax.swing.JComboBox<String> CustomAMapFrom;
    private javax.swing.JComboBox<String> CustomAMapSwizzle;
    private javax.swing.JComboBox<String> CustomAMapUVFlip;
    private javax.swing.JComboBox<String> CustomBMapApplyScale;
    private javax.swing.JComboBox<String> CustomBMapColorFlip;
    private javax.swing.JTextField CustomBMapFile;
    private javax.swing.JComboBox<String> CustomBMapFrom;
    private javax.swing.JComboBox<String> CustomBMapUVFlip;
    private javax.swing.JButton albedoHelp;
    private javax.swing.JButton back;
    private javax.swing.JButton changeFile;
    private javax.swing.JButton changeFile1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    // End of variables declaration//GEN-END:variables
}
