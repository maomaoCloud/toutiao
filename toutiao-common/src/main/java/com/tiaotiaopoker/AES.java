package com.tiaotiaopoker;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by xiekang on 2018/9/17.
 */
public class AES {
    private static String sKey        = "";
    private static String ivParameter = "1234567890123456";
    private static AES    instance    = null;

    //private static
    private AES() {

    }

    public static AES getInstance() {
        if( instance == null ) instance = new AES();
        return instance;
    }

    // 加密
    public String encrypt(String sSrc,
                          String encodingFormat,
                          String sKey,
                          String ivParameter) throws Exception {
        Cipher cipher = Cipher.getInstance( "AES/CBC/PKCS5Padding" );
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec( raw, "AES" );
        IvParameterSpec iv = new IvParameterSpec( ivParameter.getBytes() );//使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init( Cipher.ENCRYPT_MODE, skeySpec, iv );
        byte[] encrypted = cipher.doFinal( sSrc.getBytes( encodingFormat ) );
        return new BASE64Encoder().encode( encrypted );//此处使用BASE64做转码。
    }

    // 解密
    public String decrypt(String sSrc,
                          String encodingFormat,
                          String sKey,
                          String ivParameter) throws Exception {
        try {
            byte[] raw = sKey.getBytes( "ASCII" );
            SecretKeySpec skeySpec = new SecretKeySpec( raw, "AES" );
            Cipher cipher = Cipher.getInstance( "AES/CBC/PKCS5Padding" );
            IvParameterSpec iv = new IvParameterSpec( ivParameter.getBytes() );
            cipher.init( Cipher.DECRYPT_MODE, skeySpec, iv );
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer( sSrc );//先用base64解密
            byte[] original = cipher.doFinal( encrypted1 );
            String originalString = new String( original, encodingFormat );
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        // 需要加密的字串
        sKey = new String( new BASE64Decoder().decodeBuffer( "eHlrnDsLT2NEdm9po5/+sA==" ), "utf-8" );
        String cSrc = "123456";
        System.out.println( "加密前的字串是：" + cSrc );
        // 加密
        String enString = AES.getInstance().encrypt( cSrc, "utf-8", sKey, ivParameter );
        System.out.println( "加密后的字串是：" + enString );

        System.out.println( "1jdzWuniG6UMtoa3T6uNLA==".equals( enString ) );

        // 解密
        String DeString = AES.getInstance().decrypt( enString, "utf-8", sKey, ivParameter );
        System.out.println( "解密后的字串是：" + DeString );

        System.out.println( new String( new BASE64Decoder().decodeBuffer( "eHlrnDsLT2NEdm9po5/+sA==" ), "utf-8" ) );
    }
}
