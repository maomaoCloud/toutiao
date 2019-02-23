package com.tiaotiaopoker.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.entity.ApiNwsDetail;
import com.tiaotiaopoker.pojo.HeadlineNews;
import com.tiaotiaopoker.service.HeadlineNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController()
@RequestMapping("news")
@Scope("prototype")
public class HeadlineNewsController {

    @Autowired
    private HeadlineNewsService headlineNewsService;

    /*
     * 头条新闻列表
     * Created by maojian
     * @date 2018/8/24 10:43
     */
    @RequestMapping(value = "{typeId}/{pageNum}/{pageSize}",
                    method = RequestMethod.GET)
    public JsonResult newsList(@PathVariable(name = "typeId") String typeId,
                               @PathVariable(name = "pageNum") Integer pageNum,
                               @PathVariable(name = "pageSize") Integer pageSize) {
        PageHelper.startPage( pageNum, pageSize );
        JsonResult jsonResult;
        Map<String, Object> resultMap;
        try {
            resultMap = headlineNewsService.queryNewsByPage( typeId, pageNum, pageSize );
            jsonResult = JsonResult.SUCCESS( "success", resultMap );
        } catch (Exception e) {
            jsonResult = JsonResult.FAILED( "头条新闻列表接口异常" );
            e.printStackTrace();
        }
        return jsonResult;
    }

    /*
     * 头条新闻（id）
     * Created by maojian
     * @date 2018/8/24 10:40
     */
    @RequestMapping(value = "detail/{newsId}",
                    method = RequestMethod.GET)
    public JsonResult newsById(@PathVariable(name = "newsId") String newsId) {
        JsonResult jsonResult;
        try {
            if( StringUtil.isEmpty( newsId ) ) {
                jsonResult = JsonResult.FAILED( "id缺失" );
            } else {
                HeadlineNews news = headlineNewsService.queryNewsById( newsId );
                ApiNwsDetail newsDetail =  ApiNwsDetail.genFromNews(news);
                jsonResult = JsonResult.SUCCESS( "success", newsDetail );
            }
        } catch (Exception e) {
            jsonResult = JsonResult.FAILED( "头条新闻(id)异常" );
            e.printStackTrace();
        }
        return jsonResult;
    }

}
