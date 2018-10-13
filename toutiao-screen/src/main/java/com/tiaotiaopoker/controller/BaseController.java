package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.pojo.AppUser;
import com.tiaotiaopoker.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiekang on 2018/9/26.
 */
public class BaseController {
    @Autowired
    private ScreenService service;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor( Date.class, new PropertyEditorSupport() {

            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
                Date date = null;
                try {
                    //防止空数据出错
                    if( !StringUtils.isBlank( text ) ) {
                        date = format.parse( text );
                    }
                } catch (ParseException e) {
                    format = new SimpleDateFormat( "yyyy-MM-dd" );
                    try {
                        date = format.parse( text );
                    } catch (ParseException e1) {
                        format = new SimpleDateFormat( "yyyy-MM" );

                        try {
                            date = format.parse( text );
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                setValue( date );
            }

        } );
    }

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
