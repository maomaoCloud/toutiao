package com.tiaotiaopoker.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.pagehelper.PageHelper;

/**
 * 注册MyBatis分页插件PageHelper
 *
 * @author Angel(QQ:412887952)
 * @version v.0.1
 */
@Configuration
public class MyBatisConfiguration {
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty( "helperDialect", "mysql" );
        properties.setProperty( "reasonable", "true" );
        properties.setProperty( "supportMethodsArguments", "true" );
        properties.setProperty( "params", "count=countSql" );
        pageHelper.setProperties( properties );
        return pageHelper;
    }
}