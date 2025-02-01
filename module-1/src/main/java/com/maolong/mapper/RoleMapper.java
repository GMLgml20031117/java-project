package com.maolong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.maolong.pojo.dto.RoleDTO;
import com.maolong.pojo.entity.Role;

public interface RoleMapper extends BaseMapper<Role> {

    Page<Role> selectRolesByConditions(RoleDTO roleDTO);
}
