import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Monkey {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    // Card constants
    static final String[] SUITS = {"♣", "♦", "♥", "♠"}; 
    static final String[] RANKS = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    static final String[] SUIT_NAMES = {"Clubs", "Diamonds", "Hearts", "Spades"};
    static String[][] players = new String[4][13]; // Max 13 cards per player
    static int[] playerCardCount = {0, 0, 0, 0}; // Track card count per player
    static String[] deck = new String[52];

    static void Title() {
        System.out.println("  █████████████████████████████████████████████████████████████████████████████");
        System.out.println("");
        System.out.println("███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    ");
        System.out.println("████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    ");
        System.out.println("██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     ");
        System.out.println("██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      ");
        System.out.println("██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       ");
        System.out.println("╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       ");
        System.out.println("                                                           ");
        System.out.println("███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    ");
        System.out.println("████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    ");
        System.out.println("██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     ");
        System.out.println("██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      ");
        System.out.println("██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       ");
        System.out.println("╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       ");
        System.out.println("                                                           ");
        System.out.println("");
        System.out.println("  █████████████████████████████████████████████████████████████████████████████");
    }

    static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 

    public static void main(String[] args) {
        Title();
        System.out.println("1. Start Game");
        System.out.println("2. How to Play");
        System.out.println("Enter your choice:");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                clearScreen();
                
                break;
            case 2:
                clearScreen();
                System.out.println("How to Play");
                System.out.println("The game is played by 4 players. Each player is dealt 13 cards.");
                System.out.println("The player with the highest card wins the round.");
                System.out.println("The game continues until all 52 cards are played.");
                System.out.println("The player with the most number of rounds won is the winner.");
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        // Define card suits and ranks
        String[] SUITS = {"♣", "♠", "♥", "♦"};
        String[] SUIT_NAMES = {"Clubs", "Spades", "Hearts", "Diamonds"};
        String[] RANKS = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        // Print suit names
        for (String suitName : SUIT_NAMES) {
            System.out.printf("%-10s", suitName);
        }
        System.out.println("\n");

        // Create and display the deck
        String[] deck = new String[52];
        int index = 0;
        
        for (String rank : RANKS) {
            // Top border
            for (String suit : SUITS) {
                System.out.print("+----+\t");
            }
            System.out.println();

            // Rank
            for (String suit : SUITS) {
                System.out.printf("| %-2s |\t", rank);
                deck[index++] = rank + suit;
            }
            System.out.println();

            // Suit
            for (String suit : SUITS) {
                System.out.printf("|  %s |\t", suit);
            }
            System.out.println();

            // Bottom border
            for (String suit : SUITS) {
                System.out.print("+----+\t");
            }
            System.out.println();
        }

        // Pick a random card from the deck
        Random rand = new Random();
        String chosenCard = deck[rand.nextInt(52)];
        System.out.println("\nRandomly Picked Card: ");

        // Display the chosen card in ASCII art
        System.out.println("+----+");
        System.out.printf("| %-2s |\n", chosenCard.substring(0, chosenCard.length() - 1));
        System.out.printf("|  %s |\n", chosenCard.substring(chosenCard.length() - 1));
        System.out.println("+----+");
        System.out.println("Enter any key to shuffle the cards:");
        scanner.next();
        // Remove the chosen card from the deck
        for (int i = 0; i < deck.length; i++) {
            if (deck[i].equals(chosenCard)) {
            deck[i] = deck[deck.length - 1];
            deck = Arrays.copyOf(deck, deck.length - 1);
            break;
            }
        }
        System.out.println("Deck after removing the chosen card:");
        System.out.println("\n");

        // Shuffle the deck after removing the chosen card
        for (int i = deck.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            String temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }

        // Visual representation of the deck after shuffling and removing the chosen card
        for (int i = 0; i < deck.length; i += 4) {
            // Top border
            for (int j = i; j < i + 4 && j < deck.length; j++) {
            System.out.print("+----+\t");
            }
            System.out.println();

            // Rank
            for (int j = i; j < i + 4 && j < deck.length; j++) {
            System.out.printf("| %-2s |\t", deck[j].substring(0, deck[j].length() - 1));
            }
            System.out.println();

            // Suit
            for (int j = i; j < i + 4 && j < deck.length; j++) {
            System.out.printf("|  %s |\t", deck[j].substring(deck[j].length() - 1));
            }
            System.out.println();

            // Bottom border
            for (int j = i; j < i + 4 && j < deck.length; j++) {
            System.out.print("+----+\t");
            }
            System.out.println();
        }
        System.out.println("Enter any key to distribute the cards to the players and 4 computers:");
        scanner.next();
        // Distribute the cards to the players and 4 computers
        
    }
}