package com.tiaotiaopoker.service.impl;


import com.tiaotiaopoker.MD5Util;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.common.Pagination;
import com.tiaotiaopoker.dao.SysUserMapper;
import com.tiaotiaopoker.pojo.SysUser;
import com.tiaotiaopoker.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Administrator
 */
@Component(value = "sysUserService")
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> queryUserByCondition(SysUser user, Pagination page) {
        Map<String, Object> paramMap = new HashMap<>();
        //用户名
        if (!StringUtils.isBlank(user.getUserAccount())) {
            paramMap.put("userAccount", user.getUserAccount());
        }
        List<SysUser> list = sysUserMapper.queryUserByCondition(paramMap);
        if (null != page) {
            page.setTotal(sysUserMapper.countUserByCondition(paramMap));
        }
        return list;
    }


    @Override
    public SysUser queryUserById(String userId) {

        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int addOrUpdateUser(SysUser user, SysUser loginUser) {
        int result = 0;
        if (StringUtils.isBlank(user.getUserId())) {
            user.setUserId(StringUtils.generateShortUUID());
            user.setUserCreateTime(new Date());
            user.setUserUpdateTime(new Date());
            user.setUserCreateUser(loginUser.getUserId());
            user.setUserUpdateUser(loginUser.getUserId());
            user.setUserState(1);
            String userPassword = user.getUserPassword();
            user.setUserPassword(MD5Util.toMd5(userPassword));
            result = sysUserMapper.insert(user);
        } else {
            user.setUserUpdateTime(new Date());
            user.setUserUpdateUser(loginUser.getUserId());
            if (user.getUserPassword().length() != 32) {
                user.setUserPassword(MD5Util.toMd5(user.getUserPassword()));
            }
            result = sysUserMapper.updateByPrimaryKeySelective(user);
        }
        return result;
    }

    @Override
    public int editUserBySelective(SysUser user, SysUser loginUser) {
        user.setUserUpdateTime(new Date());
        user.setUserUpdateUser(loginUser.getUserId());
        return sysUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public SysUser checkAccountAndPassword(String userAccount, String userPassword) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userAccount", userAccount);
        paramMap.put("userPassword", userPassword);
        List<SysUser> list = sysUserMapper.queryUserByCondition(paramMap);
        if (null != list && list.size() > 0) {
            SysUser loginUser = new SysUser();
            loginUser = list.get(0);
            return loginUser;
        } else {
            return null;
        }
    }
}
