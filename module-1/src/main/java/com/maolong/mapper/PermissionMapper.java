package com.maolong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.maolong.common.result.PageResult;
import com.maolong.pojo.dto.PermissionDTO;
import com.maolong.pojo.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    Page<Permission> getByConditions(PermissionDTO permissionDTO);

    //查询所有的权限名称
    List<String> getAllPermissionName(List<Integer> permissionIds);

    List<Permission> getAllPermission(List<Integer> permissionIds);
}
