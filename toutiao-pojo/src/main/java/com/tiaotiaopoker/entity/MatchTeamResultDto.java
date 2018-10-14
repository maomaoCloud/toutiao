package com.tiaotiaopoker.entity;

import com.tiaotiaopoker.pojo.MatchTeamResult;

public class MatchTeamResultDto extends MatchTeamResult {

    private int tableNumber;

    private String userOne;

    private String userTwo;

    private int teamNumber;

    public String getUserOne() {
        return userOne;
    }

    public void setUserOne(String userOne) {
        this.userOne = userOne;
    }

    public String getUserTwo() {
        return userTwo;
    }

    public void setUserTwo(String userTwo) {
        this.userTwo = userTwo;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
}
