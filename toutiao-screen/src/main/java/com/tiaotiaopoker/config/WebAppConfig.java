package com.tiaotiaopoker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by xiekang on 2018/9/29.
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private LoginIntercepter loginIntercepter;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController( "/" ).setViewName( "forward:/public/login" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );

        super.addViewControllers( registry );
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( loginIntercepter ).addPathPatterns( "/sys/**" );
    }
}
