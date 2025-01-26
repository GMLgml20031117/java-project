package com.maolong.controller.login;
import com.maolong.common.consitant.LoginMessage;
import com.maolong.common.properties.JwtProperties;
import com.maolong.common.result.LoginResult;
import com.maolong.common.util.JwtUtil;
import com.maolong.pojo.dto.LoginDTO;
import com.maolong.pojo.entity.User;
import com.maolong.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
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
    public LoginResult user(@RequestBody LoginDTO user) {
        log.info("进行用户登录,用户信息：{}", user);
        //使用md5加密算法,先将密码加密，然后与数据库中的密码做对比
        String decodePassword = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(decodePassword);
        User result = userService.login(user);

        if (result != null) {
            HashMap<String, Object> claims = new HashMap<>();
            //将用户id和用户名放进jwt中的paylaod载体种
            claims.put("id", result.getId());
            claims.put("username", result.getUsername());
            String jwt = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);
            log.info("生成的token是{}",jwt);
            return LoginResult.success(result,jwt);
        } else
            return LoginResult.error(LoginMessage.LOGIN_FAIL);
    }

//以前使用URL编码，写的方法，因为URL编码方式，相当于在浏览器地址栏，导致了一些错误，现在普遍使用json'编码
//    @ApiModelProperty(value = "用户登录验证")
//    @PostMapping("/login")
//    public LoginResult user(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password,
//                            @RequestParam(value = "code",required = false) String code) {
//        log.info("进行用户登录,用户信息：{}", username,password);
//        //使用md5加密算法
//        String s = DigestUtils.md5DigestAsHex(password.getBytes());
//        UserDTO user = new UserDTO();
//        user.setUsername(username);
//        user.setPassword(s);
//
//
//        User result = userService.login(user);
//        if (result != null) {
//            HashMap<String, Object> claims = new HashMap<>();
//            claims.put("id", result.getId());
//            claims.put("username", result.getUsername());
//            String jwt = JwtUtil.createJwt(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);
//            log.info("生成的token是{}",jwt);
//            return LoginResult.success(result,jwt);
//        } else
//            return LoginResult.error(LoginMessage.LOGIN_FAIL);
//    }

}

