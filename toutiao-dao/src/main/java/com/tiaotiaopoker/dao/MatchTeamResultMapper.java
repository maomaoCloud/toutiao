package com.tiaotiaopoker.dao;

import com.tiaotiaopoker.entity.MatchTeamResultDto;
import com.tiaotiaopoker.pojo.MatchTeamResult;
import com.tiaotiaopoker.pojo.MatchTeamResultExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("matchTeamResultMapper")
public interface MatchTeamResultMapper {
    int countByExample(MatchTeamResultExample example);

    int deleteByExample(MatchTeamResultExample example);

    int insert(MatchTeamResult record);

    int insertSelective(MatchTeamResult record);

    List<MatchTeamResult> selectByExample(MatchTeamResultExample example);

    int updateByExampleSelective(@Param("record") MatchTeamResult record, @Param("example") MatchTeamResultExample example);

    int updateByExample(@Param("record") MatchTeamResult record, @Param("example") MatchTeamResultExample example);

    List<MatchTeamResultDto> queryResultWithTableNumber(Map<String, Object> paramMap);
}