package com.kineticz.videostoremanager;

import com.kineticz.videostoremanager.movies.Genre;
import com.kineticz.videostoremanager.movies.Classification;

import java.util.Scanner;

/**
 * Container class for general input validation
 */
public class Validator {

    /**
     * Forces the user to input a number between 1 and max, inclusive
     *
     * @param max The maximum input value
     * @return The validated input value, between 1 and max, inclusive
     */
    public static int getValidSelection(int max) {
        int selection;
        Scanner input = new Scanner(System.in);

        do {
            System.out.printf(" Please make a selection (1-%d, or 0 to exit):\n", max);
            while (!input.hasNextInt()) {
                input.nextLine();
                System.out.printf(" Please make a selection (1-%d, or 0 to exit):\n", max);
            }
            selection = input.nextInt();
        } while (selection < 0 || selection > max);

        return selection;
    }

    /**
     * Force the user to input a positive integer
     *
     * @return A validated, positive integer value
     */
    public static int getPositiveInt() {
        int num;
        Scanner input = new Scanner(System.in);

        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.print(" Please enter a positive integer: ");
        }
        num = input.nextInt();

        //Duplicated above validation so the "Enter a positive integer" prompt is not shown unless invalid input is received
        while (num < 0) {
            System.out.print(" Please enter a positive integer: ");

            while (!input.hasNextInt()) {
                input.nextLine();
                System.out.print(" Please enter a positive integer: ");
            }
            num = input.nextInt();
        }

        return num;
    }

    /**
     * Force the user to input a value genre value
     *
     * @return The validated genre object
     */
    public static Genre getValidGenre() {
        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                return Genre.getFromString(input.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.print(" Enter a valid genre: ");
            }
        }
    }

    /**
     * Force the user to input a value classification value
     *
     * @return The validated classification object
     */
    public static Classification getValidClassification() {
        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                return Classification.getFromString(input.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.print(" Enter a valid classification: ");
            }
        }
    }

    /**
     * Wait for the user to press enter before continuing
     */
    public static void pressAnyKeyToContinue() {
        System.out.println("Press Enter to continue...");
        try {
            System.in.read();
        } catch (Exception ignored){}
    }

}
