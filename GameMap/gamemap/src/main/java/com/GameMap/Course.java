package com.GameMap;

import java.util.Scanner;

public class Course {
    static String[] Course(Scanner scan){
            println("==============================================");
            println("|          COURSE SELECTION MENU            |");
            println("==============================================");
            println("|  Your first choice is: ENGINEERING        |");
            println("|--------------------------------------------|");
            println("|  Please enter your second and third choice |");
            println("|  of courses from the options below:       |");
            println("|--------------------------------------------|");
            println("|  2. Education                             |");
            println("|  3. Entrepreneurship                      |");
            println("|  4. Tourism Management                    |");
            println("|  5. Sociology                             |");
            println("|  6. Agriculture                           |");
            println("|  7. Fisheries                             |");
            println("==============================================");
            print("> Enter your second choice: ");
            int secondChoice = scan.nextInt();
            String secondCourse = "";
            switch (secondChoice) {
                case 2:
                    secondCourse = "Education";
                    break;
                case 3:
                    secondCourse = "Entrepreneurship";
                    break;
                case 4:
                    secondCourse = "Tourism Management";
                    break;
                case 5:
                    secondCourse = "Sociology";
                    break;
                case 6:
                    secondCourse = "Agriculture";
                    break;
                case 7:
                    secondCourse = "Fisheries";
                    break;
                default:
                    break;
            }
            print("> Enter your third choice: ");
            int thirdChoice = scan.nextInt();
            String thirdCourse = "";
            switch (thirdChoice) {
                case 2:
                    thirdCourse = "Education";
                    break;
                case 3:
                    thirdCourse = "Entrepreneurship";
                    break;
                case 4:
                    thirdCourse = "Tourism Management";
                    break;
                case 5:
                    thirdCourse = "Sociology";
                    break;
                case 6:
                    thirdCourse = "Agriculture";
                    break;
                case 7:
                    thirdCourse = "Fisheries";
                    break;
                default:
                    break;
            }

            println("==============================================");
            println("|  You selected:                             |");
            println("|  1st Choice: Engineering                   |");
            println("|  2nd Choice: " + secondCourse +"              |");
            println("|  3rd Choice: " + thirdCourse +"               |");
            println("==============================================");

            return new String[]{secondCourse, thirdCourse};
        }
    public static void println(String message) {
        System.out.println(message);
    }
    public static void print(String message) {
        System.out.print(message);
    }
}
