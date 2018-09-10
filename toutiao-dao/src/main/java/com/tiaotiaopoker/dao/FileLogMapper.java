package com.tiaotiaopoker.dao;

import com.tiaotiaopoker.pojo.FileLog;
import com.tiaotiaopoker.pojo.FileLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FileLogMapper {
    int countByExample(FileLogExample example);

    int deleteByExample(FileLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(FileLog record);

    int insertSelective(FileLog record);

    List<FileLog> selectByExample(FileLogExample example);

    FileLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") FileLog record,
                                 @Param("example") FileLogExample example);

    int updateByExample(@Param("record") FileLog record,
                        @Param("example") FileLogExample example);

    int updateByPrimaryKeySelective(FileLog record);

    int updateByPrimaryKey(FileLog record);
}