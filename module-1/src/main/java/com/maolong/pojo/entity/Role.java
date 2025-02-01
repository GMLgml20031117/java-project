package com.maolong.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class Role {
    @TableId(value="role_id",type = IdType.AUTO)
    private Integer roleId;
    private String roleName;
    private String roleNo;
    private String systemNo;
    private String editUser;
    private String addUser;
    @DateTimeFormat(pattern="yy-mm-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime editTime;
    @DateTimeFormat(pattern="yy-mm-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime addTime;
}
