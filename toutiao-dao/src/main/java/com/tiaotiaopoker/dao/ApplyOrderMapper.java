package com.tiaotiaopoker.dao;

import com.tiaotiaopoker.pojo.ApplyOrder;
import com.tiaotiaopoker.pojo.ApplyOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApplyOrderMapper {
    int countByExample(ApplyOrderExample example);

    int deleteByExample(ApplyOrderExample example);

    int deleteByPrimaryKey(String id);

    int insert(ApplyOrder record);

    int insertSelective(ApplyOrder record);

    List<ApplyOrder> selectByExample(ApplyOrderExample example);

    ApplyOrder selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ApplyOrder record,
                                 @Param("example") ApplyOrderExample example);

    int updateByExample(@Param("record") ApplyOrder record,
                        @Param("example") ApplyOrderExample example);

    int updateByPrimaryKeySelective(ApplyOrder record);

    int updateByPrimaryKey(ApplyOrder record);
}