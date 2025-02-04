package com.maolong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maolong.mapper.ModuleMapper;
import com.maolong.pojo.entity.Module;
import com.maolong.pojo.vo.ModuleTreeDataVO;
import com.maolong.pojo.vo.NodesVo;
import com.maolong.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper,Module> implements ModuleService {
    @Autowired
    ModuleMapper moduleMapper;
    @Override
    public List<NodesVo> getModules() {
        List<Module> modules = moduleMapper.selectList(null);
        modules.sort(Comparator.comparing(Module::getModuleOrder));
        List<NodesVo> nodes = new ArrayList<>();
        if(modules!=null && modules.size()>0){
            modules.forEach(module -> {
                nodes.add(new NodesVo(module.getModuleId(), module.getModuleName()));
            });
        }

        nodes.forEach(node -> {
            System.out.println(node);
        });

        return nodes;
    }

    @Override
    public List<ModuleTreeDataVO> getTreeData() {
        List<Module> modules = moduleMapper.selectList(null);
        modules.sort(Comparator.comparing(Module::getModuleOrder));
        List<ModuleTreeDataVO> treeData = new ArrayList<>();
        if (modules != null && modules.size() > 0) {
            modules.forEach(module -> {
                treeData.add(new ModuleTreeDataVO().builder()
                        .id(module.getModuleId())
                        .pId(module.getParentId())
                        .name(module.getModuleName())
                        .open(true)
                        .checked(false)
                        .build());
            });
        }
        return treeData;
    }

    @Override
    public Module getNodeData(Integer id) {
        Module module = moduleMapper.selectOne(new QueryWrapper<Module>().eq("module_id", id));
        return module;

    }


}
