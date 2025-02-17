package com.maolong.controller.system;


import com.maolong.common.result.PageResult;
import com.maolong.common.result.Result;
import com.maolong.pojo.dto.DeptDTO;
import com.maolong.pojo.dto.RoleDTO;
import com.maolong.pojo.entity.Dept;
import com.maolong.pojo.entity.Role;
import com.maolong.pojo.vo.UserRoleVO;
import com.maolong.service.IDeptService;
import com.maolong.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@Api(tags = "角色管理")
@RequestMapping("/Role")
public class RoleController {
    @Autowired
    IRoleService roleService;

    @ApiOperation("查询接口")
    @PostMapping("/list")
    public Result list(@RequestBody RoleDTO roleDTO){
        log.info("查询的参数信息为{}",roleDTO);
        PageResult<Role> rolesByConditions = roleService.getRolesByConditions(roleDTO);
        return Result.success(rolesByConditions);
    }

    @ApiOperation("新增接口和修改接口")
    @PostMapping("/save")
    public Result save(@RequestBody Role role){
        log.info("进入了save方法，{}",role);
        return roleService.saveOrUpdate(role)?Result.success():Result.error("保存失败");
    }

    @ApiOperation("删除接口")
    @DeleteMapping("/delete")
    public Result delete(@RequestParam String ids){
        log.info("进入了delete方法，{}",ids);
        roleService.deleteByIds(ids);
        return Result.success();
    }

    @ApiOperation("获取所有角色")
    @GetMapping("/dropDown/all")
    public Result getAll(){
        log.info("获取所有角色");
        List<Role> list = roleService.list();
        List<UserRoleVO> listVO = list.stream().map(role -> {
            UserRoleVO userRoleVO = new UserRoleVO();
            userRoleVO.setLabel(role.getRoleName());
            userRoleVO.setValue(role.getRoleId());
            return userRoleVO;
        }).collect(Collectors.toList());

        return Result.success(listVO);
    }

}
