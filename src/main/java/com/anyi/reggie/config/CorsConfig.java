package com.anyi.reggie.config;

import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




import java.util.Collections;

// @Configuration
public class CorsConfig  {
    @Bean
    public CorsFilter addCorsMappings() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
     config.setAllowedOrigins(Collections.singletonList("http://localhost:5173")); // 允许所有来源
        config.setAllowedMethods(Collections.singletonList("*")); // 允许所有 HTTP 方法
        config.setAllowedHeaders(Collections.singletonList("*")); // 允许所有请求头
        config.setAllowCredentials(true); // 允许携带 Cookie
        config.setMaxAge(3600L); // 预检请求缓存 1 小时
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
