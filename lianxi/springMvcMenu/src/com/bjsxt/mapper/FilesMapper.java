package com.bjsxt.mapper;

import com.bjsxt.pojo.Files;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface FilesMapper {
    @Select("select * from files")
    List<Files> show();


    @Update("update files set count=count+1 where id=#{0} and name=#{1}")
    int updCount(int id,String name);
}
