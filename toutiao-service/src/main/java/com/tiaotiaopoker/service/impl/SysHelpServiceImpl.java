package com.tiaotiaopoker.service.impl;

import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.common.Pagination;
import com.tiaotiaopoker.dao.SysHelpMapper;
import com.tiaotiaopoker.pojo.SysHelp;
import com.tiaotiaopoker.pojo.SysUser;
import com.tiaotiaopoker.service.SysHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class SysHelpServiceImpl implements SysHelpService {

    @Autowired
    private SysHelpMapper sysHelpMapper;

    @Override
    public List<SysHelp> queryHelpByCondition(SysHelp Help, Pagination page) {
        Map<String, Object> paramMap = new HashMap<>();
        if (!StringUtils.isBlank(Help.getHelpTitle())) {
            paramMap.put("HelpTitle", Help.getHelpTitle());
        }
        if (null != Help.getHelpStatus()) {
            paramMap.put("HelpStatus", Help.getHelpStatus());
        }
        if (null != page) {
            page.setTotal(sysHelpMapper.countHelpByCondition(paramMap));
        }
        List<SysHelp> list = sysHelpMapper.queryHelpByCondition(paramMap);

        return list;
    }

    @Override
    public SysHelp queryHelpById(String HelpId) {

        return sysHelpMapper.selectByPrimaryKey(HelpId);
    }

    @Override
    public int addOrUpdateHelp(SysHelp Help, SysUser loginUser) {
        int result = 0;
        if (StringUtils.isBlank(Help.getHelpId())) {
            Help.setHelpId(StringUtils.generateShortUUID());
            Help.setHelpCreateTime(new Date());
            Help.setHelpUpdateTime(new Date());
            Help.setHelpCreateUser(loginUser.getUserId());
            Help.setHelpUpdateUser(loginUser.getUserId());
            Help.setHelpStatus(2);
            Help.setHelpSort(0);
            result = sysHelpMapper.insert(Help);
        } else {
            Help.setHelpUpdateTime(new Date());
            Help.setHelpUpdateUser(loginUser.getUserId());
            result = sysHelpMapper.updateByPrimaryKeySelective(Help);
        }
        return result;
    }

    @Override
    public int editHelpBySelective(SysHelp Help, SysUser loginUser) {
        int HelpStatus = Help.getHelpStatus();
        if (Help.getHelpStatus() == 0 || Help.getHelpStatus() == 2) {
            //删除和下架时，需要将排序字段置为0，同时更新其他排序字段
            Help.setHelpSort(0);
            setHelpSort(Help, loginUser);
        }
        //设置状态（重置排序字段的同时会更新状态）
        Help.setHelpStatus(HelpStatus);
        Help.setHelpUpdateTime(new Date());
        Help.setHelpUpdateUser(loginUser.getUserId());
        return sysHelpMapper.updateByPrimaryKeySelective(Help);
    }

    @Override
    public int setHelpSort(SysHelp Help, SysUser loginUser) {
        int result = 0;
        Map<String, Object> paramMap = new HashMap<>();
        SysHelp oldPositionHelp = sysHelpMapper.selectByPrimaryKey(Help.getHelpId());
        if (oldPositionHelp.getHelpSort() == Help.getHelpSort()) {
            result = 1;
            return result;
        } else if (0 == oldPositionHelp.getHelpSort()) {
            // 未排序置于排序中
            paramMap.put("startSort", Help.getHelpSort());
            result = resetSort(paramMap, 1);
        } else if (0 == Help.getHelpSort()) {
            // 取消排序
            paramMap.put("startSort", oldPositionHelp.getHelpSort());
            resetSort(paramMap, -1);
        } else if (oldPositionHelp.getHelpSort() > Help.getHelpSort()) {
            // 向上排序
            paramMap.put("startSort", Help.getHelpSort());
            paramMap.put("endSort", oldPositionHelp.getHelpSort());
            result = resetSort(paramMap, 1);
        } else if (oldPositionHelp.getHelpSort() < Help.getHelpSort()) {
            // 向下排序
            paramMap.put("startSort", oldPositionHelp.getHelpSort());
            paramMap.put("endSort", Help.getHelpSort());
            result = resetSort(paramMap, -1);
        } else {
            result = 1;
            return result;
        }
        //置顶的同时上架
        Help.setHelpStatus(1);
        Help.setHelpUpdateTime(new Date());
        Help.setHelpUpdateUser(loginUser.getUserId());
        result = sysHelpMapper.updateByPrimaryKeySelective(Help);
        return result;
    }

    private int resetSort(Map<String, Object> paramMap, int plusOrMinus) {
        int result = 0;
        List<SysHelp> list = sysHelpMapper.queryHelpByCondition(paramMap);
        for (SysHelp Help : list) {
            Help.setHelpSort(Help.getHelpSort() + plusOrMinus);
            result = sysHelpMapper.updateByPrimaryKeySelective(Help);
        }
        return result;
    }
}
