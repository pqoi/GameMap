package com.comprog.fileOperation;

import com.comprog.FileFrame;
import com.comprog.FileTreePanel;
import com.comprog.FileTablePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class DeleteButton extends FileOperationButton {
    private FileFrame fileFrame;

    public DeleteButton(FileFrame fileFrame) {
        super("FileManegement/final/src/main/resources/DeleteIcon.png");
        this.fileFrame = fileFrame;
        getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation();
            }
        });
    }

  @Override
protected void performOperation() {
    FileTreePanel treePanel = fileFrame.getTreePanel();
    FileTablePanel tablePanel = fileFrame.getTablePanel();

    // Prevent re-entrance
    if (tablePanel.isActionInProgress()) {
        return;
    }

    tablePanel.setActionInProgress(true); // Lock UI interaction

    final File selectedFile;

    // Determine selected file/folder
    if (tablePanel.getSelectedRow() != -1) {
        selectedFile = (File) tablePanel.getModel().getValueAt(tablePanel.getSelectedRow(), 4);
        tablePanel.clearSelection(); // Clear immediately to avoid re-trigger
    } else if (treePanel.getSelectedDirectory() != null) {
        selectedFile = treePanel.getSelectedDirectory();
    } else {
        selectedFile = null;
    }

    if (selectedFile == null) {
        JOptionPane.showMessageDialog(
            null,
            "No file or folder selected.",
            "No Selection",
            JOptionPane.INFORMATION_MESSAGE
        );
        tablePanel.setActionInProgress(false);
        return;
    }

    // Ask for confirmation
    int result = JOptionPane.showConfirmDialog(
        null,
        "Are you sure you want to delete the selected file or folder?",
        "Confirm Deletion",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE
    );

    if (result != JOptionPane.YES_OPTION) {
        tablePanel.setActionInProgress(false);
        return;
    }

    // Run deletion in background
    final File fileToDelete = selectedFile;
    new Thread(() -> {
        try {
            deleteFileOrFolder(fileToDelete);
            SwingUtilities.invokeLater(() -> {
                try {
                    if (fileToDelete.isDirectory()) {
                        treePanel.updateTree();
                    }
                    File parentDirectory = fileToDelete.getParentFile();
                    tablePanel.updateTable(parentDirectory);
                } finally {
                    tablePanel.setActionInProgress(false);
                }
            });
        } catch (IOException ex) {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(
                    null,
                    "Error deleting file or folder: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                tablePanel.setActionInProgress(false);
            });
        }
    }).start();
}
    private void deleteFileOrFolder(File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectory(file);
        } else {
            if (!file.delete()) {
                throw new IOException("Failed to delete file: " + file.getAbsolutePath());
            }
        }
    }

    private void deleteDirectory(File directory) throws IOException {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                deleteFileOrFolder(file);
            }
        }
        if (!directory.delete()) {
            throw new IOException("Failed to delete directory: " + directory.getAbsolutePath());
        }
    }
}