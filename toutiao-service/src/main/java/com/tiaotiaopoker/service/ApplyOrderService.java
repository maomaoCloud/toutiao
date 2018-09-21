package com.tiaotiaopoker.service;

import com.tiaotiaopoker.entity.ApiApplyParams;

import java.util.Map;

/**
 * Created by xiekang on 2018/9/19.
 */
public interface ApplyOrderService {
    Map<String, Object> addOrder(ApiApplyParams params);
}
