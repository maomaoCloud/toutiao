package com.tiaotiaopoker.dao;

import com.tiaotiaopoker.pojo.AppUser;
import com.tiaotiaopoker.pojo.MatchTeamData;
import com.tiaotiaopoker.pojo.MatchTeamDataExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("matchTeamDataMapper")
public interface MatchTeamDataMapper {
    int countByExample(MatchTeamDataExample example);

    int deleteByExample(MatchTeamDataExample example);

    int deleteByPrimaryKey(String id);

    int insert(MatchTeamData record);

    int insertSelective(MatchTeamData record);

    List<MatchTeamData> selectByExample(MatchTeamDataExample example);

    MatchTeamData selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MatchTeamData record, @Param("example") MatchTeamDataExample example);

    int updateByExample(@Param("record") MatchTeamData record, @Param("example") MatchTeamDataExample example);

    int updateByPrimaryKeySelective(MatchTeamData record);

    int updateByPrimaryKey(MatchTeamData record);

    List<AppUser> queryTeamUserByMatchId(String matchId);
}