package com.tiaotiaopoker.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.entity.ApiAppUserInfo;
import com.tiaotiaopoker.net.HttpClient;
import com.tiaotiaopoker.net.HttpGet;
import com.tiaotiaopoker.pojo.AppUser;
import com.tiaotiaopoker.service.AppUserService;
import com.tiaotiaopoker.service.WxApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiekang on 2018/9/11.
 */
@Service
public class WxApiServiceImpl implements WxApiService {
    @Value("${wx.appid}")
    private String         WX_APPID;
    @Value("${wx.secret}")
    private String         WX_SECRET;
    @Value("${wx.api.session}")
    private String         WX_API_SESSION_URI;
    @Autowired
    private AppUserService appUserService;

    @Override
    public Map<String, Object> getSessionKey(String code) {
        Map<String, Object> resultMap = new HashMap<>();
        String url = String.format( WX_API_SESSION_URI, WX_APPID, WX_SECRET, code );

        HttpClient client = new HttpGet();

        try {
            String data = client.request( url, null );
            JSONObject jo = JSONObject.parseObject( data );
            String sessionKey = jo.getString( "session_key" );
            String openId = jo.getString( "openid" );

            AppUser appUser = appUserService.getUserByOpenId( openId );
            if( appUser == null ) {
                appUser = new AppUser();
                appUser.setId( StringUtils.generateShortUUID() );
                appUser.setRegDate( new Date() );
                appUser.setUserStatus( 0 );
                appUser.setUserLevel( 0 );
                appUser.setUserFrom( 0 );
                appUser.setOpenId( openId );
                appUser.setLastLoginDateTime( new Date() );
                appUserService.addAppUser( appUser );
            }

            ApiAppUserInfo apiUserInfo = ApiAppUserInfo.genFromAppUser( appUser );
            resultMap.put( "userInfo", apiUserInfo );
            resultMap.put( "sessionKey", sessionKey );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }
}
