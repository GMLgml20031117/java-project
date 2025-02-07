package com.maolong.controller.system;

import com.maolong.common.result.Result;
import com.maolong.mapper.RolePermissionMapper;
import com.maolong.pojo.dto.RolePermissionDTO;
import com.maolong.service.RolePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/RolePermission")
@Api("角色权限管理")
public class RolePermissionController {
    @Autowired
    RolePermissionService rolePermissionService;

    /**
     * 配置权限信息
     * @param rolePermissionDTO
     * @return
     */
    @PostMapping("/save")
    @ApiOperation("角色权限保存")
    public Result save(@RequestBody RolePermissionDTO rolePermissionDTO) {
        log.info("角色权限保存:{}", rolePermissionDTO);
        rolePermissionService.save(rolePermissionDTO);
        return Result.success();
    }
}
