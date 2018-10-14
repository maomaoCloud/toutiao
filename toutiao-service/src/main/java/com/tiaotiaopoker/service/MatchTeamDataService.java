package com.tiaotiaopoker.service;

import com.tiaotiaopoker.entity.MatchTeamDataDto;
import com.tiaotiaopoker.pojo.AppUser;
import com.tiaotiaopoker.pojo.MatchTeamData;

import java.util.List;

public interface MatchTeamDataService {
    List<AppUser> sortMatchTeamByRuleSeat(Integer ruleSeat, String matchId);

    List<AppUser> queryTeamUserByMatchId(String matchId);

    int saveMatchTeamDataAndMember(String[] userIds, String matchId, Integer turnNumber);

    List<MatchTeamDataDto> queryTeamDataByCondition(MatchTeamData matchTeamData);

    int saveMatchTeamData(MatchTeamData matchTeamData);

    int saveMatchTeamDataAndMemberNext(String matchId, Integer turnNumber);
}
