import java.util.Random;
import java.util.Scanner;
public class IamMonkey {
    static Random random = new Random();
    static Scanner scan = new Scanner(System.in);
    static String[] playerCards = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
    static String[] computer_1Cards = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
    static String[] computer_2Cards = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
    static String MonkeyCard = " ";
    String[] headOrTail = {"Head", "Tail"};
    int HeadorTailRandom = random.nextInt(headOrTail.length);

    static int isPlayerWinner = 0;
    static int isComputer1Winner = 0;
    static int isComputer2Winner = 0;
    static int if_2playerIs_winner2 = 0;


    // END THE GAME
    static boolean endTheGAME = false;
    
    static String[] Club = {"[ Ace of ♣]","[2 of ♣]","[3 of ♣]","[4 of ♣]","[5 of ♣]","[6 of ♣]","[7 of ♣]","[8 of ♣]","[9 of ♣]","[10 of ♣]","[Jack of ♣]","[Queen of ♣]","[King of ♣]"};
    static String[] Spade = {"[ Ace of ♠]","[2 of ♠]","[3 of ♠]","[4 of ♠]","[5 of ♠]","[6 of ♠]","[7 of ♠]","[8 of ♠]","[9 of ♠]","[10 of ♠]","[Jack of ♠]","[Queen of ♠]","[King of ♠]"};

    static String[] Heart = {"[ Ace of ♥]","[2 of ♥]","[3 of ♥]","[4 of ♥]","[5 of ♥]","[6 of ♥]","[7 of ♥]","[8 of ♥]","[9 of ♥]","[10 of ♥]","[Jack of ♥]","[Queen of ♥]","[King of ♥]"};

    static String[] Diamond = {"[ Ace of ♦]","[2 of ♦]","[3 of ♦]","[4 of ♦]","[5 of ♦]","[6 of ♦]","[7 of ♦]","[8 of ♦]","[9 of ♦]","[10 of ♦]","[Jack of ♦]","[Queen of ♦]","[King of ♦]"};

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scan = new Scanner(System.in);

        int mainRandomizer = 0;
        int sub_randomizer = 0;

        // PRINT THE DECK OF CARD

        System.out.println("deck of cards: " + (Club.length + Spade.length + Heart.length + Diamond.length) + " cards");
        System.out.print("[CLUBS: " + Club.length + " pieces]    | ");
        for (int i = 0; i < Club.length; i++) {
            System.out.print(Club[i]+" ");
        }
        System.out.println();
        System.out.print("[SPADES: " + Spade.length + " pieces]   | ");
        for (int i = 0; i < Spade.length; i++) {
            System.out.print(Spade[i] + " ");
        }
        System.out.println();
        System.out.print("[HEARTS: " + Heart.length + " pieces]   | ");
        for (int i = 0; i < Heart.length; i++) {
            System.out.print(Heart[i] + " ");
        }
        System.out.println();
        System.out.print("[DIAMONDS: " + Diamond.length + " pieces] | ");
        for (int i = 0; i < Diamond.length; i++) {
            System.out.print(Diamond[i] + " ");
        }
        System.out.println();
        System.out.println("_____________________________________________________________________________");


        int removed_cards = 0;

        //RANDOMLY PICK 1 CARD FROM THE DECK
        mainRandomizer = random.nextInt(13);
        sub_randomizer = random.nextInt(4);

        switch (sub_randomizer) {
            case 0:
                MonkeyCard = Club[mainRandomizer];
                Club[mainRandomizer] = "x";
                removed_cards += 1;
                break;
            case 1:
                MonkeyCard = Spade[mainRandomizer];
                Spade[mainRandomizer] = "x";
                removed_cards += 1;
                break;
            case 2:
                MonkeyCard = Heart[mainRandomizer];
                Heart[mainRandomizer] = "x";
                removed_cards += 1;
                break;
            case 3:
                MonkeyCard = Diamond[mainRandomizer];
                Diamond[mainRandomizer] = "x";
                removed_cards += 1;
                break;

        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // DISPLAYING THE CURRENT DECK WITHOUT THE PICKED CARD
        while (true) {
            System.out.print("Enter any key to pick random card from the deck : ");
            if (scan.hasNextInt()) {
                int intValue = scan.nextInt();
            } else if (scan.hasNextDouble()) {
                double doubleValue = scan.nextDouble();
            } else {
                String stringValue = scan.next();
            }
            break;
        }
        System.out.println("picking a random card. please wait...");
        System.out.println();
        System.out.println("we picked" + MonkeyCard + " !");
        while (true) {
            System.out.print("Enter any key to display the new deck : ");
            if (scan.hasNextInt()) {
                int intValue = scan.nextInt();
            } else if (scan.hasNextDouble()) {
                double doubleValue = scan.nextDouble();
            } else {
                String stringValue = scan.next();
            }
            break;
        }
        System.out.println();
        System.out.println("_____________________________________________________________________________");

//////// PRINTING THE CURRENT COUNT OF CLUBS
        while (true) {
            boolean y = false;
            for (int i = 0; i < Club.length; i++) {
                boolean isTrue = false;
                if (Club[i] == "x") {
                    isTrue = true;
                }
                if (isTrue == true) {
                    System.out.print("[CLUBS: " + (Club.length - 1) + " pieces]    | ");
                    y = true;
                    break;
                }
            }
            if (y == true) {
                break;
            } else {
                System.out.print("[CLUBS: " + Club.length + " pieces]    | ");
                break;
            }
        }

        for (int i = 0; i < Club.length; i++) {
            boolean if_empty = false;
            if (Club[i] == "x") {
                if_empty = true;
            }
            if (!if_empty) {
                System.out.print(Club[i] + ", ");
            }
        }
        //////// PRINTING THE CURRENT COUNT SPADE
        System.out.println();
        while (true) {
            boolean y = false;
            for (int i = 0; i < Spade.length; i++) {
                boolean isTrue = false;
                if (Spade[i] == "x") {
                    isTrue = true;
                }
                if (isTrue == true) {
                    System.out.print("[SPADES: " + (Club.length - 1) + " pieces]   | ");
                    y = true;
                    break;
                }
            }
            if (y == true) {
                break;
            } else {
                System.out.print("[SPADES: " + Club.length + " pieces]   | ");
                break;
            }
        }

        for (int i = 0; i < Spade.length; i++) {
            boolean if_empty = false;
            if (Spade[i] == "x") {
                if_empty = true;
            }
            if (!if_empty) {
                System.out.print(Spade[i] + ", ");
            }
        }
        //////// PRINTING THE CURRENT HEARTS
        System.out.println();

        while (true) {
            boolean y = false;
            for (int i = 0; i < Heart.length; i++) {
                boolean isTrue = false;
                if (Heart[i] == "x") {
                    isTrue = true;
                }
                if (isTrue == true) {
                    System.out.print("[HEARTS: " + (Club.length - 1) + " pieces]   | ");
                    y = true;
                    break;
                }
            }
            if (y == true) {
                break;
            } else {
                System.out.print("[HEARTS: " + Club.length + " pieces]   | ");
                break;
            }
        }

        for (int i = 0; i < Heart.length; i++) {
            boolean if_empty = false;
            if (Heart[i] == "x") {
                if_empty = true;
            }
            if (!if_empty) {
                System.out.print(Heart[i] + ", ");
            }
        }
        //////// PRINTING THE CURRENT DIAMONDS
        System.out.println();

        while (true) {
            boolean y = false;
            for (int i = 0; i < Diamond.length; i++) {
                boolean isTrue = false;
                if (Diamond[i] == "x") {
                    isTrue = true;
                }
                if (isTrue == true) {
                    System.out.print("[DIAMONDS: " + (Club.length - 1) + " pieces  | ");
                    y = true;
                    break;
                }
            }
            if (y == true) {
                break;
            } else {
                System.out.print("[DIAMONDS: " + Club.length + " pieces] | ");
                break;
            }
        }

        for (int i = 0; i < Diamond.length; i++) {
            boolean if_empty = false;
            if (Diamond[i] == "x") {
                if_empty = true;
            }
            if (!if_empty) {
                System.out.print(Diamond[i] + ", ");
            }
        }
        System.out.println("_____________________________________________________________________________");

        System.out.println("the hidden/monkey card is " + MonkeyCard + "!");
///////////////////////////////////////////////////////////////////////////////////////////////////
        String[] deckWithout_MonkeyCard = new String[(Club.length + Heart.length + Spade.length + Diamond.length) - removed_cards];
        // INDICATOR FOR MONKEY CARD IS "x"

        //MAKING A DECK WITHOUT MONKEY CARD
        while (true) {
            boolean isMonkey_inThe_Type = true;
            int deckWithout_MonkeyCard_COUNT = 0;
            for (int i = 0; i < Club.length; i++) {
                if (Club[i] == "x") {
                    isMonkey_inThe_Type = false;
                }
                if (isMonkey_inThe_Type) {
                    deckWithout_MonkeyCard[deckWithout_MonkeyCard_COUNT] = Club[i];
                    deckWithout_MonkeyCard_COUNT += 1;
                }
                isMonkey_inThe_Type = true;
            }
            for (int i = 0; i < Spade.length; i++) {
                if (Spade[i] == "x") {
                    isMonkey_inThe_Type = false;
                }
                if (isMonkey_inThe_Type) {
                    deckWithout_MonkeyCard[deckWithout_MonkeyCard_COUNT] = Spade[i];
                    deckWithout_MonkeyCard_COUNT += 1;
                }
                isMonkey_inThe_Type = true;
            }
            for (int i = 0; i < Heart.length; i++) {
                if (Heart[i] == "x") {
                    isMonkey_inThe_Type = false;
                }
                if (isMonkey_inThe_Type) {
                    deckWithout_MonkeyCard[deckWithout_MonkeyCard_COUNT] = Heart[i];
                    deckWithout_MonkeyCard_COUNT += 1;
                }
                isMonkey_inThe_Type = true;
            }
            for (int i = 0; i < Diamond.length; i++) {
                if (Diamond[i] == "x") {
                    isMonkey_inThe_Type = false;
                }
                if (isMonkey_inThe_Type) {
                    deckWithout_MonkeyCard[deckWithout_MonkeyCard_COUNT] = Diamond[i];
                    deckWithout_MonkeyCard_COUNT += 1;
                }
                isMonkey_inThe_Type = true;
            }
            break;
        }
        // SHUFFLE DECK
        int number_of_currentCard = 0;
        int[] randomized_deck = new int[deckWithout_MonkeyCard.length];
        randomized_deck[0] = random.nextInt(deckWithout_MonkeyCard.length);
        while (number_of_currentCard < 51) {
            int random_num = random.nextInt(deckWithout_MonkeyCard.length);
            boolean alreadyExists = true;

            for (int i = 0; i < number_of_currentCard; i++) {
                if (random_num == randomized_deck[i]) {
                    alreadyExists = false;
                    break;
                }
            }
            if (alreadyExists) {
                randomized_deck[number_of_currentCard] = random_num;
                number_of_currentCard += 1;
            }
        }

        // ARRAY FOR STORING THE SHUFFLED CARDS
        String[] ShuffeldDeck = new String[deckWithout_MonkeyCard.length];

        // STORING THE SHUFFLED CARDS IN ShuffledDeck
        for (int i = 0; i < deckWithout_MonkeyCard.length; i++) {
            ShuffeldDeck[i] = deckWithout_MonkeyCard[randomized_deck[i]];
        }

        while (true) {
            System.out.print("Enter any key to shuffle the deck : ");
            if (scan.hasNextInt()) {
                int intValue = scan.nextInt();
            } else if (scan.hasNextDouble()) {
                double doubleValue = scan.nextDouble();
            } else {
                String stringValue = scan.next();
            }
            break;
        }
        System.out.println("shuffling...");
        System.out.println();
        System.out.println("deck shuffled.");
        System.out.println("_____________________________________________________________________________");

        for (int i = 0; i < ShuffeldDeck.length; i++) {
            boolean y = true;
            if (i == 9) {
                System.out.println(ShuffeldDeck[i]+" ");
                y = false;
            }
            if (i == 19) {
                System.out.println(ShuffeldDeck[i]+" ");
                y = false;
            }
            if (i == 29) {
                System.out.println(ShuffeldDeck[i]+" ");
                y = false;
            }
            if (i == 39) {
                System.out.println(ShuffeldDeck[i]+" ");
                y = false;
            }
            if (i == 49) {
                System.out.println(ShuffeldDeck[i]+" ");
                y = false;
            }
            if (i == ShuffeldDeck.length - 1) {
                System.out.println(ShuffeldDeck[i]+" ");
                y = false;
                break;
            }
            if (y) {
                System.out.print(ShuffeldDeck[i]+" ");
            }
        }
        System.out.println("_____________________________________________________________________________");

        //ALOCATING THE CARDS FOR THE 3 PLAYER :D
        while (true) {
            System.out.print("Enter any key to distribute cards equally to players : ");
            if (scan.hasNextInt()) {
                int intValue = scan.nextInt();
            } else if (scan.hasNextDouble()) {
                double doubleValue = scan.nextDouble();
            } else {
                String stringValue = scan.next();
            }
            break;
        }

        System.out.println("distributing card");
        System.out.println();
        System.out.println("cards distributed.");
        System.out.println();
        int cards_gave = 0;
        for (int i = 0; i < ShuffeldDeck.length; i += 3) {
            playerCards[cards_gave] = ShuffeldDeck[i];
            cards_gave++;
            if (cards_gave == 17) {
                cards_gave = 0;
            }
        }
        for (int i = 1; i < ShuffeldDeck.length; i += 3) {
            computer_1Cards[cards_gave] = ShuffeldDeck[i];
            cards_gave++;
            if (cards_gave == 17) {
                cards_gave = 0;
            }
        }
        for (int i = 2; i < ShuffeldDeck.length; i += 3) {
            computer_2Cards[cards_gave] = ShuffeldDeck[i];
            cards_gave++;
            if (cards_gave == 17) {
                cards_gave = 0;
            }
        }

        while (true) {
            System.out.print("Enter any key to display each player's on hand cards : ");
            if (scan.hasNextInt()) {
                int intValue = scan.nextInt();
            } else if (scan.hasNextDouble()) {
                double doubleValue = scan.nextDouble();
            } else {
                String stringValue = scan.next();
            }
            break;
        }

        /// SHOWING ALL PLAYERS CARDS///////////////////////
        System.out.println("_____________");

        System.out.print("players deck : ");

        for (int i = 0; i < playerCards.length; i++) {
            boolean isCard_Removed = true;
            if (playerCards[i] == " ") {
                isCard_Removed = false;
            }
            if (isCard_Removed) {
                System.out.print(playerCards[i] + ", ");
            }
        }
        System.out.println();
        System.out.println("_____________________________________________________________________________");
        System.out.print("bot 1's deck : ");
        for (int i = 0; i < computer_1Cards.length; i++) {
            System.out.print(computer_1Cards[i] + ", ");
        }
        System.out.println();
        System.out.println("_____________________________________________________________________________");
        System.out.print("bot 2's deck : ");
        for (int i = 0; i < computer_2Cards.length; i++) {
            System.out.print(computer_2Cards[i] + ", ");
        }
        System.out.println();
        System.out.println("_____________________________________________________________________________");
        System.out.println();

        System.out.println();

        // 1 TIME METHOD FOR PLAYER v
        String[] blacklist_pair1 = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
        String[] blacklist_pair2 = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
        int[] player_cards_number_player1stround = new int[17];
        int card_number = 0;
        for (int i = 0; i < 17; i++) {
            // CHECKS IF CARD IS FROM CLUB
            for (int y = 0; y < 13; y++) {
                boolean isTrue = false;
                if (playerCards[i] == Club[y]) {
                    isTrue = true;
                }
                if (isTrue) {
                    card_number += 1;
                    player_cards_number_player1stround[i] = y;
                    break;
                }
            }
            for (int y = 0; y < 13; y++) {
                boolean isTrue = false;
                if (playerCards[i] == Spade[y]) {
                    isTrue = true;
                }
                if (isTrue) {
                    card_number += 1;
                    player_cards_number_player1stround[i] = y;
                    break;
                }

            }
            for (int y = 0; y < 13; y++) {

                boolean isTrue = false;
                if (playerCards[i] == Heart[y]) {
                    isTrue = true;
                }
                if (isTrue) {
                    card_number += 1;
                    player_cards_number_player1stround[i] = y;
                    break;
                }
            }
            for (int y = 0; y < 13; y++) {
                boolean isTrue = false;
                if (playerCards[i] == Diamond[y]) {
                    isTrue = true;
                }
                if (isTrue) {
                    card_number += 1;
                    player_cards_number_player1stround[i] = y;
                    break;

                }

            }
        }
        int[] newplayer_card_number = new int[17];
        for (int i = 0; i < 17; i++) {
            newplayer_card_number[i] = player_cards_number_player1stround[i];
        }
        int pairedcardsCount = 0;
        for (int i = 0; i < 16; i++) {
            boolean isTrue = true;

            //CHEKCS IF THE CARD IS BLANK;
            if (playerCards[i] == " ") {
                isTrue = false;
            }
            //CHECKS IF THE CURRENT CARD ALREADY HAVE A PAIR
            //FINDING THE PAIR FOR THE CURRENT CARD
            if (isTrue) {
                // VERIFY IF CARD IS NOT BLANK OR IN THE BLACKLIST
                for (int x = 0; x < 17; x++) {
                    boolean isTrue2 = true;
                    if (x == i) {
                        isTrue2 = false;
                    }
                    if (playerCards[x] == " ") {
                        isTrue2 = false;
                    }
                    if (isTrue2) {
                        boolean true_pairedCard = true;
                        if (newplayer_card_number[i] == newplayer_card_number[x]) {
                            if (playerCards[i] == " ") {
                                true_pairedCard = false;
                            }
                            if (playerCards[x] == " ") {
                                true_pairedCard = false;
                            }
                            if (true_pairedCard) {
                                blacklist_pair1[pairedcardsCount] = playerCards[i];
                                blacklist_pair2[pairedcardsCount] = playerCards[x];
                                playerCards[i] = " ";
                                playerCards[x] = " ";
                                pairedcardsCount++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("_____________________________________________________________________________");

        if (true) {
            System.out.println("_____________________________________________________________________________");

            System.out.println("theres a paired cards in your deck of cards!");
            System.out.println("found paired cards:");
            for (int i = 0; i < 17; i++) {
                boolean paired_True = true;
                if (blacklist_pair1[i] == " ") {
                    paired_True = false;
                }
                if (paired_True) {
                    System.out.println(blacklist_pair1[i] + " and " + blacklist_pair2[i]);
                }
            }
        }

        System.out.println("_____________");

        System.out.print("players deck : ");

        for (int i = 0; i < playerCards.length; i++) {
            boolean isCard_Removed = true;
            if (playerCards[i] == " ") {
                isCard_Removed = false;
            }
            if (isCard_Removed) {
                System.out.print(playerCards[i] + ", ");
            }
        }
        // METHOD FOR COMPUTER ONE TIME
        String[] blacklist_pair1_computer1 = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
        String[] blacklist_pair2computer1 = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
        int[] player_cards_number_player1stround_computer1 = new int[17];
        int card_numbercomputer1 = 0;
        for (int i = 0; i < 17; i++) {
            // CHECKS IF CARD IS FROM CLUB
            for (int y = 0; y < 13; y++) {
                boolean isTrue = false;
                if (computer_1Cards[i] == Club[y]) {
                    isTrue = true;
                }
                if (isTrue) {
                    card_numbercomputer1 += 1;
                    player_cards_number_player1stround_computer1[i] = y;
                    break;
                }
            }
            for (int y = 0; y < 13; y++) {
                boolean isTrue = false;
                if (computer_1Cards[i] == Spade[y]) {
                    isTrue = true;
                }
                if (isTrue) {
                    card_numbercomputer1 += 1;
                    player_cards_number_player1stround_computer1[i] = y;
                    break;
                }

            }
            for (int y = 0; y < 13; y++) {

                boolean isTrue = false;
                if (computer_1Cards[i] == Heart[y]) {
                    isTrue = true;
                }
                if (isTrue) {
                    card_numbercomputer1 += 1;
                    player_cards_number_player1stround_computer1[i] = y;
                    break;
                }
            }
            for (int y = 0; y < 13; y++) {
                boolean isTrue = false;
                if (computer_1Cards[i] == Diamond[y]) {
                    isTrue = true;
                }
                if (isTrue) {
                    card_numbercomputer1 += 1;
                    player_cards_number_player1stround_computer1[i] = y;
                    break;

                }

            }
        }
        // System.out.println("TOTAL OF CARDS ON HAND: "+card_number+", ");
        int[] newplayer_card_numbercomputer1 = new int[17];
        for (int i = 0; i < 17; i++) {
            newplayer_card_numbercomputer1[i] = player_cards_number_player1stround_computer1[i];
        }
        int pairedcardsCountcomputer1 = 0;
        for (int i = 0; i < 16; i++) {
            boolean isTrue = true;

            //CHEKCS IF THE CARD IS BLANK;
            if (computer_1Cards[i] == " ") {
                isTrue = false;
            }
            //CHECKS IF THE CURRENT CARD ALREADY HAVE A PAIR
            //FINDING THE PAIR FOR THE CURRENT CARD
            if (isTrue) {
                // VERIFY IF CARD IS NOT BLANK OR IN THE BLACKLIST
                for (int x = 0; x < 17; x++) {
                    boolean isTrue2 = true;
                    if (x == i) {
                        isTrue2 = false;
                    }
                    if (computer_1Cards[x] == " ") {
                        isTrue2 = false;
                    }
                    if (isTrue2) {
                        boolean true_pairedCard = true;
                        if (newplayer_card_numbercomputer1[i] == newplayer_card_numbercomputer1[x]) {
                            if (computer_1Cards[i] == " ") {
                                true_pairedCard = false;
                            }
                            if (computer_1Cards[x] == " ") {
                                true_pairedCard = false;
                            }
                            if (true_pairedCard) {
                                blacklist_pair1_computer1[pairedcardsCountcomputer1] = computer_1Cards[i];
                                blacklist_pair2computer1[pairedcardsCountcomputer1] = computer_1Cards[x];
                                computer_1Cards[i] = " ";
                                computer_1Cards[x] = " ";
                                pairedcardsCountcomputer1++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("_____________________________________________________________________________");

        if (true) {
            System.out.println("_____________________________________________________________________________");

            System.out.println("theres a paired card in bot 1's deck of cards");
            System.out.println("found paired cards:");
            for (int i = 0; i < 17; i++) {
                boolean paired_True = true;
                if (blacklist_pair1_computer1[i] == " ") {
                    paired_True = false;
                }
                if (paired_True) {
                    System.out.println(blacklist_pair1_computer1[i] + " and " + blacklist_pair2computer1[i]);
                }
            }
        }
        System.out.println();
        System.out.println("______________");
        System.out.print("bot 1's deck : ");
        for (int i = 0; i < computer_1Cards.length; i++) {
            boolean isCard_Removed = true;
            if (computer_1Cards[i] == " ") {
                isCard_Removed = false;
            }
            if (isCard_Removed) {
                System.out.print(computer_1Cards[i] + ", ");
            }
        }
        System.out.println();
        
        System.out.println("_____________________________________________________________________________");
        ///METHOD FOR COMPUTER 2
        String[] blacklist_pair1_computer22 = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
        String[] blacklist_pair2_computer22 = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
        int[] player_cards_number_player1stround_computer22 = new int[17];
        int card_number_computer22 = 0;
        for (int i = 0; i < 17; i++) {
            // CHECKS IF CARD IS FROM CLUB
            for (int y = 0; y < 13; y++) {
                boolean isTrue = false;
                if (computer_2Cards[i] == Club[y]) {
                    isTrue = true;
                }
                if (isTrue) {
                    card_number_computer22 += 1;
                    player_cards_number_player1stround_computer22[i] = y;
                    break;
                }
            }
            for (int y = 0; y < 13; y++) {
                boolean isTrue = false;
                if (computer_2Cards[i] == Spade[y]) {
                    isTrue = true;
                }
                if (isTrue) {
                    card_number_computer22 += 1;
                    player_cards_number_player1stround_computer22[i] = y;
                    break;
                }

            }
            for (int y = 0; y < 13; y++) {

                boolean isTrue = false;
                if (computer_2Cards[i] == Heart[y]) {
                    isTrue = true;
                }
                if (isTrue) {
                    card_number_computer22 += 1;
                    player_cards_number_player1stround_computer22[i] = y;
                    break;
                }
            }
            for (int y = 0; y < 13; y++) {
                boolean isTrue = false;
                if (computer_2Cards[i] == Diamond[y]) {
                    isTrue = true;
                }
                if (isTrue) {
                    card_number_computer22 += 1;
                    player_cards_number_player1stround_computer22[i] = y;
                    break;

                }

            }
        }
        // System.out.println("TOTAL OF CARDS ON HAND: "+card_number+", ");
        int[] newplayer_card_number_computer22 = new int[17];
        for (int i = 0; i < 17; i++) {
            newplayer_card_number_computer22[i] = player_cards_number_player1stround_computer22[i];
        }
        int pairedcardsCount_computer22 = 0;
        for (int i = 0; i < 16; i++) {
            boolean isTrue = true;

            //CHEKCS IF THE CARD IS BLANK;
            if (computer_2Cards[i] == " ") {
                isTrue = false;
            }
            //CHECKS IF THE CURRENT CARD ALREADY HAVE A PAIR
            //FINDING THE PAIR FOR THE CURRENT CARD
            if (isTrue) {
                // VERIFY IF CARD IS NOT BLANK OR IN THE BLACKLIST
                for (int x = 0; x < 17; x++) {
                    boolean isTrue2 = true;
                    if (x == i) {
                        isTrue2 = false;
                    }
                    if (computer_2Cards[x] == " ") {
                        isTrue2 = false;
                    }
                    if (isTrue2) {
                        boolean true_pairedCard = true;
                        if (newplayer_card_number_computer22[i] == newplayer_card_number_computer22[x]) {
                            if (computer_2Cards[i] == " ") {
                                true_pairedCard = false;
                            }
                            if (computer_2Cards[x] == " ") {
                                true_pairedCard = false;
                            }
                            if (true_pairedCard) {
                                blacklist_pair1_computer22[pairedcardsCount_computer22] = computer_2Cards[i];
                                blacklist_pair2_computer22[pairedcardsCount_computer22] = computer_2Cards[x];
                                computer_2Cards[i] = " ";
                                computer_2Cards[x] = " ";
                                pairedcardsCount_computer22++;
                            }
                        }
                    }
                }
            }
        }
        if (true) {
            System.out.println("_____________________________________________________________________________");

            System.out.println("theres a paired card in bot 2's deck of cards!");
            System.out.println("found paired cards");
            for (int i = 0; i < 17; i++) {
                boolean paired_True = true;
                if (blacklist_pair1_computer22[i] == " ") {
                    paired_True = false;
                }
                if (paired_True) {
                    System.out.println(blacklist_pair1_computer22[i] + " and " + blacklist_pair2_computer22[i]);
                }
            }
        }


        System.out.println("_____________");
        System.out.print("bot 2's deck of cards: ");
        for (int i = 0; i < computer_2Cards.length; i++) {
            boolean isCard_Removed = true;
            if (computer_2Cards[i] == " ") {
                isCard_Removed = false;
            }
            if (isCard_Removed) {
                System.out.print(computer_2Cards[i] + ", ");
            }
        }

       
        System.out.println();
         System.out.println("_____________________________________________________________________________");
        System.out.println();
        int headortail = random.nextInt(2);
        int computer1_choice;
        int computer2;
        int computerwinner= 0;
        int computer2winner = 0;
        int playerwiinner = 0;
        int playerchoice;
        boolean ifdone = false;
        while (true){
            if(ifdone){
                break;
            }
            System.out.print("bot 1 is your opponent.Enter 0 for head and 1 for tail : ");
            playerchoice = scan.nextInt();
            switch(playerchoice) {
                case 0:
                    if (headortail == playerchoice) {
                        System.out.println("You won against bot 1 [tail]");
                        ifdone = true;
                    }
                        else {
                        System.out.println("You lost against bot 1 [tail]");
                        int head = random.nextInt(2);
                        int basa = random.nextInt(2);
                        if (basa == head) {
                            System.out.println("bot 1 lost against bot 2 [tail]");
                            computer2winner = 1;
                            ifdone = true;
                            break;
                        } else {
                            System.out.println("bot 1 won against bot 2 [tail]");
                            computerwinner = 1;
                            ifdone = true;
                            break;

                        }
                        }
                    ifdone = true;
                    break;
                case 1:
                    if (headortail == playerchoice) {
                        System.out.println("You won against bot 1 [head]");
                        ifdone = true;
                    }
                    else {
                        System.out.println("You lost against bot 1 [head]");
                        int head = random.nextInt(2);
                        int basa = random.nextInt(2);
                        if (basa == head) {
                            System.out.println("bot 1 lost against bot 2 [tail]");
                            computer2winner = 1;
                            ifdone = true;
                            break;
                        } else {
                            System.out.println("bot 1 won against bot 2 [tail]");
                            computerwinner = 1;
                            ifdone = true;
                            break;

                        }
                    }
                    ifdone = true;
                    break;
            }
        }
        if (playerwiinner ==1){
            while(true){
                if(endTheGAME){
                    break;
                }
                findPair_player(computer_1Cards, playerCards, computer_2Cards);
                findPair_computer1(computer_1Cards, playerCards, computer_2Cards);
                findPair_computer2(computer_1Cards, playerCards, computer_2Cards);
                if(endTheGAME){
                    break;
                }

            }
        }else{
            if(computerwinner==1){
                while(true){
                    if(endTheGAME){
                        break;
                    }
                    findPair_computer1(computer_1Cards, playerCards, computer_2Cards);
                    findPair_player(computer_1Cards, playerCards, computer_2Cards);
                    findPair_computer2(computer_1Cards, playerCards, computer_2Cards);
                    if(endTheGAME){
                        break;
                    }

                }
            }else{
                while(true){
                    if(endTheGAME){
                        break;
                    }
                    findPair_computer2(computer_1Cards, playerCards, computer_2Cards);
                    findPair_player(computer_1Cards, playerCards, computer_2Cards);
                    findPair_computer1(computer_1Cards, playerCards, computer_2Cards);
                    if(endTheGAME){
                        break;
                    }

                }
            }
        }

    }

    public static void findPair_player(String[] args, String[] arg, String[] dsgf) {
       
        // DISPLAY PLAYER DECK IF THERES A PAIRED CARD
        String[] blacklist_pair1 = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
        String[] blacklist_pair2 = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
        boolean is_there_a_Paired = false;
        int[] player_cards_number = new int[17];
        int card_number = 0;
        IamMonkey monkey = new IamMonkey();
        if(endTheGAME){
            System.exit(0);
        }
        if (isPlayerWinner == 1) {
            System.out.println();
        } else {
            if (if_2playerIs_winner2 == 2) {
                System.out.print("your the only player left, pair your last card in the hidden card.");
                for (int i = 0; i < 17; i++) {
                    if (playerCards[i] != " ") {
                        System.out.println("Your card: ");
                        System.out.println(playerCards[i]);
                        System.out.println("Monkey card: ");
                        System.out.println(MonkeyCard);
                        System.out.println("GAME OVER");
                        System.out.println("");
                        endTheGAME = true;

                        System.exit(0);

                    }

                }
            }
            //// + Counting and DISPLAY current opponent's cards
            System.out.println("_____________________________________________________________________________");
            System.out.println(" its your turn");
            System.out.println();
            System.out.print((isComputer1Winner==0)?" bot 1's card = ":"bot 2's card= ");
            int numberOfCardsofComp1 = 0;
            for (int i = 0; i < 17; i++) {
                boolean istrue = true;
                if ((isComputer1Winner == 0) ? computer_1Cards[i] == " " : computer_2Cards[i] == " ") {
                    istrue = false;
                }
                if (istrue) {
                    System.out.print(((isComputer1Winner == 0) ? computer_1Cards[i] : computer_2Cards[i]) + ", ");
                    numberOfCardsofComp1++;
                }
            }


            int pickRandomCard = random.nextInt((endTheGAME)?7:numberOfCardsofComp1);

            System.out.println();

            int[] cardChoices = new int[numberOfCardsofComp1];
            int cardChoices_counter = 0;

            for (int i = 0; i < ((isComputer1Winner == 0) ? computer_1Cards.length : computer_2Cards.length); i++) {
                if (((isComputer1Winner == 0) ? computer_1Cards[i] != " " : computer_2Cards[i] != " ")) {
                    cardChoices[cardChoices_counter] = i;
                    cardChoices_counter++;
                }
            }
            System.out.print(" your card= ");
            for (int i = 0; i < 17; i++) {
                boolean istrue = true;
                if (playerCards[i] == " ") {
                    istrue = false;
                }
                if (istrue) {
                    System.out.print(playerCards[i] + ", ");
                }
            }

         
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();

            while (true) {
                System.out.print("Enter any key to pick a card from opponent: ");
                if (scan.hasNextInt()) {
                    int intValue = scan.nextInt();
                } else if (scan.hasNextDouble()) {
                    double doubleValue = scan.nextDouble();
                } else {
                    String stringValue = scan.next();
                }
                break;

            }

            // PRINT THE PICKED OPPONENT CARD
            System.out.println("you picked a card.");
            System.out.println(((isComputer1Winner == 0) ? computer_1Cards[cardChoices[pickRandomCard]] : computer_2Cards[cardChoices[pickRandomCard]]));

            // PRINT PLAYER'S CARD
            int cardson_hand = 17;
            for (int i = 0; i < 17; i++) {
                if (playerCards[i] == " ") {
                    cardson_hand--;
                }
            }
            System.out.print("your card = ");
            for (int i = 0; i < 17; i++) {
                boolean istrue = true;
                if (playerCards[i] == " ") {
                    istrue = false;
                }
                if (istrue) {
                    System.out.print(playerCards[i] + ", ");
                }
            }

            for (int y = 0; y < 17; y++) {
                if (playerCards[y] == " ") {
                    playerCards[y] = ((isComputer1Winner == 0) ? computer_1Cards[cardChoices[pickRandomCard]] : computer_2Cards[cardChoices[pickRandomCard]]);
                    if (isComputer1Winner == 0) {
                        computer_1Cards[cardChoices[pickRandomCard]] = " ";
                        break;
                    } else {
                        computer_2Cards[cardChoices[pickRandomCard]] = " ";
                    }
                }
            }
            System.out.println();

            for (int i = 0; i < 17; i++) {
                // CHECKS IF CARD IS FROM CLUB
                for (int y = 0; y < 13; y++) {
                    boolean isTrue = false;
                    if (playerCards[i] == Club[y]) {
                        isTrue = true;
                    }
                    if (isTrue) {
                        card_number += 1;
                        player_cards_number[i] = y;
                        break;
                    }
                }
                for (int y = 0; y < 13; y++) {
                    boolean isTrue = false;
                    if (playerCards[i] == Spade[y]) {
                        isTrue = true;
                    }
                    if (isTrue) {
                        card_number += 1;
                        player_cards_number[i] = y;
                        break;
                    }

                }
                for (int y = 0; y < 13; y++) {

                    boolean isTrue = false;
                    if (playerCards[i] == Heart[y]) {
                        isTrue = true;
                    }
                    if (isTrue) {
                        card_number += 1;
                        player_cards_number[i] = y;
                        break;
                    }
                }
                for (int y = 0; y < 13; y++) {
                    boolean isTrue = false;
                    if (playerCards[i] == Diamond[y]) {
                        isTrue = true;
                    }
                    if (isTrue) {
                        card_number += 1;
                        player_cards_number[i] = y;
                        break;

                    }

                }
            }
            // FIND THE PAIR
            int[] newplayer_card_number = new int[17];
            for (int i = 0; i < 17; i++) {
                newplayer_card_number[i] = player_cards_number[i];
            }
            int pairedcardsCount = 0;
            for (int i = 0; i < 16; i++) {
                boolean isTrue = true;

                //CHEKCS IF THE CARD IS BLANK;
                if (playerCards[i] == " ") {
                    isTrue = false;
                }
 
                if (isTrue) {
                    // VERIFY IF CARD IS NOT BLANK OR IN THE BLACKLIST
                    for (int x = 0; x < 17; x++) {
                        boolean isTrue2 = true;
                        if (x == i) {
                            isTrue2 = false;
                        }
                        if (playerCards[x] == " ") {
                            isTrue2 = false;
                        }
                        if (isTrue2) {
                            boolean true_pairedCard = true;
                            if (newplayer_card_number[i] == newplayer_card_number[x]) {
                                if (playerCards[i] == " ") {
                                    true_pairedCard = false;
                                }
                                if (playerCards[x] == " ") {
                                    true_pairedCard = false;
                                }
                                if (true_pairedCard) {
                                    is_there_a_Paired = true;
                                    blacklist_pair1[pairedcardsCount] = playerCards[i];
                                    blacklist_pair2[pairedcardsCount] = playerCards[x];
                                    playerCards[i] = " ";
                                    playerCards[x] = " ";
                                    pairedcardsCount++;
                                }
                            }
                        }
                    }
                }
            }

            // IF PAIRED IS FOUND
            if (is_there_a_Paired) {
                System.out.println("_____________________________________________________________________________");

                System.out.println("paired card found.");
                System.out.println("foung paired card:");
                for (int i = 0; i < 17; i++) {
                    boolean paired_True = true;
                    if (blacklist_pair1[i] == " ") {
                        paired_True = false;
                    }
                    if (paired_True) {
                        System.out.println(blacklist_pair1[i] + " and " + blacklist_pair2[i]);
                    }
                }
                /// CHECKS IF NO MORE CARDS LEFT
            } else {
                System.out.println("_____________________________________________________________________________");

                System.out.println("theres no paired card");
            }
            // CHECK IF THERES NO MORE CARD LEFT ON THIS PLAYER
            boolean no_more_card = true;
            for (int i = 0; i < 17; i++) {
                if (playerCards[i] != " ") {
                    no_more_card = false;
                    break;
                }
            }
            boolean no_more_card2 = true;
            for (int i = 0; i < 17; i++) {
                if (((isComputer1Winner == 0) ? computer_1Cards[i] != " " : computer_2Cards[i] != " ")) {
                    no_more_card2 = false;
                    break;
                }
            }

            // IF OPPONENTS 1 OR OPPONENT 2 CARD HAS NO CARD LEFT AFTER YOUR TURN, THEN TRUE
            if (no_more_card2) {
                if (isComputer1Winner == 0) {
                    System.out.println("bot 1 has no more card. bot 1 won");
                    isComputer1Winner++;
                    if_2playerIs_winner2++;
                } else {
                    System.out.println("bot 2 has no more card. bot 2 won");
                    isComputer2Winner++;
                    if_2playerIs_winner2++;
                }
            }

            if (no_more_card) {
                System.out.println("you have no more card. you won");
                isPlayerWinner = 1;
                if_2playerIs_winner2++;

            } else {
                cardson_hand = 17;
                for (int i = 0; i < 17; i++) {
                    if (playerCards[i] == " ") {
                        cardson_hand--;
                    }
                }
                System.out.println("_____________________________________________________________________________");

                System.out.print("your card = ");
                for (int i = 0; i < 17; i++) {
                    boolean istrue = true;
                    if (playerCards[i] == " ") {
                        istrue = false;
                    }
                    if (istrue) {
                        System.out.print(playerCards[i] + ", ");
                    }
                }
                System.out.println();
                System.out.println("_____________________________________________________________________________");

                System.out.println();
                while (true) {
                    System.out.print("Enter any key to end your turn : ");
                    if (scan.hasNextInt()) {
                        int intValue = scan.nextInt();
                    } else if (scan.hasNextDouble()) {
                        double doubleValue = scan.nextDouble();
                    } else {
                        String stringValue = scan.next();
                    }
                    break;
                }
            }
        }
    }

    public static void findPair_computer1(String[] args, String[] arg, String[] dsgf) {
       
        if(endTheGAME){
            System.exit(0);
        }
        String[] blacklist_pair1 = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
        String[] blacklist_pair2 = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
        boolean is_there_a_Paired = false;
        int[] player_cards_number = new int[17];
        int card_number = 0;
        IamMonkey monkey = new IamMonkey();
        if(endTheGAME) {
            System.out.println();
        }
        if (isComputer1Winner == 1) {
            System.out.println();
        } else {
            if (if_2playerIs_winner2 == 2) {
                System.out.println("bot 1 is the only player left. check the last card if it paired with the hidden card");
                for (int i = 0; i < 17; i++) {
                    if (computer_1Cards[i] != " ") {
                        System.out.println("Your card: ");
                        System.out.println(computer_1Cards[i]);
                        System.out.println("Monkey card: ");
                        System.out.println(MonkeyCard);
                        System.out.println();
                        System.out.print("["+computer_1Cards+"]");
                        System.out.print("["+MonkeyCard+"]");
                        System.out.println("GAME OVER,");
                        System.out.println("");
                        System.exit(0);

                    }

                }
            }

            //// + Counting and DISPLAY current opponent's cards
            System.out.println();
            System.out.println("_____________________________________________________________________________");
            System.out.println(" bot 1's turn.");

            System.out.print((isComputer2Winner==0)?"bot 2's card = ":"player's card= ");
            //// + Counting and DISPLAY current opponent's cards
            int numberOfCardsofComp1 = 0;
            for (int i = 0; i < 17; i++) {
                boolean istrue = true;
                if ((isComputer2Winner == 0) ? computer_2Cards[i] == " " : playerCards[i] == " ") {
                    istrue = false;
                }
                if (istrue) {
                    System.out.print(((isComputer2Winner == 0) ? computer_2Cards[i] : playerCards[i]) + ", ");
                    numberOfCardsofComp1++;
                }
            }

            System.out.print("bot 1's card = ");
            for (int i = 0; i < 17; i++) {
                boolean istrue = true;
                if (computer_1Cards[i] == " ") {
                    istrue = false;
                }
                if (istrue) {
                    System.out.print(computer_1Cards[i] + ", ");
                }
            }

            int pickRandomCard = random.nextInt((endTheGAME)?7:numberOfCardsofComp1);
            System.out.println();

            int[] cardChoices = new int[numberOfCardsofComp1];
            int cardChoices_counter = 0;

            for (int i = 0; i < ((isComputer2Winner == 0) ? computer_2Cards.length : playerCards.length); i++) {
                if (((isComputer2Winner == 0) ? computer_2Cards[i] != " " : playerCards[i] != " ")) {
                    cardChoices[cardChoices_counter] = i;
                    cardChoices_counter++;
                }
            }

            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();

            // PRINT THE PICKED OPPONENT CARD
            System.out.println("bot 1 picked this card.");

            System.out.println(((isComputer2Winner == 0) ? computer_2Cards[cardChoices[pickRandomCard]] : playerCards[cardChoices[pickRandomCard]]));

            // PRINT PLAYER'S CARD
            int cardson_hand = 17;
            for (int i = 0; i < 17; i++) {
                if (computer_1Cards[i] == " ") {
                    cardson_hand--;
                }
            }
            System.out.print("bot 1's card' = ");
            for (int i = 0; i < 17; i++) {
                boolean istrue = true;
                if (computer_1Cards[i] == " ") {
                    istrue = false;
                }
                if (istrue) {
                    System.out.print(computer_1Cards[i] + ", ");
                }
            }

            for (int y = 0; y < 17; y++) {
                if (computer_1Cards[y] == " ") {
                    computer_1Cards[y] = ((isComputer2Winner == 0) ? computer_2Cards[cardChoices[pickRandomCard]] : playerCards[cardChoices[pickRandomCard]]);
                    if (isComputer2Winner == 0) {
                        computer_2Cards[cardChoices[pickRandomCard]] = " ";
                        break;
                    } else {
                        playerCards[cardChoices[pickRandomCard]] = " ";
                    }
                }
            }
            System.out.println();
            
            // SCANNING THE CURRENT PLAYER CARDS

            for (int i = 0; i < 17; i++) {
                // CHECKS IF CARD IS FROM CLUB
                for (int y = 0; y < 13; y++) {
                    boolean isTrue = false;
                    if (computer_1Cards[i] == Club[y]) {
                        isTrue = true;
                    }
                    if (isTrue) {
                        card_number += 1;
                        player_cards_number[i] = y;
                        break;
                    }
                }
                for (int y = 0; y < 13; y++) {
                    boolean isTrue = false;
                    if (computer_1Cards[i] == Spade[y]) {
                        isTrue = true;
                    }
                    if (isTrue) {
                        card_number += 1;
                        player_cards_number[i] = y;
                        break;
                    }

                }
                for (int y = 0; y < 13; y++) {

                    boolean isTrue = false;
                    if (computer_1Cards[i] == Heart[y]) {
                        isTrue = true;
                    }
                    if (isTrue) {
                        card_number += 1;
                        player_cards_number[i] = y;
                        break;
                    }
                }
                for (int y = 0; y < 13; y++) {
                    boolean isTrue = false;
                    if (computer_1Cards[i] == Diamond[y]) {
                        isTrue = true;
                    }
                    if (isTrue) {
                        card_number += 1;
                        player_cards_number[i] = y;
                        break;

                    }

                }
            }
            // FIND THE PAIR
            int[] newplayer_card_number = new int[17];
            for (int i = 0; i < 17; i++) {
                newplayer_card_number[i] = player_cards_number[i];
            }
            int pairedcardsCount = 0;
            for (int i = 0; i < 16; i++) {
                boolean isTrue = true;

                //CHEKCS IF THE CARD IS BLANK;
                if (computer_1Cards[i] == " ") {
                    isTrue = false;
                }
                //CHECKS IF THE CURRENT CARD ALREADY HAVE A PAIR
                //FINDING THE PAIR FOR THE CURRENT CARD
                if (isTrue) {
                    // VERIFY IF CARD IS NOT BLANK OR IN THE BLACKLIST
                    for (int x = 0; x < 17; x++) {
                        boolean isTrue2 = true;
                        if (x == i) {
                            isTrue2 = false;
                        }
                        if (computer_1Cards[x] == " ") {
                            isTrue2 = false;
                        }
                        if (isTrue2) {
                            boolean true_pairedCard = true;
                            if (newplayer_card_number[i] == newplayer_card_number[x]) {
                                if (computer_1Cards[i] == " ") {
                                    true_pairedCard = false;
                                }
                                if (computer_1Cards[x] == " ") {
                                    true_pairedCard = false;
                                }
                                if (true_pairedCard) {
                                    is_there_a_Paired = true;
                                    blacklist_pair1[pairedcardsCount] = computer_1Cards[i];
                                    blacklist_pair2[pairedcardsCount] = computer_1Cards[x];
                                    computer_1Cards[i] = " ";
                                    computer_1Cards[x] = " ";
                                    pairedcardsCount++;
                                }
                            }
                        }
                    }
                }
            }

            // IF PAIRED IS FOUND
            if (is_there_a_Paired) {
                System.out.println("_____________________________________________________________________________");

                System.out.println("paired card found.");
                System.out.println("found paired card:");
                for (int i = 0; i < 17; i++) {
                    boolean paired_True = true;
                    if (blacklist_pair1[i] == " ") {
                        paired_True = false;
                    }
                    if (paired_True) {
                        System.out.println(blacklist_pair1[i] + " and " + blacklist_pair2[i]);
                    }
                }
                /// CHECKS IF NO MORE CARDS LEFT
            } else {
                System.out.println("_____________________________________________________________________________");

                System.out.println("bot 1 didnt get paired card.");
            }
            // CHECK IF THERES NO MORE CARD LEFT ON THIS PLAYER
            boolean no_more_card = true;
            for (int i = 0; i < 17; i++) {
                if (computer_1Cards[i] != " ") {
                    no_more_card = false;
                    break;
                }
            }
            boolean no_more_card2 = true;
            for (int i = 0; i < 17; i++) {
                if (((isComputer2Winner == 0) ? computer_2Cards[i] != " " : playerCards[i] != " ")) {
                    no_more_card2 = false;
                    break;
                }
            }

            if (no_more_card2) {
                if (isComputer2Winner == 0) {
                    System.out.println("bot 1 has no more card. bot 1 won");
                    isComputer2Winner++;
                    if_2playerIs_winner2++;
                } else {
                    System.out.println("you have no more card. you won");
                    isPlayerWinner++;
                    if_2playerIs_winner2++;
                }
            }

            if (no_more_card) {
                System.out.println("NO MORE CARD FOR PLAYER... PLAYER 1 HAS WON!");
                isComputer1Winner = 1;
                if_2playerIs_winner2++;

            } else {
                cardson_hand = 17;
                for (int i = 0; i < 17; i++) {
                    if (computer_1Cards[i] == " ") {
                        cardson_hand--;
                    }
                }
                System.out.println("_____________________________________________________________________________");

                System.out.print("bot 1's card= ");
                for (int i = 0; i < 17; i++) {
                    boolean istrue = true;
                    if (computer_1Cards[i] == " ") {
                        istrue = false;
                    }
                    if (istrue) {
                        System.out.print(computer_1Cards[i] + ", ");
                    }
                }
                System.out.println();
                System.out.println("_____________________________________________________________________________");
                System.out.println();
                while (true) {
                    System.out.print("Enter any key to end bot 1's turn : ");
                    if (scan.hasNextInt()) {
                        int intValue = scan.nextInt();
                    } else if (scan.hasNextDouble()) {
                        double doubleValue = scan.nextDouble();
                    } else {
                        String stringValue = scan.next();
                    }
                    break;
                }
            }
        }
    }
    public static void findPair_computer2(String[] args, String[] arg, String[] dsgf) {
 
        String[] blacklist_pair1 = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
        String[] blacklist_pair2 = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
        boolean is_there_a_Paired = false;
        int[] player_cards_number = new int[17];
        int card_number = 0;
        IamMonkey monkey = new IamMonkey();
        if(endTheGAME){
            System.exit(0);
        }
        if (isComputer2Winner == 1) {
            System.out.println();
        } else {
            if (if_2playerIs_winner2 == 2) {
                System.out.print("bot 2 is the only player left. pair his last card to the hidden card");
                for (int i = 0; i < 17; i++) {
                    if (computer_2Cards[i] != " ") {
                        System.out.println("Computer 2's card: ");
                        System.out.println(computer_2Cards[i]);
                        System.out.println("Monkey card: ");
                        System.out.println(MonkeyCard);
                        System.out.println("GAME OVER, ");
                        System.out.println("");
                        System.exit(0);


                    }

                }
            }

            //// + Counting and DISPLAY current opponent's cards
            System.out.println();
            System.out.println("_____________________________________________________________________________");
            System.out.println(" bot 2's turn.");
            System.out.print((isPlayerWinner==0)?"player's card = ":"bot 1's card= ");
            int numberOfCardsofComp1 = 0;
            for (int i = 0; i < 17; i++) {
                boolean istrue = true;
                if ((isPlayerWinner == 0) ? playerCards[i] == " " : computer_1Cards[i] == " ") {
                    istrue = false;
                }
                if (istrue) {
                    System.out.print(((isPlayerWinner == 0) ? playerCards[i] : computer_1Cards[i]) + ", ");
                    numberOfCardsofComp1++;
                }
            }

            System.out.print("bot 2's card' = ");
            for (int i = 0; i < 17; i++) {
                boolean istrue = true;
                if (computer_2Cards[i] == " ") {
                    istrue = false;
                }
                if (istrue) {
                    System.out.print(computer_2Cards[i] + ", ");
                }
            }
            int pickRandomCard = random.nextInt((endTheGAME)?7:numberOfCardsofComp1);
            System.out.println();

            int[] cardChoices = new int[numberOfCardsofComp1];
            int cardChoices_counter = 0;

            for (int i = 0; i < ((isPlayerWinner == 0) ? playerCards.length :  computer_1Cards.length); i++) {
                if (((isPlayerWinner == 0) ? playerCards[i] != " " :  computer_1Cards[i] != " ")) {
                    cardChoices[cardChoices_counter] = i;
                    cardChoices_counter++;
                }
            }

            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();

            // PRINT THE PICKED OPPONENT CARD
            System.out.println("bot 2 picked this card.");

            System.out.println(((isPlayerWinner == 0) ? playerCards[cardChoices[pickRandomCard]] : computer_1Cards[cardChoices[pickRandomCard]]));

            // PRINT PLAYER'S CARD
            int cardson_hand = 17;
            for (int i = 0; i < 17; i++) {
                if (computer_2Cards[i] == " ") {
                    cardson_hand--;
                }
            }
            System.out.print("bot 2's card = ");
            for (int i = 0; i < 17; i++) {
                boolean istrue = true;
                if (computer_2Cards[i] == " ") {
                    istrue = false;
                }
                if (istrue) {
                    System.out.print(computer_2Cards[i] + ", ");
                }
            }

            for (int y = 0; y < 17; y++) {
                if (computer_2Cards[y] == " ") {
                    computer_2Cards[y] = ((isPlayerWinner == 0) ? playerCards[cardChoices[pickRandomCard]] : computer_1Cards[cardChoices[pickRandomCard]]);
                    if (isPlayerWinner == 0) {
                        playerCards[cardChoices[pickRandomCard]] = " ";
                        break;
                    } else {
                        computer_1Cards[cardChoices[pickRandomCard]] = " ";
                    }
                }
            }
            System.out.println();

            for (int i = 0; i < 17; i++) {
                // CHECKS IF CARD IS FROM CLUB
                for (int y = 0; y < 13; y++) {
                    boolean isTrue = false;
                    if (computer_2Cards[i] == Club[y]) {
                        isTrue = true;
                    }
                    if (isTrue) {
                        card_number += 1;
                        player_cards_number[i] = y;
                        break;
                    }
                }
                for (int y = 0; y < 13; y++) {
                    boolean isTrue = false;
                    if (computer_2Cards[i] == Spade[y]) {
                        isTrue = true;
                    }
                    if (isTrue) {
                        card_number += 1;
                        player_cards_number[i] = y;
                        break;
                    }

                }
                for (int y = 0; y < 13; y++) {

                    boolean isTrue = false;
                    if (computer_2Cards[i] == Heart[y]) {
                        isTrue = true;
                    }
                    if (isTrue) {
                        card_number += 1;
                        player_cards_number[i] = y;
                        break;
                    }
                }
                for (int y = 0; y < 13; y++) {
                    boolean isTrue = false;
                    if (computer_2Cards[i] == Diamond[y]) {
                        isTrue = true;
                    }
                    if (isTrue) {
                        card_number += 1;
                        player_cards_number[i] = y;
                        break;

                    }

                }
            }
            // FIND THE PAIR
            int[] newplayer_card_number = new int[17];
            for (int i = 0; i < 17; i++) {
                newplayer_card_number[i] = player_cards_number[i];
            }
            int pairedcardsCount = 0;
            for (int i = 0; i < 16; i++) {
                boolean isTrue = true;

                //CHEKCS IF THE CARD IS BLANK;
                if (computer_2Cards[i] == " ") {
                    isTrue = false;
                }

                if (isTrue) {
                    // VERIFY IF CARD IS NOT BLANK OR IN THE BLACKLIST
                    for (int x = 0; x < 17; x++) {
                        boolean isTrue2 = true;
                        if (x == i) {
                            isTrue2 = false;
                        }
                        if (computer_2Cards[x] == " ") {
                            isTrue2 = false;
                        }
                        if (isTrue2) {
                            boolean true_pairedCard = true;
                            if (newplayer_card_number[i] == newplayer_card_number[x]) {
                                if (computer_2Cards[i] == " ") {
                                    true_pairedCard = false;
                                }
                                if (computer_2Cards[x] == " ") {
                                    true_pairedCard = false;
                                }
                                if (true_pairedCard) {
                                    is_there_a_Paired = true;
                                    blacklist_pair1[pairedcardsCount] = computer_2Cards[i];
                                    blacklist_pair2[pairedcardsCount] = computer_2Cards[x];
                                    computer_2Cards[i] = " ";
                                    computer_2Cards[x] = " ";
                                    pairedcardsCount++;
                                }
                            }
                        }
                    }
                }
            }

            // IF PAIRED IS FOUND
            if (is_there_a_Paired) {
                System.out.println("_____________________________________________________________________________");

                System.out.println("paired cards found.");
                System.out.println("found paired card:");
                for (int i = 0; i < 17; i++) {
                    boolean paired_True = true;
                    if (blacklist_pair1[i] == " ") {
                        paired_True = false;
                    }
                    if (paired_True) {
                        System.out.println(blacklist_pair1[i] + " and " + blacklist_pair2[i]);
                    }
                }
                /// CHECKS IF NO MORE CARDS LEFT
            } else {
                System.out.println("_____________________________________________________________________________-");

                System.out.println("bot 2 didnt found a paired card.");
            }
            // CHECK IF THERES NO MORE CARD LEFT ON THIS PLAYER
            boolean no_more_card = true;
            for (int i = 0; i < 17; i++) {
                if (computer_2Cards[i] != " ") {
                    no_more_card = false;
                    break;
                }
            }
            boolean no_more_card2 = true;
            for (int i = 0; i < 17; i++) {
                if (((isPlayerWinner == 0) ? playerCards[i] != " " : computer_1Cards[i] != " ")) {
                    no_more_card2 = false;
                    break;
                }
            }

            if (no_more_card2) {
                if (isPlayerWinner == 0) {
                    System.out.println("you have no more card. you won. ");
                    isPlayerWinner++;
                    if_2playerIs_winner2++;
                } else {
                    System.out.println("bot 1 has no more card. bot 1 won.");
                    isComputer1Winner++;
                    if_2playerIs_winner2++;
                }
            }


            // END PLAYER'S PARTICIPATION IF THE CONDITION ABOVE IS TRUE
            if (no_more_card) {
                System.out.println("bot 2 has no more card. bot 2 won.");
                isComputer2Winner = 1;
                if_2playerIs_winner2++;

            } else {
                cardson_hand = 17;
                for (int i = 0; i < 17; i++) {
                    if (computer_2Cards[i] == " ") {
                        cardson_hand--;
                    }
                }
                System.out.println("_____________________________________________________________________________");

                System.out.print("bot 2's card= ");
                for (int i = 0; i < 17; i++) {
                    boolean istrue = true;
                    if (computer_2Cards[i] == " ") {
                        istrue = false;
                    }
                    if (istrue) {
                        System.out.print(computer_2Cards[i] + ", ");
                    }
                }
                System.out.println();
                System.out.println("_____________________________________________________________________________");
                System.out.println();
                while (true) {
                    System.out.print("Enter any key to end bot 2's turn : ");
                    if (scan.hasNextInt()) {
                        int intValue = scan.nextInt();
                    } else if (scan.hasNextDouble()) {
                        double doubleValue = scan.nextDouble();
                    } else {
                        String stringValue = scan.next();
                    }
                    break;
                }
            }
        }
    }
}