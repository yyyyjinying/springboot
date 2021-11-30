package com.changgou.user.dao;

import com.changgou.user.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {
    // 痛殴用户获取角色
    @Select("select * from `tb_role` where `id` in (select role_id from `tb_user_role` where username=#{username})")
    public List<Role> getRoleList(String username);
}
