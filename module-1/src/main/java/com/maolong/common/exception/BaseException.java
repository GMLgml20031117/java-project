package com.maolong.common.exception;


import lombok.Data;


/**
 * 自定义异常类，基础异常类
 * */
@Data
public class BaseException extends RuntimeException{
    private Integer code;
    private String msg;
    public BaseException(int code,String msg){
        super(msg);
        this.code=code;
    }
}
