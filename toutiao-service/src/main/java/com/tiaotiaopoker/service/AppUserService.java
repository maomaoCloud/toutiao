package com.tiaotiaopoker.service;

import com.tiaotiaopoker.entity.AddUsersItemModel;
import com.tiaotiaopoker.pojo.AppUser;

import java.util.List;
import java.util.Map;

/**
 * Created by xiekang on 2018/9/17.
 */
public interface AppUserService {
    AppUser getUserByOpenId (String openId);

    void addAppUser (AppUser appUser);

    AppUser updateAppUserInfo (AppUser user);

    void updateUserApplyInfo (String userId,
                              String trueName,
                              String userPhone,
                              String userIdCard);

    AppUser getUserByUserId (String userId);

    Map<String, Object> getUserCenterInfo (String userId);

    Map<String, Object> getUserIncome (String userId, String matchId);

    List<AppUser> getUserByTrueName (String trueName);

    void importUserWithOrder (List<List<String>> dataList, String matchId);

    void updateUserInfoFromScreen (String orderId, String name, String phone, String idCard, Integer sign, String userType, String matchId, String userId);

    void addAppUserFromScreen(AddUsersItemModel users,String matchId);
}
