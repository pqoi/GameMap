package com.project.major;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainFileFrame extends JFrame {

    public MainFileFrame() {
        setTitle("Main File Frame");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setUndecorated(false);  // Remove borders/title
    setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize full screen
        setLocationRelativeTo(null);

        // Create background image
        ImageIcon backgroundImage = new ImageIcon("major\\Major\\src\\main\\resources\\manga-one-piece-wallpaper-preview.jpg");
        JLabel background = new JLabel(backgroundImage);
        background.setLayout(new BorderLayout());

        // Responsive background
        Image img = backgroundImage.getImage();
        Image scaledImg = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        background.setIcon(new ImageIcon(scaledImg));

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Image img = backgroundImage.getImage();
                Image scaledImg = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                background.setIcon(new ImageIcon(scaledImg));
            }
        });

        setContentPane(background);

        // Left panel with file icon
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);

        ImageIcon fileIcon = new ImageIcon("major\\Major\\src\\main\\resources\\fileManagerIcon.png");
        Image fileImg = fileIcon.getImage();
        Image scaledFileImg = fileImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel fileLabel = new JLabel(new ImageIcon(scaledFileImg));
        fileLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        leftPanel.add(fileLabel);

        fileLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new FileFrame(); // Open FileFrame
            }
        });

        background.add(leftPanel, BorderLayout.WEST);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFileFrame(); // Just call the class
    }
}
