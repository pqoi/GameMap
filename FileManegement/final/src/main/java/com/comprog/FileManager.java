package com.comprog;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileManager {

    public boolean createFile(String path) {
        File file = new File(path);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                return true;
            } else {
                System.out.println("File already exists.");
                return false;
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean createFolder(String path) {
        File folder = new File(path);
        if (folder.mkdir()) {
            System.out.println("Folder created: " + folder.getName());
            return true;
        } else {
            System.out.println("Folder already exists or failed to create.");
            return false;
        }
    }

    public boolean deleteFileOrFolder(String path) {
        File fileOrFolder = new File(path);
        if (fileOrFolder.delete()) {
            System.out.println("Deleted: " + fileOrFolder.getName());
            return true;
        } else {
            System.out.println("Failed to delete: " + fileOrFolder.getName());
            return false;
        }
    }

    public boolean renameFileOrFolder(String oldPath, String newName) {
        File oldFile = new File(oldPath);
        File newFile = new File(oldFile.getParent(), newName);
        if (oldFile.renameTo(newFile)) {
            System.out.println("Renamed successfully");
            return true;
        } else {
            System.out.println("Rename failed");
            return false;
        }
    }

    public boolean moveFile(String source, String destination) {
        File srcFile = new File(source);
        File destFile = new File(destination, srcFile.getName());
        try {
            Files.move(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Moved to: " + destFile.getAbsolutePath());
            return true;
        } catch (IOException e) {
            System.out.println("Move failed");
            e.printStackTrace();
            return false;
        }
    }

    public boolean copyFile(String source, String destination) {
        File srcFile = new File(source);
        File destFile = new File(destination, srcFile.getName());
        try {
            Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copied to: " + destFile.getAbsolutePath());
            return true;
        } catch (IOException e) {
            System.out.println("Copy failed");
            e.printStackTrace();
            return false;
        }
    }

    public void readFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Failed to read file.");
            e.printStackTrace();
        }
    }

    public void writeFile(String path, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(content);
            System.out.println("Written to file successfully.");
        } catch (IOException e) {
            System.out.println("Failed to write to file.");
            e.printStackTrace();
        }
    }

    public void getMetadata(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        System.out.println("Name: " + file.getName());
        System.out.println("Path: " + file.getAbsolutePath());
        System.out.println("Size: " + file.length() + " bytes");
        System.out.println("Readable: " + file.canRead());
        System.out.println("Writable: " + file.canWrite());
        System.out.println("Last Modified: " + sdf.format(new Date(file.lastModified())));
    }

    public void search(String directoryPath, String keyword, String fileType) {
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Directory not found.");
            return;
        }
        recursiveSearch(directory, keyword, fileType);
    }

    private void recursiveSearch(File dir, String keyword, String fileType) {
        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                recursiveSearch(file, keyword, fileType);
            } else {
                boolean matchName = file.getName().toLowerCase().contains(keyword.toLowerCase());
                boolean matchType = fileType == null || file.getName().endsWith(fileType);
                if (matchName && matchType) {
                    System.out.println("Found: " + file.getAbsolutePath());
                }
            }
        }
    }

}
