package com.GameMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Map {
    static String[] locations = new String[7];
    static int[] scores = new int[7];
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
    
        if (scores[roomIndex] > 0) {
            println("You have already completed the exam in " + currentLoc + ".");
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
    
        scores[roomIndex] = score;
        System.out.println("You scored " + score + " out of 5 in " + currentLoc + ".");
        ScoreMenu();
    
        boolean allExamsCompleted = true;
        for (int i = 0; i < 5; i++) { // Only check the first 5 rooms
            if (scores[i] == 0) {
                allExamsCompleted = false;
                break;
            }
        }

        if (allExamsCompleted) {
            int totalScore = 0;
            for (int i = 0; i < 5; i++) { // Only sum the scores of the first 5 rooms
            totalScore += scores[i];
            }
            println("Your total score is: " + totalScore + " out of " + (5 * 5));
            double percentage = ((double) totalScore / (5 * 5)) * 100;
            println("Your percentage score is: " + String.format("%.2f", percentage) + "%");
            if (totalScore >= (5 * 3.75)) { // 75% of 25 is 18.75
            println("Congratulations! You passed the exams.");
            } else {
            println("Unfortunately, you did not pass the exams. Better luck next time.");
            }
            System.exit(0);
        }
    }
    static void ScoreMenu() {
        println("Exam Score in Every Room:");
        println("Room 1: " + scores[0] + "/5");
        println("Room 2: " + scores[1] + "/5");
        println("Room 3: " + scores[2] + "/5");
        println("Room 4: " + scores[3] + "/5");
        println("Room 5: " + scores[4] + "/5");
    }

    static String MenuDestination(String currentLocation, Scanner scan) {
        println("You are in " + currentLocation);
        println("Where would you like to go?");
        
        String[] rooms = {"Room 1", "Room 2", "Room 3", "Room 4", "Room 5"};
        List<String> availableRooms = new ArrayList<>();
        
        for (int i = 0; i < rooms.length; i++) {
            if (!rooms[i].equals(currentLocation) && scores[i] == 0) {
                availableRooms.add(rooms[i]);
            }
        }
    
        if (availableRooms.isEmpty()) {
            println("All exams have been completed.");
            int totalScore = 0;
            for (int i = 0; i < 5; i++) { // Only sum the scores of the first 5 rooms
                totalScore += scores[i];
            }
            println("Your total score is: " + totalScore + " out of " + (5 * 5));
            if (totalScore >= (5 * 3.75)) { // 75% of 25 is 18.75
                println("Congratulations! You passed the exams.");
            } else {
                println("Unfortunately, you did not pass the exams. Better luck next time.");
            }
            System.exit(0);
        }
        
        for (int i = 0; i < availableRooms.size(); i++) {
            println((i + 1) + ". " + availableRooms.get(i));
        }
        
        println("Enter the number of the room you want to go to: ");
        int choice = scan.nextInt();
        return availableRooms.get(choice - 1);
    }
    

    static void showRoutesAndFindShortest(String currentLoc, String destination) {
        String[] routeNames = {
            "Room 1-Intersection 2", "Intersection 2-Room 2", "Intersection 2-Room 5", 
            "Room 1-Room 4", "Room 2-Room 3", "Intersection 1-Room 5", 
            "Room 4-Intersection 1", "Room 3-Intersection 1"
        };
        int[] distances = {dist1, dist2, dist3, dist4, dist5, dist6, dist7, dist8};
        
        String[] routes = new String[100];
        int[] routeDistances = new int[100];
        int routeCount = 0;
        //Room 1 
        if (currentLoc.equals("Room 1")) {
            if (destination.equals("Room 2")) {
            routes[routeCount] = "Room 1 to (" + dist1 + "m) Intersection 2 to (" + dist2 + "m) Room 2";
            routeDistances[routeCount++] = dist1 + dist2;
            routes[routeCount] = "Room 1 to (" + dist4 + "m) Room 4 to (" + dist7 + "m) Intersection 1 to (" + dist6 + "m) Room 5 to (" + dist3 + "m) Intersection 2 to (" + dist2 + "m) Room 2";
            routeDistances[routeCount++] = dist4 + dist7 + dist6 + dist3 + dist2;
            routes[routeCount] = "Room 1 to (" + dist4 + "m) Room 4 to (" + dist7 + "m) Intersection 1 to (" + dist8 + "m) Room 3 to (" + dist5 + "m) Room 2";
            routeDistances[routeCount++] = dist4 + dist7 + dist8 + dist5;
            }
            if (destination.equals("Room 3")) {
            routes[routeCount] = "Room 1 to (" + dist1 + "m) Intersection 2 to (" + dist2 + "m) Room 2 to (" + dist5 + "m) Room 3";
            routeDistances[routeCount++] = dist1 + dist2 + dist5;
            routes[routeCount] = "Room 1 to (" + dist4 + "m) Room 4 to (" + dist7 + "m) Intersection 1 to (" + dist8 + "m) Room 3";
            routeDistances[routeCount++] = dist4 + dist7 + dist8;
            routes[routeCount] = "Room 1 to (" + dist1 + "m) Intersection 2 to (" + dist3 + "m) Room 5 to (" + dist6 + "m) Intersection 1 to (" + dist8 + "m) Room 3";
            routeDistances[routeCount++] = dist1 + dist3 + dist6 + dist8;
            }
            if (destination.equals("Room 4")) {
            routes[routeCount] = "Room 1 to (" + dist4 + "m) Room 4";
            routeDistances[routeCount++] = dist4;
            routes[routeCount] = "Room 1 to (" + dist1 + "m) Intersection 2 to (" + dist3 + "m) Room 5 to (" + dist6 + "m) Intersection 1 to (" + dist7 + "m) Room 4";
            routeDistances[routeCount++] = dist1 + dist3 + dist6 + dist7;
            routes[routeCount] = "Room 1 to (" + dist1 + "m) Intersection 2 to (" + dist2 + "m) Room 2 to (" + dist5 + "m) Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist7 + "m) Room 4";
            routeDistances[routeCount++] = dist1 + dist2 + dist5 + dist8 + dist7;
            }
            if (destination.equals("Room 5")) {
            routes[routeCount] = "Room 1 to (" + dist1 + "m) Intersection 2 to (" + dist3 + "m) Room 5";
            routeDistances[routeCount++] = dist1 + dist3;
            routes[routeCount] = "Room 1 to (" + dist1 + "m) Intersection 2 to (" + dist2 + "m) Room 2 to (" + dist5 + "m) Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist6 + "m) Room 5";
            routeDistances[routeCount++] = dist1 + dist2 + dist5 + dist8 + dist6;
            routes[routeCount] = "Room 1 to (" + dist4 + "m) Room 4 to (" + dist7 + "m) Intersection 1 to (" + dist6 + "m) Room 5";
            routeDistances[routeCount++] = dist4 + dist7 + dist6;
            }
        }
        //possible route room 2
        if (currentLoc.equals("Room 2")) {
            if (destination.equals("Room 1")) {
            routes[routeCount] = "Room 2 to (" + dist2 + "m) Intersection 2 to (" + dist1 + "m) Room 1";
            routeDistances[routeCount++] = dist2 + dist1;
            routes[routeCount] = "Room 2 to (" + dist5 + "m) Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist7 + "m) Room 4 to (" + dist4 + "m) Room 1";
            routeDistances[routeCount++] = dist5 + dist8 + dist7 + dist4;
            routes[routeCount] = "Room 2 to (" + dist5 + "m) Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist6 + "m) Room 5 to (" + dist3 + "m) Intersection 2 to (" + dist1 + "m) Room 1";
            routeDistances[routeCount++] = dist5 + dist8 + dist6 + dist3 + dist1;
            }
            if (destination.equals("Room 3")) {
            routes[routeCount] = "Room 2 to (" + dist5 + "m) Room 3";
            routeDistances[routeCount++] = dist5;
            routes[routeCount] = "Room 2 to (" + dist2 + "m) Intersection 2 to (" + dist3 + "m) Room 5 to (" + dist6 + "m) Intersection 1 to (" + dist8 + "m) Room 3";
            routeDistances[routeCount++] = dist2 + dist3 + dist6 + dist8;
            routes[routeCount] = "Room 2 to (" + dist2 + "m) Intersection 2 to (" + dist1 + "m) Room 1 to (" + dist4 + "m) Room 4 to (" + dist7 + "m) Intersection 1 to (" + dist8 + "m) Room 3";
            routeDistances[routeCount++] = dist2 + dist1 + dist4 + dist7 + dist8;
            }
            if (destination.equals("Room 4")) {
            routes[routeCount] = "Room 2 to (" + dist5 + "m) Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist7 + "m) Room 4";
            routeDistances[routeCount++] = dist5 + dist8 + dist7;
            routes[routeCount] = "Room 2 to (" + dist2 + "m) Intersection 2 to (" + dist3 + "m) Room 5 to (" + dist6 + "m) Intersection 1 to (" + dist7 + "m) Room 4";
            routeDistances[routeCount++] = dist2 + dist3 + dist6 + dist7;
            routes[routeCount] = "Room 2 to (" + dist2 + "m) Intersection 2 to (" + dist1 + "m) Room 1 to (" + dist4 + "m) Room 4";
            routeDistances[routeCount++] = dist2 + dist1 + dist4;
            }
            if (destination.equals("Room 5")) {
            routes[routeCount] = "Room 2 to (" + dist2 + "m) Intersection 2 to (" + dist3 + "m) Room 5";
            routeDistances[routeCount++] = dist2 + dist3;
            routes[routeCount] = "Room 2 to (" + dist5 + "m) Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist6 + "m) Room 5";
            routeDistances[routeCount++] = dist5 + dist8 + dist6;
            routes[routeCount] = "Room 2 to (" + dist2 + "m) Intersection 2 to (" + dist1 + "m) Room 1 to (" + dist4 + "m) Room 4 to (" + dist7 + "m) Intersection 1 to (" + dist6 + "m) Room 5";
            routeDistances[routeCount++] = dist2 + dist1 + dist4 + dist7 + dist6;
            }
        }
        // Room 3 possible routes
        if (currentLoc.equals("Room 3")) {
            if (destination.equals("Room 2")) {
            routes[routeCount] = "Room 3 to (" + dist5 + "m) Room 2";
            routeDistances[routeCount++] = dist5;
            routes[routeCount] = "Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist6 + "m) Room 5 to (" + dist3 + "m) Intersection 2 to (" + dist2 + "m) Room 2";
            routeDistances[routeCount++] = dist8 + dist6 + dist3 + dist2;
            routes[routeCount] = "Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist7 + "m) Room 4 to (" + dist4 + "m) Room 1 to (" + dist1 + "m) Intersection 2 to (" + dist2 + "m) Room 2";
            routeDistances[routeCount++] = dist8 + dist7 + dist4 + dist1 + dist2;
            }
            if (destination.equals("Room 4")) {
            routes[routeCount] = "Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist7 + "m) Room 4";
            routeDistances[routeCount++] = dist8 + dist7;
            routes[routeCount] = "Room 3 to (" + dist5 + "m) Room 2 to (" + dist2 + "m) Intersection 2 to (" + dist1 + "m) Room 1 to (" + dist4 + "m) Room 4";
            routeDistances[routeCount++] = dist5 + dist2 + dist1 + dist4;
            routes[routeCount] = "Room 3 to (" + dist5 + "m) Room 2 to (" + dist2 + "m) Intersection 2 to (" + dist3 + "m) Room 5 to (" + dist6 + "m) Intersection 1 to (" + dist7 + "m) Room 4";
            routeDistances[routeCount++] = dist5 + dist2 + dist3 + dist6 + dist7;
            }
            if (destination.equals("Room 1")) {
            routes[routeCount] = "Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist7 + "m) Room 4 to (" + dist4 + "m) Room 1";
            routeDistances[routeCount++] = dist8 + dist7 + dist4;
            routes[routeCount] = "Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist6 + "m) Room 5 to (" + dist3 + "m) Intersection 2 to (" + dist1 + "m) Room 1";
            routeDistances[routeCount++] = dist8 + dist6 + dist3 + dist1;
            routes[routeCount] = "Room 3 to (" + dist5 + "m) Room 2 to (" + dist2 + "m) Intersection 2 to (" + dist1 + "m) Room 1";
            routeDistances[routeCount++] = dist5 + dist2 + dist1;
            }
            if (destination.equals("Room 5")) {
            routes[routeCount] = "Room 3 to (" + dist5 + "m) Room 2 to (" + dist2 + "m) Intersection 2 to (" + dist3 + "m) Room 5";
            routeDistances[routeCount++] = dist5 + dist2 + dist3;
            routes[routeCount] = "Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist6 + "m) Room 5";
            routeDistances[routeCount++] = dist8 + dist6;
            routes[routeCount] = "Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist7 + "m) Room 4 to (" + dist4 + "m) Room 1 to (" + dist1 + "m) Intersection 2 to (" + dist3 + "m) Room 5";
            routeDistances[routeCount++] = dist8 + dist7 + dist4 + dist1 + dist3;
            }
        }
        //Room 4 
        if (currentLoc.equals("Room 4")) {
            if (destination.equals("Room 1")) {
            routes[routeCount] = "Room 4 to (" + dist4 + "m) Room 1";
            routeDistances[routeCount++] = dist4;
            routes[routeCount] = "Room 4 to (" + dist7 + "m) Intersection 1 to (" + dist8 + "m) Room 3 to (" + dist5 + "m) Room 2 to (" + dist2 + "m) Intersection 2 to (" + dist1 + "m) Room 1";
            routeDistances[routeCount++] = dist7 + dist8 + dist5 + dist2 + dist1;
            routes[routeCount] = "Room 4 to (" + dist7 + "m) Intersection 1 to (" + dist6 + "m) Room 5 to (" + dist3 + "m) Intersection 2 to (" + dist1 + "m) Room 1";
            routeDistances[routeCount++] = dist7 + dist6 + dist3 + dist1;
            }
            if (destination.equals("Room 2")) {
            routes[routeCount] = "Room 4 to (" + dist4 + "m) Room 1 to (" + dist1 + "m) Intersection 2 to (" + dist2 + "m) Room 2";
            routeDistances[routeCount++] = dist4 + dist1 + dist2;
            routes[routeCount] = "Room 4 to (" + dist7 + "m) Intersection 1 to (" + dist8 + "m) Room 3 to (" + dist5 + "m) Room 2";
            routeDistances[routeCount++] = dist7 + dist8 + dist5;
            routes[routeCount] = "Room 4 to (" + dist7 + "m) Intersection 1 to (" + dist6 + "m) Room 5 to (" + dist3 + "m) Intersection 2 to (" + dist2 + "m) Room 2";
            routeDistances[routeCount++] = dist7 + dist6 + dist3 + dist2;
            }
            if (destination.equals("Room 3")) {
            routes[routeCount] = "Room 4 to (" + dist4 + "m) Room 1 to (" + dist1 + "m) Intersection 2 to (" + dist2 + "m) Room 2 to (" + dist5 + "m) Room 3";
            routeDistances[routeCount++] = dist4 + dist1 + dist2 + dist5;
            routes[routeCount] = "Room 4 to (" + dist7 + "m) Intersection 1 to (" + dist8 + "m) Room 3";
            routeDistances[routeCount++] = dist7 + dist8;
            routes[routeCount] = "Room 4 to (" + dist7 + "m) Intersection 1 to (" + dist6 + "m) Room 5 to (" + dist3 + "m) Intersection 2 to (" + dist2 + "m) Room 2 to (" + dist5 + "m) Room 3";
            routeDistances[routeCount++] = dist7 + dist6 + dist3 + dist2 + dist5;
            }
            if (destination.equals("Room 5")) {
            routes[routeCount] = "Room 4 to (" + dist7 + "m) Intersection 1 to (" + dist6 + "m) Room 5";
            routeDistances[routeCount++] = dist7 + dist6;
            routes[routeCount] = "Room 4 to (" + dist4 + "m) Room 1 to (" + dist1 + "m) Intersection 2 to (" + dist3 + "m) Room 5";
            routeDistances[routeCount++] = dist4 + dist1 + dist3;
            routes[routeCount] = "Room 4 to (" + dist7 + "m) Intersection 1 to (" + dist8 + "m) Room 3 to (" + dist5 + "m) Room 2 to (" + dist2 + "m) Intersection 2 to (" + dist3 + "m) Room 5";
            routeDistances[routeCount++] = dist7 + dist8 + dist5 + dist2 + dist3;
            routes[routeCount] = "Room 4 to (" + dist4 + "m) Room 1 to (" + dist1 + "m) Intersection 2 to (" + dist2 + "m) Room 2 to (" + dist5 + "m) Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist6 + "m) Room 5";
            routeDistances[routeCount++] = dist4 + dist1 + dist2 + dist5 + dist8 + dist6;
            }
        }
        //Room 5 possible routes
        if (currentLoc.equals("Room 5")) {
            if (destination.equals("Room 1")) {
            routes[routeCount] = "Room 5 to (" + dist3 + "m) Intersection 2 to (" + dist1 + "m) Room 1";
            routeDistances[routeCount++] = dist3 + dist1;
            routes[routeCount] = "Room 5 to (" + dist6 + "m) Intersection 1 to (" + dist7 + "m) Room 4 to (" + dist4 + "m) Room 1";
            routeDistances[routeCount++] = dist6 + dist7 + dist4;
            routes[routeCount] = "Room 5 to (" + dist3 + "m) Intersection 2 to (" + dist2 + "m) Room 2 to (" + dist5 + "m) Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist7 + "m) Room 4 to (" + dist4 + "m) Room 1";
            routeDistances[routeCount++] = dist3 + dist2 + dist5 + dist8 + dist7 + dist4;
            routes[routeCount] = "Room 5 to (" + dist6 + "m) Intersection 1 to (" + dist8 + "m) Room 3 to (" + dist5 + "m) Room 2 to (" + dist2 + "m) Intersection 2 to (" + dist1 + "m) Room 1";
            routeDistances[routeCount++] = dist6 + dist8 + dist5 + dist2 + dist1;
            }
            if (destination.equals("Room 2")) {
            routes[routeCount] = "Room 5 to (" + dist3 + "m) Intersection 2 to (" + dist2 + "m) Room 2";
            routeDistances[routeCount++] = dist3 + dist2;
            routes[routeCount] = "Room 5 to (" + dist6 + "m) Intersection 1 to (" + dist8 + "m) Room 3 to (" + dist5 + "m) Room 2";
            routeDistances[routeCount++] = dist6 + dist8 + dist5;
            routes[routeCount] = "Room 5 to (" + dist6 + "m) Intersection 1 to (" + dist7 + "m) Room 4 to (" + dist4 + "m) Room 1 to (" + dist1 + "m) Intersection 2 to (" + dist2 + "m) Room 2";
            routeDistances[routeCount++] = dist6 + dist7 + dist4 + dist1 + dist2;
            routes[routeCount] = "Room 5 to (" + dist3 + "m) Intersection 2 to (" + dist1 + "m) Room 1 to (" + dist4 + "m) Room 4 to (" + dist7 + "m) Intersection 1 to (" + dist8 + "m) Room 3 to (" + dist5 + "m) Room 2";
            routeDistances[routeCount++] = dist3 + dist1 + dist4 + dist7 + dist8 + dist5;
            }
            if (destination.equals("Room 3")) {
            routes[routeCount] = "Room 5 to (" + dist6 + "m) Intersection 1 to (" + dist8 + "m) Room 3";
            routeDistances[routeCount++] = dist6 + dist8;
            routes[routeCount] = "Room 5 to (" + dist3 + "m) Intersection 2 to (" + dist2 + "m) Room 2 to (" + dist5 + "m) Room 3";
            routeDistances[routeCount++] = dist3 + dist2 + dist5;
            routes[routeCount] = "Room 5 to (" + dist3 + "m) Intersection 2 to (" + dist1 + "m) Room 1 to (" + dist4 + "m) Room 4 to (" + dist7 + "m) Intersection 1 to (" + dist8 + "m) Room 3";
            routeDistances[routeCount++] = dist3 + dist1 + dist4 + dist7 + dist8;
            routes[routeCount] = "Room 5 to (" + dist6 + "m) Intersection 1 to (" + dist7 + "m) Room 4 to (" + dist4 + "m) Room 1 to (" + dist1 + "m) Intersection 2 to (" + dist2 + "m) Room 2 to (" + dist5 + "m) Room 3";
            routeDistances[routeCount++] = dist6 + dist7 + dist4 + dist1 + dist2 + dist5;
            }
            if (destination.equals("Room 4")) {
            routes[routeCount] = "Room 5 to (" + dist6 + "m) Intersection 1 to (" + dist7 + "m) Room 4";
            routeDistances[routeCount++] = dist6 + dist7;
            routes[routeCount] = "Room 5 to (" + dist3 + "m) Intersection 2 to (" + dist1 + "m) Room 1 to (" + dist4 + "m) Room 4";
            routeDistances[routeCount++] = dist3 + dist1 + dist4;
            routes[routeCount] = "Room 5 to (" + dist3 + "m) Intersection 2 to (" + dist2 + "m) Room 2 to (" + dist5 + "m) Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist7 + "m) Room 4";
            routeDistances[routeCount++] = dist3 + dist2 + dist5 + dist8 + dist7;
            routes[routeCount] = "Room 5 to (" + dist6 + "m) Intersection 1 to (" + dist8 + "m) Room 3 to (" + dist5 + "m) Room 2 to (" + dist2 + "m) Intersection 2 to (" + dist1 + "m) Room 1 to (" + dist4 + "m) Room 4";
            routeDistances[routeCount++] = dist6 + dist8 + dist5 + dist2 + dist1 + dist4;
            }
        }

        // Intersection 1 possible routes
        if (currentLoc.equals("Intersection 1")) {
            if (destination.equals("Room 1")) {
            routes[routeCount] = "Intersection 1 → Room 4 → Room 1";
            routeDistances[routeCount++] = distances[6] + distances[3];
            routes[routeCount] = "Intersection 1 → Room 5 → Intersection 2 → Room 1";
            routeDistances[routeCount++] = distances[5] + distances[2] + distances[0];
            routes[routeCount] = "Intersection 1 → Room 3 → Room 2 → Intersection 2 → Room 1";
            routeDistances[routeCount++] = distances[7] + distances[4] + distances[1] + distances[0];
            }
            if (destination.equals("Room 2")) {
            routes[routeCount] = "Intersection 1 → Room 3 → Room 2";
            routeDistances[routeCount++] = distances[7] + distances[4];
            routes[routeCount] = "Intersection 1 → Room 5 → Intersection 2 → Room 2";
            routeDistances[routeCount++] = distances[5] + distances[2] + distances[1];
            routes[routeCount] = "Intersection 1 → Room 4 → Room 1 → Intersection 2 → Room 2";
            routeDistances[routeCount++] = distances[6] + distances[3] + distances[0] + distances[1];
            }
            if (destination.equals("Room 3")) {
            routes[routeCount] = "Intersection 1 → Room 3";
            routeDistances[routeCount++] = distances[7];
            routes[routeCount] = "Intersection 1 → Room 5 → Intersection 2 → Room 2 → Room 3";
            routeDistances[routeCount++] = distances[5] + distances[2] + distances[1] + distances[4];
            routes[routeCount] = "Intersection 1 → Room 4 → Room 1 → Intersection 2 → Room 2 → Room 3";
            routeDistances[routeCount++] = distances[6] + distances[3] + distances[0] + distances[1] + distances[4];
            }
            if (destination.equals("Room 4")) {
            routes[routeCount] = "Intersection 1 → Room 4";
            routeDistances[routeCount++] = distances[6];
            routes[routeCount] = "Intersection 1 → Room 5 → Intersection 2 → Room 1 → Room 4";
            routeDistances[routeCount++] = distances[5] + distances[2] + distances[0] + distances[3];
            routes[routeCount] = "Intersection 1 → Room 3 → Room 2 → Intersection 2 → Room 1 → Room 4";
            routeDistances[routeCount++] = distances[7] + distances[4] + distances[1] + distances[0] + distances[3];
            }
            if (destination.equals("Room 5")) {
            routes[routeCount] = "Intersection 1 → Room 5";
            routeDistances[routeCount++] = distances[5];
            routes[routeCount] = "Intersection 1 → Room 4 → Room 1 → Intersection 2 → Room 5";
            routeDistances[routeCount++] = distances[6] + distances[3] + distances[0] + distances[2];
            routes[routeCount] = "Intersection 1 → Room 3 → Room 2 → Intersection 2 → Room 5";
            routeDistances[routeCount++] = distances[7] + distances[4] + distances[1] + distances[2];
            }
        }
        // Intersection 2 possible routes
        if (currentLoc.equals("Intersection 2")) {
            if (destination.equals("Room 1")) {
                routes[routeCount] = "Intersection 2 to (" + dist1 + "m) Room 1";
                routeDistances[routeCount++] = dist1;
                routes[routeCount] = "Intersection 2 to (" + dist3 + "m) Room 5 to (" + dist6 + "m) Intersection 1 to (" + dist7 + "m) Room 4 to (" + dist4 + "m) Room 1";
                routeDistances[routeCount++] = dist3 + dist6 + dist7 + dist4;
                routes[routeCount] = "Intersection 2 to (" + dist2 + "m) Room 2 to (" + dist5 + "m) Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist7 + "m) Room 4 to (" + dist4 + "m) Room 1";
                routeDistances[routeCount++] = dist2 + dist5 + dist8 + dist7 + dist4;
            }
            if (destination.equals("Room 2")) {
                routes[routeCount] = "Intersection 2 to (" + dist2 + "m) Room 2";
                routeDistances[routeCount++] = dist2;
                routes[routeCount] = "Intersection 2 to (" + dist1 + "m) Room 1 to (" + dist4 + "m) Room 4 to (" + dist7 + "m) Intersection 1 to (" + dist8 + "m) Room 3 to (" + dist5 + "m) Room 2";
                routeDistances[routeCount++] = dist1 + dist4 + dist7 + dist8 + dist5;
                routes[routeCount] = "Intersection 2 to (" + dist3 + "m) Room 5 to (" + dist6 + "m) Intersection 1 to (" + dist8 + "m) Room 3 to (" + dist5 + "m) Room 2";
                routeDistances[routeCount++] = dist3 + dist6 + dist8 + dist5;
            }
            if (destination.equals("Room 3")) {
                routes[routeCount] = "Intersection 2 to (" + dist2 + "m) Room 2 to (" + dist5 + "m) Room 3";
                routeDistances[routeCount++] = dist2 + dist5;
                routes[routeCount] = "Intersection 2 to (" + dist3 + "m) Room 5 to (" + dist6 + "m) Intersection 1 to (" + dist8 + "m) Room 3";
                routeDistances[routeCount++] = dist3 + dist6 + dist8;
                routes[routeCount] = "Intersection 2 to (" + dist1 + "m) Room 1 to (" + dist4 + "m) Room 4 to (" + dist7 + "m) Intersection 1 to (" + dist8 + "m) Room 3";
                routeDistances[routeCount++] = dist1 + dist4 + dist7 + dist8;
            }
            if (destination.equals("Room 4")) {
                routes[routeCount] = "Intersection 2 to (" + dist3 + "m) Room 5 to (" + dist6 + "m) Intersection 1 to (" + dist7 + "m) Room 4";
                routeDistances[routeCount++] = dist3 + dist6 + dist7;
                routes[routeCount] = "Intersection 2 to (" + dist2 + "m) Room 2 to (" + dist5 + "m) Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist7 + "m) Room 4";
                routeDistances[routeCount++] = dist2 + dist5 + dist8 + dist7;
                routes[routeCount] = "Intersection 2 to (" + dist1 + "m) Room 1 to (" + dist4 + "m) Room 4";
                routeDistances[routeCount++] = dist1 + dist4;
            }
            if (destination.equals("Room 5")) {
                routes[routeCount] = "Intersection 2 to (" + dist3 + "m) Room 5";
                routeDistances[routeCount++] = dist3;
                routes[routeCount] = "Intersection 2 to (" + dist2 + "m) Room 2 to (" + dist5 + "m) Room 3 to (" + dist8 + "m) Intersection 1 to (" + dist6 + "m) Room 5";
                routeDistances[routeCount++] = dist2 + dist5 + dist8 + dist6;
                routes[routeCount] = "Intersection 2 to (" + dist1 + "m) Room 1 to (" + dist4 + "m) Room 4 to (" + dist7 + "m) Intersection 1 to (" + dist6 + "m) Room 5";
                routeDistances[routeCount++] = dist1 + dist4 + dist7 + dist6;
            }
        }
    
        
        int minIndex = 0;
        for (int i = 1; i < routeCount; i++) {
            if (routeDistances[i] < routeDistances[minIndex]) {
            minIndex = i;
            }
        }
        
        System.out.println("All possible routes:");
        for (int i = 0; i < routeCount; i++) {
            System.out.println(routes[i] + " (Total distance: " + routeDistances[i] + "m)");
        }
        
        if (routeCount > 0) {
            System.out.println("\nShortest Path: " + routes[minIndex] + " (" + routeDistances[minIndex] + "m)");
            int travelTimeSeconds = routeDistances[minIndex] * 60; // Assuming 1 meter takes 1 second to travel
            int travelTimeMinutes = travelTimeSeconds / 60;
            int remainingSeconds = travelTimeSeconds % 60;
            System.out.println("Estimated Travel Time: " + travelTimeMinutes + " minutes and " + remainingSeconds + " seconds");
        } else {
            System.out.println("No available path found.");
        }
        }
        
        
            
        static void showMovement(String currentLocation, String destination, Scanner scan) {
            while (!currentLocation.equals(destination)) {
                println(showMap(currentLocation));
                println("Choose Key to move to the next location: ");
                println("1. Move right");
                println("2. Move left");
                println("3. Move forward");
                println("4. Move backward");

                int choice = scan.nextInt();
                String newLocation = currentLocation; 
                switch (currentLocation) {
                case "Room 1":
                    if (choice == 1) newLocation = "Intersection 2";
                    else if (choice == 4) newLocation = "Room 4";
                    break;
                case "Room 2":
                    if (choice == 2) newLocation = "Intersection 2";
                    else if (choice == 4) newLocation = "Room 3";
                    break;
                case "Room 3":
                    if (choice == 3) newLocation = "Room 2";
                    else if (choice == 2) newLocation = "Intersection 1";
                    break;
                case "Room 4":
                    if (choice == 3) newLocation = "Room 1";
                    else if (choice == 1) newLocation = "Intersection 1";
                    break;
                case "Room 5":
                    if (choice == 3) newLocation = "Intersection 2";
                    else if (choice == 4) newLocation = "Intersection 1";
                    break;
                case "Intersection 1":
                    if (choice == 2) newLocation = "Room 4";
                    else if (choice == 1) newLocation = "Room 3";
                    else if (choice == 3) newLocation = "Room 5";
                    break;
                case "Intersection 2":
                    if (choice == 1) newLocation = "Room 2";
                    else if (choice == 2) newLocation = "Room 1";
                    else if (choice == 4) newLocation = "Room 5";
                    break;
                default:
                    println("Invalid move.");
                    break;
                }

                if (!newLocation.equals(currentLocation)) {
                    println("Moving to " + newLocation);
                    currentLocation = newLocation;
                    if (!currentLocation.equals(destination) && !currentLocation.startsWith("Intersection")) {
                        askQuestion(currentLocation, scan);
                    }
                } else {
                    println("You can't move in that direction.");
                }
            }
            println("You have reached your destination: " + destination);
            askQuestion(destination, scan);
           
            }

            static void EngineerExam() {
                Scanner scan = new Scanner(System.in);

                String[][] questions = {
                    {"What is the primary purpose of a retaining wall?\nA. To support vertical loads\nB. To resist lateral soil pressure\nC. To provide aesthetic value\nD. To support roof structures"},
                    {"In structural engineering, what does the term 'buckling' refer to?\nA. Sudden failure of a structural member subjected to high compressive stress\nB. Gradual deformation of a material under load\nC. Fracture of a material due to tensile stress\nD. Corrosion of steel structures"},
                    {"What is the main advantage of using pre-stressed concrete?\nA. Increased tensile strength\nB. Reduced weight\nC. Improved thermal insulation\nD. Enhanced aesthetic appeal"},
                    {"Which of the following is a non-destructive test for concrete?\nA. Compression test\nB. Slump test\nC. Rebound hammer test\nD. Tensile test"},
                    {"What is the purpose of a geotextile in civil engineering?\nA. To reinforce soil\nB. To provide drainage\nC. To separate different soil layers\nD. All of the above"},
                    {"In fluid mechanics, what does Bernoulli's equation describe?\nA. The relationship between pressure, velocity, and elevation in a moving fluid\nB. The rate of flow of a fluid through a pipe\nC. The viscosity of a fluid\nD. The density of a fluid"},
                    {"What is the main function of a culvert?\nA. To provide a passage for vehicles\nB. To allow water to flow under a road or railway\nC. To support a bridge\nD. To provide ventilation"},
                    {"In surveying, what is the purpose of a theodolite?\nA. To measure distances\nB. To measure angles\nC. To measure elevations\nD. To measure areas"},
                    {"What is the primary cause of soil liquefaction?\nA. Heavy rainfall\nB. Earthquake shaking\nC. High wind speeds\nD. Volcanic activity"},
                    {"In transportation engineering, what is the main purpose of a roundabout?\nA. To reduce traffic speed\nB. To eliminate the need for traffic signals\nC. To improve traffic flow and reduce accidents\nD. To provide parking space"}
                };

                char[] answers = {'B', 'A', 'A', 'C', 'D', 'A', 'B', 'B', 'B', 'C'};

                int score = takeExam(questions, answers, questions.length, scan);
                boolean passed = checkIfPassed(score, questions.length, 0.75);

                if (passed) {
                    System.out.println("Congratulations! You passed the exam.");
                } else {
                    System.out.println("You failed.");
                }

                scan.close();
            }

            static int takeExam(String[][] questions, char[] answers, int totalQuestions, Scanner scan) {
                int score = 0;
                char[] userAnswers = new char[totalQuestions];

                for (int i = 0; i < totalQuestions; i++) {
                    System.out.print((i + 1) + ". ");
                    System.out.println(questions[i][0]);
                    System.out.print("Your answer: ");
                    userAnswers[i] = Character.toUpperCase(scan.next().charAt(0));
                }

                for (int i = 0; i < totalQuestions; i++) {
                    if (userAnswers[i] == answers[i]) {
                        score++;
                    }
                }

                displayResults(score, totalQuestions);
                return score;
            }

            static boolean checkIfPassed(int score, int totalQuestions, double passing_score) {
                return ((double) score / totalQuestions) >= passing_score;
            }

            static void displayResults(int score, int totalQuestions) {
                double percentage = ((double) score / totalQuestions) * 100;
                System.out.println("\nYour Score: " + score + "/" + totalQuestions);
                System.out.println("Percentage: " + String.format("%.1f", percentage) + "%");
                if (percentage >= 75) {
                    System.out.println("Congratulations! You passed the exam.");
                } else {
                    System.out.println("You failed.");
                }
            }

           
    
    

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();

        Title();
        String currentLocation = showMenu(scan);
        
        askQuestion(currentLocation, scan);
        while (true) {
            String selectedDestination = MenuDestination(currentLocation, scan);
            showRoutesAndFindShortest(currentLocation, selectedDestination);
            showMovement(currentLocation, selectedDestination, scan);
            currentLocation = selectedDestination;
        }

    }
        

        /*String filePath = "C:\\Code Practice\\GameMap\\gamemap\\src\\main\\java\\com\\GameMap\\School_boy.ansi.txt";
      
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            
            // Example: Wrapping content with ANSI color codes (green text)
            String greenText = "\u001B[32m" + content + "\u001B[0m";

            System.out.println(greenText);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }*/

      
}




