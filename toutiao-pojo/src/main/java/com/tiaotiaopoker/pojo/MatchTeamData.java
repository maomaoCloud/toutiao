package com.tiaotiaopoker.pojo;

public class MatchTeamData {
    private String id;

    private String matchId;

    private Integer turnNumber;

    private Integer tableNumber;

    private String teamOneId;

    private String teamOneScore;

    private String teamTwoId;

    private String teamTwoScore;

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
}