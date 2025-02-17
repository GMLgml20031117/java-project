package com.maolong.common.consitant;


/**
 * 常量类，用来标注返回结果的状态码
 * 1代表成功
 * 0代表失败
 */
public class ResultConstant {
    //返回前端的成功与否结果
    public static final boolean RESULT_SUCCESS=true;
    public static final boolean RESULT_ERROR=false;


    //用户的用户名与用户id
    public static final String USER_NAME="userName";
    public static final String USER_ID="user_id";
    public static final String ID="id";
    public static final String USER_PASSWORD="123456";
    public static final String IS_LOCK="is_lock";

    //redis存储token的key
    public static final String REDIS_TOKEN_KEY="token";


}
