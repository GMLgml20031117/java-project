package com.maolong.mapper;

import com.maolong.pojo.entity.Test;
import org.apache.ibatis.annotations.Select;

public interface TestMapper {
    @Select("select * from test")
    Test getTest();
}
