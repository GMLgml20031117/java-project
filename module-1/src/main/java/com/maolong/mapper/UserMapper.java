package com.maolong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.maolong.pojo.dto.LoginDTO;
import com.maolong.pojo.dto.UserDTO;
import com.maolong.pojo.entity.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    //重置密码
    @Update("update user set user_password=#{password} where id=#{userId}")
    int resetPwd(Integer userId,String password);


}
