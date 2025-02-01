package com.maolong.pojo.dto;

import com.alibaba.druid.sql.visitor.functions.Char;
import lombok.Data;

@Data
public class UserDTO {
    private String userId;
    private String roleId;
    private String userEmail;
    private String userRealName;
    private String userSex;
    private String userName;
    private String userMobile;
    private String isLock;
    private Integer page;
    private Integer limit;
    private String deptId;
}
