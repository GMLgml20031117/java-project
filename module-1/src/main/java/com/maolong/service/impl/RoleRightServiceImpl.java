package com.maolong.service.impl;

import com.maolong.common.exception.FindFailException;
import com.maolong.mapper.ModuleMapper;
import com.maolong.mapper.RoleRightMapper;
import com.maolong.pojo.dto.RoleRightDTO;
import com.maolong.pojo.entity.Module;
import com.maolong.pojo.vo.ModuleTreeDataVO;
import com.maolong.service.ModuleService;
import com.maolong.service.RoleRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RoleRightServiceImpl implements RoleRightService {
    @Autowired
    RoleRightMapper roleRightMapper;
    @Autowired
    ModuleMapper moduleMapper;

    @Override
    public List<ModuleTreeDataVO> getData(Integer id) {
        //查出来user对应的moduleIds
        List<Integer> moduleIds = roleRightMapper.listByRoleId(id);
        if (moduleIds == null || moduleIds.isEmpty()) {
            throw new FindFailException("数据不存在");
        }else {
            List<ModuleTreeDataVO> treeData = new ArrayList<>();
            //查出来用户拥有的modules
            //似乎不太需要，只需要查询全部的，然后把上面user对应的moduleId设为true
//            List<Module> modules = roleRightMapper.listByModuleIds(moduleIds);
            //查出来全部的modules
            List<Module> modules = moduleMapper.selectList(null);


            //返回的其实是所有的菜单，只不过是用户拥有的菜单给设置为了选中状态
            if (modules != null && modules.size() > 0) {
                modules.forEach(module -> {
                    if (moduleIds.contains(module.getModuleId())) {
                        treeData.add(new ModuleTreeDataVO().builder()
                                .id(module.getModuleId())
                                .pId(module.getParentId())
                                .name(module.getModuleName())
                                .open(true)
                                .checked(true)
                                .children(new ArrayList<>())
                                .build());
                    }else{
                        treeData.add(new ModuleTreeDataVO().builder()
                                .id(module.getModuleId())
                                .pId(module.getParentId())
                                .name(module.getModuleName())
                                .open(true)
                                .checked(false)
                                .children(new ArrayList<>())
                                .build());
                    }
                });
            }
            return treeData;
        }
    }

    /**
     * 实现对于菜单操作的保存
     */
    @Override
    @Transactional
    public void save(RoleRightDTO roleRightDTO) {
        //先把所有有关role_id的都删除了
        roleRightMapper.deleteByRoleId(roleRightDTO.getRoleId());
        //然后保存新的
        roleRightMapper.addBatch(roleRightDTO);
    }

}
