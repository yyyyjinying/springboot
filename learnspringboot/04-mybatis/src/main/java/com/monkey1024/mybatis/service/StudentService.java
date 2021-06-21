package com.monkey1024.mybatis.service;

import com.monkey1024.mybatis.bean.Student;

import java.util.List;

public interface StudentService {
    List<Student> selectAllStudent();
}
