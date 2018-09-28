package com.tiaotiaopoker.entity;

import com.tiaotiaopoker.pojo.MatchWithBLOBs;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiekang on 2018/9/14.
 */
public class ApiMatchDetail {
    private String headImg, theme, startDateTime, endDateTime, address, activeContent, rewardsContent, contactName, contactPhone, wxHead, id;
    private Float                fee;
    private List<MatchApplyUser> applyList;
    private Boolean              hasApply;
    private Boolean              isMine;//当前比赛是否是我自己发布的，如果是我自己发布的，就不能报名

    public ApiMatchDetail() {
        super();
    }

    public ApiMatchDetail(List<MatchApplyUser> applyList) {
        this.applyList = applyList;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTheme() {
        return theme;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getActiveContent() {
        return activeContent;
    }

    public void setActiveContent(String activeContent) {
        this.activeContent = activeContent;
    }

    public String getRewardsContent() {
        return rewardsContent;
    }

    public void setRewardsContent(String rewardsContent) {
        this.rewardsContent = rewardsContent;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getWxHead() {
        return wxHead;
    }

    public void setWxHead(String wxHead) {
        this.wxHead = wxHead;
    }

    public Float getFee() {
        return fee;
    }

    public void setFee(Float fee) {
        this.fee = fee;
    }

    public List<MatchApplyUser> getApplyList() {
        return applyList;
    }

    public void setApplyList(List<MatchApplyUser> applyList) {
        this.applyList = applyList;
    }

    public static ApiMatchDetail genFromMatch(MatchWithBLOBs m) {
        ApiMatchDetail data = new ApiMatchDetail();
        data.setHeadImg( m.getBannerImg() );
        data.setActiveContent( m.getActiveContent() );
        data.setContactName( m.getContactName() );
        data.setContactPhone( m.getPhone() );
        data.setStartDateTime( new DateTime( m.getStartDate() ).toString( "yyyy-MM-dd HH:mm" ) );
        data.setEndDateTime( new DateTime( m.getEndDate() ).toString( "yyyy-MM-dd HH:mm" ) );
        data.setFee( m.getFee() );
        data.setRewardsContent( m.getRewardsContent() );
        data.setWxHead( m.getWxHead() );
        data.setTheme( m.getTheme() );
        data.setId( m.getId() );

        StringBuffer sbStress = new StringBuffer();
        sbStress.append( m.getProvince() ).append( m.getCity() ).append( m.getArea() ).append( m.getAddress() );
        data.setAddress( sbStress.toString() );

        return data;
    }

    public Boolean getHasApply() {
        return hasApply;
    }

    public void setHasApply(Boolean hasApply) {
        this.hasApply = hasApply;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsMine() {
        return isMine;
    }

    public void setIsMine(Boolean mine) {
        isMine = mine;
    }
}
