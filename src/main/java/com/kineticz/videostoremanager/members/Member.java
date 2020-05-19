package com.kineticz.videostoremanager.members;

import java.util.UUID;
import java.util.ArrayList;

public class Member {
    private UUID uuid;

    String name;
    String address;
    String phoneNumber;
    ArrayList<String> borrowedMovies;

    public Member(String name, String address, String phoneNumber, ArrayList<String> borrowedMovies){
        uuid = UUID.randomUUID();

        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.borrowedMovies = borrowedMovies;
    }
}
