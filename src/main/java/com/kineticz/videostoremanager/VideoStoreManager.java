package com.kineticz.videostoremanager;

import com.kineticz.videostoremanager.members.Member;
import com.kineticz.videostoremanager.members.MemberCollection;
import com.kineticz.videostoremanager.movies.Classification;
import com.kineticz.videostoremanager.movies.Genre;
import com.kineticz.videostoremanager.movies.Movie;
import com.kineticz.videostoremanager.movies.MovieCollection;

/**
 * Entry point for the video store manager program
 */
public class VideoStoreManager {

    static final MovieCollection movieCollection = new MovieCollection();
    static final MemberCollection memberCollection = new MemberCollection();

    private static Member loggedInMember;

    /**
     * Application entry point and main loop
     *
     * @param args Launch args
     */
    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            isRunning = MainMenu.main();
        }
    }

    /**
     * Get the currently logged in member
     *
     * @return The currently logged in member
     */
    public static Member getLoggedInMember() {
        return loggedInMember;
    }

    /**
     * Register a given member as the logged in user
     *
     * @param member The member that has logged in
     */
    public static void logInMember(Member member) {
        loggedInMember = member;
    }

    /**
     * Log out any user that is logged in
     */
    public static void logOut() {
        loggedInMember = null;
    }

}
