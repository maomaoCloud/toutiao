package com.tiaotiaopoker.service.impl;

import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.HttpUtil;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.XmlUtils;
import com.tiaotiaopoker.dao.ApplyOrderMapper;
import com.tiaotiaopoker.dao.CustomerDaoMapper;
import com.tiaotiaopoker.dao.WithdrawLogMapper;
import com.tiaotiaopoker.entity.WithdrawLimit;
import com.tiaotiaopoker.entity.WxWithdrawData;
import com.tiaotiaopoker.pojo.AppUser;
import com.tiaotiaopoker.pojo.WithdrawLog;
import com.tiaotiaopoker.service.WithdrawLogService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiekang on 2018/9/24.
 */
@Service
public class WithdrawLogServiceImpl implements WithdrawLogService {
    @Autowired
    private WithdrawLogMapper withdrawLogMapper;
    @Autowired
    private ApplyOrderMapper  applyOrderMapper;
    @Autowired
    private CustomerDaoMapper customerDaoMapper;
    @Value("${wx.withdraw.api.check_name}")
    private String            IS_CHECK_NAME;
    @Value("${wx.appid}")
    private String            WX_APPID;
    @Value("${wx.withdraw.url}")
    private String            WX_WITHDRAW_URL;
    @Value("${wx.pay.key}")
    private String            WX_KEY;
    @Value("${wx.pay.mch_id}")
    private String            WX_MCH_ID;
    @Value( "${wx.withdraw.cert.path}" )
    private String CERT_PATH;

    @Override
    public BigDecimal getAvailableWithdraw(String userId) {
        Map<String, Object> paramMap = new HashMap<>();
        //已提现
        int alreadyWithdraw = withdrawLogMapper.sumMoneyByUserId( userId );
        //累计可提现收益
        paramMap.put("signState", Constants.Order.USER_SIGN_STATUS_END);
        BigDecimal sumAvailableWithdraw = applyOrderMapper.sumPayMoneyByCondition(paramMap);
        //可提现
        return (sumAvailableWithdraw.subtract(new BigDecimal(alreadyWithdraw))).divide(new BigDecimal(100));
    }

    @Override
    public WithdrawLimit getTodayWithdrawTimes(String userId) {
        WithdrawLimit limit = customerDaoMapper.getTodayWithdrawTimes( userId );
        if( limit == null ) {
            limit = new WithdrawLimit();
            limit.setTodayWithdrawTimes( 0 );
            limit.setTodayWithdrawTotalAmount( 0.0f );
        }

        return limit;
    }

    @Override
    public Map<String, Object> withdraw(AppUser user,
                                        Float money,
                                        String ip) {
        //第一步拼接
        String trade_no = StringUtils.generateOrderNo();
        WxWithdrawData data = new WxWithdrawData();
        data.setMch_appid( WX_APPID );
        data.setMchid( WX_MCH_ID );
        data.setNonce_str( StringUtils.genRandomKey() );
        data.setPartner_trade_no( trade_no );
        data.setOpenid( user.getOpenId() );
        data.setCheck_name( IS_CHECK_NAME );
        data.setRe_user_name( user.getTrueName() );
        data.setAmount( (int) ( money * 100.0f ) );
        data.setDesc( "竞技头条活动提现" );
        data.setSpbill_create_ip( ip );
        data.setKey( WX_KEY );

        String xmlData = data.genXmlData();

        //第二步 添加提现记录
        WithdrawLog log = new WithdrawLog();
        log.setApplyDate( new Date() );
        log.setDescription( data.getDesc() );
        log.setId( trade_no );
        log.setMoney( data.getAmount() );
        log.setUserId( user.getId() );
        log.setReUserName( user.getTrueName() );
        log.setState( 0 );
        log.setSpbillCreateIp( ip );
        withdrawLogMapper.insertSelective( log );

        //第三步  请求
        Map<String, Object> mapData = new HashMap<>();
        Map<String, Object> resultMap = new HashMap<>();
        mapData.put( "data", xmlData );
        try {
            CloseableHttpResponse response = HttpUtil.Post( WX_WITHDRAW_URL, xmlData, true, WX_MCH_ID, CERT_PATH );
            String resXml = EntityUtils.toString( response.getEntity(), "utf-8" );

            Map<String, String> resMap = XmlUtils.returnXMLData( resXml );
            String return_code = resMap.get( "return_code" );
            String return_msg = resMap.get( "return_msg" );
            if( return_code.equals( "SUCCESS" ) ) {
                String result_code = resMap.get( "result_code" );
                String err_code = resMap.get( "err_code" );
                String err_code_des = resMap.get( "err_code_des" );
                String payment_no = resMap.get( "payment_no" );

                log = new WithdrawLog();
                log.setReturnCode( return_code );
                log.setReturnMsg( return_msg );
                log.setResultCode( result_code );
                log.setErrCode( err_code );
                log.setErrCodeDes( err_code_des );
                log.setId( trade_no );
                log.setPaymentNo( payment_no );
                log.setPaymentTime( new Date() );
                log.setXmlData( resXml );
                boolean isSuccess = result_code.equals( "SUCCESS" );
                log.setState( isSuccess ? 2 : 3 );
                resultMap.put( "isSuccess", isSuccess );
                withdrawLogMapper.updateByPrimaryKeySelective( log );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }
}
