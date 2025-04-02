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

        return new String[]{
            String.format("+--------------------------+"),
            String.format("| Customer: %-13s |", name),
            String.format("| Order Type: %-10s |", orderType),
            String.format("| Location: %-12s |", location),
            String.format("|--------------------------|"),
            orderDetails.entrySet().stream()
                .map(entry -> {
                    int foodIndex = Arrays.asList(FoodOrder).indexOf(entry.getKey());
                    double itemTotalPrice = FoodPrices[foodIndex] * entry.getValue();
                    return String.format("| %-12s x%-2d | ₱%-6.2f |", entry.getKey(), entry.getValue(), itemTotalPrice);
                }).reduce((a, b) -> a + "\n" + b).orElse(""),
            String.format("|--------------------------|"),
            String.format("| Total Price:  ₱%-6.2f |", totalPrice),
            String.format("| %-24s |", paymentMethod),
            String.format("+--------------------------+")
        };
    }

    public static void displayOrders(List<String[]> orders) {
        // Display first 3 orders in one row
        for (int line = 0; line < orders.get(0).length; line++) {
            for (int i = 0; i < 3; i++) {
                System.out.print(orders.get(i)[line] + "\t");
            }
            System.out.println();
        }
        System.out.println();

        // Display last 2 orders in a second row
        for (int line = 0; line < orders.get(3).length; line++) {
            for (int i = 3; i < 5; i++) {
                System.out.print(orders.get(i)[line] + "\t");
            }
            System.out.println();
        }

        System.out.println("\nPress 'Enter' to continue...");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.nextLine().equals("")) {
            System.out.println("Please press 'Enter' to continue.");
        }
    }
}
