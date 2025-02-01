package com.maolong.service;

import com.maolong.common.result.PageResult;
import com.maolong.pojo.dto.LoginDTO;
import com.maolong.pojo.dto.UserDTO;
import com.maolong.pojo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author maolong
 * @since 2025-01-21
 */
public interface IUserService extends IService<User> {
   public User login(LoginDTO user);

   /**
    * 实现分页查询
    * @param userDTO
    * @return
    */
   public PageResult<User> getUsersByConditions(UserDTO userDTO);
   /**
    * 实现保存功能
    */
   public boolean saveUser(UserDTO userDTO);

   boolean resetPwd(String userId);

   boolean lock(String userId, String lock);
}
