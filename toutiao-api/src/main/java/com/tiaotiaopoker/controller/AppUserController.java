package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.pojo.AppUser;
import com.tiaotiaopoker.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("user")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @RequestMapping(value = "centerInfo/{userId}",
            method = RequestMethod.POST)
    public JsonResult centerInfo(@PathVariable("userId") String userId) {
        JsonResult jsonResult;
        try {
            AppUser user = appUserService.getUserByUserId(userId);
            if (null == user) {
                jsonResult = JsonResult.FAILED("用户不存在");
            } else {
                Map<String, Object> resultMap = appUserService.getUserCenterInfo(userId);
                jsonResult = JsonResult.SUCCESS("成功", resultMap);
            }
        } catch (Exception e) {
            jsonResult = JsonResult.FAILED("个人中心接口异常——AppUserController——centerInfo");
            e.printStackTrace();
        }
        return jsonResult;
    }

    @RequestMapping("income/{userId}/{matchId}")
    public JsonResult income(@PathVariable("userId") String userId, @PathVariable("matchId") String matchId) {
        JsonResult jsonResult;
        try {
            AppUser user = appUserService.getUserByUserId(userId);
            if (null == user) {
                jsonResult = JsonResult.FAILED("用户不存在");
            } else {
                Map<String, Object> resultMap = appUserService.getUserIncome(userId, matchId);
                jsonResult = JsonResult.SUCCESS("成功", resultMap);
            }
        } catch (Exception e) {
            jsonResult = JsonResult.FAILED("用户收益接口异常——AppUserController——income");
            e.printStackTrace();
        }
        return jsonResult;
    }
}
