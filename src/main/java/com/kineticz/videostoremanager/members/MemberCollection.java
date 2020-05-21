package com.kineticz.videostoremanager.members;

import com.kineticz.videostoremanager.exception.MemberCollectionException;

import java.util.Arrays;

/**
 * Array object for storing and managing registered members
 *
 * Internally, this class has a fixed maximum length, but to external classes it will appear to grow and shrink
 */
public class MemberCollection {
    private Member[] members;
    private int length;

    private final String staffUsername = "staff";
    private final String staffPasswordSalt = "e488b1eca1e1ccf463d5b2af4aed2504e961586f186389e3baf467ad2838e845";
    private final String staffPasswordHash = "134e24e42d6a3bcbb4b1200b95cdaa8c06b421106f46e03c7c7530728fda54d5";

    private static final int MAX_MEMBERS = 10;
    private static final Member[] ZERO_MEMBERS = {};

    /**
     * Create a new empty member collection
     */
    public MemberCollection() {
        members = new Member[MAX_MEMBERS];
        length = 0;
    }

    /**
     * Get the (externally-facing) length of the member collection
     * @return The length of the member collection (the number of registered members)
     */
    public int getLength() {
        return length;
    }

    /**
     * Get the entire member list, up to the last registered member
     *
     * @return The members array
     */
    public Member[] get() {
        if (length > 0) {
            return Arrays.copyOfRange(members, 0, length);
        } else {
            return ZERO_MEMBERS;
        }
    }

    /**
     * Get and return a single member, given the index
     *
     * @param i The index of the member object
     * @return The member object
     * @throws ArrayIndexOutOfBoundsException Thrown if the given index is outside the (externally-facing) length of the array
     */
    public Member getMember(int i) throws ArrayIndexOutOfBoundsException {
        if (i >= 0 && i < length) {
            return members[i];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Add a new member to the collection
     *
     * @param member The member object of the new member
     * @throws MemberCollectionException Thrown if trying to add a new member when collection is full
     */
    public void addMember(Member member) throws MemberCollectionException {
        if (length < MAX_MEMBERS){
            members[length++] = member;
        } else {
            throw new MemberCollectionException(String.format("Exceeded maximum member count of %d", MAX_MEMBERS));
        }
    }

    /**
     * Find and remove a member by username
     *
     * @param username The username of the member to be removed
     * @return Returns true if the member was found and removed, false if not found
     */
    public boolean removeMember(String username) {
        for (int i = 0; i < length; i++) {
            if (members[i].username.equals(username)) {
                if (i < MAX_MEMBERS - 1) {
                    System.arraycopy(members, i + 1, members, i, MAX_MEMBERS - 1 - i);
                }
                members[--length] = null;

                return true;
            }
        }

        return false;
    }

    /**
     * Finds a member by username and returns the index of that member
     *
     * @param username The username of the member to search for
     * @return Returns the index of the member if found, -1 if the member is not found
     */
    public int findMember(String username) {
        for (int i = 0; i < length; i++) {
            if (members[i].username.equals(username)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Get a member's phone number given their full name
     *
     * @param givenName The member's given name
     * @param surname The member's surname
     * @return The member's phone number
     * @throws MemberCollectionException Thrown if no match for the name is found
     */
    public String getPhoneNumber(String givenName, String surname) throws MemberCollectionException {
        String username = surname + givenName;

        int memberIndex = findMember(username);
        if (memberIndex != -1) {
            return members[memberIndex].phoneNumber;
        } else {
            throw new MemberCollectionException(String.format("No member found matching name %s %s.", givenName, surname));
        }
    }

    /**
     * Clears the member collection
     */
    public void clear() {
        members = new Member[MAX_MEMBERS];
    }

    public boolean checkStaffLogin(String username, String password) {
        return username.equals(staffUsername) && Passwords.checkPassword(password, staffPasswordSalt, staffPasswordHash);
    }
}
