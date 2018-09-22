package com.tiaotiaopoker.service;

import com.tiaotiaopoker.entity.ApiMatchDetail;

import java.util.List;
import java.util.Map;

/**
 * Created by xiekang on 2018/9/14.
 */
public interface OrderService {
    List<ApiMatchDetail.MatchApplyUser> getApplyUserByMatchId(String matchId);

    void updateOrder(String xmlData);
}
