package com.comprog;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.filechooser.FileSystemView;

public class FileTablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
    private boolean actionInProgress = false;

    public FileTablePanel() {
        String[] columnNames = {"Name", "Date Modified", "Type", "Size", "FileObject"};

        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setDefaultEditor(Object.class, null); // <-- Add this line to prevent renaming on double-click

        // 1. Disable table body grid lines
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);

        // 2. Set table header to keep grid/border
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(SwingConstants.CENTER);
                setBorder(UIManager.getBorder("TableHeader.cellBorder")); // Keep grid border
                setOpaque(true);
                setBackground(UIManager.getColor("TableHeader.background"));
                setForeground(UIManager.getColor("TableHeader.foreground"));
                setFont(UIManager.getFont("TableHeader.font"));
            }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                return this;
            }
        });

        // Center-align only "Date Modified", "Type", and "Size"
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // "Date Modified"
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); // "Type"
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); // "Size"
        // "Name" (column 0) remains left-aligned by default
        // Custom renderer for "Name" column to include system icon
        table.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                File file = (File) model.getValueAt(row, 4); // assuming you add File object at column 4 (hidden)
                setIcon(FileSystemView.getFileSystemView().getSystemIcon(file));
                setText(file.getName()); // ensure we show the filename
                setHorizontalAlignment(SwingConstants.LEFT); // left-align
                return this;
            }
        });
        table.getColumnModel().getColumn(4).setMinWidth(0);
        table.getColumnModel().getColumn(4).setMaxWidth(0);
        table.getColumnModel().getColumn(4).setWidth(0);

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Add double-click listener to open file or folder
       table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0 && !actionInProgress) {
                    File file = (File) model.getValueAt(row, 4);
                    if (file.isDirectory() && e.getClickCount() == 1) {
                        // Single-click on folder shows contents in the table
                        openFolder(file);
                    } else if (file.isFile() && e.getClickCount() == 2) {
                        // Double-click on file opens it in system default program
                        openFile(file);
                    }
                }
            }
        });

    }
    public File getSelectedDirectory() {
    int selectedRow = getSelectedRow();
    if (selectedRow >= 0) {
        File file = (File) model.getValueAt(selectedRow, 4);
        if (file.isDirectory()) {
            return file;
        }
    }
    return null;
}

    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void updateTable(File directory) {
        model.setRowCount(0);
        if (directory != null && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    model.addRow(new Object[]{
                        file.getName(),
                        dateFormat.format(new Date(file.lastModified())),
                        getFileType(file),
                        formatSize(file.length()),
                        file // hidden actual File object
                    });
                }
            }
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

    private void openFile(File file) {
        actionInProgress = true;
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error opening file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                actionInProgress = false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Desktop operations are not supported.", "Error", JOptionPane.ERROR_MESSAGE);
            actionInProgress = false;
        }
    }

    private void openFolder(File folder) {
    actionInProgress = true;
    try {
        updateTable(folder); // <-- This updates the JTable with contents of the folder
    } finally {
        actionInProgress = false;
    }
}

    public void clearSelection() {
        table.clearSelection();
    }
    
    // Method to set action flag - can be used by other components
    public void setActionInProgress(boolean inProgress) {
        this.actionInProgress = inProgress;
    }
    
    public boolean isActionInProgress() {
        return actionInProgress;
    }
}