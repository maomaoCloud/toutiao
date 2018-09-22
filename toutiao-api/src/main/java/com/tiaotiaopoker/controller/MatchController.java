package com.tiaotiaopoker.controller;

import com.github.pagehelper.PageHelper;
import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.entity.ApiApplyParams;
import com.tiaotiaopoker.pojo.MatchWithBLOBs;
import com.tiaotiaopoker.service.ApplyOrderService;
import com.tiaotiaopoker.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by xiekang on 2018/9/9.
 */
@RestController()
@RequestMapping("match")
@Scope("prototype")
public class MatchController extends BaseController {
    @Autowired
    private MatchService      matchService;
    @Autowired
    private ApplyOrderService applyOrderService;

    @RequestMapping(value = "add",
                    method = RequestMethod.POST)
    public JsonResult addMatch(@RequestBody MatchWithBLOBs data) {
        try {
            matchService.saveMatch( data );
            return JsonResult.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.FAILED( "添加失败！" );
        }
    }

    @RequestMapping(value = "{pageNum}/{pageSize}/{userId}",
                    method = RequestMethod.POST)
    public JsonResult getMatch(@PathVariable("pageNum") Integer pageNum,
                               @PathVariable("pageSize") Integer pageSize,
                               @PathVariable("userId") String userId) {
        JsonResult jsonResult;
        try {
            Map<String, Object> resultMap = matchService.getMatchList( pageNum, pageSize ,userId);
            jsonResult = JsonResult.SUCCESS();
            jsonResult.setResData( resultMap );
        } catch (Exception e) {
            jsonResult = JsonResult.FAILED( "赛事列表接口异常" );
            e.printStackTrace();
        }
        return jsonResult;
    }

    @RequestMapping("detail/{matchId}")
    public JsonResult getMatchDetail(@PathVariable("matchId") String matchId) {
        JsonResult jsonResult;
        try {
            Map<String, Object> resultMap = matchService.getMatchInfoById( matchId );
            jsonResult = JsonResult.SUCCESS();
            jsonResult.setResData( resultMap );
        } catch (Exception e) {
            jsonResult = JsonResult.FAILED( "赛事详情接口异常" );
            e.printStackTrace();
        }
        return jsonResult;
    }

    @RequestMapping("apply")
    public JsonResult apply(@RequestBody ApiApplyParams params) {
        try {
            params.setRequestIp( getIpAddress() );
            Map<String, Object> resultMap = applyOrderService.addOrder( params );
            return JsonResult.SUCCESS( "success", resultMap );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonResult.FAILED( "报名失败！" );
    }

}
