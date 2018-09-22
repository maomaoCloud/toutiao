package com.tiaotiaopoker.pojo;

import java.util.Date;

public class ProfitPercent {
    private String profitId;

    private Integer profitHighLine;

    private Integer profitDownLine;

    private Date profitCreateTime;

    private String profitCreateUser;

    private Date profitUpdateTime;

    private String profitUpdateUser;

    private Integer profitPercent;

    private Integer profitState;

    public String getProfitId() {
        return profitId;
    }

    public void setProfitId(String profitId) {
        this.profitId = profitId == null ? null : profitId.trim();
    }

    public Integer getProfitHighLine() {
        return profitHighLine;
    }

    public void setProfitHighLine(Integer profitHighLine) {
        this.profitHighLine = profitHighLine;
    }

    public Integer getProfitDownLine() {
        return profitDownLine;
    }

    public void setProfitDownLine(Integer profitDownLine) {
        this.profitDownLine = profitDownLine;
    }

    public Date getProfitCreateTime() {
        return profitCreateTime;
    }

    public void setProfitCreateTime(Date profitCreateTime) {
        this.profitCreateTime = profitCreateTime;
    }

    public String getProfitCreateUser() {
        return profitCreateUser;
    }

    public void setProfitCreateUser(String profitCreateUser) {
        this.profitCreateUser = profitCreateUser == null ? null : profitCreateUser.trim();
    }

    public Date getProfitUpdateTime() {
        return profitUpdateTime;
    }

    public void setProfitUpdateTime(Date profitUpdateTime) {
        this.profitUpdateTime = profitUpdateTime;
    }

    public String getProfitUpdateUser() {
        return profitUpdateUser;
    }

    public void setProfitUpdateUser(String profitUpdateUser) {
        this.profitUpdateUser = profitUpdateUser == null ? null : profitUpdateUser.trim();
    }

    public Integer getProfitPercent() {
        return profitPercent;
    }

    public void setProfitPercent(Integer profitPercent) {
        this.profitPercent = profitPercent;
    }

    public Integer getProfitState() {
        return profitState;
    }

    public void setProfitState(Integer profitState) {
        this.profitState = profitState;
    }
}