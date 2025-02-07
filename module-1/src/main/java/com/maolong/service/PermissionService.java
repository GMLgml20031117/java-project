package com.maolong.service;

import com.maolong.common.result.PageResult;
import com.maolong.pojo.dto.PermissionDTO;

public interface PermissionService {
    PageResult listByConditions(PermissionDTO permissionDTO);

    void saveOrUpdate(PermissionDTO permissionDTO);

    void deleteById(Integer ids);
}
