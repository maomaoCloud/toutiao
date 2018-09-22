package com.tiaotiaopoker.service.impl;


import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.common.Pagination;
import com.tiaotiaopoker.dao.ProfitPercentMapper;
import com.tiaotiaopoker.pojo.ProfitPercent;
import com.tiaotiaopoker.pojo.ProfitPercentExample;
import com.tiaotiaopoker.pojo.SysUser;
import com.tiaotiaopoker.service.ProfitPercentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
@Component(value = "profitPercentService")
@Transactional
public class ProfitPercentServiceImpl implements ProfitPercentService {

    @Autowired
    private ProfitPercentMapper profitPercentMapper;

    @Override
    public List<ProfitPercent> queryProfitByCondition(ProfitPercent Profit, Pagination page) {
        ProfitPercentExample example = new ProfitPercentExample();
        ProfitPercentExample.Criteria criteria = example.createCriteria();
        criteria.andProfitStateEqualTo(1);
        example.setOrderByClause("profit_down_line ASC");
        List<ProfitPercent> list = profitPercentMapper.selectByExample(example);
        if (null != page) {
            page.setTotal(profitPercentMapper.countByExample(example));
        }
        return list;
    }

    @Override
    public ProfitPercent queryProfitById(String ProfitId) {
        return profitPercentMapper.selectByPrimaryKey(ProfitId);
    }

    @Override
    public int addOrUpdateProfit(ProfitPercent Profit, SysUser loginUser) {
        int result = 0;
        //校验区间
        ProfitPercentExample downExample = new ProfitPercentExample();
        ProfitPercentExample.Criteria downCriteria = downExample.createCriteria();
        downCriteria.andProfitStateEqualTo(1);
        downCriteria.andProfitDownLineLessThan(Profit.getProfitDownLine()).andProfitHighLineGreaterThan(Profit.getProfitDownLine());
        int downLineCount = profitPercentMapper.countByExample(downExample);

        ProfitPercentExample highExample = new ProfitPercentExample();
        ProfitPercentExample.Criteria highCriteria = highExample.createCriteria();
        highCriteria.andProfitStateEqualTo(1);
        highCriteria.andProfitDownLineLessThan(Profit.getProfitHighLine()).andProfitHighLineGreaterThan(Profit.getProfitHighLine());
        int highLineCount = profitPercentMapper.countByExample(highExample);

        /*ProfitPercentExample allAxample = new ProfitPercentExample();
        ProfitPercentExample.Criteria allAriteriaAll = allAxample.createCriteria();
        allAriteriaAll.andProfitStateEqualTo(1);
        allAriteriaAll.andProfitDownLineGreaterThan(Profit.getProfitDownLine()).andProfitHighLineLessThan(Profit.getProfitHighLine());
        int highAndDownLineCount = profitPercentMapper.countByExample(allAxample);*/

        if (highLineCount > 0 || downLineCount > 0 ) {
            return -1;
        }

        ProfitPercentExample example = new ProfitPercentExample();
        ProfitPercentExample.Criteria criteria = example.createCriteria();
        criteria.andProfitStateEqualTo(1);
        criteria.andProfitDownLineGreaterThanOrEqualTo(Profit.getProfitDownLine()).andProfitHighLineLessThanOrEqualTo(Profit.getProfitHighLine());
        example.setOrderByClause("profit_create_time Desc");
        List<ProfitPercent> list = profitPercentMapper.selectByExample(example);
        if (list.size() > 0) {
            Profit.setProfitId(list.get(0).getProfitId());
        }

        if (StringUtils.isBlank(Profit.getProfitId())) {
            Profit.setProfitId(StringUtils.generateShortUUID());
        } else {
            // 将旧分成比例标记删除，添加新的分成比例
            ProfitPercent oldProfit = profitPercentMapper.selectByPrimaryKey(Profit.getProfitId());
            oldProfit.setProfitUpdateTime(new Date());
            oldProfit.setProfitUpdateUser(loginUser.getUserId());
            oldProfit.setProfitState(0);

            result = profitPercentMapper.updateByPrimaryKeySelective(oldProfit);
        }
        Profit.setProfitId(StringUtils.generateShortUUID());
        Profit.setProfitCreateTime(new Date());
        Profit.setProfitCreateUser(loginUser.getUserId());
        Profit.setProfitState(1);
        result = profitPercentMapper.insert(Profit);
        return result;
    }

    @Override
    public int editProfitBySelective(ProfitPercent Profit, SysUser loginUser) {
        Profit.setProfitUpdateTime(new Date());
        Profit.setProfitUpdateUser(loginUser.getUserId());
        return profitPercentMapper.updateByPrimaryKeySelective(Profit);
    }

}
