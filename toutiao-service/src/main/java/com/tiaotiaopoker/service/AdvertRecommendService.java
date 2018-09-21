package com.tiaotiaopoker.service;


import com.tiaotiaopoker.common.Pagination;
import com.tiaotiaopoker.entity.AdvertRecommendDto;
import com.tiaotiaopoker.pojo.AdvertRecommend;
import com.tiaotiaopoker.pojo.SysUser;

import java.util.List;

public interface AdvertRecommendService {

    List<AdvertRecommend> queryAdvertByCondition(AdvertRecommend Advert, Pagination page);

    AdvertRecommendDto queryAdvertDtoById(String AdvertId);

    int addOrUpdateAdvert(AdvertRecommend advert, SysUser loginUser);

    int editAdvertBySelective(AdvertRecommend advert, SysUser loginUser);

}