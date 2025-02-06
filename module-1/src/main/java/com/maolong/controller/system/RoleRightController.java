package com.maolong.controller.system;

import com.maolong.common.result.Result;
import com.maolong.common.util.TreeDataUtil;
import com.maolong.pojo.dto.RoleRightDTO;
import com.maolong.pojo.vo.ModuleTreeDataVO;
import com.maolong.service.ModuleService;
import com.maolong.service.RoleRightService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("角色菜单权限管理")
@RestController
@Slf4j
@RequestMapping("/RoleRight")
public class RoleRightController {

    @Autowired
    RoleRightService roleRightService;

    @Autowired
    ModuleService moduleService;


    @ApiOperation("菜单权限获取")
    @GetMapping("/tree/{id}")
    public Result<List<ModuleTreeDataVO>> getTreeData(@PathVariable Integer id) {
        log.info("获取菜单权限{}",id);
        List<ModuleTreeDataVO> treeData = roleRightService.getData(id);
//        List<ModuleTreeDataVO> trees = TreeDataUtil.getTrees(treeData);
        return Result.success(treeData);
    }

    @ApiOperation("保存菜单权限")
    @PostMapping("/save")
    public Result save(@RequestBody RoleRightDTO roleRightDTO){
        log.info("保存菜单权限{}",roleRightDTO);
        roleRightService.save(roleRightDTO);
        return Result.success();
    }
}
