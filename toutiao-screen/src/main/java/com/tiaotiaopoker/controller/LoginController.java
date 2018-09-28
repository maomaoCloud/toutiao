package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.JsonResult;
import com.tiaotiaopoker.SecurityFactory;
import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xiekang on 2018/9/26.
 */
@Controller
@Scope("prototype")
@RequestMapping("public")
public class LoginController {
    @Autowired
    private ScreenService screenService;
    @Value("${scan.qrcode.url}")
    private String        qrcode_url_prefix;
    @Value("${scan.qrcode.key}")
    private String        qrcode_key;

    @RequestMapping(value = "login",
                    method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView mv = new ModelAndView();
        String id = StringUtils.genRandomKey();
        String key = SecurityFactory.MD5.encrypt( id + qrcode_key, null );
        String qrcode_url = String.format( qrcode_url_prefix, id, key );
        mv.addObject( "qrcode_url", qrcode_url );
        mv.addObject( "id", id );
        return mv;
    }

    @RequestMapping(value = "login/check",
                    method = RequestMethod.POST)
    public String loginCheck(String id,
                             Long t) {
        long timeDiff = ( System.currentTimeMillis() - t ) / 1000;
        if( timeDiff > 5 || t < 0 ) return ""; //时间超过5s 或者时间大于服务器时间，无效
        String res = screenService.checkLogin( id ); //如果登录成功:返回HAS_LOGIN:userId 然后每次请求都要携带token=userId
        return res;
    }
}
