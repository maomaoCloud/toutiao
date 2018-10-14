package com.tiaotiaopoker.dao;

import com.tiaotiaopoker.pojo.MatchTeamResult;
import com.tiaotiaopoker.pojo.MatchTeamResultExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("matchTeamResultMapper")
public interface MatchTeamResultMapper {
    int countByExample(MatchTeamResultExample example);

    int deleteByExample(MatchTeamResultExample example);

    int insert(MatchTeamResult record);

    int insertSelective(MatchTeamResult record);

    List<MatchTeamResult> selectByExample(MatchTeamResultExample example);

    int updateByExampleSelective(@Param("record") MatchTeamResult record, @Param("example") MatchTeamResultExample example);

    int updateByExample(@Param("record") MatchTeamResult record, @Param("example") MatchTeamResultExample example);
}