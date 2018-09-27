package com.tiaotiaopoker.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.WriterException;
import com.tiaotiaopoker.QrCodeCreateUtil;
import com.tiaotiaopoker.SecurityFactory;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.service.ScreenService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xiekang on 2018/9/26.
 */
@Controller
@RequestMapping("/wx/login")
@Scope("prototype")
public class LoginController {
    @Autowired
    private ScreenService screenService;
    @Value("${scan.qrcode.key}")
    private String        qrcode_key;

    @RequestMapping("qrcode/{id}")
    public void qrcodeLogin(HttpServletRequest request,
                            HttpServletResponse response,
                            @PathVariable("id") String id,
                            String key) throws IOException, WriterException {
        if( StringUtils.isBlank( id ) || StringUtils.isBlank( key ) ) {
            return;
        }

        String serverKey = SecurityFactory.MD5.encrypt( id + qrcode_key, null );
        if( !serverKey.toUpperCase().equals( key.toUpperCase() ) ) {
            return;
        }

        response.setContentType( "image/jpeg" );
        // 禁止图像缓存。
        response.setHeader( "Pragma", "no-cache" );
        response.setHeader( "Cache-Control", "no-cache" );
        response.setDateHeader( "Expires", 0 );
        screenService.genLoginCode( id );
        JSONObject jo = new JSONObject();
        jo.put( "action", "login" );
        jo.put( "id", id );
        //向页面输出验证码图片
        QrCodeCreateUtil.createQrCode( response.getOutputStream(), jo.toJSONString(), 600, "JPEG" );
    }

}
