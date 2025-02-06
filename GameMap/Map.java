package GameMap;

import java.util.Random;
import java.util.Scanner;

public class Map {
    static void println(String print) {
        System.out.println(print);
    }
    static void print(String print) {
        System.out.print(print);
    }


    static String showMap(String currentLoc) {
    String[] mapLoc = {"Room 1 ", "Room 2", "Room 3", "Room 4", "Room 5"};
    String currentAd0 = "";
    String currentAd1 = "";
    String currentAd2 = "";
    String currentAd3 = "";
    String currentAd4 = "";
    

    int currentIndex = 0;
    for (int i = 0; i < mapLoc.length; i++) {
        if (currentLoc.equals(mapLoc[i])) {
            currentIndex = i;
            break;
        }
    }
    switch (currentIndex) {
        case 0:
            currentAd0 = "x";
            break;
        case 1:
            currentAd1 = "x";
            break;
        case 2:
            currentAd2 = "x";
            break;
        case 3:
            currentAd3 = "x";
            break;
        case 4:
            currentAd4 = "x";
            break;
    }
    String map = "" +
                " ___________________                                                  ___________________ \n" +
                "|                   |                                                |                   |     \n" +
                "|       " + mapLoc[0] + "        |________________________________________________|       " + mapLoc[1] + "        |   \n" +
                "|       (" + currentAd0 + ")         |________________________________________________|        (" + currentAd1 + ")         |  \n" +
                "|                   |                                                |                   |  \n" +
                "|___________________|                                                |___________________|  \n" +
                "        |  |                                                                 |  |      \n" +
                "        |  |                                                                 |  |      \n" +
                "        |  |                                                                 |  |       \n" +
                " _______|__|________                     ___________________          _______|__|________\n" +
                "|                   |                   |                   |        |                   |  \n" +
                "|                              |___________________|    " + mapLoc[4] + "    |        |      " + mapLoc[2] + "       |  \n" +
                "|                              |___________________|       (" + currentAd4 + ")          |        |       (" + currentAd2 + ")          |   \n" +
                "|                   |                   |                   |        |                   |   \n" +
                "|___________________|                   |___________________|        |___________________|   \n" +
                "                                                |  |                         |  |   \n" +
                "                                                |  |                         |  |   \n" +
                " ___________________                            |  |                         |  |   \n" +
                "|                   |                           |  |                         |  |   \n" +
                "|      " + mapLoc[3] + "       |___________________________|  |_________________________|  |  \n" +
                "|       (" + currentAd3 + ")          |___________________________________________________________|  \n" +
                "|                   |                      \n" +
                "|___________________|          \n";

    return map;

    }
    static void Title(){
        println("  ___                     _   _        _                      _____             _                      ");
        println(" |_ _|_      ____ _ _ __ | |_| |_ ___ | |__   ___  __ _ _ __ | ____|_ __   __ _(_)_ __   ___  ___ _ __ ");
        println("  | |\\ \\ /\\ / / _` | '_ \\| __| __/ _ \\| '_ \\ / _ \\/ _` | '_ \\|  _| | '_ \\ / _` | | '_ \\ / _ \\/ _ \\ '__|");
        println("  | | \\ V  V / (_| | | | | |_| || (_) | |_) |  __/ (_| | | | | |___| | | | (_| | | | | |  __/  __/ |   ");
        println(" |___| \\_/\\_/ \\__,_|_| |_|\\__|\\__\\___/|_.__/ \\___|\\__,_|_| |_|_____|_| |_|\\__, |_|_| |_|\\___|\\___|_|   ");
        println("                                                                          |___/                        ");
        
    }
    static void printWelcomeText() {
        // ANSI color codes for different shades of yellow
        String[] colors = {
            "\u001B[38;5;226m", // Bright yellow
            "\u001B[38;5;220m", // Gold
            "\u001B[38;5;214m", // Orange-yellow
            "\u001B[38;5;208m", // Dark orange
            "\u001B[38;5;202m"  // Reddish-orange
        };
    
        // Reset color
        String reset = "\u001B[0m";
    
        // Text with colors
        String text = colors[0] + "W" +     
                      colors[1] + "E" + 
                      colors[2] + "L" + 
                      colors[3] + "C" + 
                      colors[4] + "O" + 
                      colors[0] + "M" + 
                      colors[1] + "E" + 
                      colors[2] + " " + 
                      colors[3] + "T" + 
                      colors[4] + "O" + 
                      reset; // Reset color after text
        // Print the text
        println(text);
    }
    static void showMenu(Scanner scan) {
        while (true) {
            println("1. Start");
            println("2. How to Play");
            println("3. About");
            println("Choose key to proceed: ");
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    introduction();
                    
                    // Generate a random starting room
                    String[] mapLoc = {"Room 1", "Room 2", "Room 3", "Room 4", "Room 5"};
                    Random rand = new Random();
                    String randomRoom = mapLoc[rand.nextInt(mapLoc.length)]; // Pick a random room

                    System.out.println(showMap(randomRoom)); // Display the map with the random room
                    return; // Exit the menu after starting the game

                case 2:
                    println("How to Play");
                    println(
                        "You will start in a randomly assigned room and answer the questions in that room. " +
                        "After that, you will navigate through different rooms, each representing a different subject, and take the exams in each one. " +
                        "Once all exams are completed, your results will determine whether you pass or fail.\n\n" +
                        
                        "If you pass, you will proceed to the entrance exam for the engineering course. " +
                        "The result will then determine if you qualify for the engineering course. " +
                        "If you pass, the game will time-skip four years, leading to the board exam to officially become an engineer.\n\n" +
                        
                        "You have two chances to retake the board exam if you fail."
                    );
                    break;
                case 3:
                    System.out.println("About");
                    System.out.println(
                        "\"I Want to Be an Engineer\" is a story-based console game in Java that follows a young boy on his journey to achieving his dream of becoming an engineer. " +
                        "The player will take the entrance exam at a public university and must navigate different exam rooms, answer subject-related questions, and achieve the required scores to qualify for an engineering course. " +
                        "After four years in university, the player will take the board exam and, if successful, become a licensed engineer."
                    );
                    break;
                default:
                    println("Invalid choice, please try again.");
                    break;
            }
        }
    }
    static void introduction() {
        println(
            "Coming from a poor family, I have always dreamed of becoming an engineer to provide a better life for my loved ones.\n"+
            "Today marks a crucial step toward that dream—the entrance exam for a prestigious public university.\n "+
            "To achieve my goal, I must pass a series of challenging exams that will test my knowledge and skills.\n"+
            "The road ahead won’t be easy, but I am determined to succeed."
        );
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();


        print("                                                 ");printWelcomeText();
        Title();
        showMenu(scan);
       
        
       
    }
}
    


   
