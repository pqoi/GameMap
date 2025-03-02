import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.Scanner;


public class Map {
    static String[] locations = new String[7];
    static int[] scores = new int[7];
    static boolean[] visitedRooms = new boolean[7]; // Track rooms with answered exams

    private static final int PAGE_WIDTH = 175;
    private static final String HORIZONTAL_LINE = "+" + "=".repeat(PAGE_WIDTH - 2) + "+";
    private static final String EMPTY_LINE = "|" + " ".repeat(PAGE_WIDTH - 2) + "|";
    private static final int PAGE_WIDTH2 = 120;
    private static final String HORIZONTAL_LINE1 = "═" + "═".repeat(PAGE_WIDTH2 - 1);
    private static final String HORIZONTAL_LINE2 = "+" + "=".repeat(PAGE_WIDTH2 - 1) + "+";
    private static final String EMPTY_LINE2 = "|" + " ".repeat(PAGE_WIDTH2 - 2) + "|";

    private static String[] selectedCourses = new String[2]; // Store user choices

    private static LocalTime gameTime = LocalTime.of(8, 0); // Start at 8:00 AM
    private static final LocalTime END_TIME = LocalTime.of(9, 0); // End at 9:00 AM
    private static boolean firstIntersectionVisit = true;
    private static boolean firstExamTaken = false;
    
    static void initializeTimeTracking() {
        gameTime = LocalTime.of(8, 0);
        firstIntersectionVisit = true;
        firstExamTaken = false;
    }
    
    static String getCurrentTime() {
        return gameTime.toString();
    }
    
    static void addTime(int minutes, int seconds) {
        gameTime = gameTime.plus(minutes, ChronoUnit.MINUTES).plus(seconds, ChronoUnit.SECONDS);
        checkTimeLimit();
    }
    
    static void checkTimeLimit() {
        if (!gameTime.isBefore(END_TIME)) {
            System.out.println("Time is up! The game is over.");
            System.exit(0);
        }
    }

    static int getRoomIndex(String room) {
        switch (room) {
            case "Room 1": return 0;
            case "Room 2": return 1;
            case "Room 3": return 2;
            case "Room 4": return 3;
            case "Room 5": return 4;
            case "Intersection 1": return 5;
            case "Intersection 2": return 6;
            default: return -1;
        }
    }


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
                "        |  |                      |_______    ________|                       |  |   \n" +
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
    static String UserName(Scanner scan){
        println("=====================================");
        println("|          WELCOME TO THE GAME      |");
        println("=====================================");
        println("|  Enter Username:                  |");
        println("=====================================");
        print("> "); // Input prompt
        scan.nextLine(); // Consume the newline character
        String username = scan.nextLine();

        println("=====================================");
        println("|  " + username + ", welcome to the game!  |");
        println("=====================================");   
        return username;
    }
    static void displayUsername(String username){
        println("=====================================");
        println("|  Username: " + username + "          |");
        println("=====================================");
    }
   
        static String showMenu(Scanner scan) {
            while (true) {
                println("╔════════════════════╗");
                println("║       MENU         ║");
                println("╠════════════════════╣");
                println("║  1. Start          ║");
                println("║  2. How to Play    ║");
                println("║  3. About          ║");
                println("╚════════════════════╝");
                println("Choose key to proceed:");    int choice = scan.nextInt();
    
                switch (choice) {
                    case 1:
                        
                        UserName(scan);
                        String start = "Starting the game...";
                        
                        println("╔════════════════════════╗");
                        println("║                        ║");
                        println("║ "+start+"   ║");
                        println("║                        ║");
                        println("╚════════════════════════╝");
                        

                        introduction(); // Call introduction method
                        takeCourseInput(scan); // Call Course method
                       
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
            println("==============================================================");
            println("|  ENGINEERING ASPIRATION: THE JOURNEY BEGINS                |");
            println("==============================================================");
            println("|  Coming from a poor family, I have always dreamed of       |");
            println("|  becoming an engineer to provide a better life for my      |");
            println("|  loved ones.                                               |");
            println("|                                                            |");
            println("|  Today marks a crucial step toward that dream—the          |");
            println("|  entrance exam for a prestigious public university.        |");
            println("|                                                            |");
            println("|  To achieve my goal, I must pass a series of challenging   |");
            println("|  exams that will test my knowledge and skills.             |");
            println("|                                                            |");
            println("|  The road ahead won’t be easy, but I am determined to      |");
            println("|  succeed.                                                  |");
            println("==============================================================");
        }
        public static void takeCourseInput(Scanner scan) {
            System.out.println("==============================================");
            System.out.println("|          COURSE SELECTION MENU            |");
            System.out.println("==============================================");
            System.out.println("|  Your first choice is: ENGINEERING        |");
            System.out.println("|--------------------------------------------|");
            System.out.println("|  Please enter your second and third choice |");
            System.out.println("|  of courses from the options below:       |");
            System.out.println("|--------------------------------------------|");
            System.out.println("|  2. Education                             |");
            System.out.println("|  3. Entrepreneurship                      |");
            System.out.println("|  4. Tourism Management                    |");
            System.out.println("|  5. Sociology                             |");
            System.out.println("|  6. Agriculture                           |");
            System.out.println("|  7. Fisheries                             |");
            System.out.println("==============================================");
    
            selectedCourses[0] = getCourseSelection(scan, "> Enter your second choice: ");
            selectedCourses[1] = getCourseSelection(scan, "> Enter your third choice: ");
    
            System.out.println("==============================================");
            System.out.println("|  You selected:                             |");
            System.out.println("|  1st Choice: Engineering                   |");
            System.out.println("|  2nd Choice: " + selectedCourses[0] + "              |");
            System.out.println("|  3rd Choice: " + selectedCourses[1] + "              |");
            System.out.println("==============================================");
        }
    
        private static String getCourseSelection(Scanner scan, String prompt) {
            System.out.print(prompt);
            int choice = scan.nextInt();
            scan.nextLine(); // Consume newline
    
            switch (choice) {
                case 2: return "Education";
                case 3: return "Entrepreneurship";
                case 4: return "Tourism Management";
                case 5: return "Sociology";
                case 6: return "Agriculture";
                case 7: return "Fisheries";
                default: return "Invalid Choice";
            }
        }
    
        public static void displaySelectedCourses() {
            System.out.println("==============================================");
            System.out.println("|          Your selected courses:            |");
            System.out.println("==============================================");
            System.out.println("|  2nd Choice: " + selectedCourses[0] + "            |");
            System.out.println("|  3rd Choice: " + selectedCourses[1] + "            |");
            System.out.println("==============================================");      
        }
        static void printCenter(String text) {
            int Page_width = 120;
            int padding = (Page_width - text.length() - 2) / 2;
            String paddedText = "║" + " ".repeat(padding) + text;
            paddedText += " ".repeat(Page_width - paddedText.length() - 1) + "║";
            System.out.println(paddedText);
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
                    "Solve: 5 x (3 + 2)\nA) 15\nB) 20\nC) 25\nD) 30"
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
                case "Room 1":
                    roomIndex = 0;
                    System.out.println(HORIZONTAL_LINE1);
                    printCenter("You are in Room 1 - English");
                    System.out.println(HORIZONTAL_LINE1);
                    printCenter("The words you know reflect the world you see. Every correct answer sharpens your tongue and mind.");
                    System.out.println(HORIZONTAL_LINE1 + ("\n"));
                    break;
                case "Room 2":
                    roomIndex = 1;
                    System.out.println(HORIZONTAL_LINE1);
                    printCenter("You are in Room 2 - Math");
                    System.out.println(HORIZONTAL_LINE1);
                    printCenter("Numbers dance in your head. Equations and formulas guide your logic and structure.");
                    System.out.println(HORIZONTAL_LINE1 + ("\n"));
                    break;
                case "Room 3":
                    roomIndex = 2;
                    System.out.println(HORIZONTAL_LINE1);
                    printCenter("You are in Room 3 - Filipino");
                    System.out.println(HORIZONTAL_LINE1);
                    printCenter("Your roots whisper their strength. Language is more than words — it’s identity and culture.");
                    System.out.println(HORIZONTAL_LINE1 + ("\n"));
                    break;
                case "Room 4":
                    roomIndex = 3;
                    System.out.println(HORIZONTAL_LINE1);
                    printCenter("You are in Room 4 - Logical Thinking");
                    System.out.println(HORIZONTAL_LINE1);
                    printCenter("Puzzles unfold before you. Every solved riddle adds another brick to the foundation of your reasoning.");
                    System.out.println(HORIZONTAL_LINE1 + ("\n"));
                    break;
                case "Room 5":
                    roomIndex = 4;
                    System.out.println(HORIZONTAL_LINE1);
                    printCenter("You are in Room 5 - Ethics % Decision-Making");
                    System.out.println(HORIZONTAL_LINE1);
                    printCenter("Integrity shapes your path. With every just decision, you carve a road of honor.");
                    System.out.println(HORIZONTAL_LINE1 + ("\n"));
                    break;
                case "Intersection 1":
                    System.out.println(HORIZONTAL_LINE1);
                    printCenter("You are in an intersection. No question here.");
                    System.out.println(HORIZONTAL_LINE1);
                    return;
                case "Intersection 2":
                    System.out.println(HORIZONTAL_LINE1);
                    printCenter("You are in an intersection. No question here.");
                    System.out.println(HORIZONTAL_LINE1);
                    return;
                default:
                    System.out.println("Invalid location.");
                    return;
            }
        
            if (scores[roomIndex] > 0) {
                println("You have already completed the exam in " + currentLoc + ".");
                return;
            } 
            System.out.println();
            System.out.println();
            System.out.println(HORIZONTAL_LINE2);
            printCenter("███████ ███    ██ ████████ ██████   █████  ███    ██  ██████ ███████     ███████ ██   ██  █████  ███    ███");
            printCenter("██      ████   ██    ██    ██   ██ ██   ██ ████   ██ ██      ██          ██       ██ ██  ██   ██ ████  ████");
            printCenter("█████   ██ ██  ██    ██    ██████  ███████ ██ ██  ██ ██      █████       █████     ███   ███████ ██ ████ ██");
            printCenter("██      ██  ██ ██    ██    ██   ██ ██   ██ ██  ██ ██ ██      ██          ██       ██ ██  ██   ██ ██  ██  ██");
            printCenter("███████ ██   ████    ██    ██   ██ ██   ██ ██   ████  ██████ ███████     ███████ ██   ██ ██   ██ ██      ██");
            System.out.println(HORIZONTAL_LINE2);
            printCenter("Read the question Carefully. And choose the correct answer." );
    
    
        
          int score = 0;
            System.out.println(HORIZONTAL_LINE2);
    
            for (int i = 0; i < 5; i++) {
            String roomquestions = questions[roomIndex][i];
            String[] parts = roomquestions.split("\n");
            // For the question line
            // Print the question line centered
            String question = String.format("%d: %s", (i + 1), parts[0]);
            int questionPadding = (PAGE_WIDTH2 - question.length() - 0) / 2;
            System.out.printf("|%" + questionPadding + "s%s%" + (PAGE_WIDTH2  - question.length() - questionPadding - 2) + "s|\n", "", question, "");
    
            // Print the choices centered
            for (int j = 1; j < parts.length; j++) {
            String choice = parts[j]; // Example: "A) Square"
            int choicePadding = (PAGE_WIDTH2 - choice.length() - 0) / 2;
            System.out.printf("|%" + choicePadding + "s%s%" + (PAGE_WIDTH2 - choice.length() - choicePadding - 2) + "s|\n", "", choice, "");
            }  
            String prompt = "Your answer: ";
            int padding = (PAGE_WIDTH2 - prompt.length()) / 2; // Calculate center padding
            System.out.print("|" + " ".repeat(padding) + prompt);
            char answer = scan.next().toUpperCase().charAt(0);
    
            System.out.println(EMPTY_LINE2);
                if (answer == answers[roomIndex][i]) {
                    score++;
                }
            }
        
            scores[roomIndex] = score;

            printCenter("╔══════════════════════════════════════════════════════════════════════════╗");
            printCenter("         You scored " + score + " out of 5 in " + currentLoc + ".");
            printCenter("╚══════════════════════════════════════════════════════════════════════════╝");      
            if (score == 5) {
                printCenter("╔══════════════════════════════════════════════════════════════════════════╗");
                printCenter("Incredible! You feel a surge of confidence, as if the path ahead is clear");
                printCenter("and bright.");
                printCenter("╚══════════════════════════════════════════════════════════════════════════╝");
            } else if (score >= 3) {
                printCenter("╔══════════════════════════════════════════════════════════════════════════╗");
                printCenter("Not bad! You push forward, knowing there's still more to learn.");
                printCenter("╚══════════════════════════════════════════════════════════════════════════╝");
            } else {
                printCenter("╔══════════════════════════════════════════════════════════════════════════╗");
                printCenter("You struggle, but remind yourself that every great engineer starts");
                printCenter("somewhere.");
                printCenter("╚══════════════════════════════════════════════════════════════════════════╝");
            }      ScoreMenu();
        
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
                println("Congratulations! You passed the entrance exams.");
                println("Proceeding to the entrance exam for the engineering course...");
                //EngineerExam();
                } else {
                println("Unfortunately, you did not pass the exams. Better luck next time.");
                }
                System.exit(0);

                
            }
            if (!currentLoc.startsWith("Intersection")) {
                addTime(6,0); // Deduct 6 minutes for exams
                firstExamTaken = true;
            }
        }
   
    static void ScoreMenu() {
        printCenter("╔════════════════════════════════════════════════╗");
        printCenter("║                 Exam Score Board               ║");
        printCenter("╠════════════════════════════════════════════════╣");
        printCenter("║ Room 1: " + scores[0] + "/5                                  ║");
        printCenter("║ Room 2: " + scores[1] + "/5                                  ║");
        printCenter("║ Room 3: " + scores[2] + "/5                                  ║");
        printCenter("║ Room 4: " + scores[3] + "/5                                  ║");
        printCenter("║ Room 5: " + scores[4] + "/5                                  ║");
        printCenter("╚════════════════════════════════════════════════╝");
    }

    static String MenuDestination(String currentLocation, Scanner scan, int[] visitedRooms) {
        println(HORIZONTAL_LINE2);
        printCenter("Currren time"+getCurrentTime());
        println(HORIZONTAL_LINE2);
        printCenter("You are in " + currentLocation);
        println(HORIZONTAL_LINE2);
        printCenter("Where would you like to go?");
        println(HORIZONTAL_LINE2);

        String[] rooms = {"Room 1", "Room 2", "Room 3", "Room 4", "Room 5"};      
        int availableRoomCount = 0;
        for (int i = 0; i < rooms.length; i++) {
            if (!rooms[i].equals(currentLocation) && scores[i] == 0 && visitedRooms[i] == 0) {
                availableRoomCount++;
            }
        }

        if (availableRoomCount == 0) {
            System.out.println("All exams have been completed.");
            int totalScore = 0;
            for (int i = 0; i < 5; i++) {
                totalScore += scores[i];
            }
            System.out.println("Your total score is: " + totalScore + " out of " + (5 * 5));
            if (totalScore >= (5 * 3.75)) {
                System.out.println("Congratulations! You passed the entrance exams.");
                System.out.println("You will now proceed to the entrance exam for the engineering course.");
                //EngineerExam();
            } else {
                System.out.println("Unfortunately, you did not pass the entrance exams. Better luck next time.");
            }
            System.exit(0);
        }

        for (int i = 0; i < rooms.length; i++) {
            if (!rooms[i].equals(currentLocation) && scores[i] == 0 && visitedRooms[i] == 0) {
                System.out.println((i + 1) + ". " + rooms[i]);
            }
        }

        System.out.println("Enter the number of the room you want to go to: ");
        int choice = scan.nextInt();
        while (choice < 1 || choice > rooms.length || rooms[choice - 1].equals(currentLocation) || scores[choice - 1] > 0 || visitedRooms[choice - 1] > 0) {
            System.out.println("Invalid choice, please try again.");
            choice = scan.nextInt();
        }
        visitedRooms[choice - 1] = 1; // Mark the room as visited
        return rooms[choice - 1];
    }
    static void  showRoutesAndFindShortest(String currentLoc, String destination) {
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
            int travelTimeSeconds = routeDistances[minIndex] * 2; // 1 meter takes 2 seconds to travel
            int travelTimeMinutes = travelTimeSeconds / 60;
            int remainingSeconds = travelTimeSeconds % 60;
            System.out.println("Estimated Travel Time: " + travelTimeMinutes + " minutes and " + remainingSeconds + " seconds");
        } else {
            System.out.println("No available path found.");
        }
    }
    
        static void showMovement(String currentLocation, String destination, Scanner scan) {
            while (!currentLocation.equals(destination)) {
                System.out.println(showMap(currentLocation));
                println(HORIZONTAL_LINE2);
                printCenter("╔══════════════════════════════════════════════════════════════════════════╗");
                printCenter("║ Current Time: " + getCurrentTime() + "                                    ║");
                printCenter("╚══════════════════════════════════════════════════════════════════════════╝");
                printCenter("╔══════════════════════════════════════════════════════════════════════════╗");
                printCenter("║ Choose Key to move to the next location:                                 ║");
                printCenter("╠══════════════════════════════════════════════════════════════════════════╣");
                printCenter("║ 1. Move right                                                            ║");
                printCenter("║ 2. Move left                                                             ║");
                printCenter("║ 3. Move forward                                                          ║");
                printCenter("║ 4. Move backward                                                         ║");
                printCenter("║ Enter your choice:                                                       ║");
                printCenter("╚══════════════════════════════════════════════════════════════════════════╝");
                println(HORIZONTAL_LINE2);
                int choice = scan.nextInt();
                String newLocation = currentLocation;

                int travelTimeSeconds = 0;
                switch (currentLocation) {
                    case "Room 1":
                        if (choice == 1) {
                            newLocation = "Intersection 2";
                            travelTimeSeconds = dist1 * 2;
                        } else if (choice == 4) {
                            newLocation = "Room 4";
                            travelTimeSeconds = dist4 * 2;
                        }
                        break;
                    case "Room 2":
                        if (choice == 2) {
                            newLocation = "Intersection 2";
                            travelTimeSeconds = dist2 * 2;
                        } else if (choice == 4) {
                            newLocation = "Room 3";
                            travelTimeSeconds = dist5 * 2;
                        }
                        break;
                    case "Room 3":
                        if (choice == 3) {
                            newLocation = "Room 2";
                            travelTimeSeconds = dist5 * 2;
                        } else if (choice == 2) {
                            newLocation = "Intersection 1";
                            travelTimeSeconds = dist8 * 2;
                        }
                        break;
                    case "Room 4":
                        if (choice == 3) {
                            newLocation = "Room 1";
                            travelTimeSeconds = dist4 * 2;
                        } else if (choice == 1) {
                            newLocation = "Intersection 1";
                            travelTimeSeconds = dist7 * 2;
                        }
                        break;
                    case "Room 5":
                        if (choice == 3) {
                            newLocation = "Intersection 2";
                            travelTimeSeconds = dist3 * 2;
                        } else if (choice == 4) {
                            newLocation = "Intersection 1";
                            travelTimeSeconds = dist6 * 2;
                        }
                        break;
                    case "Intersection 1":
                        if (choice == 2) {
                            newLocation = "Room 4";
                            travelTimeSeconds = dist7 * 2;
                        } else if (choice == 1) {
                            newLocation = "Room 3";
                            travelTimeSeconds = dist8 * 2;
                        } else if (choice == 3) {
                            newLocation = "Room 5";
                            travelTimeSeconds = dist6 * 2;
                        }
                        break;
                    case "Intersection 2":
                        if (choice == 1) {
                            newLocation = "Room 2";
                            travelTimeSeconds = dist2 * 2;
                        } else if (choice == 2) {
                            newLocation = "Room 1";
                            travelTimeSeconds = dist1 * 2;
                        } else if (choice == 4) {
                            newLocation = "Room 5";
                            travelTimeSeconds = dist3 * 2;
                        }
                        break;
                    default:
                        break;
                }

                if (!newLocation.equals(currentLocation)) {
                    System.out.println("Moving to " + newLocation);
                    int travelTimeMinutes = travelTimeSeconds / 60;
                    int remainingSeconds = travelTimeSeconds % 60;
                    addTime(travelTimeMinutes, remainingSeconds); // Apply time in minutes and seconds
                    System.out.println("Current Time: " + getCurrentTime());
                    currentLocation = newLocation;
                    System.out.println(showMap(currentLocation));

                    if (!currentLocation.startsWith("Intersection") && !visitedRooms[getRoomIndex(currentLocation)]) {
                        System.out.println("You are in " + currentLocation);
                        askQuestion(currentLocation, scan);
                        visitedRooms[getRoomIndex(currentLocation)] = true;
                    }
                } else {
                    System.out.println("You can't move in that direction.");
                }
            }
            System.out.println("You have reached your destination: " + destination);

            int destIndex = getRoomIndex(destination);
            if (!visitedRooms[destIndex]) {
                askQuestion(destination, scan);
                visitedRooms[destIndex] = true;
            }
        }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();

        //Title();
        String currentLocation = showMenu(scan);
        int[] visitedRooms = new int[5]; // Initialize visitedRooms array
        

        
        askQuestion(currentLocation, scan);
        while (true) {
            String selectedDestination = MenuDestination(currentLocation, scan,visitedRooms);
            showRoutesAndFindShortest(currentLocation, selectedDestination);
            showMovement(currentLocation, selectedDestination, scan);
            currentLocation = selectedDestination;
        }
    }
    
}
