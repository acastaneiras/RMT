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

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MaterialMakerv2 {

    private static File fileToEdit;
    private static String filePath;
    private static String fileOldPath;

    MaterialMakerv2() {
        File fileToEdit;
        String filePath;
    }

    public File getFileToEdit() {
        return this.fileToEdit;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public static void main(String[] args) throws IOException, Exception {
        Windows();
        menu();
    }

    public static void menu() throws Exception {

        ImageIcon ico = new ImageIcon(MaterialMakerv2.class.getResource("/icon/ico.png"));
        Menu a = new Menu();
        a.setBounds(0, 0, 680, 295);
        a.setResizable(false);
        a.setLocationRelativeTo(null);
        a.setDefaultCloseOperation(EXIT_ON_CLOSE);
        a.setLayout(new BorderLayout());
        a.setIconImage(ico.getImage());
        a.setVisible(true);
    }

    public static void callMenu() {
        ImageIcon ico = new ImageIcon(MaterialMakerv2.class.getResource("/icon/ico.png"));
        Menu a = new Menu();
        a.setBounds(0, 0, 680, 295);
        a.setResizable(false);
        a.setLocationRelativeTo(null);
        a.setDefaultCloseOperation(EXIT_ON_CLOSE);
        a.setLayout(new BorderLayout());
        a.setIconImage(ico.getImage());
        a.setVisible(true);
    }

    public static void editMaterial(Component a) {

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new java.io.File("../../Materials"));
        fileChooser.setDialogTitle("Choose the Material you want to edit");
        FileFilter ray = new FileNameExtensionFilter("Material Files", "fx");
        fileChooser.setFileFilter(ray);

        int selection = 1;

        try {
            selection = fileChooser.showOpenDialog(a);
            filePath = fileChooser.getSelectedFile().getAbsolutePath();
            fileToEdit = new File(filePath);
        } catch (Exception e) {

        }
        if (selection == 0) {
            try {
                a.setVisible(false);
                WindowFrame w = new WindowFrame();
                w.setAlwaysOnTop(true);
                w.setAlwaysOnTop(false);
                ImageIcon img = new ImageIcon(MaterialMakerv2.class.getResource("/icon/ico.png"));
                w.setResizable(false);
                w.setBounds(0, 0, 960, 549);
                w.setLocationRelativeTo(null);
                w.setLayout(new BorderLayout());
                w.setDefaultCloseOperation(WindowFrame.DO_NOTHING_ON_CLOSE);
                w.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent we) {
                        callMenu();
                        w.dispose();
                    }
                });
                w.setIconImage(img.getImage());
                w.setVisible(true);
            } catch (Exception e) {

            }
        } else {

        }

    }

    public static void FromExisting(Component a) {
        String filePath0 = null;

        try {

            String catchContent = "";
            ImageIcon img = new ImageIcon(MaterialMakerv2.class.getResource("/icon/icosmall.png"));
            String newName;
            Matcher matcher;
            do {
                newName = (String) JOptionPane.showInputDialog(a, "<html>New material name (<B>WITHOUT EXTENSION</B>)</html>", "Create material from existing", EXIT_ON_CLOSE, img, null, null);
                Pattern pattern = Pattern.compile("\\p{Alnum}+");
                matcher = pattern.matcher(newName);
            } while (!matcher.matches() || (newName.contains(".fx") || (newName.equalsIgnoreCase("fx"))));

            if (newName != null) {

                newName += ".fx";
                //This entire method is a nightmare, don't even bother to read it.
                JFileChooser fileChooser = new JFileChooser();

                fileChooser.setCurrentDirectory(new java.io.File("../../Materials"));
                fileChooser.setDialogTitle("Choose an existing material");
                FileFilter ray = new FileNameExtensionFilter("Material Files", "fx");
                fileChooser.setFileFilter(ray);

                int seleccion = fileChooser.showOpenDialog(a);
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    filePath0 = fileChooser.getSelectedFile().getAbsolutePath();

                    FileReader fr = new FileReader(filePath0);
                    BufferedReader br = new BufferedReader(fr);
                    String s = br.readLine();

                    while (s != null) {
                        catchContent += s + "\r\n";
                        s = br.readLine();
                    }
                    br.close();
                    int input = JOptionPane.showConfirmDialog(a,
                            "<html>Would you like to specify the destination <b>folder</b> of the new material?<br><br>"
                            + "Note that If you click <i>'No'</i> the new material will be created at the same folder as the selected one.</html>", "Destination Folder",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, img);

                    switch (input) {
                        case 0:
                            //I would like to specify
                            JFileChooser chooser;
                            String choosertitle = null;
                            chooser = new JFileChooser();
                            chooser.setCurrentDirectory(new java.io.File("../../Materials"));
                            chooser.setDialogTitle(choosertitle);
                            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                            chooser.setAcceptAllFileFilterUsed(false);
                            if (chooser.showOpenDialog(a) == JFileChooser.APPROVE_OPTION) {
                                filePath = chooser.getSelectedFile().toString();
                                fileOldPath = filePath;
                                filePath += "\\" + newName;
                                fileToEdit = new File(filePath);
                                Object[] options = {"Overwrite file", "Change name", "Go to menu"};
                                if (fileToEdit.exists() && !fileToEdit.isDirectory()) {
                                    int whenExists = JOptionPane.showOptionDialog(a, "<html>It looks like a material with that exact name (<i>" + newName + "</i>), already exists.<br><br>" + "Would you like whether to <b>overwrite</b> the selected file or <b>change the name </b> of the material?</html>",
                                            "Material already exists", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, img, options, "Change name");
                                    switch (whenExists) {
                                        case 0: {
                                            //Overwrite
                                            File check = new File((fileToEdit.getParent() + "\\material_common_2.0.fxsub"));
                                            if (!check.exists()) {//If materialcommon doesn't exist in the folder.
                                                copyFile(new File("../../Materials/material_common_2.0.fxsub"), check);
                                            }
                                            FileWriter fw = new FileWriter(filePath);
                                            fw.write(catchContent);
                                            fw.close();
                                            try {
                                                a.setVisible(false);

                                                WindowFrame w = new WindowFrame();
                                                w.setAlwaysOnTop(true);
                                                w.setAlwaysOnTop(false);
                                                w.setResizable(false);
                                                w.setBounds(0, 0, 960, 549);
                                                w.setLocationRelativeTo(null);
                                                w.setLayout(new BorderLayout());
                                                w.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

                                                w.addWindowListener(new WindowAdapter() {
                                                    @Override
                                                    public void windowClosing(WindowEvent we) {
                                                        callMenu();
                                                        w.dispose();
                                                    }
                                                });
                                                ImageIcon imge = new ImageIcon(MaterialMakerv2.class.getResource("/icon/ico.png"));
                                                w.setIconImage(imge.getImage());
                                                w.setVisible(true);
                                            } catch (Exception e) {

                                            }
                                            break;
                                        }
                                        case 1: {
                                            //Change Name
                                            do {
                                                newName = (String) JOptionPane.showInputDialog(a, "<html>New material name (<B>WITHOUT EXTENSION</B>)</html>", "Create material from Default", EXIT_ON_CLOSE, img, null, null);
                                                Pattern pattern = Pattern.compile("\\p{Alnum}+");//Only validates numbers and letters
                                                matcher = pattern.matcher(newName);

                                                filePath = fileOldPath + "\\" + newName + ".fx";
                                                fileToEdit = new File(filePath);
                                                if (fileToEdit.exists()) {
                                                    newName = "fuck you.fx";
                                                }
                                            } while (!matcher.matches() || (newName.contains(".fx") || (newName.equalsIgnoreCase("fx"))));
                                            File check = new File((fileToEdit.getParent() + "\\material_common_2.0.fxsub"));
                                            if (!check.exists()) {//If materialcommon doesn't exist in the folder.
                                                copyFile(new File("../../Materials/material_common_2.0.fxsub"), check);
                                            }
                                            FileWriter fw = new FileWriter(filePath);
                                            fw.write(catchContent);
                                            fw.close();
                                            try {
                                                a.setVisible(false);
                                                WindowFrame w = new WindowFrame();
                                                w.setAlwaysOnTop(true);
                                                w.setAlwaysOnTop(false);
                                                w.setResizable(false);
                                                w.setBounds(0, 0, 960, 549);
                                                w.setLocationRelativeTo(null);
                                                w.setLayout(new BorderLayout());
                                                w.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

                                                w.addWindowListener(new WindowAdapter() {
                                                    public void windowClosing(WindowEvent we) {
                                                        callMenu();
                                                        w.dispose();
                                                    }
                                                });
                                                ImageIcon imge = new ImageIcon(MaterialMakerv2.class.getResource("/icon/ico.png"));
                                                w.setIconImage(imge.getImage());
                                                w.setVisible(true);
                                            } catch (Exception e) {

                                            }
                                            break;
                                        }
                                        default:
                                            break;
                                    }
                                } else {
                                    File check = new File((fileToEdit.getParent() + "\\material_common_2.0.fxsub"));
                                    if (!check.exists()) {//If materialcommon doesn't exist in the folder.
                                        copyFile(new File("../../Materials/material_common_2.0.fxsub"), check);
                                    }
                                    FileWriter fw = new FileWriter(filePath);

                                    fw.write(catchContent);
                                    fw.close();

                                    try {
                                        a.setVisible(false);
                                        WindowFrame w = new WindowFrame();
                                        w.setAlwaysOnTop(true);
                                        w.setAlwaysOnTop(false);
                                        w.setResizable(false);
                                        w.setBounds(0, 0, 960, 549);
                                        w.setLocationRelativeTo(null);
                                        w.setLayout(new BorderLayout());
                                        w.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                                        w.addWindowListener(new WindowAdapter() {
                                            public void windowClosing(WindowEvent we) {
                                                callMenu();
                                                w.dispose();
                                            }
                                        });
                                        ImageIcon imge = new ImageIcon(MaterialMakerv2.class.getResource("/icon/ico.png"));
                                        w.setIconImage(imge.getImage());
                                        w.setVisible(true);
                                    } catch (Exception e) {

                                    }
                                }
                            } else {
                            }
                            break;
                        case 1:
                            //I would not like to specify

                            filePath = fileChooser.getSelectedFile().getParent();
                            fileOldPath = filePath;
                            filePath += "\\" + newName;
                            fileToEdit = new File(filePath);
                            Object[] options = {"Overwrite file", "Change name", "Go to menu"};
                            if (fileToEdit.exists() && !fileToEdit.isDirectory()) {
                                int whenExists = JOptionPane.showOptionDialog(a, "<html>It looks like a material with that exact name (<i>" + newName + "</i>), already exists.<br><br>" + "Would you like whether to <b>overwrite</b> the selected file or <b>change the name </b> of the material?</html>",
                                        "Material already exists", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, img, options, "Change name");
                                switch (whenExists) {
                                    case 0: {
                                        //Overwrite
                                        File check = new File((fileToEdit.getParent() + "\\material_common_2.0.fxsub"));
                                        if (!check.exists()) {//If materialcommon doesn't exist in the folder.
                                            copyFile(new File("../../Materials/material_common_2.0.fxsub"), check);
                                        }
                                        FileWriter fw = new FileWriter(filePath);
                                        fw.write(catchContent);
                                        fw.close();
                                        try {
                                            a.setVisible(false);

                                            WindowFrame w = new WindowFrame();
                                            w.setAlwaysOnTop(true);
                                            w.setAlwaysOnTop(false);
                                            w.setResizable(false);
                                            w.setBounds(0, 0, 960, 549);
                                            w.setLocationRelativeTo(null);
                                            w.setLayout(new BorderLayout());
                                            w.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

                                            w.addWindowListener(new WindowAdapter() {
                                                @Override
                                                public void windowClosing(WindowEvent we) {
                                                    callMenu();
                                                    w.dispose();
                                                }
                                            });
                                            ImageIcon imge = new ImageIcon(MaterialMakerv2.class.getResource("/icon/ico.png"));
                                            w.setIconImage(imge.getImage());
                                            w.setVisible(true);
                                        } catch (Exception e) {

                                        }
                                        break;
                                    }
                                    case 1: {
                                        //Change Name
                                        do {
                                            newName = (String) JOptionPane.showInputDialog(a, "<html>New material name (<B>WITHOUT EXTENSION</B>)</html>", "Create material from Default", EXIT_ON_CLOSE, img, null, null);
                                            Pattern pattern = Pattern.compile("\\p{Alnum}+");
                                            matcher = pattern.matcher(newName);

                                            filePath = fileOldPath + "\\" + newName + ".fx";
                                            fileToEdit = new File(filePath);
                                            if (fileToEdit.exists()) {
                                                newName = ".fx";
                                            }
                                        } while (!matcher.matches() || (newName.contains(".fx") || (newName.equalsIgnoreCase("fx"))));
                                        File check = new File((fileToEdit.getParent() + "\\material_common_2.0.fxsub"));
                                        if (!check.exists()) {//If materialcommon doesn't exist in the folder.
                                            copyFile(new File("../../Materials/material_common_2.0.fxsub"), check);
                                        }
                                        FileWriter fw = new FileWriter(filePath);
                                        fw.write(catchContent);
                                        fw.close();
                                        try {
                                            a.setVisible(false);
                                            WindowFrame w = new WindowFrame();
                                            w.setAlwaysOnTop(true);
                                            w.setAlwaysOnTop(false);
                                            w.setResizable(false);
                                            w.setBounds(0, 0, 960, 549);
                                            w.setLocationRelativeTo(null);
                                            w.setLayout(new BorderLayout());
                                            w.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

                                            w.addWindowListener(new WindowAdapter() {
                                                public void windowClosing(WindowEvent we) {
                                                    callMenu();
                                                    w.dispose();
                                                }
                                            });
                                            ImageIcon imge = new ImageIcon(MaterialMakerv2.class.getResource("/icon/ico.png"));
                                            w.setIconImage(imge.getImage());
                                            w.setVisible(true);
                                        } catch (Exception e) {

                                        }
                                        break;
                                    }
                                    default:
                                        break;
                                }
                            } else {
                                File check = new File((fileToEdit.getParent() + "\\material_common_2.0.fxsub"));
                                if (!check.exists()) {//If materialcommon doesn't exist in the folder.
                                    copyFile(new File("../../Materials/material_common_2.0.fxsub"), check);
                                }
                                FileWriter fw = new FileWriter(filePath);

                                fw.write(catchContent);
                                fw.close();

                                try {
                                    a.setVisible(false);
                                    WindowFrame w = new WindowFrame();
                                    w.setAlwaysOnTop(true);
                                    w.setAlwaysOnTop(false);
                                    w.setResizable(false);
                                    w.setBounds(0, 0, 960, 549);
                                    w.setLocationRelativeTo(null);
                                    w.setLayout(new BorderLayout());
                                    w.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                                    w.addWindowListener(new WindowAdapter() {
                                        public void windowClosing(WindowEvent we) {
                                            callMenu();
                                            w.dispose();
                                        }
                                    });
                                    ImageIcon imge = new ImageIcon(MaterialMakerv2.class.getResource("/icon/ico.png"));
                                    w.setIconImage(imge.getImage());
                                    w.setVisible(true);
                                } catch (Exception e) {

                                }
                            }
                            break;
                        default:
                            break;
                    }

                } else {

                }
            } else {

            }

        } catch (Exception e) {

        }

    }

    static boolean isImage(String image_path) {
        try {
            File f = new File(image_path);
            return f.exists();

        } catch (Exception ex) {
            return false;
        }
    }

    private static void copyFile(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    public static void FromDefault(Component a) {

        try {

            String catchContent = "";

            ImageIcon img = new ImageIcon(MaterialMakerv2.class.getResource("/icon/ico.png"));
            String newName;
            Matcher matcher;
            do {
                newName = (String) JOptionPane.showInputDialog(a, "<html>New material name (<B>WITHOUT EXTENSION</B>)</html>", "Create material from Default", EXIT_ON_CLOSE, img, null, null);
                Pattern pattern = Pattern.compile("\\p{Alnum}+");
                matcher = pattern.matcher(newName);
            } while (!matcher.matches() || (newName.contains(".fx") || (newName.equalsIgnoreCase("fx"))));

            if (newName != null) {

                newName += ".fx";

                InputStream inputStream = Main.class.getResourceAsStream("/Default/material_2.0.fx");
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                BufferedReader br = new BufferedReader(inputStreamReader);
                String s = br.readLine();

                while (s != null) {
                    catchContent += s + "\r\n";
                    s = br.readLine();
                }
                br.close();
                int input = JOptionPane.showConfirmDialog(a,
                        "<html>Would you like to specify the destination <b>folder</b> of the new material?<br><br>"
                        + "Note that If you click <i>'No'</i> the new material will be created at the <i>/Materials</i> folder.</html>", "Destination Folder",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, img);

                switch (input) {
                    case 0:
                        JFileChooser chooser;
                        String choosertitle = null;
                        chooser = new JFileChooser();
                        chooser.setCurrentDirectory(new java.io.File("../../Materials"));
                        chooser.setDialogTitle(choosertitle);
                        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        chooser.setAcceptAllFileFilterUsed(false);
                        if (chooser.showOpenDialog(a) == JFileChooser.APPROVE_OPTION) {
                            filePath = chooser.getSelectedFile().toString();
                            fileOldPath = filePath;
                            filePath += "\\" + newName;
                            fileToEdit = new File(filePath);
                            Object[] options = {"Overwrite file", "Change name", "Go to menu"};
                            if (fileToEdit.exists() && !fileToEdit.isDirectory()) {
                                int whenExists = JOptionPane.showOptionDialog(a, "<html>It looks like a material with that exact name (<i>" + newName + "</i>), already exists.<br><br>" + "Would you like whether to <b>overwrite</b> the selected file or <b>change the name </b> of the material?</html>",
                                        "Material already exists", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, img, options, "Change name");
                                switch (whenExists) {
                                    case 0: {
                                        //Overwrite
                                        File check = new File((fileToEdit.getParent() + "\\material_common_2.0.fxsub"));
                                        if (!check.exists()) {//If materialcommon doesn't exist in the folder.
                                            copyFile(new File("../../Materials/material_common_2.0.fxsub"), check);
                                        }
                                        FileWriter fw = new FileWriter(filePath);
                                        fw.write(catchContent);
                                        fw.close();
                                        try {
                                            a.setVisible(false);

                                            WindowFrame w = new WindowFrame();
                                            w.setAlwaysOnTop(true);
                                            w.setAlwaysOnTop(false);
                                            w.setResizable(false);
                                            w.setBounds(0, 0, 960, 549);
                                            w.setLocationRelativeTo(null);
                                            w.setLayout(new BorderLayout());
                                            w.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

                                            w.addWindowListener(new WindowAdapter() {
                                                @Override
                                                public void windowClosing(WindowEvent we) {
                                                    callMenu();
                                                    w.dispose();
                                                }
                                            });
                                            ImageIcon imge = new ImageIcon(MaterialMakerv2.class.getResource("/icon/ico.png"));
                                            w.setIconImage(imge.getImage());
                                            w.setVisible(true);
                                        } catch (Exception e) {

                                        }
                                        break;
                                    }
                                    case 1: {
                                        //Change Name
                                        do {
                                            newName = (String) JOptionPane.showInputDialog(a, "<html>New material name (<B>WITHOUT EXTENSION</B>)</html>", "Create material from Default", EXIT_ON_CLOSE, img, null, null);
                                            Pattern pattern = Pattern.compile("\\p{Alnum}+");//Only validates numbers and letters
                                            matcher = pattern.matcher(newName);

                                            filePath = fileOldPath + "\\" + newName + ".fx";
                                            fileToEdit = new File(filePath);
                                            if (fileToEdit.exists()) {
                                                newName = ".fx";
                                            }
                                        } while (!matcher.matches() || (newName.contains(".fx") || (newName.equalsIgnoreCase("fx"))));
                                        File check = new File((fileToEdit.getParent() + "\\material_common_2.0.fxsub"));
                                        if (!check.exists()) {//If materialcommon doesn't exist in the folder.
                                            copyFile(new File("../../Materials/material_common_2.0.fxsub"), check);
                                        }
                                        FileWriter fw = new FileWriter(filePath);
                                        fw.write(catchContent);
                                        fw.close();
                                        try {
                                            a.setVisible(false);
                                            WindowFrame w = new WindowFrame();
                                            w.setAlwaysOnTop(true);
                                            w.setAlwaysOnTop(false);
                                            w.setResizable(false);
                                            w.setBounds(0, 0, 960, 549);
                                            w.setLocationRelativeTo(null);
                                            w.setLayout(new BorderLayout());
                                            w.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

                                            w.addWindowListener(new WindowAdapter() {
                                                public void windowClosing(WindowEvent we) {
                                                    callMenu();
                                                    w.dispose();
                                                }
                                            });
                                            ImageIcon imge = new ImageIcon(MaterialMakerv2.class.getResource("/icon/ico.png"));
                                            w.setIconImage(imge.getImage());
                                            w.setVisible(true);
                                        } catch (Exception e) {

                                        }
                                        break;
                                    }
                                    default:
                                        break;
                                }
                            } else {
                                File check = new File((fileToEdit.getParent() + "\\material_common_2.0.fxsub"));
                                if (!check.exists()) {//If materialcommon doesn't exist in the folder.
                                    copyFile(new File("../../Materials/material_common_2.0.fxsub"), check);
                                }
                                FileWriter fw = new FileWriter(filePath);

                                fw.write(catchContent);
                                fw.close();

                                try {
                                    a.setVisible(false);
                                    WindowFrame w = new WindowFrame();
                                    w.setAlwaysOnTop(true);
                                    w.setAlwaysOnTop(false);
                                    w.setResizable(false);
                                    w.setBounds(0, 0, 960, 549);
                                    w.setLocationRelativeTo(null);
                                    w.setLayout(new BorderLayout());
                                    w.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

                                    w.addWindowListener(new WindowAdapter() {
                                        public void windowClosing(WindowEvent we) {
                                            callMenu();
                                            w.dispose();
                                        }
                                    });
                                    ImageIcon imge = new ImageIcon(MaterialMakerv2.class.getResource("/icon/ico.png"));
                                    w.setIconImage(imge.getImage());
                                    w.setVisible(true);
                                } catch (Exception e) {

                                }
                            }
                        } else {
                        }
                        break;
                    case 1:
                        filePath = "../../Materials";
                        filePath += "\\" + newName;
                        fileToEdit = new File(filePath);
                        Object[] options = {"Overwrite file", "Change name", "Go to menu"};
                        if (fileToEdit.exists() && !fileToEdit.isDirectory()) {
                            int whenExists = JOptionPane.showOptionDialog(a, "<html>It looks like a material with that exact name (<i>" + newName + "</i>), already exists.<br><br>" + "Would you like whether to <b>overwrite</b> the selected file or <b>change the name </b> of the material?</html>",
                                    "Material already exists", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, img, options, "Change name");
                            switch (whenExists) {
                                case 0:
                                    //Overwrite

                                    try {
                                        File overwrite = fileToEdit;
                                        FileWriter fw = new FileWriter(overwrite);
                                        fw.write(catchContent);
                                        fw.close();

                                    } catch (Exception e) {

                                    }
                                    try {

                                        WindowFrame wf = new WindowFrame();

                                        wf.setAlwaysOnTop(true);
                                        wf.setAlwaysOnTop(false);
                                        wf.setResizable(false);
                                        wf.setBounds(0, 0, 960, 549);
                                        wf.setLocationRelativeTo(null);
                                        wf.setLayout(new BorderLayout());
                                        wf.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

                                        wf.addWindowListener(new WindowAdapter() {
                                            public void windowClosing(WindowEvent we) {
                                                callMenu();
                                                wf.dispose();
                                            }
                                        });
                                        ImageIcon imge = new ImageIcon(MaterialMakerv2.class.getResource("/icon/ico.png"));
                                        wf.setIconImage(imge.getImage());
                                        wf.setVisible(true);
                                    } catch (Exception e) {

                                    } finally {
                                        a.setVisible(false);
                                    }
                                    break;
                                case 1:
                                    do {
                                        newName = (String) JOptionPane.showInputDialog(a, "<html>New material name (<B>WITHOUT EXTENSION</B>)</html>", "Create material from Default", EXIT_ON_CLOSE, img, null, null);
                                        Pattern pattern = Pattern.compile("\\p{Alnum}+");//Only validates numbers and letters
                                        matcher = pattern.matcher(newName);

                                        filePath = "../../Materials" + "\\" + newName + ".fx";
                                        fileToEdit = new File(filePath);
                                        if (fileToEdit.exists()) {
                                            newName = "fuck you.fx";
                                        }
                                    } while (!matcher.matches() || (newName.contains(".fx") || (newName.equalsIgnoreCase("fx"))));
                                    File check = new File((fileToEdit.getParent() + "\\material_common_2.0.fxsub"));
                                    if (!check.exists()) {//If materialcommon doesn't exist in the folder.
                                        copyFile(new File("../../Materials/material_common_2.0.fxsub"), check);
                                    }
                                    FileWriter fw = new FileWriter(filePath);
                                    fw.write(catchContent);
                                    fw.close();
                                    try {
                                        a.setVisible(false);
                                        WindowFrame w = new WindowFrame();
                                        w.setAlwaysOnTop(true);
                                        w.setAlwaysOnTop(false);
                                        w.setResizable(false);
                                        w.setBounds(0, 0, 960, 549);
                                        w.setLocationRelativeTo(null);
                                        w.setLayout(new BorderLayout());
                                        w.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

                                        w.addWindowListener(new WindowAdapter() {
                                            public void windowClosing(WindowEvent we) {
                                                callMenu();
                                                w.dispose();
                                            }
                                        });
                                        ImageIcon imge = new ImageIcon(MaterialMakerv2.class.getResource("/icon/ico.png"));
                                        w.setIconImage(imge.getImage());
                                        w.setVisible(true);
                                    } catch (Exception e) {

                                    }
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            File check = new File((fileToEdit.getParent() + "\\material_common_2.0.fxsub"));
                            if (!check.exists()) {//If materialcommon doesn't exist in the folder.
                                copyFile(new File("../../Materials/material_common_2.0.fxsub"), check);
                            }
                            FileWriter fw = new FileWriter(filePath);

                            fw.write(catchContent);
                            fw.close();

                            try {
                                a.setVisible(false);
                                WindowFrame w = new WindowFrame();
                                w.setAlwaysOnTop(true);
                                w.setAlwaysOnTop(false);
                                w.setResizable(false);
                                w.setBounds(0, 0, 960, 549);
                                w.setLocationRelativeTo(null);
                                w.setLayout(new BorderLayout());
                                w.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

                                w.addWindowListener(new WindowAdapter() {
                                    @Override
                                    public void windowClosing(WindowEvent we) {
                                        callMenu();
                                        w.dispose();
                                    }
                                });
                                ImageIcon imge = new ImageIcon(MaterialMakerv2.class.getResource("/icon/ico.png"));
                                w.setIconImage(imge.getImage());
                                w.setVisible(true);
                            } catch (Exception e) {

                            }
                        }
                        break;
                    default:
                        break;
                }

            } else {

            }

        } catch (Exception e) {

        }
    }

    private static void Windows() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                break;
            }
        } catch (Exception e) {

        }
    }
}
