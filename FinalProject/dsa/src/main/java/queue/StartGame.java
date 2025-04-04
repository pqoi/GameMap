package queue;

import java.util.*;

public class StartGame {
    public static String[] DaetBarangay = {"Bagasbas", "Bacong", "Banga", "Bantigue", "Bignay I", "Bignay II", "Calasgasan", "Dapdap", "Del Pilar", "Gulod", "Ipil", "Lourdes", "Magsaysay", "Mambalit", "Mambalot", "Mamburao", "Manuel L. Quezon", "San Isidro", "San Jose", "San Juan", "San Roque"};
    static String[] CustomerName = {"Luffy", "Zoro", "Nami", "Usopp", "Sanji"};
    static String[] CustomerAddress = {"Daet", "Mercedes", "San Vicente", "Talisay", "Basud", "Capalonga", "Labo", "Paracale", "San Lorenzo Ruiz", "Santa Elena", "Vinzons"};
    static String[] FoodOrder = {"Burger", "Pizza", "Pasta", "Salad", "Sushi", "Sandwich", "Spaghetti", "Fries", "Fried Chicken", "Doughnuts", "Cupcakes"};
    static double[] FoodPrices = {80.0, 150.0, 120.0, 100.0, 200.0, 90.0, 110.0, 50.0, 130.0, 70.0, 60.0};
    static String[] PaymentMethods = {"Cash on Delivery (Paid)", "Online Payment (Paid)"};

    public static void main(String[] args) {
        Random rand = new Random();
        List<String> usedNames = new ArrayList<>();

        // Generate 5 orders
        List<String[]> orderCards = new ArrayList<>();

        orderCards.add(generateOrder("VIP", rand, usedNames));
        orderCards.add(generateOrder("Bulk", rand, usedNames));
        for (int i = 0; i < 3; i++) {
            orderCards.add(generateOrder("Regular", rand, usedNames));
        }

        // Display orders in 3+2 horizontal layout
        displayOrders(orderCards);
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
            String itemLine = String.format("%s x%d | ₱%.2f", entry.getKey(), entry.getValue(), 
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
    
    public static void displayOrders(List<String[]> orders) {
        // Display all orders vertically (one after another)
        for (String[] order : orders) {
            for (String line : order) {
                System.out.println(line);
            }
            System.out.println(); // Add a blank line between orders
        }
    
        System.out.println("\nPress 'Enter' to continue...");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.nextLine().equals("")) {
            System.out.println("Please press 'Enter' to continue.");
        }
    }
}
