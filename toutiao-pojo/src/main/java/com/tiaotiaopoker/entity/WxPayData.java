package com.tiaotiaopoker.entity;

import com.tiaotiaopoker.SecurityFactory;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.pojo.ApplyOrder;
import com.tiaotiaopoker.pojo.MatchWithBLOBs;

/**
 * Created by xiekang on 2018/9/20.
 */
public class WxPayData {
    private String  appid;
    private String  attach;
    private String  body;
    private String  mch_id;
    private String  nonce_str;
    private String  notify_url;
    private String  openid;
    private String  out_trade_no;
    private String  spbill_create_ip;
    private Integer total_fee;
    private String  trade_type;
    private String  key;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String genXmlData() {
        StringBuilder sb = new StringBuilder( "<xml>" );
        sb.append( "<appid>" ).append( this.appid ).append( "</appid>" );
        sb.append( "<attach>" ).append( this.attach ).append( "</attach>" );
        sb.append( "<body>" ).append( this.body ).append( "</body>" );
        sb.append( "<mch_id>" ).append( this.mch_id ).append( "</mch_id>" );
        sb.append( "<nonce_str>" ).append( this.nonce_str ).append( "</nonce_str>" );
        sb.append( "<notify_url>" ).append( this.notify_url ).append( "</notify_url>" );
        sb.append( "<openid>" ).append( this.openid ).append( "</openid>" );
        sb.append( "<out_trade_no>" ).append( this.out_trade_no ).append( "</out_trade_no>" );
        sb.append( "<spbill_create_ip>" ).append( this.spbill_create_ip ).append( "</spbill_create_ip>" );
        sb.append( "<total_fee>" ).append( this.total_fee ).append( "</total_fee>" );
        sb.append( "<trade_type>" ).append( this.trade_type ).append( "</trade_type>" );
        sb.append( "<sign>" ).append( genSign() ).append( "</sign>" );
        sb.append( "</xml>" );
        return sb.toString();
    }

    private String genSign() {
        StringBuffer sb = new StringBuffer();
        sb.append( "appid=" ).append( this.appid ).append( "&" );
        sb.append( "attach=" ).append( this.attach ).append( "&" );
        sb.append( "body=" ).append( this.body ).append( "&" );
        sb.append( "mch_id=" ).append( this.mch_id ).append( "&" );
        sb.append( "nonce_str=" ).append( this.nonce_str ).append( "&" );
        sb.append( "notify_url=" ).append( this.notify_url ).append( "&" );
        sb.append( "out_trade_no=" ).append( this.out_trade_no ).append( "&" );
        sb.append( "spbill_create_ip=" ).append( this.spbill_create_ip ).append( "&" );
        sb.append( "total_fee=" ).append( this.total_fee ).append( "&" );
        sb.append( "trade_type=" ).append( this.trade_type ).append( "&" );
        sb.append( "key=" ).append( this.key );

        String sign = SecurityFactory.MD5.encrypt( sb.toString(), null ).toUpperCase();
        return sign;
    }

    public static WxPayData genFromOrder(ApplyOrder order,
                                         ApiApplyParams params,
                                         MatchWithBLOBs match) {
        WxPayData res = new WxPayData();
        res.setOut_trade_no( order.getId() );
        res.setTotal_fee( 1 );
        res.setNonce_str( StringUtils.genRandomKey() );
        res.setBody( "竞技头条-活动报名付费" );
        res.setSpbill_create_ip( params.getRequestIp() );
        res.setAttach( match.getTheme() );
        res.setOpenid( params.getOpenId() );
        return res;
    }
}
