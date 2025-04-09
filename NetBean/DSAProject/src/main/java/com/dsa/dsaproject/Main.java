package com.dsa.dsaproject;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.TextGraphics;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.SGR;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

public class Main {
    public static Screen screen;

    public static void main(String[] args) throws IOException, InterruptedException {
        // Setup screen
    Screen screen = TerminalFacade.createScreen();
    screen.startScreen();

    // Display loading screen
    displayLoadingScreen(screen);

    // Wait for 2 seconds or key press to proceed
    Thread.sleep(2000); // Delay for 2 seconds before showing the image
    logo(screen);
    menuSelection(screen, args); // Call the menu selection method
    screen.stopScreen();
        
    }
    public static void displayLoadingScreen(Screen screen) throws IOException {
        // Get terminal dimensions
        int terminalWidth = screen.getTerminalSize().getColumns();
        int terminalHeight = screen.getTerminalSize().getRows();
    
        // Loading message
        String loadingMessage = "Loading... Please wait.";
    
        // Clear screen
        for (int i = 0; i < terminalHeight; i++) {
            for (int j = 0; j < terminalWidth; j++) {
                screen.putString(j, i, " ", Terminal.Color.DEFAULT, Terminal.Color.BLACK);
            }
        }
    
        // Center the loading message
        int xPos = (terminalWidth - loadingMessage.length()) / 2;
        int yPos = terminalHeight / 2;
    
        // Show loading message
        screen.putString(xPos, yPos, loadingMessage, Terminal.Color.WHITE, Terminal.Color.BLACK);
        screen.refresh();
    }
        public static void logo(Screen screen) {
            // ANSI escape code for pink/magenta color
            String pink = "\033[1;35m";
            String reset = "\u001B[0m";  // Reset color to default
            // Get terminal dimensions
            int terminalWidth = screen.getTerminalSize().getColumns();
            int terminalHeight = screen.getTerminalSize().getRows();
            // Panda logo
            String[] panda = {
                "⠀  ⠀                 ⠀⣠⣴⣶⣶⣶⣶⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣶⣶⣶⣶⣦⣤⡀⠀⠀",
                " ⠀                  ⢠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣤⣤⣶⠶⠶⠶⠶⠶⠶⠶⠶⠶⢶⣦⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀",
                "                   ⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⡆",
                "                   ⠸⣿⣿⣿⣿⣿⣿⣿⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣿⣿⣿⣿⡇",
                "                   ⠀⢿⣿⣿⣿⣿⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣿⣿⣿⣿⠁",
                " ⠀                  ⠈⠻⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣿⡿⠁",
                "                   ⠀⠀⠀⣿⠁⠀⠀⠀⢀⣠⣤⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣄⡀⠀⠀⠀⠀⠀ ⢻⡇⠀⠀⠀",
                "                   ⠀⠀⢸⡏⠀⠀⣠⣶⣿⣿⣿⠿⢿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⣿⠿⢿⣿⣷⣦⡀⠀⠀⠈⣿⠀⠀⠀",
                "                   ⠀⠀⢸⡇⠀⢰⣿⣿⣿⣿⣇⠀⢨⣿⣿⠀⠀⢠⣦⣤⣤⠀⠀⣿⣿⣇⠀⢀⣿⣿⣿⣿⡆⠀⠀⣿⠀⠀⠀",
                " ⠀                  ⠀⠸⣷⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⠈⡏⠀⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⢰⡟⠀⠀⠀",
                "                   ⠀⠀⠀⢻⣇⠈⠻⣿⣿⣿⣿⣿⣿⡟⠀⠀⠸⣦⣼⣷⣤⡶⠀⠈⢿⣿⣿⣿⣿⣿⣿⠟⠀⢠⡿⠁⠀⠀⠀",
                "                   ⠀⠀⠀⠀⠹⣷⡀⠀⠉⠙⠛⠛⠋⠀⠀⠀⠀⠈⠛⠞⠋⠀⠀⠀⠈⠛⠻⠟⠛⠉⠁⠀⣰⡿⠁⠀⠀⠀⠀",
                "                   ⠀⠀⠀⠀⠀⠈⠻⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⡾⠋⠀⠀⠀⠀⠀⠀",
                "                   ⠀⠀⠀⠀⠀⠀⠀⠈⠙⠿⣶⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣴⡾⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀",
                "                   ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠙⠛⠿⠶⠶⠶⣤⣤⡴⠶⠶⠾⠟⠛⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                "",
                "███████  ██████   ██████  ██████      ██████   █████  ███    ██ ██████   █████  ",
                "██      ██    ██ ██    ██ ██   ██     ██   ██ ██   ██ ████   ██ ██   ██ ██   ██ ",
                "█████   ██    ██ ██    ██ ██   ██     ██████  ███████ ██ ██  ██ ██   ██ ███████ ",
                "██      ██    ██ ██    ██ ██   ██     ██      ██   ██ ██  ██ ██ ██   ██ ██   ██ ",
                "██       ██████   ██████  ██████      ██      ██   ██ ██   ████ ██████  ██   ██ "
            };
           
            // Calculate starting positions to center the logo
            int logoHeight = panda.length;
            int logoWidth = getMaxLineWidth(panda);
            int xStart = (terminalWidth - logoWidth) / 2;
            int yStart = (terminalHeight - logoHeight) / 2;

            // FADE IN
            for (int i = 0; i < panda.length; i++) {
                screen.clear();
                for (int j = 0; j <= i; j++) {
                    screen.putString(xStart, yStart + j, panda[j], Terminal.Color.MAGENTA, Terminal.Color.BLACK);
                }
                try {
                    screen.refresh();
                    Thread.sleep(50);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Play sound
            LogoSound();

            try {
                Thread.sleep(1000); // Hold logo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // FADE OUT
            for (int i = panda.length - 1; i >= 0; i--) {
                screen.clear();
                for (int j = 0; j < i; j++) {
                    screen.putString(xStart, yStart + j, panda[j], Terminal.Color.MAGENTA, Terminal.Color.BLACK);
                }
                try {
                    screen.refresh();
                    Thread.sleep(50);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            screen.clear();
            screen.refresh();
        }

        // Helper method to calculate the maximum width of the logo lines
        private static int getMaxLineWidth(String[] lines) {
            int maxWidth = 0;
            for (String line : lines) {
                if (line.length() > maxWidth) {
                    maxWidth = line.length();
                }
            }
            return maxWidth;
        }
    public static void LogoSound() {
        File file = new File("NetBean\\DSAProject\\src\\main\\resources\\FoodPandaSound.wav");
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file)) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            // Wait for the sound to finish
            while (!clip.isRunning())
                Thread.sleep(10);
            while (clip.isRunning())
                Thread.sleep(10);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void menuSelection(Screen screen, String[] args) throws IOException {
        String[][] mainMenuOptions = {
            {  "┏┓                   ", 
               "┗┓╋┏┓┏┓╋             ", 
               "┗┛┗┗┻┛ ┗             " }, 
    
            {  "┓┏              ┓    ", 
               "┣┫┏┓┓┏┏  ╋┏┓  ┏┓┃┏┓┓┏", 
               "┛┗┗┛┗┻┛  ┗┗┛  ┣┛┗┗┻┗┫" }, 
    
            {  "┏┓┓                  ", 
               "┣┫┣┓┏┓┓┏╋            ", 
               "┛┗┗┛┗┛┗┻┗            " }, 
    
            {  "┏┓  •                ", 
               "┣ ┓┏┓╋               ", 
               "┗┛┛┗┗┗               " }
        };
    
        int selectedIndex = 0;
        BufferedImage logoImage = ImageIO.read(new File("NetBean\\DSAProject\\src\\main\\resources\\FoodPandaBackground.png"));
    
        while (true) {
            screen.clear();
            displayMenuWithLogo(screen, mainMenuOptions, selectedIndex, logoImage);
            screen.refresh();
    
            Key key = screen.readInput(); // Lanterna key input handling
            if (key != null) {
                switch (key.getKind()) {
                    case ArrowUp:
                        selectedIndex = (selectedIndex - 1 + mainMenuOptions.length) % mainMenuOptions.length;
                        debounce();
                        break;
                    case ArrowDown:
                        selectedIndex = (selectedIndex + 1) % mainMenuOptions.length;
                        debounce();
                        break;
                    case Enter:
                        debounce();
                        handleMenuSelection(mainMenuOptions[selectedIndex], selectedIndex, args);
                        return; // Exit the loop after handling selection
                    default:
                        // Ignore other keys
                        break;
                }
            }
        }
    }

// Updated method to correctly handle menu selection
private static void handleMenuSelection(String[] mainMenuOptions, int selectedIndex, String[] args) {
    switch (selectedIndex) {
        case 0:
            StartGame.main(args);
            System.exit(0); // Ensure the program exits after the game ends
            break;
        case 1:
            showContent("How to Play:\n" +
                    "1. Use arrow keys to navigate.\n" +
                    "2. Press Enter to select an option.",
                    "Press any key to return to the main menu.");
            break;
        case 2:
            showContent("About:\n" +
                    "This is a Lanterna-based menu demo.\n" +
                    "Author: Your Name",
                    "Press any key to return to the main menu.");
            break;
        case 3:
            System.out.println("Exiting...");
            System.exit(0);
            break;
        default:
            System.out.println("Invalid selection.");
    }
}

// Show content and wait for user input
private static void showContent(String content, String returnMessage) {
    clearConsole();
    System.out.println(content);
    System.out.println("\n" + returnMessage);
    waitForAnyKey();
}

private static void waitForAnyKey() {
    try {
        System.in.read(); // Wait for any key press
    } catch (IOException e) {
        e.printStackTrace();
    }
}
private static void clearConsole() {
    try {
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        }
    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
    }
}
private static void displayMenuWithLogo(Screen screen, String[][] menuOptions, int selectedIndex, BufferedImage logoImage) throws IOException {
    // Get terminal dimensions
    int terminalWidth = screen.getTerminalSize().getColumns();
    int terminalHeight = screen.getTerminalSize().getRows();

    // Convert the image to Unicode blocks with color
    String[] unicodeBackground = convertImageToUnicode(logoImage, terminalWidth, terminalHeight);

    // Display the background
    for (int y = 0; y < terminalHeight; y++) {
        String line = unicodeBackground[y];
        for (int x = 0; x < line.length(); x++) {
            char character = line.charAt(x);
            Terminal.Color color = getColorFromPixel(logoImage, x, y, terminalWidth, terminalHeight); // Extract color from the image
            screen.putString(x, y, String.valueOf(character), color, Terminal.Color.BLACK);
        }
    }

    // Overlay the menu on the left side
    int maxMenuLines = menuOptions.length * 3;
    for (int i = 0; i < maxMenuLines; i++) {
        int optionIndex = i / 3;
        int lineIndex = i % 3;
        String menuText = menuOptions[optionIndex][lineIndex];

        // Determine if the current menu option is selected
        if (optionIndex == selectedIndex) {
            screen.putString(0, i, menuText, Terminal.Color.MAGENTA, Terminal.Color.BLACK);
        } else {
            screen.putString(0, i, menuText, Terminal.Color.WHITE, Terminal.Color.BLACK);
        }
    }

    // Refresh the screen
    screen.refresh();
}

// Helper method to convert an image to Unicode blocks
private static String[] convertImageToUnicode(BufferedImage image, int width, int height) {
    String[] result = new String[height];
    for (int y = 0; y < height; y++) {
        StringBuilder line = new StringBuilder();
        for (int x = 0; x < width; x++) {
            // Map image pixels to Unicode block characters
            int pixel = image.getRGB(x * image.getWidth() / width, y * image.getHeight() / height);
            int red = (pixel >> 16) & 0xFF;
            int green = (pixel >> 8) & 0xFF;
            int blue = pixel & 0xFF;

            // Use a full block character for each pixel
            line.append("█");
        }
        result[y] = line.toString();
    }
    return result;
}
// Helper method to extract color from a pixel
private static Terminal.Color getColorFromPixel(BufferedImage image, int x, int y, int terminalWidth, int terminalHeight) {
    int pixel = image.getRGB(x * image.getWidth() / terminalWidth, y * image.getHeight() / terminalHeight);
    int red = (pixel >> 16) & 0xFF;
    int green = (pixel >> 8) & 0xFF;
    int blue = pixel & 0xFF;

    // Approximate RGB to Lanterna's terminal colors
    if (red > 200 && green > 200 && blue > 200) return Terminal.Color.WHITE;
    if (red > 200 && green < 50 && blue < 50) return Terminal.Color.RED;
    if (red < 50 && green > 200 && blue < 50) return Terminal.Color.GREEN;
    if (red < 50 && green < 50 && blue > 200) return Terminal.Color.BLUE;
    if (red > 200 && green > 200 && blue < 50) return Terminal.Color.YELLOW;
    if (red > 200 && green < 50 && blue > 200) return Terminal.Color.MAGENTA;
    if (red < 50 && green > 200 && blue > 200) return Terminal.Color.CYAN;
    return Terminal.Color.BLACK;
}

// Debounce utility
private static void debounce() {
    try {
        Thread.sleep(150);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
    
}
