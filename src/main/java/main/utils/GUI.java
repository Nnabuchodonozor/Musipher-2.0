package main.utils;

import main.security.StreamCipher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    int sizeOfWindow = 600;
    SemanticUtils semanticUtils = new SemanticUtils();
    StreamCipher streamCipher = new StreamCipher();
    JButton enc1, dec1, key1, enc2, dec2, key2, enc3, dec3, key3, enc4, dec4, key4;
    JMenuItem m11, m12, m13, m14;
    JMenuItem m21, m22, m23, m24;
    JMenuItem m31, m32, m33, m34;
    JMenuItem m41, m42, m43, m44;
    JTextField ta1;
    JFrame frame1;
    JFrame frame2;
    JFrame frame3;
    JFrame frame4;
    JFileChooser fileChooser = new JFileChooser();
    String filename;
    boolean keyGeneratedInThisIteration = false;

    public GUI() {
        //Creating the Frame
        frame1 = new JFrame("Musipher 2.0 Textual Composer");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(sizeOfWindow, sizeOfWindow);
        frame2 = new JFrame("Musipher 2.0 File Composer");
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(sizeOfWindow, sizeOfWindow);
        frame3 = new JFrame("Musipher 2.0 Textual PAM");
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.setSize(sizeOfWindow, sizeOfWindow);
        frame4 = new JFrame("Musipher 2.0 File PAM");
        frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame4.setSize(sizeOfWindow, sizeOfWindow);

        //Creating the MenuBar and adding components
        JMenuBar mb1 = new JMenuBar();
        JMenuBar mb2 = new JMenuBar();
        JMenuBar mb3 = new JMenuBar();
        JMenuBar mb4 = new JMenuBar();
        JMenu m1 = new JMenu("Mode");
        JMenu m2 = new JMenu("Mode");
        JMenu m3 = new JMenu("Mode");
        JMenu m4 = new JMenu("Mode");

        mb1.add(m1);
        mb2.add(m2);
        mb3.add(m3);
        mb4.add(m4);
        m11 = new JMenuItem("Textual Composer");
        m11.addActionListener(this);
        m21 = new JMenuItem("Textual Composer");
        m21.addActionListener(this);
        m31 = new JMenuItem("Textual Composer");
        m31.addActionListener(this);
        m41 = new JMenuItem("Textual Composer");
        m41.addActionListener(this);

        m22 = new JMenuItem("File Composer");
        m22.addActionListener(this);
        m32 = new JMenuItem("File Composer");
        m32.addActionListener(this);
        m12 = new JMenuItem("File Composer");
        m12.addActionListener(this);
        m42 = new JMenuItem("File Composer");
        m42.addActionListener(this);

        m33 = new JMenuItem("Textual PAM");
        m33.addActionListener(this);
        m13 = new JMenuItem("Textual PAM");
        m13.addActionListener(this);
        m23 = new JMenuItem("Textual PAM");
        m23.addActionListener(this);
        m43 = new JMenuItem("Textual PAM");
        m43.addActionListener(this);

        m44 = new JMenuItem("File PAM");
        m44.addActionListener(this);
        m14 = new JMenuItem("File PAM");
        m14.addActionListener(this);
        m24 = new JMenuItem("File PAM");
        m24.addActionListener(this);
        m34 = new JMenuItem("File PAM");
        m34.addActionListener(this);

        m1.add(m11);
        m1.add(m12);
        m1.add(m13);
        m1.add(m14);
        m2.add(m21);
        m2.add(m22);
        m2.add(m23);
        m2.add(m24);
        m3.add(m31);
        m3.add(m32);
        m3.add(m33);
        m3.add(m34);
        m4.add(m41);
        m4.add(m42);
        m4.add(m43);
        m4.add(m44);

        frame1.getContentPane().add(BorderLayout.NORTH, mb1);
        frame2.getContentPane().add(BorderLayout.NORTH, mb2);
        frame3.getContentPane().add(BorderLayout.NORTH, mb3);
        frame4.getContentPane().add(BorderLayout.NORTH, mb4);


        JPanel p1 = new JPanel();
        enc1 = new JButton("Encrypt Text to MIDI");
        enc1.addActionListener(this);

        dec1 = new JButton("Decrypt MIDI to Text");
        dec1.addActionListener(this);

        key1 = new JButton("Generate Key");
        key1.addActionListener(this);

        ta1 = new JTextField(16);
        p1.add(enc1);
        p1.add(dec1);
        p1.add(key1);
        frame1.getContentPane().add(BorderLayout.SOUTH, p1);
        frame1.getContentPane().add(BorderLayout.WEST, ta1);


        JPanel p2 = new JPanel();
        enc2 = new JButton("Encrypt File to MIDI");
        enc2.addActionListener(this);
        JButton dec2 = new JButton("Decrypt MIDI to File");
        dec2.addActionListener(this);
        JButton key2 = new JButton("Generate Key");
        key2.addActionListener(this);
        p2.add(enc2);
        p2.add(dec2);
        p2.add(key2);
        frame2.getContentPane().add(BorderLayout.SOUTH, p2);


        JPanel p3 = new JPanel();
        enc3 = new JButton("Hide Text in mp3");
        enc3.addActionListener(this);
        dec3 = new JButton("Decrypt Text from mp3");
        dec3.addActionListener(this);
        key3 = new JButton("Choose mp3");
        key3.addActionListener(this);

        JTextField ta3 = new JTextField(16);
        p3.add(enc3);
        p3.add(dec3);
        p3.add(key3);
        frame3.getContentPane().add(BorderLayout.SOUTH, p3);
        frame3.getContentPane().add(BorderLayout.WEST, ta3);

        JPanel p4 = new JPanel();
        enc4 = new JButton("Hide File in mp3");
        enc4.addActionListener(this);
        dec4 = new JButton("Decrypt File from mp3");
        dec4.addActionListener(this);
        key4 = new JButton("Choose mp3");
        key4.addActionListener(this);
        p4.add(enc4);
        p4.add(dec4);
        p4.add(key4);
        frame4.getContentPane().add(BorderLayout.SOUTH, p4);

        frame1.getContentPane().setBackground(Color.YELLOW);
        frame2.getContentPane().setBackground(Color.ORANGE);
        frame3.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame4.getContentPane().setBackground(Color.GREEN);

        frame1.setVisible(true);
        frame2.setVisible(false);
        frame3.setVisible(false);
        frame4.setVisible(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {


            int response;
            if (e.getSource() == m22 || e.getSource() == m42 || e.getSource() == m32 || e.getSource() == m12) {
                frame1.setVisible(false);
                frame3.setVisible(false);
                frame4.setVisible(false);
                frame2.setVisible(true);
            } else if (e.getSource() == m11 || e.getSource() == m41 || e.getSource() == m31 || e.getSource() == m21) {
                frame1.setVisible(true);
                frame3.setVisible(false);
                frame4.setVisible(false);
                frame2.setVisible(false);
            } else if (e.getSource() == m33 || e.getSource() == m13 || e.getSource() == m23 || e.getSource() == m43) {
                frame1.setVisible(false);
                frame3.setVisible(true);
                frame4.setVisible(false);
                frame2.setVisible(false);
            } else if (e.getSource() == m14 || e.getSource() == m24 || e.getSource() == m34 || e.getSource() == m44) {
                frame1.setVisible(false);
                frame3.setVisible(false);
                frame4.setVisible(true);
                frame2.setVisible(false);
            }

            if (e.getSource() == enc1) {
                // Encrypt text when generated key
                // if()
//            semanticUtils.encryptMIDIFromText(this.ta1.getText());
//                semanticUtils.encryptToMIDIFromText("");
            } else if (e.getSource() == dec1) {

                // Choose file to decrypt, write key to decrypt and decrypt

                response = fileChooser.showOpenDialog(null);
                if (response == JFileChooser.APPROVE_OPTION) {
                    filename = fileChooser.getSelectedFile().getAbsolutePath();
//                String key = JOptionPane.showInputDialog("Insert character representation of Stream Cipher Key");
                    String keyPath = "";
                    response = fileChooser.showOpenDialog(null);
                    if (response == JFileChooser.APPROVE_OPTION) {
                        keyPath = fileChooser.getSelectedFile().getAbsolutePath();
                    }

                    semanticUtils.decryptFromMIDIToText(filename, keyPath);
                }
            } else if (e.getSource() == key1) {
                /// todo
//                String seedAndIV = streamCipher.generateStreamCipherKeyAndIV();
//                JOptionPane.showMessageDialog(frame1,"Seed and IV for encryption, please copy and store them \n"+ seedAndIV);
                // Generate key
//                String keyAndIV = semanticUtil.generateStreamCipherKeyAndIV();
//                JOptionPane.showMessageDialog(null, keyAndIV);

            } else if (e.getSource() == enc2) {
//
                // Choose file and encrypt when already generated key
                response = fileChooser.showOpenDialog(null);
                filename = "";
                if (response == JFileChooser.APPROVE_OPTION) {
                    filename = fileChooser.getSelectedFile().getAbsolutePath();
                }
                semanticUtils.encryptToMIDIFromFile(filename,"password");
            } else if (e.getSource() == dec2) {

                // Choose file to decrypt and write key and decrypt

                response = fileChooser.showOpenDialog(null);
                if (response == JFileChooser.APPROVE_OPTION) {
                    filename = fileChooser.getSelectedFile().getAbsolutePath();
                }
            } else if (e.getSource() == key2) {
                // Generate key
//                String keyAndIV = semanticUtils.generateStreamCipherKeyAndIV();
//                JOptionPane.showMessageDialog(null, keyAndIV);

            } else if (e.getSource() == enc3) {
                // Hide text when mp3 chosen file
            } else if (e.getSource() == dec3) {
                // Choose mp3 file and decrypt it
            } else if (e.getSource() == key3) {
                // Choose mp3 file which will later
            } else if (e.getSource() == enc4) {
                // Choose file for encryption and encrypt when mp3 is chosen
            } else if (e.getSource() == dec4) {
                // Choose mp3 file and then decrypt it
            } else if (e.getSource() == key4) {
                // Choose mp3 file that will be used for steganography
            }

        }catch (Exception exception){
            JOptionPane.showMessageDialog(null,exception.getMessage());
        }
    }


}




