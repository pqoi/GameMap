package com.project.major;

import javax.swing.*;

import com.project.major.fileOperation.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class FileFrame extends JFrame {
    private FileTreePanel treePanel;
    private FileTablePanel tablePanel;
    private searchResultsPanel searchResultsPanel;

    public FileFrame() {
        // Frame setup
        setTitle("File Manager");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Initialize panels
        tablePanel = new FileTablePanel();
        treePanel = new FileTreePanel(tablePanel);
        searchResultsPanel = new searchResultsPanel();

        // Top Panel - holds buttons and search bar
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // padding

        // Button Panel - Left-aligned
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setOpaque(false);

        // Create buttons
        CreateButton createBtn = new CreateButton(this);
        DeleteButton deleteBtn = new DeleteButton(this);
        RenameButton renameBtn = new RenameButton(this);
        MoveButton moveBtn = new MoveButton(this);
        CreateFolderButton createFolderBtn = new CreateFolderButton(this);
        // Add buttons to buttonPanel
        buttonPanel.add(createBtn.getButton());
        buttonPanel.add(deleteBtn.getButton());
        buttonPanel.add(renameBtn.getButton());
        buttonPanel.add(moveBtn.getButton());
        buttonPanel.add(createFolderBtn.getButton());

        // Search Panel - Right-aligned
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setOpaque(false);

        // Search text field
        JTextField searchBar = new JTextField("Search files or folders...");
        searchBar.setForeground(Color.GRAY);
        searchBar.setPreferredSize(new Dimension(200, 30));

        // Placeholder behavior
        searchBar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchBar.getText().equals("Search files or folders...")) {
                    searchBar.setText("");
                    searchBar.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchBar.getText().isEmpty()) {
                    searchBar.setText("Search files or folders...");
                    searchBar.setForeground(Color.GRAY);
                }
            }
        });

        // Search functionality
        searchBar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = searchBar.getText();
                if (!searchText.isEmpty() && !searchText.equals("Search files or folders...")) {
                    tablePanel.searchFiles(searchText);
                    searchResultsPanel.updateSearchResults(searchText);
                } else {
                    tablePanel.showFilesInDirectory(tablePanel.getCurrentDirectory());
                    searchResultsPanel.clearSearchResults();
                }
            }
        });

        // Add search bar to searchPanel
        searchPanel.add(searchBar);

        // Add both panels to topPanel
        topPanel.add(buttonPanel, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.EAST);
        topPanel.add(searchResultsPanel, BorderLayout.SOUTH); // Optional: show results below

        // Split pane: left = treePanel, right = tablePanel
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePanel, tablePanel);
        splitPane.setDividerLocation(300); // default width of the tree panel

        // Add everything to frame
        add(topPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);

        // Show initial directory
        File desktop = new File(System.getProperty("user.home"), "Desktop");
        if (desktop.exists()) {
            tablePanel.showFilesInDirectory(desktop);
        }

        // Make visible
        setVisible(true);
    }

    public FileTreePanel getTreePanel() {
        return treePanel;
    }

    public FileTablePanel getTablePanel() {
        return tablePanel;
    }
     

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FileFrame());
    }
}