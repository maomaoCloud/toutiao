package com.tiaotiaopoker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("sys/matchSetting")
public class MatchSettingController {

    @RequestMapping("index")
    public ModelAndView index(ModelAndView mv){

        mv.setViewName("matchSetting/matchIndex");
        return  mv;
    }
}
