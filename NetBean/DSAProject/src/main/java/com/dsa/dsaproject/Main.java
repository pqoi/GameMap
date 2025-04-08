package com.dsa.dsaproject;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Setup screen
        Screen screen = TerminalFacade.createScreen();
        screen.startScreen();
        
        int terminalWidth = screen.getTerminalSize().getColumns();
        int terminalHeight = screen.getTerminalSize().getRows();

        // Display loading screen
        String loadingMessage = "Loading... Please wait.";
        int xPos = (terminalWidth - loadingMessage.length()) / 2;
        int yPos = terminalHeight / 2;
        
        for (int i = 0; i < terminalHeight; i++) {
            for (int j = 0; j < terminalWidth; j++) {
                screen.putString(j, i, " ", Terminal.Color.DEFAULT, Terminal.Color.BLACK);
            }
        }
        
        // Show loading message
        screen.putString(xPos, yPos, loadingMessage, Terminal.Color.WHITE, Terminal.Color.BLACK);
        screen.refresh();

        // Wait for 2 seconds or key press to proceed
        Thread.sleep(2000); // Delay for 2 seconds before showing the image
        
        // Load and scale image
        BufferedImage image = ImageIO.read(new File("C:\\Code Practice\\NetBean\\DSAProject\\src\\main\\resources\\FoodPandaLogo.jpg"));
        BufferedImage scaled = new BufferedImage(terminalWidth, terminalHeight, BufferedImage.TYPE_INT_RGB);
        scaled.getGraphics().drawImage(image, 0, 0, terminalWidth, terminalHeight, null);

        // Draw to terminal using background colors
        for (int y = 0; y < terminalHeight; y++) {
            for (int x = 0; x < terminalWidth; x++) {
                Color pixel = new Color(scaled.getRGB(x, y));
                
                // Check if the pixel is close to red (RGB: 255, 0, 0)
                if (isRed(pixel)) {
                    pixel = new Color(255, 105, 180); // Change to pink if it's red
                }
                
                Terminal.Color bgColor = getClosestLanternaColor(pixel);

                screen.putString(x, y, " ", Terminal.Color.DEFAULT, bgColor);
            }
        }

        screen.refresh();
        
        // Keep the screen open until the user presses Escape
        boolean keepRunning = true;
        while (keepRunning) {
            Key key = screen.readInput();
            while (key == null) {
                key = screen.readInput();
            }

            switch (key.getKind()) {
                case Escape:
                    screen.stopScreen();
                    System.exit(0);
                    break;

                default:
                    break;
            }
        }

        screen.stopScreen();
    }

    public static boolean isRed(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        
        // A simple check for red tones (R = 255, G = 0, B = 0)
        return (r > 200 && g < 50 && b < 50);
    }

    // Maps real colors to closest Lanterna 2.1.8 color
    public static Terminal.Color getClosestLanternaColor(Color color) {
        Terminal.Color[] lanternaColors = Terminal.Color.values();
        Terminal.Color closest = Terminal.Color.DEFAULT;
        double minDistance = Double.MAX_VALUE;

        for (Terminal.Color termColor : lanternaColors) {
            Color c = getAWTColor(termColor);
            double distance = colorDistance(color, c);
            if (distance < minDistance) {
                minDistance = distance;
                closest = termColor;
            }
        }
        return closest;
    }

    // Convert Terminal.Color to java.awt.Color
    public static Color getAWTColor(Terminal.Color color) { 
        switch (color) {
            case BLACK: return new Color(0, 0, 0);
            case RED: return new Color(255, 0, 0); // Corrected to pure red
            case GREEN: return new Color(0, 255, 0);
            case YELLOW: return new Color(255, 255, 0);
            case BLUE: return new Color(0, 0, 255);
            case MAGENTA: return new Color(255, 0, 255); // Corrected to pure magenta
            case CYAN: return new Color(0, 255, 255);
            case WHITE: return new Color(255, 255, 255);
            case DEFAULT: default: return new Color(0, 0, 0);
        }
    }

    // Color distance formula
    public static double colorDistance(Color c1, Color c2) {
        int rDiff = c1.getRed() - c2.getRed();
        int gDiff = c1.getGreen() - c2.getGreen();
        int bDiff = c1.getBlue() - c2.getBlue();
        return Math.sqrt(rDiff * rDiff + gDiff * gDiff + bDiff * bDiff);
    }
}
