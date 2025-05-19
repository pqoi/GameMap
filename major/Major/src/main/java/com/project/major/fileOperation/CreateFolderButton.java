package com.comprog.fileOperation;

import com.comprog.FileFrame;
import com.comprog.FileTablePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CreateFolderButton extends FileOperationButton {
    private FileTablePanel fileTablePanel;

    public CreateFolderButton(FileFrame fileFrame) {
        super("major/Major/src/main/resources/createFolder.png");
        this.fileTablePanel = fileFrame.getTablePanel(); // Ensure this is not null
        if (fileTablePanel == null) {
            throw new IllegalStateException("FileTablePanel is not initialized.");
        }
        setActionListener();
    }
     private void setActionListener() {
        this.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation();
            }
        });
    }

    @Override
    protected void performOperation() {
        if (fileTablePanel == null) {
            JOptionPane.showMessageDialog(null, "FileTablePanel is not initialized.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File selectedDirectory = fileTablePanel.getSelectedDirectory();
        if (selectedDirectory != null && selectedDirectory.isDirectory()) {
            String folderName = JOptionPane.showInputDialog("Enter the name for the new folder:");
            if (folderName != null && !folderName.isEmpty()) {
                File newFolder = new File(selectedDirectory, folderName);
                if (newFolder.mkdir()) {
                    JOptionPane.showMessageDialog(null, "Folder created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    fileTablePanel.updateTable(selectedDirectory); // Refresh the table to show the new folder
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to create folder.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No directory selected. Please select a directory to create a folder.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}