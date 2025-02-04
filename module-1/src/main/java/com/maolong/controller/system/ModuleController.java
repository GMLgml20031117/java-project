package com.maolong.controller.system;

import com.maolong.common.result.Result;
import com.maolong.pojo.dto.ModuleDTO;
import com.maolong.pojo.entity.Module;
import com.maolong.pojo.vo.ModuleTreeDataVO;
import com.maolong.pojo.vo.NodesVo;
import com.maolong.service.ModuleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api(tags = "菜单管理")
@RequestMapping("/Module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @PostMapping("/list")
    public Result<List<ModuleTreeDataVO>> list(){
        log.info("获取菜单树");
        List<ModuleTreeDataVO> treeData = moduleService.getTreeData();

        return Result.success(treeData);
    }

    @PostMapping("/nodes")
    public Result<List<NodesVo>> nodes(){
        log.info("获取父节点信息");
        List<NodesVo> nodesVos = moduleService.getModules();
        return Result.success(nodesVos);
    }

    @GetMapping("/get/{id}")
    public Result getNode(@PathVariable Integer id){
        log.info("获取节点信息,{}",id);
        Module nodeData = moduleService.getNodeData(id);
        return Result.success(nodeData);
    }

    @PostMapping("/save")
    public Result save(@RequestBody ModuleDTO moduleDTO){
        log.info("保存节点信息,{}",moduleDTO);
        Module module = new Module();
        BeanUtils.copyProperties(moduleDTO,module);
        moduleService.saveOrUpdate(module);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result delete(Integer ids){
        log.info("删除节点信息,{}",ids);
        moduleService.removeById(ids);
        return Result.success();
    }
}
