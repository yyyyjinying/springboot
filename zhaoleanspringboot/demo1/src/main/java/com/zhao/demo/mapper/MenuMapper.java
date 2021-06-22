package com.zhao.demo.mapper;

import com.zhao.demo.bean.Menu;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface MenuMapper {
    @Results(value={
        @Result(column = "id", property = "id", id = true),
        @Result(column = "name", property = "name"),
        @Result(column = "pid", property = "pid"),
        @Result(property = "children", many=@Many(select="selByPid"), column = "{uid=uid,pid=id}")

    })
    @Select("select *,#{uid} uid from menu where id in (select mid from users_menu where uid=#{uid}) and pid=#{pid}")
    List<Menu> selByPid(Map<String, Object> map);
}
