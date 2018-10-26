package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.ChineseNum;
import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.entity.MatchTeamDataDto;
import com.tiaotiaopoker.pojo.AppUser;
import com.tiaotiaopoker.pojo.MatchRule;
import com.tiaotiaopoker.pojo.MatchTeamData;
import com.tiaotiaopoker.service.MatchRuleService;
import com.tiaotiaopoker.service.MatchTeamDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping ("sys/seatSetting")
public class SeatSettingController {

    @Autowired
    private MatchRuleService matchRuleService;

    @Autowired
    private MatchTeamDataService matchTeamDataService;

    @RequestMapping ("index")
    public ModelAndView seatIndex (ModelAndView mv, String matchId) {
        mv.setViewName("seatSetting/seatIndex2");
        if (1 == 1) return mv;
        if (!StringUtils.isBlank(matchId) && !matchId.equals("0")) {
            MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId(matchId);
            if (null == matchRule.getRuleSeat() || matchRule.getRuleTurn() == 0) {
                mv.addObject("errorMsg", "请选择首轮座位编排规则！");
            } else {
                MatchTeamData matchTeamData = new MatchTeamData();
                matchTeamData.setMatchId(matchId);
                matchTeamData.setTurnNumber(Constants.result.TURN_FIRST);
                List<MatchTeamDataDto> matchDataList = matchTeamDataService.queryTeamDataByCondition(matchTeamData);
                if (matchDataList.size() == 0) {
                    List<AppUser> userList = matchTeamDataService.sortMatchTeamByRuleSeat(matchRule.getRuleSeat(), matchId);
                    mv.addObject("userList", userList);
                    List<Integer> divList = new ArrayList<>();
                    if (userList != null && userList.size() > 0) {
                        divList = getDivList(userList.size());
                    }
                    mv.addObject("divList", divList);
                } else {
                    //首轮排序已完成跳转至展示页
                    List<AppUser> userList = new ArrayList<>();
                    for (MatchTeamDataDto data : matchDataList) {
                        userList.addAll(getUserList(data));
                    }
                    mv.setViewName("seatSetting/seatShow");
                    mv.addObject("divList", getDivList(userList.size()));
                    mv.addObject("userList", userList);
                    return mv;
                }
            }
        } else {
            mv.addObject("errorMsg", "请选择比赛！");
        }
        mv.setViewName("seatSetting/seatIndex1");
        return mv;
    }

    @RequestMapping ("save")
    @ResponseBody
    public JsonResult save (String[] userIds, String matchId, Integer turnNumber) {
        JsonResult jsonResult;
        try {
            if (userIds == null || userIds.length <= 0) {
                jsonResult = JsonResult.FAILED("参数错误");
            } else {
                int result = matchTeamDataService.saveMatchTeamDataAndMember(userIds, matchId, turnNumber);
                if (result > 0) {
                    jsonResult = JsonResult.SUCCESS("保存成功");
                } else {
                    jsonResult = JsonResult.FAILED("保存失败");
                }
            }
        } catch (Exception e) {
            jsonResult = JsonResult.FAILED("保存接口异常");
            e.printStackTrace();
        }
        return jsonResult;
    }

    @RequestMapping ("saveNext")
    @ResponseBody
    public JsonResult saveNext (String matchId, Integer turnNumber) {
        JsonResult jsonResult;
        try {
            MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId(matchId);
            if (matchRule.getRuleTurn() < turnNumber) {
                jsonResult = JsonResult.SUCCESS("已达最大轮次");
            } else {
                int result = matchTeamDataService.saveMatchTeamDataAndMemberNext(matchId, turnNumber);
                if (result > 0) {
                    jsonResult = JsonResult.SUCCESS("保存成功");
                } else {
                    jsonResult = JsonResult.FAILED("保存失败");
                }
            }
        } catch (Exception e) {
            jsonResult = JsonResult.FAILED("保存接口异常");
            e.printStackTrace();
        }
        return jsonResult;
    }

    @RequestMapping ("showDetail")
    public ModelAndView showDetail (ModelAndView mv, String matchId, int turnNumber) {

        MatchTeamData data = new MatchTeamData();
        data.setMatchId(matchId);
        data.setTurnNumber(turnNumber);
        MatchRule              matchRule = matchRuleService.selectMatchRuleByMatchId(matchId);
        List<MatchTeamDataDto> dataList  = matchTeamDataService.queryTeamDataByCondition(data);
        mv.addObject("matchName", matchRule.getMatchName());
        mv.addObject("turnNumChines", ChineseNum.getChineseNum(turnNumber));
        mv.addObject("dataList", dataList);
        mv.setViewName("seatSetting/seatShowDetail");
        return mv;
    }

    @RequestMapping ("seatPrint")
    public ModelAndView seatPrint (ModelAndView mv, String matchId, int turnNumber) {
        MatchTeamData data = new MatchTeamData();
        data.setMatchId(matchId);
        data.setTurnNumber(turnNumber);
        MatchRule              matchRule = matchRuleService.selectMatchRuleByMatchId(matchId);
        List<MatchTeamDataDto> dataList  = matchTeamDataService.queryTeamDataByCondition(data);
        mv.addObject("matchDate", matchRule.getMatchDate());
        mv.addObject("matchName", matchRule.getMatchName());
        mv.addObject("dataList", dataList);
        mv.setViewName("seatSetting/seatPrint");
        return mv;
    }

    private List<AppUser> getUserList (MatchTeamDataDto data) {
        List<AppUser> userList = new ArrayList<>();
        AppUser       user1    = new AppUser();
        user1.setTrueName(data.getTeamOneUserOneName());
        user1.setAvatarUrl(data.getTeamOneUserOneHead());
        userList.add(user1);
        AppUser user2 = new AppUser();
        user2.setTrueName(data.getTeamOneUserTwoName());
        user2.setAvatarUrl(data.getTeamOneUserTwoHead());
        userList.add(user2);
        AppUser user3 = new AppUser();
        user3.setTrueName(data.getTeamTwoUserOneName());
        user3.setAvatarUrl(data.getTeamTwoUserOneHead());
        userList.add(user3);
        AppUser user4 = new AppUser();
        user4.setTrueName(data.getTeamTwoUserTwoName());
        user4.setAvatarUrl(data.getTeamTwoUserTwoHead());
        userList.add(user4);
        return userList;
    }

    private List<Integer> getDivList (int index) {
        List<Integer> divList = new ArrayList<>();
        for (int i = 0; i < index/4; i++) {
            divList.add(i + 1);
        }
        if (index%4 != 0) {
            divList.add(index/4 + 1);
        }
        return divList;
    }

}
