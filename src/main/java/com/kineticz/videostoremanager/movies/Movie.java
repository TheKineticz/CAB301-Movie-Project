package com.kineticz.videostoremanager.movies;

/**
 * Container for movies and their information
 */
public class Movie {
    String title;
    String[] starring;
    String director;
    Genre genre;
    Classification classification;

    int stock;
    int timesBorrowed;

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
}
