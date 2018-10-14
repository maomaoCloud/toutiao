package com.tiaotiaopoker.service.impl;

import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.dao.*;
import com.tiaotiaopoker.entity.MatchTeamDataDto;
import com.tiaotiaopoker.pojo.*;
import com.tiaotiaopoker.service.MatchTeamDataService;
import com.tiaotiaopoker.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class MatchTeamDataServiceImpl implements MatchTeamDataService {

    @Autowired
    private MatchTeamDataMapper matchTeamDataMapper;

    @Autowired
    private MatchTeamMemberMapper matchTeamMemberMapper;

    @Autowired
    private ApplyOrderMapper applyOrderMapper;

    @Autowired
    private MatchRuleMapper matchRuleMapper;

    @Autowired
    private MatchTeamResultMapper matchTeamResultMapper;

    @Override
    public List<AppUser> sortMatchTeamByRuleSeat(Integer ruleSeat, String matchId) {
        //查出订单中携带搭档且两人状态都为已签到
        ApplyOrderExample orderExampleSignBoth = new ApplyOrderExample();
        ApplyOrderExample.Criteria criteriaSignBoth = orderExampleSignBoth.createCriteria();
        criteriaSignBoth.andMatchIdEqualTo(matchId).andUserSignStatusEqualTo(Constants.Order.USER_SIGN_STATUS_END).andPartnerSignStatueEqualTo(Constants.Order.USER_SIGN_STATUS_END);
        orderExampleSignBoth.setOrderByClause("add_time asc");
        List<ApplyOrder> userListSignBoth = applyOrderMapper.selectByExample(orderExampleSignBoth);

        //查出订单中携带搭档但是仅有一人签到或者不携带搭档
        ApplyOrderExample orderExampleSignUser = new ApplyOrderExample();
        ApplyOrderExample.Criteria criteriaSignUser = orderExampleSignUser.createCriteria();
        criteriaSignUser.andMatchIdEqualTo(matchId).andUserSignStatusEqualTo(Constants.Order.USER_SIGN_STATUS_END).andPartnerSignStatueNotEqualTo(Constants.Order.USER_SIGN_STATUS_END);
        orderExampleSignUser.setOrderByClause("add_time asc");
        List<ApplyOrder> userListSignUser = applyOrderMapper.selectByExample(orderExampleSignUser);

        //查出订单中携带搭档但是仅搭档签到
        ApplyOrderExample orderExampleSignPartner = new ApplyOrderExample();
        ApplyOrderExample.Criteria criteriaSignPartner = orderExampleSignPartner.createCriteria();
        criteriaSignPartner.andMatchIdEqualTo(matchId).andUserSignStatusNotEqualTo(Constants.Order.USER_SIGN_STATUS_END).andPartnerSignStatueEqualTo(Constants.Order.USER_SIGN_STATUS_END);
        orderExampleSignPartner.setOrderByClause("add_time asc");
        List<ApplyOrder> userListSignPartner = applyOrderMapper.selectByExample(orderExampleSignPartner);

        List<ApplyOrder> orderList = new ArrayList<>();
        if (userListSignBoth != null && userListSignBoth.size() > 0) {

            if (ruleSeat.equals(Constants.ruleSeat.SORT_NEAR)) {
                orderList = userListSignBoth;
            } else if (ruleSeat.equals(Constants.ruleSeat.SORT_BEGIN_END)) {
                //首位组合
                for (int i = 0; i < userListSignBoth.size() / 2; i++) {
                    orderList.add(userListSignBoth.get(i));
                    orderList.add(userListSignBoth.get(userListSignBoth.size() - i));
                }
                if (userListSignBoth.size() % 2 == 1) {
                    orderList.add(userListSignBoth.get(userListSignBoth.size() / 2));
                }
            } else if (ruleSeat.equals(Constants.ruleSeat.SRTT_MIDDLE)) {
                //拦腰组合
                for (int i = 0; i < userListSignBoth.size() / 2; i++) {
                    orderList.add(userListSignBoth.get(i));
                    orderList.add(userListSignBoth.get(userListSignBoth.size() / 2 + i));
                }
                if (userListSignBoth.size() % 2 == 1) {
                    orderList.add(userListSignBoth.get(userListSignBoth.size()));
                }
            }
        }
        List<AppUser> userList = getUserList(orderList);
        userList.addAll(getUserList(userListSignUser));
        userList.addAll(getUserList(userListSignPartner));

        return userList;
    }

    @Override
    public List<AppUser> queryTeamUserByMatchId(String matchId) {
        return matchTeamDataMapper.queryTeamUserByMatchId(matchId);
    }

    @Override
    public int saveMatchTeamDataAndMember(String[] userIds, String matchId, Integer turnNumber) {
        for (int i = 0; i < userIds.length; i += 4) {
            MatchTeamData matchTeamData = new MatchTeamData();
            matchTeamData.setId(StringUtils.generateShortUUID());
            matchTeamData.setTurnNumber(turnNumber);
            matchTeamData.setMatchId(matchId);
            matchTeamData.setTableNumber(i / 4 + 1);
            //东西队伍
            MatchTeamMember teamOne = new MatchTeamMember();
            teamOne.setTeamNumber(i / 2 + 1);
            teamOne.setId(StringUtils.generateShortUUID());
            teamOne.setMatchId(matchId);
            teamOne.setTeamMemberOne(setMember(i, userIds));
            teamOne.setTeamMemberTwo(setMember(i + 1, userIds));
            //南北队伍
            MatchTeamMember teamTwo = new MatchTeamMember();
            teamTwo.setTeamNumber(i / 2 + 2);
            teamTwo.setId(StringUtils.generateShortUUID());
            teamTwo.setMatchId(matchId);
            teamTwo.setTeamMemberOne(setMember(i + 2, userIds));
            teamTwo.setTeamMemberTwo(setMember(i + 3, userIds));
            //设置对战双方
            matchTeamData.setTeamOneId(teamOne.getId());
            matchTeamData.setTeamTwoId(teamTwo.getId());

            //保存
            int resultTeamOne = matchTeamMemberMapper.insertSelective(teamOne);
            int resultTeamTwo = matchTeamMemberMapper.insertSelective(teamTwo);
            int resultTeamData = matchTeamDataMapper.insertSelective(matchTeamData);

        }
        return 1;
    }

    @Override
    public List<MatchTeamDataDto> queryTeamDataByCondition(MatchTeamData matchTeamData) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("matchId", matchTeamData.getMatchId());
        paramMap.put("turnNumber", matchTeamData.getTurnNumber());
        return matchTeamDataMapper.queryTeamDataByCondition(paramMap);
    }

    @Override
    public int saveMatchTeamData(MatchTeamData matchTeamData) {

        String[] ids = matchTeamData.getId().split(",");
        String[] teamOneIds = matchTeamData.getTeamOneId().split(",");
        String[] teamOneScores = matchTeamData.getTeamOneScore().split(",");
        String[] teamTwoIds = matchTeamData.getTeamTwoId().split(",");
        String[] teamTwoScores = matchTeamData.getTeamTwoScore().split(",");
        String matchId = matchTeamData.getMatchId();
        Integer turnNumber = matchTeamData.getTurnNumber();

        List<String> teamIdsList = new ArrayList<>(Arrays.asList(teamOneIds));
        teamIdsList.addAll(new ArrayList<>(Arrays.asList(teamTwoIds)));

        //不是首轮的情况下，查找上一轮成绩
        HashMap<Object, Object> lastResultMap = new HashMap<>();
        if (!turnNumber.equals(Constants.result.TURN_FIRST)) {
            MatchTeamResultExample resultExample = new MatchTeamResultExample();
            MatchTeamResultExample.Criteria resultExampleCriteria = resultExample.createCriteria();
            resultExampleCriteria.andTeamIdIn(teamIdsList);
            List<MatchTeamResult> matchTeamResultList = matchTeamResultMapper.selectByExample(resultExample);
            for (MatchTeamResult result : matchTeamResultList) {
                lastResultMap.put(result.getTeamId(), result);
            }
        }

        //积分规则（胜负平积分）
        MatchRuleExample example = new MatchRuleExample();
        MatchRuleExample.Criteria criteria = example.createCriteria();
        criteria.andMatchIdEqualTo(matchTeamData.getMatchId());
        List<MatchRule> matchRules = matchRuleMapper.selectByExample(example);
        int resultWin = Constants.result.WIN;
        int resultFail = Constants.result.FAIL;
        int resultDraw = Constants.result.DRAW;
        if (matchRules != null && matchRules.size() > 0) {
            MatchRule matchRule = matchRules.get(0);
            resultWin = matchRule.getRuleWin();
            resultFail = matchRule.getRuleFail();
            resultDraw = matchRule.getRuleDraw();
        }

        //保存成绩
        for (int i = 0; i < ids.length; i++) {
            MatchTeamData matchTeamDataUpdate = new MatchTeamData();
            matchTeamDataUpdate.setId(ids[i]);
            int disparity = new Integer(teamOneScores[i]) - new Integer(teamTwoScores[i]);
            //设置成绩
            matchTeamDataUpdate.setTeamOneScore(teamOneScores[i]);
            matchTeamDataUpdate.setTeamTwoScore(teamTwoScores[i]);
            //保存
            matchTeamDataMapper.updateByPrimaryKeySelective(matchTeamDataUpdate);

            //teamOne成绩详情
            MatchTeamResult teamOneResult = new MatchTeamResult();
            teamOneResult.setId(StringUtils.generateShortUUID());
            teamOneResult.setMatchId(matchId);
            teamOneResult.setTurnNumber(turnNumber);
            teamOneResult.setTeamId(teamOneIds[i]);
            teamOneResult.setScore(teamOneScores[i]);
            teamOneResult.setDisparity(disparity > 0 ? Constants.result.DISPARITY[0][disparity] : Constants.result.DISPARITY[1][-disparity]);
            //teamTwo成绩详情
            MatchTeamResult teamTwoResult = new MatchTeamResult();
            teamTwoResult.setId(StringUtils.generateShortUUID());
            teamTwoResult.setMatchId(matchId);
            teamTwoResult.setTurnNumber(turnNumber);
            teamTwoResult.setTeamId(teamTwoIds[i]);
            teamTwoResult.setScore(teamTwoScores[i]);
            teamTwoResult.setDisparity(disparity > 0 ? Constants.result.DISPARITY[1][disparity] : Constants.result.DISPARITY[0][-disparity]);

            //设置本轮得分
            if (disparity == 0) {
                teamOneResult.setPoint(resultDraw);
                teamTwoResult.setPoint(resultDraw);
            } else {
                teamOneResult.setPoint(disparity > 0 ? resultWin : resultFail);
                teamTwoResult.setPoint(disparity > 0 ? resultFail : resultWin);
            }
            //设置去首轮积分+积分和+极差和
            setTeamResult(lastResultMap, turnNumber, teamOneResult);
            setTeamResult(lastResultMap, turnNumber, teamTwoResult);
            //保存
            matchTeamResultMapper.insert(teamOneResult);
            matchTeamResultMapper.insert(teamTwoResult);
        }
        return 1;
    }

    //设置下轮座位（根据成绩）
    @Override
    public int saveMatchTeamDataAndMemberNext(String matchId, Integer turnNumber) {
        //查询上轮成绩
        MatchTeamResultExample example = new MatchTeamResultExample();
        MatchTeamResultExample.Criteria criteria = example.createCriteria();
        criteria.andMatchIdEqualTo(matchId);
        criteria.andTurnNumberEqualTo(turnNumber - 1);
        List<MatchTeamResult> matchTeamDataListLastTurn = matchTeamResultMapper.selectByExample(example);
        //排序
        Collections.sort(matchTeamDataListLastTurn);

        for (int i = 0; i < matchTeamDataListLastTurn.size(); i += 2) {
            MatchTeamData matchTeamData = new MatchTeamData();
            matchTeamData.setId(StringUtils.generateShortUUID());
            matchTeamData.setTableNumber(i / 2 + 1);
            matchTeamData.setTurnNumber(turnNumber);
            matchTeamData.setMatchId(matchId);
            matchTeamData.setTeamOneId(matchTeamDataListLastTurn.get(i).getTeamId());
            matchTeamData.setTeamTwoId(matchTeamDataListLastTurn.get(i + 1).getTeamId());
            matchTeamDataMapper.insertSelective(matchTeamData);
        }
        return 1;
    }

    private void setTeamResult(HashMap lastTurnResultMap, Integer turnNumber, MatchTeamResult result) {
        //积分和
        result.setTotalPoint(turnNumber.equals(Constants.result.TURN_FIRST) ? result.getPoint() : ((MatchTeamResult) lastTurnResultMap.get(result.getTeamId())).getTotalPoint() + result.getPoint());
        //极差和
        result.setTotalDisparity(turnNumber.equals(Constants.result.TURN_FIRST) ? result.getDisparity() : ((MatchTeamResult) lastTurnResultMap.get(result.getTeamId())).getDisparity() + result.getDisparity());
        //去首轮积分
        result.setTotalExfirst(turnNumber.equals(Constants.result.TURN_FIRST) ? 0 : ((MatchTeamResult) lastTurnResultMap.get(result.getTeamId())).getTotalExfirst() + result.getPoint());
        //去首轮积分和
        result.setTotalExfirstAll(turnNumber.equals(Constants.result.TURN_FIRST) ? 0 : ((MatchTeamResult) lastTurnResultMap.get(result.getTeamId())).getTotalExfirstAll() + result.getPoint());
    }

    private String setMember(int index, String[] userIds) {
        if (index < userIds.length) {
            return userIds[index];
        } else {
            return "";
        }
    }

    private List<AppUser> getUserList(List<ApplyOrder> applyOrders) {
        List<AppUser> userList = new ArrayList<>();
        for (ApplyOrder order : applyOrders) {
            if (order.getUserSignStatus().equals(Constants.Order.USER_SIGN_STATUS_END)) {
                AppUser user = new AppUser();
                user.setId(order.getUserId());
                user.setNickName(order.getUserName());
                user.setAvatarUrl(StringUtils.isBlank(order.getUserHead()) ? "/images/head.png" : order.getUserHead());
                userList.add(user);
            }
            if (order.getPartnerSignStatue().equals(Constants.Order.USER_SIGN_STATUS_END)) {
                AppUser partner = new AppUser();
                partner.setId(order.getPartnerId());
                partner.setNickName(order.getPartnerName());
                partner.setAvatarUrl(StringUtils.isBlank(order.getPartnerHead()) ? "/images/head.png" : order.getPartnerHead());
                userList.add(partner);
            }
        }
        return userList;
    }
}
