package com.bjsxt.pojo;

import java.util.List;

public class ListDemo {
    private List<Users> list;

    public List<Users> getList() {
        return list;
    }

    public void setList(List<Users> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ListDemo{" +
                "list=" + list +
                '}';
    }
}
