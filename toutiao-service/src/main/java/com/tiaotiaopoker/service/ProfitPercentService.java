package com.tiaotiaopoker.service;


import com.tiaotiaopoker.common.Pagination;
import com.tiaotiaopoker.pojo.ProfitPercent;
import com.tiaotiaopoker.pojo.SysUser;

import java.util.List;

public interface ProfitPercentService {

    List<ProfitPercent> queryProfitByCondition(ProfitPercent Profit,
                                               Pagination page);

    ProfitPercent queryProfitById(String ProfitId);

    int addOrUpdateProfit(ProfitPercent Profit,
                          SysUser loginUser);

    int editProfitBySelective(ProfitPercent Profit,
                              SysUser loginUser);

    Integer getPercentByPrice(float fee);
}