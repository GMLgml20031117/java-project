package com.maolong.exception;


import lombok.Data;


/**
 * 自定义异常类，基础异常类
 * */
@Data
public class BaseException extends RuntimeException{
    public BaseException() {
    }

    public BaseException(String msg) {
        super(msg);
    }
}
