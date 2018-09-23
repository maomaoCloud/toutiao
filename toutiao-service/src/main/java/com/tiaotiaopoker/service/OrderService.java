package com.tiaotiaopoker.service;

import com.tiaotiaopoker.entity.MatchApplyUser;

import java.util.List;

/**
 * Created by xiekang on 2018/9/14.
 */
public interface OrderService {
    List<MatchApplyUser> getApplyUserByMatchId(String matchId);

    void updateOrder(String xmlData);
}
