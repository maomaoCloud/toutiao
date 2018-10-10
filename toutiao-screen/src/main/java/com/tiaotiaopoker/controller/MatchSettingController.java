package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.pojo.Match;
import com.tiaotiaopoker.pojo.MatchRule;
import com.tiaotiaopoker.service.MatchRuleService;
import com.tiaotiaopoker.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("sys/matchSetting")
public class MatchSettingController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private MatchRuleService matchRuleService;

    @RequestMapping("index")
    public ModelAndView index(ModelAndView mv, String token, String matchId) {

        Match match = new Match();
        match.setUserId(token);
        //比赛设置前列出该用户创建的比赛（不需要分页）
        List<Match> matchList = matchService.queryMatchByCondition(match, null);
        mv.addObject("token", token);
        mv.addObject("matchList", matchList);
        mv.addObject("matchId", matchId);
        mv.setViewName("matchSetting/matchIndex");
        return mv;
    }

    //比赛信息
    @RequestMapping("matchInfo")
    public ModelAndView matchInfo(ModelAndView mv, String token, String matchId) {
        mv.setViewName("matchSetting/matchInfo");
        mv.addObject("token", token);
        return mv;
    }

    //比赛基础设置
    @RequestMapping("matchRule")
    public ModelAndView matchRule(ModelAndView mv, String token, String matchId) {
        mv.setViewName("matchSetting/matchRule");
        mv.addObject("token", token);
        return mv;
    }

    //比赛轮次
    @RequestMapping("matchTurn")
    @ResponseBody
    public List<String> matchTurn(String token, String matchId) {
        MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId(matchId);
        int ruleTurn = (null == matchRule ? 4 : matchRule.getRuleTurn());
        List<String> ruleTurnList = new ArrayList<>();
        for (int i = 1; i <= ruleTurn; i++) {
            switch (i){
                case 1: ruleTurnList.add("一");
                    break;
                case 2: ruleTurnList.add("二");
                    break;
                case 3: ruleTurnList.add("三");
                    break;
                case 4: ruleTurnList.add("四");
                    break;
                case 5: ruleTurnList.add("五");
                    break;
                case 6: ruleTurnList.add("六");
                    break;
                case 7: ruleTurnList.add("七");
                    break;
                case 8: ruleTurnList.add("八");
                    break;
                case 9: ruleTurnList.add("九");
                    break;
                default:
                    break;
            }
        }
        return ruleTurnList;
    }

    @RequestMapping("save")
    public JsonResult save(MatchRule matchRule) {
        JsonResult jsonResult;
        try {
            int result = matchRuleService.saveBySelective(matchRule);
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
}
