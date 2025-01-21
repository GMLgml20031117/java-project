package com.maolong.service.impl;

import com.maolong.entity.User;
import com.maolong.mapper.UserMapper;
import com.maolong.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author maolong
 * @since 2025-01-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
