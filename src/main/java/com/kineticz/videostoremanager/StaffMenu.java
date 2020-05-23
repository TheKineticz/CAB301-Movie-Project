package com.kineticz.videostoremanager;

import com.kineticz.videostoremanager.exception.MemberCollectionException;
import com.kineticz.videostoremanager.movies.*;
import com.kineticz.videostoremanager.members.*;

import java.util.Scanner;

/**
 * Staff menu container class
 */
public class StaffMenu {

    /**
     * Run the store manager staff menu
     */
    public static void main() {
        final int NUM_CHOICES = 4;

        while (true) {
            System.out.println("\n===========Staff Menu===========");
            System.out.println("1. Add a new movie DVD");
            System.out.println("2. Remove a movie DVD");
            System.out.println("3. Register a new Member");
            System.out.println("4. Find a registered member's phone number");
            System.out.println("0. Return to main menu");
            System.out.println("================================");

            int selection = Validator.getValidSelection(NUM_CHOICES);

            switch (selection) {
                case 1:
                    addMovie();
                    break;
                case 2:
                    removeMovie();
                    break;
                case 3:
                    registerMember();
                    break;
                case 4:
                    findMemberPhone();
                    break;
                case 0:
                    return;
            }
        }
    }

    /**
     * Run the prompt for adding a new movie
     */
    private static void addMovie() {
        String title;
        String[] starring;
        String director;
        Genre genre;
        Classification classification;
        int stock;

        System.out.println("\n===========Add Movie============");
        Scanner input = new Scanner(System.in);

        //Get all the movie information
        System.out.print(" Title: ");
        title = input.nextLine();

        System.out.println(" Names of starring actors, comma separated:");
        starring = input.nextLine().split("\\s*,\\s*");

        System.out.print(" Director: ");
        director = input.nextLine();

        System.out.print(" Choices: ");
        for (Genre g : Genre.values()) {
            System.out.printf("%s, ", g.toString());
        }
        System.out.print("\n Genre: ");
        genre = Validator.getValidGenre();

        System.out.print(" Choices: ");
        for (Classification c : Classification.values()) {
            System.out.printf("%s ", c.toString());
        }
        System.out.print("\n Classification: ");
        classification = Validator.getValidClassification();


        System.out.print(" Amount in stock: ");
        stock = Validator.getPositiveInt();

        //Create the movie
        VideoStoreManager.movieCollection.addMovie(new Movie(title, starring, director, genre, classification, stock));
        System.out.println("Movie added successfully!");
        Validator.pressAnyKeyToContinue();
        System.out.println("================================");
    }

    /**
     * Run the prompt for removing a movie
     */
    private static void removeMovie() {
        System.out.println("\n==========Delete Movie==========");
        Scanner input = new Scanner(System.in);

        System.out.print(" Title: ");
        String title = input.nextLine();

        VideoStoreManager.movieCollection.deleteMovie(title);
        System.out.println("Movie deleted.");
        Validator.pressAnyKeyToContinue();
        System.out.println("================================");
    }

    /**
     * Run the prompt for registering a new member
     */
    private static void registerMember() {
        String givenName;
        String surname;
        String password;
        String address;
        String phoneNumber;

        //Get all the member information
        System.out.println("\n========Register Member=========");
        Scanner input = new Scanner(System.in);

        System.out.print(" Given name: ");
        givenName = input.nextLine();

        System.out.print(" Surname: ");
        surname = input.nextLine();

        System.out.print(" Password: ");
        password = input.nextLine();

        System.out.print(" Address: ");
        address = input.nextLine();

        System.out.print(" Phone Number: ");
        phoneNumber = input.nextLine();

        //Register the member
        try {
            VideoStoreManager.memberCollection.addMember(new Member(givenName, surname, password, address, phoneNumber));
            System.out.println("Member registered successfully!");
        } catch (MemberCollectionException e) {
            System.out.println("ERROR - Failed to register member: Maximum member count exceeded.");
        }

        Validator.pressAnyKeyToContinue();
        System.out.println("================================");
    }

    /**
     * Run the prompt for finding a member's phone number
     */
    private static void findMemberPhone() {
        System.out.println("\n====Find Member Phone Number====");
        Scanner input = new Scanner(System.in);

        //Get the member information
        System.out.print(" Given name: ");
        String givenName = input.nextLine();

        System.out.print(" Surname: ");
        String surname = input.nextLine();

        //Display the phone number, if a matching member is found
        try {
            String phoneNumber = VideoStoreManager.memberCollection.getPhoneNumber(givenName, surname);
            System.out.printf(" %s %s's phone number: %s\n", givenName, surname, phoneNumber);
        } catch (MemberCollectionException e) {
            System.out.printf(" No member matching the name \"%s %s\"\n", givenName, surname);
        }

        Validator.pressAnyKeyToContinue();
        System.out.println("================================");
    }
}
