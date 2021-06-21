package com.bjsxt.mapper;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.bjsxt.pojo.Users;

public interface UsersMapper {
	
	@Results(value={
			@Result(id=true,column="id",property="id"),
			@Result(column="username",property="username"),
			@Result(column="password",property="password"),
			@Result(property="menus",many=@Many(select="com.bjsxt.mapper.MenuMapper.selByPid"),column="{uid=id,pid=pid}")
			})
	//如果需要传递过个参数 column="{"key"=列名,"key"=列名}"  key自定义
	//另一个查询中获取传递过来的参数  #{key}
	//另一个查询public void select(Map<String,Object> map);
	@Select("select *,0 pid from users where username=#{username} and password=#{password}")
	Users selByUsers(Users users);
}
