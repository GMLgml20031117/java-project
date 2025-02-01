package com.maolong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.maolong.pojo.dto.DeptDTO;
import com.maolong.pojo.entity.Dept;
import com.maolong.pojo.entity.User;

import java.util.List;

public interface DeptMapper extends BaseMapper<Dept> {

    Page<Dept> selectByConditions(DeptDTO deptDTO);
}
