package com.tiaotiaopoker.dao;

import com.tiaotiaopoker.entity.AdvertRecommendDto;
import com.tiaotiaopoker.pojo.AdvertRecommend;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "advertRecommendMapper")
public interface AdvertRecommendMapper {

    int deleteByPrimaryKey(String advertId);

    int insert(AdvertRecommend record);

    int insertSelective(AdvertRecommend record);

    AdvertRecommend selectByPrimaryKey(String advertId);

    AdvertRecommendDto selectDtoByPrimaryKey(String advertId);

    int updateByPrimaryKeySelective(AdvertRecommend record);

    int updateByPrimaryKey(AdvertRecommend record);

    List<AdvertRecommend> queryAdvertByCondition(Map<String,Object> paramMap);

    Integer countAdvertByCondition(Map<String,Object> paramMap);
}