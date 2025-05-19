package com.project.major.fileOperation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Abstract class for file operation buttons
public abstract class FileOperationButton {
    protected JButton button;

    public FileOperationButton(String iconPath) {
        ImageIcon icon = new ImageIcon(iconPath);
        button = new JButton(icon);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setPreferredSize(new java.awt.Dimension(30, 30)); // Set preferred size for the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation();
            }
        });
    }

    protected abstract void performOperation();

    public JButton getButton() {
        return button;
    }
}