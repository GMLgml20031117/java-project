package com.maolong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maolong.common.result.PageResult;
import com.maolong.pojo.dto.DeptDTO;
import com.maolong.pojo.entity.Dept;
import com.maolong.pojo.entity.User;
import com.maolong.pojo.vo.DeptVO;

import java.util.List;

public interface IDeptService extends IService<Dept> {
    PageResult<Dept> getDeptsByConditions(DeptDTO deptDTO);

    boolean saveOrUpdate(Dept dept);


    List<DeptVO> getDepts();
}
