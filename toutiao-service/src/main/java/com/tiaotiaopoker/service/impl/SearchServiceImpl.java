package com.tiaotiaopoker.service.impl;

import com.tiaotiaopoker.dao.SearchLogMapper;
import com.tiaotiaopoker.entity.ApiMatchData;
import com.tiaotiaopoker.pojo.Match;
import com.tiaotiaopoker.pojo.SearchLog;
import com.tiaotiaopoker.service.MatchService;
import com.tiaotiaopoker.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiekang on 2018/9/23.
 */
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchLogMapper searchLogMapper;
    @Autowired
    private MatchService    matchService;

    private static final Integer SEARCH_TYPE_OF_MATCH = 1;

    @Override
    public Map<String, Object> searchForMatch(String userId,
                                              String kw) {
        try {
            addSearchLog( SEARCH_TYPE_OF_MATCH, userId, kw );
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> resultMap = new HashMap<>();
        List<Match> datas = matchService.getMatchByKeyWord( kw );
        resultMap.put( "hasData", false );
        if( datas != null && datas.size() > 0 ) {
            resultMap.put( "hasData", true );
            List<ApiMatchData> resDatas = new ArrayList<>();
            for( Match data : datas ) {
                resDatas.add( ApiMatchData.genFromMatch( data ) );
            }
            resultMap.put( "data", datas );
        }

        return resultMap;
    }

    private void addSearchLog(int searchType,
                              String userId,
                              String kw) {
        SearchLog log = new SearchLog();
        log.setKw( kw );
        log.setSearchType( searchType );
        log.setUserId( userId );
        searchLogMapper.insertSelective( log );
    }
}
