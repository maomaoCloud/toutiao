package com.tiaotiaopoker.service;

import com.tiaotiaopoker.pojo.MatchAuthorization;

import java.util.List;

public interface MatchAuthorizationService {
    List<MatchAuthorization> queryAuthorizationList(String matchId);
}
