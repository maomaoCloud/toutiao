package com.tiaotiaopoker.service;

import com.tiaotiaopoker.entity.ApiSimpleUserInfo;
import com.tiaotiaopoker.entity.MatchApplyUser;
import com.tiaotiaopoker.pojo.ApplyOrder;

import java.util.List;

/**
 * Created by xiekang on 2018/9/14.
 */
public interface OrderService {
    List<MatchApplyUser> getApplyUserByMatchId(String matchId);

    void updateOrder(String xmlData);

    ApplyOrder getUserApplyData(String userId,
                                String matchId);

    List<ApiSimpleUserInfo> getUnsignAUsers(String matchId);

    ApplyOrder updateUserASignStatue(String matchId,
                                     String userId);

    ApplyOrder updateUserBSignStatue(String matchId,
                                     String aId,
                                     String userId,
                                     String userHead);

    ApplyOrder getUserBData(String userId,
                            String matchId);
}
