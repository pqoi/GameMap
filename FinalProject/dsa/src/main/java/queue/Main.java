package queue;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.ptr.IntByReference;

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
        String pink = "\u001B[38;5;206m";
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
                Thread.sleep(100); // Adjust speed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Pause before fade-out
        try {
            Thread.sleep(1500);
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
                Thread.sleep(100); // Adjust speed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Final clear screen
        System.out.print("\033[H\033[2J");
        System.out.flush();
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
    public static void menuSelection() {
        String[] mainMenuOptions = {"Start", "How to Play", "About", "Exit"};
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
                handleMenuSelection(mainMenuOptions[selectedIndex]);
            }

            sleep(50);
        }
    }

    // Handle selected menu option
    private static void handleMenuSelection(String option) {
        switch (option) {
            case "Start":
                showContent("Starting the game...", "Press any key to return");
                break;
            case "How to Play":
                showContent("How to Play:\n" +
                        "1. Use arrow keys to navigate.\n" +
                        "2. Press Enter to select an option.",
                        "Press any key to return to the main menu.");
                break;
            case "About":
                showContent("About:\n" +
                        "This is a JNA-based menu demo.\n" +
                        "Author: Your Name",
                        "Press any key to return to the main menu.");
                break;
            case "Exit":
                System.out.println("Exiting...");
                System.exit(0);
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

    private static void displayMenuWithLogo(String[] options, int selectedIndex, String[] logo) {
        int maxLength = Math.max(options.length, logo.length);
        System.out.println("┌────────────────────────────────────────────────────────────────────────┐");
        for (int i = 0; i < maxLength; i++) {
            String menuLine = (i < options.length) ? (i == selectedIndex ? "-> " + options[i] : "   " + options[i]) : "   ";
            String logoLine = (i < logo.length) ? "\u001B[38;5;206m" + logo[i] + "\u001B[0m" : ""; // Pink color
            System.out.printf("│ %-25s%-60s │%n", menuLine, logoLine);
        }
        System.out.println("└────────────────────────────────────────────────────────────────────────┘");
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
        menuSelection();
         
         
    }
}