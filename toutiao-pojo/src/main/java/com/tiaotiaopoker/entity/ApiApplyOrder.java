package com.tiaotiaopoker.entity;

import com.tiaotiaopoker.Constants;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;

import java.util.Date;

public class ApiApplyOrder {

    private String orderId;
    private String matchId;
    private String theme;
    private String bannerImg;
    private String orderAddTime;
    private String orderState;
    private String matchState;
    private String address;
    private float payMoney;
    private int signUpNum;

    public int getSignUpNum() {
        return signUpNum;
    }

    public void setSignUpNum(int signUpNum) {
        this.signUpNum = signUpNum;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    public String getOrderAddTime() {
        return orderAddTime;
    }

    public void setOrderAddTime(String orderAddTime) {
        this.orderAddTime = orderAddTime;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getMatchState() {
        return matchState;
    }

    public void setMatchState(String matchState) {
        this.matchState = matchState;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(float payMoney) {
        this.payMoney = payMoney;
    }

    public static ApiApplyOrder genFromApplyOrderDto(ApplyOrderDto orderDto) {
        ApiApplyOrder apiOrder = new ApiApplyOrder();
        apiOrder.setOrderId(orderDto.getId());
        apiOrder.setMatchId(orderDto.getMatchId());
        apiOrder.setTheme(orderDto.getMatchTheme());
        apiOrder.setBannerImg(orderDto.getMatchBannerImg());
        apiOrder.setAddress(orderDto.getMatchAddress());
        apiOrder.setPayMoney(orderDto.getPayMoney().floatValue());
        apiOrder.setSignUpNum(orderDto.getHasPartner() + 1);
        apiOrder.setOrderAddTime(new DateTime(orderDto.getAddTime()).toString("yyyy-MM-dd HH:mm"));
        //比赛状态
        DateTime nowDate = new DateTime(new Date());
        DateTime startDate = new DateTime(orderDto.getMatchStartDate());
        DateTime endDate = new DateTime(orderDto.getMatchEndDate());
        if (nowDate.compareTo(startDate) < 0) {
            apiOrder.setMatchState(Constants.Match.NOT_START);
        } else if (nowDate.compareTo(endDate) > 0) {
            apiOrder.setMatchState(Constants.Match.END);
        } else {
            apiOrder.setMatchState(Constants.Match.UNDER_WAY);
        }
        //参赛状态
        if (orderDto.getUserSignStatus() == 0) {
            apiOrder.setOrderState(Constants.Order.NOT_SIGN_IN);
        } else if (orderDto.getUserSignStatus() == 1) {
            apiOrder.setOrderState(Constants.Order.SIGN_IN);
        } else {
            apiOrder.setOrderState(Constants.Order.END);
        }
        return apiOrder;
    }
}
