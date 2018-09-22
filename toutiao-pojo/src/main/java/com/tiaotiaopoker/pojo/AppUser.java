package com.tiaotiaopoker.pojo;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.StringUtil;

import java.util.Date;

public class AppUser {
    private String id;

    private String openId;

    private String nickName;

    private String avatarUrl;

    private String trueName;

    private String phone;

    private Date regDate;

    private Date updateInfoDate;

    private String wxQrImg;

    private Integer userStatus;

    private Integer userLevel;

    private Integer userFrom;

    private String userAddress;

    private String parentUserId;

    private Date lastLoginDateTime;

    private String unionId;

    private String idCard;

    private String sex;

    private String country;

    private String province;

    private String city;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getUpdateInfoDate() {
        return updateInfoDate;
    }

    public void setUpdateInfoDate(Date updateInfoDate) {
        this.updateInfoDate = updateInfoDate;
    }

    public String getWxQrImg() {
        return wxQrImg;
    }

    public void setWxQrImg(String wxQrImg) {
        this.wxQrImg = wxQrImg == null ? null : wxQrImg.trim();
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Integer getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(Integer userFrom) {
        this.userFrom = userFrom;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public String getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(String parentUserId) {
        this.parentUserId = parentUserId == null ? null : parentUserId.trim();
    }

    public Date getLastLoginDateTime() {
        return lastLoginDateTime;
    }

    public void setLastLoginDateTime(Date lastLoginDateTime) {
        this.lastLoginDateTime = lastLoginDateTime;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId == null ? null : unionId.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public static AppUser genFromJsonData(String userInfoJsonData,
                                          String userId) {
        AppUser user = new AppUser();
        user.setId( userId );
        //Object jo = JSONObject.parse( userInfoJsonData );
        JSONObject jo = JSONObject.parseObject( userInfoJsonData );
        String openId = jo.getString( "openId" );
        String nickName = jo.getString( "nickName" );
        int gender = jo.getInteger( "gender" );
        String city = jo.getString( "city" );
        String province = jo.getString( "province" );
        String country = jo.getString( "country" );
        String avatarUrl = jo.getString( "avatarUrl" );
        String unionId = jo.getString( "unionId" );

        user.setOpenId( openId );
        user.setNickName( nickName );
        String sex;
        switch (gender) {
            case 0:
                sex = "未知";
                break;
            case 1:
                sex = "男";
                break;
            case 2:
                sex = "女";
                break;
            default:
                sex = "未知";
        }

        user.setSex( sex );
        user.setAvatarUrl( avatarUrl );
        user.setUnionId( unionId );
        StringBuffer sbAddress = new StringBuffer();
        if( StringUtil.isNotEmpty( province ) ) {
            sbAddress.append( province );
        }

        if( StringUtil.isNotEmpty( city ) ) {
            sbAddress.append( city );
        }

        user.setUserAddress( sbAddress.toString() );
        user.setProvince( province );
        user.setCity( city );
        user.setCountry( country );

        return user;
    }
}