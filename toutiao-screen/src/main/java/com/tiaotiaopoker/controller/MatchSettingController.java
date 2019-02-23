package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.entity.RuleResult;
import com.tiaotiaopoker.pojo.Match;
import com.tiaotiaopoker.pojo.MatchAuthorization;
import com.tiaotiaopoker.pojo.MatchRule;
import com.tiaotiaopoker.pojo.MatchWithBLOBs;
import com.tiaotiaopoker.service.MatchAuthorizationService;
import com.tiaotiaopoker.service.MatchRuleService;
import com.tiaotiaopoker.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("sys/matchSetting")
public class MatchSettingController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private MatchRuleService matchRuleService;

    @Autowired
    private MatchAuthorizationService authorizationService;

    @RequestMapping("index")
    public ModelAndView index(ModelAndView mv,
                              String token,
                              @ModelAttribute(value = "matchRule") MatchRule matchRule) {
        String matchId = matchRule.getMatchId();
        Match match = new Match();
        match.setUserId(token);
        //比赛设置前列出该用户创建的比赛（不需要分页）（授权的比赛）
        List<Match> matchList = matchService.queryMatchByCondition(match, null);
        //遍历创建的所有比赛，若不存在比赛规则，则创建
        for (Match myMatch : matchList) {
            matchRuleService.createMatchRuleByMatch(myMatch);
        }
        if (!StringUtils.isBlank(matchRule.getMatchId())) {
            matchRule = matchRuleService.selectMatchRuleByMatchId(matchRule.getMatchId());
        }

        //成绩排名规则
        List<RuleResult> myList = new ArrayList<>();
        List<RuleResult> otherList = new ArrayList<>();
        divideRuleResult(Constants.resultRule.resultRuleMap, matchRule.getRuleResult(), myList, otherList);
        mv.addObject("myList", myList);
        mv.addObject("otherList", otherList);

        mv.addObject("matchRule", matchRule);
        mv.addObject("token", token);
        mv.addObject("matchList", matchList);
        mv.addObject("matchId", matchId);
        mv.setViewName("matchSetting/matchIndex");

        //校验当前比赛是否是授权比赛
        if (matchId != null){
            MatchWithBLOBs matchData = matchService.getMatchDataById(matchId);
            mv.addObject("isAuthor", matchData.getUserId());
            if (matchData.getUserId().equals(token)) {
                //若当前比赛是由当前操作人创建，则查出所有当前比赛的授权用户
                List<MatchAuthorization> authorList = authorizationService.queryAuthorizationList(matchRule.getMatchId());
                mv.addObject("authorList", authorList);
            }
        }
        return mv;
    }

    //比赛信息
    @RequestMapping("matchInfo")
    public ModelAndView matchInfo(ModelAndView mv,
                                  String token,
                                  @ModelAttribute(value = "matchRule") MatchRule matchRule) {
        if (!StringUtils.isBlank(matchRule.getMatchId())) {
            matchRule = matchRuleService.selectMatchRuleByMatchId(matchRule.getMatchId());
        }
        mv.setViewName("matchSetting/matchInfo");
        mv.addObject("token", token);
        mv.addObject("matchRule", matchRule);
        return mv;
    }

    //比赛基础设置
    @RequestMapping("matchRule")
    public ModelAndView matchRule(ModelAndView mv,
                                  String token,
                                  @ModelAttribute(value = "matchRule") MatchRule matchRule) {
        if (!StringUtils.isBlank(matchRule.getMatchId())) {
            matchRule = matchRuleService.selectMatchRuleByMatchId(matchRule.getMatchId());
        }

        //成绩排名规则
        List<RuleResult> myList = new ArrayList<>();
        List<RuleResult> otherList = new ArrayList<>();
        divideRuleResult(Constants.resultRule.resultRuleMap, matchRule.getRuleResult(), myList, otherList);
        mv.addObject("myList", myList);
        mv.addObject("otherList", otherList);

        mv.setViewName("matchSetting/matchRule");
        mv.addObject("token", token);
        mv.addObject("matchRule", matchRule);

        //校验当前比赛是否是授权比赛
        MatchWithBLOBs matchData = matchService.getMatchDataById(matchRule.getMatchId());
        mv.addObject("isAuthor", matchData.getUserId());
        if (matchData.getUserId().equals(token)) {
            //若当前比赛是由当前操作人创建，则查出所有当前比赛的授权用户
            List<MatchAuthorization> authorList = authorizationService.queryAuthorizationList(matchRule.getMatchId());
            mv.addObject("authorList", authorList);
        }
        return mv;
    }

    //比赛轮次
    @RequestMapping("matchTurn")
    @ResponseBody
    public List<String> matchTurn(String token,
                                  String matchId) {
        MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId(matchId);
        int ruleTurn = (null == matchRule ? 0 : matchRule.getRuleTurn());
        List<String> ruleTurnList = new ArrayList<>();
        for (int i = 1; i <= ruleTurn; i++) {
            ruleTurnList.add(Constants.NUM_CH[i - 1]);
        }
        return ruleTurnList;
    }

    @RequestMapping("save")
    @ResponseBody
    public JsonResult save(MatchRule matchRule, String authorUserIds, String authorUserNames) {
        JsonResult jsonResult;
        try {
            int result = matchRuleService.saveBySelective(matchRule);
            MatchRule matchRuleInfo = matchRuleService.selectMatchRuleById(matchRule.getId());
            //删除当前比赛之前的所有授权
            authorizationService.deleteAuthorizationByMatchId(matchRuleInfo.getMatchId());
            if (!StringUtils.isBlank(authorUserIds)) {
                //新增授权
                MatchAuthorization matchAuthorization = new MatchAuthorization();
                matchAuthorization.setMatchId(matchRuleInfo.getMatchId());
                matchAuthorization.setMatchName(matchRuleInfo.getMatchName());
                matchAuthorization.setUserId(authorUserIds);
                matchAuthorization.setUserTrueName(authorUserNames);
                authorizationService.addAuthorization(matchAuthorization);
            }
            if (result > 0) {
                jsonResult = JsonResult.SUCCESS("保存成功");
            } else {
                jsonResult = JsonResult.FAILED("保存失败");
            }
        } catch (Exception e) {
            jsonResult = JsonResult.FAILED("比赛设置接口异常");
            e.printStackTrace();
        }
        return jsonResult;
    }

    private void divideRuleResult(Map<String, Object> ruleResultMap, String ruleResult, List<RuleResult> myList, List<RuleResult> otherList) {
        if (StringUtils.isBlank(ruleResult)) {
            ruleResult = Constants.result.DEFAULT_RESULT_RULE;
        }

        String[] ruleItems = ruleResult.split(",");
        Set<String> myListSet = new HashSet<>();
        for (String ruleItem : ruleItems) {
            myList.add(new RuleResult(ruleItem, ruleResultMap.get(ruleItem).toString()));
            myListSet.add(ruleItem);
        }

        for (Map.Entry<String, Object> entry : ruleResultMap.entrySet()) {
            if (!myListSet.contains(entry.getKey())) {
                otherList.add(new RuleResult(entry.getKey(), entry.getValue().toString()));
            }
        }

    }

    @RequestMapping("isGroup/{matchId}")
    @ResponseBody
    public JsonResult checkIsGroup(@PathVariable("matchId") String matchId) {
        Boolean isGroup = matchService.checkIsGroup(matchId);
        return JsonResult.SUCCESS("", isGroup);
    }

}
