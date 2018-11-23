package com.tiaotiaopoker.entity;

public class SeatSaveData {
    private String   matchId;
    private Integer  turn;
    private String[] userIds;
    private String[] names;
    private String[] heads;

    public String[] getUserIds() {
        return userIds;
    }

    public void setUserIds(String[] userIds) {
        this.userIds = userIds;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public String[] getHeads() {
        return heads;
    }

    public void setHeads(String[] heads) {
        this.heads = heads;
    }

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

}
