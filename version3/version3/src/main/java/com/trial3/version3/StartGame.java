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
    
    static String[] CustomerName = {"Luffy", "Zoro", "Nami", "Usopp", "Sanji"};
    static String[] CustomerAddress = {
        "Barangay VI (Centro)",
        "Barangay I(Hilahod)",
        "Barangay II(Pasig)",
        "Barangay III(Iraya)",
        "Barangay VIII(Salcedo)",
        "Barangay VII(Diego Liñan)",
        "Barangay IV(Mantagbac)",
        "Barangay V(Pandan)",
        "Lag-on",
        "Dogongan",
        "Borabod",
        "Calasgasan",
        "Camambugan",
        "Bagasbas",
        "Cobangbang",
        "Gahonon",
        "Gubat",
        "Magang",
        "Alawihao",
        "Awitan",
        "Bibirao",
        "Mambalite",
        "Mancruz",
        "Pamorangon",
        "San Isidro"
    };

    public static double[] AddressDistance = {
        0.4,  // Barangay VI (Centro) – ~0.4 km
        0.5,  // Barangay I (Hilahod) – ~0.5 km
        0.6,  // Barangay II (Pasig) – ~0.6 km
        0.7,  // Barangay III (Iraya) – ~0.7 km
        0.7,  // Barangay VIII (Salcedo) – ~0.7 km
        0.6,  // Barangay VII (Diego Liñan) – ~0.6 km
        0.8,  // Barangay IV (Mantagbac) – ~0.8 km
        0.9,  // Barangay V (Pandan) – ~0.9 km
        1.0,  // Lag-on – ~1.0 km
        1.5,  // Dogongan – ~1.5 km
        2.0,  // Borabod – ~2.0 km
        2.5,  // Calasgasan – ~2.5 km
        3.0,  // Camambugan – ~3.0 km
        3.0,  // Bagasbas – ~3.0 km
        3.5,  // Cobangbang – ~3.5 km
        4.0,  // Gahonon – ~4.0 km
        4.5,  // Gubat – ~4.5 km
        5.0,  // Magang – ~5.0 km
        5.5,  // Alawihao – ~5.5 km
        6.0,  // Awitan – ~6.0 km
        6.5,  // Bibirao – ~6.5 km
        7.0,  // Mambalite – ~7.0 km
        7.5,  // Mancruz – ~7.5 km
        8.0,  // Pamorangon – ~8.0 km
        8.5   // San Isidro – ~8.5 km
    };
    public static final String[] FoodOrder = {
        "Burger", "Fries", "Pizza", "Milk Tea", "Soda", 
        "Chicken", "Ice Cream", "Spaghetti", "Hotdog", "Nuggets"
    };
     public static String[] customerTypes = {"VIP", "Regular"};
    public static String[] orderTypes = {"Standard", "Bulk", "Urgent"};
 
    static double[] FoodPrices = {80.0, 150.0, 120.0, 100.0, 200.0, 90.0, 110.0, 50.0, 130.0, 70.0, 60.0};
    static String[] PaymentMethods = {"Cash on Delivery", "Online Payment (Paid)"};

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

           // First animation block modification
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(10);

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

                String[] progressData = getProgressBarWithMotorPosition(i, barLength);
                String bar = progressData[0];
                int motorPos = Integer.parseInt(progressData[1]);
                String percent = i + "%";
                String spinner = spinnerChars[i % spinnerChars.length];

                // Calculate position for motor to move across the bar
                int motorStartX = centerX - barLength/2;
                int motorX = motorStartX + motorPos - 15; // Center the motor horizontally
                int motorY = centerY - 12; // Position above the progress bar
                
                // Draw motor first so the bar and text appear on top
                drawMotor(screen, motorX, motorY, tg);
                
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
                     // Create terminal factory and configure it
                    terminalFactory = new DefaultTerminalFactory();

                    terminal.setTitle("");
                    terminal.setVisible(true);

                    // Force fullscreen after the frame is visible
                    SwingUtilities.invokeLater(() -> terminal.setExtendedState(JFrame.MAXIMIZED_BOTH));

                    // Create and start the screen
                    screen = new TerminalScreen(terminal);
                    screen.startScreen();

                    
                    // Second animation block modification (after ENTER key pressed)
                    for (int i = 0; i <= 100; i++) {
                        Thread.sleep(40);

                        screen.clear();
                        TextGraphics tg = screen.newTextGraphics();
                        tg.setBackgroundColor(TextColor.ANSI.BLACK);
                        tg.setForegroundColor(TextColor.ANSI.WHITE);
                        tg.fill(' ');

                        if (size == null) {
                            size = screen.getTerminalSize(); // fallback
                        }

                        int centerX = size.getColumns() / 2;
                        int centerY = size.getRows() / 2;

                        String title = "Arranging Delivery of the Orders...";

                        // Responsive bar length (e.g. max 70, min 20)
                        int maxBarLength = Math.min(70, size.getColumns() - 20);
                        int barLength = Math.max(20, maxBarLength);

                        String[] progressData = getProgressBarWithMotorPosition(i, barLength);
                        String bar = progressData[0];
                        int motorPos = Integer.parseInt(progressData[1]);
                        String percent = i + "%";
                        String spinner = spinnerChars[i % spinnerChars.length];

                        // Calculate position for motor to move across the bar
                        int motorStartX = centerX - barLength/2;
                        int motorX = motorStartX + motorPos - 15; // Center the motor horizontally
                        int motorY = centerY - 12; // Position above the progress bar
                        
                        // Draw motor first so the bar and text appear on top
                        drawMotor(screen, motorX, motorY, tg);
                        
                        // Draw centered content
                        tg.putString(centerX - title.length() / 2, centerY - 2, title);
                        tg.putString(centerX - (bar.length() + percent.length() + spinner.length() + 2) / 2, centerY,
                                bar + " " + percent + " " + spinner);

                        screen.refresh();
                    }
                    screen.clear();
                    displayDeliverysBackground(screen, orders);
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
        List<Integer> usedSpecial = new ArrayList<>();  // For VIP/Urgent restriction
        
        // Create a list of available customer indices
        List<Integer> availableCustomers = new ArrayList<>();
        for (int i = 0; i < CustomerName.length; i++) {
            availableCustomers.add(i);
        }
        Collections.shuffle(availableCustomers); // Randomize the order
    
        for (int i = 0; i < 4; i++) {
            FoodOrderEntry order = new FoodOrderEntry();
            
            // Select a unique customer name
            if (i < availableCustomers.size()) {
                int customerIndex = availableCustomers.get(i);
                order.customerName = CustomerName[customerIndex];
            } else {
                // Fallback if we have more orders than customers
                order.customerName = "Customer " + (i + 1);
            }
            
            order.address = CustomerAddress[rand.nextInt(CustomerAddress.length)];
    
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
    int startX = 15; // Left-side corner
    int startY = size.getRows() / 2 - 10; // Increase the vertical starting position by reducing the offset value.
    // Capture the current screen state (background)
    TextCharacter[][] backgroundCopy = captureScreenState(screen);
    
    // Add the rider location label at the top
    String riderLocation = "Rider Location: Barangay VI Centro";
    int riderLocationX = 33; // Center horizontally
    int riderLocationY = startY - 3; // Position above orders
    
    // Draw rider location with white text
    TextColor.RGB whiteColor = new TextColor.RGB(255, 255, 255);
    drawTextOverBackground(screen, backgroundCopy, riderLocationX, riderLocationY, riderLocation, whiteColor);
    
    // Define shipping fee calculation constants
    final double BASE_FEE = 20.0;          // Base delivery fee in pesos
    final double DISTANCE_RATE = 10.0;     // Rate per kilometer in pesos
    final double VIP_DISCOUNT = 0.15;      // 15% discount for VIP customers
    
    for (int i = 0; i < orders.size(); i++) {
        int x = startX + (i % 2) * 45; // 2x2 horizontal layout
        int y = startY + (i / 2) * 15; // 2 rows vertically
        
        FoodOrderEntry order = orders.get(i);
        
        // Find the index of this address in CustomerAddress array to get the distance
        int addressIndex = -1;
        for (int k = 0; k < CustomerAddress.length; k++) {
            if (CustomerAddress[k].equals(order.address)) {
                addressIndex = k;
                break;
            }
        }
        
        // Get the distance if found
        double distance = (addressIndex >= 0 && addressIndex < AddressDistance.length) ? 
                        AddressDistance[addressIndex] : 0.0;
        
        // Calculate shipping fee based on distance
        double shippingFee = BASE_FEE + (DISTANCE_RATE * distance);
        
        // Calculate maximum order width and height for this order
        int maxOrderWidth = 35; // Increased to accommodate prices and totals
        int orderHeight = 10 + order.foodItems.length * 2; // Increased for prices, shipping, and discounts
        
        // First restore the background area for this order
        restoreBackgroundArea(screen, backgroundCopy, x, y, maxOrderWidth, orderHeight);
        
        // Draw order information character by character
        String line = String.format("Customer: %-15s", order.customerName);
        drawTextOverBackground(screen, backgroundCopy, x, y, line, whiteColor);
        
        line = String.format("Address: %s (%.1f km)", order.address, distance);
        drawTextOverBackground(screen, backgroundCopy, x, y + 1, line, whiteColor);
        
        // Show barangay only if address contains "Daet"
        int lineOffset = 2;
        line = String.format("CustomerType: %-10s", order.customerType);
        drawTextOverBackground(screen, backgroundCopy, x, y + lineOffset++, line, whiteColor);
        
        line = String.format("OrderType: %-10s", order.orderType);
        drawTextOverBackground(screen, backgroundCopy, x, y + lineOffset++, line, whiteColor);
        
        line = String.format("Payment: %-25s", order.paymentMethod);
        drawTextOverBackground(screen, backgroundCopy, x, y + lineOffset++, line, whiteColor);
        
        line = "Items:";
        drawTextOverBackground(screen, backgroundCopy, x, y + lineOffset++, line, whiteColor);
        
        double subtotal = 0.0;
        for (int j = 0; j < order.foodItems.length; j++) {
            // Find the food item price
            int foodIndex = -1;
            for (int k = 0; k < FoodOrder.length; k++) {
                if (FoodOrder[k].equals(order.foodItems[j])) {
                    foodIndex = k;
                    break;
                }
            }
            
            // Get the price if found, default to a reasonable value if not found
            double price = (foodIndex >= 0 && foodIndex < FoodPrices.length) ? 
                          FoodPrices[foodIndex] : 100.0;
            
            double itemTotal = price * order.quantities[j];
            subtotal += itemTotal;
            
            // Display item with quantity and price
            line = String.format("  - %s x%d (₱%.2f each)", 
                   order.foodItems[j], order.quantities[j], price);
            drawTextOverBackground(screen, backgroundCopy, x, y + lineOffset++, line, whiteColor);
           
        }
        
        // Display shipping fee
        line = String.format("Shipping Fee: ₱%.2f", shippingFee);
        drawTextOverBackground(screen, backgroundCopy, x, y + lineOffset++, line, whiteColor);
        
        // Calculate and display discount for VIP customers
        double discount = 0.0;
        if ("VIP".equals(order.customerType)) {
            discount = subtotal * VIP_DISCOUNT;
            line = String.format("VIP Discount (15%%): -₱%.2f", discount);
            drawTextOverBackground(screen, backgroundCopy, x, y + lineOffset++, line, whiteColor);
        }
        
        // Calculate and display the grand total
        double grandTotal = subtotal + shippingFee - discount;
        line = String.format("Total Amount: ₱%.2f", grandTotal);
        drawTextOverBackground(screen, backgroundCopy, x, y + lineOffset++, line, whiteColor);
    }
}
    // Improved helper method to draw text character by character over background with bounds checking
        private static void drawTextOverBackground(Screen screen, TextCharacter[][] backgroundCopy, 
                int startX, int startY, String text, TextColor color) {
                // Add bounds checking to avoid ArrayIndexOutOfBoundsException
                if (startY < 0 || startY >= backgroundCopy.length) {
                return; // Y coordinate is out of bounds
                }

                for (int i = 0; i < text.length(); i++) {
                int x = startX + i;
                // Check if x is within bounds before accessing array
                if (x >= 0 && x < backgroundCopy[0].length && startY < backgroundCopy.length) {
                TextCharacter bgChar = backgroundCopy[startY][x];
                // Additional null check for background character
                if (bgChar != null) {
                TextCharacter overlayChar = bgChar.withForegroundColor(color)
                        .withCharacter(text.charAt(i));
                screen.setCharacter(x, startY, overlayChar);
                } else {
                // If background is null, create a new character with black background
                TextCharacter newChar = new TextCharacter(
                text.charAt(i), 
                color, 
                TextColor.ANSI.BLACK
                );
                screen.setCharacter(x, startY, newChar);
                }
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
       
        
        private static final String[] Motor = {
            "                           `   `.",
            "           <```--...       .---.//  < `.",
            "            `..     `.___ /       ___`.'",
            "              _`_ .      `      .'\\\\__",
            "            .'---`.`.          / .'---`.",
            "           /.'  _`.\\_\\        / /.'\\\\ `.\\",
            "           ||  <__||_|        | ||  ~  ||",
            "           \\`.___.'/ /________\\ \\`.___.'/",
            "            `.___.'              `.___.'"
        };
        // This method draws the provided ASCII motor at a specific position
        private static void drawMotor(Screen screen, int x, int y, TextGraphics tg) {
            // Use the provided ASCII motor art
            String[] motorArt = Motor;
            
            // Draw the motor
            for (int i = 0; i < motorArt.length; i++) {
                // Ensure we're not going beyond screen bounds
                if (y + i < screen.getTerminalSize().getRows()) {
                    tg.putString(x, y + i, motorArt[i]);
                }
            }
        }

    // Modify the getProgressBar method to calculate and return the position
    private static String[] getProgressBarWithMotorPosition(int percent, int barLength) {
        int filledLength = percent * barLength / 100;
        char[] bar = new char[barLength];
        Arrays.fill(bar, 0, filledLength, '█');
        Arrays.fill(bar, filledLength, barLength, '░');
        
        // Calculate motor position along the bar - scaled to bar length
        int motorPosition = (percent * barLength) / 100;
        if (motorPosition >= barLength) motorPosition = barLength - 1;
        
        return new String[] {"[" + new String(bar) + "]", String.valueOf(motorPosition)};
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
    private static void displayDeliverysBackground(Screen screen, List<FoodOrderEntry> orders) throws IOException {
        try {
            // Load image from resources
            InputStream imageStream = Version3.class.getClassLoader().getResourceAsStream("DeliveryOrder.png");
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
                    if (y < original.getHeight() && x < original.getWidth()) {
                        Color top = new Color(original.getRGB(x, y), false);
                        Color bottom = y + 1 < original.getHeight() ? 
                                    new Color(original.getRGB(x, y + 1), false) : 
                                    Color.BLACK;
    
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
            }
    
            screen.refresh();
          
            
            // Sort orders using priority queue with custom comparator
            PriorityQueue<FoodOrderEntry> priorityQueue = new PriorityQueue<>(
                new DeliveryOrderComparator()
            );
            
            // Add all orders to the priority queue
            for (FoodOrderEntry order : orders) {
                priorityQueue.add(order);
            }
            
            // Capture the background after rendering the background image
            TextCharacter[][] backgroundCopy = captureScreenState(screen);
            
            // Display orders in a horizontal line
            int orderWidth = 35;  // Width for each order box
            int orderHeight = 10; // Height for each order box
            int spacing = 3;      // Spacing between orders
            
            // Calculate the total width needed for all orders with spacing
            int totalWidth = Math.min(orders.size(), 4) * (orderWidth + spacing) - spacing;
            
            // Calculate the starting position for orders
            // Position them at the center horizontally, but 1/3 from the top vertically
            int orderStartX = (size.getColumns() - totalWidth) / 2;
            int orderStartY = size.getRows() / 3;
            
            // Add the rider location label at the top
            String riderLocation = "Rider Location: Barangay VI Centro";
            int riderLocationX = (size.getColumns() - riderLocation.length()) / 2; // Center horizontally
            int riderLocationY = orderStartY - 2; // Position above orders
            
            // Draw rider location with white text
            TextColor.RGB whiteColor = new TextColor.RGB(255, 255, 255);
            
            // Draw a darker background for the rider location
            for (int i = 0; i < riderLocation.length(); i++) {
                if (riderLocationX + i < size.getColumns() && riderLocationY < size.getRows()) {
                    TextCharacter darkChar = new TextCharacter(' ', 
                                                          TextColor.ANSI.WHITE, 
                                                          TextColor.ANSI.BLACK);
                    screen.setCharacter(riderLocationX + i, riderLocationY, darkChar);
                }
            }
            
            drawTextOverBackground(screen, backgroundCopy, riderLocationX, riderLocationY, riderLocation, whiteColor);
            
            // Draw orders based on priority
            int currentX = orderStartX;
            int orderNumber = 1;
            
            while (!priorityQueue.isEmpty() && orderNumber <= 4) {
                FoodOrderEntry order = priorityQueue.poll();
                
                // Priority label
                String priorityLabel = "";
                if ("VIP".equals(order.customerType)) {
                    priorityLabel = "PRIORITY: VIP";
                } else if ("Urgent".equals(order.orderType)) {
                    priorityLabel = "PRIORITY: URGENT";
                } else if ("Bulk".equals(order.orderType)) {
                    priorityLabel = "PRIORITY: BULK";
                } else {
                    priorityLabel = "PRIORITY: STANDARD";
                }
                
                // Find the distance to display
                double distance = 0.0;
                for (int i = 0; i < CustomerAddress.length; i++) {
                    if (CustomerAddress[i].equals(order.address)) {
                        distance = AddressDistance[i];
                        break;
                    }
                }
                
                // Draw a rectangle background for better text visibility
                for (int y = 0; y < orderHeight; y++) {
                    for (int x = 0; x < orderWidth; x++) {
                        if (currentX + x < size.getColumns() && orderStartY + y < size.getRows()) {
                            // Use dark background for better readability
                            TextCharacter darkBg = new TextCharacter(' ', 
                                                               TextColor.ANSI.WHITE, 
                                                               TextColor.ANSI.BLACK);
                            screen.setCharacter(currentX + x, orderStartY + y, darkBg);
                        }
                    }
                }
                
                // Display order details
                drawTextOverBackground(screen, backgroundCopy, currentX, orderStartY, 
                        String.format("Delivery #%d - %s", orderNumber, priorityLabel), whiteColor);
                drawTextOverBackground(screen, backgroundCopy, currentX, orderStartY + 1, 
                        String.format("Customer: %s", order.customerName), whiteColor);
                drawTextOverBackground(screen, backgroundCopy, currentX, orderStartY + 2, 
                        String.format("Address: %s (%.1f km)", order.address, distance), whiteColor);
                drawTextOverBackground(screen, backgroundCopy, currentX, orderStartY + 3, 
                        String.format("Items: %d", order.foodItems.length), whiteColor);
                
                // List a few items (up to 3)
                int maxItems = Math.min(3, order.foodItems.length);
                for (int i = 0; i < maxItems; i++) {
                    drawTextOverBackground(screen, backgroundCopy, currentX, orderStartY + 4 + i, 
                            String.format("  - %s x%d", order.foodItems[i], order.quantities[i]), whiteColor);
                }
                
                if (order.foodItems.length > 3) {
                    drawTextOverBackground(screen, backgroundCopy, currentX, orderStartY + 7, 
                            String.format("  ... and %d more items", order.foodItems.length - 3), whiteColor);
                }
                
                drawTextOverBackground(screen, backgroundCopy, currentX, orderStartY + 8, 
                        String.format("Payment: %s", order.paymentMethod), whiteColor);
                
                // Move to next position
                currentX += orderWidth + spacing;
                orderNumber++;
            }
            
            screen.refresh();
            
            // Add an instruction for the user with transparent background
            String instruction = "Press any key to continue";
            int instructionX = size.getColumns() / 2 - instruction.length() / 2;
            int instructionY = size.getRows() - 2;
            
            // Draw a dark background for the instruction
            for (int i = 0; i < instruction.length(); i++) {
                if (instructionX + i < size.getColumns() && instructionY < size.getRows()) {
                    TextCharacter darkChar = new TextCharacter(' ', 
                                                          TextColor.ANSI.WHITE, 
                                                          TextColor.ANSI.BLACK);
                    screen.setCharacter(instructionX + i, instructionY, darkChar);
                }
            }
            
            // Draw the instruction with white text
            drawTextOverBackground(screen, backgroundCopy, instructionX, instructionY, instruction, whiteColor);
            screen.refresh();
            
            // Wait for key press
            screen.readInput();
            
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
