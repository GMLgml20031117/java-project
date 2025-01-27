package com.maolong.controller.system;


import com.maolong.common.consitant.ResultConstant;
import com.maolong.common.result.PageResult;
import com.maolong.common.result.Result;
import com.maolong.common.util.BaseContext;
import com.maolong.pojo.dto.UserDTO;
import com.maolong.pojo.entity.User;
import com.maolong.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Api("用户管理")
@RequestMapping("/User")
public class UserSystemController {

    @Autowired
    private IUserService userService;

    @ApiModelProperty("查询用户")
    @PostMapping("/list")
    public Result query(@RequestBody UserDTO userDTO){
        log.info("查询信息:{}",userDTO);

        PageResult<User> usersByConditions = userService.getUsersByConditions(userDTO);

        System.out.println(usersByConditions);
        return Result.success(usersByConditions);
    }


    /**
     * 保存用户
     * @param userDTO
     * @return
     */
    @ApiModelProperty("保存用户")
    @PostMapping("/save")
    public Result save(@RequestBody UserDTO userDTO){
        log.info("待保存用户信息:{}",userDTO);
        boolean b = userService.saveUser(userDTO);
        return b?Result.success():Result.error("保存失败");

    }
}
