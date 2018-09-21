package com.tiaotiaopoker.service;


import com.tiaotiaopoker.common.Pagination;
import com.tiaotiaopoker.pojo.HeadlineNews;
import com.tiaotiaopoker.pojo.SysUser;

import java.util.List;
import java.util.Map;

public interface HeadlineNewsService {

    List<HeadlineNews> queryNewsByCondition(HeadlineNews news, Pagination page);

    Map<String, Object> queryNewsByPage(String typeId, int pageNum, int pageSize);

    HeadlineNews queryNewsById(String newsId);

    int addOrUpdateNews(HeadlineNews news, SysUser loginUser);

    int editNewsBySelective(HeadlineNews news, SysUser loginUser);

    int setNewsSort(HeadlineNews news, SysUser loginUser);

}