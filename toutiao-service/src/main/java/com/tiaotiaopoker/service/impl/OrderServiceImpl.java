package com.tiaotiaopoker.service.impl;

import com.tiaotiaopoker.XmlUtils;
import com.tiaotiaopoker.dao.ApplyOrderMapper;
import com.tiaotiaopoker.dao.CustomerDaoMapper;
import com.tiaotiaopoker.dao.WxPayLogMapper;
import com.tiaotiaopoker.entity.MatchApplyUser;
import com.tiaotiaopoker.pojo.ApplyOrder;
import com.tiaotiaopoker.pojo.WxPayLog;
import com.tiaotiaopoker.service.OrderService;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private void updateOrderPaySuccess(String orderId,
                                       Date paySuccessDate) {
        ApplyOrder order = new ApplyOrder();
        order.setId( orderId );
        order.setPayDate( paySuccessDate );
        order.setPayStatue( 1 );//支付成功
        applyOrderMapper.updateByPrimaryKeySelective( order );
    }
}
