package com.tiaotiaopoker.service;

import com.tiaotiaopoker.common.Pagination;
import com.tiaotiaopoker.pojo.Match;
import com.tiaotiaopoker.pojo.MatchWithBLOBs;
import com.tiaotiaopoker.pojo.SysUser;

import java.util.List;
import java.util.Map;

/**
 * Created by xiekang on 2018/9/9.
 */
public interface MatchService {
    void saveMatch(MatchWithBLOBs data);

    Map<String, Object> getMatchList(Integer pageNum,
                                     Integer pageSize,
                                     String userId);

    Map<String, Object> getMatchInfoById(String matchId);

    List<Match> queryMatchByCondition(Match match,
                                      Pagination page);

    int updateMatchBySelective(MatchWithBLOBs match,
                               SysUser sysUser);

    MatchWithBLOBs selectMatchById(String id);

    MatchWithBLOBs getMatchDataById(String matchId);
}
