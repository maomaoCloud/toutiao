package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.ChineseNum;
import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.entity.MatchTeamDataDto;
import com.tiaotiaopoker.entity.MatchTeamResultDto;
import com.tiaotiaopoker.pojo.MatchRule;
import com.tiaotiaopoker.pojo.MatchTeamData;
import com.tiaotiaopoker.pojo.MatchTeamResult;
import com.tiaotiaopoker.service.MatchRuleService;
import com.tiaotiaopoker.service.MatchTeamDataService;
import com.tiaotiaopoker.service.MatchTeamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("sys/resultSetting")
public class ResultSettingController {

    @Autowired
    private MatchRuleService matchRuleService;

    @Autowired
    private MatchTeamDataService matchTeamDataService;

    @Autowired
    private MatchTeamResultService matchTeamResultService;

    @RequestMapping("resultInput")
    public ModelAndView resultInput(ModelAndView mv, String matchId) {
        //根据matchId查出比赛规则（轮次）
        if (!StringUtils.isBlank(matchId)) {
            MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId(matchId);
            int ruleTurn = (null == matchRule ? 0 : matchRule.getRuleTurn());
            List<String> ruleTurnList = new ArrayList<>();
            for (int i = 1; i <= ruleTurn; i++) {
                ruleTurnList.add("第" + ChineseNum.getChineseNum(i) + "轮");
            }
            mv.addObject("ruleTurnList", ruleTurnList);
        }
        mv.setViewName("resultSetting/resultInput");
        return mv;
    }

    @RequestMapping("resultInputLoad")
    public ModelAndView resultInputLoad(ModelAndView mv, String matchId, Integer ruleTurn) {

        MatchTeamData matchTeamData = new MatchTeamData();
        matchTeamData.setMatchId(matchId);
        matchTeamData.setTurnNumber(ruleTurn);
        List<MatchTeamDataDto> list = matchTeamDataService.queryTeamDataByCondition(matchTeamData);
        MatchTeamResult matchTeamResult = new MatchTeamResult();
        matchTeamResult.setTurnNumber(ruleTurn);
        matchTeamResult.setMatchId(matchId);
        List<MatchTeamResult> resultList = matchTeamResultService.queryMatchTeamResultByCondition(matchTeamResult);
        if (resultList != null && resultList.size() > 0) {
            mv.addObject("showFlag", 1);
        } else {
            mv.addObject("showFlag", 0);
        }
        mv.addObject("list", list);
        mv.addObject("matchId", matchId);
        mv.addObject("ruleTurn", ruleTurn);
        mv.setViewName("resultSetting/resultInputLoad");
        return mv;
    }

    @RequestMapping("save")
    @ResponseBody
    public JsonResult save(MatchTeamData matchTeamData) {
        JsonResult jsonResult;
        try {
            int result = matchTeamDataService.saveMatchTeamData(matchTeamData);
            if (result > 0) {
                jsonResult = JsonResult.SUCCESS("保存成功");
            } else {
                jsonResult = JsonResult.FAILED("保存失败");
            }
        } catch (Exception e) {
            jsonResult = JsonResult.FAILED("保存接口异常");
            e.printStackTrace();
        }
        return jsonResult;
    }

    @RequestMapping("resultDetail")
    public ModelAndView resultDetail(ModelAndView mv, String matchId, int turnNumber) {

        MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId(matchId);
        if (turnNumber > matchRule.getRuleTurn()) {
            turnNumber = matchRule.getRuleTurn();
        }

        MatchTeamResult result = new MatchTeamResult();
        result.setMatchId(matchId);
        result.setTurnNumber(turnNumber);
        List<MatchTeamResultDto> resultlist = matchTeamResultService.sortMatchTeamResult(result);
        mv.addObject("turnNumber", ChineseNum.getChineseNum(turnNumber));
        mv.addObject("resultlist", resultlist);
        mv.setViewName("resultSetting/resultShow");
        return mv;
    }
}
