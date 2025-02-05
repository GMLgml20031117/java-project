package com.maolong.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Module implements Serializable {

    @TableId(value = "module_id", type = IdType.AUTO)
    private Integer moduleId;
    private Integer parentId;
    private String moduleName;
    private String moduleIcon;
    private String moduleUrl;
    private String moduleNotes;
    private Integer moduleOrder;
    private String moduleLevel;
    private Integer systemNo;
    private String isLeaf;
    private String fullIndex;
    private boolean open;
    private boolean checked;


    private String addUser;
    @DateTimeFormat(pattern="yy-mm-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime addTime;
    private String editUser;
    @DateTimeFormat(pattern="yy-mm-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime editTime;
}
