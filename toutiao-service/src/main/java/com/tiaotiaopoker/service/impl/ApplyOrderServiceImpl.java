package com.tiaotiaopoker.service.impl;

import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.XmlUtils;
import com.tiaotiaopoker.dao.ApplyOrderMapper;
import com.tiaotiaopoker.entity.ApiApplyParams;
import com.tiaotiaopoker.entity.ApiSimpleUserInfo;
import com.tiaotiaopoker.entity.WxPayData;
import com.tiaotiaopoker.entity.WxPayResultModel;
import com.tiaotiaopoker.net.HttpPosts;
import com.tiaotiaopoker.pojo.AppUser;
import com.tiaotiaopoker.pojo.ApplyOrder;
import com.tiaotiaopoker.pojo.ApplyOrderExample;
import com.tiaotiaopoker.pojo.MatchWithBLOBs;
import com.tiaotiaopoker.service.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

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
    @Autowired
    private RedisService     redisService;
    @Autowired
    private ScreenService    screenService;

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
    public Map<String, Object> addOrder(ApiApplyParams params) throws IOException, URISyntaxException {


        MatchWithBLOBs match = matchService.getMatchDataById( params.getMatchId() );
        Map<String, Object> resultMap = new HashMap<>();
        if( match == null ) return resultMap;

        AppUser user = appUserService.getUserByUserId( params.getUserId() );

        //查看此用户是否已经报名
        ApplyOrderExample example = new ApplyOrderExample();
        example.createCriteria().andUserIdEqualTo( params.getUserId() ).andMatchIdEqualTo( params.getMatchId() );
        List<ApplyOrder> applyOrders = applyOrderMapper.selectByExample( example );
        ApplyOrder order;
        Boolean needPay = true;
        resultMap.put( "hasPay", false );
        if( applyOrders != null && applyOrders.size() > 0 ) {
            order = applyOrders.get( 0 );
            if( order.getPayStatue() == 1 ) {
                resultMap.put( "hasPay", true );
                return resultMap;
            }
        } else {
            order = new ApplyOrder();
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
            order.setUserHead( user.getAvatarUrl() );
            order.setPartnerHead( params.getPartnerHead() );
            order.setAddTime( new Date() );
            order.setSharePercent(
                    match.getFeeSharePercent() == null ? 1.0f : 1.0f - ( match.getFeeSharePercent() * 0.01f ) );
            order.setPayStatue( 0 );
            order.setUserSignStatus( 0 );
            order.setPartnerSignStatue( 0 );
            order.setPatnerCode( StringUtils.gen6Num() );
            needPay = order.getPayMoney() > 0.0f;

            if( !needPay ) {//如果不需要支付
                order.setPayStatue( 1 );
                order.setPayDate( new Date() );
            }

            applyOrderMapper.insertSelective( order );
        }

        if( order.getPayStatue().equals( 1 ) ) {
            resultMap.put( "needPay", false );//是否需要支付
            return resultMap;
        }


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

        if( needPay ) {
            //接下来需要统一下单了
            WxPayData payData = WxPayData.genFromOrder( order, params, match );
            installPayData( payData );

            Map<String, Object> data = new HashMap<>();
            data.put( "data", payData.genXmlData() );

            System.out.println( "[" + new DateTime().toString( "yyyy-MM-dd" ) + "] 下单：" + data );

            String orderResult = HttpPosts.POST.request( WX_UNIFIEDORDER_URL, data );
            System.out.println( "[" + new DateTime().toString( "yyyy-MM-dd" ) + "] 下单结果：" + orderResult );
            Map<String, String> orderResultMap = XmlUtils.returnXMLData( orderResult );
            String return_code = orderResultMap.get( "return_code" );

            if( return_code.equals( "SUCCESS" ) ) {
                String prepay_id = orderResultMap.get( "prepay_id" );
                WxPayResultModel model = new WxPayResultModel();
                model.setAppId( WX_APPID );
                model.setKey( WX_KEY );
                model.setNonceStr( payData.getNonce_str() );
                model.setPackageName( prepay_id );
                model.setSignType( "MD5" );
                model.setTimeStamp( new Date().getTime() / 1000 );
                resultMap.put( "unifiedorder", true );
                resultMap.put( "payment", model );
            } else {
                resultMap.put( "unifiedorder", false );
            }

        }

        return resultMap;
    }

    @Override
    public Map<String, Object> addSignOrder(ApiApplyParams params) throws IOException, URISyntaxException {
        String key = screenService.genSignDataKey( params.getMatchId() );
        Map<String, Object> resultMap = new HashMap<>();
        Object o = redisService.get( key );
        if( o == null ) {
            return resultMap;
        }

        params.setMatchId( o.toString() );

        MatchWithBLOBs match = matchService.getMatchDataById( params.getMatchId() );
        if( match == null ) return resultMap;

        //查看此用户是否已经报名
        ApplyOrderExample example = new ApplyOrderExample();
        example.createCriteria().andUserIdEqualTo( params.getUserId() ).andMatchIdEqualTo( params.getMatchId() );
        List<ApplyOrder> applyOrders = applyOrderMapper.selectByExample( example );
        ApplyOrder order;
        Boolean needPay = true;
        resultMap.put( "hasPay", false );
        if( applyOrders != null && applyOrders.size() > 0 ) {
            order = applyOrders.get( 0 );
            if( order.getPayStatue() == 1 ) {
                resultMap.put( "hasPay", true );
                return resultMap;
            }
        } else {
            order = new ApplyOrder();
            order.setId( StringUtils.generateOrderNo() );
            order.setUserId( params.getUserId() );
            order.setUserPhone( params.getUserPhone() );
            order.setUserName( params.getUserName() );
            order.setHasPartner( 0 );
            order.setPrice( match.getFee() );
            order.setPayMoney( match.getFee() );
            order.setMatchId( params.getMatchId() );
            order.setUserHead( params.getUserHead() );
            order.setAddTime( new Date() );
            order.setSharePercent(
                    match.getFeeSharePercent() == null ? 1.0f : 1.0f - ( match.getFeeSharePercent() * 0.01f ) );
            order.setPayStatue( 0 );
            order.setUserSignStatus( 3 );//需要等待审核

            needPay = order.getPayMoney() > 0.0f;

            if( !needPay ) {//如果不需要支付
                order.setPayStatue( 1 );
                order.setPayDate( new Date() );
            }

            applyOrderMapper.insertSelective( order );
        }

        if( order.getPayStatue().equals( 1 ) ) {
            resultMap.put( "needPay", false );//是否需要支付
            return resultMap;
        }


        resultMap.put( "needPay", needPay );//是否需要支付
        resultMap.put( "orderId", order.getId() );
        if( needPay ) {
            resultMap.put( "payMoney", order.getPayMoney() );//如果需要支付，返回支付价格
        }

        //更新一下用户新信息
        appUserService.updateUserApplyInfo( params.getUserId(), params.getUserName(), params.getUserPhone() );

        if( needPay ) {
            //接下来需要统一下单了
            WxPayData payData = WxPayData.genFromOrder( order, params, match );
            installPayData( payData );

            Map<String, Object> data = new HashMap<>();
            data.put( "data", payData.genXmlData() );

            System.out.println( "[" + new DateTime().toString( "yyyy-MM-dd" ) + "] 下单：" + data );

            String orderResult = HttpPosts.POST.request( WX_UNIFIEDORDER_URL, data );
            System.out.println( "[" + new DateTime().toString( "yyyy-MM-dd" ) + "] 下单结果：" + orderResult );
            Map<String, String> orderResultMap = XmlUtils.returnXMLData( orderResult );
            String return_code = orderResultMap.get( "return_code" );

            if( return_code.equals( "SUCCESS" ) ) {
                String prepay_id = orderResultMap.get( "prepay_id" );
                WxPayResultModel model = new WxPayResultModel();
                model.setAppId( WX_APPID );
                model.setKey( WX_KEY );
                model.setNonceStr( payData.getNonce_str() );
                model.setPackageName( prepay_id );
                model.setSignType( "MD5" );
                model.setTimeStamp( new Date().getTime() / 1000 );
                resultMap.put( "unifiedorder", true );
                resultMap.put( "payment", model );
            } else {
                resultMap.put( "unifiedorder", false );
            }
        }

        return resultMap;
    }

    @Override
    public Map<String, Object> getNewSignData(String matchId,
                                              Date datePoint) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put( "datePoint", new DateTime().toString( "yyyy-MM-dd HH:mm:ss" ) );
        //查询所有的
        ApplyOrderExample example = new ApplyOrderExample();
        //支付了的 已经签到的 签到时间大于这个时间的
        example.createCriteria().andMatchIdEqualTo( matchId ).andPayStatueEqualTo( 1 ).andUserSignStatusEqualTo( 1 );
        List<ApplyOrder> applyOrders = applyOrderMapper.selectByExample( example );
        List<ApiSimpleUserInfo> aUsers = new ArrayList<>();
        List<ApiSimpleUserInfo> bUsers = new ArrayList<>();
        List<ApiSimpleUserInfo> cUsers = new ArrayList<>();
        ApiSimpleUserInfo userInfo;
        for( ApplyOrder order : applyOrders ) {
            userInfo = ApiSimpleUserInfo.genFromApplyOrder( order );
            aUsers.add( userInfo );
        }

        //获取签到了的B用户
        example = new ApplyOrderExample();
        example.createCriteria().andMatchIdEqualTo( matchId ).andPayStatueEqualTo( 1 ).andPartnerSignStatueEqualTo(
                1 );//.andPartnerSignDatetimeGreaterThanOrEqualTo( datePoint );
        applyOrders = applyOrderMapper.selectByExample( example );
        for( ApplyOrder order : applyOrders ) {
            userInfo = ApiSimpleUserInfo.genPartnerFromApplyOrder( order );
            bUsers.add( userInfo );
        }

        //获取空降人
        example = new ApplyOrderExample();
        example.setOrderByClause( "pay_date DESC" );
        example.createCriteria().andMatchIdEqualTo( matchId ).andPayStatueEqualTo( 1 ).andUserSignStatusEqualTo( 3 );
        applyOrders = applyOrderMapper.selectByExample( example );
        for( ApplyOrder order : applyOrders ) {
            userInfo = ApiSimpleUserInfo.genFromApplyOrder( order );
            cUsers.add( userInfo );
        }

        resultMap.put( "aUsers", aUsers );
        resultMap.put( "bUsers", bUsers );
        resultMap.put( "cUsers", cUsers );

        return resultMap;
    }

    @Override
    public List<ApplyOrder> getSignData(String matchId) {
        ApplyOrderExample example = new ApplyOrderExample();
        //拿到已经支付了的用户
        example.setOrderByClause( "pay_date" );
        example.createCriteria().andMatchIdEqualTo( matchId ).andPayStatueEqualTo( 1 ).andUserSignStatusNotEqualTo(
                -1 );

        return applyOrderMapper.selectByExample( example );
    }

    @Override
    public void updateWaitApprove(String userId,
                                  String matchId,
                                  Integer nextStatue) {
        ApplyOrderExample example = new ApplyOrderExample();
        example.createCriteria().andUserIdEqualTo( userId ).andMatchIdEqualTo( matchId );

        ApplyOrder order = new ApplyOrder();
        order.setUserSignStatus( nextStatue );
        applyOrderMapper.updateByExampleSelective( order, example );
    }

    private void installPayData(WxPayData payData) {
        payData.setAppid( WX_APPID );
        payData.setKey( WX_KEY );
        payData.setMch_id( WX_MCH_ID );
        payData.setNotify_url( WX_NOTIFY );
        payData.setTrade_type( WX_TRADE_TYPE );
    }
}
