package com.tiaotiaopoker.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class ApplyOrder {
    private String id;

    private String userId;

    private String userName;

    private String userPhone;

    private String partnerId;

    private String partnerName;

    private String partnerPhone;

    private Integer hasPartner;

    private BigDecimal price;

    private BigDecimal payMoney;

    private String matchId;

    private String userHead;

    private String partnerHead;

    private Date addTime;

    private Integer payStatue;

    private BigDecimal sharePercent;

    private Date payDate;

    private Integer userSignStatus;

    private Integer partnerSignStatue;

    private String patnerCode;

    private Date userSignDatetime;

    private Date partnerSignDatetime;

    private String userIdCard;

    private String partnerIdCard;

    private String groupName;

    private String companyName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId == null ? null : partnerId.trim();
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName == null ? null : partnerName.trim();
    }

    public String getPartnerPhone() {
        return partnerPhone;
    }

    public void setPartnerPhone(String partnerPhone) {
        this.partnerPhone = partnerPhone == null ? null : partnerPhone.trim();
    }

    public Integer getHasPartner() {
        return hasPartner;
    }

    public void setHasPartner(Integer hasPartner) {
        this.hasPartner = hasPartner;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId == null ? null : matchId.trim();
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead == null ? null : userHead.trim();
    }

    public String getPartnerHead() {
        return partnerHead;
    }

    public void setPartnerHead(String partnerHead) {
        this.partnerHead = partnerHead == null ? null : partnerHead.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getPayStatue() {
        return payStatue;
    }

    public void setPayStatue(Integer payStatue) {
        this.payStatue = payStatue;
    }

    public BigDecimal getSharePercent() {
        return sharePercent;
    }

    public void setSharePercent(BigDecimal sharePercent) {
        this.sharePercent = sharePercent;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Integer getUserSignStatus() {
        return userSignStatus;
    }

    public void setUserSignStatus(Integer userSignStatus) {
        this.userSignStatus = userSignStatus;
    }

    public Integer getPartnerSignStatue() {
        return partnerSignStatue;
    }

    public void setPartnerSignStatue(Integer partnerSignStatue) {
        this.partnerSignStatue = partnerSignStatue;
    }

    public String getPatnerCode() {
        return patnerCode;
    }

    public void setPatnerCode(String patnerCode) {
        this.patnerCode = patnerCode == null ? null : patnerCode.trim();
    }

    public Date getUserSignDatetime() {
        return userSignDatetime;
    }

    public void setUserSignDatetime(Date userSignDatetime) {
        this.userSignDatetime = userSignDatetime;
    }

    public Date getPartnerSignDatetime() {
        return partnerSignDatetime;
    }

    public void setPartnerSignDatetime(Date partnerSignDatetime) {
        this.partnerSignDatetime = partnerSignDatetime;
    }

    public String getUserIdCard() {
        return userIdCard;
    }

    public void setUserIdCard(String userIdCard) {
        this.userIdCard = userIdCard == null ? null : userIdCard.trim();
    }

    public String getPartnerIdCard() {
        return partnerIdCard;
    }

    public void setPartnerIdCard(String partnerIdCard) {
        this.partnerIdCard = partnerIdCard == null ? null : partnerIdCard.trim();
    }

    public String getGroupName () {
        return groupName;
    }

    public void setGroupName (String groupName) {
        this.groupName = groupName;
    }

    public String getCompanyName () {
        return companyName;
    }

    public void setCompanyName (String companyName) {
        this.companyName = companyName;
    }

}