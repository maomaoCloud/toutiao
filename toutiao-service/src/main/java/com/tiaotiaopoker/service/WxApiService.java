package com.tiaotiaopoker.service;

import java.util.Map;

/**
 * Created by xiekang on 2018/9/11.
 */
public interface WxApiService {
    Map<String, Object> getSessionKey(String code);
}
