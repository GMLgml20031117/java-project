package com.maolong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.maolong.common.consitant.ResultConstant;
import com.maolong.common.result.PageResult;
import com.maolong.mapper.RoleMapper;
import com.maolong.pojo.dto.RoleDTO;
import com.maolong.pojo.entity.Dept;
import com.maolong.pojo.entity.Role;
import com.maolong.service.IRoleService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    RoleMapper RoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageResult<Role> getRolesByConditions(RoleDTO roleDTO) {
        PageHelper.startPage(roleDTO.getPage(),roleDTO.getLimit());
        Page<Role> rolePage = RoleMapper.selectRolesByConditions(roleDTO);
        if(rolePage!=null){
            return new PageResult<>(rolePage.getResult(),rolePage.size());
        }else
            return new PageResult<>(Collections.emptyList(),0);
    }

    @Override
    public boolean saveOrUpdate(Role role) {
        role.setAddUser(MDC.get(ResultConstant.USER_NAME));
        role.setEditUser(MDC.get(ResultConstant.USER_NAME));
        Role roleRes = roleMapper.selectById(role.getRoleId());
        if(roleRes==null){
            return this.save(role);
        }else
            return this.updateById(role);
    }
}
