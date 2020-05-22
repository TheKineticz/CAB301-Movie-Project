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

    /**
     * Return an enum value from a string
     *
     * @param input The input string value
     * @return The corresponding enum value
     */
    public static Classification getFromString(String input) {
        for (Classification classification : Classification.values()) {
            if (input.equals(classification.toString())) {
                return classification;
            }
        }

        throw new IllegalArgumentException();
    }
}
