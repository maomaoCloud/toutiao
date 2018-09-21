package com.tiaotiaopoker.service;

import com.tiaotiaopoker.entity.ApiMatchDetail;

import java.util.List;

/**
 * Created by xiekang on 2018/9/14.
 */
public interface OrderService {
    List<ApiMatchDetail.MatchApplyUser> getApplyUserByMatchId(String matchId);
}
