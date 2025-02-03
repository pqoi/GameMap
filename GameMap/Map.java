package GameMap;

import java.util.Random;
import java.util.Scanner;

public class Map {
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

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();

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



        System.out.println("                                                 "+text);
        System.out.println("  ___                     _   _        _                      _____             _                      ");
        System.out.println(" |_ _|_      ____ _ _ __ | |_| |_ ___ | |__   ___  __ _ _ __ | ____|_ __   __ _(_)_ __   ___  ___ _ __ ");
        System.out.println("  | |\\ \\ /\\ / / _` | '_ \\| __| __/ _ \\| '_ \\ / _ \\/ _` | '_ \\|  _| | '_ \\ / _` | | '_ \\ / _ \\/ _ \\ '__|");
        System.out.println("  | | \\ V  V / (_| | | | | |_| || (_) | |_) |  __/ (_| | | | | |___| | | | (_| | | | | |  __/  __/ |   ");
        System.out.println(" |___| \\_/\\_/ \\__,_|_| |_|\\__|\\__\\___/|_.__/ \\___|\\__,_|_| |_|_____|_| |_|\\__, |_|_| |_|\\___|\\___|_|   ");
        System.out.println("                                                                          |___/                        ");
        
        
        System.out.println(Map.showMap("Home"));
    }
}
    


   
