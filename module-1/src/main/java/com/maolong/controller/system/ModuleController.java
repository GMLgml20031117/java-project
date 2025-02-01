package com.maolong.controller.system;

import com.maolong.common.result.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags = "菜单管理")
@RequestMapping("/Module")
public class ModuleController {
    @PostMapping("/list")
    public Result list(){
        return Result.success();
    }

    @PostMapping("/nodes")
    public Result nodes(){
        return Result.success();
    }
}
