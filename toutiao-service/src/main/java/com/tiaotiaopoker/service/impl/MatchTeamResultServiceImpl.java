package com.tiaotiaopoker.service.impl;

import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.dao.MatchTeamDataMapper;
import com.tiaotiaopoker.dao.MatchTeamMemberMapper;
import com.tiaotiaopoker.dao.MatchTeamResultMapper;
import com.tiaotiaopoker.entity.MatchTeamDataDto;
import com.tiaotiaopoker.entity.MatchTeamResultDto;
import com.tiaotiaopoker.pojo.*;
import com.tiaotiaopoker.service.MatchRuleService;
import com.tiaotiaopoker.service.MatchTeamDataService;
import com.tiaotiaopoker.service.MatchTeamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatchTeamResultServiceImpl implements MatchTeamResultService {

    @Autowired
    private MatchTeamResultMapper matchTeamResultMapper;

    @Autowired
    private MatchRuleService matchRuleService;

    @Autowired
    private MatchTeamMemberMapper matchTeamMemberMapper;

    @Autowired
    private MatchTeamDataMapper matchTeamDataMapper;

    @Autowired
    private MatchTeamDataService matchTeamDataService;

    @Override
    public List<MatchTeamResult> queryMatchTeamResultByCondition (MatchTeamResult matchTeamResult) {
        MatchTeamResultExample example = new MatchTeamResultExample();
        MatchTeamResultExample.Criteria criteria = example.createCriteria();
        criteria.andMatchIdEqualTo(matchTeamResult.getMatchId());
        criteria.andTurnNumberEqualTo(matchTeamResult.getTurnNumber());
        return matchTeamResultMapper.selectByExample(example);
    }

    @Override
    public List<MatchTeamResultDto> sortMatchTeamResult (MatchTeamResult result) {
        //查询比赛规则
        MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId(result.getMatchId());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("matchId", result.getMatchId());
        paramMap.put("turnNumber", result.getTurnNumber());
        List<MatchTeamResultDto> matchTeamResultDtos = matchTeamResultMapper.queryResultWithTableNumber(paramMap);
        //排名规则
        for (MatchTeamResultDto teamResult : matchTeamResultDtos) {
            teamResult.setResultRule(matchRule.getRuleResult());
        }
        Collections.sort(matchTeamResultDtos);
        int i = 1;
        for (MatchTeamResultDto resultDto : matchTeamResultDtos) {
            resultDto.setScore(Constants.result.SCORE[new Integer(resultDto.getScore()) - 2]);
            resultDto.setIndex(i++);
        }
        return matchTeamResultDtos;
    }

    @Override
    public List<MatchTeamMember> getAllTeams (String matchId) {
        MatchTeamMemberExample example = new MatchTeamMemberExample();
        example.createCriteria().andMatchIdEqualTo(matchId);
        return matchTeamMemberMapper.selectByExample(example);
    }

    @Override
    public void editPersonalScore (String teamId, Integer turnNumber, int val, String matchId) {
        //拿到当前轮次所有的成绩
        int currentTurn = turnNumber;

        //删除当前轮次以及之后轮次的详细成绩
        deleteDetailResult(matchId, turnNumber);

        List<MatchTeamData> currentMatchTeamData;
        for (; ; ) {
            currentMatchTeamData = getMatchTeamDataByTurnNumber(matchId, currentTurn);
            if (currentMatchTeamData == null || currentMatchTeamData.size() <= 0 || org.apache.commons.lang3.StringUtils.isBlank(currentMatchTeamData.get(0).getTeamTwoScore()))
                return;

            //更新成绩
            if (currentTurn == turnNumber) {//如果是当前轮，更新成绩
                for (MatchTeamData data : currentMatchTeamData) {
                    if (data.getTeamOneId().equals(teamId)) {
                        data.setTeamOneScore(new Integer(val).toString());
                        break;
                    }

                    if (data.getTeamTwoId().equals(teamId)) {
                        data.setTeamTwoScore(new Integer(val).toString());
                        break;
                    }
                }
            }


            //封装保存成绩方法所需参数
            MatchTeamData matchTeamDataParam = installParams(matchId, currentTurn, currentMatchTeamData);
            //调用保存成绩方法，让他自动计算当前轮成绩
            matchTeamDataService.saveMatchTeamData(matchTeamDataParam);

            currentTurn++;
        }

    }

    private MatchTeamData installParams (String matchId, int currentTurn, List<MatchTeamData> currentMatchTeamData) {
        MatchTeamData param = new MatchTeamDataDto();
        param.setMatchId(matchId);
        param.setTurnNumber(currentTurn);
        String[] ids = new String[currentMatchTeamData.size()];
        String[] teamOneIds = new String[currentMatchTeamData.size()];
        String[] teamOneScores = new String[currentMatchTeamData.size()];
        String[] teamTwoIds = new String[currentMatchTeamData.size()];
        String[] teamTwoScores = new String[currentMatchTeamData.size()];

        for (int i = 0; i < currentMatchTeamData.size(); i++) {
            ids[i] = currentMatchTeamData.get(i).getId();
            teamOneIds[i] = currentMatchTeamData.get(i).getTeamOneId();
            teamOneScores[i] = currentMatchTeamData.get(i).getTeamOneScore();
            teamTwoIds[i] = currentMatchTeamData.get(i).getTeamTwoId();
            teamTwoScores[i] = currentMatchTeamData.get(i).getTeamTwoScore();
        }

        param.setId(StringUtils.arrayToString(ids, ","));
        param.setTeamOneId(StringUtils.arrayToString(teamOneIds, ","));
        param.setTeamOneScore(StringUtils.arrayToString(teamOneScores, ","));
        param.setTeamTwoId(StringUtils.arrayToString(teamTwoIds, ","));
        param.setTeamTwoScore(StringUtils.arrayToString(teamTwoScores, ","));

        return param;
    }

    private void deleteDetailResult (String matchId, Integer turnNumber) {
        MatchTeamResultExample example = new MatchTeamResultExample();
        example.createCriteria().andMatchIdEqualTo(matchId).andTurnNumberGreaterThanOrEqualTo(turnNumber);

        matchTeamResultMapper.deleteByExample(example);
    }

    private List<MatchTeamData> getMatchTeamDataByTurnNumber (String matchId, Integer turnNumber) {
        MatchTeamDataExample example = new MatchTeamDataExample();
        example.createCriteria().andMatchIdEqualTo(matchId).andTurnNumberEqualTo(turnNumber);
        return matchTeamDataMapper.selectByExample(example);
    }

}
