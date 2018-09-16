package com.tiaotiaopoker.service.impl;

import com.tiaotiaopoker.entity.ApiMatchDetail;
import com.tiaotiaopoker.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiekang on 2018/9/14.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public List<ApiMatchDetail.MatchApplyUser> getApplyUserByMatchId(String matchId) {
        return null;
    }
}
