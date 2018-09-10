package com.tiaotiaopoker.entity;

import com.tiaotiaopoker.DateUtils;
import com.tiaotiaopoker.pojo.HeadlineNews;
import org.joda.time.DateTime;

/**
 * Created by xiekang on 2018/8/23.
 */
public class NewsListItem {
    private String id;
    private String title;
    private String img;
    private String date;
    private String dateDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateDesc() {
        return dateDesc;
    }

    public void setDateDesc(String dateDesc) {
        this.dateDesc = dateDesc;
    }

    public static NewsListItem genItemFromHeadlineNews(HeadlineNews hn) {
        NewsListItem item = new NewsListItem();
        item.setId(hn.getNewsId());
        item.setTitle(hn.getNewsTitle());
        item.setImg(hn.getNewsCoverImg());
        item.setDate(new DateTime(hn.getNewsCreateTime()).toString("yyyy-MM-dd"));
        item.setDateDesc( DateUtils.dateDesc(hn.getNewsCreateTime()));
        return item;
    }
}
