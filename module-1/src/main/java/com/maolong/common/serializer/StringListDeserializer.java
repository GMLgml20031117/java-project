package com.maolong.common.serializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义反序列化器，用于将JSON字符串转换为List<Integer>
 */

public class StringListDeserializer extends JsonDeserializer<List<Integer>> {

    @Override
    public List<Integer> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        // 1. 读取原始字符串（如 "[1,2,3]"）
        String rawValue = p.getValueAsString();

        // 2. 去除字符串两端的方括号和空格
        String cleaned = rawValue.replaceAll("[\\[\\]\\s]", "");

        // 3. 按逗号分割并转换为Integer列表
        return Arrays.stream(cleaned.split(","))
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}