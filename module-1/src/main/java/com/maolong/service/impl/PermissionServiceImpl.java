package com.maolong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.maolong.common.consitant.ResultConstant;
import com.maolong.common.result.PageResult;
import com.maolong.mapper.PermissionMapper;
import com.maolong.mapper.RolePermissionMapper;
import com.maolong.pojo.dto.PermissionDTO;
import com.maolong.pojo.entity.Permission;
import com.maolong.pojo.entity.RolePermission;
import com.maolong.service.PermissionService;
import org.slf4j.MDC;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 根据条件查询
     * @param permissionDTO
     * @return
     */
    @Override
    public PageResult listByConditions(PermissionDTO permissionDTO) {
        PageHelper.startPage(permissionDTO.getPage(),permissionDTO.getLimit());


        Page<Permission> byConditions = permissionMapper.getByConditions(permissionDTO);

        System.out.println(byConditions.getResult());

        return new PageResult(byConditions.getResult(),byConditions.size());
    }

    /**
     * 保存或者修改
     * @param permissionDTO
     */
    @Override
    public void saveOrUpdate(PermissionDTO permissionDTO) {

        Permission res = permissionMapper.selectById(permissionDTO.getPermissionId());
        Permission permission = new Permission();
        BeanUtils.copyProperties(permissionDTO,permission);

        //没查到数据，代表还没保存，那么进行保存操作
        if(res==null){
            permission.setAddUser(MDC.get(ResultConstant.USER_NAME));
            permission.setEditUser(MDC.get(ResultConstant.USER_NAME));
            permissionMapper.insert(permission);
        }else {
            //查到了数据，代表已经保存，那么进行修改操作
            BeanUtils.copyProperties(permissionDTO,res);
            permission.setAddUser(MDC.get(ResultConstant.USER_NAME));
            permission.setEditUser(MDC.get(ResultConstant.USER_NAME));
            permissionMapper.updateById(res);
        }

    }

    /**
     * 删除权限信息
     * @param ids
     */
    @Transactional
    @Override
    public void deleteById(Integer ids) {
        rolePermissionMapper.delete(new QueryWrapper<RolePermission>().eq("permission_id",ids));
        permissionMapper.deleteById(ids);
    }
}
