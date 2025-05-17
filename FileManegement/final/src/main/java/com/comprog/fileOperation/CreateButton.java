package com.comprog.fileOperation;

import com.comprog.FileFrame;
import com.comprog.FileTreePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class CreateButton extends FileOperationButton {
    private FileFrame fileFrame;

    public CreateButton(FileFrame fileFrame) {
        super("FileManegement/final/src/main/resources/createIcon.png");
        this.fileFrame = fileFrame;
    }

    @Override
    protected void performOperation() {
        File selectedDir = fileFrame.getTreePanel().getSelectedDirectory();
        if (selectedDir == null) {
            JOptionPane.showMessageDialog(null, "No current file path is available. The file cannot be created.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String fileName = JOptionPane.showInputDialog("Enter the file name (default extension is .txt):");
        if (fileName == null || fileName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "File name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        

        File newFile = new File(selectedDir, fileName);

        // Use SwingWorker to perform file creation in the background
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    if (newFile.createNewFile()) {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(null, "File created successfully: " + newFile.getAbsolutePath(), "Success", JOptionPane.INFORMATION_MESSAGE);
                            // Refresh the table to show the new file
                            fileFrame.getTablePanel().updateTable(selectedDir);
                        });
                    } else {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(null, "File already exists: " + newFile.getAbsolutePath(), "Error", JOptionPane.ERROR_MESSAGE);
                        });
                    }
                } catch (IOException e) {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(null, "Error creating file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    });
                }
                return null;
            }
        };

        worker.execute();
    }
}