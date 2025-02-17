package com.maolong.interceptor;

import com.maolong.common.consitant.ResultConstant;
import com.maolong.common.properties.JwtProperties;
import com.maolong.common.util.BaseContext;
import com.maolong.common.util.JsonObjUtil;
import com.maolong.common.util.JwtUtil;
import com.maolong.pojo.entity.User;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate redisTemplate;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         //判断当前拦截到的是Controller的方法还是其他资源
         if (!(handler instanceof HandlerMethod)) {
             log.info("当前拦截到的不是动态方法，直接放行");
             //当前拦截到的不是动态方法，直接放行
             return true;
         }

         //判断redis中是否有token
        User user = (User) redisTemplate.opsForValue().get(ResultConstant.REDIS_TOKEN_KEY);
         if(user!=null){
             if(user!=null){
                 log.info("redis中有token,用户信息:{}",user);
                 MDC.put(ResultConstant.USER_NAME,user.getUserName());
                 MDC.put(ResultConstant.USER_ID,user.getId().toString());
             }
             return true;
         }

        //检测是否有请求头令牌
         String token = request.getHeader(jwtProperties.getAdminTokenName());

         if(token==null||token.isEmpty()){
             token=request.getParameter(jwtProperties.getAdminTokenName());
             if(token==null||token.isEmpty()){
                 log.info("请求头没有token");
                 response.setStatus(401);
                 return false;
             }else
                 return util(token);
         }else {
             return util(token);
         }
    }

    public boolean util(String token){
        log.info("请求令牌token:{}",token);
        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(),token);
        String username = claims.get(ResultConstant.USER_NAME).toString();
        Integer userId=Integer.valueOf(claims.get(ResultConstant.USER_ID).toString());
        log.info("用户名:{},用户id:{}",username,userId);
        //将用户名放入ThreadLocal中,TODO,使用ThreadLocal存储用户信息,需要编写BaseContext类
        BaseContext.setCurrentUserId(userId);
        //将用户名放入MDC中
        MDC.put(ResultConstant.USER_NAME,username);
        MDC.put(ResultConstant.USER_ID,userId.toString());
        return true;
    }
}
