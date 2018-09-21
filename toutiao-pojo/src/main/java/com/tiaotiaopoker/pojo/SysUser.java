package com.tiaotiaopoker.pojo;

import java.util.Date;

public class SysUser {
    private String userId;

    private String userAccount;

    private String userPassword;

    private Integer userSex;

    private String userMobilePhone;

    private String userEmail;

    private Integer userState;

    private String userCreateUser;

    private Date userCreateTime;

    private String userUpdateUser;

    private Date userUpdateTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getUserMobilePhone() {
        return userMobilePhone;
    }

    public void setUserMobilePhone(String userMobilePhone) {
        this.userMobilePhone = userMobilePhone == null ? null : userMobilePhone.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public String getUserCreateUser() {
        return userCreateUser;
    }

    public void setUserCreateUser(String userCreateUser) {
        this.userCreateUser = userCreateUser == null ? null : userCreateUser.trim();
    }

    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public String getUserUpdateUser() {
        return userUpdateUser;
    }

    public void setUserUpdateUser(String userUpdateUser) {
        this.userUpdateUser = userUpdateUser == null ? null : userUpdateUser.trim();
    }

    public Date getUserUpdateTime() {
        return userUpdateTime;
    }

    public void setUserUpdateTime(Date userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }
}