package com.tiaotiaopoker.service.impl;


import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.common.Pagination;
import com.tiaotiaopoker.entity.NewsListItem;
import com.tiaotiaopoker.pojo.HeadlineNews;
import com.tiaotiaopoker.service.HeadlineNewsService;
import com.tiaotiaopoker.dao.HeadlineNewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Administrator
 */
@Component(value = "headlineNewsService")
@Transactional
public class HeadlineNewsServiceImpl implements HeadlineNewsService {

    @Autowired
    private HeadlineNewsMapper headlineNewsMapper;

    @Override
    public List<HeadlineNews> queryNewsByCondition(HeadlineNews news, Pagination page) {
        Map<String, Object> paramMap = new HashMap<>();
        if (!StringUtils.isBlank(news.getNewsTitle())) {
            paramMap.put("newsTitle", news.getNewsTitle());
        }
        if (!StringUtils.isBlank(news.getNewsType())) {
            paramMap.put("newsType", news.getNewsType());
        }
        if (null != news.getNewsStatus()) {
            paramMap.put("newsStatus", news.getNewsStatus());
        }
        List<HeadlineNews> list = headlineNewsMapper.queryNewsByCondition(paramMap);
        page.setTotal(headlineNewsMapper.countNewsByCondition(paramMap));
        return list;
    }

    @Override
    public Map<String, Object> queryNewsByPage(String typeId,
                                               int pageNum,
                                               int pageSize) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        if (!StringUtils.isBlank(typeId)) {
            paramMap.put("typeId", typeId);
        }

        List<HeadlineNews> originDatalist = headlineNewsMapper.queryNewsByCondition(paramMap);
        List<NewsListItem> list = new ArrayList<>();
        for (HeadlineNews hn : originDatalist)
            list.add(NewsListItem.genItemFromHeadlineNews(hn));

        int total;
        total = headlineNewsMapper.countNewsByCondition(paramMap);
        resultMap.put("hasMore", (pageNum - 1) * pageSize + list.size() < total);
        resultMap.put("data", list);
        return resultMap;
    }

    @Override
    public HeadlineNews queryNewsById(String newsId) {

        return headlineNewsMapper.selectByPrimaryKey(newsId);
    }

    @Override
    public int addOrUpdateNews(HeadlineNews news) {
        int result = 0;
        if (StringUtils.isBlank(news.getNewsId())) {
            news.setNewsId(StringUtils.generateShortUUID());
            news.setNewsCreateTime(new Date());
            news.setNewsUpdateTime(new Date());
            news.setNewsSort(0);
            news.setNewsBrowseCount(new Long(0));
            result = headlineNewsMapper.insert(news);
        } else {
            news.setNewsUpdateTime(new Date());
            result = headlineNewsMapper.updateByPrimaryKeySelective(news);
        }
        return result;
    }

    @Override
    public int editNewsBySelective(HeadlineNews news) {
        int newsStatus = news.getNewsStatus();
        if (news.getNewsStatus() == 0 || news.getNewsStatus() == 2) {
            //删除和下架时，需要将排序字段置为0，同时更新其他排序字段
            news.setNewsSort(0);
            setNewsSort(news);
        }
        //设置状态（重置排序字段的同时会更新状态）
        news.setNewsStatus(newsStatus);
        news.setNewsUpdateTime(new Date());
        return headlineNewsMapper.updateByPrimaryKeySelective(news);
    }

    @Override
    public int setNewsSort(HeadlineNews news) {
        int result = 0;
        Map<String, Object> paramMap = new HashMap<>();
        HeadlineNews oldPositionNews = headlineNewsMapper.selectByPrimaryKey(news.getNewsId());
        if (oldPositionNews.getNewsSort() == news.getNewsSort()) {
            result = 1;
            return result;
        } else if (0 == oldPositionNews.getNewsSort()) {
            // 未排序置于排序中
            paramMap.put("startSort", news.getNewsSort());
            result = resetSort(paramMap, 1);
        } else if (0 == news.getNewsSort()) {
            // 取消排序
            paramMap.put("startSort", oldPositionNews.getNewsSort());
            resetSort(paramMap, -1);
        } else if (oldPositionNews.getNewsSort() > news.getNewsSort()) {
            // 向上排序
            paramMap.put("startSort", news.getNewsSort());
            paramMap.put("endSort", oldPositionNews.getNewsSort());
            result = resetSort(paramMap, 1);
        } else if (oldPositionNews.getNewsSort() < news.getNewsSort()) {
            // 向下排序
            paramMap.put("startSort", oldPositionNews.getNewsSort());
            paramMap.put("endSort", news.getNewsSort());
            result = resetSort(paramMap, -1);
        } else {
            result = 1;
            return result;
        }
        //置顶的同时上架
        news.setNewsStatus(1);
        news.setNewsUpdateTime(new Date());
        result = headlineNewsMapper.updateByPrimaryKeySelective(news);
        return result;
    }

    private int resetSort(Map<String, Object> paramMap, int plusOrMinus) {
        int result = 0;
        List<HeadlineNews> list = headlineNewsMapper.queryNewsByCondition(paramMap);
        for (HeadlineNews news : list) {
            news.setNewsSort(news.getNewsSort() + plusOrMinus);
            result = headlineNewsMapper.updateByPrimaryKeySelective(news);
        }
        return result;
    }

}
