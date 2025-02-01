package com.maolong.pojo.dto;

import lombok.Data;

@Data
public class DeptDTO {
    //部门代码
    private String deptNo;
    //部门名称
    private String deptName;
    private Integer page;
    private Integer limit;
}
