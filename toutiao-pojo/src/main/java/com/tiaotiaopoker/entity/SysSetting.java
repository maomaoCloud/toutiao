package com.tiaotiaopoker.entity;

import java.util.List;

public class SysSetting {
    /**
     * 页面滚动配置
     */
    private List<BiValue<Integer, Integer>> data;

    public List<BiValue<String, String>> hideColumn;

    public List<BiValue<String, String>> showColumn;

    public List<BiValue<Integer, Integer>> getData () {
        return data;
    }

    public void setData (List<BiValue<Integer, Integer>> data) {
        this.data = data;
    }

    public List<BiValue<String, String>> getHideColumn () {
        return hideColumn;
    }

    public void setHideColumn (List<BiValue<String, String>> hideColumn) {
        this.hideColumn = hideColumn;
    }

    public List<BiValue<String, String>> getShowColumn () {
        return showColumn;
    }

    public void setShowColumn (List<BiValue<String, String>> showColumn) {
        this.showColumn = showColumn;
    }

    public static class Constants {
        /**
         * 签到页
         */
        public static final int INDEX_OF_SIGN_PAGE = 0;

        /**
         * 座位页
         */
        public static final int INDEX_OF_SEAT_PAGE = 1;

        /**
         * 成绩页
         */
        public static final int INDEX_OF_SCORE_PAGE = 2;

        /**
         * 团队成绩页
         */
        public static final int INDEX_OF_GROUP_SCORE_PAGE = 3;

        public static final int TOTAL_SIZE = 4;

    }

}
