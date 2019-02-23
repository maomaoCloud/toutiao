package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.ChineseNum;
import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.entity.MatchTeamDataDto;
import com.tiaotiaopoker.entity.SysSetting;
import com.tiaotiaopoker.pojo.*;
import com.tiaotiaopoker.service.MatchRuleService;
import com.tiaotiaopoker.service.MatchService;
import com.tiaotiaopoker.service.MatchTeamDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
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

    @Autowired
    private MatchService matchService;

    @RequestMapping ("index")
    public ModelAndView seatIndex (ModelAndView mv, String matchId) {
        if (!StringUtils.isBlank(matchId) && !matchId.equals("0")) {
            MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId(matchId);
            if (null == matchRule.getRuleSeat() || matchRule.getRuleTurn() == 0) {
                mv.addObject("errorMsg", "请选择首轮座位编排规则！");
            } else {
                MatchTeamData matchTeamData = new MatchTeamData();
                matchTeamData.setMatchId(matchId);
                List<MatchTeamDataDto> matchDataList = matchTeamDataService.queryTeamDataByCondition(matchTeamData);
                if (matchDataList.size() == 0) {
                    //查找当前比赛是否存在对局数据，若不存在则查找签到人员进行首轮排序
                    List<AppUser> userList = matchTeamDataService.sortMatchTeamByRuleSeat(matchRule.getRuleSeat(), matchId);
                    //签到人员不是4的倍数时，补全用户
                    while (userList.size()%4 != 0) {
                        AppUser user = new AppUser();
                        user.setId(String.valueOf(userList.size()));
                        user.setNickName("我是null");
                        user.setAvatarUrl(Constants.user.HEAD_IMG_URL);
                        userList.add(user);
                    }
                    mv.addObject("userList", userList);
                } else {
                    //已存在对局数据，则需要判断当前轮次是否是最终以录完成绩的数据
                    //获取当前轮次
                    int nowTurn = matchTeamDataService.getNowTurn(matchId);
                    if (nowTurn == 0) {
                        //最终成绩已生成，不得重新排座位
                        mv.addObject("msg", Constants.result.MSG_FINANL_TURN);
                    } else {
                        //最终成绩未生成
                        matchTeamData.setTurnNumber(nowTurn);
                        matchDataList = matchTeamDataService.queryTeamDataByCondition(matchTeamData);
                        mv.addObject("matchDataList", matchDataList);
                    }
                    mv.addObject("nowTurn", nowTurn);
                    mv.setViewName("seatSetting/seatShow");
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
    public JsonResult save (String userIds, String userNames, String userHeads, String matchId, Integer turnNumber) {
        JsonResult jsonResult;
        try {
            if (StringUtils.isBlank(userIds)) {
                jsonResult = JsonResult.FAILED("参数错误");
            } else {
                int result = matchTeamDataService.saveMatchTeamDataAndMember(userIds.split(","), userNames.split(","), userHeads.split(","), matchId, turnNumber);
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

    @RequestMapping ("update")
    @ResponseBody
    public JsonResult update (String teamIds, String matchId, Integer turnNumber) {
        JsonResult jsonResult;
        try {
            if (StringUtils.isBlank(teamIds)) {
                jsonResult = JsonResult.FAILED("参数错误");
            } else {
                int result = matchTeamDataService.updateMatchTeamData(teamIds.split(","), matchId, turnNumber);
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
        MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId(matchId);
        List<MatchTeamDataDto> dataList = matchTeamDataService.queryTeamDataByCondition(data);
        List<List<MatchTeamDataDto>> dataResult = new ArrayList<>();
        int i = 0;
        List<MatchTeamDataDto> tmp = null;

        SysSetting setting = matchRuleService.getSysSetting(matchId);
        for (MatchTeamDataDto matchTeamDataDto : dataList) {
            if (i%setting.getData().get(SysSetting.Constants.INDEX_OF_SEAT_PAGE).getValA() == 0) {
                if (tmp != null) dataResult.add(tmp);

                tmp = new ArrayList<>();
            }
            tmp.add(matchTeamDataDto);
            i++;
        }
        if (tmp != null && tmp.size() > 0) {
            dataResult.add(tmp);
        }

        mv.addObject("matchName", matchRule.getMatchName());
        mv.addObject("turnNumChines", ChineseNum.getChineseNum(turnNumber));
        mv.addObject("dataList", dataList);
        mv.addObject("dataResult", dataResult);
        mv.addObject("interval", setting.getData().get(SysSetting.Constants.INDEX_OF_SEAT_PAGE).getValB()*1000);
        mv.setViewName("seatSetting/seatShowDetail");
        return mv;
    }

    @RequestMapping ("seatPrint")
    public ModelAndView seatPrint (ModelAndView mv, String matchId, int turnNumber) {
        MatchTeamData data = new MatchTeamData();
        data.setMatchId(matchId);
        data.setTurnNumber(turnNumber);
        MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId(matchId);
        List<MatchTeamDataDto> dataList = matchTeamDataService.queryTeamDataByCondition(data);
        mv.addObject("matchDate", matchRule.getMatchDate());
        mv.addObject("matchName", matchRule.getMatchName());
        mv.addObject("dataList", dataList);
        mv.setViewName("seatSetting/seatPrint");
        return mv;
    }

    private List<AppUser> getUserList (MatchTeamDataDto data) {
        List<AppUser> userList = new ArrayList<>();
        AppUser user1 = new AppUser();
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
