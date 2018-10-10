package com.tiaotiaopoker.service.impl;

import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.dao.MatchRuleMapper;
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
        int result = 0;
        MatchWithBLOBs match = matchService.selectMatchById(matchRule.getMatchId());
        if (null == match) {
            matchRule.setId(StringUtils.generateShortUUID());
            result = matchRuleMapper.insert(matchRule);
        } else {
            result = matchRuleMapper.updateByPrimaryKeySelective(matchRule);
        }
        return result;
    }

    @Override
    public MatchRule selectMatchRuleByMatchId(String matchId) {
        MatchRuleExample matchRuleExample = new MatchRuleExample();
        MatchRuleExample.Criteria criteria = matchRuleExample.createCriteria();
        criteria.andMatchIdEqualTo(matchId);
        List<MatchRule> matchRuleList = matchRuleMapper.selectByExample(matchRuleExample);
        if (matchRuleList.size()>0){
            return matchRuleList.get(0);
        }else {
            return null;
        }
    }
}
