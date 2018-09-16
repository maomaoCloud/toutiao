package com.tiaotiaopoker.service;

import com.tiaotiaopoker.pojo.MatchWithBLOBs;

import java.util.Map;

/**
 * Created by xiekang on 2018/9/9.
 */
public interface MatchService {
    void saveMatch(MatchWithBLOBs data);

    Map<String, Object> getMatchList(Integer pageNum,
                                     Integer pageSize);

    Map<String, Object> getMatchInfoById(String matchId);
}
