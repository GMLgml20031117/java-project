package com.maolong.pojo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.maolong.common.serializer.StringListDeserializer;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoleRightDTO implements Serializable {
    private Integer roleId;
    @JsonDeserialize(using = StringListDeserializer.class)
    private List<Integer> moduleIds;
}
