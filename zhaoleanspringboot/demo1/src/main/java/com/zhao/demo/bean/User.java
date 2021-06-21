package com.zhao.demo.bean;

import lombok.Data;

import java.sql.Date;

@Data
public class User {
    private Integer id;
    private String name;
    private String phone;
    private String address;
    private Date birthday;
}
