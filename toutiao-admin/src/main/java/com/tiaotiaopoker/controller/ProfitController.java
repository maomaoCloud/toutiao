package com.tiaotiaopoker.controller;

import com.github.pagehelper.PageHelper;
import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.common.Pagination;
import com.tiaotiaopoker.pojo.ProfitPercent;
import com.tiaotiaopoker.pojo.SysUser;
import com.tiaotiaopoker.service.ProfitPercentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("profit")
public class ProfitController {
    @Autowired
    private ProfitPercentService profitPercentService;
    @Autowired
    private HttpSession session;

    @RequestMapping("addAndEditPage")
    public ModelAndView addAndEditPage(ModelAndView mv, @ModelAttribute(value = "profit") ProfitPercent profit) {
        profit.setProfitState(1);
        if (!StringUtils.isBlank(profit.getProfitId())) {
            profit = profitPercentService.queryProfitById(profit.getProfitId());
        }
        mv.addObject("profit", profit);
        mv.setViewName("admin/profit/profitAddPage");
        return mv;
    }

    @RequestMapping(value = "addOrEdit", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addOrEdit(@ModelAttribute(value = "profit") ProfitPercent profit) {
        JsonResult jsonResult;
        SysUser loginUser = (SysUser) session.getAttribute("sysUser");
        try {
            if (null == loginUser) {
                jsonResult = JsonResult.FAILED("nologin");
            } else {
                int result = profitPercentService.addOrUpdateProfit(profit, loginUser);
                if (result > 0) {
                    jsonResult = JsonResult.SUCCESS("编辑成功");
                } else if (result == -1) {
                    jsonResult = JsonResult.FAILED("区间冲突");
                } else {
                    jsonResult = JsonResult.FAILED("编辑失败");
                }
            }
        } catch (Exception e) {
            jsonResult = JsonResult.FAILED("编辑接口异常");
            e.printStackTrace();
        }
        return jsonResult;
    }

    /*列表页*/
    @RequestMapping("listPage")
    public ModelAndView listPage(ModelAndView mv,
                                 @ModelAttribute(value = "page") Pagination page,
                                 @ModelAttribute(value = "profit") ProfitPercent profit) {
        PageHelper.startPage(page.getPage(), page.getRows());
        List<ProfitPercent> list = profitPercentService.queryProfitByCondition(profit, page);
        mv.addObject("news", profit);
        mv.addObject("page", page);
        mv.addObject("list", list);
        mv.setViewName("admin/profit/profitListPage");
        return mv;
    }

    /*更新接口*/
    @RequestMapping("update")
    @ResponseBody
    public JsonResult profitUpdate(ProfitPercent profit) {
        JsonResult jsonResult;
        SysUser loginUser = (SysUser) session.getAttribute("sysUser");
        try {
            if (null == loginUser) {
                jsonResult = JsonResult.FAILED("nologin");
            } else {
                if (StringUtils.isBlank(profit.getProfitId())) {
                    jsonResult = JsonResult.FAILED("id缺失");
                } else {
                    int result = profitPercentService.editProfitBySelective(profit, loginUser);
                    if (result > 0) {
                        jsonResult = JsonResult.SUCCESS("更新成功");
                    } else {
                        jsonResult = JsonResult.FAILED("更新失败");
                    }
                }
            }
        } catch (Exception e) {
            jsonResult = JsonResult.FAILED("更新接口异常");
            e.printStackTrace();
        }
        return jsonResult;
    }
}
