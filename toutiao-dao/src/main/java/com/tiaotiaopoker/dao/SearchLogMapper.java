package com.tiaotiaopoker.dao;

import com.tiaotiaopoker.pojo.SearchLog;
import com.tiaotiaopoker.pojo.SearchLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SearchLogMapper {
    int countByExample(SearchLogExample example);

    int deleteByExample(SearchLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SearchLog record);

    int insertSelective(SearchLog record);

    List<SearchLog> selectByExample(SearchLogExample example);

    SearchLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SearchLog record,
                                 @Param("example") SearchLogExample example);

    int updateByExample(@Param("record") SearchLog record,
                        @Param("example") SearchLogExample example);

    int updateByPrimaryKeySelective(SearchLog record);

    int updateByPrimaryKey(SearchLog record);
}