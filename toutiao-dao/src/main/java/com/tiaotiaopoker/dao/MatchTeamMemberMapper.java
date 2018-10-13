package com.tiaotiaopoker.dao;

import com.tiaotiaopoker.pojo.MatchTeamMember;
import com.tiaotiaopoker.pojo.MatchTeamMemberExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("matchTeamMemberMapper")
public interface MatchTeamMemberMapper {
    int countByExample(MatchTeamMemberExample example);

    int deleteByExample(MatchTeamMemberExample example);

    int deleteByPrimaryKey(String id);

    int insert(MatchTeamMember record);

    int insertSelective(MatchTeamMember record);

    List<MatchTeamMember> selectByExample(MatchTeamMemberExample example);

    MatchTeamMember selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MatchTeamMember record, @Param("example") MatchTeamMemberExample example);

    int updateByExample(@Param("record") MatchTeamMember record, @Param("example") MatchTeamMemberExample example);

    int updateByPrimaryKeySelective(MatchTeamMember record);

    int updateByPrimaryKey(MatchTeamMember record);
}