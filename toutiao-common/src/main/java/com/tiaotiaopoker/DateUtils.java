package com.tiaotiaopoker;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by xiekang on 2018/8/23.
 */
public class DateUtils {
    /**
     * 1分钟前->1小时前->1天前->NULL
     */
    public static String dateDesc(Date date) {
        Date now = new Date();
        int diff = getDateDiffSeconds( date, now );
        if( diff < 60 ) return diff + "秒之前";

        diff /= 60;
        if( diff < 60 ) return diff + "分钟之前";

        diff /= 60;
        if( diff < 24 ) return diff + "小时之前";

        diff /= 24;
        if( diff < 30 ) return diff + "天之前";

        return new DateTime(date).toString("yyyy-MM-dd");
    }

    /**
     * 获取两个时间的秒数差
     */
    public static int getDateDiffSeconds(Date date1,
                                         Date date2) {
        int s1 = (int) ( new DateTime( date1 ).getMillis() / 1000 );
        int s2 = (int) ( new DateTime( date2 ).getMillis() / 1000 );
        return s2 - s1;
    }
}