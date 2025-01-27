package com.maolong.mapper;

import com.github.pagehelper.Page;
import com.maolong.pojo.dto.LoginDTO;
import com.maolong.pojo.dto.UserDTO;
import com.maolong.pojo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author maolong
 * @since 2025-01-21
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where user_name=#{username} and user_password=#{password}")
    User getByNameAndPasswordUser(LoginDTO user);

    //根据条件查询用户列表
    Page<User> getUsersByConditions(UserDTO userDTO);

    //保存用户信息
    int saveUser(User user);


}
