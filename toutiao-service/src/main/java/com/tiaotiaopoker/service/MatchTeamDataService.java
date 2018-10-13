package com.tiaotiaopoker.service;

import com.tiaotiaopoker.pojo.AppUser;

import java.util.List;

public interface MatchTeamDataService {
    List<AppUser> sortMatchTeamByRuleSeat(Integer ruleSeat, String matchId);

    List<AppUser> queryTeamUserByMatchId(String matchId);
}
