package com.project.major.fileOperation;

import com.project.major.FileTablePanel;
import com.project.major.FileTreePanel;

import javax.swing.*;
import java.io.File;

public class RenameWorker extends SwingWorker<Boolean, Void> {
    private final File sourceFile;
    private final File targetFile;
    private final FileTablePanel tablePanel;
    private final FileTreePanel treePanel;

    public RenameWorker(File sourceFile, File targetFile, FileTablePanel tablePanel, FileTreePanel treePanel) {
        this.sourceFile = sourceFile;
        this.targetFile = targetFile;
        this.tablePanel = tablePanel;
        this.treePanel = treePanel;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        // Perform the rename operation
        return sourceFile.renameTo(targetFile);
    }

    @Override
    protected void done() {
        try {
            boolean success = get(); // Get the result of the operation
            
            if (success) {
                // If it was a directory, update the specific node in the tree
                if (sourceFile.isDirectory()) {
                    // This is now efficiently handled by the improved FileTreePanel
                    treePanel.updateNodeAfterRename(sourceFile, targetFile);
                }
                
                // Update the file table with parent directory contents
                File parentDir = targetFile.getParentFile();
                tablePanel.updateTable(parentDir);
                
                JOptionPane.showMessageDialog(null, 
                        "Successfully renamed to: " + targetFile.getName(), 
                        "Rename Successful", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, 
                        "Failed to rename file or folder. Check permissions.", 
                        "Rename Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                    "Error during rename: " + e.getMessage(), 
                    "Rename Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Reset the action in progress flag
            tablePanel.setActionInProgress(false);
        }
    }
}