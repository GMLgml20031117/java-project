package com.maolong.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleTreeDataVO {
    private Integer id;
    private Integer pId;
    private String name;
    private boolean open;
    private boolean checked;
}
