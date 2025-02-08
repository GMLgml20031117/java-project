package com.maolong.handler;
import com.maolong.common.result.Result;
import com.maolong.exception.BaseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 异常处理类
 */

@RestControllerAdvice
public class MyExceptionHandler {

    /**
     * 捕获异常，并且基础基础BaseException
     * @param e
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public Result handleException(BaseException e){
        return Result.error(e.getMessage());
    }
}
