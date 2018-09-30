package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.pojo.AppUser;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xiekang on 2018/9/29.
 */
@Controller
@Scope("prototype")
@RequestMapping("sys")
public class SysController extends BaseController {

    @RequestMapping("index")
    public ModelAndView index(ModelAndView mv) {
        AppUser user = getLoginUser();
        String token = user.getId();
        mv.addObject("token", token);
        mv.setViewName("common/left");
        return mv;
    }
}
