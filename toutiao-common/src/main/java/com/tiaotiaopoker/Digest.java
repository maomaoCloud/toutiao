package com.tiaotiaopoker;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Digest {
    /** MD5 加密 */
    public static String hexMd5(String input) throws NoSuchAlgorithmException {
        return hexDigest(input, "MD5");
    }

    /** SHA-1加密 */
    public static String hexSha1(String input) throws NoSuchAlgorithmException {
        return hexDigest(input, "SHA-1");
    }

    public static String hexDigest(String input,
                                   String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        byte[] utf8byte = input.getBytes(Charset.forName("UTF-8"));
        byte[] result = digest.digest(utf8byte);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    /** AES 加密 */
    public static byte[] aesEncrypt(String content,
                                    String password) {
        if (content == null || password == null) return null;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(password.getBytes(), "AES"));
            return cipher.doFinal(content.getBytes("UTF-8"));
        }
        catch (Exception e) {
            return null;
        }
    }

    /** AES 解密 */
    public static String aesDecrypt(byte[] content,
                                    String password) {
        if (content == null || password == null) return null;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(password.getBytes(), "AES"));
            byte[] bytes = cipher.doFinal(content);
            return new String(bytes, "UTF-8");
        }
        catch (Exception e) {
            return null;
        }
    }

    /** 将二进制数组转换为16进制字符串 */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /** 将16进制转换为二进制 */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String encrypt = SecurityFactory.AES.encrypt("18012673347", "81lokmr7cfolohb3");
        System.out.println(encrypt);
        System.out.println(SecurityFactory.AES.decrypt("6b7c837503cea6759805275603989fa4b5ebf8d4710bc7ff891ac0a31e3371a5a908db6491f8f5519957bdf6247a2ccc8a2e880e52ac2de0ef1c7836c5ac94bc360d94208ce7e3c546612d1a1627afd2afbfe1e35954aef7a7c8ade9b06dbd3f", "81lokmr7cfolohb3"));
    }
}
