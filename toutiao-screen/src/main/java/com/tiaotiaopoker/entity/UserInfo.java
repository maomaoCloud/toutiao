package com.tiaotiaopoker.entity;

import com.tiaotiaopoker.pojo.ApplyOrder;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

/**
 * Created by xiekang on 2018/10/13.
 */
public class UserInfo {
    private String  userId;
    private String  name;
    private String  phone;
    private String  head;
    private String  applyDate;
    private String  groupName;
    private String  idCard;
    private String  signStatue;
    private boolean hasPartner;
    private String  partnerName;
    private String  partnerPhone;
    private String  partnerHead;
    private String  partnerIdCard;
    private String  partnerSignStatue;

    private String partnerId;

    private String orderId;

    public String getUserId () {
        return userId;
    }

    public void setUserId (String userId) {
        this.userId = userId;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getPhone () {
        return phone;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }

    public String getHead () {
        return head;
    }

    public void setHead (String head) {
        this.head = head;
    }

    public String getApplyDate () {
        return applyDate;
    }

    public void setApplyDate (String applyDate) {
        this.applyDate = applyDate;
    }

    public String getSignStatue () {
        return signStatue;
    }

    public void setSignStatue (String signStatue) {
        this.signStatue = signStatue;
    }

    public static UserInfo genFromApplyOrder (ApplyOrder order) {
        UserInfo info = new UserInfo();
        info.setOrderId(order.getId());
        info.setUserId(order.getUserId());
        info.setHead(StringUtils.isBlank(order.getUserHead()) ? "/images/head.png" : order.getUserHead());
        info.setName(order.getUserName());
        info.setPhone(order.getUserPhone());
        info.setApplyDate(new DateTime(order.getAddTime()).toString("yyyy-MM-dd HH:mm:ss"));
        if (order.getHasPartner().equals(1)){
            info.setPartnerName(order.getPartnerName());
            info.setPartnerPhone(order.getPartnerPhone());
            info.setPartnerSignStatue(getSignStatusName(order.getPartnerSignStatue()));
        }
        info.setSignStatue(getSignStatusName(order.getUserSignStatus()));
        info.setHasPartner(order.getHasPartner().equals(1));
        info.setGroupName(order.getGroupName());
        info.setIdCard(order.getUserIdCard());
        if (info.getHasPartner()) {
            info.setPartnerHead(order.getPartnerHead());
            if (StringUtils.isBlank(info.getPartnerHead())) {
                info.setPartnerHead("/images/head.png");
            }

            info.setPartnerIdCard(order.getPartnerIdCard());
            info.setPartnerName(order.getPartnerName());
            info.setPartnerPhone(order.getPartnerPhone());
            info.setPartnerSignStatue(getSignStatusName(order.getPartnerSignStatue()));
            info.setPartnerId(order.getPartnerId());
        }
        return info;
    }

    public static UserInfo genFromApplyOrderBUser (ApplyOrder order) {
        UserInfo info = new UserInfo();
        info.setOrderId(order.getId());
        info.setUserId(order.getPartnerId());
        info.setHead(StringUtils.isBlank(order.getPartnerHead()) ? "/images/head.png" : order.getPartnerHead());
        info.setName(order.getPartnerName());
        info.setPhone(order.getPartnerPhone());
        info.setApplyDate(new DateTime(order.getAddTime()).toString("yyyy-MM-dd HH:mm:ss"));
        info.setSignStatue(getSignStatusName(order.getUserSignStatus()));
        info.setHasPartner(order.getHasPartner().equals(1));
        info.setGroupName(order.getGroupName());
        info.setIdCard(order.getUserIdCard());
        if (info.getHasPartner()) {
            info.setPartnerHead(order.getPartnerHead());
            if (StringUtils.isBlank(info.getPartnerHead())) {
                info.setPartnerHead("/images/head.png");
            }

            info.setPartnerIdCard(order.getPartnerIdCard());
            info.setPartnerName(order.getPartnerName());
            info.setPartnerPhone(order.getPartnerPhone());
            info.setPartnerSignStatue(getSignStatusName(order.getPayStatue()));
            info.setPartnerId(order.getPartnerId());
        }
        return info;
    }

    private static String getSignStatusName (Integer signStatus) {
        String signStatue = "";
        switch (signStatus) {
            case -1:
                signStatue = "拒绝签到";
                break;
            case 0:
                signStatue = "未签到";
                break;
            case 1:
                signStatue = "已签到";
                break;
            case 2:
                signStatue = "比赛已结束";
                break;
            case 3:
                signStatue = "等待审核";
                break;
        }
        return signStatue;
    }

    public String getPartnerName () {
        return partnerName;
    }

    public void setPartnerName (String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerPhone () {
        return partnerPhone;
    }

    public void setPartnerPhone (String partnerPhone) {
        this.partnerPhone = partnerPhone;
    }

    public String getPartnerHead () {
        return partnerHead;
    }

    public void setPartnerHead (String partnerHead) {
        this.partnerHead = partnerHead;
    }

    public String getPartnerSignStatue () {
        return partnerSignStatue;
    }

    public void setPartnerSignStatue (String partnerSignStatue) {
        this.partnerSignStatue = partnerSignStatue;
    }

    public boolean getHasPartner () {
        return hasPartner;
    }

    public void setHasPartner (boolean hasPartner) {
        this.hasPartner = hasPartner;
    }


    public String getGroupName () {
        return groupName;
    }

    public void setGroupName (String groupName) {
        this.groupName = groupName;
    }

    public String getIdCard () {
        return idCard;
    }

    public void setIdCard (String idCard) {
        this.idCard = idCard;
    }

    public String getPartnerIdCard () {
        return partnerIdCard;
    }

    public void setPartnerIdCard (String partnerIdCard) {
        this.partnerIdCard = partnerIdCard;
    }

    public String getOrderId () {
        return orderId;
    }

    public void setOrderId (String orderId) {
        this.orderId = orderId;
    }

    public String getPartnerId () {
        return partnerId;
    }

    public void setPartnerId (String partnerId) {
        this.partnerId = partnerId;
    }

}
