 package com.comprog;

import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.*;
public class MainFileFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
    frame.setSize(1000, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    
    // Create background panel with ImageIcon
    ImageIcon backgroundImage = new ImageIcon("FileManegement\\final\\src\\main\\resources\\LoginBackground.jpg");
    JLabel background = new JLabel(backgroundImage);
    background.setLayout(new BorderLayout());
    
    // Make background image responsive
    Image img = backgroundImage.getImage();
    Image scaledImg = img.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
    background.setIcon(new ImageIcon(scaledImg));
    
    // Add resize listener to keep background responsive
    frame.addComponentListener(new java.awt.event.ComponentAdapter() {
        public void componentResized(java.awt.event.ComponentEvent e) {
            Image img = backgroundImage.getImage();
            Image scaledImg = img.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
            background.setIcon(new ImageIcon(scaledImg));
        }
    });
    
    frame.setContentPane(background);
    // Create panel for icons on the left side
    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
    leftPanel.setOpaque(false);

    // Add FileIcon with resized dimensions
    ImageIcon FileIcon = new ImageIcon("FileManegement\\final\\src\\main\\resources\\FileManagerIcon.png");
    Image fileImg = FileIcon.getImage();
    Image scaledFileImg = fileImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    JLabel fileLabel = new JLabel(new ImageIcon(scaledFileImg));
    fileLabel.setBorder(BorderFactory.createEmptyBorder(20,20,10,20)); // Add padding
    leftPanel.add(fileLabel);
    fileLabel.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            new FileFrame(); // Open FileFrame on click
        }
    });
    // Add RecycleIcon below with resized dimensions
    ImageIcon recycleIcon = new ImageIcon("FileManegement\\final\\src\\main\\resources\\Recycle Bin.png");
    Image recycleImg = recycleIcon.getImage();
    Image scaledRecycleImg = recycleImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    JLabel recycleLabel = new JLabel(new ImageIcon(scaledRecycleImg));
    recycleLabel.setBorder(BorderFactory.createEmptyBorder(10,20,20,20)); // Add padding
    leftPanel.add(recycleLabel);

    background.add(leftPanel, BorderLayout.WEST);
    frame.setVisible(true); 
    }
}
