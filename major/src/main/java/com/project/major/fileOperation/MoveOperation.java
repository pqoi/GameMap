

package com.project.major.fileOperation;

import com.project.major.FileFrame;
import com.project.major.FileTablePanel;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class MoveOperation {
    private FileFrame fileFrame;

    public MoveOperation(FileFrame fileFrame) {
        this.fileFrame = fileFrame;
    }

 public boolean moveFileOrDirectory(File source, File targetDir) {
    try {
        if (!source.exists()) {
            throw new IOException("Source file does not exist.");
        }

        File targetFile = new File(targetDir, source.getName());

        // Use NIO for moving (supports folders recursively)
        Files.move(source.toPath(), targetFile.toPath(),
                   StandardCopyOption.REPLACE_EXISTING);

        return true;
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(fileFrame, "Error moving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}
}
