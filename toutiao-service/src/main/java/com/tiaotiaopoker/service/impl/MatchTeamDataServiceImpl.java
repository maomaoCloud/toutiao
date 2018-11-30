package com.tiaotiaopoker.service.impl;

import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.dao.*;
import com.tiaotiaopoker.entity.MatchTeamDataDto;
import com.tiaotiaopoker.pojo.*;
import com.tiaotiaopoker.service.MatchRuleService;
import com.tiaotiaopoker.service.MatchTeamDataService;
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

    @Autowired
    private MatchRuleService matchRuleService;

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
                    orderList.add(userListSignBoth.get(userListSignBoth.size() - i - 1));
                }
                if (userListSignBoth.size() % 2 == 1) {
                    orderList.add(userListSignBoth.get(userListSignBoth.size() / 2));
                }
            } else if (ruleSeat.equals(Constants.ruleSeat.SORT_MIDDLE)) {
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
    public int saveMatchTeamDataAndMember(String[] userIds, String[] userNames, String[] userHeads, String matchId, Integer turnNumber) {
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
            //设置东西队伍队员id，姓名，头像
            teamOne.setTeamMemberOne(setMemberId(i, userIds));
            teamOne.setTeamMemberOneName(setMemberName(i, userNames));
            teamOne.setTeamMemberOneHead(setMemberHead(i, userHeads));
            teamOne.setTeamMemberTwo(setMemberId(i + 1, userIds));
            teamOne.setTeamMemberTwoName(setMemberName(i + 1, userNames));
            teamOne.setTeamMemberTwoHead(setMemberHead(i + 1, userHeads));
            //南北队伍
            MatchTeamMember teamTwo = new MatchTeamMember();
            teamTwo.setTeamNumber(i / 2 + 2);
            teamTwo.setId(StringUtils.generateShortUUID());
            teamTwo.setMatchId(matchId);
            //设置南北队伍队员id，姓名，头像
            teamTwo.setTeamMemberOne(setMemberId(i + 2, userIds));
            teamTwo.setTeamMemberOneName(setMemberName(i + 2, userNames));
            teamTwo.setTeamMemberOneHead(setMemberHead(i + 2, userHeads));
            teamTwo.setTeamMemberTwo(setMemberId(i + 3, userIds));
            teamTwo.setTeamMemberTwoName(setMemberName(i + 3, userNames));
            teamTwo.setTeamMemberTwoHead(setMemberHead(i + 3, userHeads));
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
            setTeamResult(lastResultMap, turnNumber, teamOneResult, teamTwoResult, disparity > 0 ? 1 : 0);
            setTeamResult(lastResultMap, turnNumber, teamTwoResult, teamOneResult, disparity < 0 ? 1 : 0);
            //保存
            matchTeamResultMapper.insert(teamOneResult);
            matchTeamResultMapper.insert(teamTwoResult);
        }
        return 1;
    }

    //设置下轮座位（根据成绩）
    @Override
    public int saveMatchTeamDataAndMemberNext(String matchId, Integer turnNumber) {
        //查询比赛规则
        MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId(matchId);
        //查询上轮成绩
        MatchTeamResultExample example = new MatchTeamResultExample();
        MatchTeamResultExample.Criteria criteria = example.createCriteria();
        criteria.andMatchIdEqualTo(matchId);
        criteria.andTurnNumberEqualTo(turnNumber - 1);
        List<MatchTeamResult> matchTeamDataListLastTurn = matchTeamResultMapper.selectByExample(example);
        for (MatchTeamResult
                teamResult :
                matchTeamDataListLastTurn) {
            teamResult.setResultRule(matchRule.getRuleResult());
        }
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

    @Override
    public int getNowTurn(String matchId) {
        return matchTeamDataMapper.getNowTurn(matchId);
    }

    @Override
    public int updateMatchTeamData(String[] split, String matchId, Integer turnNumber) {
        //删除旧座位
        MatchTeamDataExample delExample = new MatchTeamDataExample();
        delExample.createCriteria().andMatchIdEqualTo(matchId).andTurnNumberEqualTo(turnNumber);
        matchTeamDataMapper.deleteByExample(delExample);
        //新增新座位
        for (int i = 0; i < split.length; i += 2) {
            MatchTeamData teamData = new MatchTeamData();
            teamData.setTableNumber(i / 2 + 1);
            teamData.setId(StringUtils.generateShortUUID());
            teamData.setMatchId(matchId);
            teamData.setTurnNumber(turnNumber);
            teamData.setTeamOneId(split[i]);
            teamData.setTeamTwoId(split[i + 1]);
            matchTeamDataMapper.insertSelective(teamData);
        }
        return 1;
    }

    private void setTeamResult(HashMap lastTurnResultMap, Integer turnNumber, MatchTeamResult result, MatchTeamResult opponentResult, int winCount) {
        MatchTeamResult lastResult = (MatchTeamResult) lastTurnResultMap.get(result.getTeamId());
        boolean isFirst = turnNumber.equals(Constants.result.TURN_FIRST);
        //积分和
        result.setTotalPoint(isFirst ? result.getPoint() : lastResult.getTotalPoint() + result.getPoint());
        //极差和
        result.setTotalDisparity(isFirst ? result.getDisparity() : lastResult.getTotalDisparity() + result.getDisparity());
        //去首轮积分(累进分)
        result.setTotalExfirst(isFirst ? 0 : lastResult.getTotalExfirst() + result.getPoint());
        //去首轮积分和（累进分和）
        result.setTotalExfirstAll(isFirst ? 0 : lastResult.getTotalExfirstAll() + result.getTotalExfirst());
        //累计胜轮次
        result.setTotalWin(isFirst ? winCount : lastResult.getTotalWin() + winCount);
        //累计总升级数
        result.setTotalScore(isFirst ? new Integer(result.getScore()) - 2 : lastResult.getTotalScore() + new Integer(result.getScore()) - 2);
        //累计对手极差分
        result.setTotalDisparityOpponent(isFirst ? opponentResult.getDisparity() : lastResult.getTotalDisparityOpponent() + opponentResult.getDisparity());
        //累计过A数
        result.setTotalOverA(isFirst ? (result.getScore().equals(Constants.result.OVER_A) ? 1 : 0) : lastResult.getTotalOverA() + (result.getScore().equals(Constants.result.OVER_A) ? 1 : 0));
    }

    private String setMemberId(int index, String[] userIds) {
        if (index < userIds.length) {
            return userIds[index];
        } else {
            return "";
        }
    }

    private String setMemberName(int index, String[] userNames) {
        if (index < userNames.length) {
            return userNames[index];
        } else {
            return "";
        }
    }

    private String setMemberHead(int index, String[] userHeads) {
        if (index < userHeads.length) {
            return userHeads[index];
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
