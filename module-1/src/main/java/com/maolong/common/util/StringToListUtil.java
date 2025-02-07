package com.maolong.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 将类似于 “[1,2,3]” 的字符串转换为 List<Integer>[1,2,3]
 */
public class StringToListUtil {
    public static List<Integer> stringToList(String moduleIdsJson) {
        List<Integer> moduleIds = new ArrayList<>();
        if(!moduleIdsJson.contains("[")){
            moduleIdsJson="["+moduleIdsJson+"]";
        }
        if (moduleIdsJson != null && !moduleIdsJson.isEmpty()) {
            String[] ids = moduleIdsJson.replace("[", "").replace("]", "").split(",");
            for (String id : ids) {
                moduleIds.add(Integer.parseInt(id.trim()));
            }
        }
        return moduleIds;
    }
}
