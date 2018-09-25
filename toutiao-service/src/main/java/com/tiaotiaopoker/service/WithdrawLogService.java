package com.tiaotiaopoker.service;

import java.math.BigDecimal;

/**
 * Created by xiekang on 2018/9/24.
 */
public interface WithdrawLogService {

    BigDecimal getAvailableWithdraw(String userId);
}
