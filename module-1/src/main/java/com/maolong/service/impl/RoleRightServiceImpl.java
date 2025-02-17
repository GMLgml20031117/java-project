package com.maolong.service.impl;

import com.maolong.mapper.ModuleMapper;
import com.maolong.mapper.RoleRightMapper;
import com.maolong.pojo.dto.RoleRightDTO;
import com.maolong.pojo.entity.Module;
import com.maolong.pojo.vo.ModuleTreeDataVO;
import com.maolong.service.RoleRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

            List<ModuleTreeDataVO> treeData = new ArrayList<>();
            List<Module> modules = moduleMapper.selectList(null);


            if(moduleIds!=null && modules.size()>0){
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

            }else {
                if (modules != null && modules.size() > 0) {
                    modules.forEach(module -> {
                    treeData.add(new ModuleTreeDataVO().builder()
                            .id(module.getModuleId())
                            .pId(module.getParentId())
                            .name(module.getModuleName())
                            .open(true)
                            .checked(false)
                            .children(new ArrayList<>())
                            .build());
                    });
                }
            }
        return treeData;
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
