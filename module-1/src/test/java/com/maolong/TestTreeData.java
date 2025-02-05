package com.maolong;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestTreeData {
    @Data
    @AllArgsConstructor
    class Node {
        int id;
        int parentId;
        String name;
        List<Node> children;
    }

    @Test
    public void test() {
        List<Node> list = new ArrayList<>();
        list.add(new Node(1, 1, "*", new ArrayList<>()));
        list.add(new Node(2, 1, "A", new ArrayList<>()));
        list.add(new Node(3, 1, "B", new ArrayList<>()));
        list.add(new Node(4, 2, "A1", new ArrayList<>()));
        list.add(new Node(5, 2, "A2", new ArrayList<>()));
        list.add(new Node(6, 3, "B1", new ArrayList<>()));
        list.add(new Node(7, 6, "B2", new ArrayList<>()));

        // 创建父ID到子节点列表的映射
        Map<Integer, List<Node>> parentIdToChildren = new HashMap<>();
        for (Node node : list) {
            parentIdToChildren.computeIfAbsent(node.getParentId(), k -> new ArrayList<>()).add(node);
        }

        List<Node> rootNodes = new ArrayList<>();

        for (Node node : list) {
            int nodeId = node.getId();
            int parentId = node.getParentId();

            // 判断是否为根节点（这里假设根节点的parentId等于自身id）
            if (nodeId == parentId) {
                rootNodes.add(node);
                // 获取子节点并排除自身（如果存在）
                List<Node> children = parentIdToChildren.getOrDefault(parentId, Collections.emptyList())
                        .stream()
                        .filter(child -> child.getId() != nodeId) // 排除自身作为子节点
                        .collect(Collectors.toList());
                node.getChildren().addAll(children);
            } else {
                // 非根节点直接设置子节点
                List<Node> children = parentIdToChildren.get(nodeId);
                if (children != null) {
                    node.getChildren().addAll(children);
                }
            }
        }

        // 输出根节点及其树结构
        System.out.println(rootNodes);
    }
}