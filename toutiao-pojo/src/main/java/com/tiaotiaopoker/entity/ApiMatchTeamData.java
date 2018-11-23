package com.tiaotiaopoker.entity;

public class ApiMatchTeamData {
    private Integer zh;

    private Integer groupANumber;

    private String groupAId;

    private String groupAUserAHead;

    private String groupAUserAName;

    private String groupAUserBName;

    private String groupAUserBHead;

    private Integer groupBNumber;

    private String groupBId;

    private String groupBUserAHead;

    private String groupBUserAName;

    private String groupBUserBName;

    private String groupBUserBHead;

    private String id;

    public Integer getZh () {
        return zh;
    }

    public void setZh (Integer zh) {
        this.zh = zh;
    }

    public Integer getGroupANumber () {
        return groupANumber;
    }

    public void setGroupANumber (Integer groupANumber) {
        this.groupANumber = groupANumber;
    }

    public String getGroupAUserAHead () {
        return groupAUserAHead;
    }

    public void setGroupAUserAHead (String groupAUserAHead) {
        this.groupAUserAHead = groupAUserAHead;
    }

    public String getGroupAUserAName () {
        return groupAUserAName;
    }

    public void setGroupAUserAName (String groupAUserAName) {
        this.groupAUserAName = groupAUserAName;
    }

    public String getGroupAUserBName () {
        return groupAUserBName;
    }

    public void setGroupAUserBName (String groupAUserBName) {
        this.groupAUserBName = groupAUserBName;
    }

    public String getGroupAUserBHead () {
        return groupAUserBHead;
    }

    public void setGroupAUserBHead (String groupAUserBHead) {
        this.groupAUserBHead = groupAUserBHead;
    }

    public Integer getGroupBNumber () {
        return groupBNumber;
    }

    public void setGroupBNumber (Integer groupBNumber) {
        this.groupBNumber = groupBNumber;
    }

    public String getGroupBUserAHead () {
        return groupBUserAHead;
    }

    public void setGroupBUserAHead (String groupBUserAHead) {
        this.groupBUserAHead = groupBUserAHead;
    }

    public String getGroupBUserAName () {
        return groupBUserAName;
    }

    public void setGroupBUserAName (String groupBUserAName) {
        this.groupBUserAName = groupBUserAName;
    }

    public String getGroupBUserBName () {
        return groupBUserBName;
    }

    public void setGroupBUserBName (String groupBUserBName) {
        this.groupBUserBName = groupBUserBName;
    }

    public String getGroupBUserBHead () {
        return groupBUserBHead;
    }

    public void setGroupBUserBHead (String groupBUserBHead) {
        this.groupBUserBHead = groupBUserBHead;
    }

    public String getGroupBId () {
        return groupBId;
    }

    public void setGroupBId (String groupBId) {
        this.groupBId = groupBId;
    }

    public String getGroupAId () {
        return groupAId;
    }

    public void setGroupAId (String groupAId) {
        this.groupAId = groupAId;
    }

    public String getId () {
        return id;
    }

    public void setId (String id) {
        this.id = id;
    }

    public static ApiMatchTeamData genFromMatchTeamDataDto (MatchTeamDataDto dataDto) {
        ApiMatchTeamData data = new ApiMatchTeamData();
        data.setZh(dataDto.getTableNumber());
        data.setGroupANumber(new Integer(dataDto.getTeamOneNum()));
        data.setGroupAId(dataDto.getTeamOneId());
        data.setGroupAUserAHead(dataDto.getTeamOneUserOneHead());
        data.setGroupAUserAName(dataDto.getTeamOneUserOneName());
        data.setGroupAUserBHead(dataDto.getTeamOneUserTwoHead());
        data.setGroupAUserBName(dataDto.getTeamOneUserTwoName());
        data.setGroupBNumber(new Integer(dataDto.getTeamTwoNum()));
        data.setGroupBId(dataDto.getTeamTwoId());
        data.setGroupBUserAHead(dataDto.getTeamTwoUserOneHead());
        data.setGroupBUserAName(dataDto.getTeamTwoUserOneName());
        data.setGroupBUserBHead(dataDto.getTeamTwoUserTwoHead());
        data.setGroupBUserBName(dataDto.getTeamTwoUserTwoName());
        data.setId(dataDto.getId());
        return data;
    }

}
