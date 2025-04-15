package com.trial3.version3;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.SimpleTheme;
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

import java.util.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType; 
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;
public class StartGame {
    public static String[] DaetBarangay = {"Bagasbas","Baranggay I", "Baranggay II", "Calasgasan"};
    static String[] CustomerName = {"Luffy", "Zoro", "Nami", "Usopp", "Sanji"};
    static String[] CustomerAddress = {"Daet", "Mercedes", "San Vicente", "Talisay", "Basud", "Capalonga", "Labo", "Paracale", "San Lorenzo Ruiz", "Santa Elena", "Vinzons"};
    public static final String[] FoodOrder = {
        "Burger", "Fries", "Pizza", "Milk Tea", "Soda", 
        "Chicken", "Ice Cream", "Spaghetti", "Hotdog", "Nuggets"
    };
     public static String[] customerTypes = {"VIP", "Regular"};
    public static String[] orderTypes = {"Standard", "Bulk", "Urgent"};
 
    static double[] FoodPrices = {80.0, 150.0, 120.0, 100.0, 200.0, 90.0, 110.0, 50.0, 130.0, 70.0, 60.0};
    static String[] PaymentMethods = {"Cash on Delivery (Paid)", "Online Payment (Paid)"};

    public static void main(String[] args) {
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
                Thread.sleep(40);

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

                String title = "Generating Random Orders";

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
            
            displayOrdersBackground(screen);
            List<FoodOrderEntry> orders = generateRandomOrders();
            TextGraphics textGraphics = screen.newTextGraphics();
            renderOrdersOnScreen(screen, orders, textGraphics);
            
            // Add an instruction for the user with transparent background
            TerminalSize size = screen.getTerminalSize();
            String instruction = "Press ENTER to continue";
            int instructionX = size.getColumns() / 2 - instruction.length() / 2;
            int instructionY = size.getRows() - 3;

            // Capture the current screen state (background)
            TextCharacter[][] backgroundCopy = captureScreenState(screen);

            // First restore the background area for the instruction text
            restoreBackgroundArea(screen, backgroundCopy, instructionX, instructionY, instruction.length(), 1);

            // Draw the instruction with black text over the background
            TextColor.RGB blackColor = new TextColor.RGB(0, 0, 0);
            drawTextOverBackground(screen, backgroundCopy, instructionX, instructionY, instruction, blackColor);
            screen.refresh();
            
            // Wait for ENTER key
            boolean waiting = true;
            while (waiting) {
                KeyStroke keyPress = screen.readInput();
                if (keyPress.getKeyType() == KeyType.Enter) {
                    waiting = false;
                    // Here you will call your new function
                    // yourNewFunction(screen);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static Random rand = new Random();

    public static class FoodOrderEntry {
        String customerName;
        String address;
        String barangay;
        String[] foodItems;
        int[] quantities;
        String customerType;
        String orderType;
        String paymentMethod;
    }
     public static List<FoodOrderEntry> generateRandomOrders() {
        List<FoodOrderEntry> orders = new ArrayList<>();
        Set<Integer> usedSpecial = new HashSet<>();  // For VIP/Urgent restriction

        for (int i = 0; i < 4; i++) {
            FoodOrderEntry order = new FoodOrderEntry();
            order.customerName = CustomerName[rand.nextInt(CustomerName.length)];
            order.address = CustomerAddress[rand.nextInt(CustomerAddress.length)];
            order.barangay = DaetBarangay[rand.nextInt(DaetBarangay.length)];

            int itemCount = 1 + rand.nextInt(5);
            List<Integer> indices = new ArrayList<>();
            for (int j = 0; j < FoodOrder.length; j++) indices.add(j);
            Collections.shuffle(indices);
            order.foodItems = new String[itemCount];
            order.quantities = new int[itemCount];

            for (int j = 0; j < itemCount; j++) {
                order.foodItems[j] = FoodOrder[indices.get(j)];
                order.quantities[j] = 1 + rand.nextInt(2);
            }

            order.paymentMethod = PaymentMethods[rand.nextInt(PaymentMethods.length)];

            // Classify one as VIP/Urgent
            if (usedSpecial.isEmpty()) {
                String special = rand.nextBoolean() ? "VIP" : "Urgent";
                order.customerType = special.equals("VIP") ? "VIP" : "Regular";
                order.orderType = special.equals("Urgent") ? "Urgent" : "Standard";
                usedSpecial.add(i);
            } else {
                order.customerType = "Regular";
                order.orderType = (itemCount == 5) ? "Bulk" : "Standard";
            }

            orders.add(order);
        }

        return orders;
    }

        public static void renderOrdersOnScreen(Screen screen, List<FoodOrderEntry> orders, TextGraphics tg) throws IOException {
            TerminalSize size = screen.getTerminalSize();
            int startX = 8; // Left-side corner
            int startY = size.getRows() / 2 - 8;
            
            // Capture the current screen state (background)
            TextCharacter[][] backgroundCopy = captureScreenState(screen);
            
            for (int i = 0; i < orders.size(); i++) {
                int x = startX + (i % 2) * 40; // 2x2 horizontal layout
                int y = startY + (i / 2) * 12; // 2 rows vertically
                
                FoodOrderEntry order = orders.get(i);
                
                // Calculate maximum order width and height for this order
                int maxOrderWidth = 30; // Estimate, adjust based on your content
                int orderHeight = 6 + order.foodItems.length; // Base lines + food items
                
                // First restore the background area for this order
                restoreBackgroundArea(screen, backgroundCopy, x, y, maxOrderWidth, orderHeight);
                
                // Define text colors - changed to WHITE
                TextColor.RGB whiteColor = new TextColor.RGB(255, 255, 255);
                
                // Draw order information character by character
                String line = String.format("Customer: %-15s", order.customerName);
                drawTextOverBackground(screen, backgroundCopy, x, y, line, whiteColor);
                
                line = String.format("Address: %-20s", order.address);
                drawTextOverBackground(screen, backgroundCopy, x, y + 1, line, whiteColor);
                
                // Show barangay only if address contains "Daet"
                int lineOffset = 2;
                if (order.address.contains("Daet")) {
                    line = String.format("Barangay: %-15s", order.barangay);
                    drawTextOverBackground(screen, backgroundCopy, x, y + lineOffset++, line, whiteColor);
                }
                
                line = String.format("CustomerType: %-10s", order.customerType);
                drawTextOverBackground(screen, backgroundCopy, x, y + lineOffset++, line, whiteColor);
                
                line = String.format("OrderType: %-10s", order.orderType);
                drawTextOverBackground(screen, backgroundCopy, x, y + lineOffset++, line, whiteColor);
                
                line = String.format("Payment: %-25s", order.paymentMethod);
                drawTextOverBackground(screen, backgroundCopy, x, y + lineOffset++, line, whiteColor);
                
                line = "Items:";
                drawTextOverBackground(screen, backgroundCopy, x, y + lineOffset++, line, whiteColor);
                
                for (int j = 0; j < order.foodItems.length; j++) {
                    line = "  - " + order.foodItems[j] + " x" + order.quantities[j];
                    drawTextOverBackground(screen, backgroundCopy, x, y + lineOffset + j, line, whiteColor);
                }
            }
        }

    // Helper method to draw text character by character over background
        private static void drawTextOverBackground(Screen screen, TextCharacter[][] backgroundCopy, 
                                                int startX, int startY, String text, TextColor color) {
            for (int i = 0; i < text.length(); i++) {
                if (startY < backgroundCopy.length && (startX + i) < backgroundCopy[0].length) {
                    TextCharacter bgChar = backgroundCopy[startY][startX + i];
                    TextCharacter overlayChar = bgChar.withForegroundColor(color)
                                                    .withCharacter(text.charAt(i));
                    screen.setCharacter(startX + i, startY, overlayChar);
                }
            }
        }
    
        // Method to capture the current screen state (copied from Version3.java)
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
    
    // Method to restore a portion of the background (copied from Version3.java)
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
       
        
    

     // Helper to generate a progress bar string
     private static String getProgressBar(int percent, int barLength) {
        int filledLength = percent * barLength / 100;
        char[] bar = new char[barLength];
        Arrays.fill(bar, 0, filledLength, '█');
        Arrays.fill(bar, filledLength, barLength, '░');
        return "[" + new String(bar) + "]";
    }
    private static void displayOrdersBackground(Screen screen) throws IOException {
        try {
            // Load image from resources
            InputStream imageStream = Version3.class.getClassLoader().getResourceAsStream("OrdersFP.png");
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
    private static String getImageTypeName(int type) {
        switch (type) {
            case BufferedImage.TYPE_INT_RGB: return "TYPE_INT_RGB";
            case BufferedImage.TYPE_INT_ARGB: return "TYPE_INT_ARGB";
            case BufferedImage.TYPE_3BYTE_BGR: return "TYPE_3BYTE_BGR";
            case BufferedImage.TYPE_4BYTE_ABGR: return "TYPE_4BYTE_ABGR";
            default: return "Unknown (" + type + ")";
        }
    }
     
    
}
