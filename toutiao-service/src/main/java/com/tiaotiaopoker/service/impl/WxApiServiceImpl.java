package com.tiaotiaopoker.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tiaotiaopoker.net.HttpClient;
import com.tiaotiaopoker.net.HttpGet;
import com.tiaotiaopoker.service.WxApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiekang on 2018/9/11.
 */
@Service
public class WxApiServiceImpl implements WxApiService {
    @Value("${wx.appid}")
    private String WX_APPID;
    @Value("${wx.secret}")
    private String WX_SECRET;
    @Value("${wx.api.session}")
    private String WX_API_SESSION_URI;

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

            resultMap.put( "sessionKey", sessionKey );
            resultMap.put( "openId", openId );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }
}
