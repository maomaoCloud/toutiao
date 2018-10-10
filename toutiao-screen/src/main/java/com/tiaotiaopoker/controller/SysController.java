package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.pojo.AppUser;
import com.tiaotiaopoker.pojo.MatchRule;
import com.tiaotiaopoker.pojo.MatchWithBLOBs;
import com.tiaotiaopoker.service.MatchRuleService;
import com.tiaotiaopoker.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiekang on 2018/9/29.
 */
@Controller
@Scope("prototype")
@RequestMapping("sys")
public class SysController extends BaseController {

    @Autowired
    private MatchRuleService matchRuleService;

    @RequestMapping("index")
    public ModelAndView index(ModelAndView mv, String matchId) {
        AppUser user = getLoginUser();
        String token = user.getId();
        mv.addObject("token", token);

        //根据matchId查出比赛规则（轮次）
        if (!StringUtils.isBlank(matchId)) {
            MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId(matchId);
            int ruleTurn = (null == matchRule ? 2 : matchRule.getRuleTurn());
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
            mv.addObject("matchId",matchId);
            mv.addObject("ruleTurnList", ruleTurnList);
        }
        mv.setViewName("common/left");
        return mv;
    }
}
