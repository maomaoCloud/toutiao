package com.tiaotiaopoker.service.impl;

import com.tiaotiaopoker.dao.MatchAuthorizationMapper;
import com.tiaotiaopoker.pojo.MatchAuthorization;
import com.tiaotiaopoker.pojo.MatchAuthorizationExample;
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

    @Override
    public int deleteAuthorizationByMatchId(String matchId) {
        MatchAuthorizationExample authorizationExample = new MatchAuthorizationExample();
        MatchAuthorizationExample.Criteria criteria = authorizationExample.createCriteria();
        criteria.andMatchIdEqualTo(matchId);
        return authorizationMapper.deleteByExample(authorizationExample);
    }

    @Override
    public int addAuthorization(MatchAuthorization matchAuthor) {
        int result = 0;
        String[] userIds = matchAuthor.getUserId().split(",");
        String[] userNames = matchAuthor.getUserTrueName().split(",");
        MatchAuthorization matchAuthorization = new MatchAuthorization();
        matchAuthorization.setMatchName(matchAuthor.getMatchName());
        matchAuthorization.setMatchId(matchAuthor.getMatchId());
        for (int i = 0; i < userIds.length; i++) {
            matchAuthorization.setUserId(userIds[i]);
            matchAuthorization.setUserTrueName(userNames[i]);
            int count = authorizationMapper.insertSelective(matchAuthorization);
            result += count;
        }
        return result;
    }
}
