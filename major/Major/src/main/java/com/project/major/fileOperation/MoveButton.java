package com.comprog.fileOperation;

import com.comprog.FileTreePanel;
import com.comprog.FileTablePanel;
import com.comprog.FileFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class MoveButton extends FileOperationButton {
    private FileTreePanel fileTreePanel;
    private FileTablePanel fileTablePanel;
    private JFrame directorySelectionFrame;

    public MoveButton(FileTablePanel fileTablePanel) {
        super("major\Major\src\main\resources\moveIcon.png");
        this.fileTablePanel = fileTablePanel;
        initializeDirectorySelectionPanel();
    }

    public MoveButton(FileFrame fileFrame) {
        super("FileManegement/final/src/main/resources/moveIcon.png");
        this.fileTablePanel = fileFrame.getTablePanel();
        initializeDirectorySelectionPanel();
    }

    private void initializeDirectorySelectionPanel() {
        fileTreePanel = new FileTreePanel();
        directorySelectionFrame = new JFrame("Select Destination Folder");
        directorySelectionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        directorySelectionFrame.add(fileTreePanel, BorderLayout.CENTER);
        directorySelectionFrame.setSize(400, 300);
        directorySelectionFrame.setLocationRelativeTo(null);

        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(e -> {
            File selectedDirectory = fileTreePanel.getSelectedDirectory();
            if (selectedDirectory != null) {
                moveFile(selectedDirectory);
            }
            directorySelectionFrame.dispose();
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> directorySelectionFrame.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(selectButton);
        buttonPanel.add(cancelButton);
        directorySelectionFrame.add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void performOperation() {
        File selectedFile = fileTablePanel.getSelectedDirectory();
        if (selectedFile != null) {
            directorySelectionFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "No file or folder selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void moveFile(File destination) {
        File selectedFile = fileTablePanel.getSelectedDirectory();
        if (selectedFile != null) {
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    try {
                        // Perform the move operation
                        File newFile = new File(destination, selectedFile.getName());
                        if (selectedFile.isDirectory()) {
                            moveDirectory(selectedFile, newFile);
                        } else {
                            selectedFile.renameTo(newFile);
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error moving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    return null;
                }

                @Override
                protected void done() {
                    // Update the file table and tree after the move operation
                    fileTablePanel.updateTable(fileTablePanel.getCurrentDirectory());
                    fileTreePanel.updateTree();
                }
            };
            worker.execute();
        }
    }

    private void moveDirectory(File source, File destination) throws IOException {
        if (!destination.exists()) {
            destination.mkdir();
        }
        String[] files = source.list();
        if (files != null) {
            for (String file : files) {
                File srcFile = new File(source, file);
                File destFile = new File(destination, file);
                if (srcFile.isDirectory()) {
                    moveDirectory(srcFile, destFile);
                } else {
                    srcFile.renameTo(destFile);
                }
            }
        }
    }
}