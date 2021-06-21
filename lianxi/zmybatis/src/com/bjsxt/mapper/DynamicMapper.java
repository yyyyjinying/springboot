package com.bjsxt.mapper;

import com.bjsxt.pojo.People;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DynamicMapper {

    int upd(People people);
    int ins(List<String> list);
    List<People> sels();
    List<People> selByDynamic(List<Integer> list);

    List<People> selectByValues(@Param("name") String name, @Param("age") String age);
}
