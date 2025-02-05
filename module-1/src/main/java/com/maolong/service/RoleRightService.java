package com.maolong.service;

import com.maolong.pojo.dto.RoleRightDTO;
import com.maolong.pojo.vo.ModuleTreeDataVO;

import java.util.List;

public interface RoleRightService {
    List<ModuleTreeDataVO> getData(Integer id);

    void save(RoleRightDTO roleRightDTO);
}
