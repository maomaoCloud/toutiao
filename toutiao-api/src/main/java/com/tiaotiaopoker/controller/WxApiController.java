package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.WxUtils;
import com.tiaotiaopoker.entity.ApiAppUserInfo;
import com.tiaotiaopoker.entity.WithdrawLimit;
import com.tiaotiaopoker.entity.WxLoginParam;
import com.tiaotiaopoker.pojo.AppUser;
import com.tiaotiaopoker.pojo.WithdrawLog;
import com.tiaotiaopoker.service.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiekang on 2018/9/11.
 */
@RestController()
@RequestMapping("wx")
@Scope("prototype")
public class WxApiController extends BaseController {
    @Autowired
    private WxApiService       wxApiService;
    @Autowired
    private AppUserService     appUserService;
    @Autowired
    private OrderService       orderService;
    @Autowired
    private WithdrawLogService withdrawLogService;
    @Value("${wx.withdraw.money.limit}")
    private Float              MONEY_LIMIT;
    @Value("${wx.withdraw.times.day.limit}")
    private Integer            TIMES_DAY_LIMIT;
    @Autowired
    private ScreenService      screenService;

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
    public JsonResult updateUserInfo(@RequestBody WxLoginParam param) {
        try {

            String userInfoJsonData = WxUtils.getUserInfo( param.getEncryptedData(), param.getSessionKey(),
                    param.getIv() );

            AppUser user = AppUser.genFromJsonData( userInfoJsonData, param.getUserId() );
            appUserService.updateAppUserInfo( user );

            ApiAppUserInfo apiAppUserInfo = ApiAppUserInfo.genFromAppUser( user );

            return JsonResult.SUCCESS( "success", apiAppUserInfo );
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.FAILED( "登录接口异常！" );
        }
    }

    /**
     * 提现接口
     */
    @RequestMapping(value = "pay/withdraw")
    @ResponseBody
    public JsonResult withdraw(String userId,
                               String openId,
                               Float money) {
        AppUser user = appUserService.getUserByUserId( userId );
        if( user == null || !openId.equals( user.getOpenId() ) || money > MONEY_LIMIT ) {
            return JsonResult.FAILED( "非法操作." );
        }

        //查看今天是否领取
        WithdrawLimit limit = withdrawLogService.getTodayWithdrawTimes( userId );
        if( limit.getTodayWithdrawTimes() >= TIMES_DAY_LIMIT ) {
            return JsonResult.FAILED( "今日提现次数已达上限！" );
        }

        if( limit.getTodayWithdrawTotalAmount() >= MONEY_LIMIT ) {
            return JsonResult.FAILED( "每日最多提现2w！" );
        }

        //查看领取金额是否合法

        String ip = getIpAddress();
        try {
            Map<String, Object> resultMap = withdrawLogService.withdraw( user, money, ip );
            return JsonResult.SUCCESS( "success", resultMap );
        } catch (Exception e) {
            return JsonResult.FAILED( "发放失败" );
        }
    }

    @RequestMapping("pay/notify")
    @ResponseBody
    public String payNotify(HttpServletRequest request) {
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            StringBuffer sb = new StringBuffer();
            is = request.getInputStream();
            isr = new InputStreamReader( is );
            br = new BufferedReader( isr );
            String s = "";
            while (( s = br.readLine() ) != null) {
                sb.append( s );
            }
            String str = sb.toString();
            System.out.println( "收到支付回调：" + str );
            orderService.updateOrder( str );
        } catch (Exception e) {
            e.printStackTrace();
            return returnXml( "FAILED", "NO" );
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return returnXml( "SUCCESS", "OK" );
    }

    private String returnXml(String return_code,
                             String return_msg) {
        StringBuilder sb = new StringBuilder();
        sb.append( "<xml><return_code><![CDATA[" ).append( return_code ).append(
                "]]></return_code><return_msg><![CDATA[" ).append( return_msg ).append( "]]></return_msg></xml>" );
        return sb.toString();
    }

    @RequestMapping("login/scan")
    public JsonResult loginScan(String id,
                                String userId) {
        try {
            Map<String, Object> resultMap = screenService.scan( id, userId );
            return JsonResult.SUCCESS( "success", resultMap );
        } catch (Exception e) {
            return JsonResult.FAILED( "接口异常！" );
        }
    }

    @RequestMapping("login/confirm")
    public JsonResult loginConfirm(String id,
                                   String userId) {
        try {
            Map<String, Object> resultMap = screenService.loginConfirm( id, userId );
            if( resultMap.get( "isOk" ).equals( true ) ) {
                //确认登录完毕后 读取用户信息到redis中
                screenService.login( userId );
            }

            return JsonResult.SUCCESS( "success", resultMap );
        } catch (Exception e) {
            return JsonResult.FAILED( "接口异常！" );
        }
    }
}
