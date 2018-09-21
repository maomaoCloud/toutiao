package com.tiaotiaopoker.service.impl;

import com.tiaotiaopoker.dao.AppUserMapper;
import com.tiaotiaopoker.pojo.AppUser;
import com.tiaotiaopoker.pojo.AppUserExample;
import com.tiaotiaopoker.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by xiekang on 2018/9/17.
 */
@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserMapper appUserMapper;

    @Override
    public AppUser getUserByOpenId(String openId) {
        AppUserExample example = new AppUserExample();
        example.createCriteria().andOpenIdEqualTo( openId );

        List<AppUser> appUsers = appUserMapper.selectByExample( example );
        if( appUsers != null && appUsers.size() > 0 ) return appUsers.get( 0 );

        return null;
    }

    @Override
    public void addAppUser(AppUser appUser) {
        appUserMapper.insertSelective( appUser );
    }

    @Override
    public void updateAppUserInfo(AppUser user) {
        user.setUpdateInfoDate( new Date() );
        user.setLastLoginDateTime( new Date() );
        appUserMapper.updateByPrimaryKeySelective( user );
    }

    @Override
    public void updateUserApplyInfo(String userId,
                                    String trueName,
                                    String userPhone) {
        AppUser user = new AppUser();
        user.setId( userId );
        user.setTrueName( trueName );
        user.setPhone( userPhone );
        appUserMapper.updateByPrimaryKeySelective( user );
    }
}
