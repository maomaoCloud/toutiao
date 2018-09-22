package com.tiaotiaopoker.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.common.Pagination;
import com.tiaotiaopoker.dao.CustomerDaoMapper;
import com.tiaotiaopoker.dao.MatchMapper;
import com.tiaotiaopoker.entity.ApiMatchData;
import com.tiaotiaopoker.entity.ApiMatchDetail;
import com.tiaotiaopoker.pojo.Match;
import com.tiaotiaopoker.pojo.MatchExample;
import com.tiaotiaopoker.pojo.MatchWithBLOBs;
import com.tiaotiaopoker.pojo.SysUser;
import com.tiaotiaopoker.service.MatchService;
import com.tiaotiaopoker.service.OrderService;
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
    private MatchMapper       matchMapper;
    @Value("${match.banner.default}")
    private String            MATCH_BANNER_DEFAULT;
    @Autowired
    private OrderService      orderService;
    @Autowired
    private CustomerDaoMapper customerDaoMapper;

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
                                            Integer pageSize,
                                            String userId) {
        Page<Object> page = PageHelper.startPage( pageNum, pageSize );
        MatchExample ex = new MatchExample();
        ex.setOrderByClause( "start_date" );
        ex.createCriteria().andIsDeleteEqualTo( Constants.DataBaseCommon.IS_DELETE_FALSE ).andStatueEqualTo(
                1 ).andStartDateGreaterThanOrEqualTo( new Date() );
        List<Match> dataList = matchMapper.selectByExample( ex );
        List<ApiMatchData> resDataList = new ArrayList<>();
        ApiMatchData apiData;
        List<String> ids = new ArrayList<>();
        for( Match md : dataList ) {
            ids.add( md.getId() );
            apiData = ApiMatchData.genFromMatch( md );
            resDataList.add( apiData );
        }

        int totalPages = page.getPages();
        Map<String, Object> resultMap = new HashMap<>();
        if( ids.size() > 0 ) {
            List<String> matchIds = customerDaoMapper.checkUserHasApply( userId, ids );
            Set<String> matchIdSet = new HashSet<>( matchIds );
            for( ApiMatchData data : resDataList ) {
                data.setHasApply( matchIdSet.contains( data.getId() ) );
            }

        }


        resultMap.put( "data", resDataList );
        resultMap.put( "hasMore", totalPages > pageNum );

        return resultMap;
    }

    @Override
    public Map<String, Object> getMatchInfoById(String matchId) {
        Map<String, Object> resultMap = new HashMap<>();
        if( StringUtil.isNotEmpty( matchId ) ) {
            MatchExample ex = new MatchExample();
            ex.createCriteria().andIdEqualTo( matchId );
            List<MatchWithBLOBs> matches = matchMapper.selectByExampleWithBLOBs( ex );

            if( matches != null && matches.size() > 0 ) {
                ApiMatchDetail data = ApiMatchDetail.genFromMatch( matches.get( 0 ) );
                List<ApiMatchDetail.MatchApplyUser> matchApplyUsersList = orderService.getApplyUserByMatchId( matchId );
                data.setApplyList( matchApplyUsersList );
                resultMap.put( "data", data );
            }
        }
        return resultMap;
    }

    public List<Match> queryMatchByCondition(Match match,
                                             Pagination page) {
        Map<String, Object> paramMap = new HashMap<>();
        if( null != match.getStatue() ) {
            paramMap.put( "statue", match.getStatue() );
        }
        if( !StringUtils.isBlank( match.getTheme() ) ) {
            paramMap.put( "theme", match.getTheme() );
        }
        List<Match> list = matchMapper.queryMatchByCondition( paramMap );
        page.setTotal( matchMapper.countMatchByCondition( paramMap ) );
        return list;
    }

    @Override
    public int updateMatchBySelective(MatchWithBLOBs match,
                                      SysUser sysUser) {
        match.setOperateUser( sysUser.getUserId() );
        return matchMapper.updateByExampleSelective( match, null );
    }

    public MatchWithBLOBs getMatchDataById(String matchId) {
        MatchExample example = new MatchExample();
        example.createCriteria().andIdEqualTo( matchId );
        List<MatchWithBLOBs> matches = matchMapper.selectByExampleWithBLOBs( example );
        if( matches != null && matches.size() > 0 ) return matches.get( 0 );
        return null;
    }

    @Override
    public MatchWithBLOBs selectMatchById(String id) {
        return matchMapper.selectMatchById( id );
    }
}
