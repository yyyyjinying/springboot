package com.monkey1024.crud.service;

import com.monkey1024.crud.bean.Student;

import java.util.List;

public interface StudentService {

    List<Student> selectAllStudent();

    Student selectStudentById(Integer id);

    void addStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(Integer id);


}
