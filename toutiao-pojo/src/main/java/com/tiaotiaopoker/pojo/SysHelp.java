package com.tiaotiaopoker.pojo;

import java.util.Date;

public class SysHelp {
    private String helpId;

    private String helpTitle;

    private Integer helpStatus;

    private String helpColor;

    private Integer helpSort;

    private Date helpCreateTime;

    private String helpCreateUser;

    private Date helpUpdateTime;

    private String helpUpdateUser;

    private String helpContent;

    public String getHelpId() {
        return helpId;
    }

    public void setHelpId(String helpId) {
        this.helpId = helpId == null ? null : helpId.trim();
    }

    public String getHelpTitle() {
        return helpTitle;
    }

    public void setHelpTitle(String helpTitle) {
        this.helpTitle = helpTitle == null ? null : helpTitle.trim();
    }

    public Integer getHelpStatus() {
        return helpStatus;
    }

    public void setHelpStatus(Integer helpStatus) {
        this.helpStatus = helpStatus;
    }

    public String getHelpColor() {
        return helpColor;
    }

    public void setHelpColor(String helpColor) {
        this.helpColor = helpColor == null ? null : helpColor.trim();
    }

    public Integer getHelpSort() {
        return helpSort;
    }

    public void setHelpSort(Integer helpSort) {
        this.helpSort = helpSort;
    }

    public Date getHelpCreateTime() {
        return helpCreateTime;
    }

    public void setHelpCreateTime(Date helpCreateTime) {
        this.helpCreateTime = helpCreateTime;
    }

    public String getHelpCreateUser() {
        return helpCreateUser;
    }

    public void setHelpCreateUser(String helpCreateUser) {
        this.helpCreateUser = helpCreateUser == null ? null : helpCreateUser.trim();
    }

    public Date getHelpUpdateTime() {
        return helpUpdateTime;
    }

    public void setHelpUpdateTime(Date helpUpdateTime) {
        this.helpUpdateTime = helpUpdateTime;
    }

    public String getHelpUpdateUser() {
        return helpUpdateUser;
    }

    public void setHelpUpdateUser(String helpUpdateUser) {
        this.helpUpdateUser = helpUpdateUser == null ? null : helpUpdateUser.trim();
    }

    public String getHelpContent() {
        return helpContent;
    }

    public void setHelpContent(String helpContent) {
        this.helpContent = helpContent == null ? null : helpContent.trim();
    }
}