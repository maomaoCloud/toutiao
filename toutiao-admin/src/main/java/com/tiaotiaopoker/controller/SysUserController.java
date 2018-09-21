package com.tiaotiaopoker.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.common.Pagination;
import com.tiaotiaopoker.pojo.SysUser;
import com.tiaotiaopoker.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private HttpSession session;

    @RequestMapping("addAndEditPage")
    public ModelAndView addAndEditPage(ModelAndView mv, @ModelAttribute(value = "user") SysUser user) {
        if (!StringUtils.isBlank(user.getUserId())) {
            user = sysUserService.queryUserById(user.getUserId());
        }
        mv.addObject("user", user);
        mv.setViewName("admin/sysUser/addPage");
        return mv;
    }

    @RequestMapping(value = "addOrEdit", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addOrEdit(@ModelAttribute(value = "user") SysUser user) {
        JsonResult jsonResult;
        SysUser loginUser = (SysUser) session.getAttribute("sysUser");
        try {
            if (null == loginUser) {
                jsonResult = JsonResult.FAILED("nologin");
            } else {
                SysUser sysUser = new SysUser();
                sysUser.setUserAccount(user.getUserAccount());
                List<SysUser> userList = sysUserService.queryUserByCondition(user, null);
                if (userList.size() > 0 && (StringUtils.isBlank(user.getUserId()) || !user.getUserId().equals(userList.get(0).getUserId()))) {
                    jsonResult = JsonResult.FAILED("用户名被占用！");
                } else {
                    int result = sysUserService.addOrUpdateUser(user,loginUser);
                    if (result > 0) {
                        jsonResult = JsonResult.SUCCESS("编辑成功");
                    } else {
                        jsonResult = JsonResult.FAILED("编辑失败");
                    }
                }
            }
        } catch (Exception e) {
            jsonResult = JsonResult.FAILED("编辑接口异常");
            e.printStackTrace();
        }
        return jsonResult;
    }

    /*列表页*/
    @RequestMapping("listPage")
    public ModelAndView listPage(ModelAndView mv,
                                 @ModelAttribute(value = "page") Pagination page,
                                 @ModelAttribute(value = "user") SysUser user) {
        PageHelper.startPage(page.getPage(), page.getRows());
        List<SysUser> list = sysUserService.queryUserByCondition(user, page);
        mv.addObject("news", user);
        mv.addObject("page", page);
        mv.addObject("list", list);
        mv.setViewName("admin/sysUser/listPage");
        return mv;
    }

    /*更新接口*/
    @RequestMapping("update")
    @ResponseBody
    public JsonResult userUpdate(SysUser user) {
        JsonResult jsonResult;
        SysUser loginUser = (SysUser) session.getAttribute("sysUser");
        try {
            if (null == loginUser) {
                jsonResult = JsonResult.FAILED("nologin");
            } else {
                if (StringUtils.isBlank(user.getUserId())) {
                    jsonResult = JsonResult.FAILED("id缺失");
                } else {
                    int result = sysUserService.editUserBySelective(user,loginUser);
                    if (result > 0) {
                        jsonResult = JsonResult.SUCCESS("更新成功");
                    } else {
                        jsonResult = JsonResult.FAILED("更新失败");
                    }
                }
            }
        } catch (Exception e) {
            jsonResult = JsonResult.FAILED("更新接口异常");
            e.printStackTrace();
        }
        return jsonResult;
    }
}