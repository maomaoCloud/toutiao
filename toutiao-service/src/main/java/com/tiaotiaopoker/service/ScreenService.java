package com.tiaotiaopoker.service;

import com.tiaotiaopoker.pojo.AppUser;

import java.util.Map;

/**
 * Created by xiekang on 2018/9/26.
 * 投屏和小程序交互的相关接口
 */
public interface ScreenService {
    /**
     * 用户登录，更新id相关的key的状态为已扫描，等待确认登录.
     */
    Map<String, Object> scan(String id,
                             String userId);

    /**
     * 用户确认登录， 更新key的状态为确认登录
     */
    Map<String, Object> loginConfirm(String id,
                                     String userId);

    void genLoginCode(String id);

    String checkLogin(String id);

    void login(String userId);

    AppUser getLoginUser(String token);
}
