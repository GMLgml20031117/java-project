package com.maolong.controller.system;


import com.maolong.common.consitant.LoginMessage;
import com.maolong.common.consitant.ResultConstant;
import com.maolong.common.properties.JwtProperties;
import com.maolong.common.result.LoginResult;
import com.maolong.common.result.PageResult;
import com.maolong.common.result.Result;
import com.maolong.common.util.JwtUtil;
import com.maolong.pojo.dto.LoginDTO;
import com.maolong.pojo.dto.UserDTO;
import com.maolong.pojo.entity.User;
import com.maolong.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@Api("用户管理以及登录验证")
@RequestMapping("/User")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    @ApiModelProperty(value = "登录验证")
    @PostMapping("/login")
    public LoginResult user(@RequestBody LoginDTO user) {
        log.info("进行用户登录,用户信息：{}", user);
        //使用md5加密算法,先将密码加密，然后与数据库中的密码做对比
        String decodePassword = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(decodePassword);
        User userResult = userService.login(user);

        if (userResult != null) {
            HashMap<String, Object> claims = new HashMap<>();
            //将用户id和用户名放进jwt中的paylaod载体种
            claims.put(ResultConstant.USER_ID, userResult.getId());
            claims.put(ResultConstant.USER_NAME, userResult.getUserName());
            String jwt = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);
            log.info("生成的token是{}",jwt);
            return LoginResult.success(userResult,jwt);
        } else
            return LoginResult.error(LoginMessage.LOGIN_FAIL);
    }


    @ApiModelProperty("查询用户")
    @PostMapping("/list")
    public Result query(@RequestBody UserDTO userDTO){
        log.info("查询信息:{}",userDTO);

        PageResult<User> usersByConditions = userService.getUsersByConditions(userDTO);

        System.out.println(usersByConditions);
        return Result.success(usersByConditions);
    }


    /**
     * 保存编辑用户
     * @param userDTO
     * @return
     */
    @ApiModelProperty("保存编辑用户")
    @PostMapping("/save")
    public Result save(@RequestBody UserDTO userDTO){
        log.info("待保存用户信息:{}",userDTO);
        boolean b = userService.saveUser(userDTO);
        return b?Result.success():Result.error("保存失败");

    }

    @ApiModelProperty("删除用户")
    @DeleteMapping("/delete")
    public Result delete(Integer ids){
        log.info("待删除用户id:{}",ids);
        return userService.removeById(ids)?Result.success():Result.error("删除失败");

    }

    @ApiModelProperty("重置密码")
    @PostMapping("/pwd")
    public Result resetPwd(@RequestBody Map<String,Object>map){
        log.info("待重置密码用户userId:{}",map.get("userId"));
        String userId = (String) map.get("userId");
        return userService.resetPwd(userId)?Result.success():Result.error("重置密码失败");
    }

    @ApiModelProperty("修改状态")
    @GetMapping("/lock")
    public Result lock(String userId,String lock){
        log.info("待修改状态用户id:{},状态:{}",userId,lock);
        return userService.lock(userId,lock)?Result.success():Result.error("修改状态失败");
    }

}
