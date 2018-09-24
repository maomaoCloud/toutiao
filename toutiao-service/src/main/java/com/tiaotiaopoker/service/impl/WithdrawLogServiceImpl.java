package com.tiaotiaopoker.service.impl;

import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.dao.ApplyOrderMapper;
import com.tiaotiaopoker.dao.WithdrawLogMapper;
import com.tiaotiaopoker.service.WithdrawLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiekang on 2018/9/24.
 */
@Service
public class WithdrawLogServiceImpl implements WithdrawLogService {
    @Autowired
    private WithdrawLogMapper withdrawLogMapper;
    @Autowired
    private ApplyOrderMapper applyOrderMapper;

    @Override
    public int getAvailableWithdraw(String userId) {
        Map<String, Object> paramMap = new HashMap<>();
        //已提现
        int alreadyWithdraw = withdrawLogMapper.sumMoneyByUserId(userId);
        //累计可提现收益
        paramMap.put("signState", Constants.Order.USER_SIGN_STATUS_END);
        int sumAvailableWithdraw = applyOrderMapper.sumPayMoneyByCondition(paramMap);
        //可提现
        return sumAvailableWithdraw - alreadyWithdraw;
    }
}
