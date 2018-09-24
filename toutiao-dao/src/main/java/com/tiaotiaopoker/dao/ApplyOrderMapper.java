package com.tiaotiaopoker.dao;

import com.tiaotiaopoker.entity.ApplyOrderDto;
import com.tiaotiaopoker.pojo.ApplyOrder;
import com.tiaotiaopoker.pojo.ApplyOrderExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component(value = "applyOrderMapper")
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

    List<ApplyOrderDto> queryOrderListByUserId(String userId);

    int sumSignUpNumByMatchId(String id);

    int sumPayMoneyByCondition(Map<String, Object> paramMap);

    int sumSignUpNumByCondition(Map<String, Object> paramMap);

    int sumSignInNumByMatchId(String id);
}