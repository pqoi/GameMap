package com.project.major;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.io.File;

public class FileTreePanel extends JPanel {
    private JTree tree;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode rootNode;

    public FileTreePanel(FileTablePanel fileTablePanel) {
    setLayout(new BorderLayout());
    rootNode = new DefaultMutableTreeNode("Computer");
    treeModel = new DefaultTreeModel(rootNode);
    tree = new JTree(treeModel);

    File[] roots = File.listRoots();
    for (File root : roots) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(root);
        rootNode.add(node);
        populateTree(node, root);
    }

    // Add listener to update FileTablePanel when a node is selected
    tree.addTreeSelectionListener(e -> {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (selectedNode != null) {
            Object userObject = selectedNode.getUserObject();
            if (userObject instanceof File) {
                File selectedFile = (File) userObject;
                fileTablePanel.showFilesInDirectory(selectedFile);
            }
        }
    });

    tree.expandRow(0); // Expand 'Computer'
    JScrollPane scrollPane = new JScrollPane(tree);
    add(scrollPane, BorderLayout.CENTER);
}


    private void populateTree(DefaultMutableTreeNode parentNode, File dir) {
        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory() && !file.isHidden()) {
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(file);
                parentNode.add(childNode);
                // One level of lazy loading for better performance
            }
        }
    }


    public File getSelectedDirectory() {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (selectedNode != null) {
            Object userObject = selectedNode.getUserObject();
            if (userObject instanceof File) {
                return (File) userObject;
            }
        }
        return null;
    }
     public void updateTree() {
        // Start a background task to refresh the tree
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                return null; // No intensive work in this method
            }
            
            @Override
            protected void done() {
                DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
                root.removeAllChildren(); // Remove all existing nodes

                // Rebuild the tree with the current file system state
                File[] roots = File.listRoots();
                for (File fileRoot : roots) {
                    DefaultMutableTreeNode driveNode = new DefaultMutableTreeNode(new FileNode(fileRoot));
                    root.add(driveNode);
                    driveNode.add(new DefaultMutableTreeNode("Loading...")); // Placeholder for lazy loading
                }

                // Notify the tree that the model has changed
                ((DefaultTreeModel) tree.getModel()).reload(root);
            }
        };
        
        worker.execute();
    }
     // Helper class to store node search results
    private static class NodeSearchResult {
        DefaultMutableTreeNode node;
        
        NodeSearchResult(DefaultMutableTreeNode node) {
            this.node = node;
        }
    }
    
    // Improved helper method to find a tree node that represents a specific file
    private NodeSearchResult findNodeForFile(DefaultMutableTreeNode currentNode, File targetFile) {
        // Skip placeholder and message nodes
        Object userObject = currentNode.getUserObject();
        if (userObject instanceof String) {
            return null;
        }
        
        // Check if this is the Computer root node
        if (userObject instanceof String && "Computer".equals(userObject)) {
            // Search among drive children
            for (int i = 0; i < currentNode.getChildCount(); i++) {
                DefaultMutableTreeNode child = (DefaultMutableTreeNode) currentNode.getChildAt(i);
                NodeSearchResult result = findNodeForFile(child, targetFile);
                if (result != null) {
                    return result;
                }
            }
            return null;
        }
        
        // If this is a FileNode, check if it matches
        if (userObject instanceof FileNode) {
            File nodeFile = ((FileNode) userObject).getFile();
            
            // Check for direct match
            if (nodeFile.equals(targetFile)) {
                return new NodeSearchResult(currentNode);
            }
            
            // If target is inside this directory and node is expanded
            if (targetFile.getPath().startsWith(nodeFile.getPath()) && 
                currentNode.getChildCount() > 0 && 
                !(((DefaultMutableTreeNode) currentNode.getChildAt(0)).getUserObject() instanceof String)) {
                
                // Search child nodes
                for (int i = 0; i < currentNode.getChildCount(); i++) {
                    DefaultMutableTreeNode child = (DefaultMutableTreeNode) currentNode.getChildAt(i);
                    NodeSearchResult result = findNodeForFile(child, targetFile);
                    if (result != null) {
                        return result;
                    }
                }
            }
        }
        
        return null; // Not found in this branch
    }
     // Improved method to update a specific node in the tree after rename
    public void updateNodeAfterRename(File oldFile, File newFile) {
        SwingWorker<NodeSearchResult, Void> worker = new SwingWorker<NodeSearchResult, Void>() {
            @Override
            protected NodeSearchResult doInBackground() {
                // Find the node in the tree that corresponds to the renamed file
                DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
                return findNodeForFile(root, oldFile);
            }
            
            @Override
            protected void done() {
                try {
                    NodeSearchResult result = get();
                    if (result != null && result.node != null) {
                        DefaultMutableTreeNode node = result.node;
                        // Update the node with the new file information
                        node.setUserObject(new FileNode(newFile));
                        
                        // Update the tree display
                        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                        model.nodeChanged(node);
                        
                        // If it's a directory with children, we should handle path updates
                        if (newFile.isDirectory() && node.getChildCount() > 0) {
                            // Preserve expanded state
                            boolean wasExpanded = tree.isExpanded(new TreePath(node.getPath()));
                        
                            // For large directories, we'll reload instead of recursively updating
                            // This approach is more efficient for the EDT
                            if (node.getChildCount() > 10) {
                                // Just clear and add a loading placeholder
                                node.removeAllChildren();
                                node.add(new DefaultMutableTreeNode("Loading..."));
                            } else {
                                // For smaller directories, update each child node
                                updateChildNodesNonRecursive(node, oldFile.getPath(), newFile.getPath());
                            }
                            
                            // Reload the node
                            model.reload(node);
                            
                            // Restore expanded state if it was expanded 
                            if (wasExpanded) {
                                tree.expandPath(new TreePath(node.getPath()));
                            }
                        }
                    } else {
                        // If node not found, refresh the entire tree
                        // This is a fallback mechanism
                        updateTree();
                    }
                } catch (Exception e) {
                    System.err.println("Error updating tree node: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        };
        
        worker.execute();
    }
    
    // Non-recursive method to update child node paths
    private void updateChildNodesNonRecursive(DefaultMutableTreeNode parentNode, String oldParentPath, String newParentPath) {
        for (int i = 0; i < parentNode.getChildCount(); i++) {
            DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) parentNode.getChildAt(i);
            Object userObject = childNode.getUserObject();
            
            // Skip placeholder nodes
            if (!(userObject instanceof FileNode)) {
                continue;
            }
            
            FileNode fileNode = (FileNode) userObject;
            File oldFile = fileNode.getFile();
            
            // Create new path by replacing old parent path with new one
            String childPath = oldFile.getPath();
            String newChildPath = childPath.replace(oldParentPath, newParentPath);
            File newFile = new File(newChildPath);
            
            // Update this node
            childNode.setUserObject(new FileNode(newFile));
            
            // Don't recursively process - just set a placeholder if this is a directory
            if (oldFile.isDirectory() && childNode.getChildCount() > 0) {
                childNode.removeAllChildren();
                childNode.add(new DefaultMutableTreeNode("Loading..."));
            }
        }
    }
}
   