package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.service.FileLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RequestMapping("upload")
@Controller
public class UploadController {
    /*
     * 图片命名格式
     */
    private static final String DEFAULT_SUB_FOLDER_FORMAT_AUTO = "yyyyMMddHHmmss";

    /*
     * 上传图片文件夹
     */
    private static final String UPLOAD_PATH = "/upload/CKimg/";

    @Autowired
    private FileLogService fileLogService;

    /*
     * 上传图片
     */
    @RequestMapping(value = "uploadImg")
    @ResponseBody
    public String uplodaHeadlineImg(@RequestParam("upload") MultipartFile file, @RequestParam("token") String token, HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> resultMap = fileLogService.saveFile(token, file);
            return resultMap.get("fileUrl").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void printResponse(HttpServletResponse response, String responseStr) {
        try {
            response.getWriter().println(responseStr);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
        }
    }

}
