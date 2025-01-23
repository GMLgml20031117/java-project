package com.maolong.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResult {
    private Object data;
    private String msg;
    private boolean success;
    private String token;

    public static LoginResult success(Object data,String token){
        return new LoginResult(data,null,true,token);
    }
    public static LoginResult error(String msg){
        return new LoginResult(null,msg,false,null);
    }
}
