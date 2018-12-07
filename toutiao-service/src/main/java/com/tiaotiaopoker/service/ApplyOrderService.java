package com.tiaotiaopoker.service;

import com.tiaotiaopoker.entity.ApiApplyParams;
import com.tiaotiaopoker.pojo.ApplyOrder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by xiekang on 2018/9/19.
 */
public interface ApplyOrderService {
    Map<String, Object> addOrder (ApiApplyParams params) throws IOException, URISyntaxException;

    Map<String, Object> addSignOrder (ApiApplyParams params) throws IOException, URISyntaxException;

    Map<String, Object> getNewSignData (String matchId,
                                        Date datePoint);

    List<ApplyOrder> getSignData (String matchId);

    void updateWaitApprove (String userId,
                            String matchId,
                            Integer nextStatue);

    void updateGroupName (String orderId, String groupName);

}
