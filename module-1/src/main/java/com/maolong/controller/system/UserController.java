package com.maolong.controller.system;


import com.maolong.common.consitant.ResultConstant;
import com.maolong.common.properties.JwtProperties;
import com.maolong.common.result.LoginResult;
import com.maolong.common.result.PageResult;
import com.maolong.common.result.Result;
import com.maolong.common.util.JwtUtil;
import com.maolong.pojo.dto.LoginDTO;
import com.maolong.pojo.dto.UserDTO;
import com.maolong.pojo.entity.User;
import com.maolong.pojo.vo.DeptVO;
import com.maolong.pojo.vo.UserRoleVO;
import com.maolong.service.IDeptService;
import com.maolong.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@Api(tags = "用户管理以及登录验证")
@RequestMapping("/User")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private IDeptService iDeptService;
    @Autowired
    RedisTemplate redisTemplate;


    @ApiOperation(value = "登录验证")
    @PostMapping("/login")
    public LoginResult user(@RequestBody LoginDTO user) {
        //使用md5加密算法,先将密码加密，然后与数据库中的密码做对比
        String decodePassword = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(decodePassword);
        User userResult = userService.login(user);

        HashMap<String, Object> claims = new HashMap<>();
        //将用户id和用户名放进jwt中的paylaod载体种
        claims.put(ResultConstant.USER_ID, userResult.getId());
        claims.put(ResultConstant.USER_NAME, userResult.getUserName());
        //生成了jwt
        String jwt = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);
        log.info("生成的token是{}",jwt);
        //将token存入redis中
        //key:token，value:userResult内容
        redisTemplate.opsForValue().set(ResultConstant.REDIS_TOKEN_KEY,userResult,jwtProperties.getAdminTtl(),TimeUnit.MILLISECONDS);
        return LoginResult.success(userResult,jwt);

    }


    @ApiOperation("查询用户")
    @PostMapping("/list")
    public Result query(@RequestBody UserDTO userDTO){

        PageResult<User> usersByConditions = userService.getUsersByConditions(userDTO);

        System.out.println(usersByConditions);
        return Result.success(usersByConditions);
    }


    /**
     * 保存编辑用户
     * @param userDTO
     * @return
     */
    @ApiOperation("保存编辑用户")
    @PostMapping("/save")
    public Result save(@RequestBody UserDTO userDTO){
        userService.saveUser(userDTO);
        return Result.success();

    }

    @ApiOperation("删除用户")
    @DeleteMapping("/delete")
    public Result delete(Integer ids){
        return userService.removeById(ids)?Result.success():Result.error("删除失败");

    }

    @ApiOperation("重置密码")
    @PostMapping("/pwd")
    public Result resetPwd(@RequestBody Map<String,Object>map){
        Integer userId = (Integer) map.get("id");
        userService.resetPwd(userId);
        return Result.success();
    }

    @ApiOperation("修改状态")
    @GetMapping("/lock")
    public Result lock(String userId,String lock){
        userService.lock(userId,lock);
        return Result.success();
    }

    @ApiOperation("获取用户角色")
    @GetMapping("/role")
    public Result getRole(){
        List<UserRoleVO> roles = userService.getRoles();
        return Result.success(roles);
    }
    @ApiOperation("获取公司部门信息")
    @GetMapping("/dept")
    public Result getDept(){
        List<DeptVO> depts = iDeptService.getDepts();
        return Result.success(depts);
    }


}
