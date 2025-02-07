package com.maolong.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer permissionId;
    private String permissionName;
    private String permission;
    private boolean layChecked;

    private String addUser;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime addTime;
    private String editUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime editTime;
}
