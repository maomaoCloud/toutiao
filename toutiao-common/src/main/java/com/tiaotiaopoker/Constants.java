package com.tiaotiaopoker;


/**
 * Created by xiekang on 17/4/18.
 */
public class Constants {
    public final static class ApiCode {
        public static final Integer SUCCCESS = 200;
        public static final Integer FAILED   = 500;

    }

    /**
     * 接口返回NULL字符串
     */
    public static final String NULL_STR = "NULL";

    public final static class DataBaseCommon {
        public static final Integer IS_DELETE_FALSE = 0;
        public static final Integer IS_DELETE_TRUE  = 1;
    }

    public static final class Http {
        public static final int HTTP_POST = 0;
        public static final int HTTP_GET  = 1;
    }

    public static final class Charset {
        public static final String UTF8     = "UTF-8";
        public static final String ISO88591 = "ISO-8859-1";
    }
}