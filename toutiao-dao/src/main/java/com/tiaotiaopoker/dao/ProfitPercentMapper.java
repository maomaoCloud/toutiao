package com.tiaotiaopoker.dao;

import com.tiaotiaopoker.pojo.ProfitPercent;
import com.tiaotiaopoker.pojo.ProfitPercentExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "profitPercentMapper")
public interface ProfitPercentMapper {
    int countByExample(ProfitPercentExample example);

    int deleteByExample(ProfitPercentExample example);

    int deleteByPrimaryKey(String profitId);

    int insert(ProfitPercent record);

    int insertSelective(ProfitPercent record);

    List<ProfitPercent> selectByExample(ProfitPercentExample example);

    ProfitPercent selectByPrimaryKey(String profitId);

    int updateByExampleSelective(@Param("record") ProfitPercent record, @Param("example") ProfitPercentExample example);

    int updateByExample(@Param("record") ProfitPercent record, @Param("example") ProfitPercentExample example);

    int updateByPrimaryKeySelective(ProfitPercent record);

    int updateByPrimaryKey(ProfitPercent record);
}