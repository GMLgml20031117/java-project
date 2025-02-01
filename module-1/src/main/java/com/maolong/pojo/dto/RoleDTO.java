package com.maolong.pojo.dto;

import lombok.Data;

@Data
public class RoleDTO {
        private String roleNo;
        private String roleName;
        private Integer page;
        private Integer limit;
}
