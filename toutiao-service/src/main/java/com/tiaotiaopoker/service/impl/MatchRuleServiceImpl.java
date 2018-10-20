package com.tiaotiaopoker.service.impl;

import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.dao.MatchRuleMapper;
import com.tiaotiaopoker.pojo.Match;
import com.tiaotiaopoker.pojo.MatchRule;
import com.tiaotiaopoker.pojo.MatchRuleExample;
import com.tiaotiaopoker.pojo.MatchWithBLOBs;
import com.tiaotiaopoker.service.MatchRuleService;
import com.tiaotiaopoker.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchRuleServiceImpl implements MatchRuleService {

    @Autowired
    private MatchService matchService;

    @Autowired
    private MatchRuleMapper matchRuleMapper;

    @Override
    public int saveBySelective(MatchRule matchRule) {
        return matchRuleMapper.updateByPrimaryKeySelective(matchRule);
    }

    @Override
    public MatchRule selectMatchRuleByMatchId(String matchId) {
        MatchRuleExample matchRuleExample = new MatchRuleExample();
        MatchRuleExample.Criteria criteria = matchRuleExample.createCriteria();
        criteria.andMatchIdEqualTo(matchId);
        List<MatchRule> matchRuleList = matchRuleMapper.selectByExample(matchRuleExample);
        if (matchRuleList.size() > 0) {
            return matchRuleList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void createMatchRuleByMatch(Match myMatch) {
        MatchRuleExample matchRuleExample = new MatchRuleExample();
        MatchRuleExample.Criteria criteria = matchRuleExample.createCriteria();
        criteria.andMatchIdEqualTo(myMatch.getId());
        List<MatchRule> matchRuleList = matchRuleMapper.selectByExample(matchRuleExample);
        if (matchRuleList.size() == 0) {
            //创建比赛规则
            MatchRule matchRule = new MatchRule();
            matchRule.setMatchId(myMatch.getId());
            matchRule.setId(StringUtils.generateShortUUID());
            matchRule.setMatchName(myMatch.getTheme());
            matchRule.setMatchAddress(myMatch.getCity() + myMatch.getArea() + myMatch.getAddress());
            matchRule.setMatchDate(myMatch.getStartDate().toString() + "——" + myMatch.getEndDate().toString());
            matchRule.setRuleTurn(3);
            matchRule.setRuleWin(2);
            matchRule.setRuleDraw(1);
            matchRule.setRuleFail(0);
            matchRuleMapper.insertSelective(matchRule);
        } else {
            return;
        }
    }
}
