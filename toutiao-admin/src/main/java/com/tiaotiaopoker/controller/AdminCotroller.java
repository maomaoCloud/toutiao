package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.pojo.SysUser;
import com.tiaotiaopoker.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class AdminCotroller {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private HttpSession session;

    @Value("${application.hello:Hello Angel}")
    private String hello;

    @RequestMapping("/helloJsp")
    public String helloJsp(Map<String, Object> map) {
        System.out.println("HelloController.helloJsp().hello=" + hello);
        map.put("hello", hello);
        return "helloJsp";
    }

    @RequestMapping("/")
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("admin/login");
        return mv;
    }

    @RequestMapping("login")
    public ModelAndView admin(ModelAndView mv) {
        mv.setViewName("admin/login");
        return mv;
    }

    @RequestMapping("loginCheckSysUser")
    @ResponseBody
    public JsonResult loginCheckSysUser(ModelAndView mv, SysUser loginUser, HttpSession session) {
        JsonResult jsonResult;
        try {
            SysUser user = sysUserService.checkAccountAndPassword(loginUser.getUserAccount(), loginUser.getUserPassword());
            if (null != user) {
                session.setAttribute("sysUser", user);
                jsonResult = JsonResult.SUCCESS("登录成功！！");
            } else {
                jsonResult = JsonResult.FAILED("用户名或者密码错误！！");
            }
        } catch (Exception e) {
            jsonResult = JsonResult.FAILED("登录接口异常————adminCotroller————loginCheckSysUser");
            e.printStackTrace();
        }
        return jsonResult;
    }

    // 推出登录
    @RequestMapping("sysUserLoginOut")
    public ModelAndView sysUserLoginOut(HttpSession session,ModelAndView mv) {
        SysUser user  = (SysUser)session.getAttribute("sysUser");
        if (user != null) {
            session.removeAttribute("sysUser");
        }
        mv.setViewName("admin/login");
        return mv;
    }

    @RequestMapping("right")
    public ModelAndView right(ModelAndView mv,HttpSession session) {
        SysUser sysUser = (SysUser) session.getAttribute("sysUser");
        if (null == sysUser){
            mv.setViewName("admin/login");
        }else {
            mv.setViewName("admin/common/right");
            mv.addObject("sysUser",sysUser);
        }
        return mv;
    }
}
