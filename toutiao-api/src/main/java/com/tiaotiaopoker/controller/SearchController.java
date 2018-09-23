package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by xiekang on 2018/9/23.
 */
@RequestMapping("search")
@RestController
@Scope("prototype")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping("/match/{userId}/{kw}")
    public JsonResult matchSearch(@PathVariable("userId") String userId,
                                  @PathVariable("kw") String kw) {
        try {
            Map<String, Object> resultMap = searchService.searchForMatch( userId, kw );
            return JsonResult.SUCCESS( "success", resultMap );
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.FAILED( "接口异常！" );
        }
    }

    @RequestMapping("/match/hotSearch")
    public JsonResult getMatchHotSearch() {

        return  JsonResult.FAILED();
    }

}
