package com.kineticz.videostoremanager.movies;

import com.kineticz.videostoremanager.exception.MovieStockException;

/**
 * Container for movies and their information
 */
public class Movie {
    public final String title;
    public final String[] starring;
    public final String director;
    public final Genre genre;
    public final Classification classification;

    private int stock;
    private int timesBorrowed;

    /**
     * Creates a new movie object
     *
     * @param title The title of the movie
     * @param starring An array of the actors starring in the movie
     * @param director The director of the movie
     * @param genre The genre of the movie
     * @param classification The movie's classification
     * @param stock How many copies of the movie are in stock
     */
    public Movie(String title, String[] starring, String director, Genre genre, Classification classification, int stock) {
        this.title = title;
        this.starring = starring;
        this.director = director;
        this.genre = genre;
        this.classification = classification;

        this.stock = stock;
        timesBorrowed = 0;
    }

    /**
     * Get the amount of movie discs in stock
     *
     * @return The number of discs in stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Adds n movie discs to the stock
     *
     * @param amount The number of movie discs to add
     * @throws MovieStockException Thrown if the number to be added is zero or negative
     */
    public void addStock(int amount) throws MovieStockException {
        if (amount > 0) {
            stock += amount;
        } else {
            throw new MovieStockException("Cannot add zero or negative stock");
        }
    }

    /**
     * Removes n discs from the stock
     *
     * @param amount The number of discs to remove
     * @throws MovieStockException Thrown if the number of discs to be removed is greater than the amount currently in stock
     */
    public void removeStock(int amount) throws MovieStockException {
        if (amount <= stock) {
            stock -= amount;
        } else {
            throw new MovieStockException("Cannot remove more stock than exists");
        }
    }

    /**
     * Shorthand function for borrowing a single movie disc from the stock
     *
     * @return Returns true if the transaction was successful, false if there is not enough stock to complete the transaction
     */
    public boolean borrowSingle() {
        try {
            removeStock(1);
            timesBorrowed++;
            return true;

        } catch (MovieStockException e) {
            return false;
        }
    }

    /**
     * Shorthand function for returning a single movie disc to the stock
     */
    public void returnSingle() {
        try {
            addStock(1);
        } catch (MovieStockException e) {
            System.out.println("Pack up and move to a new universe. It should be impossible to see this message as long as the laws of physics stay true.");
        }

    }

    /**
     * Get the amount of times the movie has been borrowed
     *
     * @return The amount of times the movie has been borrowed
     */
    public int getTimesBorrowed() {
        return timesBorrowed;
    }

    public String toStringVerbose() {
        StringBuilder str = new StringBuilder();

        str.append(title).append("\n");

        str.append("Starring: ").append(starring[0]);
        for (int i = 1; i < starring.length; i++) {
            str.append(", ").append(starring[i]);
        }
        str.append("\n");

        str.append("Director: ").append(director).append("\n");
        str.append("Genre: ").append(genre.toString()).append("\n");
        str.append("Classification: ").append(classification.toString()).append("\n\n");

        return str.toString();
    }

    /**
     * Get the title of the movie as the string form of the movie
     *
     * @return The title of the movie
     */
    @Override
    public String toString() {
        return title;
    }
}
