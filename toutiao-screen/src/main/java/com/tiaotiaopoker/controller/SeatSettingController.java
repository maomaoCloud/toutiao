package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.pojo.AppUser;
import com.tiaotiaopoker.pojo.MatchRule;
import com.tiaotiaopoker.service.MatchRuleService;
import com.tiaotiaopoker.service.MatchTeamDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("sys/seatSetting")
public class SeatSettingController {

    @Autowired
    private MatchRuleService matchRuleService;

    @Autowired
    private MatchTeamDataService matchTeamDataService;

    @RequestMapping("index")
    public ModelAndView seatIndex(ModelAndView mv, String matchId) {
        if (!StringUtils.isBlank(matchId)) {
            MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId(matchId);
            if (null == matchRule.getRuleSeat() || matchRule.getRuleTurn() == 0) {
                mv.addObject("errorMsg", "请选择首轮座位编排规则！");
            } else {
                List<AppUser> userList = new ArrayList<>();
                userList = matchTeamDataService.queryTeamUserByMatchId(matchId);
                if (userList.size() == 0) {
                    userList = matchTeamDataService.sortMatchTeamByRuleSeat(matchRule.getRuleSeat(), matchId);
                }
                mv.addObject("userlist", userList);
            }
        } else {
            mv.addObject("errorMsg", "请选择比赛！");
        }
        mv.setViewName("seatSetting/seatIndex");
        return mv;
    }
}
