package com.comprog;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.io.File;
import javax.swing.tree.ExpandVetoException;

public class FileTreeExpandListener implements TreeWillExpandListener {
    @Override
    public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
        TreePath path = event.getPath();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
        node.removeAllChildren();

        Object userObject = node.getUserObject();
        if (userObject instanceof FileNode) {
            File file = ((FileNode) userObject).getFile();
            File[] files = file.listFiles();
            if (files != null) {
                for (File child : files) {
                    if (child.isDirectory()) {
                        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new FileNode(child));
                        childNode.add(new DefaultMutableTreeNode("Loading..."));
                        node.add(childNode);
                    }
                }
            }
        }
    }

    @Override
    public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
        // No action needed
    }
}
