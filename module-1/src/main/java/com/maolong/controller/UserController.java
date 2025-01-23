package com.maolong.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maolong.common.consitant.LoginMessage;
import com.maolong.common.properties.JwtProperties;
import com.maolong.common.result.LoginResult;
import com.maolong.common.result.Result;
import com.maolong.common.util.JwtUtil;
import com.maolong.pojo.dto.UserDTO;
import com.maolong.pojo.entity.User;
import com.maolong.response.UserResponse;
import com.maolong.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
@RequestMapping("/User")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private JwtProperties jwtProperties;


    @ApiModelProperty(value = "用户登录验证")
    @PostMapping("/login")
    public LoginResult user(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password,
                            @RequestParam(value = "code",required = false) String code) {
        log.info("进行用户登录,用户信息：{}", username,password);
        UserDTO user = new UserDTO();
        user.setUsername(username);
        user.setPassword(password);

        User result = userService.login(user);
        if (result != null) {
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("id", result.getId());
            claims.put("username", result.getUsername());
            String jwt = JwtUtil.createJwt(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);
            log.info("生成的token是{}",jwt);
            return LoginResult.success(result,jwt);
        } else
            return LoginResult.error(LoginMessage.LOGIN_FAIL);
    }

    //TODO:为什么使用json注入的方式不行啊？
//@ApiModelProperty(value = "用户登录验证")
//@PostMapping("/login")
//public Result user(@RequestBody UserDTO user) {
//    log.info("进行用户登录,用户信息：{}", user);
//    User result = userService.login(user);
//    if (result != null) {
//        HashMap<String, Object> claims = new HashMap<>();
//        claims.put("id", result.getId());
//        claims.put("username", result.getUsername());
//        String jwt = JwtUtil.createJwt(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);
//        log.info("生成的token是{}",jwt);
//            return LoginResult.success(result,jwt);
//} else
//        return LoginResult.error(LoginMessage.LOGIN_FAIL);
//}
}

