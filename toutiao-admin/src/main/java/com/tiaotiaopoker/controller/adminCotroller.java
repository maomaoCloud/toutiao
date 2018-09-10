package com.tiaotiaopoker.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class adminCotroller {

    @Value("${application.hello:Hello Angel}")
    private String hello;

    @RequestMapping("/helloJsp")
    public String helloJsp(Map<String, Object> map) {
        System.out.println("HelloController.helloJsp().hello=" + hello);
        map.put("hello", hello);
        return "helloJsp";
    }

    @RequestMapping("login")
    public ModelAndView admin(ModelAndView mv) {
        mv.setViewName("admin/login");
        return mv;
    }

    @RequestMapping("right")
    public ModelAndView right(ModelAndView mv) {

        mv.setViewName("admin/common/right");
        return mv;
    }
}
