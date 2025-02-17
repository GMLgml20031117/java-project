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
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Configuration
@EnableSwagger2
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JwtTokenInterceptor jwtTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        List<String> excludePath=new ArrayList<>();
        excludePath.add("/doc.html");            // Knife4j 页面
        excludePath.add("/webjars/**");          // 静态资源
        excludePath.add("/swagger-resources/**");// Swagger 配置
        excludePath.add("/v2/api-docs");         // API 文档 JSON
        excludePath.add("/v3/api-docs");
        excludePath.add("/swagger-ui/**");       // Swagger UI 资源
        excludePath.add("/User/login");
        registry.addInterceptor(jwtTokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
    }


//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**") // 为 /api/* 路径下的请求配置 CORS
//                .allowedOrigins("http://localhost:9999") // 允许来自 localhost:9999 的请求
//                .allowedMethods("POST", "GET", "OPTIONS") // 允许的 HTTP 方法
//                .allowCredentials(true); // 允许携带 cookie
//    }


    /**
     * 跨域问题，java端解决
     * @return
     */
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

    /**
     * 通过knife4j生成接口文档
     * @return
     */
    @Bean
    public Docket docket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("高茂龙项目接口文档")
                .version("2.0")
                .description("高茂龙项目接口文档")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.maolong.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    /**
     * 设置静态资源映射
     * @param registry
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}