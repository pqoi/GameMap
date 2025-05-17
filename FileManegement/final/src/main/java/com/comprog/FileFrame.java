package com.comprog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class FileFrame {
    private FileTreePanel treePanel;
    private FileTablePanel tablePanel;

    public FileFrame() {
        JFrame frame = new JFrame("File Manager");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Top panel
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel searchPanel = new JPanel();
       

        JButton CreateButton = new JButton("Create");
        JButton DeleteButton = new JButton("Delete");
        JButton RenameButton = new JButton("Rename");
        JButton CopyButton = new JButton("Copy");
        JButton PasteButton = new JButton("Paste");
        JButton CreateFolderButton = new JButton("Create Folder");
        topPanel.add(searchPanel, BorderLayout.CENTER);
        
        frame.add(topPanel, BorderLayout.NORTH);

        // Tree and Table Panels
        treePanel = new FileTreePanel();
        tablePanel = new FileTablePanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePanel, tablePanel);
        splitPane.setDividerLocation(300);
        frame.add(splitPane, BorderLayout.CENTER);

       

        // Tree selection listener to update table
        treePanel.getFileTree().addTreeSelectionListener(e -> {
            File selectedDir = treePanel.getSelectedDirectory();
            tablePanel.updateTable(selectedDir);
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FileFrame::new);
    }
}
