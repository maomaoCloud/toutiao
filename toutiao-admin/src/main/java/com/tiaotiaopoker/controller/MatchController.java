package com.tiaotiaopoker.controller;

import com.github.pagehelper.PageHelper;
import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.common.Pagination;
import com.tiaotiaopoker.pojo.Match;
import com.tiaotiaopoker.pojo.MatchWithBLOBs;
import com.tiaotiaopoker.pojo.SysUser;
import com.tiaotiaopoker.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("match")
public class MatchController {
    @Autowired
    private MatchService matchService;
    @Autowired
    private HttpSession session;

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
}
