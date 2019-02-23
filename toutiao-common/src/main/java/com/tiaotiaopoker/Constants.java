package com.tiaotiaopoker;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiekang on 17/4/18.
 */
public class Constants {
    public static final String NUM_CH[] = {"一", "二", "三", "四", "五", "六", "七", "八", "九"};

    public final static class ApiCode {
        public static final Integer SUCCESS = 200;
        public static final Integer FAILED  = 500;

        public static final Integer LOGIN_EXPIRE = 405;

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

    public static final class Match {
        public static final String NOT_START      = "未开始";
        public static final String UNDER_WAY      = "进行中";
        public static final String END            = "已结束";
        public static final int    NOT_START_CODE = 0;
        public static final int    END_CODE       = 2;
        public static final int    UNDER_WAY_CODE = 1;

    }

    public static final class Order {
        public static final String  NOT_SIGN_IN          = "未签到";
        public static final String  SIGN_IN              = "已签到";
        public static final String  END                  = "已结束";
        public static final Integer USER_SIGN_STATUS_END = 1;

    }

    public static final class ScreenLoginStatue {
        public static final String LOGIN_STEP_1 = "WAIT_SCAN";
        public static final String LOGIN_STEP_2 = "HAS_SCAN";
        public static final String LOGIN_STEP_3 = "HAS_LOGIN";

    }

    public static final class ruleSeat {
        public static final Integer SORT_NEAR      = 1;
        public static final Integer SORT_BEGIN_END = 2;
        public static final Integer SORT_MIDDLE    = 3;

    }

    public static final class result {
        public static final Integer[][] DISPARITY           = {{13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26}, {13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}};
        public static final Integer     WIN                 = 2;
        public static final Integer     FAIL                = 0;
        public static final Integer     DRAW                = 1;
        public static final Integer     TURN_FIRST          = 1;
        public static final String[]    SCORE               = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "A+"};
        public static final String      OVER_A              = "15";
        public static final String      MSG_SEAT_SETTING    = "请先进行座位编排";
        public static final String      MSG_FINANL_TURN     = "最后一轮成绩已录入";
        public static final String      DEFAULT_RESULT_RULE = "TotalPoint,TotalExfirstAll,TotalWin,TotalDisparity,TotalScore";

    }

    public static final class user {
        public static final String HEAD_IMG_URL = "/images/george-avatar.jpg";

    }

    public static final class resultRule {
        public static final Map<String, String> resultRuleMap = new HashMap<String, String>() {
            {
                put("TotalPoint", "总积分");
                put("TotalWin", "胜轮次");
                put("TotalDisparity", "累计级差分");
                put("TotalScore", "累计升级数");
                put("TotalOverA", "累计过A数");
                put("TotalDisparityOpponent", "累计对手级差分");
                put("TotalExfirst", "去首累进分");
                put("TotalExfirstAll", "去首累进分和");
            }
        };

    }

    public static final Map<String, String> ResultColumnMap = new HashMap() {
        {
            this.put("Index", "名次");
            this.put("TeamNumber", "编号");
            this.put("UserOne", "选手A");
            this.put("UserTwo", "选手B");
            this.put("TableNumber", "桌号");
            this.put("Score", "级数");
            this.put("Disparity", "级差分");
            this.put("OpDisparity", "对手级差分");
            this.putAll(resultRule.resultRuleMap);
        }
    };

    /**
     * 默认成绩页展示列
     */
    public static final String DEFAULT_COLUMN = "Index,TeamNumber,UserOne,UserTwo,TableNumber,Score,Disparity,OpDisparity";

}