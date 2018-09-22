package com.tiaotiaopoker.dao;

import com.tiaotiaopoker.entity.ApiMatchData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xiekang on 2018/9/22.
 */
public interface CustomerDaoMapper {
    @Deprecated
    List<String> checkUserHasApply(@Param("userId") String userId,
                                   @Param("ids") List<String> ids);

    List<ApiMatchData> getMatchOtherInfo(@Param("userId") String userId,
                                         @Param("ids") List<String> ids);
}
