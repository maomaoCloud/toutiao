package com.tiaotiaopoker.entity;

import com.tiaotiaopoker.pojo.ApplyOrder;

import java.util.Date;

public class ApplyOrderDto extends ApplyOrder {

    private String matchTheme;

    private String matchAddress;

    private String matchBannerImg;

    private Date matchStartDate;

    private Date matchEndDate;

    public Date getMatchStartDate() {
        return matchStartDate;
    }

    public void setMatchStartDate(Date matchStartDate) {
        this.matchStartDate = matchStartDate;
    }

    public Date getMatchEndDate() {
        return matchEndDate;
    }

    public void setMatchEndDate(Date matchEndDate) {
        this.matchEndDate = matchEndDate;
    }

    public String getMatchTheme() {
        return matchTheme;
    }

    public void setMatchTheme(String matchTheme) {
        this.matchTheme = matchTheme;
    }

    public String getMatchAddress() {
        return matchAddress;
    }

    public void setMatchAddress(String matchAddress) {
        this.matchAddress = matchAddress;
    }

    public String getMatchBannerImg() {
        return matchBannerImg;
    }

    public void setMatchBannerImg(String matchBannerImg) {
        this.matchBannerImg = matchBannerImg;
    }
}
