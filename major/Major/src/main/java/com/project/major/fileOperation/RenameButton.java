package com.project.major.fileOperation;

import com.project.major.FileFrame;
import com.project.major.FileTablePanel;
import com.project.major.FileTreePanel;

import javax.swing.*;
import java.io.File;

public class RenameButton extends FileOperationButton {
    private FileTablePanel tablePanel;
    private FileTreePanel treePanel;
    private FileFrame fileFrame;

    public RenameButton(FileFrame fileFrame) {
        super("major/Major/src/main/resources/renameIcon.png");
        this.tablePanel = fileFrame.getTablePanel();
        this.treePanel = fileFrame.getTreePanel();
    }

    @Override
    protected void performOperation() {
        // Check if any operation is already in progress
        if (tablePanel.isActionInProgress()) {
            JOptionPane.showMessageDialog(null, "Another operation is in progress. Please wait.", 
                    "Operation in Progress", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Set action in progress flag
        tablePanel.setActionInProgress(true);
        
        try {
            // First try to get selected file from table panel
            File selectedFile = null;
            int selectedRow = tablePanel.getSelectedRow();
            
            if (selectedRow >= 0) {
                selectedFile = (File) tablePanel.getModel().getValueAt(selectedRow, 4);
            } else {
                // If nothing selected in table, try to get from tree panel
                selectedFile = treePanel.getSelectedDirectory();
            }
            
            // If no file is selected in either panel
            if (selectedFile == null) {
                JOptionPane.showMessageDialog(null, "Please select a file or folder to rename", 
                        "No Selection", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Prompt user for new name
            String currentName = selectedFile.getName();
            String newName = JOptionPane.showInputDialog(null, 
                    "Enter new name:", "Rename", JOptionPane.QUESTION_MESSAGE);
            
            // If user cancels or enters empty name
            if (newName == null || newName.trim().isEmpty()) {
                return;
            }
            
            // Create new file path with new name but same parent directory
            File parentDir = selectedFile.getParentFile();
            File newFile = new File(parentDir, newName);
            
            // Check if a file with the new name already exists
            if (newFile.exists()) {
                JOptionPane.showMessageDialog(null, 
                        "A file or folder with that name already exists.", 
                        "Name Conflict", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Create and start rename worker thread
            RenameWorker worker = new RenameWorker(selectedFile, newFile, tablePanel, treePanel);
            worker.execute();
            
        } finally {
            // Reset action flag only if not passing to worker thread
            if (!SwingUtilities.isEventDispatchThread()) {
                tablePanel.setActionInProgress(false);
            }
        }
    }
}