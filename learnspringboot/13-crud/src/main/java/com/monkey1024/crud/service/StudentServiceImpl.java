package com.monkey1024.crud.service;

import com.monkey1024.crud.bean.Student;
import com.monkey1024.crud.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;


    @Override
    public List<Student> selectAllStudent() {

        return studentMapper.selectAllStudent();
    }

    @Override
    public Student selectStudentById(Integer id) {

        return studentMapper.selectStudentById(id);
    }

    @Override
    public void addStudent(Student student) {
        studentMapper.addStudent(student);

    }

    @Override
    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentMapper.deleteStudent(id);
    }
}
