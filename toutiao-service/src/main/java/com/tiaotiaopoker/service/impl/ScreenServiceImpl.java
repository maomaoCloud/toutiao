package com.tiaotiaopoker.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.pojo.AppUser;
import com.tiaotiaopoker.service.AppUserService;
import com.tiaotiaopoker.service.RedisService;
import com.tiaotiaopoker.service.ScreenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiekang on 2018/9/26.
 */
@Service
public class ScreenServiceImpl implements ScreenService {
    @Autowired
    private RedisService   redisService;
    @Value("${scan.login.key}")
    private String         SCAN_LOGIN_KEY_PATTERN;
    @Value("${scan.login.key.expire}")
    private Long           SCAN_EXPIRE_TIMEOUT;
    @Autowired
    private AppUserService appUserService;
    @Value("${login.user.key}")
    private String         login_user_key;
    @Value("${login.user.expire}")
    private Long           login_user_expire;


    @Override
    public Map<String, Object> scan(String id,
                                    String userId) {
        return loginDo( id, Constants.ScreenLoginStatue.LOGIN_STEP_1, Constants.ScreenLoginStatue.LOGIN_STEP_2 );
    }

    @Override
    public Map<String, Object> loginConfirm(String id,
                                            String userId) {
        return loginDo( id, Constants.ScreenLoginStatue.LOGIN_STEP_2,
                Constants.ScreenLoginStatue.LOGIN_STEP_3 + ":" + userId );
    }

    @Override
    public void genLoginCode(String id) {
        String key = genLoginScanKey( id );
        try {
            if( redisService.exists( key ) ) return;

            redisService.set( key, Constants.ScreenLoginStatue.LOGIN_STEP_1 );
            redisService.expire( key, SCAN_EXPIRE_TIMEOUT );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String checkLogin(String id) {
        String key = genLoginScanKey( id );
        try {
            Object resObj = redisService.get( key );
            if( resObj != null ) return resObj.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void login(String userId) {
        AppUser appUser = appUserService.getUserByUserId( userId );
        String key = genUserLoginKey( userId );
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonData = mapper.writeValueAsString( appUser );
            redisService.set( key, jsonData );
            redisService.expire( key, login_user_expire );
        } catch (Exception e) {
        }
    }

    @Override
    public AppUser getLoginUser(String token) {
        String key = genUserLoginKey( token );
        try {
            Object userObj = redisService.get( key );
            if( userObj != null && StringUtils.isNotBlank( userObj.toString() ) ) {
                return new ObjectMapper().readValue( userObj.toString(), AppUser.class );
            }
        } catch (Exception e) {
        }

        return null;
    }

    private String genUserLoginKey(String token) {
        return String.format( login_user_key, token );
    }

    private Map<String, Object> loginDo(String id,
                                        String currentStep,
                                        String nextStep) {
        Map<String, Object> resultMap = new HashMap<>();
        String key = genLoginScanKey( id );
        try {
            //查看 key 是否存在
            Object res = redisService.get( key );
            if( res == null || StringUtils.isBlank( res.toString() ) || !StringUtils.equals( res.toString(),
                    currentStep ) ) {
                resultMap.put( "isOk", false );
                resultMap.put( "msg", "二维码已过期！" );
                return resultMap;
            }

            //更改状态
            redisService.set( key, nextStep );
            resultMap.put( "isOk", true );
        } catch (Exception e) {
            resultMap.put( "isOk", false );
            resultMap.put( "msg", "缓存异常！" );
            return resultMap;
        }

        return resultMap;
    }

    /**
     * 组合用户登录的key
     */
    private String genLoginScanKey(String id) {
        return String.format( SCAN_LOGIN_KEY_PATTERN, id );
    }
}
