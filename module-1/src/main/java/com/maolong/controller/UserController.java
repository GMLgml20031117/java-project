package com.maolong.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maolong.entity.User;
import com.maolong.response.UserResponse;
import com.maolong.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author maolong
 * @since 2025-01-19
 */
@Slf4j
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/user")
    public String user(){
        return "user";
    }

    @ApiModelProperty(value = "用户登录验证")
    @PostMapping("/login")
    public UserResponse user(@RequestParam(value = "username") String username,
                             @RequestParam(value = "password") String password){

        log.info("进入了post方法");
        log.info("数据为{}", username);
        log.info("数据为{}", password);
        User one = userService.getOne(new QueryWrapper<User>().eq("username", username).eq("password", password));
        if (one != null){
            log.info("登录成功");
            return new UserResponse(null,"登陆成功",true);
        }else
            return new UserResponse(null,"登陆失败",false);
    }

    @DeleteMapping("/user/{id}")
    public String user(@PathVariable(value = "id") Long id){
        return "user";
    }

}
