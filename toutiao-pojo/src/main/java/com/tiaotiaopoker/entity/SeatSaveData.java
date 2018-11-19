package com.tiaotiaopoker.entity;

public class SeatSaveData {
    private String    matchId;
    private Integer   turn;
    private Integer[] res;

    public String getMatchId () {
        return matchId;
    }

    public void setMatchId (String matchId) {
        this.matchId = matchId;
    }

    public Integer getTurn () {
        return turn;
    }

    public void setTurn (Integer turn) {
        this.turn = turn;
    }

    public Integer[] getRes () {
        return res;
    }

    public void setRes (Integer[] res) {
        this.res = res;
    }

}
