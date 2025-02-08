package com.maolong.aspect;

import com.maolong.common.consitant.ResultConstant;
import com.maolong.mapper.LogMapper;
import com.maolong.pojo.entity.WebLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class WebLogAspect {

    @Autowired
    LogMapper webLogMapper;

    @Pointcut("execution(* com.maolong.controller.*..*(..))")
    public void webLog() {}

    @Around("webLog()")
    public Object logWebRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录请求日志
        log.info("URL: {}", request.getRequestURL());
        log.info("HTTP Method: {}", request.getMethod());
        log.info("Class Method: {}.{}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
        log.info("IP: {}", request.getRemoteAddr());
        log.info("Args: {}", Arrays.toString(joinPoint.getArgs()));

        // 统计耗时
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed(); // 执行目标方法
        long endTime = System.currentTimeMillis();

        String URL= request.getRequestURL().toString();
        String httpMethod = request.getMethod();
        String ClassMethod= joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        String ip = request.getRemoteAddr();
        String args = Arrays.toString(joinPoint.getArgs());
        Long timeCost=endTime-startTime;
        String userName= MDC.get(ResultConstant.USER_NAME);
        LocalDateTime visitTime=LocalDateTime.now();

        WebLog webLog = new WebLog().builder()
                .url(URL)
                .httpMethod(httpMethod)
                .classMethod(ClassMethod)
                .ip(ip)
                .args(args)
                .timeCost(timeCost)
                .userName(userName)
                .visitTime(visitTime)
                .build();
        webLogMapper.insertWeb(webLog);
        log.info("Time Cost: {} ms", endTime - startTime);
        return result;
    }
}
