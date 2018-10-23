package com.tiaotiaopoker.controller;

import com.github.pagehelper.PageHelper;
import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.common.Pagination;
import com.tiaotiaopoker.pojo.SysHelp;
import com.tiaotiaopoker.pojo.SysUser;
import com.tiaotiaopoker.service.SysHelpService;
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
@RequestMapping("help")
public class HelpController {

    @Autowired
    private SysHelpService sysHelpService;
    @Autowired
    private HttpSession session;

    /*新增+编辑页*/
    @RequestMapping("addAndEditPage")
    public ModelAndView addAndEditPage(ModelAndView mv, @ModelAttribute(value = "help") SysHelp help) {
        if (!StringUtils.isBlank(help.getHelpId())) {
            help = sysHelpService.queryHelpById(help.getHelpId());
        }
        mv.addObject("help", help);
        mv.setViewName("admin/SysHelp/helpAddPage");
        return mv;
    }

    /*列表页*/
    @RequestMapping("helpListPage")
    public ModelAndView listPage(ModelAndView mv,
                                 @ModelAttribute(value = "page") Pagination page,
                                 @ModelAttribute(value = "SysHelp") SysHelp help) {
        PageHelper.startPage(page.getPage(), page.getRows());
        List<SysHelp> list = sysHelpService.queryHelpByCondition(help, page);
        mv.addObject("help", help);
        mv.addObject("page", page);
        mv.addObject("list", list);
        mv.setViewName("admin/SysHelp/listPage");
        return mv;
    }

    @RequestMapping(value = "addOrEdit", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addOrEdit(@ModelAttribute(value = "help") SysHelp help) {
        JsonResult jsonResult;
        SysUser loginUser = (SysUser) session.getAttribute("sysUser");
        try {
            if (null == loginUser) {
                jsonResult = JsonResult.FAILED("nologin");
            } else {
                int result = sysHelpService.addOrUpdateHelp(help, loginUser);
                if (result > 0) {
                    jsonResult = JsonResult.SUCCESS("编辑成功");
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

    /*置顶接口*/
    @RequestMapping(value = "setTop",
            method = RequestMethod.POST)
    @ResponseBody
    public JsonResult setTop(SysHelp help) {
        JsonResult jsonResult;
        SysUser loginUser = (SysUser) session.getAttribute("sysUser");
        try {
            if (null == loginUser) {
                jsonResult = JsonResult.FAILED("nologin");
            } else {
                if (StringUtils.isBlank(help.getHelpId()) || null == help.getHelpSort()) {
                    jsonResult = JsonResult.FAILED("参数缺失");
                } else {
                    int result = sysHelpService.setHelpSort(help, loginUser);
                    if (result > 0) {
                        jsonResult = JsonResult.SUCCESS();
                    } else {
                        jsonResult = JsonResult.FAILED();
                    }
                }
            }
        } catch (Exception e) {
            jsonResult = JsonResult.FAILED("置顶接口异常");
            e.printStackTrace();
        }
        return jsonResult;

    }

    /*更新接口(上下架+删除)*/
    @RequestMapping("update")
    @ResponseBody
    public JsonResult helpUpdate(SysHelp help) {
        JsonResult jsonResult;
        SysUser loginUser = (SysUser) session.getAttribute("sysUser");
        try {
            if (null == loginUser) {
                jsonResult = JsonResult.FAILED("nologin");
            } else {
                if (StringUtils.isBlank(help.getHelpId())) {
                    jsonResult = JsonResult.FAILED("id缺失");
                } else {
                    int result = sysHelpService.editHelpBySelective(help, loginUser);
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
