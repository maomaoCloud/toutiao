package com.tiaotiaopoker.entity;

import com.tiaotiaopoker.pojo.MatchTeamData;

public class MatchTeamDataDto extends MatchTeamData {
    private String teamOneUserOneHead;
    private String teamOneUserOneName;

    private String teamOneUserTwoHead;
    private String teamOneUserTwoName;

    private String teamTwoUserOneHead;
    private String teamTwoUserOneName;

    private String teamTwoUserTwoHead;
    private String teamTwoUserTwoName;

    private String teamOneNum;
    private String teamTwoNum;

    private String teamOneId;
    private String teamTwoId;

    private String id;

    public String getTeamOneNum() {
        return teamOneNum;
    }

    public void setTeamOneNum(String teamOneNum) {
        this.teamOneNum = teamOneNum;
    }

    public String getTeamTwoNum() {
        return teamTwoNum;
    }

    public void setTeamTwoNum(String teamTwoNum) {
        this.teamTwoNum = teamTwoNum;
    }

    public String getTeamOneUserOneHead() {
        return teamOneUserOneHead;
    }

    public void setTeamOneUserOneHead(String teamOneUserOneHead) {
        this.teamOneUserOneHead = teamOneUserOneHead;
    }

    public String getTeamOneUserOneName() {
        return teamOneUserOneName;
    }

    public void setTeamOneUserOneName(String teamOneUserOneName) {
        this.teamOneUserOneName = teamOneUserOneName;
    }

    public String getTeamOneUserTwoHead() {
        return teamOneUserTwoHead;
    }

    public void setTeamOneUserTwoHead(String teamOneUserTwoHead) {
        this.teamOneUserTwoHead = teamOneUserTwoHead;
    }

    public String getTeamOneUserTwoName() {
        return teamOneUserTwoName;
    }

    public void setTeamOneUserTwoName(String teamOneUserTwoName) {
        this.teamOneUserTwoName = teamOneUserTwoName;
    }

    public String getTeamTwoUserOneHead() {
        return teamTwoUserOneHead;
    }

    public void setTeamTwoUserOneHead(String teamTwoUserOneHead) {
        this.teamTwoUserOneHead = teamTwoUserOneHead;
    }

    public String getTeamTwoUserOneName() {
        return teamTwoUserOneName;
    }

    public void setTeamTwoUserOneName(String teamTwoUserOneName) {
        this.teamTwoUserOneName = teamTwoUserOneName;
    }

    public String getTeamTwoUserTwoHead() {
        return teamTwoUserTwoHead;
    }

    public void setTeamTwoUserTwoHead(String teamTwoUserTwoHead) {
        this.teamTwoUserTwoHead = teamTwoUserTwoHead;
    }

    public String getTeamTwoUserTwoName() {
        return teamTwoUserTwoName;
    }

    public void setTeamTwoUserTwoName(String teamTwoUserTwoName) {
        this.teamTwoUserTwoName = teamTwoUserTwoName;
    }

    @Override
    public String getTeamOneId () {
        return teamOneId;
    }

    @Override
    public void setTeamOneId (String teamOneId) {
        this.teamOneId = teamOneId;
    }

    @Override
    public String getTeamTwoId () {
        return teamTwoId;
    }

    @Override
    public void setTeamTwoId (String teamTwoId) {
        this.teamTwoId = teamTwoId;
    }

    @Override
    public String getId () {
        return id;
    }

    @Override
    public void setId (String id) {
        this.id = id;
    }

}
