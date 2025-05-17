package com.comprog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import com.comprog.fileOperation.*;

public class FileFrame {
    private FileTreePanel treePanel;
    private FileTablePanel tablePanel;

    public FileFrame() {
        JFrame frame = new JFrame("File Manager");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        // Tree and Table Panels
        treePanel = new FileTreePanel();
        tablePanel = new FileTablePanel();
        // Top panel
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel searchPanel = new JPanel();
       
        // Create button instances
        CreateButton createBtn = new CreateButton(this); // Pass the FileFrame instance
        // In FileFrame.java
        DeleteButton deleteBtn = new DeleteButton(this); // Pass the FileFrame instance
        RenameButton renameBtn = new RenameButton();
        CopyButton copyBtn = new CopyButton();
        PasteButton pasteBtn = new PasteButton();
        CreateFolderButton createFolderBtn = new CreateFolderButton(this);

        // Add buttons to the searchPanel (not topPanel)
        searchPanel.add(createBtn.getButton());
        searchPanel.add(deleteBtn.getButton());
        searchPanel.add(renameBtn.getButton());
        searchPanel.add(copyBtn.getButton());
        searchPanel.add(pasteBtn.getButton());
        searchPanel.add(createFolderBtn.getButton());

        topPanel.add(searchPanel, BorderLayout.WEST);
        
        frame.add(topPanel, BorderLayout.NORTH);

        

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePanel, tablePanel);
        splitPane.setDividerLocation(300);
        frame.add(splitPane, BorderLayout.CENTER);

        // Tree selection listener to update table
        treePanel.getFileTree().addTreeSelectionListener(e -> {
            File selectedDir = treePanel.getSelectedDirectory();
            tablePanel.updateTable(selectedDir);
        });
    

        
        // Tree selection listener to update table
        treePanel.getFileTree().addTreeSelectionListener(e -> {
            File selectedDir = treePanel.getSelectedDirectory();
            tablePanel.updateTable(selectedDir);
        });

        frame.setVisible(true);
    }

    public FileTreePanel getTreePanel() {
        return treePanel;
    }

    public FileTablePanel getTablePanel() {
        return tablePanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FileFrame::new);
    }
}