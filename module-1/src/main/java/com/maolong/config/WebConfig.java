package com.maolong.config;

import com.maolong.interceptor.JwtTokenInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;
@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JwtTokenInterceptor jwtTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(jwtTokenInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/login");
    }
//    protected void addInterceptors(InterceptorRegistry registry) {
//        log.info("开始注册自定义拦截器...");
//        registry.addInterceptor(jwtTokenAdminInterceptor)
//                .addPathPatterns("/admin/**")
//                .excludePathPatterns("/admin/employee/login");
//    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**") // 为 /api/* 路径下的请求配置 CORS
//                .allowedOrigins("http://localhost:9999") // 允许来自 localhost:9999 的请求
//                .allowedMethods("POST", "GET", "OPTIONS") // 允许的 HTTP 方法
//                .allowCredentials(true); // 允许携带 cookie
//    }

    @Bean
    public CorsFilter corsFilter() {
        // 创建一个 CorsConfiguration 对象，用于定义 CORS 配置
        CorsConfiguration config = new CorsConfiguration();
        // 设置允许的请求源，允许所有来源。`*` 表示任何域名都可以访问资源
        config.setAllowedOriginPatterns(Collections.singletonList("*"));
        // 设置允许的 HTTP 方法。`*` 表示允许所有 HTTP 方法（如 GET, POST, PUT, DELETE 等）
        config.addAllowedMethod("*");
        // 设置允许的请求头。`*` 表示允许所有请求头
        config.addAllowedHeader("*");
        // 设置是否允许带有凭据（如 cookies）的请求。true 表示允许
        config.setAllowCredentials(true);
        // 创建一个 UrlBasedCorsConfigurationSource，设置跨域配置源
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 注册 CORS 配置到指定的 URL 路径上，"/**" 表示对所有请求路径都启用这个 CORS 配置
        source.registerCorsConfiguration("/**", config);
        // 返回一个 CorsFilter 实例，Spring 会自动使用这个过滤器来处理跨域请求
        return new CorsFilter(source);
    }
}