package com.tiaotiaopoker.service.impl;

import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.dao.ApplyOrderMapper;
import com.tiaotiaopoker.entity.ApiApplyParams;
import com.tiaotiaopoker.entity.WxPayData;
import com.tiaotiaopoker.pojo.ApplyOrder;
import com.tiaotiaopoker.pojo.MatchWithBLOBs;
import com.tiaotiaopoker.service.AppUserService;
import com.tiaotiaopoker.service.ApplyOrderService;
import com.tiaotiaopoker.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiekang on 2018/9/19.
 */
@Service
public class ApplyOrderServiceImpl implements ApplyOrderService {
    @Autowired
    private ApplyOrderMapper applyOrderMapper;
    @Autowired
    private MatchService     matchService;
    @Autowired
    private AppUserService   appUserService;

    @Value("${wx.appid}")
    private String WX_APPID;
    @Value("${wx.pay.unifiedorder}")
    private String WX_UNIFIEDORDER_URL;
    @Value("${wx.pay.key}")
    private String WX_KEY;
    @Value("${wx.pay.notify}")
    private String WX_NOTIFY;
    @Value("${wx.pay.trade_type}")
    private String WX_TRADE_TYPE;
    @Value("${wx.pay.mch_id}")
    private String WX_MCH_ID;

    @Override
    public Map<String, Object> addOrder(ApiApplyParams params) {
        MatchWithBLOBs match = matchService.getMatchDataById( params.getMatchId() );
        Map<String, Object> resultMap = new HashMap<>();
        if( match == null ) return resultMap;

        ApplyOrder order = new ApplyOrder();
        order.setId( StringUtils.generateOrderNo() );
        order.setUserId( params.getUserId() );
        order.setUserPhone( params.getUserPhone() );
        order.setUserName( params.getUserName() );
        order.setPartnerName( params.getPartnerName() );
        order.setPartnerPhone( params.getPartnerPhone() );
        order.setHasPartner( params.getHasPartner() ? 1 : 0 );
        order.setPrice( match.getFee() );
        order.setPayMoney( match.getFee() * params.getApplyCount() );
        order.setMatchId( params.getMatchId() );
        order.setUserHead( params.getUserHead() );
        order.setPartnerHead( params.getPartnerHead() );
        order.setAddTime( new Date() );
        order.setSharePercent( match.getFeeSharePercent() == null ? 1.0f : ( match.getFeeSharePercent() * 0.01f ) );
        order.setPayStatue( 0 );
        order.setUserSignStatus( 0 );
        order.setPartnerSignStatue( 0 );
        order.setPatnerCode( StringUtils.gen6Num() );
        applyOrderMapper.insertSelective( order );

        Boolean needPay = order.getPayMoney() > 0.0f;
        resultMap.put( "needPay", needPay );//是否需要支付
        resultMap.put( "orderId", order.getId() );
        if( needPay ) {
            resultMap.put( "payMoney", order.getPayMoney() );//如果需要支付，返回支付价格
        }

        if( params.getHasPartner() ) {
            resultMap.put( "partnerCode", order.getPatnerCode() );//如果有partner，返回code
        }

        //更新一下用户新信息
        appUserService.updateUserApplyInfo( params.getUserId(), params.getUserName(), params.getUserPhone() );

        //接下来需要统一下单了
        WxPayData payData = WxPayData.genFromOrder( order, params, match );
        installPayData( payData );

        return resultMap;
    }

    private void installPayData(WxPayData payData) {
        payData.setAppid( WX_APPID );
        payData.setKey( WX_KEY );
        payData.setMch_id( WX_MCH_ID );
        payData.setNotify_url( WX_NOTIFY );
        payData.setTrade_type( WX_TRADE_TYPE );
    }
}
