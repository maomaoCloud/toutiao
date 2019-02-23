package com.tiaotiaopoker.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.dao.MatchRuleMapper;
import com.tiaotiaopoker.entity.BiValue;
import com.tiaotiaopoker.entity.SysSetting;
import com.tiaotiaopoker.pojo.Match;
import com.tiaotiaopoker.pojo.MatchRule;
import com.tiaotiaopoker.pojo.MatchRuleExample;
import com.tiaotiaopoker.pojo.MatchWithBLOBs;
import com.tiaotiaopoker.service.MatchRuleService;
import com.tiaotiaopoker.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MatchRuleServiceImpl implements MatchRuleService {

    @Autowired
    private MatchService matchService;

    @Autowired
    private MatchRuleMapper matchRuleMapper;

    @Override
    public int saveBySelective (MatchRule matchRule) {
        return matchRuleMapper.updateByPrimaryKeySelective(matchRule);
    }

    @Override
    public MatchRule selectMatchRuleByMatchId (String matchId) {
        MatchRuleExample matchRuleExample = new MatchRuleExample();
        MatchRuleExample.Criteria criteria = matchRuleExample.createCriteria();
        criteria.andMatchIdEqualTo(matchId);
        List<MatchRule> matchRuleList = matchRuleMapper.selectByExampleWithBLOBs(matchRuleExample);
        if (matchRuleList.size() > 0) {
            return matchRuleList.get(0);
        }
        return null;
    }

    @Override
    public void createMatchRuleByMatch (Match myMatch) {
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
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            matchRule.setMatchDate(formatter.format(myMatch.getStartDate()) + "——" + formatter.format(myMatch.getEndDate()));
            matchRule.setRuleTurn(3);
            matchRule.setRuleWin(2);
            matchRule.setRuleDraw(1);
            matchRule.setRuleFail(0);
            matchRuleMapper.insertSelective(matchRule);
        }
    }


    @Override
    public MatchRule selectMatchRuleById(String id) {
        return matchRuleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveByMatchIdSelective (MatchRule rule) {
        MatchRuleExample example = new MatchRuleExample();
        example.createCriteria().andMatchIdEqualTo(rule.getMatchId());
        matchRuleMapper.updateByExampleSelective(rule, example);
    }

    @Override
    public SysSetting getSysSetting (String matchId) {
        MatchRule matchRule = selectMatchRuleByMatchId(matchId);
        String ruleResult = matchRule.getRuleResult();
        String sysSetting = matchRule.getSysSetting();
        SysSetting setting = null;
        if (StringUtils.isBlank(sysSetting)) {
            List<BiValue<Integer, Integer>> data = new ArrayList<>();
            setting = new SysSetting();
            for (int i = 0; i < SysSetting.Constants.TOTAL_SIZE; i++) {
                BiValue<Integer, Integer> item = new BiValue<>();
                item.setValA(10);
                item.setValB(5);
                data.add(item);
            }

            setting.setData(data);

            //设置显示列
            if (StringUtils.isBlank(ruleResult)) {
                ruleResult = Constants.result.DEFAULT_RESULT_RULE;
            }

            String showColumnStr = Constants.DEFAULT_COLUMN + "," + ruleResult;
            List<BiValue<String, String>> hideColumn = new ArrayList<>();
            List<BiValue<String, String>> showColumn = new ArrayList<>();
            setting.setHideColumn(hideColumn);

            String[] columnItems = showColumnStr.split(",");
            for (String columnItem : columnItems) {
                BiValue<String, String> item = new BiValue<>();
                item.setValB(columnItem);
                String valA = Constants.ResultColumnMap.get(columnItem);
                item.setValA(valA);
                showColumn.add(item);
            }

            setting.setShowColumn(showColumn);

            matchRule.setSysSetting(JSONObject.toJSONString(setting));
            MatchRule rule = new MatchRule();
            rule.setMatchId(matchId);
            rule.setSysSetting(matchRule.getSysSetting());
            saveByMatchIdSelective(rule);
        } else {
            setting = JSONObject.parseObject(sysSetting).toJavaObject(SysSetting.class);
        }

        return setting;
    }

}
