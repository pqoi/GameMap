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

        public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(50);
                System.out.print("\r" + " ".repeat(80) + "\r");
            }
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(50);
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
        
    static String getCardASCII(String card) {
        String rank = card.substring(0, card.length() - 1); // Extract rank (e.g., "A" from "A♠")
        String suit = card.substring(card.length() - 1); // Extract suit (e.g., "♠" from "A♠")
        
        // Padding for ranks to ensure proper alignment
        String paddedRank = rank.length() == 1 ? rank + " " : rank;
        
        return String.format(
            "┌───────┐\n" +
            "│ %s    │\n" +
            "│       │\n" +
            "│   %s   │\n" +
            "│       │\n" +
            "│    %s │\n" +
            "└───────┘", 
            paddedRank, suit, paddedRank);
    }
 static String getSuitName(String suit) {
        switch (suit) {
            case "♣": return "Clubs";
            case "♦": return "Diamonds";
            case "♥": return "Hearts";
            case "♠": return "Spades";
            default: return "";
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
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

        // Create and display the deck
        String[] deck = new String[52];
        int cardIndex = 0;
        
       for (String suit : SUITS) {
            System.out.println("=== " + suit + " (" + getSuitName(suit) + ") ===\n");
            
            // Create an array to hold all cards of current suit
            String[] suitCards = new String[RANKS.length];
            for (int i = 0; i < RANKS.length; i++) {
                suitCards[i] = RANKS[i] + suit;
            }

            String[][] cardLines = new String[suitCards.length][];
            for (int i = 0; i < suitCards.length; i++) {
                cardLines[i] = getCardASCII(suitCards[i]).split("\n");
            }
                
                // Print each row of all cards
                for (int line = 0; line < 7; line++) {  // 7 lines per card
                    for (int j = 0; j < cardLines.length; j++) {
                        System.out.print(cardLines[j][line] + "  ");
                    }
                    System.out.println();
                }
                System.out.println();  // Extra space between rows
            
    }
    for (String suit : SUITS) {
        for (String rank : RANKS) {
            deck[cardIndex++] = rank + suit;
        }
    }

        // Pick a random card from the deck
        Random rand = new Random();
        String chosenCard = deck[rand.nextInt(52)];
        System.out.println("\nRandomly Picked Card: ");

        // Display the chosen card in ASCII art
        System.out.println(getCardASCII(chosenCard));
        
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
         System.out.println("Deck after shuffling and removing the chosen card:");
          int cardsPerRow = 13; // Number of cards per row
          for (int i = 0; i < deck.length; i += cardsPerRow) {
          String[][] cardLines = new String[Math.min(cardsPerRow, deck.length - i)][];

            // Convert each card into ASCII format and split into lines
            for (int j = 0; j < cardLines.length; j++) {
                cardLines[j] = getCardASCII(deck[i + j]).split("\n");
            }

            // Print each line of all cards side by side
            for (int line = 0; line < 7; line++) {  // Each card has 7 lines
            for (int j = 0; j < cardLines.length; j++) {
            System.out.print(cardLines[j][line] + "  "); // Print each card line side by side
            }
                System.out.println(); // Move to the next line
        }
        System.out.println(); // Add space between rows of cards
    }
        System.out.println("Enter any key to distribute the cards to the players and 4 computers:");
        scanner.next();
        // Reset card index
        cardIndex = 0;

        // Distribute the cards
        String[] humanHand = new String[11];
        String[][] botHands = new String[4][10];

        // Assign 11 cards to the human player
        for (int i = 0; i < 11; i++) {
            humanHand[i] = deck[cardIndex++];
        }

        // Assign 10 cards to each bot
        for (int b = 0; b < 4; b++) {
            for (int i = 0; i < 10; i++) {
                botHands[b][i] = deck[cardIndex++];
            }
        }

        // Display hands
        System.out.println("\nHuman Player's Hand:");
        for (String card : humanHand) {
            System.out.print(card + " ");
        }
        System.out.println("\n");

        // Display bot hands
        for (int b = 0; b < 4; b++) {
            System.out.println("Bot " + (b + 1) + "'s Hand:");
            for (String card : botHands[b]) {
                System.out.print(card + " ");
            }
            System.out.println("\n");
        }

        // Hidden card remains secret
        System.out.println("A card is hidden for game mechanics.");
        System.out.println(("Checking Duplicate Cards..."));

        // Check for duplicate cards
        boolean hasDuplicates = false;
        String[] seenCards = new String[52];
        int seenIndex = 0;
        String[] duplicates = new String[52];
        int duplicateIndex = 0;

        // Check human hand for duplicates
        for (String card : humanHand) {
            boolean isDuplicate = false;
            for (int i = 0; i < seenIndex; i++) {
            if (seenCards[i].equals(card)) {
                duplicates[duplicateIndex++] = card;
                isDuplicate = true;
                hasDuplicates = true;
                break;
            }
            }
            if (!isDuplicate) {
            seenCards[seenIndex++] = card;
            }
        }

        // Check bot hands for duplicates
        for (String[] botHand : botHands) {
            for (String card : botHand) {
            boolean isDuplicate = false;
            for (int i = 0; i < seenIndex; i++) {
                if (seenCards[i].equals(card)) {
                duplicates[duplicateIndex++] = card;
                isDuplicate = true;
                hasDuplicates = true;
                break;
                }
            }
            if (!isDuplicate) {
                seenCards[seenIndex++] = card;
            }
            }
        }

        if (hasDuplicates) {
            System.out.print("Duplicate cards found: ");
            for (int i = 0; i < duplicateIndex; i++) {
            System.out.print(duplicates[i] + " ");
            }
            System.out.println();
        } else {
            System.out.println("No duplicate cards found.");
        }

        // Remove duplicates from human hand
        String[] newHumanHand = new String[11];
        int newHumanIndex = 0;
        for (String card : humanHand) {
            boolean isDuplicate = false;
            for (int i = 0; i < duplicateIndex; i++) {
            if (duplicates[i].equals(card)) {
                isDuplicate = true;
                break;
            }
            }
            if (!isDuplicate) {
            newHumanHand[newHumanIndex++] = card;
            }
        }
        humanHand = Arrays.copyOf(newHumanHand, newHumanIndex);

        // Remove duplicates from bot hands
        for (int b = 0; b < botHands.length; b++) {
            String[] newBotHand = new String[10];
            int newBotIndex = 0;
            for (String card : botHands[b]) {
            boolean isDuplicate = false;
            for (int i = 0; i < duplicateIndex; i++) {
                if (duplicates[i].equals(card)) {
                isDuplicate = true;
                break;
                }
            }
            if (!isDuplicate) {
                newBotHand[newBotIndex++] = card;
            }
            }
            botHands[b] = Arrays.copyOf(newBotHand, newBotIndex);
        }

        // Display remaining cards for each player
        System.out.println("\nHuman Player's Hand after removing duplicates:");
        for (String card : humanHand) {
            System.out.print(card + " ");
        }
        System.out.println("\n");

        for (int b = 0; b < botHands.length; b++) {
            System.out.println("Bot " + (b + 1) + "'s Hand after removing duplicates:");
            for (String card : botHands[b]) {
            System.out.print(card + " ");
            }
            System.out.println("\n");
        }

        System.out.println("Enter any key to roll the dice to decide who picks first:");
        scanner.next();

        // Simulate dice rolling animation for human player
        int humanRoll = 0;
        try {
            humanRoll = rollDiceWithAnimation(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int[] botRolls = new int[4];
        boolean[] rollUsed = new boolean[7]; // Track used rolls (1-6)
        rollUsed[humanRoll] = true;

        for (int i = 0; i < 4; i++) {
            int roll;
            do {
            // Simulate dice rolling animation for each bot
            try {
                roll = rollDiceWithAnimation(random);
            } catch (InterruptedException e) {
                e.printStackTrace();
                roll = random.nextInt(6) + 1; // Fallback in case of interruption
            }
            } while (rollUsed[roll]);
            botRolls[i] = roll;
            rollUsed[roll] = true;
        }

        // Display dice roll results
        System.out.println("Human Player rolled: " + humanRoll);
        for (int i = 0; i < 4; i++) {
            System.out.println("Bot " + (i + 1) + " rolled: " + botRolls[i]);
        }

        // Determine picking order
        int[] rolls = new int[5];
        rolls[0] = humanRoll;
        System.arraycopy(botRolls, 0, rolls, 1, 4);

        Integer[] order = new Integer[5];
        for (int i = 0; i < 5; i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) -> Integer.compare(rolls[a], rolls[b]));

        System.out.println("Picking order:");
        for (int i = 0; i < 5; i++) {
            if (order[i] == 0) {
            System.out.println((i + 1) + ": Human Player");
            } else {
            System.out.println((i + 1) + ": Bot " + order[i]);
            }
        }

    }

    // Dice rolling animation
    private static int rollDiceWithAnimation(Random random) throws InterruptedException {
        int finalRoll = random.nextInt(6) + 1;
        String[] diceFaces = {"⚀", "⚁", "⚂", "⚃", "⚄", "⚅"};

        System.out.print("Rolling: ");
        for (int i = 0; i < 10; i++) {  // Simulate rolling animation
            System.out.print(diceFaces[random.nextInt(6)] + " ");
            Thread.sleep(200);  // Delay to create animation effect
        }
        System.out.println("\nFinal Roll: " + diceFaces[finalRoll - 1]);

        return finalRoll;
    }
}
 
