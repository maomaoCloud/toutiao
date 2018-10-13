package com.tiaotiaopoker.service.impl;

import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.dao.ApplyOrderMapper;
import com.tiaotiaopoker.dao.MatchTeamDataMapper;
import com.tiaotiaopoker.pojo.AppUser;
import com.tiaotiaopoker.pojo.ApplyOrder;
import com.tiaotiaopoker.pojo.ApplyOrderExample;
import com.tiaotiaopoker.service.MatchTeamDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MatchTeamDataServiceImpl implements MatchTeamDataService {

    @Autowired
    private MatchTeamDataMapper matchTeamDataMapper;

    @Autowired
    private ApplyOrderMapper applyOrderMapper;

    @Override
    public List<AppUser> sortMatchTeamByRuleSeat(Integer ruleSeat, String matchId) {
        //查出订单中携带搭档且两人状态都为已签到
        List<AppUser> userListSignBoth = new ArrayList<>();
        ApplyOrderExample orderExampleSignBoth = new ApplyOrderExample();
        ApplyOrderExample.Criteria criteriaSignBoth = orderExampleSignBoth.createCriteria();
        criteriaSignBoth.andUserSignStatusEqualTo(new Integer(Constants.Order.SIGN_IN)).andPartnerSignStatueEqualTo(new Integer(Constants.Order.SIGN_IN));
        orderExampleSignBoth.setOrderByClause("add_time asc");
        List<ApplyOrder> applyOrdersBoth = applyOrderMapper.selectByExample(orderExampleSignBoth);

        //查出订单中携带搭档但是仅有一人签到或者不携带搭档
        ApplyOrderExample orderExampleSignUser = new ApplyOrderExample();
        ApplyOrderExample.Criteria criteriaSignUser = orderExampleSignUser.createCriteria();
        criteriaSignUser.andUserSignStatusEqualTo(new Integer(Constants.Order.SIGN_IN)).andPartnerSignStatueNotEqualTo(new Integer(Constants.Order.SIGN_IN));
        orderExampleSignUser.setOrderByClause("add_time asc");
        List<ApplyOrder> applyOrdersUser = applyOrderMapper.selectByExample(orderExampleSignUser);

        //查出订单中携带搭档但是仅搭档签到
        ApplyOrderExample orderExampleSignPartner = new ApplyOrderExample();
        ApplyOrderExample.Criteria criteriaSignPartner = orderExampleSignPartner.createCriteria();
        criteriaSignPartner.andUserSignStatusNotEqualTo(new Integer(Constants.Order.SIGN_IN)).andPartnerSignStatueEqualTo(new Integer(Constants.Order.SIGN_IN));
        orderExampleSignPartner.setOrderByClause("add_time asc");
        List<ApplyOrder> applyOrdersPartner = applyOrderMapper.selectByExample(orderExampleSignPartner);

        if (ruleSeat.equals(Constants.ruleSeat.SORT_NEAR)) {

        } else if (ruleSeat.equals(Constants.ruleSeat.SORT_BEGIN_END)) {
            
        } else if (ruleSeat.equals(Constants.ruleSeat.SRTT_MIDDLE)) {

        }
        List<AppUser> userList = getUserList(applyOrdersBoth);
        userList.addAll(getUserList(applyOrdersUser));
        userList.addAll(getUserList(applyOrdersPartner));

        return userList;
    }

    @Override
    public List<AppUser> queryTeamUserByMatchId(String matchId) {
        return matchTeamDataMapper.queryTeamUserByMatchId(matchId);
    }

    private List<AppUser> getUserList(List<ApplyOrder> applyOrders) {
        List<AppUser> userList = new ArrayList<>();
        for (ApplyOrder order : applyOrders) {
            if (order.getUserSignStatus().equals(Constants.Order.USER_SIGN_STATUS_END)) {
                AppUser user = new AppUser();
                user.setId(order.getUserId());
                user.setNickName(order.getUserName());
                user.setAvatarUrl(order.getUserHead());
                userList.add(user);
            }
            if (order.getPartnerSignStatue().equals(Constants.Order.USER_SIGN_STATUS_END)) {
                AppUser partner = new AppUser();
                partner.setId(order.getPartnerId());
                partner.setNickName(order.getUserName());
                partner.setAvatarUrl(order.getUserHead());
                userList.add(partner);
            }
        }
        return userList;
    }
}
