package com.comprog;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.io.File;

public class FileTreeCellRenderer extends DefaultTreeCellRenderer {
    private Icon fileIcon = UIManager.getIcon("FileView.fileIcon");
    private Icon folderIcon;
    private Icon driveIcon = UIManager.getIcon("FileView.hardDriveIcon");

    public FileTreeCellRenderer() {
        // Load and scale custom folder icon to 16x16
        java.net.URL folderIconUrl = getClass().getResource("/FolderIcon.png");
        if (folderIconUrl != null) {
            ImageIcon originalIcon = new ImageIcon(folderIconUrl);
            Image scaledImage = originalIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
            folderIcon = new ImageIcon(scaledImage);
        } else {
            folderIcon = UIManager.getIcon("FileView.directoryIcon"); // fallback
        }
    }

   @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel,
                                                boolean expanded, boolean leaf, int row,
                                                boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        Object userObject = ((DefaultMutableTreeNode) value).getUserObject();

        // If it's the "Computer" label
        if ("Computer".equals(userObject)) {
            setIcon(folderIcon); // Use your custom icon
        } else if (userObject instanceof FileNode) {
            File file = ((FileNode) userObject).getFile();

            if (file.isDirectory()) {
                // If this is a root drive (C:\, D:\), file.getParent() will be null
                setIcon(folderIcon); // Use the same custom icon for drives too
            } else {
                setIcon(fileIcon);
            }
        }

        return this;
    }

}
