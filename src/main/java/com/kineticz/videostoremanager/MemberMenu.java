package com.kineticz.videostoremanager;

import com.kineticz.videostoremanager.movies.Movie;

import java.util.Scanner;

/**
 * Member menu container class
 */
public class MemberMenu {

    /**
     * Run the store manager member menu
     */
    public static void main() {
        final int NUM_CHOICES = 5;

        while (true) {
            System.out.println("\n==========Member Menu===========");
            System.out.println("1. Display all movies");
            System.out.println("2. Borrow a movie DVD");
            System.out.println("3. Return a movie DVD");
            System.out.println("4. List current borrowed movie DVDs");
            System.out.println("5. Display top 10 most popular movies");
            System.out.println("0. Return to main menu");
            System.out.println("================================");

            int selection = Validator.getValidSelection(NUM_CHOICES);

            switch (selection) {
                case 1:
                    displayMovies();
                    break;
                case 2:
                    borrowMovie();
                    break;
                case 3:
                    returnMovie();
                    break;
                case 4:
                    listBorrowedMovies();
                    break;
                case 5:
                    listTop10Movies();
                    break;
                case 0:
                    VideoStoreManager.logOut();
                    return;
            }
        }
    }

    /**
     * Display a list of all movies, one at a time
     */
    private static void displayMovies() {
        System.out.println("\n=============Movies=============");

        for (Movie movie : VideoStoreManager.movieCollection.toArray()) {
            System.out.print(movie.toStringVerbose());
            Validator.pressAnyKeyToContinue();
        }

        System.out.println("================================");
    }

    /**
     * Run the prompt for borrowing a movie
     */
    private static void borrowMovie() {
        System.out.println("\n==========Borrow Movie==========");
        Scanner input = new Scanner(System.in);

        System.out.print("Title: ");
        String title = input.nextLine();

        Movie movie = VideoStoreManager.movieCollection.findMovie(title);
        if (movie != null) {
            if (VideoStoreManager.getLoggedInMember().getBorrowedMovies().findMovie(title) == null) {
                if (VideoStoreManager.getLoggedInMember().borrowMovie(movie)) {
                    System.out.printf("Successfully borrowed %s!\n", title);
                } else {
                    System.out.printf("%s is not currently in stock.\n", title);
                }
            } else {
                System.out.printf("You have already borrowed %s!\n", title);
            }
            
        } else {
            System.out.printf("Movie \"%s\" does not exist.\n", title);
        }

        Validator.pressAnyKeyToContinue();
        System.out.println("================================");
    }

    /**
     * Run the prompt for returning a borrowed movie
     */
    private static void returnMovie() {
        System.out.println("\n==========Return Movie==========");

        Movie[] movies = VideoStoreManager.getLoggedInMember().getBorrowedMovies().toArray();

        if (movies.length > 0) {
            System.out.println("Select the movie to return:");


            int i = 1;
            for (Movie movie : movies) {
                System.out.printf("%d. %s\n", i, movie.toString());
                i++;
            }

            int returnChoice = Validator.getValidSelection(i - 1);
            if (returnChoice != 0) {
                VideoStoreManager.getLoggedInMember().returnMovie(movies[returnChoice - 1]);
                System.out.println("Movie returned successfully.");
            }
        } else {
            System.out.println("You have no borrowed movies!");
        }

        Validator.pressAnyKeyToContinue();
        System.out.println("================================");
    }

    /**
     * Lists all movies borrowed by the logged in member
     */
    private static void listBorrowedMovies() {
        System.out.println("\n=========Borrowed Movies========");

        Movie[] movies = VideoStoreManager.getLoggedInMember().getBorrowedMovies().toArray();
        if (movies.length > 0) {
            for (Movie movie : movies) {
                System.out.println(movie.toString());
            }
        } else {
            System.out.println("You have no borrowed movies!");
        }

        Validator.pressAnyKeyToContinue();
        System.out.println("================================");
    }

    /**
     * Lists the top 10 most borrowed movies in order
     */
    private static void listTop10Movies() {
        System.out.println("\n==========Top 10 Movies=========");

        Movie[] topMovies = VideoStoreManager.movieCollection.getTop10Borrowed();

        int i = 1;
        for (Movie movie : topMovies) {
            System.out.printf("%d. %s [Borrowed %d times]\n", i, movie.toString(), movie.getTimesBorrowed());
            i++;
        }

        Validator.pressAnyKeyToContinue();
        System.out.println("================================");
    }
}
