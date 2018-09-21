package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.AES;
import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.entity.ApiAppUserInfo;
import com.tiaotiaopoker.pojo.AppUser;
import com.tiaotiaopoker.service.AppUserService;
import com.tiaotiaopoker.service.WxApiService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.util.Map;

/**
 * Created by xiekang on 2018/9/11.
 */
@RestController()
@RequestMapping("wx")
@Scope("prototype")
public class WxApiController extends BaseController {
    @Autowired
    private WxApiService   wxApiService;
    @Autowired
    private AppUserService appUserService;

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

    @RequestMapping(value = "userInfo/update",
                    method = RequestMethod.POST)
    public JsonResult updateUserInfo(String encryptedData,
                                     String sessionKey,
                                     String iv,
                                     String userId) {
        try {
            String encodingFormat = "utf-8";
            String aesKey = new String( new BASE64Decoder().decodeBuffer( sessionKey ), encodingFormat );
            String aesIv = new String( new BASE64Decoder().decodeBuffer( iv ), encodingFormat );
            String userInfoJsonData = AES.getInstance().decrypt( encryptedData, encodingFormat, aesKey, aesIv );

            AppUser user = AppUser.genFromJsonData( userInfoJsonData, userId );
            appUserService.updateAppUserInfo( user );

            ApiAppUserInfo apiAppUserInfo = ApiAppUserInfo.genFromAppUser( user );

            return JsonResult.SUCCESS( "success", apiAppUserInfo );
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.FAILED( "登录接口异常！" );
        }
    }

}
