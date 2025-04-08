package com.acupoint.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author WangZhiWei
 * @version 21.0.2
 * 2024/12/21 下午4:49
 */
@Configuration
public class MyCorsConfig {
    @Bean
    public CorsFilter corsFilter(){
        //1.创建CORS配置对象
        UrlBasedCorsConfigurationSource Source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        //支持域
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        //请求方式
        config.addAllowedMethod("*");
        //是否发送Cookie
        config.setAllowCredentials(true);
        //添加地址映射
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(corsConfigurationSource);
    }
}