package com.maolong.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolePermission implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id; //主键
    private Integer roleId; //角色id
    private Integer permissionId; //权限id
    private String roleName;
    private String permissionName;
}
