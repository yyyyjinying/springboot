package com.bjsxt.mapper;

import com.bjsxt.pojo.Menu;

import java.util.List;

public interface MenuMapper {
    List<Menu> selByPid(int pid);
}
