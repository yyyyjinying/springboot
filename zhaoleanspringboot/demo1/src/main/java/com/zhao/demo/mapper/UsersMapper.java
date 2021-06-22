package com.zhao.demo.mapper;

import com.zhao.demo.bean.Users;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UsersMapper {

    @Results(value={
            @Result(column = "id", property = "id",id=true),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(property = "menus", many=@Many(select="com.zhao.demo.mapper.MenuMapper.selByPid"), column = "{uid=id,pid=pid}")

    })
    @Select("select *,0 pid from users where username=#{username} and password=#{password}")
    Users selByUsers(Users users);
}
