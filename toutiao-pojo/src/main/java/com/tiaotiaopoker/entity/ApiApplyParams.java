package com.tiaotiaopoker.entity;

/**
 * Created by xiekang on 2018/9/19.
 */
public class ApiApplyParams {
    private String  userId;
    private Integer applyCount;
    private String  matchId;
    private String  userName;
    private String  userPhone;
    private String  partnerName;
    private String  partnerPhone;
    private String  userHead;
    private String  partnerHead;
    private Boolean hasPartner;
    private String  requestIp;
    private String  openId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(Integer applyCount) {
        this.applyCount = applyCount;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerPhone() {
        return partnerPhone;
    }

    public void setPartnerPhone(String partnerPhone) {
        this.partnerPhone = partnerPhone;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getPartnerHead() {
        return partnerHead;
    }

    public void setPartnerHead(String partnerHead) {
        this.partnerHead = partnerHead;
    }

    public Boolean getHasPartner() {
        return hasPartner;
    }

    public void setHasPartner(Boolean hasPartner) {
        this.hasPartner = hasPartner;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
