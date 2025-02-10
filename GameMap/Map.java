package GameMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Map {
    static java.util.Map<String, Integer> scores = new HashMap<>();
    static void println(String print) {
        System.out.println(print);
    }
    static void print(String print) {
        System.out.print(print);
    }

    private static Integer dist1, dist2, dist3, dist4, dist5, dist6, dist7, dist8;
    static String showMap(String currentLoc) {
        Random rand = new Random();
    
        if (dist1 == null) {
            dist1 = rand.nextInt(100) + 1; 
            dist2 = rand.nextInt(100) + 1; 
            dist3 = rand.nextInt(100) + 1; 
            dist4 = rand.nextInt(100) + 1; 
            dist5 = rand.nextInt(100) + 1; 
            dist6 = rand.nextInt(100) + 1; 
            dist7 = rand.nextInt(100) + 1; 
            dist8 = rand.nextInt(100) + 1; 
        }
        String[] mapLoc = {"Room 1 ", "Room 2", "Room 3", "Room 4", "Room 5", "Intersection 1", "Intersection 2"};
        String[] currentAd = new String[7];
    
        for (int i = 0; i < currentAd.length; i++) {
            currentAd[i] = "";
        }
    
        int currentIndex = 0;
        for (int i = 0; i < mapLoc.length; i++) {
            if (currentLoc.equals(mapLoc[i])) {
                currentIndex = i;
                break;
            }
        }
        currentAd[currentIndex] = "x";
    
        String map = "" +
                " ___________________                                                  ___________________   \n" +
                "|                   |                                                |                   |  \n" +
                "|                   |________________________________________________|                   |  \n" +
                "|       " + mapLoc[0] + "                       " + mapLoc[6] + "                         " + mapLoc[1] + "      |  \n" +
                "|        (" + currentAd[0] + ")                                ("   +  currentAd[6]  +   ")                                  ("  + currentAd[1] +  ")        |  \n" +
                "|                    _____________________   ________________________                    |  \n" +
                "|                   |          "  + dist1 +  "m        |  |            "  +  dist2 +  "m        |                   |  \n" +
                "|________  _________|                     |  |                       |________   ________|  \n" +
                "        |  |                              |  |                                |  |      \n" +
                "        |  |                              |  | "  + dist3 +  "m                            |  |      \n" +
                "        |  |                              |  |                                |  |       \n" +
                "        |  |                       _______|  |________                        |  |\n" +
                "        |  |                      |                   |                       |  |  \n" +
                "  "  + dist4 +  "m   |  |                      |      "  + mapLoc[4] +  "       |                       |  |  " + dist5 +  "m     \n" +
                "        |  |                      |        ("  + currentAd[4] +  ")         |                       |  |           \n" +
                "        |  |                      |                   |                       |  |   \n" +
                "        |  |                      |___________________|                       |  |   \n" +
                "        |  |                              |  |                                |  |   \n" +
                "        |  |                              |  | " + dist6 + "m                            |  |   \n" +
                " _______|  |_________                     |  |                        ________|  |_______  \n" +
                "|                   |          " + dist7 + "m        |  |          "  + dist8 +  "m           |                  |   \n"  +
                "|                   |_____________________|  |________________________|                  |  \n" +
                "|                                                                                        |    \n" +
                "|      " + mapLoc[3] + "                        " + mapLoc[5] + "                          " + mapLoc[2] + "      |  \n" +
                "|        ("  + currentAd[3] +  ")                               (" + currentAd[5] + ")                                  (" + currentAd[2] + ")         |    \n" +
                "|                   __________________________________________________                   |    \n" +
                "|                   |                                                 |                  |        \n" +
                "|___________________|                                                 |__________________|                  \n";

        return map;
    }

    static void Title(){

        println("\u001B[33m"); // Yellow color
        println("                                           ██     ██     ██  █████  ███    ██ ████████    ████████  ██████      ██████  ███████ ");
        println("                                           ██     ██     ██ ██   ██ ████   ██    ██          ██    ██    ██     ██   ██ ██      ");
        println("                                           ██     ██  █  ██ ███████ ██ ██  ██    ██          ██    ██    ██     ██████  █████   ");
        println("                                           ██     ██ ███ ██ ██   ██ ██  ██ ██    ██          ██    ██    ██     ██   ██ ██      ");
        println("                                           ██      ███ ███  ██   ██ ██   ████    ██          ██     ██████      ██████  ███████ ");
        println("\u001B[0m"); // Reset color
        System.out.println();
        println("\u001B[33m"); // Yellow color
        println("                                                                            █████  ███    ██ ");
        println("                                                                           ██   ██ ████   ██ ");
        println("                                                                           ███████ ██ ██  ██ ");
        println("                                                                           ██   ██ ██  ██ ██ ");
        println("                                                                           ██   ██ ██   ████ ");
        println("\u001B[0m"); //
        System.out.println();
        println("\u001B[33m"); // Yellow color
        println("                                                   ███████ ███    ██  ██████  ██ ███    ██ ███████ ███████ ██████  ");
        println("                                                   ██      ████   ██ ██       ██ ████   ██ ██      ██      ██   ██ ");
        println("                                                   █████   ██ ██  ██ ██   ███ ██ ██ ██  ██ █████   █████   ██████  ");
        println("                                                   ██      ██  ██ ██ ██    ██ ██ ██  ██ ██ ██      ██      ██   ██ ");
        println("                                                   ███████ ██   ████  ██████  ██ ██   ████ ███████ ███████ ██   ██ ");
        println("\u001B[0m"); // Reset color
    }
    
    static String showMenu(Scanner scan) {
        while (true) {
            println("1. Start");
            println("2. How to Play");
            println("3. About");
            println("Choose key to proceed: ");
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    println("Enter Username:");
                    String username = scan.next(); // Get user input for username
                    
                    println(username + ", welcome to the game!");
                    println("Starting the game...");
                    introduction(); // Call introduction method
                    
                    println("Your first choice is Engineering.");
                    println("Please enter your second and third choice of courses:");
                    
                    String choice2 = scan.next(); // Get second choice
                    String choice3 = scan.next(); // Get third choice
                    
                    println("Your chosen courses:");
                    println("1. Engineering");
                    println("2. " + choice2);
                    println("3. " + choice3);
                    
                    // Generate a random starting room
                    String[] mapLoc = {"Room 1", "Room 2", "Room 3", "Room 4", "Room 5", "Intersection 1", "Intersection 2"};
                    Random rand = new Random();
                    String randomRoom = mapLoc[rand.nextInt(mapLoc.length)]; // Pick a random room

                    System.out.println(showMap(randomRoom)); // Display the map with the random room
                    return randomRoom; // Return the current location


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
                    println("About");
                    println(
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
   
    static void askQuestion(String currentLoc, Scanner scan) {
        String[][] questions = {
            { // Room 1 - English
                "What is the synonym of 'Happy'?\nA) Sad\nB) Joyful\nC) Angry\nD) Confused",
                "Which is a correct sentence?\nA) He go to school.\nB) She going school.\nC) They are playing.\nD) Me is hungry.",
                "What is the past tense of 'Run'?\nA) Running\nB) Runs\nC) Ran\nD) Runned",
                "Which is a noun?\nA) Run\nB) Quickly\nC) Elephant\nD) Jumping",
                "Choose the correct spelling:\nA) Becouse\nB) Because\nC) Beccause\nD) Becauze"
            },
            { // Room 2 - Math
                "What is 12 ÷ 4?\nA) 2\nB) 3\nC) 4\nD) 6",
                "What is 15% of 200?\nA) 20\nB) 25\nC) 30\nD) 35",
                "What is the square root of 81?\nA) 7\nB) 8\nC) 9\nD) 10",
                "Which number is prime?\nA) 4\nB) 6\nC) 9\nD) 13",
                "Solve: 5 × (3 + 2)\nA) 15\nB) 20\nC) 25\nD) 30"
            },
            { // Room 3 - Filipino
                "Ano ang kahulugan ng 'Maligaya'?\nA) Malungkot\nB) Masaya\nC) Galit\nD) Takot",
                "Ano ang pambansang bayani ng Pilipinas?\nA) Emilio Aguinaldo\nB) Andres Bonifacio\nC) Jose Rizal\nD) Lapu-Lapu",
                "Ano ang kabisera ng Pilipinas?\nA) Cebu\nB) Davao\nC) Manila\nD) Baguio",
                "Ano ang salitang balbal para sa 'Pera'?\nA) Kuwarta\nB) Datung\nC) Kaban\nD) Lupa",
                "Ano ang salitang ugat ng 'Kumakain'?\nA) Kain\nB) Kumain\nC) Kakain\nD) Kinain"
            },
            { // Room 4 - Logical Thinking
                "Which shape has three sides?\nA) Square\nB) Triangle\nC) Circle\nD) Pentagon",
                "Which number is missing? 2, 4, 6, ?, 10\nA) 7\nB) 8\nC) 9\nD) 10",
                "If a dog has 4 legs, how many legs do 3 dogs have?\nA) 6\nB) 8\nC) 10\nD) 12",
                "If today is Monday, what day is 3 days later?\nA) Wednesday\nB) Thursday\nC) Friday\nD) Saturday",
                "Which word does NOT belong?\nA) Apple\nB) Banana\nC) Orange\nD) Chair"
            },
            { // Room 5 - Ethics & Decision-Making
                "What is considered ethical behavior?\nA) Lying for personal gain\nB) Stealing if no one notices\nC) Being honest and fair\nD) Cheating to win",
                "Which is an ethical choice?\nA) Taking credit for someone else's work\nB) Helping a lost child find their parents\nC) Ignoring someone in need\nD) Spreading false rumors",
                "If you see a wallet on the ground, what should you do?\nA) Keep the money\nB) Report it to authorities\nC) Ignore it\nD) Hide it",
                "Which action is unfair?\nA) Treating everyone equally\nB) Making decisions based on facts\nC) Discriminating against people\nD) Helping others",
                "Why is honesty important?\nA) It builds trust\nB) It makes people afraid\nC) It helps you lie better\nD) It has no effect"
            }
        };

        char[][] answers = {
            {'B', 'C', 'C', 'C', 'B'}, // Room 1 answers
            {'B', 'C', 'C', 'D', 'C'}, // Room 2 answers
            {'B', 'C', 'C', 'B', 'A'}, // Room 3 answers
            {'B', 'B', 'D', 'B', 'D'}, // Room 4 answers
            {'C', 'B', 'B', 'C', 'A'}  // Room 5 answers
        };

        int roomIndex = -1;
        switch (currentLoc) {
            case "Room 1": roomIndex = 0; break;
            case "Room 2": roomIndex = 1; break;
            case "Room 3": roomIndex = 2; break;
            case "Room 4": roomIndex = 3; break;
            case "Room 5": roomIndex = 4; break;
            default:
                System.out.println("You are in an intersection. No question here.");
                return;
        }

        int score = 0;
        for (int i = 0; i < 5; i++) {
            System.out.println(questions[roomIndex][i]);
            System.out.print("Your answer: ");
            char answer = scan.next().toUpperCase().charAt(0);
            if (answer == answers[roomIndex][i]) {
                score++;
            }
        }

        scores.put(currentLoc, score);
        System.out.println("You scored " + score + " out of 5 in " + currentLoc + ".");
    }
    static String MenuDestination(String currentLocation, Scanner scan) {
        println("You are in " + currentLocation);
        println("Where would you like to go?");
        
        String[] rooms = {"Room 1", "Room 2", "Room 3", "Room 4", "Room 5"};
    
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].equals(currentLocation)) {
                continue; // Skip the current location
            }
            println((i + 1) + ". " + rooms[i]);
        } 
    
        println("Enter the number of the room you want to go to: ");
        
        int choice = scan.nextInt();
        return "Room " + choice;
    }
    static void showRoutesAndFindShortest(String currentLoc, String destination) {
        // Use stored distances instead of generating new ones
        HashMap<String, Integer> distances = new HashMap<>();
        distances.put("Room 1-Intersection 2", dist1);
        distances.put("Intersection 2-Room 2", dist2);
        distances.put("Intersection 2-Room 5", dist3);
        distances.put("Room 1-Room 4", dist4);
        distances.put("Room 2-Room 3", dist5);
        distances.put("Intersection 1-Room 5", dist6);
        distances.put("Room 4-Intersection 1", dist7);
        distances.put("Room 3-Intersection 1", dist8);
    
        // Possible paths
        List<String> routes = new ArrayList<>();
        List<Integer> routeDistances = new ArrayList<>();
        // Room 1 possible routes
        if (currentLoc.equals("Room 1")) {
            if (destination.equals("Room 2")) {
                routes.add("Room 1 to (" + dist1 + "m) Intersection 2 to (" + dist2 + "m) Room 2");
                routeDistances.add(dist1 + dist2);
            }
            if (destination.equals("Room 3")) {
                routes.add("Room 1 to (" + dist1 + "m) Intersection 2 to (" + dist2 + "m) Room 2 to (" + dist5 + "m) Room 3");
                routeDistances.add(dist1 + dist2 + dist5);
            }
            if (destination.equals("Room 4")) {
                routes.add("Room 1 to (" + dist4 + "m) Room 4");
                routeDistances.add(dist4);
            }
            if (destination.equals("Room 5")) {
                routes.add("Room 1 to (" + dist1 + "m) Intersection 2 to (" + dist3 + "m) Room 5");
                routeDistances.add(dist1 + dist3);
                routes.add("Room 1 to (" + dist4 + "m) Room 4 to (" + dist7 + "m) Intersection 1 to (" + dist6 + "m) Room 5");
                routeDistances.add(dist4 + dist7 + dist6);
            }
        }
        
        if (currentLoc.equals("Room 2")) {
            if (destination.equals("Room 1")) {
                routes.add("Room 2 to (" + dist2 + "m) Intersection 2 to (" + dist1 + "m) Room 1");
                routeDistances.add(dist2 + dist1);
            }
            if (destination.equals("Room 3")) {
                routes.add("Room 2 to (" + dist5 + "m) Room 3");
                routeDistances.add(dist5);
            }
            if (destination.equals("Room 4")) {
                routes.add("Room 2 to (" + dist2 + "m) Intersection 2 to (" + dist1 + "m) Room 1 to (" + dist4 + "m) Room 4");
                routeDistances.add(dist2 + dist1 + dist4);
            }
            if (destination.equals("Room 5")) {
                routes.add("Room 2 to (" + dist2 + "m) Intersection 2 to (" + dist3 + "m) Room 5");
                routeDistances.add(dist2 + dist3);
            }
        }
        // Room 3 possible routes
        if (currentLoc.equals("Room 3")) {
            if (destination.equals("Room 1")) {
                routes.add("Room 3 to (" + dist5 + "m) Room 2 to (" + dist2 + "m) Room 1");
                routeDistances.add(dist5 + dist2);
                routes.add("Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist7 + "m) Room 1");
                routeDistances.add(dist8 + dist7);
                routes.add("Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist3 + "m) Intersection 2 to (" + dist1 + "m) Room 1");
                routeDistances.add(dist8 + dist3 + dist1);
            }
            if (destination.equals("Room 2")) {
                routes.add("Room 3 to (" + dist5 + "m) Room 2");
                routeDistances.add(dist5);
                routes.add("Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist2 + "m) Room 2");
                routeDistances.add(dist8 + dist2);
            }
            if (destination.equals("Room 4")) {
                routes.add("Room 3 to (" + dist6 + "m) Room 5 to (" + dist7 + "m) Room 4");
                routeDistances.add(dist6 + dist7);
                routes.add("Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist7 + "m) Room 4");
                routeDistances.add(dist8 + dist7);
            }
            if (destination.equals("Room 5")) {
                routes.add("Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist6 + "m) Room 5");
                routeDistances.add(dist8 + dist6);
                routes.add("Room 3 to (" + dist6 + "m) Room 4 to (" + dist3 + "m) Room 5");
                routeDistances.add(dist6 + dist3);
            }
        }   
        
        if (currentLoc.equals("Room 4")) {
            if (destination.equals("Room 1")) {
                routes.add("Room 4 to (" + dist4 + "m) Room 1");
                routeDistances.add(dist4);
            }
            if (destination.equals("Room 2")) {
                routes.add("Room 4 to (" + dist7 + "m) Intersection 1 to (" + dist6 + "m) Room 5 to (" + dist3 + "m) Intersection 2 to (" + dist2 + "m) Room 2");
                routeDistances.add(dist7 + dist6 + dist3 + dist2);
            }
            if (destination.equals("Room 3")) {
                routes.add("Room 4 to (" + dist7 + "m) Intersection 1 to (" + dist8 + "m) Room 3");
                routeDistances.add(dist7 + dist8);
            }
            if (destination.equals("Room 5")) {
                routes.add("Room 4 to (" + dist7 + "m) Intersection 1 to (" + dist6 + "m) Room 5");
                routeDistances.add(dist7 + dist6);
            }
        }

        if (currentLoc.equals("Room 5")) {
            if (destination.equals("Room 1")) {
                routes.add("Room 5 to (" + dist3 + "m) Intersection 2 to (" + dist1 + "m) Room 1");
                routeDistances.add(dist3 + dist1);
            }
            if (destination.equals("Room 2")) {
                routes.add("Room 5 to (" + dist3 + "m) Intersection 2 to (" + dist2 + "m) Room 2");
                routeDistances.add(dist3 + dist2);
            }
            if (destination.equals("Room 3")) {
                routes.add("Room 5 to (" + dist6 + "m) Intersection 1 to (" + dist8 + "m) Room 3");
                routeDistances.add(dist6 + dist8);
            }
            if (destination.equals("Room 4")) {
                routes.add("Room 5 to (" + dist6 + "m) Intersection 1 to (" + dist7 + "m) Room 4");
                routeDistances.add(dist6 + dist7);
            }
        }

        // Intersection 1 possible routes
        if (currentLoc.equals("Intersection 1")) {
            if (destination.equals("Room 1")) {
                routes.add("Intersection 1 → Room 4 → Room 1");
                routeDistances.add(distances.get("Room 4-Intersection 1") + distances.get("Room 1-Room 4"));
            }
            if (destination.equals("Room 2")) {
                routes.add("Intersection 1 → Room 5 → Intersection 2 → Room 2");
                routeDistances.add(distances.get("Intersection 1-Room 5") + distances.get("Intersection 2-Room 5") + distances.get("Intersection 2-Room 2"));
            }
            if (destination.equals("Room 3")) {
                routes.add("Intersection 1 → Room 3");
                routeDistances.add(distances.get("Room 3-Intersection 1"));
            }
            if (destination.equals("Room 4")) {
                routes.add("Intersection 1 → Room 4");
                routeDistances.add(distances.get("Room 4-Intersection 1"));
            }
            if (destination.equals("Room 5")) {
                routes.add("Intersection 1 → Room 5");
                routeDistances.add(distances.get("Intersection 1-Room 5"));
            }
        }

        // Intersection 2 possible routes
        if (currentLoc.equals("Intersection 2")) {
            if (destination.equals("Room 1")) {
                routes.add("Intersection 2 → Room 1");
                routeDistances.add(dist1);
                routes.add("Intersection 2 → Room 5 → Intersection 1 → Room 4 → Room 1");
                routeDistances.add(dist3 + dist6 + dist7 + dist4);
                routes.add("Intersection 2 → Room 2 → Room 3 → Intersection 1 → Room 4 → Room 1");
                routeDistances.add(dist2 + dist5 + dist8 + dist7 + dist4);
            }
            if (destination.equals("Room 2")) {
                routes.add("Intersection 2 → Room 2");
                routeDistances.add(dist2);
                routes.add("Intersection 2 → Room 1 → Room 4 → Intersection 1 → Room 3 → Room 2");
                routeDistances.add(dist1 + dist4 + dist7 + dist8 + dist5);
                routes.add("Intersection 2 → Room 5 → Intersection 1 → Room 3 → Room 2");
                routeDistances.add(dist3 + dist6 + dist8 + dist5);
            }
            if (destination.equals("Room 3")) {
                routes.add("Intersection 2 → Room 2 → Room 3");
                routeDistances.add(dist2 + dist5);
                routes.add("Intersection 2 → Room 5 → Intersection 1 → Room 3");
                routeDistances.add(dist3 + dist6 + dist8);
                routes.add("Intersection 2 → Room 1 → Room 4 → Intersection 1 → Room 3");
                routeDistances.add(dist1 + dist4 + dist7 + dist8);
            }
            if (destination.equals("Room 4")) {
                routes.add("Intersection 2 → Room 5 → Intersection 1 → Room 4");
                routeDistances.add(dist3 + dist6 + dist7);
                routes.add("Intersection 2 → Room 2 → Room 3 → Intersection 1 → Room 4");
                routeDistances.add(dist2 + dist5 + dist8 + dist7);
                routes.add("Intersection 2 → Room 1 → Room 4");
                routeDistances.add(dist1 + dist4);
            }
            if (destination.equals("Room 5")) {
                routes.add("Intersection 2 → Room 5");
                routeDistances.add(dist3);
                routes.add("Intersection 2 → Room 2 → Room 3 → Intersection 1 → Room 5");
                routeDistances.add(dist2 + dist5 + dist8 + dist6);
                routes.add("Intersection 2 → Room 1 → Room 4 → Intersection 1 → Room 5");
                routeDistances.add(dist1 + dist4 + dist7 + dist6);
            }
        }

        int minIndex = 0;
        for (int i = 1; i < routeDistances.size(); i++) {
            if (routeDistances.get(i) < routeDistances.get(minIndex)) {
                minIndex = i;
            }
        }
        
        System.out.println("All possible routes:");
        for (int i = 0; i < routes.size(); i++) {
            System.out.println(routes.get(i) + " (Total distance: " + routeDistances.get(i) + "m)");
        }
        
        if (!routes.isEmpty()) {
            println("\nShortest Path: " + routes.get(minIndex) + " (" + routeDistances.get(minIndex) + "m)");
            println("Estimated Travel Time: " + routeDistances.get(minIndex) + " seconds");
        } else {
            println("No available path found.");
        }
    }
    static String[] mapLoc = {"Room 1", "Room 2", "Room 3", "Room 4", "Room 5", "Intersection 1", "Intersection 2"};
    static String currentLocation = getRandomLocation(); // Start at a random location

    static String getRandomLocation() {
        Random rand = new Random();
        return mapLoc[rand.nextInt(mapLoc.length)]; // Pick a random room/intersection
    }
    static void moveTo(String destination) {
        System.out.println("\nMoving from " + currentLocation + " to " + destination + "...");
    
        if (isValidMove(currentLocation, destination)) {
            currentLocation = destination; // Update the player's location
            System.out.println("You have arrived at " + currentLocation);
            System.out.println(showMap(currentLocation)); // Refresh the map with new position
        } else {
            System.out.println("Invalid move. Try again.");
        }
    }
    
    static boolean isValidMove(String currentLoc, String destination) {
        HashMap<String, List<String>> validMoves = new HashMap<>();
        validMoves.put("Room 1", Arrays.asList("Room 2", "Room 4", "Room 5"));
        validMoves.put("Room 2", Arrays.asList("Room 1", "Room 3", "Room 5"));
        validMoves.put("Room 3", Arrays.asList("Room 2", "Intersection 1"));
        validMoves.put("Room 4", Arrays.asList("Room 1", "Intersection 1"));
        validMoves.put("Room 5", Arrays.asList("Room 1", "Room 2", "Intersection 1", "Intersection 2"));
        validMoves.put("Intersection 1", Arrays.asList("Room 3", "Room 4", "Room 5"));
        validMoves.put("Intersection 2", Arrays.asList("Room 1", "Room 2", "Room 5"));

        return validMoves.containsKey(currentLoc) && validMoves.get(currentLoc).contains(destination);
    }
    static void showMap() {
        System.out.println("\nCurrent Map:");
        for (String loc : mapLoc) {
            if (loc.equals(currentLocation)) {
                System.out.println("[" + loc + "] ← You are here");
            } else {
                System.out.println(loc);
            }
        }
    }
    static void gameLoop() {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            showMap();
            System.out.println("\nEnter your destination (or type 'exit' to quit): ");
            String destination = scanner.nextLine();
    
            if (destination.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the game.");
                break;
            }
    
            moveTo(destination);
        }
    
        scanner.close();
    }
    public static void moveUser(String newLocation) {
        if (isValidMove(currentLocation, newLocation)) { // Validate the move
            currentLocation = newLocation; // Update the user's position
            System.out.println("You moved to: " + currentLocation);
            System.out.println(showMap(currentLocation)); // Display the updated map
        } else {
            System.out.println("Invalid move. Try again.");
        }
    }
    
        
    
    

    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();
        Title();
        String currentLocation = showMenu(scan);
        
        askQuestion(currentLocation, scan);
        String selectedDestination = MenuDestination(currentLocation, scan);
        showRoutesAndFindShortest(currentLocation, selectedDestination);
        while (true) {
            System.out.println("\nEnter your destination (Room 1-5 or Intersection 1-2), or 'exit' to quit:");
            String destination = scan.nextLine();
    
            if (destination.equalsIgnoreCase("exit")) {
                System.out.println("Game Over!");
                break;
            }
    
            moveUser(destination);
        }
        gameLoop(); 
       
    }
}
    


   
