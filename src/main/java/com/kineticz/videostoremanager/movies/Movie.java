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

    public Movie(String title, String[] starring, String director, Genre genre, Classification classification, int stockQuantity) {
        this.title = title;
        this.starring = starring;
        this.director = director;
        this.genre = genre;
        this.classification = classification;
    }
}
