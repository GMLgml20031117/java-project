package com.maolong;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maolong.entity.User;
import com.maolong.service.IUserService;
import com.maolong.util.JsonObjUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestLogin {
    @Autowired
    IUserService userService;
    @Test
    public void testLogin(){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username","gao");
        User one = userService.getOne(queryWrapper);
        List<User> list = userService.list();
        System.out.println(list);

        System.out.println(one);
    }
    @Test
    public void testUtil() throws Exception {
        String json = "{\"id\":1,\"username\":\"gao\",\"password\":\"123456\",\"sex\":\"男\",\"phone\":\"123456789\",\"email\":\"123456789@qq.com\"}";
        User user = JsonObjUtil.toObject(json, User.class);
        System.out.println(user);
        System.out.println(JsonObjUtil.toJson(user));

    }
}
