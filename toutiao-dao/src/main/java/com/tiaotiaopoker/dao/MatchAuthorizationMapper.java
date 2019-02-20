package com.tiaotiaopoker.dao;

import com.tiaotiaopoker.pojo.MatchAuthorization;
import com.tiaotiaopoker.pojo.MatchAuthorizationExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("matchAuthorizationMapper")
public interface MatchAuthorizationMapper {
    int countByExample(MatchAuthorizationExample example);

    int deleteByExample(MatchAuthorizationExample example);

    int insert(MatchAuthorization record);

    int insertSelective(MatchAuthorization record);

    List<MatchAuthorization> selectByExample(MatchAuthorizationExample example);

    int updateByExampleSelective(@Param("record") MatchAuthorization record, @Param("example") MatchAuthorizationExample example);

    int updateByExample(@Param("record") MatchAuthorization record, @Param("example") MatchAuthorizationExample example);

    List<MatchAuthorization> queryAuthorizationList(String matchId);
}