package com.kineticz.videostoremanager.members;

public class MemberCollection {
    private Member[] members;
    private int end;

    private static final int MAX_MEMBERS = 10;

    public MemberCollection() {
        members = new Member[MAX_MEMBERS];
        end = -1;
    }

    public Member[] getMembers() {
        return members;
    }

    public void addMember(Member member) throws Exception {
        if (end < MAX_MEMBERS - 1){
            end++;
            members[end] = member;
        } else {
            throw new Exception(String.format("Exceeded maximum member count of %d", MAX_MEMBERS));
        }

    }

    public boolean removeMember(String username) {
        for (int i = 0; i <= end; i++) {
            if (members[i].username.equals(username)) {
                if (end - 1 - i >= 0) {
                    System.arraycopy(members, i + 1, members, i, end - 1 - i);
                }

                end--;
                return true;
            }
        }

        return false;
    }

    public int find(String username) {
        for (int i = 0; i < end; i++) {
            if (members[i].username.equals(username)) {
                return i;
            }
        }

        return -1;
    }

    public void clear() {
        members = new Member[MAX_MEMBERS];
    }
}
