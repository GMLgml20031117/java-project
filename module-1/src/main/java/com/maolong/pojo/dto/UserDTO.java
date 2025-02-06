package com.maolong.pojo.dto;

import com.alibaba.druid.sql.visitor.functions.Char;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    private Integer id;
    private String roleId;
    private String userEmail;
    private String userRealName;
    private String userSex;
    private String userName;
    private String userMobile;
    //公司序号
    private String deptName;
    private String isLock;
    private Integer page;
    private Integer limit;
}
