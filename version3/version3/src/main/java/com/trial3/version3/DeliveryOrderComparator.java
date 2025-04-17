package com.trial3.version3;

import java.util.Comparator;

/**
 * Custom comparator for sorting food delivery orders based on priority rules:
 * 1. VIP orders always come first
 * 2. Urgent orders are next in priority
 * 3. Orders closer to the rider's location (Barangay VI Centro) come before those farther away
 * 4. Bulk orders have the lowest priority unless they are marked as Urgent or VIP
 */
public class DeliveryOrderComparator implements Comparator<StartGame.FoodOrderEntry> {
    
    @Override
    public int compare(StartGame.FoodOrderEntry order1, StartGame.FoodOrderEntry order2) {
        // VIP customer priority
        if ("VIP".equals(order1.customerType) && !"VIP".equals(order2.customerType)) {
            return -1; // order1 comes first
        }
        if (!"VIP".equals(order1.customerType) && "VIP".equals(order2.customerType)) {
            return 1; // order2 comes first
        }
        
        // Urgent order priority
        if ("Urgent".equals(order1.orderType) && !"Urgent".equals(order2.orderType)) {
            return -1; // order1 comes first
        }
        if (!"Urgent".equals(order1.orderType) && "Urgent".equals(order2.orderType)) {
            return 1; // order2 comes first
        }
        
        // Distance comparison (closer first)
        double distance1 = getDistanceToRider(order1.address);
        double distance2 = getDistanceToRider(order2.address);
        if (distance1 < distance2) {
            return -1; // order1 is closer
        }
        if (distance1 > distance2) {
            return 1; // order2 is closer
        }
        
        // Bulk orders get lowest priority unless they are VIP or Urgent
        // (The above rules already handle VIP and Urgent cases)
        if ("Bulk".equals(order1.orderType) && !"Bulk".equals(order2.orderType)) {
            return 1; // order1 is bulk, push to end
        }
        if (!"Bulk".equals(order1.orderType) && "Bulk".equals(order2.orderType)) {
            return -1; // order2 is bulk, push to end
        }
        
        // Default case: equal priority
        return 0;
    }
    
    /**
     * Helper method to get the distance from rider's location to the address
     */
    private double getDistanceToRider(String address) {
        // Find the index of the address in the CustomerAddress array
        for (int i = 0; i < StartGame.CustomerAddress.length; i++) {
            if (StartGame.CustomerAddress[i].equals(address)) {
                return StartGame.AddressDistance[i];
            }
        }
        // Default value if address not found
        return Double.MAX_VALUE;
    }
}