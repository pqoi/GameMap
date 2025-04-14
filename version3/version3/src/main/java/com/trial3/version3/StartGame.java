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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class StartGame {
    public static String[] DaetBarangay = {"Bagasbas","Baranggay I", "Baranggay II", "Calasgasan"};
    static String[] CustomerName = {"Luffy", "Zoro", "Nami", "Usopp", "Sanji"};
    static String[] CustomerAddress = {"Daet", "Mercedes", "San Vicente", "Talisay", "Basud", "Capalonga", "Labo", "Paracale", "San Lorenzo Ruiz", "Santa Elena", "Vinzons"};
    public static final String[] FoodOrder = {
        "Burger", "Fries", "Pizza", "Milk Tea", "Soda", 
        "Chicken", "Ice Cream", "Spaghetti", "Hotdog", "Nuggets"
    };
    
    public static final String[] FoodEmojis = {
        "🍔", "🍟", "🍕", "🧋", "🥤", 
        "🍗", "🍨", "🍝", "🌭", "🍖"
    };
    
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
            List<Order> orders = generateRandomOrders();
            showOrders(screen, orders);

            screen.stopScreen();
            screen.stopScreen();
    


            

            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }
    
    public static class Order {
        String item;
        String municipality;
        boolean isUrgent;
        boolean isVIP;

        public Order(String item, String municipality, boolean isUrgent, boolean isVIP) {
            this.item = item;
            this.municipality = municipality;
            this.isUrgent = isUrgent;
            this.isVIP = isVIP;
        }

        public String getDisplayText() {
            return item + "\n" +
                    "Municipality: " + municipality + "\n" +
                    "Urgent: " + (isUrgent ? "Yes" : "No") + "\n" +
                    "VIP: " + (isVIP ? "Yes" : "No");
        }
    } 
    public static List<Order> generateRandomOrders() {
        List<String> items = Arrays.asList("🍕 Small Pizza", "🍔 Burger Combo", "🍜 Noodles", "🍛 Rice Meal", "🥤 Drink Only", "🍩 Donuts", "🍟 Fries");
        List<String> municipalities = Arrays.asList("A", "B", "C", "D", "E", "F", "G");
        List<Order> orders = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            String item = items.get(random.nextInt(items.size()));
            String municipality = municipalities.get(random.nextInt(municipalities.size()));
            boolean isUrgent = random.nextBoolean();
            boolean isVIP = random.nextBoolean();
            orders.add(new Order(item, municipality, isUrgent, isVIP));
        }

        return orders;
    }

    public static void showOrders(Screen screen, List<Order> orders) {
        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen);
        Panel mainPanel = new Panel(new GridLayout(3));

        TerminalSize panelSize = new TerminalSize(30, 7);

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            Panel orderPanel = new Panel();
            orderPanel.setPreferredSize(panelSize);
            orderPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
            orderPanel.setTheme(new SimpleTheme(TextColor.ANSI.BLACK, TextColor.ANSI.WHITE));
            orderPanel.addComponent(new Label(order.getDisplayText()));
            orderPanel.withBorder(Borders.singleLine("Order " + (i + 1)));
            mainPanel.addComponent(orderPanel);
        }

        // Fill empty cell to align last row
        if (orders.size() == 5) {
            mainPanel.addComponent(new EmptySpace(panelSize));
        }

        BasicWindow window = new BasicWindow("Random Orders");
        window.setComponent(mainPanel);
        gui.addWindowAndWait(window);
    }
     
    public static String[] generateOrder(String orderType, Random rand, List<String> usedNames) {
        String name;
        do {
            name = CustomerName[rand.nextInt(CustomerName.length)];
        } while (usedNames.contains(name));
        usedNames.add(name);
    
        String location = CustomerAddress[rand.nextInt(CustomerAddress.length)];
        if (location.equals("Daet")) {
            location += ", Barangay " + DaetBarangay[rand.nextInt(DaetBarangay.length)];
        }
    
        int numItems = orderType.equals("Bulk") ? rand.nextInt(6) + 5 : rand.nextInt(4) + 1;
        Map<String, Integer> orderDetails = new LinkedHashMap<>();
        double totalPrice = 0;
    
        for (int i = 0; i < numItems; i++) {
            int foodIndex;
            String foodItem;
            do {
                foodIndex = rand.nextInt(FoodOrder.length);
                foodItem = FoodOrder[foodIndex];
            } while (orderDetails.containsKey(foodItem));
            int quantity = rand.nextInt(3) + 1;
            orderDetails.put(foodItem, quantity);
            totalPrice += FoodPrices[foodIndex] * quantity;
        }
    
        String paymentMethod = PaymentMethods[rand.nextInt(PaymentMethods.length)];
    
        // Calculate the maximum width needed for the card
        int maxWidth = 26; // Minimum width
        
        maxWidth = Math.max(maxWidth, "Customer: ".length() + name.length() + 2);
        maxWidth = Math.max(maxWidth, "Order Type: ".length() + orderType.length() + 2);
        maxWidth = Math.max(maxWidth, "Location: ".length() + location.length() + 2);
        
        for (Map.Entry<String, Integer> entry : orderDetails.entrySet()) {
            int foodIndex = Arrays.asList(FoodOrder).indexOf(entry.getKey());
            String emoji = FoodEmojis[foodIndex];
            String itemLine = String.format("%s %s x%d | ₱%.2f", 
                emoji, entry.getKey(), entry.getValue(), 
                FoodPrices[foodIndex] * entry.getValue());
            
            maxWidth = Math.max(maxWidth, itemLine.length() + 4); // +4 for margins
        }
        
        maxWidth = Math.max(maxWidth, "Total Price: ₱".length() + String.format("%.2f", totalPrice).length() + 2);
        maxWidth = Math.max(maxWidth, paymentMethod.length() + 2);
        
        // Make sure width is even for symmetry
        if (maxWidth % 2 != 0) maxWidth++;
        
        // Create the order card with dynamic width
        List<String> orderCard = new ArrayList<>();
        
        String topBorder = "+" + "-".repeat(maxWidth - 2) + "+";
        orderCard.add(topBorder);
        
        orderCard.add(formatLine("Customer: " + name, maxWidth));
        orderCard.add(formatLine("Order Type: " + orderType, maxWidth));
        orderCard.add(formatLine("Location: " + location, maxWidth));
        
        String divider = "|" + "-".repeat(maxWidth - 2) + "|";
        orderCard.add(divider);
        
        for (Map.Entry<String, Integer> entry : orderDetails.entrySet()) {
            int foodIndex = Arrays.asList(FoodOrder).indexOf(entry.getKey());
            double itemTotalPrice = FoodPrices[foodIndex] * entry.getValue();
            orderCard.add(formatLine(String.format("%s x%d | ₱%.2f", entry.getKey(), 
                                    entry.getValue(), itemTotalPrice), maxWidth));
        }
        
        orderCard.add(divider);
        orderCard.add(formatLine(String.format("Total Price: ₱%.2f", totalPrice), maxWidth));
        orderCard.add(formatLine(paymentMethod, maxWidth));
        orderCard.add(topBorder);
        
        return orderCard.toArray(new String[0]);
    }
    
    // Helper method to format a line with proper padding in the card
    private static String formatLine(String content, int width) {
        int contentLength = content.length();
        int padding = width - 2 - contentLength;
        return "| " + content + " ".repeat(padding) + "|";
    }
    
    public static void displayOrdersLanterna(Screen screen, List<String[]> orders) throws IOException {
        screen.clear();
        TextGraphics tg = screen.newTextGraphics();
    
        TerminalSize terminalSize = screen.getTerminalSize();
        int totalCards = orders.size();
        int spacing = 4;
    
        // Determine the max height of cards
        int cardHeight = orders.stream().mapToInt(order -> order.length).max().orElse(10);
        int[] cardWidths = orders.stream().mapToInt(card -> card[0].length()).toArray();
    
        int totalWidth = Arrays.stream(cardWidths).sum() + spacing * (totalCards - 1);
        int startX = (terminalSize.getColumns() - totalWidth) / 2;
        int startY = (terminalSize.getRows() - cardHeight) / 2;
    
        int currentX = startX;
    
        for (int i = 0; i < totalCards; i++) {
            String[] order = orders.get(i);
            int cardWidth = cardWidths[i];
    
            for (int j = 0; j < order.length; j++) {
                tg.putString(currentX, startY + j, order[j]);
            }
    
            currentX += cardWidth + spacing;
        }
    
        screen.refresh();
    
        // Wait for ENTER key
        KeyStroke key;
        do {
            key = screen.readInput();
        } while (key.getKeyType() != KeyType.Enter);
    }
    

     // Helper to generate a progress bar string
     private static String getProgressBar(int percent, int barLength) {
        int filledLength = percent * barLength / 100;
        char[] bar = new char[barLength];
        Arrays.fill(bar, 0, filledLength, '█');
        Arrays.fill(bar, filledLength, barLength, '░');
        return "[" + new String(bar) + "]";
    }
    private static void displayHomeBackground(Screen screen) throws IOException {
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
