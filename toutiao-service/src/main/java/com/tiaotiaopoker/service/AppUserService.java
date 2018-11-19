package com.tiaotiaopoker.service;

import com.tiaotiaopoker.pojo.AppUser;

import java.util.Map;

/**
 * Created by xiekang on 2018/9/17.
 */
public interface AppUserService {
    AppUser getUserByOpenId (String openId);

    void addAppUser (AppUser appUser);

    void updateAppUserInfo (AppUser user);

    void updateUserApplyInfo (String userId,
                              String trueName,
                              String userPhone,
                              String userIdCard);

    AppUser getUserByUserId (String userId);

    Map<String, Object> getUserCenterInfo (String userId);

    Map<String, Object> getUserIncome (String userId, String matchId);

}
