package com.kineticz.videostoremanager;

import com.kineticz.videostoremanager.members.Member;
import com.kineticz.videostoremanager.members.StaffAccount;

import java.util.Scanner;

/**
 * Main menu container class
 */
public class MainMenu {

    /**
     * Run the store manager main menu
     *
     * @return Return true if the application should continue running, false if it should exit
     */
    static boolean main() {
        final int NUM_CHOICES = 2;

        System.out.println("\nWelcome to the Community Library");
        System.out.println("===========Main Menu============");
        System.out.println(" 1. Staff Login");
        System.out.println(" 2. Member Login");
        System.out.println(" 0. Exit");
        System.out.println("================================");

        int selection = Validator.getValidSelection(NUM_CHOICES);

        switch (selection) {
            case 1:
                if (staffLogin()) {
                    StaffMenu.main();
                } break;

            case 2:
                if (memberLogin()) {
                    MemberMenu.main();
                } break;

            case 0:
                return false;
        }

        return true;
    }

    /**
     * Run the prompt for staff login credentials
     *
     * @return Return true if the entered credentials are correct, false if incorrect
     */
    private static boolean staffLogin() {
        String username;
        String password;

        System.out.println("\n==========Staff Login===========");
        Scanner input = new Scanner(System.in);

        System.out.print(" Username: ");
        username = input.nextLine();
        System.out.print(" Password: ");
        password = input.nextLine();

        if (StaffAccount.checkStaffLogin(username, password)) {
            System.out.println("Logged in successfully.");
            System.out.println("================================");
            return true;
        } else {
            System.out.println("Staff credentials incorrect.");
            Validator.pressAnyKeyToContinue();
            System.out.println("================================");
            return false;
        }
    }

    /**
     * Run the prompt for member login credentials
     *
     * @return Return true if the entered credentials are correct, false if incorrect
     */
    private static boolean memberLogin() {
        String username;
        String password;
        Member member;

        System.out.println("\n==========Member Login==========");
        Scanner input = new Scanner(System.in);

        System.out.print(" Username: ");
        username = input.nextLine();

        int memberIndex = VideoStoreManager.memberCollection.findMember(username);
        if (memberIndex != -1) {
            member = VideoStoreManager.memberCollection.getMember(memberIndex);
        } else {
            System.out.printf("No member exists with username \"%s\"\n", username);
            return false;
        }

        System.out.print(" Password: ");
        password = input.nextLine();

        if (member.checkPassword(password)) {
            VideoStoreManager.logInMember(member);
            System.out.println("Logged in successfully.");
            System.out.println("================================");
            return true;
        } else {
            System.out.println("Password incorrect.");
            Validator.pressAnyKeyToContinue();
            System.out.println("================================");
            return false;
        }
    }
}
