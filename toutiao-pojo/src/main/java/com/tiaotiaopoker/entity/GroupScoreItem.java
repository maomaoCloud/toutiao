package com.tiaotiaopoker.entity;

import java.util.List;

public class GroupScoreItem extends MatchTeamResultDto {
    private String                   groupName;
    private List<MatchTeamResultDto> data;

    public List<MatchTeamResultDto> getData () {
        return data;
    }

    public void setData (List<MatchTeamResultDto> data) {
        this.data = data;
    }

    public String getGroupName () {
        return groupName;
    }

    public void setGroupName (String groupName) {
        this.groupName = groupName;
    }

}
