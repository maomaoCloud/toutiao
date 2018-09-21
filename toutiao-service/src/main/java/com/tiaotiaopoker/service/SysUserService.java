package com.tiaotiaopoker.service;


import com.tiaotiaopoker.common.Pagination;
import com.tiaotiaopoker.pojo.SysUser;

import java.util.List;

public interface SysUserService {

    List<SysUser> queryUserByCondition(SysUser User, Pagination page);

    SysUser queryUserById(String UserId);

    int addOrUpdateUser(SysUser user, SysUser User);

    int editUserBySelective(SysUser user, SysUser User);

    SysUser checkAccountAndPassword(String userAccount, String userPassword);
}