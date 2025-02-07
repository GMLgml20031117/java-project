package com.maolong.service.impl;

import com.maolong.common.util.StringToListUtil;
import com.maolong.mapper.PermissionMapper;
import com.maolong.mapper.RoleMapper;
import com.maolong.mapper.RolePermissionMapper;
import com.maolong.pojo.dto.RolePermissionDTO;
import com.maolong.pojo.entity.Permission;
import com.maolong.pojo.entity.Role;
import com.maolong.pojo.entity.RolePermission;
import com.maolong.service.IRoleService;
import com.maolong.service.PermissionService;
import com.maolong.service.RolePermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    RolePermissionMapper rolePermissionMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PermissionMapper permissionMapper;

    @Transactional
    @Override
    public void save(RolePermissionDTO rolePermissionDTO) {

        Role role = roleMapper.selectById(rolePermissionDTO.getRoleId());

        //查出对应权限
        List<Permission> allPermission = permissionMapper.getAllPermission(StringToListUtil.stringToList(rolePermissionDTO.getPermissionIds()));

        System.out.println(allPermission);

        List<RolePermission> rolePermissions = new ArrayList<>();
        //构造一个List<permission>对象,批量插入

        allPermission.stream().forEach(per->{
            RolePermission rolePer = RolePermission.builder()
                    .roleId(role.getRoleId())
                    .roleName(role.getRoleName())
                    .permissionId(per.getPermissionId())
                    .permissionName(per.getPermissionName())
                    .build();
            rolePermissions.add(rolePer);
        });
        System.out.println(allPermission);

        rolePermissionMapper.insertBatch(rolePermissions);


    }
}
