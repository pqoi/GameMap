package com.project.major.fileOperation;

import com.project.major.FileFrame;
import com.project.major.FileTablePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CreateFolderButton extends FileOperationButton {
    private FileTablePanel fileTablePanel;

    public CreateFolderButton(FileFrame fileFrame) {
        super("major/Major/src/main/resources/createFolder.png");
        this.fileTablePanel = fileFrame.getTablePanel();
        if (fileTablePanel == null) {
            throw new IllegalStateException("FileTablePanel is not initialized.");
        }
         // ✅ Set tooltip text here
        this.getButton().setToolTipText("Create A Folder");
        setActionListener();
    }

   private void setActionListener() {
    // Remove any existing listeners first
    ActionListener[] listeners = this.getButton().getActionListeners();
    for (ActionListener listener : listeners) {
        this.getButton().removeActionListener(listener);
    }

    // Add only one listener
    this.getButton().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            performOperation();
        }
    });
}

   private boolean isCreating = false;

    @Override
    protected void performOperation() {
        if (isCreating) return; // Skip if already creating

        isCreating = true;

        File currentDirectory = fileTablePanel.getCurrentDirectory();
        if (currentDirectory == null || !currentDirectory.isDirectory()) {
            JOptionPane.showMessageDialog(
                null,
                "Cannot create folder. Invalid directory selected.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            isCreating = false;
            return;
        }

        String folderName = JOptionPane.showInputDialog(
            null,
            "Enter new folder name:",
            "Create New Folder",
            JOptionPane.PLAIN_MESSAGE
        );

        if (folderName != null && !folderName.trim().isEmpty()) {
            File newFolder = new File(currentDirectory, folderName.trim());

            if (newFolder.exists()) {
                JOptionPane.showMessageDialog(
                    null,
                    "A folder or file with that name already exists.",
                    "Error",
                    JOptionPane.WARNING_MESSAGE
                );
            } else {
                boolean success = newFolder.mkdirs();

                if (success) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Folder created successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    fileTablePanel.updateTable(currentDirectory);
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Failed to create folder. Check permissions or path.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }

        isCreating = false;
    }
}