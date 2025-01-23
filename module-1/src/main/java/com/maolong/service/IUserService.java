package com.maolong.service;

import com.maolong.pojo.dto.UserDTO;
import com.maolong.pojo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author maolong
 * @since 2025-01-21
 */
public interface IUserService extends IService<User> {
   public User login(UserDTO user);

}
