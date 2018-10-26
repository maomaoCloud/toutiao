package com.tiaotiaopoker.entity;

/**
 * Created by xiekang on 2018/9/22.
 */
public class MatchApplyUser {
    private String  name;
    private String  headImg;
    private Integer hasPartner;

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getHeadImg () {
        return headImg;
    }

    public void setHeadImg (String headImg) {
        this.headImg = headImg;
    }

    public Integer getHasPartner () {
        return hasPartner;
    }

    public void setHasPartner (Integer hasPartner) {
        this.hasPartner = hasPartner;
    }

}
