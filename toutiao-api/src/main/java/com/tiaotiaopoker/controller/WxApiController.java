package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.service.WxApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by xiekang on 2018/9/11.
 */
@RestController()
@RequestMapping("wx")
@Scope("prototype")
public class WxApiController {
    @Autowired
    private WxApiService wxApiService;

    @RequestMapping("sessionKey")
    public JsonResult getSessionKey(String code) {

        try {
            Map<String, Object> resultMap = wxApiService.getSessionKey( code );
            return JsonResult.SUCCESS( "success", resultMap );
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.FAILED( "接口异常！" );
        }
    }
}
