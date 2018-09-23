package com.tiaotiaopoker.dao;

import com.tiaotiaopoker.pojo.WithdrawLog;
import com.tiaotiaopoker.pojo.WithdrawLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WithdrawLogMapper {
    int countByExample(WithdrawLogExample example);

    int deleteByExample(WithdrawLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WithdrawLog record);

    int insertSelective(WithdrawLog record);

    List<WithdrawLog> selectByExampleWithBLOBs(WithdrawLogExample example);

    List<WithdrawLog> selectByExample(WithdrawLogExample example);

    WithdrawLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WithdrawLog record,
                                 @Param("example") WithdrawLogExample example);

    int updateByExampleWithBLOBs(@Param("record") WithdrawLog record,
                                 @Param("example") WithdrawLogExample example);

    int updateByExample(@Param("record") WithdrawLog record,
                        @Param("example") WithdrawLogExample example);

    int updateByPrimaryKeySelective(WithdrawLog record);

    int updateByPrimaryKeyWithBLOBs(WithdrawLog record);

    int updateByPrimaryKey(WithdrawLog record);
}