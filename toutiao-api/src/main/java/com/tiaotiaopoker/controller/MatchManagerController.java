package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.entity.*;
import com.tiaotiaopoker.pojo.*;
import com.tiaotiaopoker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("match/manager/")
@Scope("prototype")
public class MatchManagerController {
    @Autowired
    private MatchRuleService matchRuleService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private MatchTeamDataService matchTeamDataService;
    @Autowired
    private MatchTeamResultService matchTeamResultService;
    @Autowired
    private SysHelpService sysHelpService;
    @Autowired
    private ApplyOrderService applyOrderService;

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
    @RequestMapping("info/{matchId}")
    public JsonResult matchInfo(@PathVariable("matchId") String matchId) {

        try {
            MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId(matchId);
            Map<String, Object> resultMap = new HashMap<>();
            if (null == matchRule) {
                resultMap.put("hasSettingRule", false);
                return JsonResult.SUCCESS("success", resultMap);
            } else {
                //已设置比赛信息
                resultMap.put("hasSettingRule", true);
                //ruleDraw=1(不可平)
                resultMap.put("canDraw", matchRule.getRuleDraw().equals(1));
                resultMap.put("totalTurn", matchRule.getRuleTurn());
                //当前轮次
                int nowTurn = matchTeamDataService.getNowTurn(matchId);
                resultMap.put("currentTurn", nowTurn);
                if (nowTurn != 0) {
                    resultMap.put("currentTurnName", Constants.NUM_CH[nowTurn - 1]);
                }
                //当前轮次是否有成绩信息
                MatchTeamResult matchTeamResult = new MatchTeamResult();
                matchTeamResult.setTurnNumber(nowTurn);
                matchTeamResult.setMatchId(matchId);
                List<MatchTeamResult> matchTeamResults = matchTeamResultService.queryMatchTeamResultByCondition(matchTeamResult);
                if (matchTeamResults != null && matchTeamResults.size() > 0) {
                    resultMap.put("currentTurnHasInputScore", true);
                } else {
                    resultMap.put("currentTurnHasInputScore", false);
                }
                return JsonResult.SUCCESS("success", resultMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.FAILED("操作失败！");
        }
    }

    /**
     * 1.帮助中心接口 -> 帮助中心列表
     * resData:[{helpId:"123",helpTitle:"title",helpColor:"#EFEFEF"},{helpId:"456",helpTitle:"title",helpColor:"#EFEFEF"}]
     */
    @RequestMapping("help/list")
    public JsonResult helpList() {
        List<ApiHelpData> resultList = new ArrayList<>();
        try {
            List<SysHelp> helpList = sysHelpService.queryHelpByCondition(null, null);
            for (SysHelp help : helpList) {
                resultList.add(ApiHelpData.genFromHelp(help));
            }
            return JsonResult.SUCCESS("success", resultList);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.FAILED("操作失败！");
        }
    }

    /**
     * 2.帮助中心接口 -> 帮助详情
     * 时间格式: yyyy-MM-dd
     * resData:{helpId:"123", helpTitle:"",helpContent:"", helpCreateTime:"yyyy-MM-dd"}
     */
    @RequestMapping("help/detail/{id}")
    public JsonResult helpDetail(@PathVariable("id") String id) {
        try {
            SysHelp sysHelp = sysHelpService.queryHelpById(id);
            return JsonResult.SUCCESS("success", ApiHelpData.genDetailDataFromHelp(sysHelp));
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.FAILED("操作失败！");
        }
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
     * <p>
     * resData{
     * nameList[积分和，去首轮积分和]，
     * apiResultList[scoreDataItem,scoreDataItem]
     * }
     * scoreDataItem:{
     * index:1,   //名次
     * userAHead:"",
     * userAName:"",
     * userBHead:"",
     * userBName:"",
     * resultString:""   //参考成绩（逗号分隔字符串，值与nameList里的属性名称一一对应）
     * }
     */
    @RequestMapping("score/show/{matchId}/{turn}")
    public JsonResult showScore(@PathVariable("matchId") String matchId, @PathVariable("turn") Integer turn) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            //比赛设置参考成绩属性名称集合
            MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId(matchId);
            List<String> nameList = new ArrayList<>();
            String resultRule = matchRule.getRuleResult() == null ? Constants.result.DEFAULT_RESULT_RULE : matchRule.getRuleResult();
            for (String title : resultRule.split(",")) {
                nameList.add((String) Constants.resultRule.resultRuleMap.get(title));
            }
            resultMap.put("nameList", nameList);

            //成绩数据集合
            List<ApiMatchTeamResult> apiResultList = new ArrayList<>();
            MatchTeamResult result = new MatchTeamResult();
            result.setMatchId(matchId);
            result.setTurnNumber(turn);
            List<MatchTeamResultDto> resultDtolist = matchTeamResultService.sortMatchTeamResult(result);
            //根据规则显示相应的成绩
            for (MatchTeamResultDto resultDto : resultDtolist) {
                ApiMatchTeamResult apiResult = ApiMatchTeamResult.genFromMatchTeamResultDto(resultDto);
                String resultString = "";
                Class clazz = resultDto.getClass();
                for (String getName : resultRule.split(",")) {
                    resultString += clazz.getMethod("get" + getName).invoke(resultDto) + ",";
                }
                apiResult.setResultString(resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : "");
                apiResult.setIndex(resultDtolist.indexOf(resultDto) + 1);
                apiResultList.add(apiResult);
            }
            resultMap.put("apiResultList", apiResultList);
            return JsonResult.SUCCESS("success", resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.FAILED("操作失败！");
        }
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
    @RequestMapping("sign/detail/{matchId}")
    public JsonResult signDetail(@PathVariable("matchId") String matchId) {
        try {
            List<ApiSignDetail> signDetailList = new ArrayList<>();
            List<ApplyOrder> orderList = applyOrderService.getSignData(matchId);
            for (ApplyOrder order : orderList) {
                signDetailList.add(ApiSignDetail.genFromApplyOrder(order));
            }
            return JsonResult.SUCCESS("success", signDetailList);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.FAILED("操作失败！");
        }
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
    @RequestMapping("seat/detail/{matchId}/{turn}")
    public JsonResult seatDetail(@PathVariable("matchId") String matchId, @PathVariable("turn") Integer turn) {
        try {
            List<ApiMatchTeamData> apiMatchTeamDataList = new ArrayList<>();
            MatchTeamData data = new MatchTeamData();
            data.setMatchId(matchId);
            data.setTurnNumber(turn);
            List<MatchTeamDataDto> matchTeamDataDtoList = matchTeamDataService.queryTeamDataByCondition(data);
            for (MatchTeamDataDto dataDto : matchTeamDataDtoList) {
                apiMatchTeamDataList.add(ApiMatchTeamData.genFromMatchTeamDataDto(dataDto));
            }
            return JsonResult.SUCCESS("success", apiMatchTeamDataList);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.FAILED("操作失败！");

        }
    }

    /**
     * 5.获取比赛的设置信息
     */
    @RequestMapping("setting/{matchId}")
    public JsonResult getMatchSettingInfo(@PathVariable("matchId") String matchId) {
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
     * 6.获取保存比赛规则信息
     */
    @RequestMapping("setting/save")
    public JsonResult saveMatchRule(@RequestBody MatchRule matchRule) {
        try {
            matchRuleService.saveBySelective(matchRule);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.FAILED("操作失败！");
        }
        return JsonResult.SUCCESS();
    }

    /**
     * 保存座位信息
     * SeatSaveData:
     * matchId: 比赛Id
     * turn:要保存座位的轮次
     * userIds[]:用户id数组，座位按这个顺序编排 每两个为一组
     * names[]:用户姓名数组
     * heads[]:用户头像数组
     */
    @RequestMapping("seat/save")
    public JsonResult saveSeat(@RequestBody SeatSaveData data) {
        try {
            matchTeamDataService.saveMatchTeamDataAndMember(data.getUserIds(), data.getNames(), data.getHeads(), data.getMatchId(), data.getTurn());
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.FAILED("操作失败！");
        }
        return JsonResult.SUCCESS();
    }

    /**
     * 更新座位信息
     * SeatUpdateData:
     * teamIds[]:队伍id集合
     * matchId:比赛id
     * turn:轮次
     */
    @RequestMapping("seat/update")
    public JsonResult updateSeat(@RequestBody SeatUpdateData data) {
        try {
            matchTeamDataService.updateMatchTeamData(data.getTeamIds(), data.getMatchId(), data.getTurn());
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.FAILED("操作失败！");
        }
        return JsonResult.SUCCESS();
    }

    /**
     * 获取首轮编排座位信息
     * resData:[dataItem,dataItem]
     * dataItem:
     * userId:用户Id
     * name:名字
     * head:头像
     * <p>
     * 根据设置的首轮排序规则，生成座位
     * 每个dataItem代表一个人，每两个默认为一组。如：
     * resData[1,2,3,4,5,6,7,8]
     * 1,2为第一组 3,4为第二组  1，2，3，4为第一桌
     */
    @RequestMapping("seat/firstTurn/{matchId}")
    public JsonResult getFirstTurnSeat(@PathVariable("matchId") String matchId) {
        try {
            List<ApiSeatUser> resData = new ArrayList<>();
            //首轮座位规则
            MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId(matchId);
            List<AppUser> userList = matchTeamDataService.sortMatchTeamByRuleSeat(matchRule.getRuleSeat(), matchId);
            //签到人员不是4的倍数时，补全用户
            while (userList.size() % 4 != 0) {
                ApiSeatUser user = new ApiSeatUser();
                user.setUserId(String.valueOf(userList.size()));
                user.setName("我是null");
                user.setHead(Constants.user.HEAD_IMG_URL);
                resData.add(user);
            }
            return JsonResult.SUCCESS("success", resData);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.FAILED("操作失败！");
        }
    }

    /**
     * 成绩保存，你定义数据格式，我来跟你
     * matchId: 比赛Id
     * turn:要保存座位的轮次
     * teamDataIds:当前轮次四人组的id集合
     * teamOneIds[]:队伍一（即东西方）id集合
     * teamOneScores[]:队伍一成绩集合
     * teamTwoIds[]:队伍二（即南北方）id集合
     * teamTwoScores[]:队伍二成绩
     */
    @RequestMapping("score/save")
    public JsonResult saveScore(@RequestBody ApiTeamData data) {
        try {
            MatchTeamData matchTeamData = new MatchTeamData();
            matchTeamData.setMatchId(data.getMatchId());
            matchTeamData.setTurnNumber(data.getTurn());
            matchTeamData.setId(data.getTeamDataIds().toString());
            matchTeamData.setTeamOneId(data.getTeamOneIds().toString());
            matchTeamData.setTeamOneScore(data.getTeamOneScores().toString());
            matchTeamData.setTeamTwoId(data.getTeamTwoIds().toString());
            matchTeamData.setTeamTwoScore(data.getTeamTwoScores().toString());
            matchTeamDataService.saveMatchTeamData(matchTeamData);

            //生成下一轮座位
            MatchRule matchRule = matchRuleService.selectMatchRuleByMatchId(data.getMatchId());
            if (matchRule.getRuleTurn() == data.getTurn()) {
                return JsonResult.SUCCESS();
            } else {
                matchTeamDataService.saveMatchTeamDataAndMemberNext(data.getMatchId(), data.getTurn() + 1);
                return JsonResult.SUCCESS();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.FAILED("操作失败！");
        }
    }

}
