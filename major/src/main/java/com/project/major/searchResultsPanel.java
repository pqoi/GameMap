package com.project.major;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class searchResultsPanel extends JPanel {
    private Map<String, DefaultListModel<String>> typeMap;

    public searchResultsPanel() {
        setLayout(new BorderLayout());
        typeMap = new HashMap<>();
    }

    public void updateSearchResults(final String searchText) {
        clearSearchResults();

        // Use SwingWorker to perform the search in a background thread
        SwingWorker<Void, Object[]> worker = new SwingWorker<Void, Object[]>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Search across all drives (C: and D:)
                File[] roots = File.listRoots();
                for (File root : roots) {
                    searchDirectory(root, searchText);
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Object[]> chunks) {
                for (Object[] chunk : chunks) {
                    String type = (String) chunk[0];
                    String path = (String) chunk[1];
                    DefaultListModel<String> model = typeMap.getOrDefault(type, new DefaultListModel<>());
                    model.addElement(path);
                    typeMap.put(type, model);
                }
            }

            @Override
            protected void done() {
                // Display results grouped by type
                for (Map.Entry<String, DefaultListModel<String>> entry : typeMap.entrySet()) {
                    JList<String> list = new JList<>(entry.getValue());
                    JScrollPane scrollPane = new JScrollPane(list);
                    add(scrollPane, BorderLayout.CENTER);
                }
                revalidate();
                repaint();
            }
        };
        worker.execute();
    }

    private void searchDirectory(File directory, String searchText) {
        File[] files = directory.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (!file.isHidden() && (file.getName().toLowerCase().startsWith(searchText.toLowerCase()))) {
                String type = getFileType(file);
                publish(new Object[]{type, file.getAbsolutePath()});
            }

            if (file.isDirectory()) {
                searchDirectory(file, searchText);
            }
        }
    }

    private void publish(Object[] objects) {
        // This method is called from the background thread to publish results
        SwingUtilities.invokeLater(() -> {
            String type = (String) objects[0];
            String path = (String) objects[1];
            DefaultListModel<String> model = typeMap.getOrDefault(type, new DefaultListModel<>());
            model.addElement(path);
            typeMap.put(type, model);
        });
    }

    private String getFileType(File file) {
        if (file.isDirectory()) {
            return "Folders";
        } else {
            String name = file.getName();
            int index = name.lastIndexOf('.');
            if (index > 0) {
                return name.substring(index + 1).toUpperCase();
            } else {
                return "Unknown";
            }
        }
    }

    public void clearSearchResults() {
        typeMap.clear();
        removeAll();
        revalidate();
        repaint();
    }
}