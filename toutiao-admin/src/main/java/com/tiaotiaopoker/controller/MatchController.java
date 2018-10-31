package com.tiaotiaopoker.controller;

import com.github.pagehelper.PageHelper;
import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.common.Pagination;
import com.tiaotiaopoker.pojo.HeadlineNews;
import com.tiaotiaopoker.pojo.Match;
import com.tiaotiaopoker.pojo.MatchWithBLOBs;
import com.tiaotiaopoker.pojo.SysUser;
import com.tiaotiaopoker.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("match")
public class MatchController {
    @Autowired
    private MatchService matchService;
    @Autowired
    private HttpSession session;

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期 注意这里的转化要和传进来的字符串的格式一直 如2015-9-9 就应该为yyyy-MM-dd
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }

    @RequestMapping("matchListPage")
    public ModelAndView matchListPage(ModelAndView mv,
                                      @ModelAttribute(value = "page") Pagination page,
                                      @ModelAttribute(value = "match") Match match) {
        PageHelper.startPage(page.getPage(), page.getRows());
        List<Match> list = matchService.queryMatchByCondition(match, page);
        mv.addObject("list", list);
        mv.addObject("page", page);
        mv.addObject("match", match);
        mv.setViewName("admin/match/matchListPage");
        return mv;
    }

    @RequestMapping("update")
    @ResponseBody
    public JsonResult update(MatchWithBLOBs match) {
        JsonResult jsonResult;
        SysUser sysUser = (SysUser) session.getAttribute("sysUser");
        try {
            if (null == sysUser) {
                jsonResult = JsonResult.FAILED("nologin");
            } else {
                int result = matchService.updateMatchBySelective(match, sysUser);
                if (result > 0) {
                    jsonResult = JsonResult.SUCCESS("更新成功");
                } else {
                    jsonResult = JsonResult.FAILED("更新失败");
                }
            }
        } catch (Exception e) {
            jsonResult = JsonResult.FAILED("更新接口异常————MatchController————update");
            e.printStackTrace();
        }
        return jsonResult;
    }

    @RequestMapping("remarkPage")
    public ModelAndView remarkPage(ModelAndView mv,String id){
        MatchWithBLOBs match = matchService.selectMatchById(id);
        mv.addObject("match",match);
        mv.setViewName("admin/match/remarkPage");
        return mv;
    }

    /*新增+编辑页*/
    @RequestMapping("addAndEditPage")
    public ModelAndView addAndEditPage(ModelAndView mv, @ModelAttribute(value = "match") MatchWithBLOBs match) {
        if (!StringUtils.isBlank(match.getId())) {
            MatchWithBLOBs matchWithBLOBs = matchService.selectMatchById(match.getId());
            mv.addObject("match", matchWithBLOBs);
        }
        mv.setViewName("admin/match/matchAddPage");
        return mv;
    }

    @RequestMapping(value = "addOrEdit", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addOrEdit(@ModelAttribute(value = "match") MatchWithBLOBs match) {
        JsonResult jsonResult;
        SysUser loginUser = (SysUser) session.getAttribute("sysUser");
        try {
            if (null == loginUser) {
                jsonResult = JsonResult.FAILED("nologin");
            } else {
                int result = matchService.updateMatchBySelective(match, loginUser);
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
}
