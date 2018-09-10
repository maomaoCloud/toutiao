package com.tiaotiaopoker.pojo;

public class MatchWithBLOBs extends Match {
    private String activeContent;

    private String rewardsContent;

    public String getActiveContent() {
        return activeContent;
    }

    public void setActiveContent(String activeContent) {
        this.activeContent = activeContent == null ? null : activeContent.trim();
    }

    public String getRewardsContent() {
        return rewardsContent;
    }

    public void setRewardsContent(String rewardsContent) {
        this.rewardsContent = rewardsContent == null ? null : rewardsContent.trim();
    }
}