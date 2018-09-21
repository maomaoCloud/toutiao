package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.controller.ext.SpecialDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by xiekang on 2018/9/9.
 */
public class BaseController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor( Date.class, new SpecialDateEditor() );
    }

    public HttpServletRequest getRequest() {
        HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes() ).getRequest();
        return request;
    }

    public String getIpAddress() {
        HttpServletRequest request = getRequest();
        String ip = request.getHeader( "x-forwarded-for" );
        if( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) ) {
            ip = request.getHeader( "Proxy-Client-IP" );
        }
        if( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) ) {
            ip = request.getHeader( "WL-Proxy-Client-IP" );
        }
        if( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) ) {
            ip = request.getHeader( "HTTP_CLIENT_IP" );
        }
        if( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) ) {
            ip = request.getHeader( "HTTP_X_FORWARDED_FOR" );
        }
        if( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) ) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
