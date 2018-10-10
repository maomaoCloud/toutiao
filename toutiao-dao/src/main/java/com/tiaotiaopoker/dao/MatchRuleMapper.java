package com.tiaotiaopoker.dao;

import com.tiaotiaopoker.pojo.MatchRule;
import com.tiaotiaopoker.pojo.MatchRuleExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("matchRuleMapper")
public interface MatchRuleMapper {
    int countByExample(MatchRuleExample example);

    int deleteByExample(MatchRuleExample example);

    int deleteByPrimaryKey(String id);

    int insert(MatchRule record);

    int insertSelective(MatchRule record);

    List<MatchRule> selectByExample(MatchRuleExample example);

    MatchRule selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MatchRule record, @Param("example") MatchRuleExample example);

    int updateByExample(@Param("record") MatchRule record, @Param("example") MatchRuleExample example);

    int updateByPrimaryKeySelective(MatchRule record);

    int updateByPrimaryKey(MatchRule record);
}