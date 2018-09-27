package com.tiaotiaopoker;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

/**
 * Created by xiekang on 2018/9/24.
 */
public class HttpUtil {
    public static CloseableHttpResponse Post(String url,
                                             String outputEntity,
                                             boolean isLoadCert,
                                             String mch_id,
                                             String path) throws Exception {
        HttpPost httpPost = new HttpPost( url );
        // 得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        httpPost.addHeader( "Content-Type", "text/xml" );
        httpPost.setEntity( new StringEntity( outputEntity, "UTF-8" ) );
        if( isLoadCert ) {
            // 加载含有证书的http请求
            return HttpClients.custom().setSSLSocketFactory( CertUtil.initCert( mch_id, path ) ).build().execute(
                    httpPost );
        } else {
            return HttpClients.custom().build().execute( httpPost );
        }
    }
}
