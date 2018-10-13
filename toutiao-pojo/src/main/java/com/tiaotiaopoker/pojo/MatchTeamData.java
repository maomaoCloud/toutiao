package com.tiaotiaopoker.pojo;

public class MatchTeamData {
    private String id;

    private String matchId;

    private Integer turnNumber;

    private Integer tableNumber;

    private String teamOneId;

    private String teamOneScore;

    private Integer teamOnePoint;

    private Integer teamOneDisparity;

    private Integer teamOneTotalExfirst;

    private String teamTwoId;

    private String teamTwoScore;

    private Integer teamTwoPoint;

    private Integer teamTwoDisparity;

    private Integer teamTwoTotalExfirst;

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

    public Integer getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(Integer turnNumber) {
        this.turnNumber = turnNumber;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getTeamOneId() {
        return teamOneId;
    }

    public void setTeamOneId(String teamOneId) {
        this.teamOneId = teamOneId == null ? null : teamOneId.trim();
    }

    public String getTeamOneScore() {
        return teamOneScore;
    }

    public void setTeamOneScore(String teamOneScore) {
        this.teamOneScore = teamOneScore == null ? null : teamOneScore.trim();
    }

    public Integer getTeamOnePoint() {
        return teamOnePoint;
    }

    public void setTeamOnePoint(Integer teamOnePoint) {
        this.teamOnePoint = teamOnePoint;
    }

    public Integer getTeamOneDisparity() {
        return teamOneDisparity;
    }

    public void setTeamOneDisparity(Integer teamOneDisparity) {
        this.teamOneDisparity = teamOneDisparity;
    }

    public Integer getTeamOneTotalExfirst() {
        return teamOneTotalExfirst;
    }

    public void setTeamOneTotalExfirst(Integer teamOneTotalExfirst) {
        this.teamOneTotalExfirst = teamOneTotalExfirst;
    }

    public String getTeamTwoId() {
        return teamTwoId;
    }

    public void setTeamTwoId(String teamTwoId) {
        this.teamTwoId = teamTwoId == null ? null : teamTwoId.trim();
    }

    public String getTeamTwoScore() {
        return teamTwoScore;
    }

    public void setTeamTwoScore(String teamTwoScore) {
        this.teamTwoScore = teamTwoScore == null ? null : teamTwoScore.trim();
    }

    public Integer getTeamTwoPoint() {
        return teamTwoPoint;
    }

    public void setTeamTwoPoint(Integer teamTwoPoint) {
        this.teamTwoPoint = teamTwoPoint;
    }

    public Integer getTeamTwoDisparity() {
        return teamTwoDisparity;
    }

    public void setTeamTwoDisparity(Integer teamTwoDisparity) {
        this.teamTwoDisparity = teamTwoDisparity;
    }

    public Integer getTeamTwoTotalExfirst() {
        return teamTwoTotalExfirst;
    }

    public void setTeamTwoTotalExfirst(Integer teamTwoTotalExfirst) {
        this.teamTwoTotalExfirst = teamTwoTotalExfirst;
    }
}