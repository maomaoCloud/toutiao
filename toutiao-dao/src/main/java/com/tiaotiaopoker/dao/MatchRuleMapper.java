package com.tiaotiaopoker.dao;

import com.tiaotiaopoker.pojo.MatchRule;
import com.tiaotiaopoker.pojo.MatchRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component("matchRuleMapper")
public interface MatchRuleMapper {
    int countByExample(MatchRuleExample example);

    int deleteByExample(MatchRuleExample example);

    int deleteByPrimaryKey(String id);

    int insert(MatchRule record);

    int insertSelective(MatchRule record);

    List<MatchRule> selectByExampleWithBLOBs(MatchRuleExample example);

    List<MatchRule> selectByExample(MatchRuleExample example);

    MatchRule selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MatchRule record, @Param("example") MatchRuleExample example);

    int updateByExampleWithBLOBs(@Param("record") MatchRule record, @Param("example") MatchRuleExample example);

    int updateByExample(@Param("record") MatchRule record, @Param("example") MatchRuleExample example);

    int updateByPrimaryKeySelective(MatchRule record);

    int updateByPrimaryKeyWithBLOBs(MatchRule record);

    int updateByPrimaryKey(MatchRule record);
}