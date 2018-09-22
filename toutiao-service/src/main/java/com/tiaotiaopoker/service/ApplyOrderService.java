package com.tiaotiaopoker.service;

import com.tiaotiaopoker.entity.ApiApplyParams;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Created by xiekang on 2018/9/19.
 */
public interface ApplyOrderService {
    Map<String, Object> addOrder(ApiApplyParams params) throws IOException, URISyntaxException;
}
