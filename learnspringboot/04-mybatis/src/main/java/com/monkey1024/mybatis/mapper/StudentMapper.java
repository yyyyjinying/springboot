package com.monkey1024.mybatis.mapper;

import com.monkey1024.mybatis.bean.Student;
//import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper//可以被spring boot扫描到
public interface StudentMapper {
    List<Student> selectAllStudent();
}
