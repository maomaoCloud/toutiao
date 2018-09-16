package com.tiaotiaopoker.dao;

import com.tiaotiaopoker.pojo.AppUser;
import com.tiaotiaopoker.pojo.AppUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppUserMapper {
    int countByExample(AppUserExample example);

    int deleteByExample(AppUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(AppUser record);

    int insertSelective(AppUser record);

    List<AppUser> selectByExample(AppUserExample example);

    AppUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AppUser record,
                                 @Param("example") AppUserExample example);

    int updateByExample(@Param("record") AppUser record,
                        @Param("example") AppUserExample example);

    int updateByPrimaryKeySelective(AppUser record);

    int updateByPrimaryKey(AppUser record);
}