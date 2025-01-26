package com.maolong.controller.system;


import com.maolong.common.result.Result;
import com.maolong.pojo.dto.UserDTO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api("用户管理")
@RequestMapping("/User")
public class UserSystemController {

    @PostMapping("/save")
    public Result save(@RequestBody UserDTO userDTO){
        log.info("用户信息:{}",userDTO);

        return Result.success();

    }
}
