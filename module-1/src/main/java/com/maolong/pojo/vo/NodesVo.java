package com.maolong.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class NodesVo implements Serializable {
    private Integer id;
    private String name;
}
