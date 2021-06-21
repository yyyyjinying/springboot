package com.monkey1024.crud.mapper;

import com.monkey1024.crud.bean.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper//在dao接口上加入Mapper注解，这样springboot就可以扫描到
public interface StudentMapper {
    List<Student> selectAllStudent();

    Student selectStudentById(Integer id);

    int addStudent(Student student);

    int updateStudent(Student student);

    int deleteStudent(Integer id);
}
