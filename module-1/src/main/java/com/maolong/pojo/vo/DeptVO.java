package com.maolong.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 给UserDeptController使用，因为数据库的字段与前端需要的字段不相同
 */
@Data
@AllArgsConstructor
public class DeptVO {
    private String value;
    private String name;
}
