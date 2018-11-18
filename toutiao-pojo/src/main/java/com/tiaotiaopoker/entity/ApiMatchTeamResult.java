package com.tiaotiaopoker.entity;

import com.tiaotiaopoker.pojo.MatchTeamResult;

public class ApiMatchTeamResult {
    Integer index;

    String userAHead;

    String userAName;

    String userBHead;

    String userBName;

    String resultString;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getUserAHead() {
        return userAHead;
    }

    public void setUserAHead(String userAHead) {
        this.userAHead = userAHead;
    }

    public String getUserAName() {
        return userAName;
    }

    public void setUserAName(String userAName) {
        this.userAName = userAName;
    }

    public String getUserBHead() {
        return userBHead;
    }

    public void setUserBHead(String userBHead) {
        this.userBHead = userBHead;
    }

    public String getUserBName() {
        return userBName;
    }

    public void setUserBName(String userBName) {
        this.userBName = userBName;
    }

    public String getResultString() {
        return resultString;
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }

    public static ApiMatchTeamResult genFromMatchTeamResultDto(MatchTeamResultDto result) {
        ApiMatchTeamResult apiResult = new ApiMatchTeamResult();
        apiResult.setUserAHead(result.getUserOneHead());
        apiResult.setUserAName(result.getUserOne());
        apiResult.setUserBHead(result.getUserTwoHead());
        apiResult.setUserBName(result.getUserTwo());
        return apiResult;
    }
}
