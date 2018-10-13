package com.tiaotiaopoker.dao;

import com.tiaotiaopoker.pojo.Match;
import com.tiaotiaopoker.pojo.MatchExample;
import com.tiaotiaopoker.pojo.MatchWithBLOBs;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component(value = "matchMapper")
public interface MatchMapper {
    int countByExample(MatchExample example);

    int deleteByExample(MatchExample example);

    int insert(MatchWithBLOBs record);

    int insertSelective(MatchWithBLOBs record);

    List<MatchWithBLOBs> selectByExampleWithBLOBs(MatchExample example);

    List<Match> selectByExample(MatchExample example);

    int updateByExampleSelective(@Param("record") MatchWithBLOBs record,
                                 @Param("example") MatchExample example);

    int updateByExampleWithBLOBs(@Param("record") MatchWithBLOBs record,
                                 @Param("example") MatchExample example);

    int updateByExample(@Param("record") Match record,
                        @Param("example") MatchExample example);

    List<Match> queryMatchByCondition(Map<String,Object> paramMap);

    Integer countMatchByCondition(Map<String,Object> paramMap);
}