package trial;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class Main {
    public interface User32 extends Library {
        User32 INSTANCE = Native.load("user32", User32.class);
        int GetAsyncKeyState(int vKey);
    }

    public static void main(String[] args) {
        menuSelection();
    }

    public static void menuSelection() {
        String[][] menuOptions = {
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

        String[] arrowIndicator = {
            "  ███   ",
            "  █ █   ",
            "  ███   "
        };

        int selectedIndex = 0;

        while (true) {
            // Clear screen
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Print the menu
            for (int i = 0; i < menuOptions.length; i++) {
                if (i == selectedIndex) {
                    System.out.println("\033[1;32m"); // Green highlight
                    for (int j = 0; j < 3; j++) {
                        System.out.println(arrowIndicator[j] + " " + menuOptions[i][j]);
                    }
                    System.out.println("\033[0m"); // Reset color
                } else {
                    for (int j = 0; j < 3; j++) {
                        System.out.println("        " + menuOptions[i][j]);
                    }
                }
                System.out.println();
            }

            // Handle key input
            if ((User32.INSTANCE.GetAsyncKeyState(0x26) & 0x8000) != 0) { // Up Arrow
                selectedIndex = (selectedIndex - 1 + menuOptions.length) % menuOptions.length;
                try { Thread.sleep(150); } catch (InterruptedException e) { e.printStackTrace(); }
            } 
            else if ((User32.INSTANCE.GetAsyncKeyState(0x28) & 0x8000) != 0) { // Down Arrow
                selectedIndex = (selectedIndex + 1) % menuOptions.length;
                try { Thread.sleep(150); } catch (InterruptedException e) { e.printStackTrace(); }
            } 
            else if ((User32.INSTANCE.GetAsyncKeyState(0x0D) & 0x8000) != 0) { // Enter Key
                executeOption(selectedIndex);
            }

            try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    // Executes the selected menu option
    public static void executeOption(int selectedIndex) {
        clearScreen();
        switch (selectedIndex) {
            case 0:
                System.out.println("Starting the game...");
                break;
            case 1:
                System.out.println("How to Play:");
                System.out.println("Press any key to return to the menu.");
                waitForKeyPress();
                break;
            case 2:
                System.out.println("About:");
                System.out.println("This is a sample application using JNA.");
                System.out.println("Press any key to return to the menu.");
                waitForKeyPress();
                break;
            case 3:
                System.out.println("Exiting...");
                System.exit(0);
                break;
        }
    }

    // Clears the console screen
    private static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Waits for any key press before returning to the menu
    private static void waitForKeyPress() {
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

