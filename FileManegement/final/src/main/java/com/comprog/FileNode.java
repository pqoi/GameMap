package com.comprog;

import java.io.File;

public class FileNode {
    private File file;

    public FileNode(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        return file.getName().isEmpty() ? file.getPath() : file.getName();
    }
}