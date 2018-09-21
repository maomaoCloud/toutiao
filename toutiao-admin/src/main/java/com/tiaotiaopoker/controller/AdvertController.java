package com.tiaotiaopoker.controller;

import com.github.pagehelper.PageHelper;
import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.common.Pagination;
import com.tiaotiaopoker.entity.AdvertRecommendDto;
import com.tiaotiaopoker.pojo.AdvertRecommend;
import com.tiaotiaopoker.pojo.SysUser;
import com.tiaotiaopoker.service.AdvertRecommendService;
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
@RequestMapping("advert")
public class AdvertController {
    @Autowired
    private AdvertRecommendService advertRecommendService;
    @Autowired
    private HttpSession session;

    @RequestMapping("addAndEditPage")
    public ModelAndView addAndEditPage(ModelAndView mv, @ModelAttribute(value = "advert") AdvertRecommendDto advertDto) {
        if (!StringUtils.isBlank(advertDto.getAdvertId())) {
            advertDto = advertRecommendService.queryAdvertDtoById(advertDto.getAdvertId());
        }
        mv.addObject("advert", advertDto);
        mv.setViewName("admin/advert/advertAddPage");
        return mv;
    }

    @RequestMapping(value = "addOrEdit", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addOrEdit(@ModelAttribute(value = "advert") AdvertRecommend advert) {
        JsonResult jsonResult;
        SysUser loginUser = (SysUser) session.getAttribute("sysUser");
        try {
            if (null == loginUser) {
                jsonResult = JsonResult.FAILED("nologin");
            } else {
                int result = advertRecommendService.addOrUpdateAdvert(advert, loginUser);
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

    /*列表页*/
    @RequestMapping("listPage")
    public ModelAndView listPage(ModelAndView mv,
                                 @ModelAttribute(value = "page") Pagination page,
                                 @ModelAttribute(value = "advert") AdvertRecommend advert) {
        PageHelper.startPage(page.getPage(), page.getRows());
        List<AdvertRecommend> list = advertRecommendService.queryAdvertByCondition(advert, page);
        mv.addObject("news", advert);
        mv.addObject("page", page);
        mv.addObject("list", list);
        mv.setViewName("admin/advert/advertListPage");
        return mv;
    }

    /*更新接口*/
    @RequestMapping("update")
    @ResponseBody
    public JsonResult advertUpdate(AdvertRecommend advert) {
        JsonResult jsonResult;
        SysUser loginUser = (SysUser) session.getAttribute("sysUser");
        try {
            if (null == loginUser) {
                jsonResult = JsonResult.FAILED("nologin");
            } else {
                if (StringUtils.isBlank(advert.getAdvertId())) {
                    jsonResult = JsonResult.FAILED("id缺失");
                } else {
                    int result = advertRecommendService.editAdvertBySelective(advert, loginUser);
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
