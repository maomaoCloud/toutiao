package com.tiaotiaopoker.entity;

import com.tiaotiaopoker.pojo.AppUser;

/**
 * Created by xiekang on 2018/9/17.
 */
public class ApiAppUserInfo {
    private String id;
    private String openId;
    private String nickName;
    private String avatarUrl;
    private String trueName;
    private String phone;
    private String wxQrImg;
    private String userAddress;
    private String unionId;
    private String userIdCard;

    public String getId () {
        return id;
    }

    public void setId (String id) {
        this.id = id;
    }

    public String getOpenId () {
        return openId;
    }

    public void setOpenId (String openId) {
        this.openId = openId;
    }

    public String getNickName () {
        return nickName;
    }

    public void setNickName (String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl () {
        return avatarUrl;
    }

    public void setAvatarUrl (String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getTrueName () {
        return trueName;
    }

    public void setTrueName (String trueName) {
        this.trueName = trueName;
    }

    public String getPhone () {
        return phone;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }

    public String getWxQrImg () {
        return wxQrImg;
    }

    public void setWxQrImg (String wxQrImg) {
        this.wxQrImg = wxQrImg;
    }

    public String getUserAddress () {
        return userAddress;
    }

    public void setUserAddress (String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUnionId () {
        return unionId;
    }

    public void setUnionId (String unionId) {
        this.unionId = unionId;
    }

    public String getUserIdCard () {
        return userIdCard;
    }

    public void setUserIdCard (String userIdCard) {
        this.userIdCard = userIdCard;
    }

    public static ApiAppUserInfo genFromAppUser (AppUser appUser) {
        ApiAppUserInfo user = new ApiAppUserInfo();
        user.setId(appUser.getId());
        user.setOpenId(appUser.getOpenId());
        user.setNickName(appUser.getNickName());
        user.setAvatarUrl(appUser.getAvatarUrl());
        user.setTrueName(appUser.getTrueName());
        user.setPhone(appUser.getPhone());
        user.setWxQrImg(appUser.getWxQrImg());
        user.setUserAddress(appUser.getUserAddress());
        user.setUnionId(user.getUnionId());
        user.setUserIdCard(appUser.getIdCard());
        return user;
    }

}
