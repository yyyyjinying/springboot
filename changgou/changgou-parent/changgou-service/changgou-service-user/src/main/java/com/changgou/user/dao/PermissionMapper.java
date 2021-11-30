package com.changgou.user.dao;


import com.changgou.user.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper {
    //    通过用户获取权限信息
    @Select("select * from `tb_permission` where `id` in(select permission_id from `tb_role_permission` where `role_id` in (select role_id from `tb_user_role` where username=#{username}))")
    List<Permission> getPermissionByUsername(String username);
}
