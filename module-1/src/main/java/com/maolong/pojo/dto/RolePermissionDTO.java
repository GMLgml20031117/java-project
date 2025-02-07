package com.maolong.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * 用来配置角色权限的
 *
 */
@Data
public class RolePermissionDTO {
    private Integer roleId;//角色名称

    //因为前端传过来的就是"1,2,3"是，所以只能用String类型
    private String permissionIds;//权限id
}
