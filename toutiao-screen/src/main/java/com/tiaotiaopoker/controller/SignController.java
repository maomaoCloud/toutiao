package com.tiaotiaopoker.controller;

import com.alibaba.fastjson.JSON;
import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.SecurityFactory;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.config.ViewConstants;
import com.tiaotiaopoker.entity.ApiSimpleUserInfo;
import com.tiaotiaopoker.entity.UserInfo;
import com.tiaotiaopoker.pojo.ApplyOrder;
import com.tiaotiaopoker.service.ApplyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by xiekang on 2018/10/9.
 */
@Controller
@RequestMapping("sys/sign")
@Scope("prototype")
public class SignController extends BaseController {
    @Value("${scan.sign.qrcode.url}")
    private String            qrcode_url_prefix;
    @Value("${scan.qrcode.key}")
    private String            qrcode_key;
    @Autowired
    private ApplyOrderService applyOrderService;

    @RequestMapping("match/{matchId}")
    public ModelAndView index(ModelAndView mv,
                              @PathVariable("matchId") String matchId) {
        String id = StringUtils.genRandomKey();
        String key = SecurityFactory.MD5.encrypt( id + qrcode_key, null );
        String qrcode_url = String.format( qrcode_url_prefix, id, matchId, key );
        mv.addObject( "qrcode_url", qrcode_url );
        mv.addObject( "id", id );
        mv.setViewName( ViewConstants.SIGN );

        List<ApplyOrder> signData = applyOrderService.getSignData( matchId );
        List<ApiSimpleUserInfo> signUsers = new ArrayList<>();
        List<ApiSimpleUserInfo> partnerUsers = new ArrayList<>();
        List<ApiSimpleUserInfo> waitApprove = new ArrayList<>();

        ApiSimpleUserInfo userInfo;
        for( ApplyOrder sd : signData ) {
            userInfo = ApiSimpleUserInfo.genFromApplyOrder( sd );
            if( sd.getUserSignStatus().equals( 3 ) ) {
                //这个是需要等待审核的
                waitApprove.add( userInfo );
            } else {
                signUsers.add( userInfo );
            }

            if( sd.getHasPartner().equals( 1 ) ) {//有搭档
                userInfo = ApiSimpleUserInfo.genPartnerFromApplyOrder( sd );
                partnerUsers.add( userInfo );
            }
        }
        mv.addObject( "signUsers", signUsers );
        mv.addObject( "partnerUsers", partnerUsers );
        mv.addObject( "waitApprove", waitApprove );

        return mv;
    }

    @RequestMapping("match/{matchId}/new")
    @ResponseBody
    public JsonResult showNewSign(@PathVariable("matchId") String matchId,
                                  Date datePoint) {
        //每次只获取datePoint之后的数据
        try {
            Map<String, Object> resultMap = applyOrderService.getNewSignData( matchId, datePoint );
            return JsonResult.SUCCESS( "success", resultMap );
        } catch (Exception e) {
        }
        return JsonResult.FAILED();
    }

    @RequestMapping("wait/approve")
    @ResponseBody
    public JsonResult waitApprove(String userId,
                                  String matchId,
                                  Integer doType) {
        //每次只获取datePoint之后的数据
        try {
            Integer nextStatue = doType.equals( 1 ) ? 1 : -1; //更新的签到状态  1为同意 -1为不同意
            applyOrderService.updateWaitApprove( userId, matchId, nextStatue );
            return JsonResult.SUCCESS();
        } catch (Exception e) {
        }
        return JsonResult.FAILED();
    }

    @RequestMapping("users/{matchId}")
    public ModelAndView users(ModelAndView mv,
                              @PathVariable("matchId") String matchId) {
        List<ApplyOrder> signData = applyOrderService.getSignData( matchId );
        List<UserInfo> datas = new ArrayList<>();
        for( ApplyOrder order : signData ) {
            datas.add( UserInfo.genFromApplyOrder( order ) );
        }

        mv.addObject( "datas", datas );
        mv.setViewName( ViewConstants.USERS );
        return mv;
    }
}
