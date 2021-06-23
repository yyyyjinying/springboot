package com.zhao.demo.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("m_user")
public class Muser {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
