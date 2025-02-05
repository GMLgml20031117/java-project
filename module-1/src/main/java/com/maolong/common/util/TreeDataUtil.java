package com.maolong.common.util;

import com.maolong.pojo.vo.ModuleTreeDataVO;

import java.util.*;
import java.util.stream.Collectors;

public class TreeDataUtil {

    /**
     * 构造树结构
     */
    public static List<ModuleTreeDataVO> getTrees(List<ModuleTreeDataVO> list) {

        // 创建父ID到子节点列表的映射
        Map<Integer, List<ModuleTreeDataVO>> parentIdToChildren = new HashMap<>();
        for (ModuleTreeDataVO ModuleTreeDataVO : list) {
            parentIdToChildren.computeIfAbsent(ModuleTreeDataVO.getPId(), k -> new ArrayList<>()).add(ModuleTreeDataVO);
        }

        List<ModuleTreeDataVO> rootNodes = new ArrayList<>();

        for (ModuleTreeDataVO ModuleTreeDataVO : list) {
            int nodeId = ModuleTreeDataVO.getId();
            int parentId = ModuleTreeDataVO.getPId();

            // 判断是否为根节点（这里假设根节点的parentId等于自身id）
            if (nodeId == parentId) {
                rootNodes.add(ModuleTreeDataVO);
                // 获取子节点并排除自身（如果存在）
                List<ModuleTreeDataVO> children = parentIdToChildren.getOrDefault(parentId, Collections.emptyList())
                        .stream()
                        .filter(child -> child.getId() != nodeId) // 排除自身作为子节点
                        .collect(Collectors.toList());
                ModuleTreeDataVO.getChildren().addAll(children);
            } else {
                // 非根节点直接设置子节点
                List<ModuleTreeDataVO> children = parentIdToChildren.get(nodeId);
                if (children != null) {
                    ModuleTreeDataVO.getChildren().addAll(children);
                }
            }
        }

        // 输出根节点及其树结构
        return rootNodes;
    }
}
