package com.maolong.interceptor;

import com.maolong.common.properties.JwtProperties;
import com.maolong.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {
    @Autowired
    JwtProperties jwtProperties;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         //判断当前拦截到的是Controller的方法还是其他资源
         if (!(handler instanceof HandlerMethod)) {
             log.info("当前拦截到的不是动态方法，直接放行");
             //当前拦截到的不是动态方法，直接放行
             return true;
         }

         //检测是否有请求头令牌
         String token = request.getHeader(jwtProperties.getAdminTokenName());
         if(token==null||token.isEmpty()){
             log.info("请求头没有token");
             return false;
         }else {
             log.info("请求令牌token:{}",token);
             Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(),token);
             String username = claims.get("username").toString();
             log.info("用户名:{}",username);
             return true;
         }


    }
}
