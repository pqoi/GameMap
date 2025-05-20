package com.project.major;

import java.io.File;
import java.util.Objects;

/**
 * A wrapper class for File objects used in the file tree.
 * Provides better display names and equality checks for Swing tree nodes.
 */
public class FileNode {
    private File file;

    public FileNode(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        if (file == null) return "Invalid FileNode";

        // Root drives like C:\ have no parent
        if (file.getParentFile() == null) {
            return file.getPath(); // e.g., "C:\"
        } else {
            return file.getName(); // e.g., "Documents"
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FileNode)) return false;

        FileNode other = (FileNode) obj;
        return Objects.equals(this.file, other.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file);
    }
}