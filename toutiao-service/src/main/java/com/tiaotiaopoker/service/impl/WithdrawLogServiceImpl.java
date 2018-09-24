package com.tiaotiaopoker.service.impl;

import com.tiaotiaopoker.dao.WithdrawLogMapper;
import com.tiaotiaopoker.service.WithdrawLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiekang on 2018/9/24.
 */
@Service
public class WithdrawLogServiceImpl implements WithdrawLogService {
    @Autowired
    private WithdrawLogMapper withdrawLogMapper;
}
