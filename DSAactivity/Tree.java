package DSAactivity;

import java.util.*;

public class Tree{
    static Random rand = new Random();
    static int[] array = new int[20]; 
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        for (int i = 0; i < array.length; i++) {
            array[i] = -1;
        }

        try{
            System.out.println("Starting the Tree");
            array[0] = RandomValue(); 
            System.out.println("Root node value: " + array[0]);
            System.out.println("Enter the number of children for the root node (0,1,2): ");
            int childRoot = scan.nextInt();
            addChildren(parentIndex,childCount,scan);
            boolean Running = true;
            do{
                System.out.println("MENU");
                System.out.println("1. Show Root");
                System.out.println("2. Show Parent of a Node");
                System.out.println("3. Show Children of a Node");
                System.out.println("4. Show Internal Nodes");
                System.out.println("5. Show Leaf Nodes");
                System.out.println("6. Display Full Tree Array");
                System.out.println("7. Exit");
                int Menuchoice = scan.nextInt();
                
                switch (Menuchoice) {
                    case 1:
                        System.out.println("Root Node: " + array[0]);
                        break;
                    case 2:
                        System.out.print("Enter the index of the node to find its parent: ");
                        int parentIndex = scan.nextInt();
                        showParent(parentIndex);
                        break;
                    case 3:
                        System.out.print("Enter the index of the node to find its children: ");
                        int childIndex = scan.nextInt();
                        showChildren(childIndex);
                        break;
                    case 4:
                        System.out.println("Internal Nodes: ");
                        showInternalNodes();
                        break;
                    case 5:
                        System.out.println("Leaf Nodes: ");
                        showLeafNodes();
                        break;
                    case 6:
                        System.out.println("Tree Structure: ");
                        displayTreeArray();
                        break;
                    case 7:
                        Running = false;
                        System.out.println("Exiting the program.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }while(Running);
        }catch (Exception e) {
           System.out.println("Invalid input. Please try again");
        } 
    }
    public static int RandomValue(){
        int randomValue = rand.nextInt(91) + 10;
        return randomValue;
    }
    public static void addChildren(int parentIndex, int childCount, Scanner scan){
        if(childCount == 0){
            System.out.println("No children for this node.");
            System.out.println("The node is a leaf node.");

        }else if(childCount == 1){
            int leftChildIndex = 2 * parentIndex + 1;
            if(leftChildIndex < array.length){
                array[leftChildIndex] = RandomValue();
                System.out.println("Added left child at index " + leftChildIndex + " with value " + array[leftChildIndex]);
                System.out.println("Enter the number of children for node at index " + leftChildIndex + " (0,1,2): ");
                int children = scan.nextInt();
                addChildren(leftChildIndex, children, scan);
            }
        }else if(childCount == 2){
            int leftChildIndex = 2 * parentIndex + 1;
            int rightChildIndex = 2 * parentIndex + 2;
            if(leftChildIndex < array.length){
                array[leftChildIndex] = RandomValue();
                System.out.println("Added left child at index " + leftChildIndex + " with value " + array[leftChildIndex]);
                System.out.println("Enter the number of children for node at index " + leftChildIndex + " (0,1,2): ");
                int childrenLeft = scan.nextInt();
                addChildren(leftChildIndex, childrenLeft, scan);
            }
            if(rightChildIndex < array.length){
                array[rightChildIndex] = RandomValue();
                System.out.println("Added right child at index " + rightChildIndex + " with value " + array[rightChildIndex]);
                System.out.println("Enter the number of children for node at index " + rightChildIndex + " (0,1,2): ");
                int childrenRight = scan.nextInt();
                addChildren(rightChildIndex, childrenRight, scan);
            }
        } 
    }
    public static void showParent(int nodeIndex){
        if(nodeIndex == 0){
            System.out.println("Root node has no parent.");
        }else{
            int parentIndex = (nodeIndex - 1) / 2;
            System.out.println("Parent of node at index " + nodeIndex + " is: " + array[parentIndex]);
        }
    }
    public static void showChildren(int nodeIndex){
        int leftChildIndex = 2 * nodeIndex + 1;
        int rightChildIndex = 2 * nodeIndex + 2;
        if(leftChildIndex < array.length && array[leftChildIndex] != -1){
            System.out.println("Left child of node at index " + nodeIndex + " is: " + array[leftChildIndex]);
        }else{
            System.out.println("No left child for node at index " + nodeIndex);
        }
        if(rightChildIndex < array.length && array[rightChildIndex] != -1){
            System.out.println("Right child of node at index " + nodeIndex + " is: " + array[rightChildIndex]);
        }else{
            System.out.println("No right child for node at index " + nodeIndex);
        }
    }

    public static void showInternalNodes() {
        System.out.println("Internal Nodes:");
        for (int i = 0; i < array.length; i++) {
            if (array[i] != -1) {
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                if ((left < array.length && array[left] != -1) || (right < array.length && array[right] != -1)) {
                    System.out.println("Index " + i + ": " + array[i]);
                }
            }
        }
    }
    public static void showLeafNodes() {
        System.out.println("Leaf Nodes:");
        for (int i = 0; i < array.length; i++) {
            if (array[i] != -1) {
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                if ((left >= array.length || array[left] == -1) && (right >= array.length || array[right] == -1)) {
                    System.out.println("Index " + i + ": " + array[i]);
                }
            }
        }
    }
    public static void displayTreeArray() {
        System.out.println("Tree Structure:");
        int level = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != -1) {
                int currentLevel = (int) (Math.log(i + 1) / Math.log(2));
                if (currentLevel > level) {
                    System.out.println(); // Move to the next level
                    level = currentLevel;
                }
                int spaces = (int) Math.pow(2, Math.max(3 - level, 0)); // Adjust spacing
                for (int j = 0; j < spaces; j++) {
                    System.out.print(" ");
                }
                System.out.print(array[i]);
            }
        }
        System.out.println(); // Final newline
    } 
}