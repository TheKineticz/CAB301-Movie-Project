package com.kineticz.videostoremanager.movies;

/**
 * Enum for movie classification types
 */
public enum Classification {
    GENERAL("G"),
    PARENTALGUIDANCE("PG"),
    MATURE("M15+"),
    MATUREACCOMPANIED("MA15+");

    private String value;

    Classification(String value){
        this.value = value;
    }

    /**
     * Overrides the toString function to return the value added in the constructor
     *
     * @return The name of the classification in human-readable form
     */
    @Override
    public String toString(){
        return value;
    }
}
