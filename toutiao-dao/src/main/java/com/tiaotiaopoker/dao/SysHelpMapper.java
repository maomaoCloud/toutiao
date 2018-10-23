package com.tiaotiaopoker.dao;

import com.tiaotiaopoker.pojo.SysHelp;
import com.tiaotiaopoker.pojo.SysHelpExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("sysHelpMapper")
public interface SysHelpMapper {
    int countByExample(SysHelpExample example);

    int deleteByExample(SysHelpExample example);

    int deleteByPrimaryKey(String helpId);

    int insert(SysHelp record);

    int insertSelective(SysHelp record);

    List<SysHelp> selectByExampleWithBLOBs(SysHelpExample example);

    List<SysHelp> selectByExample(SysHelpExample example);

    SysHelp selectByPrimaryKey(String helpId);

    int updateByExampleSelective(@Param("record") SysHelp record, @Param("example") SysHelpExample example);

    int updateByExampleWithBLOBs(@Param("record") SysHelp record, @Param("example") SysHelpExample example);

    int updateByExample(@Param("record") SysHelp record, @Param("example") SysHelpExample example);

    int updateByPrimaryKeySelective(SysHelp record);

    int updateByPrimaryKeyWithBLOBs(SysHelp record);

    int updateByPrimaryKey(SysHelp record);

    List<SysHelp> queryHelpByCondition(Map<String, Object> paramMap);

    Integer countHelpByCondition(Map<String, Object> paramMap);
}