package com.maolong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maolong.pojo.dto.RoleRightDTO;
import com.maolong.pojo.entity.Module;
import com.maolong.pojo.vo.ModuleTreeDataVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleRightMapper{
    /**
     * 根据角色id获得菜单id
     * @param id
     * @return
     */
    @Select("select module_id from role_module where role_id=#{id}")
    List<Integer> listByRoleId(Integer id);


    List<Module> listByModuleIds(List<Integer> moduleIds);

    @Delete("delete from role_module where role_id=#{roleId}")
    void deleteByRoleId(Integer roleId);


    void addBatch(RoleRightDTO roleRightDTO);
}
