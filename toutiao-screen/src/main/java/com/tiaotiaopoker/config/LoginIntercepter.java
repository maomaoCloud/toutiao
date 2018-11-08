package com.tiaotiaopoker.config;

import com.tiaotiaopoker.Constants;
import com.tiaotiaopoker.pojo.AppUser;
import com.tiaotiaopoker.service.ScreenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xiekang on 2018/9/29.
 */
@Component
public class LoginIntercepter implements HandlerInterceptor {
    @Autowired
    private ScreenService service;

    @Override
    public boolean preHandle (HttpServletRequest request,
                              HttpServletResponse response,
                              Object handler) throws Exception {
        //String token = request.getParameter("token");

        String   token   = null;
        Cookie[] cookies = request.getCookies();
        String   method  = request.getMethod();
        if (cookies == null) {
            deal(method, request, response);
            return false;
        }
        for (Cookie cookie : cookies) {
            if (StringUtils.equals(cookie.getName(), "__token")) {
                token = cookie.getValue();
                break;
            }
        }

        if (StringUtils.isBlank(token)) {
            deal(method, request, response);
            return false;
        }

        AppUser loginUser = service.getLoginUser(token);
        if (loginUser == null) {
            deal(method, request, response);
            return false;
        }

        return true;
    }

    private void deal (String method, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (method.toUpperCase().equals("POST")) {
            PrintWriter out = response.getWriter();
            out.print(
                    "{\"success\":false,\"code\":" + Constants.ApiCode.LOGIN_EXPIRE + ",\"msg\":\"登录过期\",\"resData\":null}");
            out.flush();
        } else {
            response.sendRedirect("/public/login?r=" + request.getRequestURL());
        }
    }

}
