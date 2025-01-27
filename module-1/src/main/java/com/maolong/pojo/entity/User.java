package com.maolong.pojo.entity;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author maolong
 * @since 2025-01-21
 */
@ApiModel(value = "User对象", description = "")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userName;

    private String userRealName;

    private String userMobile;

    private String userSex;

    private String deptName;

    private String userEmail;

    private String roleId;

    private String userPassword;

    private String addUser;


    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern="yy-mm-dd HH:mm:ss")
    private LocalDateTime addTime;

    private String editUser;


    @DateTimeFormat(pattern="yy-mm-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime editTime;

    private String isLock;

}
