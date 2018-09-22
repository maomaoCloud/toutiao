package com.tiaotiaopoker.dao;

import com.tiaotiaopoker.pojo.WxPayLog;
import com.tiaotiaopoker.pojo.WxPayLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxPayLogMapper {
    int countByExample(WxPayLogExample example);

    int deleteByExample(WxPayLogExample example);

    int deleteByPrimaryKey(String transactionId);

    int insert(WxPayLog record);

    int insertSelective(WxPayLog record);

    List<WxPayLog> selectByExampleWithBLOBs(WxPayLogExample example);

    List<WxPayLog> selectByExample(WxPayLogExample example);

    WxPayLog selectByPrimaryKey(String transactionId);

    int updateByExampleSelective(@Param("record") WxPayLog record,
                                 @Param("example") WxPayLogExample example);

    int updateByExampleWithBLOBs(@Param("record") WxPayLog record,
                                 @Param("example") WxPayLogExample example);

    int updateByExample(@Param("record") WxPayLog record,
                        @Param("example") WxPayLogExample example);

    int updateByPrimaryKeySelective(WxPayLog record);

    int updateByPrimaryKeyWithBLOBs(WxPayLog record);

    int updateByPrimaryKey(WxPayLog record);
}