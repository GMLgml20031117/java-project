package com.maolong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maolong.common.result.PageResult;
import com.maolong.pojo.dto.DeptDTO;
import com.maolong.pojo.dto.RoleDTO;
import com.maolong.pojo.entity.Dept;
import com.maolong.pojo.entity.Role;

public interface IRoleService extends IService<Role> {
    PageResult<Role> getRolesByConditions(RoleDTO roleDTO);
    boolean saveOrUpdate(Role role);

    void deleteByIds(String ids);
}
