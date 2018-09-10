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
}
