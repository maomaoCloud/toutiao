package com.tiaotiaopoker.pojo;

public class MatchTeamMember {
    private String id;

    private String matchId;

    private Integer teamNumber;

    private String teamMemberOne;

    private String teamMemberOneName;

    private String teamMemberOneHead;

    private String teamMemberTwo;

    private String teamMemberTwoName;

    private String teamMemberTwoHead;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId == null ? null : matchId.trim();
    }

    public Integer getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(Integer teamNumber) {
        this.teamNumber = teamNumber;
    }

    public String getTeamMemberOne() {
        return teamMemberOne;
    }

    public void setTeamMemberOne(String teamMemberOne) {
        this.teamMemberOne = teamMemberOne == null ? null : teamMemberOne.trim();
    }

    public String getTeamMemberOneName() {
        return teamMemberOneName;
    }

    public void setTeamMemberOneName(String teamMemberOneName) {
        this.teamMemberOneName = teamMemberOneName == null ? null : teamMemberOneName.trim();
    }

    public String getTeamMemberOneHead() {
        return teamMemberOneHead;
    }

    public void setTeamMemberOneHead(String teamMemberOneHead) {
        this.teamMemberOneHead = teamMemberOneHead == null ? null : teamMemberOneHead.trim();
    }

    public String getTeamMemberTwo() {
        return teamMemberTwo;
    }

    public void setTeamMemberTwo(String teamMemberTwo) {
        this.teamMemberTwo = teamMemberTwo == null ? null : teamMemberTwo.trim();
    }

    public String getTeamMemberTwoName() {
        return teamMemberTwoName;
    }

    public void setTeamMemberTwoName(String teamMemberTwoName) {
        this.teamMemberTwoName = teamMemberTwoName == null ? null : teamMemberTwoName.trim();
    }

    public String getTeamMemberTwoHead() {
        return teamMemberTwoHead;
    }

    public void setTeamMemberTwoHead(String teamMemberTwoHead) {
        this.teamMemberTwoHead = teamMemberTwoHead == null ? null : teamMemberTwoHead.trim();
    }
}