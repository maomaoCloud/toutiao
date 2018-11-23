package com.tiaotiaopoker.entity;//

public class ApiTeamData {

    String matchId;//比赛Id

    Integer turn;//要保存座位的轮次

    String teamDataIds[];//当前轮次四人组的id集合

    String teamOneIds[];//队伍一（即东西方）id集合

    String teamOneScores[];//队伍一成绩集合

    String teamTwoIds[];//队伍二（即南北方）id集合

    String teamTwoScores[];//队伍二成绩

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

    public String[] getTeamDataIds() {
        return teamDataIds;
    }

    public void setTeamDataIds(String[] teamDataIds) {
        this.teamDataIds = teamDataIds;
    }

    public String[] getTeamOneIds() {
        return teamOneIds;
    }

    public void setTeamOneIds(String[] teamOneIds) {
        this.teamOneIds = teamOneIds;
    }

    public String[] getTeamOneScores() {
        return teamOneScores;
    }

    public void setTeamOneScores(String[] teamOneScores) {
        this.teamOneScores = teamOneScores;
    }

    public String[] getTeamTwoIds() {
        return teamTwoIds;
    }

    public void setTeamTwoIds(String[] teamTwoIds) {
        this.teamTwoIds = teamTwoIds;
    }

    public String[] getTeamTwoScores() {
        return teamTwoScores;
    }

    public void setTeamTwoScores(String[] teamTwoScores) {
        this.teamTwoScores = teamTwoScores;
    }
}
