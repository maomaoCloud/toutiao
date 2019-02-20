package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.entity.MatchApplyUser;
import com.tiaotiaopoker.pojo.AppUser;
import com.tiaotiaopoker.pojo.MatchAuthorization;
import com.tiaotiaopoker.service.AppUserService;
import com.tiaotiaopoker.service.MatchAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("sys/matchAuthorController")
public class MatchAuthorizationController {

    @Autowired
    private MatchAuthorizationService authorizationService;

    @Autowired
    private AppUserService appUserService;

    @RequestMapping("searchUserByTrueName")
    @ResponseBody
    public List<AppUser> searchUserByTrueName(String trueName) {
        return appUserService.getUserByTrueName(trueName);
    }

    @RequestMapping("queryAuthorizationList")
    @ResponseBody
    public List<MatchAuthorization> queryAuthorizationList(String matchId){
        return authorizationService.queryAuthorizationList(matchId);
    }

}
