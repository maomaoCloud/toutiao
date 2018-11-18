package com.tiaotiaopoker.entity;

import com.tiaotiaopoker.pojo.ApplyOrder;

public class ApiSignDetail {
    String userAId;

    String userAHead;

    String userAName;

    boolean userAHasSign; //true 表示userA已经签到，  false表示还未签到

    boolean hasPartner; //true 表示有搭档   false 表示没有搭档,则下列值可为空

    String userBId;

    String userBName;

    String userBHead;

    boolean userBHasHasSign;

    public String getUserAId() {
        return userAId;
    }

    public void setUserAId(String userAId) {
        this.userAId = userAId;
    }

    public String getUserAHead() {
        return userAHead;
    }

    public void setUserAHead(String userAHead) {
        this.userAHead = userAHead;
    }

    public String getUserAName() {
        return userAName;
    }

    public void setUserAName(String userAName) {
        this.userAName = userAName;
    }

    public boolean isUserAHasSign() {
        return userAHasSign;
    }

    public void setUserAHasSign(boolean userAHasSign) {
        this.userAHasSign = userAHasSign;
    }

    public boolean isHasPartner() {
        return hasPartner;
    }

    public void setHasPartner(boolean hasPartner) {
        this.hasPartner = hasPartner;
    }

    public String getUserBId() {
        return userBId;
    }

    public void setUserBId(String userBId) {
        this.userBId = userBId;
    }

    public String getUserBName() {
        return userBName;
    }

    public void setUserBName(String userBName) {
        this.userBName = userBName;
    }

    public String getUserBHead() {
        return userBHead;
    }

    public void setUserBHead(String userBHead) {
        this.userBHead = userBHead;
    }

    public boolean isUserBHasHasSign() {
        return userBHasHasSign;
    }

    public void setUserBHasHasSign(boolean userBHasHasSign) {
        this.userBHasHasSign = userBHasHasSign;
    }

    public static ApiSignDetail genFromApplyOrder(ApplyOrder order) {
        ApiSignDetail data = new ApiSignDetail();
        data.setUserAId(order.getUserId());
        data.setUserAHead(order.getUserHead());
        data.setUserAName(order.getUserName());
        data.setUserAHasSign(order.getUserSignStatus() == 1 ? true : false);
        data.setHasPartner(order.getHasPartner() == 1 ? true : false);
        if (data.isHasPartner()) {
            data.setUserBId(order.getPartnerId());
            data.setUserBName(order.getPartnerName());
            data.setUserBHead(order.getPartnerHead());
        }
        return data;
    }
}
