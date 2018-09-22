package com.tiaotiaopoker.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tiaotiaopoker.SecurityFactory;

/**
 * Created by xiekang on 2018/9/22.
 * 返回小程序支付需要的数据
 */
@JsonIgnoreProperties({ "appId", "key" })
public class WxPayResultModel {
    private String appId;
    private String nonceStr;
    private String packageName;
    private String signType = "MD5";
    private Long   timeStamp;
    private String key;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPackage() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getTimeStamp() {
        return timeStamp.toString();
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPaySign() {
        StringBuilder sb = new StringBuilder();
        sb.append( "appId=" ).append( appId ).append( "&" );
        sb.append( "nonceStr=" ).append( nonceStr ).append( "&" );
        sb.append( "package=prepay_id=" ).append( packageName ).append( "&" );
        sb.append( "signType=" ).append( signType ).append( "&" );
        sb.append( "timeStamp=" ).append( timeStamp ).append( "&" );
        sb.append( "key=" ).append( key );

        return SecurityFactory.MD5.encrypt( sb.toString(), null ).toUpperCase();
    }
}
