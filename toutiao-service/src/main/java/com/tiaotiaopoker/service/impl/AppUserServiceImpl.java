package com.tiaotiaopoker.service.impl;

import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.dao.*;
import com.tiaotiaopoker.entity.ApiAdvertData;
import com.tiaotiaopoker.entity.ApiApplyOrder;
import com.tiaotiaopoker.entity.ApiMatchData;
import com.tiaotiaopoker.entity.ApplyOrderDto;
import com.tiaotiaopoker.pojo.*;
import com.tiaotiaopoker.service.AppUserService;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by xiekang on 2018/9/17.
 */
@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserMapper         appUserMapper;
    @Autowired
    private AdvertRecommendMapper advertRecommendMapper;
    @Autowired
    private ApplyOrderMapper      applyOrderMapper;
    @Autowired
    private MatchMapper           matchMapper;
    @Autowired
    private WithdrawLogMapper     withdrawLogMapper;

    @Override
    public AppUser getUserByOpenId (String openId) {
        AppUserExample example = new AppUserExample();
        example.createCriteria().andOpenIdEqualTo(openId);

        List<AppUser> appUsers = appUserMapper.selectByExample(example);
        if (appUsers != null && appUsers.size() > 0) return appUsers.get(0);

        return null;
    }

    @Override
    public void addAppUser (AppUser appUser) {
        appUserMapper.insertSelective(appUser);
    }

    @Override
    public AppUser updateAppUserInfo (AppUser user) {
        user.setUpdateInfoDate(new Date());
        user.setLastLoginDateTime(new Date());
        appUserMapper.updateByPrimaryKeySelective(user);

        if (StringUtils.isNotBlank(user.getAvatarUrl())) {
            //更新用户头像
            ApplyOrderExample example = new ApplyOrderExample();
            example.createCriteria().andUserIdEqualTo(user.getId());

            ApplyOrder order = new ApplyOrder();
            order.setUserHead(user.getAvatarUrl());
            applyOrderMapper.updateByExampleSelective(order, example);
        }

        return appUserMapper.selectByPrimaryKey(user.getId());
    }

    @Override
    public void updateUserApplyInfo (String userId,
                                     String trueName,
                                     String userPhone,
                                     String userIdCard) {
        AppUser user = new AppUser();
        user.setId(userId);
        user.setTrueName(trueName);
        user.setPhone(userPhone);
        user.setIdCard(userIdCard);
        appUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public AppUser getUserByUserId (String userId) {
        return appUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public Map<String, Object> getUserCenterInfo (String userId) {
        Map<String, Object> resultMap = new HashMap<>();
        //我的报名(已支付)
        List<ApplyOrderDto>      orderList = applyOrderMapper.queryOrderListByUserId(userId);
        ArrayList<ApiApplyOrder> notStart  = new ArrayList<>();
        ArrayList<ApiApplyOrder> underWay  = new ArrayList<>();
        ArrayList<ApiApplyOrder> end       = new ArrayList<>();
        ArrayList<ApiApplyOrder> all       = new ArrayList<>();
        Map<String, Object>      orderMap  = new HashMap<>();
        for (ApplyOrderDto orderDto : orderList) {
            ApiApplyOrder apiOrder = ApiApplyOrder.genFromApplyOrderDto(orderDto);
            all.add(apiOrder);
            if (Constants.Match.NOT_START.equals(apiOrder.getMatchState())) {
                notStart.add(apiOrder);
            } else if (Constants.Match.UNDER_WAY.equals(apiOrder.getMatchState())) {
                underWay.add(apiOrder);
            } else {
                end.add(apiOrder);
            }
        }
        //全部
        orderMap.put("all", all);
        //未开始
        orderMap.put("notStart", notStart);
        //进行中
        orderMap.put("underWay", underWay);
        //已结束
        orderMap.put("end", end);
        resultMap.put("myOrder", orderMap);

        //我的发布
        MatchExample          matchExample         = new MatchExample();
        MatchExample.Criteria matchExampleCriteria = matchExample.createCriteria();
        matchExampleCriteria.andUserIdEqualTo(userId);
        ArrayList<ApiMatchData> notStartMatch = new ArrayList<>();
        ArrayList<ApiMatchData> underWayMatch = new ArrayList<>();
        ArrayList<ApiMatchData> endMatch      = new ArrayList<>();
        ArrayList<ApiMatchData> allMatch      = new ArrayList<>();
        List<Match>             matchList     = matchMapper.selectByExample(matchExample);
        Map<String, Object>     matchMap      = new HashMap<>();
        for (Match match : matchList) {
            ApiMatchData apiMatch  = ApiMatchData.genFromMatch(match, userId);
            int          signUpNum = applyOrderMapper.sumSignUpNumByMatchId(match.getId());
            apiMatch.setSignUpNum(signUpNum);
            allMatch.add(apiMatch);
            if (Constants.Match.NOT_START.equals(apiMatch.getMatchState())) {
                notStartMatch.add(apiMatch);
            } else if (Constants.Match.UNDER_WAY.equals(apiMatch.getMatchState())) {
                underWayMatch.add(apiMatch);
            } else {
                endMatch.add(apiMatch);
            }
        }
        //全部
        matchMap.put("all", allMatch);
        //未开始
        matchMap.put("notStart", notStartMatch);
        //进行中
        matchMap.put("underWay", underWayMatch);
        //已结束
        matchMap.put("end", end);
        resultMap.put("myPublish", matchMap);

        //当前时间，日期范围内的广告
        Map<String, Object> paramMap = new HashMap<>();
        DateTime            dateTime = new DateTime();
        paramMap.put("date", dateTime.toString("yyyy-MM-dd"));
        paramMap.put("time", dateTime.toString("HH:mm"));
        List<AdvertRecommend> advertList     = advertRecommendMapper.queryAdvertByCondition(paramMap);
        ArrayList<Object>     advertDataList = new ArrayList<>();
        for (AdvertRecommend advert : advertList) {
            advertDataList.add(ApiAdvertData.genFromAdvert(advert));
        }
        resultMap.put("advertList", advertDataList);
        return resultMap;
    }

    @Override
    public Map<String, Object> getUserIncome (String userId,
                                              String matchId) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> paramMap  = new HashMap<>();
        BigDecimal          hundred   = new BigDecimal(100);
        //累计收益
        paramMap.put("userId", userId);
        BigDecimal sumPayMoney = applyOrderMapper.sumPayMoneyByCondition(paramMap);
        if (!"0".equals(matchId)) {
            //需要查出该用户本场比赛收益
            paramMap.put("matchId", matchId);
            BigDecimal sumPayMoneyByMatch = applyOrderMapper.sumPayMoneyByCondition(paramMap);
            resultMap.put("sumMoneyMatch", sumPayMoneyByMatch.divide(hundred));
            resultMap.put("sumMoney", sumPayMoney.divide(hundred));
        }

        paramMap.clear();
        paramMap.put("userId", userId);
        paramMap.put("today", new DateTime(new Date()).toString("yyyy-MM-dd"));
        //今日报名数
        int sumSignUpToday = applyOrderMapper.sumSignUpNumByCondition(paramMap);
        //今日报名费
        BigDecimal sumPayMoneyToday = applyOrderMapper.sumPayMoneyByCondition(paramMap);
        //已提现
        int alreadyWithdraw = withdrawLogMapper.sumMoneyByUserId(userId);
        //累计可提现收益
        paramMap.put("signState", Constants.Order.USER_SIGN_STATUS_END);
        BigDecimal sumAvailableWithdraw = applyOrderMapper.sumPayMoneyByCondition(paramMap);
        //可提现
        BigDecimal availableWithdraw = (sumAvailableWithdraw.subtract(new BigDecimal(alreadyWithdraw))).divide(
                hundred);
        resultMap.put("sumSignUpToday", sumSignUpToday);
        resultMap.put("sumMoneyToday", sumPayMoneyToday.divide(hundred));
        resultMap.put("availableWithdraw", availableWithdraw);
        return resultMap;
    }

    @Override
    public List<AppUser> getUserByTrueName(String trueName) {
        return appUserMapper.getUserByTrueName(trueName);
    }

    /**
     * 导入用户同时生成比赛订单
     * Created by maojian
     *
     * @date 2019/2/22 15:11
     */
    @Transactional
    @Override
    public void importUserWithOrder(List<List<String>> dataList, String matchId) {
        for (List<String> data : dataList) {
            //创建appUser
            AppUser appUser = genImportUser(data.get(0), data.get(0), data.get(1));
            appUserMapper.insertSelective(appUser);

            //判断是否带搭档
            AppUser partnerUser = null;
            if (data.get(4).equals("1")) {
                //创建appUser搭档
                partnerUser = genImportUser(data.get(2), data.get(2), data.get(3));
                appUserMapper.insertSelective(partnerUser);
            }

            //生成比赛订单
            ApplyOrder applyOrder = new ApplyOrder();
            applyOrder.setId(com.tiaotiaopoker.StringUtils.generateOrderNo());
            applyOrder.setUserId(appUser.getId());
            applyOrder.setUserName(data.get(0));
            applyOrder.setUserPhone(data.get(1));
            applyOrder.setPartnerId(partnerUser == null ? null : partnerUser.getId());
            applyOrder.setPartnerName(partnerUser == null ? null : partnerUser.getNickName());
            applyOrder.setPartnerPhone(partnerUser == null ? null : partnerUser.getPhone());
            applyOrder.setHasPartner(new Integer(data.get(4)));
            applyOrder.setMatchId(matchId);
            applyOrder.setUserHead(appUser.getAvatarUrl());
            applyOrder.setPartnerHead(partnerUser == null ? null : partnerUser.getAvatarUrl());
            applyOrder.setAddTime(new Date());
            //支付和签到状态
            applyOrder.setPrice(new BigDecimal(0));
            applyOrder.setPayMoney(new BigDecimal(0));
            applyOrder.setSharePercent(new BigDecimal(0));
            applyOrder.setPayDate(new Date());
            applyOrder.setPayStatue(1);
            applyOrder.setUserSignStatus(new Integer(data.get(7)));
            applyOrder.setPartnerSignStatue(partnerUser == null ? null : new Integer(data.get(7)));
            applyOrder.setUserSignDatetime(new Date());
            applyOrder.setPartnerSignDatetime(partnerUser == null ? null : new Date());
            //团队公司信息
            applyOrder.setGroupName(data.get(5));
            applyOrder.setCompanyName(data.get(6));
            applyOrderMapper.insertSelective(applyOrder);

        }
    }

    public static AppUser genImportUser(String nickName, String trueName, String phone) {
        AppUser appUser = new AppUser();
        appUser.setId(com.tiaotiaopoker.StringUtils.generateShortUUID());
        appUser.setNickName(nickName);
        appUser.setTrueName(trueName);
        appUser.setRegDate(new Date());
        //生成头像
        appUser.setAvatarUrl("https://match.tiaotiaopoker.com/tools/placeholder/200x200/bgRGB_CCCCCC/textRGB_000000/fontSize_60/"+trueName);
        appUser.setPhone(phone);
        appUser.setUserStatus(0);
        appUser.setUserLevel(0);
        //0——小程序，1——导入用户
        appUser.setUserFrom(0);
        appUser.setLastLoginDateTime(new Date());
        return appUser;
    }

}
