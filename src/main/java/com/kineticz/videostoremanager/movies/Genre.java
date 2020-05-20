package com.kineticz.videostoremanager.movies;

/**
 * Enum for movie genre types
 */
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

    /**
     * Overrides the toString function to return the value added in the constructor
     *
     * @return The name of the genre in human-readable form
     */
    @Override
    public String toString(){
        return value;
    }
}
