package com.zhao.demo.mapper;

import com.zhao.demo.bean.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper
public interface StudentMapper {
    List<Student> selectAllStudent();
}
