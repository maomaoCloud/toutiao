package com.tiaotiaopoker.entity;

import com.tiaotiaopoker.pojo.AdvertRecommend;

public class ApiAdvertData {

    private  String id;

    private  String title;

    private  String imgUrl;

    private  String linkUrl;

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public static ApiAdvertData genFromAdvert(AdvertRecommend advert) {
        ApiAdvertData data = new ApiAdvertData();

        data.setId(advert.getAdvertId());
        data.setTitle(advert.getAdvertTitle());
        data.setImgUrl(advert.getAdvertImgUrl());
        data.setLinkUrl(advert.getAdvertLinkUrl());

        return data;
    }
}
