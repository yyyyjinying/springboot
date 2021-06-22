package com.zhao.demo.bean;

import lombok.Data;

import java.util.List;

@Data
public class Users {
    private Integer id;
    private String username;
    private String password;

    private List<Menu> menus;
}
