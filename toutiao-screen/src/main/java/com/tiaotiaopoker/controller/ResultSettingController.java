package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.ChineseNum;
import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.entity.*;
import com.tiaotiaopoker.pojo.MatchRule;
import com.tiaotiaopoker.pojo.MatchTeamData;
import com.tiaotiaopoker.pojo.MatchTeamMember;
import com.tiaotiaopoker.pojo.MatchTeamResult;
import com.tiaotiaopoker.service.MatchRuleService;
import com.tiaotiaopoker.service.MatchTeamDataService;
import com.tiaotiaopoker.service.MatchTeamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Controller
@RequestMapping ("sys/resultSetting")
public class ResultSettingController {

    @Autowired
    private MatchRuleService matchRuleService;

    @Autowired
    private MatchTeamDataService matchTeamDataService;

    @Autowired
    private MatchTeamResultService matchTeamResultService;

    @RequestMapping ("resultInput")
    public ModelAndView resultInput (ModelAndView mv, String matchId) {
        //根据matchId查出比赛规则（轮次）
        if (!StringUtils.isBlank(matchId)) {
            MatchTeamData matchTeamData = new MatchTeamData();
            matchTeamData.setMatchId(matchId);
            List<MatchTeamDataDto> matchDataList = matchTeamDataService.queryTeamDataByCondition(matchTeamData);
            if (matchDataList.size() == 0) {
                //查找当前比赛是否存在对局数据，若不存在需要提示先进行座位排序
                mv.addObject("msg", Constants.result.MSG_SEAT_SETTING);
                mv.addObject("turn", Constants.result.TURN_FIRST);
            } else {
                //查找当前比赛轮次
                int nowTurn = matchTeamDataService.getNowTurn(matchId);
                if (nowTurn == 0) {
                    //最终成绩已生成，不得重新排座位
                    mv.addObject("msg", Constants.result.MSG_FINANL_TURN);
                    mv.addObject("turn", nowTurn);
                } else {
                    //最终成绩未生成
                    matchTeamData.setTurnNumber(nowTurn);
                    matchDataList = matchTeamDataService.queryTeamDataByCondition(matchTeamData);
                    mv.addObject("matchDataList", matchDataList);
                    mv.addObject("matchId", matchId);
                    mv.addObject("turn", nowTurn);
                }
            }
        }
        mv.setViewName("resultSetting/resultInput");
        return mv;
    }

    @RequestMapping ("resultInputLoad")
    public ModelAndView resultInputLoad (ModelAndView mv, String matchId, Integer ruleTurn) {

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

    @RequestMapping ("save")
    @ResponseBody
    public JsonResult save (MatchTeamData matchTeamData) {
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

    @RequestMapping ("resultDetail")
    public ModelAndView resultDetail (ModelAndView mv, String matchId, int turnNumber) {
        MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId(matchId);
        if (turnNumber > matchRule.getRuleTurn()) {
            turnNumber = matchRule.getRuleTurn();
        }
        //成绩td标题
        List<String> tdList = new ArrayList<>();
        String resultRule = StringUtils.isBlank(matchRule.getRuleResult()) ? Constants.result.DEFAULT_RESULT_RULE : matchRule.getRuleResult();
        for (String title : resultRule.split(",")) {
            tdList.add((String) Constants.resultRule.resultRuleMap.get(title));
        }
        mv.addObject("tdList", tdList);
        //成绩数据
        MatchTeamResult result = new MatchTeamResult();
        result.setMatchId(matchId);
        result.setTurnNumber(turnNumber);
        List<MatchTeamResultDto> resultlist = matchTeamResultService.sortMatchTeamResult(result);
        //根据规则显示相应的成绩
        SysSetting setting = matchRuleService.getSysSetting(matchId);
        List<List<BiValue<String, String>>> columnData = new ArrayList<>();

        try {
            Class clazz = MatchTeamResultDto.class;
            for (MatchTeamResultDto resultDto : resultlist) {
                List<BiValue<String, String>> dataItem = new ArrayList<>();
                for (BiValue<String, String> biValue : setting.getShowColumn()) {
                    BiValue tmpBiValue = new BiValue();
                    tmpBiValue.setValB(clazz.getMethod("get" + biValue.getValB()).invoke(resultDto).toString());
                    dataItem.add(tmpBiValue);
                }
                columnData.add(dataItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<List<List<BiValue<String, String>>>> resultData = new ArrayList<>();
        List<List<BiValue<String, String>>> tmp = null;
        int i = 0;

        for (List<BiValue<String, String>> item : columnData) {
            //matchTeamResultDto.setIndex(i + 1);
            if (i%setting.getData().get(SysSetting.Constants.INDEX_OF_SCORE_PAGE).getValA() == 0) {
                if (tmp != null) {
                    resultData.add(tmp);
                }

                tmp = new ArrayList<>();
            }
            tmp.add(item);
            i++;
        }

        if (tmp != null && tmp.size() > 0)
            resultData.add(tmp);

        List<DoubleList<List<BiValue<String, String>>>> rlt = new ArrayList<>();
        for (i = 0; i < resultData.size()/2; i++) {
            DoubleList<List<BiValue<String, String>>> di = new DoubleList<>();
            di.setMainList(resultData.get(2*i));
            di.setSubList(resultData.get(2*i + 1));
            rlt.add(di);
        }

        if (resultData.size()%2 != 0) {
            DoubleList<List<BiValue<String, String>>> di = new DoubleList<>();
            di.setMainList(resultData.get(resultData.size() - 1));
            di.setSubList(new ArrayList<>());
            rlt.add(di);
        }

        mv.addObject("matchName", matchRule.getMatchName());
        mv.addObject("turnNumberName", ChineseNum.getChineseNum(turnNumber));
        mv.addObject("turnNumber", turnNumber);
        mv.addObject("resultlist", resultlist);
        mv.addObject("resultData", rlt);
        mv.addObject("setting", setting);
        mv.addObject("interval", setting.getData().get(SysSetting.Constants.INDEX_OF_SCORE_PAGE).getValB()*1000);
        mv.setViewName("resultSetting/resultShow");
        return mv;
    }

    /**
     * 根据分组拿到成绩
     */
    @RequestMapping ("groupResultDetail")
    public ModelAndView showResultByGroupName (ModelAndView mv, Integer turnNumber, String matchId) {
        MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId(matchId);
        /*if (turnNumber > matchRule.getRuleTurn()) {
            turnNumber = matchRule.getRuleTurn();
        }*/
        //成绩td标题
        List<String> tdList = new ArrayList<>();
        String resultRule = StringUtils.isBlank(matchRule.getRuleResult()) ? Constants.result.DEFAULT_RESULT_RULE : matchRule.getRuleResult();
        for (String title : resultRule.split(",")) {
            tdList.add((String) Constants.resultRule.resultRuleMap.get(title));
        }
        mv.addObject("tdList", tdList);
        //成绩数据
        MatchTeamResult result = new MatchTeamResult();
        result.setMatchId(matchId);
        result.setTurnNumber(turnNumber > 100 ? matchRule.getRuleTurn() : turnNumber);
        List<MatchTeamResultDto> resultlist = matchTeamResultService.sortMatchTeamResult(result);
        //根据规则显示相应的成绩
        try {
            for (MatchTeamResultDto resultDto : resultlist) {
                String resultString = "";
                Class clazz = resultDto.getClass();
                for (String getName : resultRule.split(",")) {
                    resultString += clazz.getMethod("get" + getName).invoke(resultDto) + ",";
                }
                resultDto.setResultString(resultString.substring(0, resultString.length() - 1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获取所有的队伍信息  更具groupName分组
        List<MatchTeamMember> matchTeamMembers = matchTeamResultService.getAllTeams(matchId);
        Map<Integer, String> groupNameMap = new HashMap<>();
        for (MatchTeamMember member : matchTeamMembers) {
            groupNameMap.put(member.getTeamNumber(), member.getGroupName());
        }


        Map<String, List<MatchTeamResultDto>> groupByGroupName = new HashMap<>();
        List<MatchTeamResultDto> tmp;
        String groupName = null;
        for (MatchTeamResultDto matchTeamResultDto : resultlist) {
            groupName = groupNameMap.get(matchTeamResultDto.getTeamNumber());
            tmp = groupByGroupName.get(groupName);
            if (tmp == null) {
                tmp = new ArrayList<>();
            }
            tmp.add(matchTeamResultDto);
            groupByGroupName.put(groupName, tmp);
        }

        List<GroupScoreItem> resultData = new ArrayList<>();
        for (String gn : groupByGroupName.keySet()) {
            resultData.add(summary(resultRule, groupByGroupName.get(gn), gn));
        }

        groupByGroupName.clear();

        Collections.sort(resultData);

        SysSetting setting = matchRuleService.getSysSetting(matchId);
        if (turnNumber > 100) {
            //表示展示团队总成绩
            List<List<GroupScoreItem>> groupList = new ArrayList<>();
            List<GroupScoreItem> tmpList = null;
            int i = 0;
            for (GroupScoreItem resultDatum : resultData) {
                resultDatum.setIndex(i + 1);
                if (i%setting.getData().get(SysSetting.Constants.INDEX_OF_GROUP_SCORE_PAGE).getValA() == 0) {
                    if (tmpList != null) groupList.add(tmpList);
                    tmpList = new ArrayList<>();
                }

                tmpList.add(resultDatum);
                i++;
            }

            if (tmpList != null && tmpList.size() > 0) groupList.add(tmpList);
            mv.addObject("groupDataList", groupList);
        }

        mv.addObject("matchName", matchRule.getMatchName());
        mv.addObject("turnNumber", ChineseNum.getChineseNum(turnNumber));
        mv.addObject("resultlist", resultlist);
        mv.addObject("resultData", resultData);
        mv.addObject("interval", setting.getData().get(SysSetting.Constants.INDEX_OF_GROUP_SCORE_PAGE).getValB()*1000);
        mv.setViewName(turnNumber > 100 ? "resultSetting/groupTotal" : "resultSetting/groupResultShow");
        return mv;
    }

    public static GroupScoreItem summary (String rule, List<MatchTeamResultDto> list, String groupName) {
        GroupScoreItem res = new GroupScoreItem();
        if (StringUtils.isBlank(rule)) {
            rule = Constants.result.DEFAULT_RESULT_RULE;
        }

        Class clazz = MatchTeamResultDto.class;

        String[] ruleItem = rule.split(",");
        if (ruleItem.length > 0) {
            Object itemValue, myValue;
            for (MatchTeamResultDto data : list) {
                for (int i = 0; i < ruleItem.length; i++) {
                    try {
                        itemValue = clazz.getMethod("get" + ruleItem[i]).invoke(data);
                        myValue = clazz.getMethod("get" + ruleItem[i]).invoke(res);
                        if (myValue == null) myValue = 0;
                        clazz.getMethod("set" + ruleItem[i], Integer.class).invoke(res, ((int) itemValue) + ((int) myValue));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            list.get(i).setIndex(i + 1);
        }

        String resultString = "";
        for (String getName : ruleItem) {
            try {
                resultString += clazz.getMethod("get" + getName).invoke(res) + ",";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        res.setResultString(resultString.substring(0, resultString.length() - 1));

        res.setResultRule(rule);
        res.setGroupName(groupName);
        res.setData(list);
        return res;
    }

    @RequestMapping ("resultEdit")
    public ModelAndView editResult (ModelAndView mv, String matchId, Integer turnNumber) {
        MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId(matchId);
        if (turnNumber > matchRule.getRuleTurn()) {
            turnNumber = matchRule.getRuleTurn();
        }

        //成绩数据
        MatchTeamResult result = new MatchTeamResult();
        result.setMatchId(matchId);
        result.setTurnNumber(turnNumber);
        List<MatchTeamResultDto> resultlist = matchTeamResultService.sortMatchTeamResult(result);

        mv.addObject("turnNumberName", ChineseNum.getChineseNum(turnNumber));
        mv.addObject("turnNumber", turnNumber);
        mv.addObject("resultlist", resultlist);
        mv.addObject("matchId", matchId);
        mv.setViewName("resultSetting/resultEdit");
        return mv;
    }

    @RequestMapping ("resultEditDo")
    @ResponseBody
    public JsonResult resultEditDo (String teamId, Integer turnNumber, int val, String matchId) {
        //matchTeamResultService
        matchTeamResultService.editPersonalScore(teamId, turnNumber, val, matchId);
        return JsonResult.SUCCESS();
    }

}
