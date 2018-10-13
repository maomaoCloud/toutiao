package com.tiaotiaopoker.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.entity.ApiSimpleUserInfo;
import com.tiaotiaopoker.pojo.AppUser;
import com.tiaotiaopoker.pojo.ApplyOrder;
import com.tiaotiaopoker.pojo.MatchWithBLOBs;
import com.tiaotiaopoker.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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

    @Value("${scan.sign.key}")
    private String         scan_sign_key;
    @Value("${scan.sign.expire}")
    private Long           scan_sign_expire;
    @Autowired
    private OrderService   orderService;
    @Autowired
    private MatchService   matchService;

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

    @Override
    public void genSignCode(MatchWithBLOBs matchData,
                            String id) {
        //1.根据id生成key
        //2.设置过期时间为1天
        String key = genSignDataKey( id );
        try {
            redisService.set( key, matchData.getId() );
            redisService.expire( key, scan_sign_expire );
        } catch (Exception e) {
        }
    }

    @Override
    public Map<String, Object> loadUserSignInfo(String id,
                                                String userId) {
        Map<String, Object> resultMap = new HashMap<>();
        String key = genSignDataKey( id );
        Object o = redisService.get( key );
        if( o == null ) {
            return resultMap;
        }

        String matchId = o.toString();

        //1.查看是否已经签到
        //加载比赛信息
        MatchWithBLOBs matchData = matchService.getMatchDataById( matchId );
        if( matchData == null ) {
            return resultMap;
        }

        resultMap.put( "matchTheme", matchData.getTheme() );
        resultMap.put( "fee", matchData.getFee() );
        ApplyOrder order = orderService.getUserApplyData( userId, matchId );

        if( order == null ) {
            //查看此用户以B用户的身份是否签到
            order = orderService.getUserBData( userId, matchId );
            if( order != null ) {
                resultMap.put( "hasApply", true );
                resultMap.put( "canSign",false ); //此时表明B用户已经签到了
                return  resultMap;
            }

            //没有报名信息：
            resultMap.put( "hasApply", false );
            //加载所有的已经报名的A用户
            List<ApiSimpleUserInfo> aUsers = orderService.getUnsignAUsers( matchId );
            resultMap.put( "aUsers", aUsers );
        } else {
            resultMap.put( "hasApply", true );
            resultMap.put( "canSign", order.getUserSignStatus().equals( 0 ) ); //查看是否可以签到
        }

        return resultMap;
    }

    @Override
    public Map<String, Object> confirmSign(String id,
                                           String userId) {
        Map<String, Object> resultMap = new HashMap<>();
        String key = genSignDataKey( id );
        Object o = redisService.get( key );
        if( o == null ) return resultMap;

        String matchId = o.toString();
        orderService.updateUserASignStatue( matchId, userId );

        return resultMap;
    }

    @Override
    public Map<String, Object> confirmBUserSign(String id,
                                                String userId,
                                                String aId) {
        Map<String, Object> resultMap = new HashMap<>();
        String key = genSignDataKey( id );
        Object o = redisService.get( key );
        if( o == null ) return resultMap;

        String matchId = o.toString();
        AppUser bUser = appUserService.getUserByUserId( userId );
        ApplyOrder order = orderService.updateUserBSignStatue( matchId, aId, userId, bUser.getAvatarUrl() );

        appUserService.updateUserApplyInfo( userId, order.getPartnerName(), order.getPartnerPhone() );

        return resultMap;
    }

    @Override
    public String genSignDataKey(String id) {
        return String.format( scan_sign_key, id );
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
