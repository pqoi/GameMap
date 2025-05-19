package com.project.major;

import java.io.File;
import java.util.Objects;

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
        if (file.getParentFile() == null) {
            // This is a root drive - show the path (like "C:\")
            return file.getPath();
        } else {
            // This is a regular file or directory - show only the name
            return file.getName();
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FileNode other = (FileNode) obj;
        return Objects.equals(file, other.file);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(file);
    }
}