package com.bjsxt.mapper;

import com.bjsxt.pojo.Rusers;
import org.apache.ibatis.annotations.Insert;

public interface RusersMapper {

    @Insert("insert into rusers values (default,#{username},#{password},#{photo})")
    int insRusers(Rusers rusers);
}
