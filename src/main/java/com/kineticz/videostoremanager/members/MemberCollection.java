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

    private static final int MAX_MEMBERS = 10;
    private static final Member[] NO_MEMBERS = {};

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
            return NO_MEMBERS;
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
    public void add(Member member) throws MemberCollectionException {
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
    public boolean remove(String username) {
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
    public int find(String username) {
        for (int i = 0; i < length; i++) {
            if (members[i].username.equals(username)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Clears the member collection
     */
    public void clear() {
        members = new Member[MAX_MEMBERS];
    }
}
