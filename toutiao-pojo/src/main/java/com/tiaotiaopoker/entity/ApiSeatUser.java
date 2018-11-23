package com.tiaotiaopoker.entity;


import com.tiaotiaopoker.pojo.AppUser;

public class ApiSeatUser {
    private String userId;
    private String name;
    private String head;

    public String getUserId () {
        return userId;
    }

    public void setUserId (String userId) {
        this.userId = userId;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getHead () {
        return head;
    }

    public void setHead (String head) {
        this.head = head;
    }

    public static ApiSeatUser genFromAppUser (AppUser u) {
        ApiSeatUser user = new ApiSeatUser();
        user.setUserId(u.getId());
        user.setName(u.getNickName());
        user.setHead(u.getAvatarUrl());
        return user;
    }

}
