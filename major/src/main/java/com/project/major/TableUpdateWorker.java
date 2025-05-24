package com.project.major;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TableUpdateWorker extends SwingWorker<List<Object[]>, Void> {
    private final DefaultTableModel model;
    private final File directory;
    private final SimpleDateFormat dateFormat;

    public TableUpdateWorker(DefaultTableModel model, File directory, SimpleDateFormat dateFormat) {
        this.model = model;
        this.directory = directory;
        this.dateFormat = dateFormat;
    }

    @Override
    protected List<Object[]> doInBackground() throws Exception {
        List<Object[]> rows = new ArrayList<>();
        
        if (directory != null && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    Object[] row = new Object[]{
                        file.getName(),
                        dateFormat.format(new Date(file.lastModified())),
                        getFileType(file),
                        formatSize(file.length()),
                        file // hidden actual File object
                    };
                    rows.add(row);
                }
            }
        }
        
        return rows;
    }

    @Override
    protected void done() {
        try {
            // Clear the table first
            model.setRowCount(0);
            
            // Get the rows from background processing
            List<Object[]> rows = get();
            
            // Add all rows to the table model
            for (Object[] row : rows) {
                model.addRow(row);
            }
        } catch (Exception e) {
            System.err.println("Error updating table: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private String getFileType(File file) {
        if (file.isDirectory()) return "Folder";
        String name = file.getName();
        int dotIndex = name.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < name.length() - 1) {
            return name.substring(dotIndex + 1).toUpperCase() + " File";
        }
        return "Unknown";
    }

    private String formatSize(long sizeInBytes) {
        if (sizeInBytes >= 1024L * 1024 * 1024)
            return String.format("%.2f GB", sizeInBytes / (1024.0 * 1024 * 1024));
        else if (sizeInBytes >= 1024L * 1024)
            return String.format("%.2f MB", sizeInBytes / (1024.0 * 1024));
        else
            return String.format("%.2f KB", sizeInBytes / 1024.0);
    }
}