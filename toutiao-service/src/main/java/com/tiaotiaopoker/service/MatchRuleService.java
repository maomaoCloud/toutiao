package com.tiaotiaopoker.service;

import com.tiaotiaopoker.pojo.Match;
import com.tiaotiaopoker.pojo.MatchRule;

import java.util.Map;

public interface MatchRuleService {
    int saveBySelective(MatchRule matchRule);

    MatchRule selectMatchRuleByMatchId(String matchId);

    void createMatchRuleByMatch(Match myMatch);
}
