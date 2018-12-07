package com.tiaotiaopoker.service.impl;

import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.dao.MatchTeamMemberMapper;
import com.tiaotiaopoker.dao.MatchTeamResultMapper;
import com.tiaotiaopoker.entity.MatchTeamResultDto;
import com.tiaotiaopoker.pojo.*;
import com.tiaotiaopoker.service.MatchRuleService;
import com.tiaotiaopoker.service.MatchTeamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MatchTeamResultServiceImpl implements MatchTeamResultService {

    @Autowired
    private MatchTeamResultMapper matchTeamResultMapper;

    @Autowired
    private MatchRuleService matchRuleService;

    @Autowired
    private MatchTeamMemberMapper matchTeamMemberMapper;

    @Override
    public List<MatchTeamResult> queryMatchTeamResultByCondition (MatchTeamResult matchTeamResult) {
        MatchTeamResultExample          example  = new MatchTeamResultExample();
        MatchTeamResultExample.Criteria criteria = example.createCriteria();
        criteria.andMatchIdEqualTo(matchTeamResult.getMatchId());
        criteria.andTurnNumberEqualTo(matchTeamResult.getTurnNumber());
        return matchTeamResultMapper.selectByExample(example);
    }

    @Override
    public List<MatchTeamResultDto> sortMatchTeamResult (MatchTeamResult result) {
        //查询比赛规则
        MatchRule           matchRule = matchRuleService.selectMatchRuleByMatchId(result.getMatchId());
        Map<String, Object> paramMap  = new HashMap<>();
        paramMap.put("matchId", result.getMatchId());
        paramMap.put("turnNumber", result.getTurnNumber());
        List<MatchTeamResultDto> matchTeamResultDtos = matchTeamResultMapper.queryResultWithTableNumber(paramMap);
        //排名规则
        for (MatchTeamResultDto teamResult : matchTeamResultDtos) {
            teamResult.setResultRule(matchRule.getRuleResult());
        }
        Collections.sort(matchTeamResultDtos);
        for (MatchTeamResultDto resultDto : matchTeamResultDtos) {
            resultDto.setScore(Constants.result.SCORE[new Integer(resultDto.getScore()) - 2]);
        }
        return matchTeamResultDtos;
    }

    @Override
    public List<MatchTeamMember> getAllTeams (String matchId) {
        MatchTeamMemberExample example = new MatchTeamMemberExample();
        example.createCriteria().andMatchIdEqualTo(matchId);
        return matchTeamMemberMapper.selectByExample(example);
    }

}
