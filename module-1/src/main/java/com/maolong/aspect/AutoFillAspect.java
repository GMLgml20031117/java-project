package com.maolong.aspect;

import com.maolong.common.annotation.AutoFill;
import com.maolong.common.consitant.ResultConstant;
import com.maolong.common.enumClass.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;


@Component
@Aspect
@Slf4j
public class AutoFillAspect {

    @Pointcut("execution(* com.maolong.mapper.*.*(..)) && @annotation(com.maolong.common.annotation.AutoFill)")
    public void insertOrUpdate() {}

    @Before("insertOrUpdate()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("进行自动填充数据");

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill annotation = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType value = annotation.value();
        Object[] args = joinPoint.getArgs();

        if(args == null || args.length == 0){
            return;
        }

        Object entity = args[0];

        String name = MDC.get(ResultConstant.USER_NAME);
        LocalDateTime now=LocalDateTime.now();
        //根据当前不同的操作类型，为对应的属性通过反射来赋值
        if(value == OperationType.INSERT){
            //为4个公共字段赋值
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod("addTime", LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod("addUser", String.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod("editTime", LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod("editUser", String.class);

                //通过反射为对象属性赋值
                setCreateTime.invoke(entity,now);
                setCreateUser.invoke(entity,name);
                setUpdateTime.invoke(entity,now);
                setUpdateUser.invoke(entity,name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(value == OperationType.UPDATE){
            //为2个公共字段赋值
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod("editTime", LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod("editUser", String.class);

                //通过反射为对象属性赋值
                setUpdateTime.invoke(entity,now);
                setUpdateUser.invoke(entity,name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
