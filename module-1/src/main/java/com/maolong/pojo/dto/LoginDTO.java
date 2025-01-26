package com.maolong.pojo.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String username;
    private String password;
    private String code;
}
