package com.tiaotiaopoker.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.pojo.ApplyOrder;

/**
 * Created by xiekang on 2018/10/11.
 */
public class ApiSimpleUserInfo {
    private String  userId;
    private String  name;
    private String  phone;
    private String  head;
    private Boolean hasPartner;
    private String  userType;
    private String  partnerUserId;
    private String  partnerName;
    private String  partnerPhone;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Boolean getHasPartner() {
        return hasPartner;
    }

    public void setHasPartner(Boolean hasPartner) {
        this.hasPartner = hasPartner;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString( this );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "{}";
    }

    public String getPartnerUserId() {
        return partnerUserId;
    }

    public void setPartnerUserId(String partnerUserId) {
        this.partnerUserId = partnerUserId;
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

    public static ApiSimpleUserInfo genFromApplyOrder(ApplyOrder sd) {
        ApiSimpleUserInfo res = new ApiSimpleUserInfo();
        res.setUserId( sd.getUserId() );
        res.setName( sd.getUserName() );
        res.setPhone( sd.getUserPhone() );
        res.setHead( sd.getUserHead() );
        if( StringUtils.isBlank( res.getHead() ) ){
            res.setHead( "/images/head.png" );
        }
        return res;
    }

    public static ApiSimpleUserInfo genPartnerFromApplyOrder(ApplyOrder sd) {
        ApiSimpleUserInfo res = new ApiSimpleUserInfo();
        res.setUserId( sd.getPartnerId() );
        res.setPartnerUserId( sd.getPartnerId() );
        res.setPartnerName( sd.getPartnerName() );
        res.setPartnerPhone( sd.getPartnerPhone() );
        res.setHead( sd.getPartnerHead() );
        res.setName(sd.getPartnerName());
        if( StringUtils.isBlank( res.getHead() ) ){
            res.setHead( "/images/head.png" );
        }
        return res;
    }
}
