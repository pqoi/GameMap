package com.comprog;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

public class FileNode extends DefaultMutableTreeNode {

    private final File file;

    public FileNode(File file) {
        super(file.getName().isEmpty() ? file.getPath() : file.getName());
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public boolean isDirectory() {
        return file.isDirectory();
    }

    public boolean isFile() {
        return file.isFile();
    }

    @Override
    public String toString() {
        return file.getName().isEmpty() ? file.getPath() : file.getName();
    }

    public File[] listFiles() {
        return file.listFiles();
    }
}
