package com.tiaotiaopoker.entity;

import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.pojo.ApplyOrder;
import org.joda.time.DateTime;

/**
 * Created by xiekang on 2018/10/13.
 */
public class UserInfo {
    private String userId;
    private String name;
    private String phone;
    private String head;
    private String applyDate;
    private String signStatue;

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

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getSignStatue() {
        return signStatue;
    }

    public void setSignStatue(String signStatue) {
        this.signStatue = signStatue;
    }

    public static UserInfo genFromApplyOrder(ApplyOrder order) {
        UserInfo info = new UserInfo();
        info.setUserId( order.getUserId() );
        info.setHead( StringUtils.isBlank( order.getUserHead() ) ? "/images/head.png" : order.getUserHead() );
        info.setName( order.getUserName() );
        info.setPhone( order.getUserPhone() );
        info.setApplyDate( new DateTime( order.getAddTime() ).toString( "yyyy-MM-dd HH:mm:ss" ) );
        info.setSignStatue( getSignStatusName( order.getUserSignStatus() ) );
        return info;
    }

    public static UserInfo genFromApplyOrderBUser(ApplyOrder order) {
        UserInfo info = new UserInfo();
        info.setUserId( order.getPartnerId() );
        info.setHead( StringUtils.isBlank( order.getPartnerHead() ) ? "/images/head.png" : order.getPartnerHead() );
        info.setName( order.getPartnerName() );
        info.setPhone( order.getPartnerPhone() );
        info.setApplyDate( new DateTime( order.getAddTime() ).toString( "yyyy-MM-dd HH:mm:ss" ) );
        info.setSignStatue( getSignStatusName( order.getPartnerSignStatue() ) );
        return info;
    }

    private static String getSignStatusName(Integer signStatus) {
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
}
