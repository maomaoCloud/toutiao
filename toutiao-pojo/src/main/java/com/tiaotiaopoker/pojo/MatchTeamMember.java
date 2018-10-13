package com.tiaotiaopoker.pojo;

public class MatchTeamMember {
    private String id;

    private Integer teamNumber;

    private String teamMemberOne;

    private String teamMemberTwo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getTeamMemberTwo() {
        return teamMemberTwo;
    }

    public void setTeamMemberTwo(String teamMemberTwo) {
        this.teamMemberTwo = teamMemberTwo == null ? null : teamMemberTwo.trim();
    }
}