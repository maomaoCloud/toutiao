package com.tiaotiaopoker.service.impl;

import com.tiaotiaopoker.dao.MatchAuthorizationMapper;
import com.tiaotiaopoker.pojo.MatchAuthorization;
import com.tiaotiaopoker.service.MatchAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component(value = "matchAuthorizationSevice")
@Transactional
public class MatchAuthorizationSeviceImpl implements MatchAuthorizationService {

    @Autowired
    private MatchAuthorizationMapper authorizationMapper;

    @Override
    public List<MatchAuthorization> queryAuthorizationList(String matchId) {
        return authorizationMapper.queryAuthorizationList(matchId);
    }
}
