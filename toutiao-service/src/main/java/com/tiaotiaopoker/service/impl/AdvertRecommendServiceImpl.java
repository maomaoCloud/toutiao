package com.tiaotiaopoker.service.impl;


import com.tiaotiaopoker.MD5Util;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.common.Pagination;
import com.tiaotiaopoker.dao.AdvertRecommendMapper;
import com.tiaotiaopoker.entity.AdvertRecommendDto;
import com.tiaotiaopoker.pojo.AdvertRecommend;
import com.tiaotiaopoker.pojo.SysUser;
import com.tiaotiaopoker.service.AdvertRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Component(value = "AdvertRecommendService")
@Transactional
public class AdvertRecommendServiceImpl implements AdvertRecommendService {

    @Autowired
    private AdvertRecommendMapper advertRecommendMapper;

    @Override
    public List<AdvertRecommend> queryAdvertByCondition(AdvertRecommend Advert, Pagination page) {
        Map<String, Object> paramMap = new HashMap<>();
        //广告名称
        if (!StringUtils.isBlank(Advert.getAdvertTitle())) {
            paramMap.put("advertTitle", Advert.getAdvertTitle());
        }
        if (null != Advert.getAdvertState()) {
            paramMap.put("advertState", Advert.getAdvertState());
        }

        List<AdvertRecommend> list = advertRecommendMapper.queryAdvertByCondition(paramMap);
        if (null != page) {
            page.setTotal(advertRecommendMapper.countAdvertByCondition(paramMap));
        }
        return list;
    }


    @Override
    public AdvertRecommendDto queryAdvertDtoById(String AdvertId) {
        AdvertRecommendDto advertDto = advertRecommendMapper.selectDtoByPrimaryKey(AdvertId);
        if (!StringUtils.isBlank(advertDto.getAdvertStartTime())){
            advertDto.setAdvertStartHour(advertDto.getAdvertStartTime().split(":")[0]);
            advertDto.setAdvertStartMin(advertDto.getAdvertStartTime().split(":")[1]);
        }
        if (!StringUtils.isBlank(advertDto.getAdvertEndTime())){
            advertDto.setAdvertEndHour(advertDto.getAdvertEndTime().split(":")[0]);
            advertDto.setAdvertEndMin(advertDto.getAdvertEndTime().split(":")[1]);
        }
        return advertDto;
    }

    @Override
    public int addOrUpdateAdvert(AdvertRecommend advert, SysUser loginUser) {
        int result = 0;
        advert.setAdvertStartTime(advert.getAdvertStartTime().replace(",",":"));
        advert.setAdvertEndTime(advert.getAdvertEndTime().replace(",",":"));
        advert.setAdvertUpdateTime(new Date());
        advert.setAdvertUpdateUser(loginUser.getUserId());
        if (StringUtils.isBlank(advert.getAdvertId())) {
            advert.setAdvertId(StringUtils.generateShortUUID());
            advert.setAdvertCreateTime(new Date());
            advert.setAdvertCreateUser(loginUser.getUserId());
            advert.setAdvertState(2);
            result = advertRecommendMapper.insert(advert);
        } else {
            result = advertRecommendMapper.updateByPrimaryKeySelective(advert);
        }
        return result;
    }

    @Override
    public int editAdvertBySelective(AdvertRecommend advert, SysUser loginUser) {
        advert.setAdvertUpdateTime(new Date());
        advert.setAdvertUpdateUser(loginUser.getUserId());
        return advertRecommendMapper.updateByPrimaryKeySelective(advert);
    }

}
