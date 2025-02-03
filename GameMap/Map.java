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

        System.out.println("Welcome to");
        System.out.println("https://patorjk.com/text-color-fader/#google_vignette");


        System.out.println(Map.showMap("Home"));
    }
}
    


   
