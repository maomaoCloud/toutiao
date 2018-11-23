package com.tiaotiaopoker.entity;

public class SeatUpdateData {
    private String[] teamIds;
    private String matchId;
    private Integer turn;

    public String[] getTeamIds() {
        return teamIds;
    }

    public void setTeamIds(String[] teamIds) {
        this.teamIds = teamIds;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }
}
