// === FileTreePanel.java ===
package com.comprog;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.io.File;

public class FileTreePanel extends JPanel {
    private JTree fileTree;

    public FileTreePanel() {
        setLayout(new BorderLayout());

        // Use system root drives as root nodes
        File[] roots = File.listRoots();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Computer");
        for (File fileRoot : roots) {
            DefaultMutableTreeNode driveNode = new DefaultMutableTreeNode(new FileNode(fileRoot));
            root.add(driveNode);
            driveNode.add(new DefaultMutableTreeNode("Loading...")); // Placeholder for lazy loading
        }

        fileTree = new JTree(root);
        fileTree.setCellRenderer(new FileTreeCellRenderer());
        fileTree.addTreeWillExpandListener(new FileTreeExpandListener());

        JScrollPane scrollPane = new JScrollPane(fileTree);
        add(scrollPane, BorderLayout.CENTER);
    }

    public JTree getFileTree() {
        return fileTree;
    }

    public File getSelectedDirectory() {
        TreePath path = fileTree.getSelectionPath();
        if (path != null) {
            Object lastPathComponent = path.getLastPathComponent();
            if (lastPathComponent instanceof DefaultMutableTreeNode) {
                Object userObject = ((DefaultMutableTreeNode) lastPathComponent).getUserObject();
                if (userObject instanceof FileNode) {
                    return ((FileNode) userObject).getFile();
                }
            }
        }
        return null;
    }
}