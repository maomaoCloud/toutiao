package com.tiaotiaopoker.service;


import com.tiaotiaopoker.common.Pagination;
import com.tiaotiaopoker.pojo.HeadlineNews;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

public interface HeadlineNewsService {

    List<HeadlineNews> queryNewsByCondition(HeadlineNews news, Pagination page);

    Map<String, Object> queryNewsByPage(String typeId, int pageNum, int pageSize);

    HeadlineNews queryNewsById(String newsId);

    int addOrUpdateNews(HeadlineNews news);

    int editNewsBySelective(HeadlineNews news);

    int setNewsSort(HeadlineNews news);

}