package com.tiaotiaopoker.pojo;

import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.StringUtils;

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

    private Integer totalWin;

    private Integer totalScore;

    private Integer totalDisparityOpponent;

    private Integer totalOverA;

    private String resultRule;

    public String getResultRule() {
        return resultRule;
    }

    public void setResultRule(String resultRule) {
        this.resultRule = resultRule;
    }

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

    public Integer getTotalWin() {
        return totalWin;
    }

    public void setTotalWin(Integer totalWin) {
        this.totalWin = totalWin;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getTotalDisparityOpponent() {
        return totalDisparityOpponent;
    }

    public void setTotalDisparityOpponent(Integer totalDisparityOpponent) {
        this.totalDisparityOpponent = totalDisparityOpponent;
    }

    public Integer getTotalOverA() {
        return totalOverA;
    }

    public void setTotalOverA(Integer totalOverA) {
        this.totalOverA = totalOverA;
    }

    @Override
    public int compareTo(MatchTeamResult o) {
        String rule = this.resultRule;
        if (StringUtils.isBlank(rule)) {
            rule = Constants.result.DEFAULT_RESULT_RULE;
        }
        String[] ruleItem = rule.split(",");
        if (ruleItem.length > 0) {
            Class clazz = this.getClass();
            Object thisValue, oValue;
            for (int i = 0; i < ruleItem.length; i++) {
                try {
                    thisValue = clazz.getMethod("get" + ruleItem[i]).invoke(this);
                    oValue = clazz.getMethod("get" + ruleItem[i]).invoke(o);
                    if ((int) thisValue != (int) oValue) {
                        return (int) oValue - (int) thisValue;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }


}