import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    static String directions = "UP";

    static native void control();

    public static void ControlUp() { directions = "UP"; }
    public static void ControlDown() { directions = "DOWN"; }
    public static void ControlLeft() { directions = "LEFT"; }
    public static void ControlRight() { directions = "RIGHT"; }
    public static void ControlExit() { System.exit(0); }
    
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
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
    
    public static void MenuSelection() {
        System.out.println("1. Start Game");
        System.out.println("2. How to Play");
        System.out.println("3. About");
        System.out.println("4. Exit Game");
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
            System.out.println("║                   ⠀⠀    LOADING PLEASE WAIT...                       ║");
            System.out.println("║                                                                      ║");
            System.out.println("║                   ⠀⠀⠀" + spinnerChar + " " + bar + " " + (i * 100 / duration) + "%⠀         ║");
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
    
    public static void main(String[] args) {
        showLoadingScreen("Loading", 10); // Customize the message and duration
        clearConsole();
        showBlinkingCompletion();
        clearConsole();
        logo();
        clearConsole();
        MenuSelection();
        
    }
}
