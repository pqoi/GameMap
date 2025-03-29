package trial;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;

public class Main {
    public interface User32 extends Library {
        User32 INSTANCE = Native.load("user32", User32.class);

        int GetAsyncKeyState(int vKey);
    }

    public static void main(String[] args) {
        CLibrary.INSTANCE.printf("Hello, %s!\n", "World");
        menuSelection();
    }

    public static void menuSelection() {
        String[] menuOptions = {"Start", "How to Play", "About", "Exit"};
        int selectedIndex = 0;

        while (true) {
            // Clear the console (Windows-specific)
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Display the menu
            for (int i = 0; i < menuOptions.length; i++) {
                if (i == selectedIndex) {
                    System.out.println("-> " + menuOptions[i]);
                } else {
                    System.out.println("   " + menuOptions[i]);
                }
            }

            // Handle key input
            if ((User32.INSTANCE.GetAsyncKeyState(0x26) & 0x8000) != 0) { // Up arrow key (VK_UP)
                selectedIndex = (selectedIndex - 1 + menuOptions.length) % menuOptions.length;
                try {
                    Thread.sleep(150); // Debounce
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if ((User32.INSTANCE.GetAsyncKeyState(0x28) & 0x8000) != 0) { // Down arrow key (VK_DOWN)
                selectedIndex = (selectedIndex + 1) % menuOptions.length;
                try {
                    Thread.sleep(150); // Debounce
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if ((User32.INSTANCE.GetAsyncKeyState(0x0D) & 0x8000) != 0) { // Enter key (VK_RETURN)
                try {
                    Thread.sleep(150); // Debounce
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                switch (selectedIndex) {
                    case 0:
                        System.out.println("Starting the game...");
                        break;
                    case 1:
                        System.out.println("How to Play:");
                        System.out.println("Press any key to return to the menu.");
                        try {
                            System.in.read();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        System.out.println("About:");
                        System.out.println("This is a sample application using JNA.");
                        System.out.println("Press any key to return to the menu.");
                        try {
                            System.in.read();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        return;
                }
            }

            try {
                Thread.sleep(50); // Reduce CPU usage
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}