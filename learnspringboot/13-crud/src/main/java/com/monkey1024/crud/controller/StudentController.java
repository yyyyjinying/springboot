package com.monkey1024.crud.controller;

import com.monkey1024.crud.bean.Student;
import com.monkey1024.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 查询全部数据
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        List<Student> studentList = studentService.selectAllStudent();
        model.addAttribute("studentList", studentList);

        return "index";
    }


    /**
     * 去修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/student/toUpdate")
    public String toUpdate(@RequestParam(value = "id") Integer id,Model model) {

        Student student = studentService.selectStudentById(id);

        model.addAttribute("student", student);

        return "student";

    }

    /**
     * 去添加页面
     * @return
     */
    @RequestMapping("/student/toAdd")
    public String toAdd(){
        return "student";
    }

    /**
     * 添加或修改
     * @param student
     * @return
     */
    @RequestMapping("/student/addOrUpdate")
    public String addOrUpdate(Student student) {
        if (student.getId() == null){
            //添加
            studentService.addStudent(student);
        }else {
            //修改
            studentService.updateStudent(student);
        }

        return "redirect:/index";
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/student/delete")
    public String delete(@RequestParam("id") Integer id){
        studentService.deleteStudent(id);

        return "redirect:/index";
    }
}
