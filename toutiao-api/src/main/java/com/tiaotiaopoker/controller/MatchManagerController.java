package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.pojo.MatchRule;
import com.tiaotiaopoker.pojo.MatchWithBLOBs;
import com.tiaotiaopoker.service.MatchRuleService;
import com.tiaotiaopoker.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController ()
@RequestMapping ("match/manager/")
@Scope ("prototype")
public class MatchManagerController {
    @Autowired
    private MatchRuleService matchRuleService;
    @Autowired
    private MatchService     matchService;

    /**
     * 0.根据比赛Id 获取比赛轮次信息
     * resData:{
     * hasSettingRule:true,     //是否设置比赛信息，  如果没有设置的话，别的接口我是不让操作的
     * canDraw:true             //是否可平
     * totalTurn:4,             //总轮次数
     * currentTurn:1,           //当前轮次， 取：座位编排信息中最大的轮次数
     * currentTurnName:"一",    //轮次名称
     * currentTurnHasInputScore:true    //当前轮次是否已录入成绩
     * }
     */
    @RequestMapping ("info/{matchId}")
    public JsonResult matchInfo (@PathVariable ("matchId") String matchId) {
        return JsonResult.SUCCESS();
    }

    /**
     * 1.帮助中心接口 -> 帮助中心列表
     * resData:[{helpId:"123",helpTitle:"title",helpColor:"#EFEFEF"},{helpId:"456",helpTitle:"title",helpColor:"#EFEFEF"}]
     */
    @RequestMapping ("help/list")
    public JsonResult helpList () {

        return JsonResult.SUCCESS();
    }

    /**
     * 2.帮助中心接口 -> 帮助详情
     * 时间格式: yyyy-MM-dd
     * resData:{helpId:"123", helpTitle:"",helpContent:"", helpCreateTime:"yyyy-MM-dd"}
     */
    @RequestMapping ("help/detail/{id}")
    public JsonResult helpDetail (@PathVariable ("id") String id) {
        return JsonResult.SUCCESS();
    }

    /**
     * 3.根据轮次查看成绩
     * 根据matchId，和turn查看当前轮次的成绩
     * resData:[scoreDataItem,scoreDataItem]
     * scoreDataItem的属性名可以根据你以前写的来改
     * scoreDataItem:{
     * index:1,   //名次
     * userAHead:"",
     * userAName:"",
     * userBHead:"",
     * userBName:"",
     * 这个比赛设置的一些参考成绩项目
     * }
     */
    @RequestMapping ("score/show/{matchId}/{turn}")
    public JsonResult showScore (@PathVariable ("matchId") String matchId, @PathVariable ("turn") Integer turn) {
        return JsonResult.SUCCESS();
    }

    /**
     * 4.签到情况
     * 获取当前比赛的签到情况
     * resData:[resDataItem,resDataItem]
     * resDataItem:{
     * userAId:"",
     * userAHead:"",
     * userAName:"",
     * userAHasSign:true, //true 表示userA已经签到，  false表示还未签到
     * hasPartner:true, //true 表示有搭档   false 表示没有搭档,则下列值可为空
     * userBId:"",
     * userBName:"",
     * userBHead:"",
     * userBHasHasSign:false
     * }
     */
    @RequestMapping ("sign/detail/{matchId}")
    public JsonResult signDetail (@PathVariable ("matchId") String matchId) {
        return JsonResult.SUCCESS();
    }

    /**
     * 5.根据轮次拿到座位信息
     * 根据轮次拿到当前座位信息
     * resData:[resDataItem,resDataItem]
     * resDataItem:{
     * zh:1             //桌号,按顺序递增
     * groupANumber:1,  //第一组编号
     * groupAUserAHead:"",
     * groupAUserAName:"",
     * groupAUserBName:"",
     * groupAUserBHead:"",
     * groupBNumber:2,  //第二组编号
     * groupBUserAHead:"",
     * groupBUserAName:"",
     * groupBUserBName:"",
     * groupBUserBHead:"",
     * }
     */
    @RequestMapping ("seat/detail/{matchId}/{turn}")
    public JsonResult seatDetail (@PathVariable ("matchId") String matchId, @PathVariable ("turn") Integer turn) {
        return JsonResult.SUCCESS();
    }

    /**
     * 5.获取比赛的设置信息
     */
    @RequestMapping ("setting/{matchId}")
    public JsonResult getMatchSettingInfo (@PathVariable ("matchId") String matchId) {
        MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId(matchId);
        if (matchRule == null) {
            MatchWithBLOBs matchData = matchService.getMatchDataById(matchId);
            if (matchData == null) {
                return JsonResult.FAILED("活动不存在");
            }
            matchRuleService.createMatchRuleByMatch(matchData);
            matchRule = matchRuleService.selectMatchRuleByMatchId(matchId);
        }
        return JsonResult.SUCCESS("success", matchRule);
    }

    /**
     * 5.获取保存比赛规则信息
     */
    @RequestMapping ("setting/save")
    public JsonResult saveMatchRule (@RequestBody MatchRule matchRule) {
        try {
            matchRuleService.saveBySelective(matchRule);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.FAILED("操作失败！");
        }
        return JsonResult.SUCCESS();
    }

}
