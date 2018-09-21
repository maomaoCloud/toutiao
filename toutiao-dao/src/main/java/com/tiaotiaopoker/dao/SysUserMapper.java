package com.tiaotiaopoker.dao;

import com.tiaotiaopoker.pojo.SysUser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "sysUserMapper")
public interface SysUserMapper {

    SysUser selectByPrimaryKey(String userId);

    int deleteByPrimaryKey(String userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    List<SysUser> queryUserByCondition(Map<String,Object> paramMap);

    Integer countUserByCondition(Map<String,Object> paramMap);
}