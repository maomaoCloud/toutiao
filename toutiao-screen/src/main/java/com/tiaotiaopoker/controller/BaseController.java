package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.pojo.AppUser;
import com.tiaotiaopoker.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiekang on 2018/9/26.
 */
public class BaseController {
    @Autowired
    private ScreenService service;

    public HttpServletRequest getRequest() {
        HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes() ).getRequest();
        return request;
    }

    public AppUser getLoginUser() {
        String token = getRequest().getParameter( "token" );
        if( StringUtils.isBlank( token ) ) return null;

        return service.getLoginUser( token );
    }
}
