package com.maolong.controller.system;

import com.maolong.common.result.PageResult;
import com.maolong.common.result.Result;
import com.maolong.pojo.dto.PermissionDTO;
import com.maolong.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "权限管理")
@RestController
@Slf4j
@RequestMapping("/Permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;


    /**
     * 根据条件获取列表，
     * @param permissionDTO
     * @return
     */
    @PostMapping("/list")
    public Result<PageResult> list(@RequestBody PermissionDTO permissionDTO){
        PageResult pageResult = permissionService.listByConditions(permissionDTO);
        return Result.success(pageResult);
    }

    /**
     * 保存或者修改
     * @param permissionDTO
     * @return
     */
    @PostMapping("/save")
    @ApiOperation("保存或者修改")
    public Result add(@RequestBody PermissionDTO permissionDTO) {
        log.info("保存或者修改权限信息:{}",permissionDTO);
        permissionService.saveOrUpdate(permissionDTO);
        return Result.success();
    }

    /**
     * 删除权限信息
     */
    @DeleteMapping("/delete")
    @ApiOperation("删除权限信息")
    public Result delete(Integer ids) {
        log.info("删除权限信息{}",ids);
        permissionService.deleteById(ids);
        return Result.success();
    }
}
