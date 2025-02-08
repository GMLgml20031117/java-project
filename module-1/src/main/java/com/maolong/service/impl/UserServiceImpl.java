package com.maolong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.maolong.common.consitant.ExceptionMessage;
import com.maolong.common.consitant.ResultConstant;
import com.maolong.common.result.PageResult;
import com.maolong.exception.FindFailException;
import com.maolong.exception.SaveFailException;
import com.maolong.mapper.RoleMapper;
import com.maolong.mapper.UserMapper;
import com.maolong.pojo.dto.LoginDTO;
import com.maolong.pojo.dto.UserDTO;
import com.maolong.pojo.entity.Role;
import com.maolong.pojo.entity.User;
import com.maolong.pojo.vo.UserRoleVO;
import com.maolong.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Autowired
    RoleMapper roleMapper;

    /**
     * 验证登录
     * @param user
     * @return
     */
    @Override
    public User login(LoginDTO user) {
        User byNameAndPasswordUser = userMapper.getByNameAndPasswordUser(user);
        if (byNameAndPasswordUser == null) {
            //抛出异常
            throw new FindFailException(ExceptionMessage.USER_PASSWORD_ERROR);
        }else
            return byNameAndPasswordUser;
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
            throw new FindFailException(ExceptionMessage.FIND_ERROR);
        }else
            return new PageResult<>(usersByConditions.getResult(),usersByConditions.size());
    }
    /**
     * 保存以及更新方法
     * 通过先通过用户id查询是否存在，不存在的话，则新增，存在的话，则更新
     */
    @Override
    public void saveUser(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);

        //先判断是否有id,有id则更新,没有id则新增，这里直接使用了mybatis-plus中的东西
        User ifUser = userMapper.selectOne(new QueryWrapper<User>().eq(ResultConstant.ID, user.getId()));
        if (ifUser == null) {
            user = userDefault(user);
            if(userMapper.insert(user)==0){
                throw new SaveFailException(ExceptionMessage.SAVE_ERROR);
            }
        }else{
            user = userEditDefault(user);
            if(userMapper.update(user,new UpdateWrapper<User>().eq(ResultConstant.ID,user.getId()))==0){
                throw new SaveFailException(ExceptionMessage.SAVE_ERROR);
            }
        }
    }

    /**
     * 重置密码
     * @param userId
     * @return
     */
    @Override
    public void resetPwd(Integer userId) {
        if(userMapper.resetPwd(userId,userDefault(new User()).getUserPassword())==0){
            throw new SaveFailException(ExceptionMessage.SAVE_ERROR);
        }
    }

    @Override
    public void lock(String userId, String lock) {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        if (lock.equals("N")){
            userUpdateWrapper.eq(ResultConstant.USER_ID,userId).set(ResultConstant.IS_LOCK,"F");
        }else{
            userUpdateWrapper.eq(ResultConstant.USER_ID,userId).set(ResultConstant.IS_LOCK,"N");
        }

        if(userMapper.update(null,userUpdateWrapper)==0){
            throw new SaveFailException(ExceptionMessage.SAVE_ERROR);
        }

    }



    /**
     * 用来设置一些user的默认属性，相当于工具类
     * @param user
     * @return
     */
    public User userDefault(User user){
        String s = MDC.get(ResultConstant.USER_NAME);
        user.setAddUser(s);
        user.setEditUser(s);
        user.setUserPassword(DigestUtils.md5DigestAsHex(ResultConstant.USER_PASSWORD.getBytes()));
        user.setIsLock("N");
        user.setAddTime(LocalDateTime.now());
        user.setEditTime(LocalDateTime.now());
        return user;
    }

    /**
     * 用来设置编辑user的一些默认操作,相当于工具类
     */
    public User userEditDefault(User user){
        String s = MDC.get(ResultConstant.USER_NAME);
        user.setEditUser(s);
        user.setEditTime(LocalDateTime.now());
        return user;
    }

    /**
     * 获取用户的角色信息
     * @return
     */
    @Override
    public List<UserRoleVO> getRoles() {
        List<Role> roles = roleMapper.selectList(null);
        List<UserRoleVO> userRoleVOS=new ArrayList<>();

        roles.forEach(role -> {
            UserRoleVO userRoleVO = new UserRoleVO();
            userRoleVO.setValue(role.getRoleId());
            userRoleVO.setLabel(role.getRoleName());
            userRoleVOS.add(userRoleVO);
        });

        if((userRoleVOS==null)||(userRoleVOS.size()==0)){
            throw new FindFailException(ExceptionMessage.FIND_ERROR);
        }
        return userRoleVOS;
    }


}
