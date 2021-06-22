package com.zhao.demo.bean;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    private Integer id;
    private String name;
    private Integer pid;
    private List<Menu> children;
}
