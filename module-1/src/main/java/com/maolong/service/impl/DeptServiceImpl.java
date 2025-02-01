package com.maolong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.maolong.common.consitant.ResultConstant;
import com.maolong.common.result.PageResult;
import com.maolong.mapper.DeptMapper;
import com.maolong.mapper.UserMapper;
import com.maolong.pojo.dto.DeptDTO;
import com.maolong.pojo.entity.Dept;
import com.maolong.pojo.entity.User;
import com.maolong.service.IDeptService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Autowired
    DeptMapper deptMapper;
    @Override
    public PageResult<Dept> getDeptsByConditions(DeptDTO deptDTO) {
        PageHelper.startPage(deptDTO.getPage(),deptDTO.getLimit());
        Page<Dept> deptPage = deptMapper.selectByConditions(deptDTO);
        if(deptPage!=null){
            return new PageResult<>(deptPage.getResult(),deptPage.size());
        }else
            return new PageResult<>(Collections.emptyList(),0);
    }

    public boolean saveOrUpdate(Dept dept){
        dept.setAddUser(MDC.get(ResultConstant.USER_NAME));
        dept.setEditUser(MDC.get(ResultConstant.USER_NAME));
        Dept deptRes = deptMapper.selectById(dept.getDeptId());
        if(deptRes==null){
            return this.save(dept);
        }else
            return this.updateById(dept);
    }
}
