package com.tiaotiaopoker.entity;

import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.pojo.MatchTeamResult;

import java.util.ArrayList;
import java.util.List;

public class MatchTeamResultDto extends MatchTeamResult {

    private int index;

    private int tableNumber;

    private String userOne;

    private String userTwo;

    private String userOneHead;

    private String userTwoHead;

    private int teamNumber;

    private String resultString;

    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getResultString () {
        return resultString;
    }

    public void setResultString (String resultString) {
        this.resultString = resultString;
    }

    public String getUserOneHead () {
        return userOneHead;
    }

    public void setUserOneHead (String userOneHead) {
        this.userOneHead = userOneHead;
    }

    public String getUserTwoHead () {
        return userTwoHead;
    }

    public void setUserTwoHead (String userTwoHead) {
        this.userTwoHead = userTwoHead;
    }

    public String getUserOne () {
        return userOne;
    }

    public void setUserOne (String userOne) {
        this.userOne = userOne;
    }

    public String getUserTwo () {
        return userTwo;
    }

    public void setUserTwo (String userTwo) {
        this.userTwo = userTwo;
    }

    public int getTeamNumber () {
        return teamNumber;
    }

    public void setTeamNumber (int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public int getTableNumber () {
        return tableNumber;
    }

    public void setTableNumber (int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getIndex () {
        return index;
    }

    public void setIndex (int index) {
        this.index = index;
    }

}
