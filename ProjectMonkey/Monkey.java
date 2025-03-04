import java.util.Scanner;
import java.util.Random;
public class Monkey {
    static Scanner scanner = new Scanner(System.in);

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
    static void Menu(){
        System.out.println("Start Game");
        System.out.println("How to Play");
        System.out.println("Enter key to Continue");
        int key = scanner.nextInt();
        switch (key) {
            case 1:
                clearScreen();
                System.out.println("Start Game");
                createDeck();
                System.out.println("Enter key to Continue");
                int start = scanner.nextInt();
                if (start == 1) {
                    clearScreen();
                    ShuffleDeck();
                }
                else {
                    clearScreen();
                    Menu();
                }
               

                break;
            case 2:
                System.out.println("How to Play");
                break;
        
            default:
                break;
        }
    } 

    static void createDeck(){
        String[] suits = {"\u2663", "\u2666", "\u2665", "\u2660"}; // Clubs, Diamonds, Hearts, Spades
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] labels = {"Clubs", "Diamonds", "Hearts", "Spades"};

        for (String label : labels) {
            System.out.print(label + "\t");
        }
        System.out.println();

        for (String rank : ranks) {
            for (String suit : suits) {
                System.out.print("+----+\t");
            }
            System.out.println();
            for (String suit : suits) {
                System.out.print("| " + rank + "  |\t");
            }
            System.out.println();
            for (String suit : suits) {
                System.out.print("|  " + suit + " |\t");
            }
            System.out.println();
            for (String suit : suits) {
                System.out.print("+----+\t");
            }
            System.out.println();
        }
    }
    static void ShuffleDeck(){
        String[] suits = {"\u2663", "\u2666", "\u2665", "\u2660"}; // Clubs, Diamonds, Hearts, Spades
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] deck = new String[52];
        int index = 0;
        Random random = new Random();

        for (String suit : suits) {
            for (String rank : ranks) {
                deck[index++] = rank + suit;
            }
        }

        // Shuffle the deck
        for (int i = deck.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            String temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }

        System.out.println("Shuffled Deck");

        for (String rank : ranks) {
            for (String suit : suits) {
                System.out.print("+----+\t");
            }
            System.out.println();
            for (String suit : suits) {
                System.out.print("| " + rank + "  |\t");
            }
            System.out.println();
            for (String suit : suits) {
                System.out.print("|  " + suit + " |\t");
            }
            System.out.println();
            for (String suit : suits) {
                System.out.print("+----+\t");
            }
            System.out.println();
        }
    }

            
        public static void main(String[] args) {
        Title();
        Menu();
        
        
    }
}
