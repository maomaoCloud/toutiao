package com.tiaotiaopoker.pojo;

import java.util.Date;

public class AdvertRecommend {
    private String advertId;

    private String advertTitle;

    private String advertLinkUrl;

    private String advertImgUrl;

    private Date advertCreateTime;

    private Date advertUpdateTime;

    private String advertCreateUser;

    private String advertUpdateUser;

    private String advertStartTime;

    private String advertEndTime;

    private String advertStartDate;

    private String advertEndDate;

    private Integer advertState;

    public String getAdvertUpdateUser() {
        return advertUpdateUser;
    }

    public void setAdvertUpdateUser(String advertUpdateUser) {
        this.advertUpdateUser = advertUpdateUser;
    }

    public String getAdvertStartTime() {
        return advertStartTime;
    }

    public void setAdvertStartTime(String advertStartTime) {
        this.advertStartTime = advertStartTime;
    }

    public String getAdvertEndTime() {
        return advertEndTime;
    }

    public void setAdvertEndTime(String advertEndTime) {
        this.advertEndTime = advertEndTime;
    }

    public String getAdvertStartDate() {
        return advertStartDate;
    }

    public void setAdvertStartDate(String advertStartDate) {
        this.advertStartDate = advertStartDate;
    }

    public String getAdvertEndDate() {
        return advertEndDate;
    }

    public void setAdvertEndDate(String advertEndDate) {
        this.advertEndDate = advertEndDate;
    }

    public String getAdvertId() {
        return advertId;
    }

    public void setAdvertId(String advertId) {
        this.advertId = advertId == null ? null : advertId.trim();
    }

    public String getAdvertTitle() {
        return advertTitle;
    }

    public void setAdvertTitle(String advertTitle) {
        this.advertTitle = advertTitle == null ? null : advertTitle.trim();
    }

    public String getAdvertLinkUrl() {
        return advertLinkUrl;
    }

    public void setAdvertLinkUrl(String advertLinkUrl) {
        this.advertLinkUrl = advertLinkUrl == null ? null : advertLinkUrl.trim();
    }

    public String getAdvertImgUrl() {
        return advertImgUrl;
    }

    public void setAdvertImgUrl(String advertImgUrl) {
        this.advertImgUrl = advertImgUrl == null ? null : advertImgUrl.trim();
    }

    public Date getAdvertCreateTime() {
        return advertCreateTime;
    }

    public void setAdvertCreateTime(Date advertCreateTime) {
        this.advertCreateTime = advertCreateTime;
    }

    public Date getAdvertUpdateTime() {
        return advertUpdateTime;
    }

    public void setAdvertUpdateTime(Date advertUpdateTime) {
        this.advertUpdateTime = advertUpdateTime;
    }

    public String getAdvertCreateUser() {
        return advertCreateUser;
    }

    public void setAdvertCreateUser(String advertCreateUser) {
        this.advertCreateUser = advertCreateUser == null ? null : advertCreateUser.trim();
    }

    public Integer getAdvertState() {
        return advertState;
    }

    public void setAdvertState(Integer advertState) {
        this.advertState = advertState;
    }
}