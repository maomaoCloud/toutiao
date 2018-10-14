package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.config.ViewConstants;
import com.tiaotiaopoker.pojo.AppUser;
import com.tiaotiaopoker.pojo.MatchRule;
import com.tiaotiaopoker.service.MatchRuleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ModelAndView index(ModelAndView mv,
                              String matchId) {
        AppUser user = getLoginUser();
        String token = user.getId();
        mv.addObject( "token", token );

        //根据matchId查出比赛规则（轮次）
        if( !StringUtils.isBlank( matchId ) ) {
            MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId( matchId );
            int ruleTurn = ( null == matchRule ? 2 : matchRule.getRuleTurn() );
            List<String> ruleTurnList = new ArrayList<>();
            for( int i = 1; i <= ruleTurn; i++ ) {
                ruleTurnList.add( Constants.NUM_CH[i - 1] );
            }
            mv.addObject( "matchId", matchId );
            mv.addObject( "ruleTurnList", ruleTurnList );
        }
        mv.setViewName( "common/left" );
        return mv;
    }

    @RequestMapping("layout")
    public String scr(Model model) {
        return ViewConstants.LAYOUT;
    }

    @RequestMapping("countDown/{matchId}")
    public ModelAndView countDown(ModelAndView mv,
                                  @PathVariable("matchId") String matchId) {

        MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId( matchId );
        Integer totalSeconds = matchRule.getRuleTime() * 60;
        mv.addObject( "totalSeconds", totalSeconds );

        String logo = matchRule.getMatchLogo();
        Boolean hasLogo = StringUtils.isNotBlank( logo );
        mv.addObject( "logo", logo );
        mv.addObject( "hasLogo", hasLogo );

        String host = matchRule.getMatchHost();
        Boolean hasHost = StringUtils.isNotBlank( host );
        mv.addObject( "host", host );
        mv.addObject( "hasHost", hasHost );

        String theme = matchRule.getMatchName();
        mv.addObject( "theme", theme );

        mv.setViewName( ViewConstants.COUNTDOWN );
        return mv;
    }

    @RequestMapping("rule/{matchId}")
    public ModelAndView rule(ModelAndView mv,
                             @PathVariable("matchId") String matchId) {
        MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId( matchId );
        String totalTurn = Constants.NUM_CH[matchRule.getRuleTurn() - 1];
        Integer ruleDraw = matchRule.getRuleDraw();
        String canDraw = ruleDraw.equals( 1 ) ? "否" : "是";
        String draw = ruleDraw.equals( 1 ) ? "不可平" : ruleDraw.toString();
        String rules[] = { " 临近编排", "首尾编排", " 拦腰编排" };
        String firstSeatRuleName = rules[matchRule.getRuleSeat() - 1];

        mv.addObject( "totalTurn", totalTurn );
        mv.addObject( "draw", draw );
        mv.addObject( "canDraw", canDraw );
        mv.addObject( "firstSeatRuleName", firstSeatRuleName );
        mv.addObject( "data", matchRule );

        mv.setViewName( ViewConstants.RULE );
        return mv;
    }
}
