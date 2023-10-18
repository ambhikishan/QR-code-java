package org.example;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import net.glxn.qrgen.javase.QRCode;
import javax.swing.*;



import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Qrcode {
    public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        ByteArrayOutputStream stream = QRCode
                .from(barcodeText)
                .withSize(250, 250)
                .stream();
        ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());

        return ImageIO.read(bis);
    }
    Qrcode(){
        JFrame window = new JFrame("QR Code");
        window.setVisible(true);
        window.setSize(400,400);
        JTextField textbox = new JTextField();
        window.add(textbox);
        textbox.setSize(400,50);
        JButton save = new JButton("Save QR");
        save.setVisible(true);
        window.add(save);
        save.setBounds(150,150,100,40);
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to save");
                fileChooser.setFileFilter(new FileNameExtensionFilter("JPEG", "jpeg"));
                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
//                    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
                    try {
                        ImageIO.write(generateQRCodeImage(textbox.getText()), "jpeg", new File(fileToSave.getAbsolutePath()+".jpeg"));
                    } catch (Exception ex) {
                        System.out.println("file not saved");
                    }
                }}


        };
        save.addActionListener(actionListener);
        JLabel label = new JLabel("");
        KeyListener action = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {


                try {
                    String st = textbox.getText();
                    BufferedImage image = generateQRCodeImage(st);
                    ImageIO.write(image,"jpeg",new File("hello.png"));


                } catch (Exception ex) {
                    System.out.println("sorry");
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        textbox.addKeyListener(action);



        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String args[]) throws Exception {
        new Qrcode();




    }
}
