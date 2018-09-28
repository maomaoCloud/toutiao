package com.tiaotiaopoker;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

/**
 * Created by xiekang on 2018/9/24.
 */
public class CertUtil {
    /**
     * 加载证书
     */
    public static SSLConnectionSocketFactory initCert(String mch_id,
                                                      String filePath) throws Exception {
        FileInputStream instream = null;
        KeyStore keyStore = KeyStore.getInstance( "PKCS12" );
        instream = new FileInputStream( new File( filePath ) );
        keyStore.load( instream, mch_id.toCharArray() );

        if( null != instream ) {
            instream.close();
        }

        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial( keyStore, mch_id.toCharArray() ).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory( sslcontext, new String[]{ "TLSv1" }, null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER );

        return sslsf;
    }
}
