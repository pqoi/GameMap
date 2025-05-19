package com.project.major;

import javax.swing.*;

import com.project.major.fileOperation.CreateButton;
import com.project.major.fileOperation.CreateFolderButton;
import com.project.major.fileOperation.DeleteButton;
import com.project.major.fileOperation.MoveButton;
import com.project.major.fileOperation.RenameButton;

import java.awt.*;
import java.awt.event.*;
import java.io.File;


public class FileFrame extends FileTreePanel {
    private FileTreePanel treePanel;
    private FileTablePanel tablePanel;

    public FileFrame() {
        super(null); // Pass an appropriate FileTablePanel or required argument here
        JFrame frame = new JFrame("File Manager");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        // Tree and Table Panels
        tablePanel = new FileTablePanel();
        treePanel = new FileTreePanel(tablePanel);
        // Top panel
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel searchPanel = new JPanel();
        // Create button instances
        CreateButton createBtn = new CreateButton(this); // Pass the FileFrame instance
        // In FileFrame.java
        DeleteButton deleteBtn = new DeleteButton(this); // Pass the FileFrame instance
        RenameButton renameBtn = new RenameButton(this);
        //MoveButton moveBtn = new MoveButton();
        
        //CreateFolderButton createFolderBtn = new CreateFolderButton();

        // Add buttons to the searchPanel (not topPanel)
        searchPanel.add(createBtn.getButton());
        searchPanel.add(deleteBtn.getButton());
        searchPanel.add(renameBtn.getButton());
        //searchPanel.add(moveBtn.getButton());
        //searchPanel.add(createFolderBtn.getButton());

        topPanel.add(searchPanel, BorderLayout.WEST);
        
        frame.add(topPanel, BorderLayout.NORTH);
      
        topPanel.add(searchPanel, BorderLayout.WEST);
        
        frame.add(topPanel, BorderLayout.NORTH);

        

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePanel, tablePanel);
        splitPane.setDividerLocation(300);
        frame.add(splitPane, BorderLayout.CENTER);

       

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