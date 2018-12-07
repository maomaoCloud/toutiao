package com.tiaotiaopoker.service;

import com.tiaotiaopoker.entity.MatchTeamResultDto;
import com.tiaotiaopoker.pojo.MatchTeamMember;
import com.tiaotiaopoker.pojo.MatchTeamResult;

import java.util.List;

public interface MatchTeamResultService {
    List<MatchTeamResult> queryMatchTeamResultByCondition (MatchTeamResult matchTeamResult);

    List<MatchTeamResultDto> sortMatchTeamResult (MatchTeamResult result);

    List<MatchTeamMember> getAllTeams (String matchId);

}
