package com.tiaotiaopoker.service.impl;

import com.tiaotiaopoker.XmlUtils;
import com.tiaotiaopoker.dao.ApplyOrderMapper;
import com.tiaotiaopoker.dao.CustomerDaoMapper;
import com.tiaotiaopoker.dao.WxPayLogMapper;
import com.tiaotiaopoker.entity.ApiSimpleUserInfo;
import com.tiaotiaopoker.entity.MatchApplyUser;
import com.tiaotiaopoker.pojo.ApplyOrder;
import com.tiaotiaopoker.pojo.ApplyOrderExample;
import com.tiaotiaopoker.pojo.WxPayLog;
import com.tiaotiaopoker.service.OrderService;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by xiekang on 2018/9/14.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ApplyOrderMapper  applyOrderMapper;
    @Autowired
    private WxPayLogMapper    wxPayLogMapper;
    @Autowired
    private CustomerDaoMapper customerDaoMapper;

    @Override
    public List<MatchApplyUser> getApplyUserByMatchId(String matchId) {

        return customerDaoMapper.getMatchApplyUserList( matchId );
    }

    @Override
    public void updateOrder(String xmlData) {
        Map<String, String> resultMap = XmlUtils.returnXMLData( xmlData );
        String return_code = resultMap.get( "return_code" );
        String return_msg = resultMap.get( "return_msg" );
        if( return_code.equals( "SUCCESS" ) ) {
            String out_trade_no = resultMap.get( "out_trade_no" );
            String transaction_id = resultMap.get( "transaction_id" );
            int total_fee = Integer.parseInt( resultMap.get( "total_fee" ) );
            Date payDate = DateTimeFormat.forPattern( "yyyyMMddHHmmss" ).parseDateTime(
                    resultMap.get( "time_end" ) ).toDate();
            updateOrderPaySuccess( out_trade_no, payDate );

            WxPayLog wxPayLog = new WxPayLog();
            wxPayLog.setAddTime( new Date() );
            wxPayLog.setNotifyData( xmlData );
            wxPayLog.setOutTradeNo( out_trade_no );
            wxPayLog.setPayFee( total_fee );
            wxPayLog.setPaySuccessTime( payDate );
            wxPayLog.setReturnCode( return_code );
            wxPayLog.setReturnMsg( return_msg );
            wxPayLog.setTransactionId( transaction_id );
            wxPayLogMapper.insert( wxPayLog );
        }
    }

    @Override
    public ApplyOrder getUserApplyData(String userId,
                                       String matchId) {
        ApplyOrderExample example = new ApplyOrderExample();
        example.createCriteria().andUserIdEqualTo( userId ).andMatchIdEqualTo( matchId ).andPayStatueEqualTo( 1 );
        List<ApplyOrder> applyOrders = applyOrderMapper.selectByExample( example );
        if( applyOrders != null && applyOrders.size() > 0 ) return applyOrders.get( 0 );
        return null;
    }

    @Override
    public List<ApiSimpleUserInfo> getUnsignAUsers(String matchId) {
        ApplyOrderExample example = new ApplyOrderExample();
        //已经支付 有搭档   搭档未签到
        example.createCriteria().andMatchIdEqualTo( matchId ).andHasPartnerEqualTo( 1 ).andPayStatueEqualTo(
                1 ).andPartnerSignStatueEqualTo( 0 );
        List<ApiSimpleUserInfo> resultList = new ArrayList<>();
        List<ApplyOrder> applyOrders = applyOrderMapper.selectByExample( example );
        if( applyOrders != null && applyOrders.size() > 0 ) {
            ApiSimpleUserInfo info;
            for( ApplyOrder order : applyOrders ) {
                info = new ApiSimpleUserInfo();
                info.setHead( order.getUserHead() );
                info.setName( order.getUserName() );
                info.setPhone( order.getUserPhone() );
                info.setUserId( order.getUserId() );
                info.setPartnerName( order.getPartnerName() );
                info.setPartnerPhone( order.getPartnerPhone() );
                info.setPartnerUserId( order.getPartnerId() );

                resultList.add( info );
            }
        }

        return resultList;
    }

    @Override
    public ApplyOrder updateUserASignStatue(String matchId,
                                            String userId) {
        ApplyOrderExample example = new ApplyOrderExample();
        example.createCriteria().andUserIdEqualTo( userId ).andMatchIdEqualTo( matchId );

        ApplyOrder order = new ApplyOrder();
        order.setUserSignDatetime( new Date() );
        order.setUserSignStatus( 1 );

        applyOrderMapper.updateByExampleSelective( order, example );

        return applyOrderMapper.selectByExample( example ).get( 0 );
    }

    @Override
    public ApplyOrder updateUserBSignStatue(String matchId,
                                            String aId,
                                            String userId,
                                            String userHead) {
        ApplyOrderExample example = new ApplyOrderExample();
        example.createCriteria().andUserIdEqualTo( aId ).andMatchIdEqualTo( matchId );

        ApplyOrder order = new ApplyOrder();
        order.setPartnerSignStatue( 1 );
        order.setPartnerSignDatetime( new Date() );
        order.setPartnerId( userId );
        order.setPartnerHead( userHead );

        applyOrderMapper.updateByExampleSelective( order, example );

        return applyOrderMapper.selectByExample( example ).get( 0 );
    }

    @Override
    public ApplyOrder getUserBData(String userId,
                                   String matchId) {
        ApplyOrderExample example = new ApplyOrderExample();
        example.createCriteria().andMatchIdEqualTo( matchId ).andPartnerIdEqualTo( userId ).andPartnerSignStatueEqualTo(
                1 );
        List<ApplyOrder> applyOrders = applyOrderMapper.selectByExample( example );
        if( applyOrders != null && applyOrders.size() > 0 ) return applyOrders.get( 0 );

        return null;
    }

    private void updateOrderPaySuccess(String orderId,
                                       Date paySuccessDate) {
        ApplyOrder order = new ApplyOrder();
        order.setId( orderId );
        order.setPayDate( paySuccessDate );
        order.setPayStatue( 1 );//支付成功
        applyOrderMapper.updateByPrimaryKeySelective( order );
    }
}
