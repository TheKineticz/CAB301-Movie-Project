package com.kineticz.videostoremanager.movies;

public class Movie {
    String title;
    String[] starring;
    String director;
    Genre genre;
    Classification classification;

    public Movie(String title, String[] starring, String director, Genre genre, Classification classification){
        this.title = title;
        this.starring = starring;
        this.director = director;
        this.genre = genre;
        this.classification = classification;
    }
}
