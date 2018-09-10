package com.tiaotiaopoker.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.common.Pagination;
import com.tiaotiaopoker.pojo.HeadlineNews;
import com.tiaotiaopoker.service.HeadlineNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("news")
@Scope("prototype")
public class HeadlineNewsController {

    @Autowired
    private HeadlineNewsService headlineNewsService;

    /*新增+编辑页*/
    @RequestMapping("addAndEditPage")
    public ModelAndView addAndEditPage(ModelAndView mv, @ModelAttribute(value = "news") HeadlineNews news) {
        if (!StringUtils.isBlank(news.getNewsId())) {
            news = headlineNewsService.queryNewsById(news.getNewsId());
        }
        mv.addObject("news", news);
        mv.setViewName("admin/headLineNews/addPage");
        return mv;
    }

    /*列表页*/
    @RequestMapping("listPage")
    public ModelAndView listPage(ModelAndView mv,
                                 @ModelAttribute(value = "page") Pagination page,
                                 @ModelAttribute(value = "headlineNews") HeadlineNews news) {
        PageHelper.startPage(page.getPage(), page.getRows());
        List<HeadlineNews> list = headlineNewsService.queryNewsByCondition(news, page);
        mv.addObject("news", news);
        mv.addObject("page", page);
        mv.addObject("list", list);
        mv.setViewName("admin/headlineNews/listPage");
        return mv;
    }

    @RequestMapping(value = "addOrEdit", method = RequestMethod.POST)
    public JsonResult addOrEdit(@ModelAttribute(value = "headlineNews") HeadlineNews news) {
        JsonResult jsonResult;
        try {
            int result = headlineNewsService.addOrUpdateNews(news);
            if (result > 0) {
                jsonResult = JsonResult.SUCCESS("编辑成功");
            } else {
                jsonResult = JsonResult.FAILED("编辑失败");
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
    public JsonResult setTop(HeadlineNews news) {

        JsonResult jsonResult;
        try {
            if (StringUtils.isBlank(news.getNewsId()) || null == news.getNewsSort()) {
                jsonResult = JsonResult.FAILED("参数缺失");
            } else {
                int result = headlineNewsService.setNewsSort(news);
                if (result > 0) {
                    jsonResult = JsonResult.SUCCESS();
                } else {
                    jsonResult = JsonResult.FAILED();
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
    public JsonResult newsUpdate(HeadlineNews news) {
        JsonResult jsonResult;
        try {
            if (StringUtils.isBlank(news.getNewsId())) {
                jsonResult = JsonResult.FAILED("id缺失");
            } else {
                int result = headlineNewsService.editNewsBySelective(news);
                if (result > 0) {
                    jsonResult = JsonResult.SUCCESS("更新成功");
                } else {
                    jsonResult = JsonResult.FAILED("更新失败");
                }
            }
        } catch (Exception e) {
            jsonResult = JsonResult.FAILED("更新接口异常");
            e.printStackTrace();
        }
        return jsonResult;
    }
}
