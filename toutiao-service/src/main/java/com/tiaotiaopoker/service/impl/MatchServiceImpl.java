package com.tiaotiaopoker.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.common.Pagination;
import com.tiaotiaopoker.dao.ApplyOrderMapper;
import com.tiaotiaopoker.dao.CustomerDaoMapper;
import com.tiaotiaopoker.dao.MatchMapper;
import com.tiaotiaopoker.dao.WithdrawLogMapper;
import com.tiaotiaopoker.entity.ApiMatchData;
import com.tiaotiaopoker.entity.ApiMatchDetail;
import com.tiaotiaopoker.entity.MatchApplyUser;
import com.tiaotiaopoker.pojo.*;
import com.tiaotiaopoker.service.AppUserService;
import com.tiaotiaopoker.service.MatchService;
import com.tiaotiaopoker.service.OrderService;
import com.tiaotiaopoker.service.ProfitPercentService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.zip.Inflater;

/**
 * Created by xiekang on 2018/9/9.
 */
@Service
public class MatchServiceImpl implements MatchService {
    @Autowired
    private MatchMapper          matchMapper;
    @Value("${match.banner.default}")
    private String               MATCH_BANNER_DEFAULT;
    @Autowired
    private OrderService         orderService;
    @Autowired
    private CustomerDaoMapper    customerDaoMapper;
    @Autowired
    private ProfitPercentService profitPercentService;
    @Autowired
    private ApplyOrderMapper     applyOrderMapper;
    @Autowired
    private WithdrawLogMapper    withdrawLogMapper;
    @Autowired
    private AppUserService       appUserService;


    @Override
    public void saveMatch(@RequestBody MatchWithBLOBs data) {
        data.setId( StringUtils.generateShortUUID() );
        data.setIsDelete( Constants.DataBaseCommon.IS_DELETE_FALSE );
        if( StringUtils.isBlank( data.getBannerImg() ) ) {
            data.setBannerImg( MATCH_BANNER_DEFAULT );
        }

        Integer percent = profitPercentService.getPercentByPrice( data.getFee() );
        data.setFeeSharePercent( percent );

        data.setAddTime( new Date() );
        matchMapper.insertSelective( data );

        try {
            //更新用户信息
            AppUser appUser = new AppUser();
            appUser.setId( data.getUserId() );
            appUser.setPhone( data.getPhone() );
            appUser.setTrueName( data.getContactName() );
            appUser.setWxQrImg( data.getWxHead() );
            appUserService.updateAppUserInfo( appUser );
        } catch (Exception e) {
        }
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
    public Map<String, Object> getMatchInfoById(String matchId,
                                                String userId) {
        Map<String, Object> resultMap = new HashMap<>();
        if( StringUtil.isNotEmpty( matchId ) ) {
            MatchExample ex = new MatchExample();
            ex.createCriteria().andIdEqualTo( matchId );
            List<MatchWithBLOBs> matches = matchMapper.selectByExampleWithBLOBs( ex );

            List<String> ids = new ArrayList<>();
            ids.add( matchId );
            List<String> applyIds = customerDaoMapper.checkUserHasApply( userId, ids );
            Set<String> applySet = new HashSet<>( applyIds );

            if( matches != null && matches.size() > 0 ) {
                ApiMatchDetail data = ApiMatchDetail.genFromMatch( matches.get( 0 ) );
                List<MatchApplyUser> matchApplyUsersList = orderService.getApplyUserByMatchId( matchId );
                data.setApplyList( matchApplyUsersList );
                data.setHasApply( applySet.contains( data.getId() ) );
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
    public List<Match> getMatchByKeyWord(String kw) {
        MatchExample example = new MatchExample();
        example.setOrderByClause( "order_no DESC, add_time DESC" );
        example.createCriteria().andThemeLike( "%" + kw + "%" ).andStatueEqualTo( 1 );
        return matchMapper.selectByExample( example );
    }

    @Override
    public MatchWithBLOBs selectMatchById(String id) {
        return matchMapper.selectMatchById( id );
    }

    @Override
    public Map<String, Object> getMatchListManage(String userId) {
        Map<String, Object> resultMap = new HashMap<>();
        List<ApiMatchData> resultList = new ArrayList<>();

        MatchExample matchExample = new MatchExample();
        MatchExample.Criteria matchExampleCriteria = matchExample.createCriteria();
        matchExampleCriteria.andUserIdEqualTo( userId );
        matchExample.setOrderByClause( "add_time asc" );
        List<Match> matchList = matchMapper.selectByExample( matchExample );
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put( "userId", userId );
        //累计收益
        int sumPayMoney = applyOrderMapper.sumPayMoneyByCondition( paramMap );
        //累计可提现收益
        paramMap.put( "signState", Constants.Order.USER_SIGN_STATUS_END );
        int sumAvailableWithdraw = applyOrderMapper.sumPayMoneyByCondition( paramMap );
        //可提现
        int alreadyWithdraw = withdrawLogMapper.sumMoneyByUserId( userId );
        int availableWithdraw = sumAvailableWithdraw - alreadyWithdraw;
        //格式list的同时找出距离当前时间最近的未开始或者进行中的比赛
        int index = 0;
        int flagIndex = 0;
        DateTime nowDate = new DateTime( new Date() );
        DateTime flagDate = null;
        for( Match match : matchList ) {
            //判断比赛开始时间
            if( nowDate.compareTo( new DateTime( match.getEndDate() ) ) < 0 ) {
                if( null == flagDate ) {
                    flagDate = new DateTime( match.getStartDate() );
                } else {
                    if( flagDate.compareTo( new DateTime( match.getStartDate() ) ) > 0 ) {
                        flagDate = new DateTime( match.getStartDate() );
                        flagIndex = index;
                    }
                }
            }
            index += 1;
            ApiMatchData apiMatch = ApiMatchData.genFromMatch( match );
            // 已签到人数
            int signInNum = applyOrderMapper.sumSignInNumByMatchId( match.getId() );
            apiMatch.setSignInNum( signInNum );
            // 已报名人数
            int signUpNum = applyOrderMapper.sumSignUpNumByMatchId( match.getId() );
            apiMatch.setSignUpNum( signUpNum );
            //本场比赛收益
            paramMap.put( "matchId", match.getId() );
            int sumPayMoneyByMatch = applyOrderMapper.sumPayMoneyByCondition( paramMap );
            apiMatch.setSumMoney( sumPayMoneyByMatch );
            resultList.add( apiMatch );
        }
        resultMap.put( "index", flagIndex );
        resultMap.put( "sumPayMoney", sumPayMoney );
        resultMap.put( "availableWithdraw", availableWithdraw );
        resultMap.put( "matchList", resultList );
        return resultMap;
    }
}
