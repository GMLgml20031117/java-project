package com.maolong.aspect;

import com.maolong.mapper.LogMapper;
import com.maolong.pojo.entity.DBLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
public class DBLogAspect {

    @Autowired
    LogMapper dbLogMapper;


    @Pointcut("execution(* com.maolong.mapper..*.*(..)) && !execution(* com.maolong.mapper.LogMapper.*(..))")
    public void dbLogPointCut() {
    }

    @Around("dbLogPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalDateTime now= LocalDateTime.now();
        String method=joinPoint.getSignature().getName();
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        log.info("方法{}执行时间{}ms",method,end-start);

        DBLog dbLog=new DBLog();
        dbLog.setMethod(method);
        dbLog.setVisitTime(now);
        dbLog.setTimeCost(end-start);

        dbLogMapper.insertDB(dbLog);

        return result;
    }
}
