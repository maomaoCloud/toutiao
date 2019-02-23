package com.tiaotiaopoker.controller;

import com.alibaba.fastjson.JSONObject;
import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.entity.BiValue;
import com.tiaotiaopoker.entity.SysSetting;
import com.tiaotiaopoker.pojo.MatchRule;
import com.tiaotiaopoker.service.MatchRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping ("sys/scrollSetting")
public class ScrollSettingController {
    @Autowired
    private MatchRuleService matchRuleService;

    @RequestMapping ("/page")
    public ModelAndView scrollSettingPage (String matchId) {
        ModelAndView mv = new ModelAndView("scrollSetting/page");
        SysSetting setting = matchRuleService.getSysSetting(matchId);
        mv.addObject("setting", setting);
        return mv;
    }


    @RequestMapping ("save")
    @ResponseBody
    public JsonResult saveSetting (String matchId, String scrollSetting, String hideColumn, String showColumn) {
        List<Integer> vals = Arrays.asList(scrollSetting.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());

        SysSetting setting = new SysSetting();

        List<BiValue<Integer, Integer>> data = new ArrayList<>();
        for (int i = 0; i < vals.size(); i += 2) {
            BiValue<Integer, Integer> item = new BiValue<>();
            item.setValA(vals.get(i));
            item.setValB(vals.get(i + 1));
            data.add(item);
        }
        setting.setData(data);

        String[] hide = hideColumn.split(",");
        List<BiValue<String, String>> hideColumnList = new ArrayList<>();
        for (String s : hide) {
            BiValue<String, String> item = new BiValue<>();
            item.setValB(s);
            item.setValA(Constants.ResultColumnMap.get(s));
            hideColumnList.add(item);
        }
        setting.setHideColumn(hideColumnList);


        String show[] = showColumn.split(",");
        List<BiValue<String, String>> showColumnList = new ArrayList<>();
        for (String s : show) {
            BiValue<String, String> item = new BiValue<>();
            item.setValB(s);
            item.setValA(Constants.ResultColumnMap.get(s));
            showColumnList.add(item);
        }
        setting.setShowColumn(showColumnList);

        String jsonSetting = JSONObject.toJSONString(setting);

        MatchRule rule = new MatchRule();
        rule.setMatchId(matchId);
        rule.setSysSetting(jsonSetting);
        matchRuleService.saveByMatchIdSelective(rule);
        return JsonResult.SUCCESS();
    }

}
