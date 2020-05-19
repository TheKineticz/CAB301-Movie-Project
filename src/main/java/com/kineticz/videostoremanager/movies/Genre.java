package com.kineticz.videostoremanager.movies;

public enum Genre {
    DRAMA("Drama"),
    ADVENTURE("Adventure"),
    FAMILY("Family"),
    ACTION("Action"),
    SCIFI("Sci-Fi"),
    COMEDY("Comedy"),
    ANIMATED("Animated"),
    THRILLER("Thriller"),
    OTHER("Other");

    private String value;

    Genre(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return value;
    }
}
