package com.tiaotiaopoker.pojo;

import java.util.Date;

public class SearchLog {
    private Long id;

    private String kw;

    private Date addDate;

    private String userId;

    private Integer searchType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKw() {
        return kw;
    }

    public void setKw(String kw) {
        this.kw = kw == null ? null : kw.trim();
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }
}