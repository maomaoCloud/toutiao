package com.tiaotiaopoker.entity;

import com.tiaotiaopoker.SecurityFactory;

/**
 * Created by xiekang on 2018/9/24.
 */
public class WxWithdrawData {
    private String  mch_appid;
    private String  mchid;
    private String  nonce_str;
    private String  partner_trade_no;
    private String  openid;
    private String  check_name;
    private String  re_user_name;
    private Integer amount;
    private String  desc;
    private String  spbill_create_ip;
    private String  key;

    public String getMch_appid() {
        return mch_appid;
    }

    public void setMch_appid(String mch_appid) {
        this.mch_appid = mch_appid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getPartner_trade_no() {
        return partner_trade_no;
    }

    public void setPartner_trade_no(String partner_trade_no) {
        this.partner_trade_no = partner_trade_no;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCheck_name() {
        return check_name;
    }

    public void setCheck_name(String check_name) {
        this.check_name = check_name;
    }

    public String getRe_user_name() {
        return re_user_name;
    }

    public void setRe_user_name(String re_user_name) {
        this.re_user_name = re_user_name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String genXmlData() {
        StringBuffer sb = new StringBuffer( "<xml>" );
        sb.append( "<mch_appid>" ).append( this.mch_appid ).append( "</mch_appid>" );
        sb.append( "<mchid>" ).append( this.mchid ).append( "</mchid>" );
        sb.append( "<nonce_str>" ).append( this.nonce_str ).append( "</nonce_str>" );
        sb.append( "<partner_trade_no>" ).append( this.partner_trade_no ).append( "</partner_trade_no>" );
        sb.append( "<openid>" ).append( this.openid ).append( "</openid>" );
        sb.append( "<check_name>" ).append( this.check_name ).append( "</check_name>" );
        sb.append( "<re_user_name>" ).append( this.re_user_name ).append( "</re_user_name>" );
        sb.append( "<amount>" ).append( this.amount ).append( "</amount>" );
        sb.append( "<desc>" ).append( this.desc ).append( "</desc>" );
        sb.append( "<spbill_create_ip>" ).append( this.spbill_create_ip ).append( "</spbill_create_ip>" );
        sb.append( "<sign>" ).append( this.genSign() ).append( "</sign>" );
        sb.append( "</xml>" );
        return sb.toString();
    }

    private String genSign() {
        StringBuffer sb = new StringBuffer();
        sb.append( "amount=" ).append( amount ).append( "&" );
        sb.append( "check_name=" ).append( check_name ).append( "&" );
        sb.append( "desc=" ).append( desc ).append( "&" );
        sb.append( "mch_appid=" ).append( mch_appid ).append( "&" );
        sb.append( "mchid=" ).append( mchid ).append( "&" );
        sb.append( "nonce_str=" ).append( nonce_str ).append( "&" );
        sb.append( "openid=" ).append( openid ).append( "&" );
        sb.append( "partner_trade_no=" ).append( partner_trade_no ).append( "&" );
        sb.append( "re_user_name=" ).append( re_user_name ).append( "&" );
        sb.append( "spbill_create_ip=" ).append( spbill_create_ip ).append( "&" );
        sb.append( "key=" ).append( key );

        return SecurityFactory.MD5.encrypt( sb.toString(), null ).toUpperCase();
    }

}
