package com.project.major;

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
import java.text.SimpleDateFormat;




public class FileTablePanel extends JPanel{
    private JTable table;
    private DefaultTableModel model;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
    private boolean actionInProgress = false;
    private File currentDirectory = null;
    private JProgressBar progressBar = new JProgressBar();
    private JComponent progressView = null;
    private FileSystemView fileSystemView = FileSystemView.getFileSystemView();
    public FileTablePanel() {
        String[] columnNames = {"Name", "Date Modified", "Type", "Size", "FileObject"};

        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        // Set the background color of the table to white
        table.setBackground(Color.WHITE);
        table.setGridColor(Color.WHITE); // Optional: if grid lines exist

        // Set the default cell renderer to white background
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                        boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBackground(Color.WHITE);
                setForeground(UIManager.getColor("Table.foreground")); // Keep default text color
                return this;
            }
        });

        // Set background of JScrollPane containing the table to white
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBackground(Color.WHITE);
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
                File file = (File) model.getValueAt(row, 4); // Use hidden FileObject
                setIcon(fileSystemView.getSystemIcon(file));
                setText(file.getName());
                setHorizontalAlignment(SwingConstants.LEFT);
                return this;
            }
        });

        table.getColumnModel().getColumn(4).setMinWidth(0);
        table.getColumnModel().getColumn(4).setMaxWidth(0);
        table.getColumnModel().getColumn(4).setWidth(0);

        setLayout(new BorderLayout());
       add(scrollPane, BorderLayout.CENTER);
        // Add double-click listener to open file or folder
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0 && !actionInProgress) {
                    File file = (File) model.getValueAt(row, 4);
                    if (file.isDirectory() && e.getClickCount() == 1) {
                        // Single-click on folder shows contents in the table
                       showFilesInDirectory(file);
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
    
    // Get the current directory being displayed
    public File getCurrentDirectory() {
        return currentDirectory;
    }

   public void showFilesInDirectory(File directory) {
    if (directory == null || !directory.isDirectory()) return;

    File[] files = directory.listFiles();
    if (files == null) return;

    currentDirectory = directory;

    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0); // Clear existing rows

    for (File file : files) {
        if (!file.isHidden()) {
            String name = file.getName();
            String type = fileSystemView.getSystemTypeDescription(file);
            String size = formatSize(getFileSize(file));
            String dateModified = dateFormat.format(new Date(file.lastModified()));

            model.addRow(new Object[]{file, dateModified, type, size, file});
        }
    }
}

private String formatSize(long sizeInBytes) {
    if (sizeInBytes < 1024) return sizeInBytes + " B";
    int exp = (int) (Math.log(sizeInBytes) / Math.log(1024));
    String pre = "KMGTPE".charAt(exp - 1) + "B";
    return String.format("%.1f %s", sizeInBytes / Math.pow(1024, exp), pre);
}
private long getFileSize(File file) {
    if (file.isFile()) {
        return file.length();
    } else if (file.isDirectory()) {
        long size = 0;
        File[] files = file.listFiles();
        if (files != null) {
            for (File child : files) {
                size += getFileSize(child);
            }
        }
        return size;
    }
    return 0;
}

public void updateTable(File directory) {
        this.currentDirectory = directory;
        setActionInProgress(true);
        
        // Use our worker thread to update the table in the background
        TableUpdateWorker worker = new TableUpdateWorker(model, directory, dateFormat);
        worker.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName()) && 
                SwingWorker.StateValue.DONE == evt.getNewValue()) {
                setActionInProgress(false);
            }
        });
        worker.execute();
    }
    public void searchFiles(String searchText) {
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0); // Clear existing rows

    if (currentDirectory == null || !currentDirectory.isDirectory()) return;

    File[] files = currentDirectory.listFiles();
    if (files == null) return;

    for (File file : files) {
        if (!file.isHidden() && (file.getName().toLowerCase().contains(searchText.toLowerCase()) || 
                                 fileSystemView.getSystemTypeDescription(file).toLowerCase().contains(searchText.toLowerCase()))) {
            String name = file.getName();
            String type = fileSystemView.getSystemTypeDescription(file);
            String size = formatSize(getFileSize(file));
            String dateModified = dateFormat.format(new Date(file.lastModified()));

            model.addRow(new Object[]{name, dateModified, type, size, file});
        }
    }
}
    public void globalSearch(String searchText) {
        setActionInProgress(true);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        SwingWorker<Void, Void> searchWorker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                File[] roots = File.listRoots();
                for (File root : roots) {
                    searchRecursively(root, searchText);
                }
                return null;
            }

            @Override
            protected void done() {
                setActionInProgress(false);
            }

            private void searchRecursively(File dir, String searchText) {
                if (dir == null || !dir.exists() || dir.isHidden()) return;

                File[] files = dir.listFiles();
                if (files == null) return;

                for (File file : files) {
                    if (isCancelled()) return;

                    if (!file.isHidden() && (file.getName().toLowerCase().contains(searchText.toLowerCase()) ||
                            fileSystemView.getSystemTypeDescription(file).toLowerCase().contains(searchText.toLowerCase()))) {
                        String name = file.getName();
                        String type = fileSystemView.getSystemTypeDescription(file);
                        String size = formatSize(getFileSize(file));
                        String dateModified = dateFormat.format(new Date(file.lastModified()));

                        SwingUtilities.invokeLater(() -> model.addRow(new Object[]{name, dateModified, type, size, file}));
                    }

                    if (file.isDirectory()) {
                        searchRecursively(file, searchText); // Recurse
                    }
                }
            }
        };

        searchWorker.execute();
    }

}