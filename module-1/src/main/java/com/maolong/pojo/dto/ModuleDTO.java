package com.maolong.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDTO implements Serializable {
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
}