package com.tiaotiaopoker.service;

import com.tiaotiaopoker.common.Pagination;
import com.tiaotiaopoker.pojo.SysHelp;
import com.tiaotiaopoker.pojo.SysUser;

import java.util.List;
import java.util.Map;

public interface SysHelpService {

    List<SysHelp> queryHelpByCondition(SysHelp Help, Pagination page);

    SysHelp queryHelpById(String HelpId);

    int addOrUpdateHelp(SysHelp Help, SysUser loginUser);

    int editHelpBySelective(SysHelp Help, SysUser loginUser);

    int setHelpSort(SysHelp Help, SysUser loginUser);
}
