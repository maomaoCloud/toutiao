package com.tiaotiaopoker;


/**
 * Created by xiekang on 17/4/18.
 */
public class Constants {
    public static final String NUM_CH[] = {"一", "二", "三", "四", "五", "六", "七", "八", "九"};

    public final static class ApiCode {
        public static final Integer SUCCESS = 200;
        public static final Integer FAILED = 500;

        public static final Integer LOGIN_EXPIRE = 405;
    }

    /**
     * 接口返回NULL字符串
     */
    public static final String NULL_STR = "NULL";

    public final static class DataBaseCommon {
        public static final Integer IS_DELETE_FALSE = 0;
        public static final Integer IS_DELETE_TRUE = 1;
    }

    public static final class Http {
        public static final int HTTP_POST = 0;
        public static final int HTTP_GET = 1;
    }

    public static final class Charset {
        public static final String UTF8 = "UTF-8";
        public static final String ISO88591 = "ISO-8859-1";
    }

    public static final class Match {
        public static final String NOT_START = "未开始";
        public static final String UNDER_WAY = "进行中";
        public static final String END = "已结束";
    }

    public static final class Order {
        public static final String NOT_SIGN_IN = "未签到";
        public static final String SIGN_IN = "已签到";
        public static final String END = "已结束";
        public static final Integer USER_SIGN_STATUS_END = 1;
    }

    public static final class ScreenLoginStatue {
        public static final String LOGIN_STEP_1 = "WAIT_SCAN";
        public static final String LOGIN_STEP_2 = "HAS_SCAN";
        public static final String LOGIN_STEP_3 = "HAS_LOGIN";
    }

    public static final class ruleSeat {
        public static final Integer SORT_NEAR = 1;
        public static final Integer SORT_BEGIN_END = 2;
        public static final Integer SRTT_MIDDLE = 3;
    }

    public static final class result {
        public static final Integer[][] DISPARITY = {{13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26}, {13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}};
        public static final Integer WIN = 2;
        public static final Integer FAIL = 0;
        public static final Integer DRAW = 1;
        public static final Integer TURN_FIRST = 1;
        public static final String[] SCORE = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "A+"};

    }
}