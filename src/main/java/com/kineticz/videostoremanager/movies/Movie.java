package com.kineticz.videostoremanager.movies;

import java.util.UUID;

public class Movie {
    private UUID uuid;

    String title;
    String[] starring;
    String director;
    Genre genre;
    Classification classification;

    public Movie(String title, String[] starring, String director, Genre genre, Classification classification){
        uuid = UUID.randomUUID();

        this.title = title;
        this.starring = starring;
        this.director = director;
        this.genre = genre;
        this.classification = classification;
    }

    public UUID getUUID(){
        return uuid;
    }
}
