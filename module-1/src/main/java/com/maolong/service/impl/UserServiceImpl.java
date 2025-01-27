package com.maolong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.maolong.common.consitant.ResultConstant;
import com.maolong.common.result.PageResult;
import com.maolong.pojo.dto.LoginDTO;
import com.maolong.pojo.dto.UserDTO;
import com.maolong.pojo.entity.User;
import com.maolong.mapper.UserMapper;
import com.maolong.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author maolong
 * @since 2025-01-21
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(LoginDTO user) {
        return userMapper.getByNameAndPasswordUser(user);
    }

    /**
     * 分页查询的使用
     * @param userDTO
     * @return
     */
    @Override
    public PageResult<User> getUsersByConditions(UserDTO userDTO) {
        PageHelper.startPage(userDTO.getPage(),userDTO.getLimit());
        Page<User> usersByConditions = userMapper.getUsersByConditions(userDTO);
        if (usersByConditions == null) {
            return new PageResult<>(Collections.emptyList(),0);
        }else
            return new PageResult<>(usersByConditions.getResult(),usersByConditions.size());
    }
    /**
     * 保存以及更新方法
     */
    @Override
    public boolean saveUser(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        //先判断是否有id,有id则更新,没有id则新增，这里直接使用了mybatis-plus中的东西
        User ifUser = userMapper.selectOne(new QueryWrapper<User>().eq("user_name", user.getUserName()));
        if (ifUser == null) {
            String s = MDC.get(ResultConstant.USER_NAME);
            log.info("当前用户名:{}",s);
            user.setAddUser(s);
            user.setUserPassword("123456");
            user.setIsLock("N");
            user.setAddTime(LocalDateTime.now());
            user.setEditTime(LocalDateTime.now());
            return userMapper.insert(user)>0?true:false;
        }else{
            user.setId(Integer.valueOf(MDC.get(ResultConstant.USER_ID)));
            return userMapper.updateById(user)>0?true:false;//直接使用了mybatis-plus中的东西
        }





    }
}
