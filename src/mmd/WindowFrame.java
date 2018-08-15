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
import java.awt.Desktop;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import rpmxc.Start;

public class WindowFrame extends javax.swing.JFrame {

    public static MaterialMakerv2 foo = new MaterialMakerv2();

    ImageIcon bg = new ImageIcon("bg/bgDIM.png");

    public WindowFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        fromExistingMore = new javax.swing.JButton();
        EditMore = new javax.swing.JButton();
        fromDefaultMore = new javax.swing.JButton();
        BGMore = new javax.swing.JPanel();
        back1 = new javax.swing.JButton();
        gotofolder = new javax.swing.JButton();
        METALNESS = new javax.swing.JButton();
        SPECULARANDSMOOTHNESS = new javax.swing.JButton();
        PARALLAXANDOCCLUSION = new javax.swing.JButton();
        ALPHA = new javax.swing.JButton();
        CUSTOMENABLE = new javax.swing.JButton();
        EMISSIVE = new javax.swing.JButton();
        ALBEDO = new javax.swing.JButton();
        NORMALMAP = new javax.swing.JButton();
        FINISHED = new javax.swing.JButton();
        gotofolder1 = new javax.swing.JButton();
        BGMore1 = new javax.swing.JPanel();
        BGMore2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Edit "+foo.getFileToEdit().getName());
        setAutoRequestFocus(false);
        setResizable(false);
        setSize(new java.awt.Dimension(960, 540));
        getContentPane().setLayout(null);

        fromExistingMore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/moreexisting.png"))); // NOI18N
        fromExistingMore.setBorderPainted(false);
        fromExistingMore.setContentAreaFilled(false);
        fromExistingMore.setFocusPainted(false);
        fromExistingMore.setFocusable(false);
        fromExistingMore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromExistingMoreActionPerformed(evt);
            }
        });
        getContentPane().add(fromExistingMore);
        fromExistingMore.setBounds(440, 10, 60, 60);

        EditMore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/moredit.png"))); // NOI18N
        EditMore.setBorderPainted(false);
        EditMore.setContentAreaFilled(false);
        EditMore.setFocusPainted(false);
        EditMore.setFocusable(false);
        EditMore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditMoreActionPerformed(evt);
            }
        });
        getContentPane().add(EditMore);
        EditMore.setBounds(520, 10, 60, 60);

        fromDefaultMore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/moredefault.png"))); // NOI18N
        fromDefaultMore.setBorderPainted(false);
        fromDefaultMore.setContentAreaFilled(false);
        fromDefaultMore.setFocusPainted(false);
        fromDefaultMore.setFocusable(false);
        fromDefaultMore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromDefaultMoreActionPerformed(evt);
            }
        });
        getContentPane().add(fromDefaultMore);
        fromDefaultMore.setBounds(360, 10, 60, 60);

        BGMore.setBackground(new Color(0,0,0,64));
        BGMore.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        BGMore.setRequestFocusEnabled(false);
        getContentPane().add(BGMore);
        BGMore.setBounds(330, -10, 280, 90);

        back1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bg/back.png"))); // NOI18N
        back1.setBorder(null);
        back1.setContentAreaFilled(false);
        back1.setFocusPainted(false);
        back1.setFocusable(false);
        back1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back1ActionPerformed(evt);
            }
        });
        getContentPane().add(back1);
        back1.setBounds(30, 10, 50, 57);

        gotofolder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/notepad.png"))); // NOI18N
        gotofolder.setBorderPainted(false);
        gotofolder.setContentAreaFilled(false);
        gotofolder.setFocusPainted(false);
        gotofolder.setFocusable(false);
        gotofolder.setOpaque(false);
        gotofolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gotofolderActionPerformed(evt);
            }
        });
        getContentPane().add(gotofolder);
        gotofolder.setBounds(790, 20, 50, 40);

        METALNESS.setText("METALNESS");
        METALNESS.setFocusPainted(false);
        METALNESS.setFocusable(false);
        METALNESS.setMaximumSize(new java.awt.Dimension(172, 30));
        METALNESS.setMinimumSize(new java.awt.Dimension(172, 30));
        METALNESS.setOpaque(false);
        METALNESS.setPreferredSize(new java.awt.Dimension(172, 30));
        METALNESS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                METALNESSActionPerformed(evt);
            }
        });
        getContentPane().add(METALNESS);
        METALNESS.setBounds(386, 382, 172, 45);

        SPECULARANDSMOOTHNESS.setText("SPECULAR & SMOOTHNESS");
        SPECULARANDSMOOTHNESS.setFocusPainted(false);
        SPECULARANDSMOOTHNESS.setFocusable(false);
        SPECULARANDSMOOTHNESS.setMaximumSize(new java.awt.Dimension(172, 30));
        SPECULARANDSMOOTHNESS.setMinimumSize(new java.awt.Dimension(172, 30));
        SPECULARANDSMOOTHNESS.setOpaque(false);
        SPECULARANDSMOOTHNESS.setPreferredSize(new java.awt.Dimension(172, 30));
        SPECULARANDSMOOTHNESS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SPECULARANDSMOOTHNESSActionPerformed(evt);
            }
        });
        getContentPane().add(SPECULARANDSMOOTHNESS);
        SPECULARANDSMOOTHNESS.setBounds(386, 120, 172, 45);

        PARALLAXANDOCCLUSION.setText("PARALLAX & OCCLUSION");
        PARALLAXANDOCCLUSION.setFocusPainted(false);
        PARALLAXANDOCCLUSION.setFocusable(false);
        PARALLAXANDOCCLUSION.setMaximumSize(new java.awt.Dimension(172, 35));
        PARALLAXANDOCCLUSION.setMinimumSize(new java.awt.Dimension(172, 35));
        PARALLAXANDOCCLUSION.setOpaque(false);
        PARALLAXANDOCCLUSION.setPreferredSize(new java.awt.Dimension(172, 35));
        PARALLAXANDOCCLUSION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PARALLAXANDOCCLUSIONActionPerformed(evt);
            }
        });
        getContentPane().add(PARALLAXANDOCCLUSION);
        PARALLAXANDOCCLUSION.setBounds(386, 249, 172, 45);

        ALPHA.setText("ALPHA");
        ALPHA.setFocusPainted(false);
        ALPHA.setFocusable(false);
        ALPHA.setMaximumSize(new java.awt.Dimension(172, 30));
        ALPHA.setMinimumSize(new java.awt.Dimension(172, 30));
        ALPHA.setOpaque(false);
        ALPHA.setPreferredSize(new java.awt.Dimension(172, 30));
        ALPHA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ALPHAActionPerformed(evt);
            }
        });
        getContentPane().add(ALPHA);
        ALPHA.setBounds(120, 249, 172, 45);

        CUSTOMENABLE.setText("CUSTOM ENABLE");
        CUSTOMENABLE.setFocusPainted(false);
        CUSTOMENABLE.setFocusable(false);
        CUSTOMENABLE.setMaximumSize(new java.awt.Dimension(172, 35));
        CUSTOMENABLE.setMinimumSize(new java.awt.Dimension(172, 35));
        CUSTOMENABLE.setOpaque(false);
        CUSTOMENABLE.setPreferredSize(new java.awt.Dimension(172, 35));
        CUSTOMENABLE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CUSTOMENABLEActionPerformed(evt);
            }
        });
        getContentPane().add(CUSTOMENABLE);
        CUSTOMENABLE.setBounds(657, 249, 172, 45);

        EMISSIVE.setText("EMISSIVE");
        EMISSIVE.setFocusPainted(false);
        EMISSIVE.setFocusable(false);
        EMISSIVE.setMaximumSize(new java.awt.Dimension(172, 35));
        EMISSIVE.setMinimumSize(new java.awt.Dimension(172, 35));
        EMISSIVE.setOpaque(false);
        EMISSIVE.setPreferredSize(new java.awt.Dimension(172, 35));
        EMISSIVE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EMISSIVEActionPerformed(evt);
            }
        });
        getContentPane().add(EMISSIVE);
        EMISSIVE.setBounds(657, 120, 172, 45);

        ALBEDO.setText("ALBEDO");
        ALBEDO.setFocusPainted(false);
        ALBEDO.setFocusable(false);
        ALBEDO.setMaximumSize(new java.awt.Dimension(172, 30));
        ALBEDO.setMinimumSize(new java.awt.Dimension(172, 30));
        ALBEDO.setOpaque(false);
        ALBEDO.setPreferredSize(new java.awt.Dimension(172, 30));
        ALBEDO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ALBEDOActionPerformed(evt);
            }
        });
        getContentPane().add(ALBEDO);
        ALBEDO.setBounds(120, 120, 172, 45);

        NORMALMAP.setText("NORMAL MAP");
        NORMALMAP.setFocusPainted(false);
        NORMALMAP.setFocusable(false);
        NORMALMAP.setMaximumSize(new java.awt.Dimension(172, 30));
        NORMALMAP.setMinimumSize(new java.awt.Dimension(172, 30));
        NORMALMAP.setOpaque(false);
        NORMALMAP.setPreferredSize(new java.awt.Dimension(172, 30));
        NORMALMAP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NORMALMAPActionPerformed(evt);
            }
        });
        getContentPane().add(NORMALMAP);
        NORMALMAP.setBounds(120, 382, 172, 45);

        FINISHED.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bg/exit.png"))); // NOI18N
        FINISHED.setBorder(null);
        FINISHED.setContentAreaFilled(false);
        FINISHED.setFocusPainted(false);
        FINISHED.setFocusable(false);
        FINISHED.setMaximumSize(new java.awt.Dimension(172, 30));
        FINISHED.setMinimumSize(new java.awt.Dimension(172, 30));
        FINISHED.setPreferredSize(new java.awt.Dimension(172, 30));
        FINISHED.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FINISHEDActionPerformed(evt);
            }
        });
        getContentPane().add(FINISHED);
        FINISHED.setBounds(80, 10, 50, 60);

        gotofolder1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/folder.png"))); // NOI18N
        gotofolder1.setBorderPainted(false);
        gotofolder1.setContentAreaFilled(false);
        gotofolder1.setFocusPainted(false);
        gotofolder1.setFocusable(false);
        gotofolder1.setOpaque(false);
        gotofolder1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gotofolder1ActionPerformed(evt);
            }
        });
        getContentPane().add(gotofolder1);
        gotofolder1.setBounds(850, 20, 50, 40);

        BGMore1.setBackground(new Color(0,0,0,64));
        BGMore1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        BGMore1.setRequestFocusEnabled(false);
        getContentPane().add(BGMore1);
        BGMore1.setBounds(20, -10, 130, 90);

        BGMore2.setBackground(new Color(0,0,0,64));
        BGMore2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        BGMore2.setRequestFocusEnabled(false);
        getContentPane().add(BGMore2);
        BGMore2.setBounds(760, -10, 170, 90);

        jLabel2.setIcon(bg);
        jLabel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                jLabel2ComponentMoved(evt);
            }
        });
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 960, 550);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void callMenu() {

        ImageIcon ico = new ImageIcon(getClass().getResource("/icon/ico.png"));
        Menu a = new Menu();
        a.setBounds(0, 0, 645, 300);
        a.setResizable(false);
        a.setLocationRelativeTo(null);
        a.setDefaultCloseOperation(EXIT_ON_CLOSE);
        a.setLayout(new BorderLayout());
        a.setIconImage(ico.getImage());
        a.setVisible(true);
        a.setAlwaysOnTop(true);
        a.setAlwaysOnTop(false);
    }

    public void callWindow(Point p) {
        WindowFrame w = new WindowFrame();
        w.setAlwaysOnTop(true);
        w.setAlwaysOnTop(false);
        ImageIcon img = new ImageIcon(getClass().getResource("/icon/ico.png"));
        w.setResizable(false);
        w.setBounds(0, 0, 960, 549);
        w.setLocationRelativeTo(null);
        if (p != null) {
            w.setLocation(p);
        }
        w.setLayout(new BorderLayout());
        w.setDefaultCloseOperation(WindowFrame.DO_NOTHING_ON_CLOSE);
        if (foo.getRecursive() == true) {

            w.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent we) {
                    w.dispose();
                    try {
                        rpmxc.Options.main(null, Start.parentComponent);
                    } catch (IOException ex) {
                    }
                }
            });

        } else {
            w.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent we) {
                    callMenu();
                    w.dispose();
                }
            });
        }
        w.setIconImage(img.getImage());
        w.setVisible(true);
        w.setAlwaysOnTop(true);
        w.setAlwaysOnTop(false);

    }

    static boolean bgExists(String image_path) {
        try {
            File f = new File(image_path);
            return f.exists();

        } catch (Exception ex) {
            return false;
        }
    }
    private void METALNESSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_METALNESSActionPerformed
        MetalnessSection met = new MetalnessSection();
        met.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        met.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                Point pos = met.getLocation();
                callWindow(pos);
                met.dispose();
            }
        });
        met.setSize(960, 549);
        met.setVisible(true);
        met.setAlwaysOnTop(true);
        met.setAlwaysOnTop(false);
        met.setLocationRelativeTo(this);
        met.setLayout(new AbsoluteLayout());
        this.dispose();
        ImageIcon img = new ImageIcon(getClass().getResource("/icon/ico.png"));
        met.setIconImage(img.getImage());
    }//GEN-LAST:event_METALNESSActionPerformed

    private void SPECULARANDSMOOTHNESSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SPECULARANDSMOOTHNESSActionPerformed

        SpecularAndSmoothness sas = new SpecularAndSmoothness();
        sas.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        sas.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                Point pos = sas.getLocation();
                callWindow(pos);
                sas.dispose();
            }
        });
        sas.setSize(960, 549);
        sas.setVisible(true);
        sas.setAlwaysOnTop(true);
        sas.setAlwaysOnTop(false);
        sas.setLocationRelativeTo(this);
        sas.setLayout(new AbsoluteLayout());
        this.dispose();
        ImageIcon img = new ImageIcon(getClass().getResource("/icon/ico.png"));
        sas.setIconImage(img.getImage());
        sas.AlbedoMapFile1 = "";
    }//GEN-LAST:event_SPECULARANDSMOOTHNESSActionPerformed

    private void PARALLAXANDOCCLUSIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PARALLAXANDOCCLUSIONActionPerformed

        ParallaxAndOcclusion sao = new ParallaxAndOcclusion();
        sao.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        sao.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                Point pos = sao.getLocation();
                callWindow(pos);
                sao.dispose();
            }
        });
        sao.setLayout(new AbsoluteLayout());
        sao.setSize(960, 549);
        sao.setVisible(true);
        sao.setLocationRelativeTo(this);
        sao.setAlwaysOnTop(true);
        sao.setAlwaysOnTop(false);
        this.dispose();
        ImageIcon img = new ImageIcon(getClass().getResource("/icon/ico.png"));
        sao.setIconImage(img.getImage());

    }//GEN-LAST:event_PARALLAXANDOCCLUSIONActionPerformed

    private void ALPHAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ALPHAActionPerformed

        AlphaSection as = new AlphaSection();
        as.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        as.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                Point pos = as.getLocation();
                callWindow(pos);
                as.dispose();
            }
        });
        as.setSize(960, 549);
        as.setAlwaysOnTop(true);
        as.setAlwaysOnTop(false);
        as.setLocationRelativeTo(this);

        as.setLayout(new AbsoluteLayout());
        as.setVisible(true);
        this.dispose();
        ImageIcon img = new ImageIcon(getClass().getResource("/icon/ico.png"));
        as.setIconImage(img.getImage());
        as.AlbedoMapFile1 = "";
    }//GEN-LAST:event_ALPHAActionPerformed

    private void CUSTOMENABLEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CUSTOMENABLEActionPerformed

        CustomEnableSection ces = new CustomEnableSection();
        ces.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        ces.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                Point pos = ces.getLocation();
                callWindow(pos);
                ces.dispose();
            }
        });
        ces.setSize(960, 549);
        ces.setLayout(new AbsoluteLayout());
        ces.setAlwaysOnTop(true);
        ces.setAlwaysOnTop(false);
        ces.setLocationRelativeTo(this);
        ces.setVisible(true);
        this.dispose();
        ImageIcon img = new ImageIcon(getClass().getResource("/icon/ico.png"));
        ces.setIconImage(img.getImage());

    }//GEN-LAST:event_CUSTOMENABLEActionPerformed

    private void EMISSIVEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EMISSIVEActionPerformed

        EmissiveSection es = new EmissiveSection();
        es.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        es.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                Point pos = es.getLocation();
                callWindow(pos);
                es.dispose();
            }
        });
        es.setSize(960, 549);
        es.setLayout(new AbsoluteLayout());
        es.setAlwaysOnTop(true);
        es.setAlwaysOnTop(false);
        es.setBounds(0, 0, 960, 549);
        es.setLocationRelativeTo(this);
        es.setVisible(true);
        this.dispose();
        ImageIcon img = new ImageIcon(getClass().getResource("/icon/ico.png"));
        es.setIconImage(img.getImage());
    }//GEN-LAST:event_EMISSIVEActionPerformed

    private void ALBEDOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ALBEDOActionPerformed
        AlbedoSection ab = new AlbedoSection();
        ab.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        ab.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                Point pos = ab.getLocation();
                callWindow(pos);
                ab.dispose();
            }
        });
        ab.setSize(960, 549);
        ab.setVisible(true);
        ab.setAlwaysOnTop(true);
        ab.setAlwaysOnTop(false);
        ab.setLocationRelativeTo(this);
        ab.setLayout(new AbsoluteLayout());
        this.dispose();
        ImageIcon img = new ImageIcon(getClass().getResource("/icon/ico.png"));
        ab.setIconImage(img.getImage());
        ab.AlbedoMapFile1 = "";
    }//GEN-LAST:event_ALBEDOActionPerformed

    private void NORMALMAPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NORMALMAPActionPerformed

        NormalSection ns = new NormalSection();
        ns.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        ns.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                Point pos = ns.getLocation();
                callWindow(pos);
                ns.dispose();
            }
        });
        ns.setSize(960, 549);
        ns.setVisible(true);
        ns.setAlwaysOnTop(true);
        ns.setAlwaysOnTop(false);
        ns.setLocationRelativeTo(this);
        ns.setLayout(new AbsoluteLayout());
        this.dispose();
        ImageIcon img = new ImageIcon(getClass().getResource("/icon/ico.png"));
        ns.setIconImage(img.getImage());
        ns.AlbedoMapFile1 = "";
    }//GEN-LAST:event_NORMALMAPActionPerformed

    private void FINISHEDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FINISHEDActionPerformed

        this.dispose();

        ImageIcon ico = new ImageIcon(getClass().getResource("/icon/ico.png"));
        ImageIcon img = new ImageIcon(getClass().getResource("/icon/icosmall.png"));
        Menu a = new Menu();
        a.setBounds(0, 0, 680, 295);
        a.setResizable(false);
        a.setLocationRelativeTo(null);
        a.setDefaultCloseOperation(EXIT_ON_CLOSE);
        a.setLayout(new BorderLayout());
        a.setIconImage(ico.getImage());
        a.setVisible(true);

        try {
            int n = JOptionPane.showConfirmDialog(
                    a,
                    "Would you like to create any more Materials?",
                    "More Materials?",
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, img);

            if (n == JOptionPane.YES_OPTION) {

            } else {
                System.exit(0);

            }
        } catch (Exception ex) {
            Logger.getLogger(WindowFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_FINISHEDActionPerformed

    private void gotofolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gotofolderActionPerformed

        try {

            Desktop d = Desktop.getDesktop();
            File f = new File(foo.getFileToEdit().getAbsolutePath());
            d.open(f);

        } catch (Exception e) {

        }
    }//GEN-LAST:event_gotofolderActionPerformed

    private void gotofolder1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gotofolder1ActionPerformed
        try {
            Runtime.getRuntime().exec("explorer.exe /select," + foo.getFileToEdit());
        } catch (IOException ex) {
            Logger.getLogger(WindowFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_gotofolder1ActionPerformed

    private void jLabel2ComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jLabel2ComponentMoved

    }//GEN-LAST:event_jLabel2ComponentMoved

    private void back1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back1ActionPerformed
        this.dispose();
        if (foo.getRecursive() != true) {
            try {
                MaterialMakerv2.main(null);
            } catch (Exception ex) {
                Logger.getLogger(WindowFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                rpmxc.Options.main(null, Start.parentComponent);
            } catch (IOException ex) {
                Logger.getLogger(WindowFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_back1ActionPerformed

    private void fromDefaultMoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromDefaultMoreActionPerformed
        Point location = this.getLocation();
        MaterialMakerv2.FromDefault(this, location, true);
    }//GEN-LAST:event_fromDefaultMoreActionPerformed

    private void EditMoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditMoreActionPerformed

        Point location = this.getLocation();
        MaterialMakerv2.editMaterial(this, location, true);
    }//GEN-LAST:event_EditMoreActionPerformed

    private void fromExistingMoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromExistingMoreActionPerformed
        Point location = this.getLocation();
        MaterialMakerv2.FromExisting(this, location, true);
    }//GEN-LAST:event_fromExistingMoreActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WindowFrame().setVisible(true);
            }
        });
    }

    public JButton getALBEDOALPHA() {
        return this.ALBEDO;
    }

    public JButton getCUSTOMENABLE() {
        return this.CUSTOMENABLE;
    }

    public JButton getEMISSIVE() {
        return this.EMISSIVE;
    }

    public JButton getNORMALMAP() {
        return this.ALPHA;
    }

    public JButton getOCCLUISON() {
        return this.PARALLAXANDOCCLUSION;
    }

    public JButton getPARALLAX() {
        return this.METALNESS;
    }

    public JButton getSAVE() {
        return this.FINISHED;
    }

    public JButton getSMOOTHNESSMATALNESS() {
        return this.NORMALMAP;
    }

    public JButton getSPECULAR() {
        return this.SPECULARANDSMOOTHNESS;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ALBEDO;
    private javax.swing.JButton ALPHA;
    private javax.swing.JPanel BGMore;
    private javax.swing.JPanel BGMore1;
    private javax.swing.JPanel BGMore2;
    private javax.swing.JButton CUSTOMENABLE;
    private javax.swing.JButton EMISSIVE;
    private javax.swing.JButton EditMore;
    private javax.swing.JButton FINISHED;
    private javax.swing.JButton METALNESS;
    private javax.swing.JButton NORMALMAP;
    private javax.swing.JButton PARALLAXANDOCCLUSION;
    private javax.swing.JButton SPECULARANDSMOOTHNESS;
    private javax.swing.JButton back1;
    private javax.swing.JButton fromDefaultMore;
    private javax.swing.JButton fromExistingMore;
    private javax.swing.JButton gotofolder;
    private javax.swing.JButton gotofolder1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    // End of variables declaration//GEN-END:variables

}
