package com.tiaotiaopoker.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.QrCodeCreateUtil;
import com.tiaotiaopoker.SecurityFactory;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.entity.ApiApplyParams;
import com.tiaotiaopoker.pojo.AppUser;
import com.tiaotiaopoker.pojo.Match;
import com.tiaotiaopoker.pojo.MatchWithBLOBs;
import com.tiaotiaopoker.service.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by xiekang on 2018/9/9.
 */
@RestController()
@RequestMapping("match")
@Scope("prototype")
public class MatchController extends BaseController {
    @Autowired
    private MatchService      matchService;
    @Autowired
    private ApplyOrderService applyOrderService;
    @Autowired
    private AppUserService    appUserService;
    @Value("${scan.qrcode.key}")
    private String            qrcode_key;
    @Autowired
    private ScreenService     screenService;

    @RequestMapping(value = "add",
                    method = RequestMethod.POST)
    public JsonResult addMatch(@RequestBody MatchWithBLOBs data) {
        try {
            matchService.saveMatch( data );
            return JsonResult.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.FAILED( "添加失败！" );
        }
    }

    @RequestMapping(value = "{pageNum}/{pageSize}/{userId}",
                    method = RequestMethod.POST)
    public JsonResult getMatch(@PathVariable("pageNum") Integer pageNum,
                               @PathVariable("pageSize") Integer pageSize,
                               @PathVariable("userId") String userId) {
        JsonResult jsonResult;
        try {
            Map<String, Object> resultMap = matchService.getMatchList( pageNum, pageSize, userId );
            jsonResult = JsonResult.SUCCESS();
            jsonResult.setResData( resultMap );
        } catch (Exception e) {
            jsonResult = JsonResult.FAILED( "赛事列表接口异常" );
            e.printStackTrace();
        }
        return jsonResult;
    }

    @RequestMapping("detail/{matchId}/{userId}")
    public JsonResult getMatchDetail(@PathVariable("matchId") String matchId,
                                     @PathVariable("userId") String userId) {
        JsonResult jsonResult;
        try {
            Map<String, Object> resultMap = matchService.getMatchInfoById( matchId, userId );
            jsonResult = JsonResult.SUCCESS();
            jsonResult.setResData( resultMap );
        } catch (Exception e) {
            jsonResult = JsonResult.FAILED( "赛事详情接口异常" );
            e.printStackTrace();
        }
        return jsonResult;
    }

    @RequestMapping("apply")
    public JsonResult apply(@RequestBody ApiApplyParams params) {
        try {
            params.setRequestIp( getIpAddress() );
            Map<String, Object> resultMap = applyOrderService.addOrder( params );
            return JsonResult.SUCCESS( "success", resultMap );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonResult.FAILED( "报名失败！" );
    }

    /**
     * 签到的时候的报名
     */
    @RequestMapping("sign/apply")
    public JsonResult signApply(@RequestBody ApiApplyParams params) {
        try {
            params.setRequestIp( getIpAddress() );
            Map<String, Object> resultMap = applyOrderService.addSignOrder( params );
            return JsonResult.SUCCESS( "success", resultMap );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonResult.FAILED( "报名失败！" );
    }

    @RequestMapping("manage/{userId}")
    public JsonResult manage(@PathVariable("userId") String userId) {
        JsonResult jsonResult;
        try {
            AppUser user = appUserService.getUserByUserId( userId );
            if( null == user ) {
                jsonResult = JsonResult.FAILED( "用户不存在" );
            } else {
                Map<String, Object> resultMap = matchService.getMatchListManage( userId );
                jsonResult = JsonResult.SUCCESS( "成功", resultMap );
            }
        } catch (Exception e) {
            jsonResult = JsonResult.FAILED( "比赛接口异常——MatchController——manage" );
            e.printStackTrace();
        }
        return jsonResult;

    }

    /**
     * 生成签到二维码
     */
    @RequestMapping("sign/qrcode/{id}/{matchId}")
    public void genSignQrCode(HttpServletRequest request,
                              HttpServletResponse response,
                              @PathVariable("id") String id,
                              @PathVariable("matchId") String matchId,
                              String key) throws Exception {
        if( StringUtils.isBlank( id ) || StringUtils.isBlank( key ) || StringUtils.isBlank( matchId ) ) {
            return;
        }

        String serverKey = SecurityFactory.MD5.encrypt( id + qrcode_key, null );
        if( !serverKey.toUpperCase().equals( key.toUpperCase() ) ) {
            return;
        }

        //检查当前比赛是否合法
        MatchWithBLOBs matchData = matchService.checkIsValidate( matchId );
        if( matchData == null ) {
            return;
        }

        response.setContentType( "image/jpeg" );
        // 禁止图像缓存。
        response.setHeader( "Pragma", "no-cache" );
        response.setHeader( "Cache-Control", "no-cache" );
        response.setDateHeader( "Expires", 0 );
        screenService.genSignCode( matchData, id );
        JSONObject jo = new JSONObject();
        jo.put( "action", "sign" );
        jo.put( "id", id );
        //向页面输出验证码图片
        QrCodeCreateUtil.createQrCode( response.getOutputStream(), jo.toJSONString(), 600, "JPEG" );
    }

    @RequestMapping("sign/userId/{userId}/id/{id}")
    @ResponseBody
    public JsonResult sign(@PathVariable("userId") String userId,
                           @PathVariable("id") String id) {
        try {
            Map<String, Object> resultMap = screenService.loadUserSignInfo( id, userId );
            return JsonResult.SUCCESS( "success", resultMap );
        } catch (Exception e) {
            ;
        }
        return JsonResult.FAILED();
    }

    @RequestMapping("sign/confirm")
    @ResponseBody
    public JsonResult signConfirm(String userId,
                                  String id) {
        try {
            Map<String, Object> resultMap = screenService.confirmSign( id, userId );
            return JsonResult.SUCCESS( "success", resultMap );
        } catch (Exception e) {
            ;
        }
        return JsonResult.FAILED();
    }

    @RequestMapping("bSign/userId/{userId}/aId/{aId}/id/{id}")
    @ResponseBody
    public JsonResult bSign(@PathVariable("userId") String userId,
                            @PathVariable("id") String id,
                            @PathVariable("aId") String aId) {
        try {
            Map<String, Object> resultMap = screenService.confirmBUserSign( id, userId, aId );
            return JsonResult.SUCCESS( "success", resultMap );
        } catch (Exception e) {
            ;
        }
        return JsonResult.FAILED();
    }
}
