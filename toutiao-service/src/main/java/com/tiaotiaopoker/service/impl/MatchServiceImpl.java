package com.tiaotiaopoker.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.dao.MatchMapper;
import com.tiaotiaopoker.entity.ApiMatchData;
import com.tiaotiaopoker.pojo.Match;
import com.tiaotiaopoker.pojo.MatchExample;
import com.tiaotiaopoker.pojo.MatchWithBLOBs;
import com.tiaotiaopoker.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

/**
 * Created by xiekang on 2018/9/9.
 */
@Service
public class MatchServiceImpl implements MatchService {
    @Autowired
    private MatchMapper matchMapper;
    @Value("${match.banner.default}")
    private String      MATCH_BANNER_DEFAULT;

    @Override
    public void saveMatch(@RequestBody MatchWithBLOBs data) {
        data.setId( StringUtils.generateShortUUID() );
        data.setIsDelete( Constants.DataBaseCommon.IS_DELETE_FALSE );
        if( StringUtils.isBlank( data.getBannerImg() ) ) {
            data.setBannerImg( MATCH_BANNER_DEFAULT );
        }
        data.setAddTime( new Date() );
        matchMapper.insertSelective( data );
    }

    @Override
    public Map<String, Object> getMatchList(Integer pageNum,
                                            Integer pageSize) {
        Page<Object> page = PageHelper.startPage( pageNum, pageSize );
        MatchExample ex = new MatchExample();
        ex.setOrderByClause( "start_date" );
        ex.createCriteria().andIsDeleteEqualTo( Constants.DataBaseCommon.IS_DELETE_FALSE ).andStatueEqualTo(
                1 ).andStartDateGreaterThanOrEqualTo( new Date() );
        List<Match> dataList = matchMapper.selectByExample( ex );
        List<ApiMatchData> resDataList = new ArrayList<>();
        ApiMatchData apiData;
        for( Match md : dataList ) {
            apiData = ApiMatchData.genFromMatch( md );
            // TODO 设置订阅人数
            resDataList.add( apiData );
        }

        int totalPages = page.getPages();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put( "data", resDataList );
        resultMap.put( "hasMore", totalPages > pageNum );

        return resultMap;
    }
}
