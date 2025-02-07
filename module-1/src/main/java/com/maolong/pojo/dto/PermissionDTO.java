package com.maolong.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 权限查询参数DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDTO implements Serializable {
    private Integer page;
    private Integer limit;
    private Integer permissionId;
    private String permissionName;
    private String permission;
    private String roleName;
}
