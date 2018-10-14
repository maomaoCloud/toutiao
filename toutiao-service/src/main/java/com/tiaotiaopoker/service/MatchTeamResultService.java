package com.tiaotiaopoker.service;

import com.tiaotiaopoker.pojo.MatchTeamResult;

import java.util.List;

public interface MatchTeamResultService {
    List<MatchTeamResult> queryMatchTeamResultByCondition(MatchTeamResult matchTeamResult);
}
