package com.tiaotiaopoker.service;

import java.math.BigDecimal;

import com.tiaotiaopoker.entity.WithdrawLimit;
import com.tiaotiaopoker.pojo.AppUser;

import java.util.Map;

/**
 * Created by xiekang on 2018/9/24.
 */
public interface WithdrawLogService {

    BigDecimal getAvailableWithdraw(String userId);

    WithdrawLimit getTodayWithdrawTimes(String userId);

    Map<String, Object> withdraw(AppUser user,
                                 Float money,
                                 String ip);
}
