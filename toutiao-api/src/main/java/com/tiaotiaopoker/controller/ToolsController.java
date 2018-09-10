package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.service.FileLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by xiekang on 2018/9/8.
 */
@RestController()
@RequestMapping("tools")
@Scope("prototype")
public class ToolsController {
    @Autowired
    private FileLogService fileLogService;

    @RequestMapping(value = "upload",
                    method = RequestMethod.POST)
    public JsonResult uploadFile(String userId,
                                 MultipartFile file) {
        try {
            Map<String, Object> resultMap = fileLogService.saveFile( userId, file );
            return JsonResult.SUCCESS( "success", resultMap );
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.FAILED( "文件上传失败！" );
        }
    }
}
