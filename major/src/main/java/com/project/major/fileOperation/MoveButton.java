package com.project.major.fileOperation;

import com.project.major.FileFrame;
import com.project.major.FileTablePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MoveButton extends FileOperationButton {
    private FileFrame fileFrame;

    public MoveButton(FileFrame fileFrame) {
        super("major/Major/src/main/resources/moveIcon.png");
        this.fileFrame = fileFrame;

        // Optional: Add tooltip
        setToolTipText("Move selected file or folder");
         // ✅ Set tooltip text here
        this.getButton().setToolTipText("Move a file or folder");
        // Action listener for button click
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = fileFrame.getTablePanel().getSelectedRow();
                File selectedFile = null;
                if (selectedRow >= 0) {
                    selectedFile = (File) fileFrame.getTablePanel().getModel().getValueAt(selectedRow, 4);
                }

                if (selectedFile != null) {
                    performMove(selectedFile);
                } else {
                    JOptionPane.showMessageDialog(fileFrame, "No file or folder selected.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    public void setToolTipText(String text) {
        super.getButton().setToolTipText(text);  // Correctly set tooltip on the JButton
    }

    public void addActionListener(ActionListener listener) {
        super.getButton().addActionListener(listener);  // Forward to internal JButton
    }

    @Override
    protected void performOperation() {
        int selectedRow = fileFrame.getTablePanel().getSelectedRow();
        File selectedFile = null;
        if (selectedRow >= 0) {
            selectedFile = (File) fileFrame.getTablePanel().getModel().getValueAt(selectedRow, 4);
        }

        if (selectedFile != null) {
            performMove(selectedFile);
        } 
    }

    public void performMove(File sourceFile) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("Select Target Directory");
        int result = chooser.showOpenDialog(fileFrame);

        if (result == JFileChooser.APPROVE_OPTION) {
            File targetDir = chooser.getSelectedFile();
            MoveOperation moveOp = new MoveOperation(fileFrame);
            boolean success = moveOp.moveFileOrDirectory(sourceFile, targetDir);

            if (success) {
                JOptionPane.showMessageDialog(fileFrame, "Moved successfully.");
                fileFrame.getTreePanel().updateTree(); // Refresh tree
                fileFrame.getTablePanel().showFilesInDirectory(
                    fileFrame.getTablePanel().getCurrentDirectory()
                ); // Refresh current directory view
            } else {
                JOptionPane.showMessageDialog(fileFrame, "Failed to move file or folder.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}