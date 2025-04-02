package queue;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import javax.sound.sampled.*;
import com.sun.jna.Library;
import com.sun.jna.Native;


public class Main {
    static String directions = "UP";
    static int selectedOption = 0; // 0 = Start, 1 = How to Play, 2 = Exit
    static final int MENU_OPTIONS = 3;
    static boolean menuRunning = true;

    static native void control();

    public static void ControlUp() { directions = "UP"; }
    public static void ControlDown() { directions = "DOWN"; }
    public static void ControlLeft() { directions = "LEFT"; }
    public static void ControlRight() { directions = "RIGHT"; }
    public static void ControlExit() { System.exit(0); }
    
    
    
    static void printCenter(String text) {
        int Page_width = 120;
        int totalPadding = Page_width - text.length();
    
        // Ensure padding is not negative
        if (totalPadding < 0) {
            System.out.println(text); // Just print the text as it is
            return;
        }
    
        int leftPadding = totalPadding / 2;
        int rightPadding = totalPadding - leftPadding;
    
        System.out.println(" ".repeat(leftPadding) + text + " ".repeat(rightPadding));
    }
    
    public static void logo() {
        // ANSI escape code for pink/magenta color
        String pink = "\033[1;35m";
        String reset = "\u001B[0m";  // Reset color to default
        
        // Panda logo
        String[] panda = {
            "⠀  ⠀                 ⠀⣠⣴⣶⣶⣶⣶⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣶⣶⣶⣶⣦⣤⡀⠀⠀",
            " ⠀                  ⢠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣤⣤⣶⠶⠶⠶⠶⠶⠶⠶⠶⠶⢶⣦⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀",
            "                   ⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⡆",
            "                   ⠸⣿⣿⣿⣿⣿⣿⣿⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣿⣿⣿⣿⡇",
            "                   ⠀⢿⣿⣿⣿⣿⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣿⣿⣿⣿⠁",
            " ⠀                  ⠈⠻⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣿⡿⠁",
            "                   ⠀⠀⠀⣿⠁⠀⠀⠀⢀⣠⣤⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣄⡀⠀⠀⠀⠀⠀ ⢻⡇⠀⠀⠀",
            "                   ⠀⠀⢸⡏⠀⠀⣠⣶⣿⣿⣿⠿⢿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⣿⠿⢿⣿⣷⣦⡀⠀⠀⠈⣿⠀⠀⠀",
            "                   ⠀⠀⢸⡇⠀⢰⣿⣿⣿⣿⣇⠀⢨⣿⣿⠀⠀⢠⣦⣤⣤⠀⠀⣿⣿⣇⠀⢀⣿⣿⣿⣿⡆⠀⠀⣿⠀⠀⠀",
            " ⠀                  ⠀⠸⣷⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⠈⡏⠀⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⢰⡟⠀⠀⠀",
            "                   ⠀⠀⠀⢻⣇⠈⠻⣿⣿⣿⣿⣿⣿⡟⠀⠀⠸⣦⣼⣷⣤⡶⠀⠈⢿⣿⣿⣿⣿⣿⣿⠟⠀⢠⡿⠁⠀⠀⠀",
            "                   ⠀⠀⠀⠀⠹⣷⡀⠀⠉⠙⠛⠛⠋⠀⠀⠀⠀⠈⠛⠞⠋⠀⠀⠀⠈⠛⠻⠟⠛⠉⠁⠀⣰⡿⠁⠀⠀⠀⠀",
            "                   ⠀⠀⠀⠀⠀⠈⠻⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⡾⠋⠀⠀⠀⠀⠀⠀",
            "                   ⠀⠀⠀⠀⠀⠀⠀⠈⠙⠿⣶⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣴⡾⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀",
            "                   ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠙⠛⠿⠶⠶⠶⣤⣤⡴⠶⠶⠾⠟⠛⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "",
            "███████  ██████   ██████  ██████      ██████   █████  ███    ██ ██████   █████  ",
            "██      ██    ██ ██    ██ ██   ██     ██   ██ ██   ██ ████   ██ ██   ██ ██   ██ ",
            "█████   ██    ██ ██    ██ ██   ██     ██████  ███████ ██ ██  ██ ██   ██ ███████ ",
            "██      ██    ██ ██    ██ ██   ██     ██      ██   ██ ██  ██ ██ ██   ██ ██   ██ ",
            "██       ██████   ██████  ██████      ██      ██   ██ ██   ████ ██████  ██   ██ "
        };
        // Fade-in effect
        for (int i = 0; i <= panda.length; i++) {
            System.out.print("\033[H\033[2J"); // Clear screen
            System.out.flush();

            System.out.println(pink);
            for (int j = 0; j < i; j++) {
                System.out.println(panda[j]);
            }
            System.out.println(reset);

            try {
                Thread.sleep(10); // Adjust speed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // Play sound after fade-in
        LogoSound();
        // Pause before fade-out
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Fade-out effect
        for (int i = panda.length; i >= 0; i--) {
            System.out.print("\033[H\033[2J"); // Clear screen
            System.out.flush();

            System.out.println(pink);
            for (int j = 0; j < i; j++) {
                System.out.println(panda[j]);
            }
            System.out.println(reset);

            try {
                Thread.sleep(10); // Adjust speed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Final clear screen
        System.out.print("\033[H\033[2J");
        System.out.flush();

    }
    public static void LogoSound() {
        File file = new File("C:\\Code Practice\\FinalProject\\dsa\\src\\main\\java\\queue\\FoodPandaSound.wav");
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file)) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            // Wait for the sound to finish
            while (!clip.isRunning())
                Thread.sleep(10);
            while (clip.isRunning())
                Thread.sleep(10);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public static void showLoadingScreen(String message, int duration) {
        String[] spinner = { "|", "/", "-", "\\" }; // Rotating spinner
        int barLength = 30; // Longer progress bar for bigger size

        for (int i = 0; i <= duration; i++) {
            int progress = (i * barLength) / duration;
            String bar = "[" + "=".repeat(progress) + " ".repeat(barLength - progress) + "]";
            String spinnerChar = spinner[i % spinner.length];

            // Clear the console (optional, makes animation smoother)
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // Large ASCII Box with Animation
            System.out.println("╔══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                                      ║");
            System.out.println("║                                                                      ║");
            System.out.println("║                                                                      ║");
            System.out.println("║                                                                      ║");
            System.out.println("║                                                                      ║");
            System.out.println("║                                                                      ║");
            System.out.println("║                                                                      ║");
            System.out.println("║                       LOADING PLEASE WAIT...                         ║");
            System.out.println("║                                                                      ║");
            System.out.println("║                   ⠀" + spinnerChar + " " + bar + " " + (i * 100 / duration) + "%⠀           ║");
            System.out.println("║                                                                      ║");
            System.out.println("║                                                                      ║");
            System.out.println("║                                                                      ║");
            System.out.println("║                                                                      ║");
            System.out.println("║                                                                      ║");
            System.out.println("║                                                                      ║");
            System.out.println("║                                                                      ║");
            System.out.println("║                                                                      ║");
            System.out.println("║                                                                      ║");
            System.out.println("║                                                                      ║");
            System.out.println("╚══════════════════════════════════════════════════════════════════════╝");

            try {
                Thread.sleep(100); // Animation speed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("\nLoading interrupted!");
                return;
            }
        } 
    }
    
    public static void showBlinkingCompletion() {
        String message = "      ✅ LOADING COMPLETE";
        String box = 
            "                                                                      \n "+
            "                                                                       \n"+
            "                                                                       \n"+
            "                                                                       \n"+
            "                                                                       \n"+
            "                                                                       \n"+
            "                                                                       \n"+
            "                                                                       \n"+
            "                                                                       \n"+
            "╔══════════════════════════════════════════════════════════════════════╗\n" +
            "║                                                                      ║\n" +
            "╚══════════════════════════════════════════════════════════════════════╝";
    
        for (int i = 0; i < 6; i++) { // Blink 6 times
            System.out.print("\033[H\033[2J"); // Clear screen
            System.out.flush();
            System.out.println(box);
            if (i % 2 == 0) { // Show text every other iteration
                System.out.print("\033[2A"); // Move up
                System.out.println("║                    " + message + "                         ║");
            }
            try {
                Thread.sleep(500); // Adjust blinking speed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    public interface User32 extends Library {
        User32 INSTANCE = Native.load("user32", User32.class);

        int GetAsyncKeyState(int vKey);
    }
    public static void menuSelection(String[] args) {
        String[][] mainMenuOptions = {
            {  "┏┓                   ", 
               "┗┓╋┏┓┏┓╋             ", 
               "┗┛┗┗┻┛ ┗             " }, 

            {  "┓┏              ┓    ", 
               "┣┫┏┓┓┏┏  ╋┏┓  ┏┓┃┏┓┓┏", 
               "┛┗┗┛┗┻┛  ┗┗┛  ┣┛┗┗┻┗┫" }, 

            {  "┏┓┓                  ", 
               "┣┫┣┓┏┓┓┏╋            ", 
               "┛┗┗┛┗┛┗┻┗            " }, 

            {  "┏┓  •                ", 
               "┣ ┓┏┓╋               ", 
               "┗┛┛┗┗┗               " }
        };
        

        int selectedIndex = 0;

        String[] foodPandaLogo = {
            "███████  ██████   ██████  ██████          ",
            "██      ██    ██ ██    ██ ██   ██         ",
            "█████   ██    ██ ██    ██ ██   ██         ",
            "██      ██    ██ ██    ██ ██   ██         ",
            "██       ██████   ██████  ██████          ",
            "                                          ",
            "                                          ",
            "██████   █████  ███    ██ ██████   █████  ",
            "██   ██ ██   ██ ████   ██ ██   ██ ██   ██ ",
            "██████  ███████ ██ ██  ██ ██   ██ ███████ ",
            "██      ██   ██ ██  ██ ██ ██   ██ ██   ██ ",
            "██      ██   ██ ██   ████ ██████  ██   ██ ",
            "                                          ",
            "                                          "
        };

        while (true) {
            clearConsole();
            displayMenuWithLogo(mainMenuOptions, selectedIndex, foodPandaLogo);

            if (isUpKeyPressed()) {
                selectedIndex = (selectedIndex - 1 + mainMenuOptions.length) % mainMenuOptions.length;
                debounce();
            } else if (isDownKeyPressed()) {
                selectedIndex = (selectedIndex + 1) % mainMenuOptions.length;
                debounce();
            } else if (isEnterKeyPressed()) {
                debounce();
                handleMenuSelection(mainMenuOptions[selectedIndex], selectedIndex, args);
            }

            sleep(50);
        }
    }

    // Updated method to correctly handle menu selection
    private static void handleMenuSelection(String[] mainMenuOptions, int selectedIndex, String[] args) {
        switch (selectedIndex) {
            case 0:
                StartGame.main(args);
                System.exit(0); // Ensure the program exits after the game ends
                break;
            case 1:
                showContent("How to Play:\n" +
                        "1. Use arrow keys to navigate.\n" +
                        "2. Press Enter to select an option.",
                        "Press any key to return to the main menu.");
                break;
            case 2:
                showContent("About:\n" +
                        "This is a JNA-based menu demo.\n" +
                        "Author: Your Name",
                        "Press any key to return to the main menu.");
                break;
            case 3:
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid selection.");
        }
    }


    // Show content and wait for user input
    private static void showContent(String content, String returnMessage) {
        clearConsole();
        System.out.println(content);
        System.out.println("\n" + returnMessage);
        waitForAnyKey();
    }
    // Key checks
    private static boolean isUpKeyPressed() {
        return (User32.INSTANCE.GetAsyncKeyState(0x26) & 0x8000) != 0;
    }

    private static boolean isDownKeyPressed() {
        return (User32.INSTANCE.GetAsyncKeyState(0x28) & 0x8000) != 0;
    }

    private static boolean isEnterKeyPressed() {
        return (User32.INSTANCE.GetAsyncKeyState(0x0D) & 0x8000) != 0;
    }

    // Utilities
    private static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   private static void displayMenuWithLogo(String[][] menuOptions, int selectedIndex, String[] logo) {
        int maxMenuLines = menuOptions.length * 3;
        int maxLogoLines = logo.length;
        int maxLength = Math.max(maxMenuLines, maxLogoLines);

        System.out.println("┌───────────────────────────────────────────────────────────────────────────┐");
        for (int i = 0; i < maxLength; i++) {
            StringBuilder line = new StringBuilder("│ ");
            
            // Handle menu part
            if (i < maxMenuLines) {
                int optionIndex = i / 3;
                int lineIndex = i % 3;
                String menuText = menuOptions[optionIndex][lineIndex];
                
                if (optionIndex == selectedIndex) {
                    line.append("\033[1;35m").append(menuText).append("\033[0m");
                } else {
                    line.append(menuText);
                }
                
                // Calculate padding based on visible text length, not ANSI code length
                int visibleLength = menuText.length();
                int padding = 25 - visibleLength;
                line.append(" ".repeat(padding));
            } else {
                line.append(" ".repeat(25));
            }
            
            line.append("│ ");
            
            // Handle logo part
            if (i < maxLogoLines) {
                line.append("\033[1;35m").append(logo[i]).append("\u001B[0m");
                
                // Calculate padding based on visible text length
                int visibleLength = logo[i].length();
                int padding = 46 - visibleLength;
                if (padding > 0) {
                    line.append(" ".repeat(padding));
                }
            } else {
                line.append(" ".repeat(60));
            }
            
            line.append(" │");
            System.out.println(line);
        }
        System.out.println("└───────────────────────────────────────────────────────────────────────────┘");
    }

    private static void debounce() {
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Wait for any key press (fixes immediate dismissal)
    private static void waitForAnyKey() {
        try {
            // Clear input buffer first
            clearInputBuffer();
            // Wait for any single key (not requiring Enter)
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Clear any leftover input in the buffer
    private static void clearInputBuffer() {
        try {
            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        showLoadingScreen("Loading", 10); // Customize the message and duration
        clearConsole();
        showBlinkingCompletion();
        clearConsole();
        logo();
        clearConsole();
        menuSelection(args);
         
         
    }
}