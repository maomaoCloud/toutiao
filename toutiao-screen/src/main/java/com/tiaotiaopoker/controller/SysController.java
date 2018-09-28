package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.pojo.AppUser;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiekang on 2018/9/29.
 */
@Controller
@Scope("prototype")
@RequestMapping("sys")
public class SysController extends BaseController {

    @RequestMapping("index")
    @ResponseBody
    public AppUser index() {
        return getLoginUser();
    }
}
