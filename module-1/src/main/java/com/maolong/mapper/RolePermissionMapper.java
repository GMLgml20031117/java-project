package com.maolong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maolong.pojo.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    void insertBatch(List<RolePermission> rolePermissions);
}
