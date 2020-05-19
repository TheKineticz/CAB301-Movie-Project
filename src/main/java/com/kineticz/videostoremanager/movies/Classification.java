package com.kineticz.videostoremanager.movies;

public enum Classification {
    GENERAL("G"),
    PARENTALGUIDANCE("PG"),
    MATURE("M15+"),
    MATUREACCOMPANIED("MA15+");

    private String value;

    Classification(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return value;
    }
}
