package com.kineticz.videostoremanager.movies;

import com.kineticz.videostoremanager.members.Member;

public class Movie {
    String title;
    String[] starring;
    String director;
    Genre genre;
    Classification classification;

    private int stockQuantity;
    private int timesBorrowed;

    public Movie(String title, String[] starring, String director, Genre genre, Classification classification, int stockQuantity) {
        this.title = title;
        this.starring = starring;
        this.director = director;
        this.genre = genre;
        this.classification = classification;

        this.stockQuantity = stockQuantity;
        timesBorrowed = 0;
    }

    public int getStock() {
        return stockQuantity;
    }

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }
}
