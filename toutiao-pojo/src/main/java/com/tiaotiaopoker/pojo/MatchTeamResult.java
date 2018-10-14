package com.tiaotiaopoker.pojo;

public class MatchTeamResult implements Comparable<MatchTeamResult> {
    private String id;

    private String matchId;

    private String teamId;

    private Integer turnNumber;

    private String score;

    private Integer point;

    private Integer disparity;

    private Integer totalExfirst;

    private Integer totalPoint;

    private Integer totalExfirstAll;

    private Integer totalDisparity;

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

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId == null ? null : teamId.trim();
    }

    public Integer getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(Integer turnNumber) {
        this.turnNumber = turnNumber;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getDisparity() {
        return disparity;
    }

    public void setDisparity(Integer disparity) {
        this.disparity = disparity;
    }

    public Integer getTotalExfirst() {
        return totalExfirst;
    }

    public void setTotalExfirst(Integer totalExfirst) {
        this.totalExfirst = totalExfirst;
    }

    public Integer getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Integer totalPoint) {
        this.totalPoint = totalPoint;
    }

    public Integer getTotalExfirstAll() {
        return totalExfirstAll;
    }

    public void setTotalExfirstAll(Integer totalExfirstAll) {
        this.totalExfirstAll = totalExfirstAll;
    }

    public Integer getTotalDisparity() {
        return totalDisparity;
    }

    public void setTotalDisparity(Integer totalDisparity) {
        this.totalDisparity = totalDisparity;
    }

    @Override
    public int compareTo(MatchTeamResult o) {
        if (!this.totalPoint.equals(o.getTotalPoint())) {
            return o.getTotalPoint() - this.totalPoint;
        }
        if (!this.totalExfirstAll.equals(o.getTotalExfirstAll())) {
            return o.getTotalExfirstAll() - this.totalExfirstAll;
        }
        return o.getTotalDisparity() - this.totalDisparity;
    }
}