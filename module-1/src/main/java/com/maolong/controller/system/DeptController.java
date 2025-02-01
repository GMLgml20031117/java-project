package com.maolong.controller.system;


import com.maolong.common.consitant.ResultConstant;
import com.maolong.common.result.PageResult;
import com.maolong.common.result.Result;
import com.maolong.pojo.dto.DeptDTO;
import com.maolong.pojo.entity.Dept;
import com.maolong.service.IDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api("部门管理")
@RequestMapping("/Dept")
public class DeptController {

    @Autowired
    IDeptService deptService;

    @ApiModelProperty("查询接口")
    @PostMapping("/list")
    public Result list(@RequestBody DeptDTO deptDTO){
        log.info("查询的参数信息为{}",deptDTO);
        PageResult<Dept> deptsByConditions = deptService.getDeptsByConditions(deptDTO);
        return Result.success(deptsByConditions);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Dept dept){
        log.info("进入了save方法，{}",dept);
        return deptService.saveOrUpdate(dept)?Result.success():Result.error("保存失败");
    }

    @GetMapping("/delete")
    public Result delete(@RequestParam String ids){
        log.info("进入了delete方法，{}",ids);
        return deptService.removeById(ids)?Result.success():Result.error("删除失败");
    }


}
