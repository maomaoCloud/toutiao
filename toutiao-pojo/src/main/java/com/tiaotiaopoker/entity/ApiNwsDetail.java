package com.tiaotiaopoker.entity;

import com.tiaotiaopoker.pojo.HeadlineNews;
import org.joda.time.DateTime;

/**
 * Created by xiekang on 2018/9/10.
 */
public class ApiNwsDetail {
    private String newsId;
    private String title;
    private String addTime;
    private String content;

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static ApiNwsDetail genFromNews(HeadlineNews news) {
        ApiNwsDetail newsDetail = new ApiNwsDetail();
        newsDetail.setNewsId( news.getNewsId() );
        newsDetail.setAddTime( new DateTime( news.getNewsCreateTime() ).toString( "yyyy-MM-dd HH:mm:ss" ) );
        newsDetail.setTitle( news.getNewsTitle() );
        newsDetail.setContent( news.getNewsDetail() );
        return newsDetail;
    }
}
