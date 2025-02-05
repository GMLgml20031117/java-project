package com.maolong.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleTreeDataVO {
    private Integer id;
    private Integer pId;
    private String name;
    List<ModuleTreeDataVO> children;
    private boolean open;
    private boolean checked;
}
