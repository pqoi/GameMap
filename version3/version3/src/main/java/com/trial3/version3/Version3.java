package com.trial3.version3;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.*;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class Version3 {

    public static void main(String[] args) throws IOException {
        try {
            // Create terminal factory and configure it
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();

            // Create the Swing terminal  
            SwingTerminalFrame terminal = terminalFactory.createSwingTerminal();


            terminal.setTitle("");
            terminal.setVisible(true);

            // Force fullscreen after the frame is visible
            SwingUtilities.invokeLater(() -> terminal.setExtendedState(JFrame.MAXIMIZED_BOTH));

            // Create and start the screen
            Screen screen = new TerminalScreen(terminal);
            screen.startScreen();

            // Spinner frames
            String[] spinnerChars = { "⠋", "⠙", "⠹", "⠸", "⠼", "⠴", "⠦", "⠧", "⠇", "⠏" };

           // Animate loading bar (responsive)
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(1);

                screen.clear();
                TextGraphics tg = screen.newTextGraphics();
                tg.setBackgroundColor(TextColor.ANSI.BLACK);
                tg.setForegroundColor(TextColor.ANSI.WHITE);
                tg.fill(' ');

                // Get current terminal size
                TerminalSize size = screen.doResizeIfNecessary(); // Forces update if size changed
                if (size == null) {
                    size = screen.getTerminalSize(); // fallback
                }

                int centerX = size.getColumns() / 2;
                int centerY = size.getRows() / 2;

                String title = "Loading, please wait...";

                // Responsive bar length (e.g. max 70, min 20)
                int maxBarLength = Math.min(70, size.getColumns() - 20);
                int barLength = Math.max(20, maxBarLength);

                String bar = getProgressBar(i, barLength);
                String percent = i + "%";
                String spinner = spinnerChars[i % spinnerChars.length];

                // Draw centered content
                tg.putString(centerX - title.length() / 2, centerY - 2, title);
                tg.putString(centerX - (bar.length() + percent.length() + spinner.length() + 2) / 2, centerY,
                        bar + " " + percent + " " + spinner);

                screen.refresh();
            }


            // Clear loading screen
            screen.clear();
            screen.refresh();
            displayFoodPandaLogo(screen);
            screen.refresh();
            screen.clear();
            screen.refresh();
            MenuSound();
            displayFoodPandaBackground(screen);
            screen.refresh();

            // This will display the background and the menu together
            String selection = MenuSelection(screen);

            // Handle the selection
            if (selection.equals("START")) {
                // Handle START
            } else if (selection.equals("ABOUT")) {
                // Handle ABOUT
            } else if (selection.equals("EXIT")) {
                // Handle EXIT
            }
                // Refresh the screen to display the image
                screen.refresh();
                Thread.sleep(4000); // Let the image stay for 4 seconds (optional)
             
               

            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }

    // Helper to generate a progress bar string
    private static String getProgressBar(int percent, int barLength) {
        int filledLength = percent * barLength / 100;
        char[] bar = new char[barLength];
        Arrays.fill(bar, 0, filledLength, '█');
        Arrays.fill(bar, filledLength, barLength, '░');
        return "[" + new String(bar) + "]";
    }
    private static void displayFoodPandaLogo(Screen screen) throws IOException {
        try {
            InputStream imageStream = Version3.class.getClassLoader().getResourceAsStream("CuteLogo1.png");
            if (imageStream == null) {
                throw new RuntimeException("Image not found in resources");
            }
    
            BufferedImage original = ImageIO.read(imageStream);
            TextGraphics tg = screen.newTextGraphics();
            TerminalSize size = screen.getTerminalSize();
    
            int maxWidth = size.getColumns();
            int maxHeight = size.getRows() * 2;
    
            if (original.getWidth() > maxWidth || original.getHeight() > maxHeight) {
                BufferedImage scaled = new BufferedImage(maxWidth, maxHeight, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = scaled.createGraphics();
                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g.drawImage(original, 0, 0, maxWidth, maxHeight, null);
                g.dispose();
                original = scaled;
            }
    
            int fullWidth = original.getWidth();
            int fullHeight = original.getHeight();
    
            // POP IN Animation
            for (double scale = 0.2; scale <= 1.0; scale += 0.1) {
                drawImageWithScale(screen, original, scale);
                Thread.sleep(3);
            }
    
            LogoSound();
            Thread.sleep(100); // Hold full-size logo
    
            // POP OUT Animation
            for (double scale = 1.0; scale >= 0.2; scale -= 0.1) {
                drawImageWithScale(screen, original, scale);
                Thread.sleep(5);
            }
    
        } catch (Exception e) {
            System.err.println("Error displaying image:");
            e.printStackTrace();
            TextGraphics tg = screen.newTextGraphics();
            tg.setForegroundColor(TextColor.ANSI.RED);
            tg.putString(0, 0, "Error displaying image: " + e.getMessage());
            screen.refresh();
            try { Thread.sleep(3000); } catch (InterruptedException ie) {}
        }
    }
    
    // Helper: Draw scaled image centered on screen
    private static void drawImageWithScale(Screen screen, BufferedImage original, double scale) throws IOException {
        int newWidth = Math.max(1, (int)(original.getWidth() * scale));
        int newHeight = Math.max(2, (int)(original.getHeight() * scale)); // ensure even for half blocks
    
        BufferedImage scaled = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = scaled.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(original, 0, 0, newWidth, newHeight, null);
        g.dispose();
    
        TerminalSize size = screen.getTerminalSize();
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(TextColor.ANSI.BLACK);
        tg.fill(' ');
    
        int startX = (size.getColumns() - newWidth) / 2;
        int startY = (size.getRows() - newHeight / 2) / 2;
    
        for (int y = 0; y < newHeight - 1; y += 2) {
            for (int x = 0; x < newWidth; x++) {
                Color top = new Color(scaled.getRGB(x, y), false);
                Color bottom = new Color(scaled.getRGB(x, y + 1), false);
    
                TextColor.RGB bg = new TextColor.RGB(top.getRed(), top.getGreen(), top.getBlue());
                TextColor.RGB fg = new TextColor.RGB(bottom.getRed(), bottom.getGreen(), bottom.getBlue());
    
                tg.setBackgroundColor(bg);
                tg.setForegroundColor(fg);
    
                if (x + startX < size.getColumns() && (y / 2 + startY) < size.getRows()) {
                    tg.putString(x + startX, y / 2 + startY, "▄");
                }
            }
        }
    
        screen.refresh();
    }
    
    
      private static void displayFoodPandaBackground(Screen screen) throws IOException {
        try {
            // Load image from resources
            InputStream imageStream = Version3.class.getClassLoader().getResourceAsStream("FBBackground.jpg");
            if (imageStream == null) {
                throw new RuntimeException("Image not found in resources");
            }

            BufferedImage original = ImageIO.read(imageStream);
            System.out.println("Image loaded. Dimensions: " + original.getWidth() + "x" + original.getHeight());
            System.out.println("Image type: " + getImageTypeName(original.getType()));

            // Get terminal dimensions
            TextGraphics tg = screen.newTextGraphics();
            TerminalSize size = screen.getTerminalSize();
            
            // Scale image if too large
            int maxWidth = size.getColumns();
            int maxHeight = size.getRows() * 2; // *2 because we use half blocks
            if (original.getWidth() > maxWidth || original.getHeight() > maxHeight) {
                BufferedImage scaled = new BufferedImage(maxWidth, maxHeight, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = scaled.createGraphics();
                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g.drawImage(original, 0, 0, maxWidth, maxHeight, null);
                g.dispose();
                original = scaled;
                System.out.println("Image scaled to: " + maxWidth + "x" + maxHeight);
            }

            // Clear screen
            tg.setBackgroundColor(TextColor.ANSI.BLACK);
            tg.fill(' ');

            // Calculate centered position
            int startX = (size.getColumns() - original.getWidth()) / 2;
            int startY = (size.getRows() - original.getHeight() / 2) / 2;

            // Render image using half-block characters
            for (int y = 0; y < original.getHeight() - 1; y += 2) {
                for (int x = 0; x < original.getWidth(); x++) {
                    Color top = new Color(original.getRGB(x, y), false);
                    Color bottom = new Color(original.getRGB(x, y + 1), false);

                    // Skip rendering if both pixels are transparent/black
                    if (top.getRGB() == Color.BLACK.getRGB() && bottom.getRGB() == Color.BLACK.getRGB()) {
                        continue;
                    }

                    TextColor.RGB bg = new TextColor.RGB(top.getRed(), top.getGreen(), top.getBlue());
                    TextColor.RGB fg = new TextColor.RGB(bottom.getRed(), bottom.getGreen(), bottom.getBlue());

                    tg.setBackgroundColor(bg);
                    tg.setForegroundColor(fg);

                    if (x + startX < size.getColumns() && (y / 2 + startY) < size.getRows()) {
                        tg.putString(x + startX, y / 2 + startY, "▄");
                    }
                }
            }

            screen.refresh();
            
            Thread.sleep(100); // Display for 5 seconds

        } catch (Exception e) {
            System.err.println("Error displaying image:");
            e.printStackTrace();
            
            // Show error message on screen
            TextGraphics tg = screen.newTextGraphics();
            tg.setForegroundColor(TextColor.ANSI.RED);
            tg.putString(0, 0, "Error displaying image: " + e.getMessage());
            screen.refresh();
            try { Thread.sleep(3000); } catch (InterruptedException ie) {}
        }
    }
    private static String MenuSelection(Screen screen) throws IOException { 
        final String[][] menuOptions = {
            // Option 1: "START" (5 lines)
            {
                "███████╗████████╗ █████╗ ██████╗ ████████╗",
                "██╔════╝╚══██╔══╝██╔══██╗██╔══██╗╚══██╔══╝",
                "███████╗   ██║   ███████║██████╔╝   ██║   ",
                "╚════██║   ██║   ██╔══██║██╔══██╗   ██║   ",
                "███████║   ██║   ██║  ██║██║  ██║   ██║   "
            },
            // Option 2: "About" (6 lines)
            {
                " █████╗ ██████╗  ██████╗ ██╗   ██╗████████╗",
                "██╔══██╗██╔══██╗██╔═══██╗██║   ██║╚══██╔══╝",
                "███████║██████╔╝██║   ██║██║   ██║   ██║   ", 
                "██╔══██║██╔══██╗██║   ██║██║   ██║   ██║   ", 
                "██║  ██║██████╔╝╚██████╔╝╚██████╔╝   ██║   ", 
                "╚═╝  ╚═╝╚═════╝  ╚═════╝  ╚═════╝    ╚═╝   " 
            },
            // Option 3: "EXIT" (5 lines)
            {
                "███████╗██╗  ██╗██╗████████╗        ",
                "██╔════╝╚██╗██╔╝██║╚══██╔══╝        ",
                "█████╗   ╚███╔╝ ██║   ██║           ",
                "██╔══╝   ██╔██╗ ██║   ██║           ",
                "███████╗██╔╝ ██╗██║   ██║           "
            }
        };
        int selectedIndex = 0;
        boolean running = true;
        String selectedOption = "";

        // Pink color for selection
        TextColor.RGB pinkColor = new TextColor.RGB(255, 105, 180);
        TextColor.RGB whiteColor = new TextColor.RGB(255, 255, 255);

        // Spacing between menu options (adjust this value to increase/decrease spacing)
        int optionSpacing = 3; // Additional spacing between menu options

        // Display the background first - only once, not in the loop
        displayFoodPandaBackground(screen);
        
        
        // Create a copy of the background
        TextCharacter[][] backgroundCopy = captureScreenState(screen);
        
        while (running) {
            TextGraphics tg = screen.newTextGraphics();
            TerminalSize size = screen.getTerminalSize();
            
            // Position menu at left center
            int startX = 22 ; // Left margin
            int startY = size.getRows() / 2 - 10; // Adjust for better vertical positioning
            
            // Calculate the menu area total dimensions to clear
            int totalMenuHeight = 0;
            int maxMenuWidth = 0;
            
            for (String[] option : menuOptions) {
                totalMenuHeight += option.length + optionSpacing;
                for (String line : option) {
                    maxMenuWidth = Math.max(maxMenuWidth, line.length());
                }
            }
            
            // Clear the entire menu area by restoring the background first
            restoreBackgroundArea(screen, backgroundCopy, startX, startY, maxMenuWidth, totalMenuHeight + 5); // +5 for instructions
            
            // Draw menu options
            int currentY = startY;
            for (int i = 0; i < menuOptions.length; i++) {
                String[] option = menuOptions[i];
                
                // Set foreground color based on selection
                TextColor.RGB color = (i == selectedIndex) ? pinkColor : whiteColor;
                
                for (int j = 0; j < option.length; j++) {
                    // Draw each character individually to avoid changing background
                    String line = option[j];
                    for (int x = 0; x < line.length(); x++) {
                        // Get the original background character/color from our copy
                        TextCharacter bgChar = backgroundCopy[currentY + j][startX + x];
                        // Create a new character with the menu text color but keep the background
                        TextCharacter overlayChar = bgChar.withForegroundColor(color)
                                                        .withCharacter(line.charAt(x));
                        
                        // Place the new character
                        screen.setCharacter(startX + x, currentY + j, overlayChar);
                    }
                }
                
                // Move to next option with spacing
                currentY += option.length + optionSpacing;
            }
            
           
            
            screen.refresh();
            
            // Handle keyboard input
            KeyStroke key = screen.readInput();
            
            if (key.getKeyType() == KeyType.ArrowUp) {
                selectedIndex = (selectedIndex - 1 + menuOptions.length) % menuOptions.length;
            } 
            else if (key.getKeyType() == KeyType.ArrowDown) {
                selectedIndex = (selectedIndex + 1) % menuOptions.length;
            }
            else if (key.getKeyType() == KeyType.Enter) {
                // Handle selection
                selectedOption = switch (selectedIndex) {
                    case 0 -> {
                        screen.clear();
                        screen.refresh();
                        StartGame.main(null);
                        yield  "START";
                    } 
                    
                    case 1 -> {
                        screen.clear();
                        AboutContent(screen);
                        yield "ABOUT";
                    }
                    case 2 -> {
                        System.exit(0);
                        yield "EXIT";
                    }
                    default -> "";
                };
                running = false;
            }
            else if (key.getKeyType() == KeyType.Escape) {
                selectedOption = "EXIT";
                running = false;
            }
        }
        
        return selectedOption;
    }

    // Method to capture the current screen state
    private static TextCharacter[][] captureScreenState(Screen screen) throws IOException {
        TerminalSize size = screen.getTerminalSize();
        TextCharacter[][] screenCopy = new TextCharacter[size.getRows()][size.getColumns()];
        
        // Copy each character from the screen
        for (int y = 0; y < size.getRows(); y++) {
            for (int x = 0; x < size.getColumns(); x++) {
                screenCopy[y][x] = screen.getBackCharacter(x, y);
            }
        }
        
        return screenCopy;
    }

    // Method to restore a portion of the background
    private static void restoreBackgroundArea(Screen screen, TextCharacter[][] backgroundCopy, 
                                            int startX, int startY, int width, int height) {
        TerminalSize size = screen.getTerminalSize();
        
        // Make sure we stay within bounds
        int endX = Math.min(startX + width, size.getColumns());
        int endY = Math.min(startY + height, size.getRows());
        
        // Restore each character in the specified area
        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                if (y < backgroundCopy.length && x < backgroundCopy[0].length) {
                    screen.setCharacter(x, y, backgroundCopy[y][x]);
                }
            }
        }
    }
        
      private static String getImageTypeName(int type) {
        switch (type) {
            case BufferedImage.TYPE_INT_RGB: return "TYPE_INT_RGB";
            case BufferedImage.TYPE_INT_ARGB: return "TYPE_INT_ARGB";
            case BufferedImage.TYPE_3BYTE_BGR: return "TYPE_3BYTE_BGR";
            case BufferedImage.TYPE_4BYTE_ABGR: return "TYPE_4BYTE_ABGR";
            default: return "Unknown (" + type + ")";
        }
    }
    public static void LogoSound() {
        File file = new File("C:\\Code Practice\\version3\\version3\\src\\main\\resources\\FoodPandaSound.wav");
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file)) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            // Wait for the sound to finish
            while (!clip.isRunning())
                Thread.sleep(5); 
            while (clip.isRunning())
                Thread.sleep(5);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }
   public static void MenuSound() {
    File file = new File("C:\\Code Practice\\version3\\version3\\src\\main\\resources\\MenuSound.wav");
    try {
        // Create a Clip that will handle the audio playing
        Clip clip = AudioSystem.getClip();
        
        // Get the audio input stream
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        
        // Open the clip with the audio stream
        clip.open(audioInputStream);
        
        // Set the clip to loop continuously
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        
        // Start playing
        clip.start();
        
        // Optional: Add a shutdown hook to close the clip when the application exits
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (clip.isRunning()) {
                clip.stop();
                clip.close();
            }
        }));
        
    } catch (UnsupportedAudioFileException e) {
        System.err.println("Audio file format is not supported: " + e.getMessage());
        e.printStackTrace();
    } catch (IOException e) {
        System.err.println("Error reading audio file: " + e.getMessage());
        e.printStackTrace();
    } catch (LineUnavailableException e) {
        System.err.println("Audio line unavailable: " + e.getMessage());
        e.printStackTrace();
    }
                }
    private static void displayFoodPandaAbout(Screen screen) throws IOException {
        try {
            // Load image from resources
            InputStream imageStream = Version3.class.getClassLoader().getResourceAsStream("AboutFoodPanda.png");
            if (imageStream == null) {
                throw new RuntimeException("Image not found in resources");
            }

            BufferedImage original = ImageIO.read(imageStream);
            System.out.println("Image loaded. Dimensions: " + original.getWidth() + "x" + original.getHeight());
            System.out.println("Image type: " + getImageTypeName(original.getType()));

            // Get terminal dimensions
            TextGraphics tg = screen.newTextGraphics();
            TerminalSize size = screen.getTerminalSize();
            
            // Scale image if too large
            int maxWidth = size.getColumns();
            int maxHeight = size.getRows() * 2; // *2 because we use half blocks
            if (original.getWidth() > maxWidth || original.getHeight() > maxHeight) {
                BufferedImage scaled = new BufferedImage(maxWidth, maxHeight, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = scaled.createGraphics();
                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g.drawImage(original, 0, 0, maxWidth, maxHeight, null);
                g.dispose();
                original = scaled;
                System.out.println("Image scaled to: " + maxWidth + "x" + maxHeight);
            }

            // Clear screen
            tg.setBackgroundColor(TextColor.ANSI.BLACK);
            tg.fill(' ');

            // Calculate centered position
            int startX = (size.getColumns() - original.getWidth()) / 2;
            int startY = (size.getRows() - original.getHeight() / 2) / 2;

            // Render image using half-block characters
            for (int y = 0; y < original.getHeight() - 1; y += 2) {
                for (int x = 0; x < original.getWidth(); x++) {
                    Color top = new Color(original.getRGB(x, y), false);
                    Color bottom = new Color(original.getRGB(x, y + 1), false);

                    // Skip rendering if both pixels are transparent/black
                    if (top.getRGB() == Color.BLACK.getRGB() && bottom.getRGB() == Color.BLACK.getRGB()) {
                        continue;
                    }

                    TextColor.RGB bg = new TextColor.RGB(top.getRed(), top.getGreen(), top.getBlue());
                    TextColor.RGB fg = new TextColor.RGB(bottom.getRed(), bottom.getGreen(), bottom.getBlue());

                    tg.setBackgroundColor(bg);
                    tg.setForegroundColor(fg);

                    if (x + startX < size.getColumns() && (y / 2 + startY) < size.getRows()) {
                        tg.putString(x + startX, y / 2 + startY, "▄");
                    }
                }
            }

            screen.refresh();
            
            Thread.sleep(100); // Display for 5 seconds

        } catch (Exception e) {
            System.err.println("Error displaying image:");
            e.printStackTrace();
            
            // Show error message on screen
            TextGraphics tg = screen.newTextGraphics();
            tg.setForegroundColor(TextColor.ANSI.RED);
            tg.putString(0, 0, "Error displaying image: " + e.getMessage());
            screen.refresh();
            try { Thread.sleep(3000); } catch (InterruptedException ie) {}
        }
    }
    private static void AboutContent(Screen screen) throws IOException {
        // Define content without \r\n, just use each line as a separate string
        String[] content = {
            "╔╦╗┬ ┬┌─┐  ┌─┐┬─┐┌─┐┌─┐┬─┐┌─┐┌┬┐  ┌─┐┬┌┬┐┬ ┬┬  ┌─┐┌┬┐┌─┐┌─┐",
            " ║ ├─┤├┤   ├─┘├┬┘│ ││ ┬├┬┘├─┤│││  └─┐│││││ ││  ├─┤ │ ├┤ └─┐",
            " ╩ ┴ ┴└─┘  ┴  ┴└─└─┘└─┘┴└─┴ ┴┴ ┴  └─┘┴┴ ┴└─┘┴─┘┴ ┴ ┴ └─┘└─┘",
            "┌┬┐┬ ┬┌─┐  ╔═╗┌─┐┌─┐┌┬┐┌─┐┌─┐┌┐┌┌┬┐┌─┐  ┌┬┐┌─┐┬  ┬┬  ┬┌─┐┬─┐┬ ┬",
            " │ ├─┤├┤   ╠╣ │ ││ │ ││├─┘├─┤│││ ││├─┤   ││├┤ │  │└┐┌┘├┤ ├┬┘└┬┘",
            " ┴ ┴ ┴└─┘  ╚  └─┘└─┘─┴┘┴  ┴ ┴┘└┘─┴┘┴ ┴  ─┴┘└─┘┴─┘┴ └┘ └─┘┴└─ ┴",
            "┌─┐┬ ┬┌─┐┌┬┐┌─┐┌┬┐╔╦╗┬ ┬┌─┐  ┌┬┐┌─┐┬  ┬┬  ┬┌─┐┬─┐┬ ┬",
            "└─┐└┬┘└─┐ │ ├┤ │││ ║ ├─┤├┤    ││├┤ │  │└┐┌┘├┤ ├┬┘└┬┘",
            "└─┘ ┴ └─┘ ┴ └─┘┴ ┴o╩ ┴ ┴└─┘  ─┴┘└─┘┴─┘┴ └┘ └─┘┴└─ ┴",
            "┌─┐┬─┐┌─┐┌─┐┌─┐┌─┐┌─┐  ┬┌─┐  ┌┐ ┌─┐┌─┐┌─┐┌┬┐  ┌─┐┌┐┌",
            "├─┘├┬┘│ ││  ├┤ └─┐└─┐  │└─┐  ├┴┐├─┤└─┐├┤  ││  │ ││││",
            "┴  ┴└─└─┘└─┘└─┘└─┘└─┘  ┴└─┘  └─┘┴ ┴└─┘└─┘─┴┘  └─┘┘└┘",
            "┌─┐┬ ┬┌─┐┌┬┐┌─┐┌┬┐┌─┐┬─┐  ┌┬┐┬ ┬┌─┐┌─┐   ┌─┐┬─┐┌┬┐┌─┐┬─┐",
            "│  │ │└─┐ │ │ ││││├┤ ├┬┘   │ └┬┘├─┘├┤    │ │├┬┘ ││├┤ ├┬┘",
            "└─┘└─┘└─┘ ┴ └─┘┴ ┴└─┘┴└─   ┴  ┴ ┴  └─┘┘  └─┘┴└──┴┘└─┘┴└─",
            "┌┬┐┌─┐┌┬┐┌─┐┬┬  ┌─┐   ┌─┐┌┐┌┌┬┐  ┌┬┐┬┌─┐┌┬┐┌─┐┌┐┌┌─┐┌─┐",
            " ││├┤  │ ├─┤││  └─┐   ├─┤│││ ││   │││└─┐ │ ├─┤││││  ├┤",
            "─┴┘└─┘ ┴ ┴ ┴┴┴─┘└─┘┘  ┴ ┴┘└┘─┴┘  ─┴┘┴└─┘ ┴ ┴ ┴┘└┘└─┘└─┘"
        };
        
        // Text for the return instruction
        String returnText = "Press ESC to return to menu";
        
        // Display the background first
        displayFoodPandaAbout(screen);
        
        // Create a copy of the background
        TextCharacter[][] backgroundCopy = captureScreenState(screen);
        
        boolean running = true;
        TextColor.RGB whiteColor = new TextColor.RGB(255, 255, 255);
        TextColor.RGB pinkColor = new TextColor.RGB(255, 105, 180);
        
        while (running) {
            TextGraphics tg = screen.newTextGraphics();
            TerminalSize size = screen.getTerminalSize();
            
            // Find the maximum width of content for centering
            int maxWidth = 0;
            for (String line : content) {
                maxWidth = Math.max(maxWidth, line.length());
            }
            
            // Position content at right side center
            int startX = (size.getColumns() * 2) / 3 - maxWidth / 2 + 15; // Adjusted slightly to the right by adding 5
            int startY = (size.getRows() - content.length) / 2; // Vertical center
            
            // Ensure startX and startY are positive
            startX = Math.max(10, startX);
            startY = Math.max(5, startY);
            
            // Clear the area by restoring the background first
            restoreBackgroundArea(screen, backgroundCopy, 0, 0, size.getColumns(), size.getRows());
            
            // Draw the ASCII art content
            for (int j = 0; j < content.length; j++) {
                String line = content[j];
                for (int x = 0; x < line.length(); x++) {
                    // Ensure we stay within bounds
                    if (startX + x < size.getColumns() && startY + j < size.getRows()) {
                        char c = line.charAt(x);
                        // Skip invalid control characters
                        if (c >= 32 && c != 127) { // Valid printable characters
                            // Get the original background character/color from our copy
                            TextCharacter bgChar = backgroundCopy[startY + j][startX + x];
                            // Create a new character with the text color but keep the background
                            TextCharacter overlayChar = bgChar.withForegroundColor(whiteColor)
                                                          .withCharacter(c);
                            
                            // Place the new character
                            screen.setCharacter(startX + x, startY + j, overlayChar);
                        }
                    }
                }
            }
            
            // Add ESC to return instruction at the bottom
            int instructionY = startY + content.length + 2;
            int instructionX = startX + (maxWidth - returnText.length()) / 2; // Center the instruction
            
            for (int x = 0; x < returnText.length(); x++) {
                if (instructionX + x < size.getColumns() && instructionY < size.getRows()) {
                    TextCharacter bgChar = backgroundCopy[instructionY][instructionX + x];
                    TextCharacter overlayChar = bgChar.withForegroundColor(pinkColor)
                                                  .withCharacter(returnText.charAt(x));
                    screen.setCharacter(instructionX + x, instructionY, overlayChar);
                }
            }
            
            screen.refresh();
            
            // Handle keyboard input - only ESC to return
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Escape) {
                running = false;
            }
        }
        
        // Clear screen and return to menu
        screen.clear();
        MenuSelection(screen);
        screen.refresh();
    }
}
 