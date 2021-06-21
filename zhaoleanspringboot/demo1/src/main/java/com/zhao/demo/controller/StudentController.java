package com.zhao.demo.controller;

import com.zhao.demo.bean.Student;
import com.zhao.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("selectStudent")
    @ResponseBody
    public List<Student> selectStudent(){
        return studentService.selectAllStudent();
    }
}
