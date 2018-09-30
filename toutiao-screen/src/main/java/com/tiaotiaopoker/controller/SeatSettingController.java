package com.tiaotiaopoker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("sys/seatSetting")
public class SeatSettingController {

    @RequestMapping("index")
    public ModelAndView seatIndex(ModelAndView mv) {

        mv.setViewName("seatSetting/seatIndex");
        return mv;
    }
}
