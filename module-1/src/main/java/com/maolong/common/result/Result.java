package com.maolong.common.result;

import com.maolong.common.consitant.ResultConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {
    private Integer code;
    private T data;
    private String msg;

    public static <T> Result<T> success(){
        return new Result<>(ResultConstant.RESULT_SUCCESS,null,null);
    }

    public static <T> Result<T> success(T object){
        return new Result<>(ResultConstant.RESULT_SUCCESS,object,null);
    }


    public static Result error(String msg){
        return new Result(ResultConstant.RESULT_ERROR,null,msg);
    }
}
