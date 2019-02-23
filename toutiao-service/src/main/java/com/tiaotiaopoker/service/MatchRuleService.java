package com.tiaotiaopoker.service;

import com.tiaotiaopoker.entity.SysSetting;
import com.tiaotiaopoker.pojo.Match;
import com.tiaotiaopoker.pojo.MatchRule;

import java.util.Map;

public interface MatchRuleService {
    int saveBySelective (MatchRule matchRule);

    MatchRule selectMatchRuleByMatchId (String matchId);

    void createMatchRuleByMatch (Match myMatch);

    void saveByMatchIdSelective (MatchRule rule);

    SysSetting getSysSetting (String matchId);


    MatchRule selectMatchRuleById(String id);
}
