package com.maolong.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 工具类，实现了json和java对象的转换
 */
public class JsonObjUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object obj) throws Exception {
        try {
            return objectMapper.writeValueAsString(obj);
        }catch (Exception e){
            throw new Exception("对象转换为json失败",e);
        }

    }

    public static <T> T toObject(String json, Class<T> clazz) throws Exception {
        try {
            return objectMapper.readValue(json, clazz);
        }catch (Exception e){
            throw new Exception("json转换为对象失败",e);
        }

    }
}
