package com.maolong.mapper;

import com.maolong.pojo.entity.DBLog;
import com.maolong.pojo.entity.WebLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {
    @Insert("insert into web_log(url, http_method, class_method, ip, args, time_cost,user_name,visit_time) " +
            "values(#{url}, #{httpMethod}, #{classMethod}, #{ip}, #{args}, #{timeCost},#{userName}, #{visitTime})")
    void insertWeb(WebLog webLog);

    @Insert("insert into db_log(method, time_cost, visit_time) values(#{method}, #{timeCost}, #{visitTime})")
    void insertDB(DBLog dbLog);
}
