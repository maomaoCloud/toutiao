package com.tiaotiaopoker.entity;

import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.pojo.Match;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by xiekang on 2018/9/10.
 */
public class ApiMatchData {
    private String  id;
    private String  theme;
    private String  startDate;
    private String  location;
    private int     applyCount;
    private Integer fee;
    private String  bannerImg;
    private boolean hasApply;
    private String matchState;
    private int signUpNum;
    private int signInNum;
    private int sumMoney;

    public int getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(int sumMoney) {
        this.sumMoney = sumMoney;
    }

    public int getSignInNum() {
        return signInNum;
    }

    public void setSignInNum(int signInNum) {
        this.signInNum = signInNum;
    }

    public int getSignUpNum() {
        return signUpNum;
    }

    public void setSignUpNum(int signUpNum) {
        this.signUpNum = signUpNum;
    }

    public String getMatchState() {
        return matchState;
    }

    public void setMatchState(String matchState) {
        this.matchState = matchState;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(Integer applyCount) {
        this.applyCount = applyCount;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    public void setHasApply(boolean hasApply) {
        this.hasApply = hasApply;
    }

    public Boolean getHasApply() {
        return hasApply;
    }

    public static ApiMatchData genFromMatch(Match md) {
        ApiMatchData res = new ApiMatchData();
        res.setId( md.getId() );
        res.setBannerImg( md.getBannerImg() );
        res.setApplyCount( md.getApplyCount() );

        try {

            res.setFee( (int) ( (float) md.getFee() ) );
        } catch (Exception e) {
            res.setFee( 0 );
        }

        String location = md.getProvince();
        if( !md.getProvince().equals( md.getCity() ) ) {
            location += md.getCity();
        }
        location += md.getArea();
        res.setLocation( location );
        res.setStartDate( new DateTime( md.getStartDate() ).toString( "yyyy-MM-dd HH:mm" ) );
        res.setTheme( md.getTheme() );

        //比赛状态
        DateTime nowDate = new DateTime(new Date());
        DateTime startDate = new DateTime(md.getStartDate());
        DateTime endDate = new DateTime(md.getEndDate());
        if (nowDate.compareTo(startDate) < 0) {
            res.setMatchState(Constants.Match.NOT_START);
        } else if (nowDate.compareTo(endDate) > 0) {
            res.setMatchState(Constants.Match.END);
        } else {
            res.setMatchState(Constants.Match.UNDER_WAY);
        }

        return res;
    }
}

