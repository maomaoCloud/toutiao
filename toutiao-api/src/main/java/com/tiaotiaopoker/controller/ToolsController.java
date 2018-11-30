package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.ImagePlaceHolder;
import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.service.FileLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by xiekang on 2018/9/8.
 */
@RestController ()
@RequestMapping ("tools")
@Scope ("prototype")
public class ToolsController {
    @Autowired
    private FileLogService fileLogService;

    @RequestMapping (value = "upload",
            method = RequestMethod.POST)
    public JsonResult uploadFile (String userId,
                                  MultipartFile file) {
        try {
            Map<String, Object> resultMap = fileLogService.saveFile(userId, file);
            return JsonResult.SUCCESS("success", resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.FAILED("文件上传失败！");
        }
    }

    @RequestMapping ("placeholder/{width}x{height}/bgRGB_{bgRGB}/textRGB_{textRGB}/fontSize_{fontSize}/{text}")
    public void imagePlaceHolder (@PathVariable ("width") Integer width,
                                  @PathVariable ("height") Integer height,
                                  @PathVariable ("bgRGB") String bgRGB,
                                  @PathVariable ("textRGB") String textRGB,
                                  @PathVariable ("fontSize") Integer fontSize,
                                  @PathVariable ("text") String text,
                                  HttpServletResponse response) {
        try {
            ImagePlaceHolder.TextToPic(text, width, height, fontSize, bgRGB, textRGB, response.getOutputStream());
        } catch (Exception e) {
        }
    }

}
