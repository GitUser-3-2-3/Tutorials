package com.project.sec.controller;

import com.project.sec.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final List<Student> studentList = new ArrayList<>(List.of(
        new Student(1, "Alice", 100),
        new Student(2, "Bob", 90),
        new Student(3, "Charlie", 80)
    ));

    @GetMapping
    public List<Student> getAllStudents() {
        return studentList;
    }

    @GetMapping("/getToken")
    public CsrfToken getToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        if (student != null) {
            studentList.add(student);
        }
        return student;
    }
}







