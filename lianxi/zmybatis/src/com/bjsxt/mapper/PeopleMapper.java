package com.bjsxt.mapper;

import com.bjsxt.pojo.People;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PeopleMapper {
    List<People> selAll();
    int update(@Param("name") String name12, @Param("id") Integer id);
}
