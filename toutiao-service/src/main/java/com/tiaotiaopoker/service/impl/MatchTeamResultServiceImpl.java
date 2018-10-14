package com.tiaotiaopoker.service.impl;

import com.tiaotiaopoker.dao.MatchTeamResultMapper;
import com.tiaotiaopoker.pojo.MatchTeamResult;
import com.tiaotiaopoker.pojo.MatchTeamResultExample;
import com.tiaotiaopoker.service.MatchTeamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MatchTeamResultServiceImpl implements MatchTeamResultService {

    @Autowired
    private MatchTeamResultMapper matchTeamResultMapper;

    @Override
    public List<MatchTeamResult> queryMatchTeamResultByCondition(MatchTeamResult matchTeamResult) {
        MatchTeamResultExample example = new MatchTeamResultExample();
        MatchTeamResultExample.Criteria criteria = example.createCriteria();
        criteria.andMatchIdEqualTo(matchTeamResult.getMatchId());
        criteria.andTurnNumberEqualTo(matchTeamResult.getTurnNumber());
        return matchTeamResultMapper.selectByExample(example);
    }
}
