package com.comprog;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Date;

public class SimpleFileManager extends JFrame {
    private JTree fileTree;
    private DefaultTreeModel treeModel;
    private JTextField pathField;
    private JTextArea fileDetailsArea;
    private JPanel fileViewPanel;
    
    public SimpleFileManager() {
        // Set up the frame
        super("Simple File Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        
        // Create components
        createMenuBar();
        createFileTree();
        createPathPanel();
        createFileDetailsPanel();
        createFileViewPanel();
        
        // Arrange components
        JSplitPane leftSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, 
                new JScrollPane(fileTree), fileDetailsArea);
        leftSplitPane.setDividerLocation(400);
        
        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, 
                leftSplitPane, fileViewPanel);
        mainSplitPane.setDividerLocation(300);
        
        add(pathField, BorderLayout.NORTH);
        add(mainSplitPane, BorderLayout.CENTER);
    }
    
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        
        JMenu viewMenu = new JMenu("View");
        JMenuItem refreshItem = new JMenuItem("Refresh");
        refreshItem.addActionListener(e -> refreshFileTree());
        viewMenu.add(refreshItem);
        
        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        setJMenuBar(menuBar);
    }
    
    private void createFileTree() {
        // Create the root node
        File[] roots = File.listRoots();
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("File System");
        
        // Add root directories
        for (File root : roots) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(root);
            rootNode.add(node);
            
            // Add dummy node to allow expansion
            node.add(new DefaultMutableTreeNode("Loading..."));
        }
        
        treeModel = new DefaultTreeModel(rootNode);
        fileTree = new JTree(treeModel);
        fileTree.setCellRenderer(new FileTreeCellRenderer());
        
        // Configure tree appearance
        fileTree.setShowsRootHandles(true);
        fileTree.setRootVisible(true);
        
        // Add tree expansion listener
        fileTree.addTreeExpansionListener(new TreeExpansionListener() {
            @Override
            public void treeExpanded(TreeExpansionEvent event) {
                TreePath path = event.getPath();
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                
                // If this node has a dummy child, remove it and add real children
                if (node.getChildCount() == 1 && 
                    ((DefaultMutableTreeNode)node.getFirstChild()).getUserObject().equals("Loading...")) {
                    node.removeAllChildren();
                    
                    Object nodeInfo = node.getUserObject();
                    if (nodeInfo instanceof File) {
                        File dir = (File) nodeInfo;
                        populateDirectoryNode(node, dir);
                    }
                    
                    treeModel.nodeStructureChanged(node);
                }
            }
            
            @Override
            public void treeCollapsed(TreeExpansionEvent event) {
                // Do nothing
            }
        });
        
        // Add selection listener
        fileTree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) fileTree.getLastSelectedPathComponent();
            if (node == null) return;
            
            Object nodeInfo = node.getUserObject();
            if (nodeInfo instanceof File) {
                File file = (File) nodeInfo;
                updatePathField(file);
                showFileDetails(file);
                
                if (file.isDirectory()) {
                    showDirectoryContents(file);
                }
            }
        });
    }
    
    private void populateDirectoryNode(DefaultMutableTreeNode parent, File directory) {
        File[] files = directory.listFiles();
        if (files == null) return;
        
        // Add all directories first, then files
        for (File file : files) {
            if (file.isDirectory()) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(file);
                parent.add(node);
                // Add dummy node to allow expansion
                node.add(new DefaultMutableTreeNode("Loading..."));
            }
        }
        
        for (File file : files) {
            if (file.isFile()) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(file);
                parent.add(node);
            }
        }
    }
    
    private void createPathPanel() {
        pathField = new JTextField();
        pathField.setEditable(false);
    }
    
    private void createFileDetailsPanel() {
        fileDetailsArea = new JTextArea();
        fileDetailsArea.setEditable(false);
    }
    
    private void createFileViewPanel() {
        fileViewPanel = new JPanel(new BorderLayout());
        
        // Initialize with a message
        JLabel initialLabel = new JLabel("Select a directory to view contents", SwingConstants.CENTER);
        fileViewPanel.add(initialLabel, BorderLayout.CENTER);
    }
    
    private void updatePathField(File file) {
        pathField.setText(file.getAbsolutePath());
    }
    
    private void showFileDetails(File file) {
        StringBuilder details = new StringBuilder();
        details.append("Name: ").append(file.getName()).append("\n");
        details.append("Path: ").append(file.getAbsolutePath()).append("\n");
        details.append("Size: ").append(file.length()).append(" bytes\n");
        details.append("Last Modified: ").append(new Date(file.lastModified())).append("\n");
        details.append("Is Directory: ").append(file.isDirectory()).append("\n");
        details.append("Is Hidden: ").append(file.isHidden()).append("\n");
        
        fileDetailsArea.setText(details.toString());
    }
    
    private void showDirectoryContents(File directory) {
        fileViewPanel.removeAll();
        
        File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            fileViewPanel.add(new JLabel("Empty directory", SwingConstants.CENTER), BorderLayout.CENTER);
        } else {
            // Create a panel with a grid layout to show file icons
            JPanel gridPanel = new JPanel(new GridLayout(0, 4, 10, 10));
            gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            for (File file : files) {
                JLabel fileLabel = new JLabel(file.getName(), getFileIcon(file), SwingConstants.CENTER);
                fileLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
                fileLabel.setHorizontalTextPosition(SwingConstants.CENTER);
                fileLabel.setToolTipText(file.getAbsolutePath());
                
                // Add double-click to open directories
                if (file.isDirectory()) {
                    fileLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (e.getClickCount() == 2) {
                                // Find node in tree and select it
                                findAndSelectNode(file);
                            }
                        }
                    });
                }
                
                gridPanel.add(fileLabel);
            }
            
            JScrollPane scrollPane = new JScrollPane(gridPanel);
            fileViewPanel.add(scrollPane, BorderLayout.CENTER);
        }
        
        // Update UI
        fileViewPanel.revalidate();
        fileViewPanel.repaint();
    }
    
    private Icon getFileIcon(File file) {
        if (file.isDirectory()) {
            return UIManager.getIcon("FileView.directoryIcon");
        } else {
            return UIManager.getIcon("FileView.fileIcon");
        }
    }
    
    private void findAndSelectNode(File fileToFind) {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel.getRoot();
        findNodeAndSelect(root, fileToFind);
    }
    
    private boolean findNodeAndSelect(DefaultMutableTreeNode node, File fileToFind) {
        if (node.getUserObject() instanceof File) {
            File nodeFile = (File) node.getUserObject();
            if (nodeFile.equals(fileToFind)) {
                // Found the node, select it
                TreePath path = new TreePath(treeModel.getPathToRoot(node));
                fileTree.setSelectionPath(path);
                fileTree.scrollPathToVisible(path);
                return true;
            }
        }
        
        // Search children
        for (int i = 0; i < node.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
            if (findNodeAndSelect(child, fileToFind)) {
                return true;
            }
        }
        
        return false;
    }
    
    private void refreshFileTree() {
        // Save the current selection
        TreePath selectionPath = fileTree.getSelectionPath();
        
        // Recreate the tree model
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("File System");
        File[] roots = File.listRoots();
        
        for (File root : roots) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(root);
            rootNode.add(node);
            node.add(new DefaultMutableTreeNode("Loading..."));
        }
        
        treeModel.setRoot(rootNode);
        
        // Try to restore the selection if possible
        if (selectionPath != null) {
            Object[] path = selectionPath.getPath();
            // Skip the root node which may have changed
            if (path.length > 1 && path[1] instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode selectedRoot = (DefaultMutableTreeNode) path[1];
                if (selectedRoot.getUserObject() instanceof File) {
                    File selectedFile = (File) selectedRoot.getUserObject();
                    // Find and select this file again
                    findAndSelectNode(selectedFile);
                }
            }
        }
    }
    
    // Custom cell renderer for the JTree
    class FileTreeCellRenderer extends DefaultTreeCellRenderer {
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
                                                   boolean expanded, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
            
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            Object userObject = node.getUserObject();
            
            if (userObject instanceof File) {
                File file = (File) userObject;
                setText(file.getName().isEmpty() ? file.getPath() : file.getName());
                
                if (file.isDirectory()) {
                    setIcon(UIManager.getIcon("FileView.directoryIcon"));
                } else {
                    setIcon(UIManager.getIcon("FileView.fileIcon"));
                }
            }
            
            return this;
        }
    }
    
    public static void main(String[] args) {
        // Use system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            SimpleFileManager fileManager = new SimpleFileManager();
            fileManager.setVisible(true);
        });
    }
}