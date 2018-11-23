package com.tiaotiaopoker.entity;

import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.pojo.Match;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by xiekang on 2018/9/10.
 */
public class ApiMatchData {
    private String     id;
    private String     theme;
    private String     startDate;
    private String     location;
    private int        applyCount;
    private Integer    fee;
    private String     bannerImg;
    private boolean    hasApply;
    private String     matchState;
    private int        signUpNum;
    private int        signInNum;
    private BigDecimal sumMoney;
    private Boolean    isMine;
    private Boolean    hasLimit;
    private int        statue;

    public BigDecimal getSumMoney () {
        return sumMoney;
    }

    public void setSumMoney (BigDecimal sumMoney) {
        this.sumMoney = sumMoney;
    }

    public int getSignInNum () {
        return signInNum;
    }

    public void setSignInNum (int signInNum) {
        this.signInNum = signInNum;
    }

    public int getSignUpNum () {
        return signUpNum;
    }

    public void setSignUpNum (int signUpNum) {
        this.signUpNum = signUpNum;
    }

    public String getMatchState () {
        return matchState;
    }

    public void setMatchState (String matchState) {
        this.matchState = matchState;
    }

    public String getId () {
        return id;
    }

    public void setId (String id) {
        this.id = id;
    }

    public String getTheme () {
        return theme;
    }

    public void setTheme (String theme) {
        this.theme = theme;
    }

    public String getStartDate () {
        return startDate;
    }

    public void setStartDate (String startDate) {
        this.startDate = startDate;
    }

    public String getLocation () {
        return location;
    }

    public void setLocation (String location) {
        this.location = location;
    }

    public Integer getApplyCount () {
        return applyCount;
    }

    public void setApplyCount (Integer applyCount) {
        this.applyCount = applyCount;
    }

    public Integer getFee () {
        return fee;
    }

    public void setFee (Integer fee) {
        this.fee = fee;
    }

    public String getBannerImg () {
        return bannerImg;
    }

    public void setBannerImg (String bannerImg) {
        this.bannerImg = bannerImg;
    }

    public void setHasApply (boolean hasApply) {
        this.hasApply = hasApply;
    }

    public Boolean getHasApply () {
        return hasApply;
    }

    public Boolean getIsMine () {
        return isMine;
    }

    public void setIsMine (Boolean mine) {
        isMine = mine;
    }

    public Boolean getHasLimit () {
        return hasLimit;
    }

    public void setHasLimit (Boolean hasLimit) {
        this.hasLimit = hasLimit;
    }

    public int getStatue () {
        return statue;
    }

    public void setStatue (int statue) {
        this.statue = statue;
    }

    public static ApiMatchData genFromMatch (Match md,
                                             String myUserId) {
        ApiMatchData res = new ApiMatchData();
        res.setId(md.getId());
        res.setBannerImg(md.getBannerImg());
        res.setApplyCount(md.getApplyCount());
        res.setIsMine(md.getUserId().equals(myUserId));

        try {
            res.setFee((int) ((float) md.getFee()));
        } catch (Exception e) {
            res.setFee(0);
        }


        res.setHasLimit(md.getUserLimit() != null && !md.getUserLimit().equals(-1) && md.getApplyCount() >= md.getUserLimit());

        String location = md.getCity();
        location += md.getArea();
        res.setLocation(location);
        res.setStartDate(new DateTime(md.getStartDate()).toString("yyyy-MM-dd HH:mm"));
        res.setTheme(md.getTheme());

        //比赛状态
        DateTime nowDate   = new DateTime(new Date());
        DateTime startDate = new DateTime(md.getStartDate());
        DateTime endDate   = new DateTime(md.getEndDate());
        if (nowDate.compareTo(startDate) < 0) {
            res.setMatchState(Constants.Match.NOT_START);
            res.setStatue(Constants.Match.NOT_START_CODE);
        } else if (nowDate.compareTo(endDate) > 0) {
            res.setMatchState(Constants.Match.END);
            res.setStatue(Constants.Match.END_CODE);
        } else {
            res.setMatchState(Constants.Match.UNDER_WAY);
            res.setStatue(Constants.Match.UNDER_WAY_CODE);
        }

        return res;
    }


}

