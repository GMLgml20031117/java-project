package com.maolong.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maolong.mapper.ModuleMapper;
import com.maolong.pojo.entity.Module;
import com.maolong.pojo.vo.ModuleTreeDataVO;
import com.maolong.pojo.vo.NodesVo;

import java.util.List;

public interface ModuleService extends IService<Module> {
    List<NodesVo> getModules();

    List<ModuleTreeDataVO> getTreeData();

    Module getNodeData(Integer id);
}
