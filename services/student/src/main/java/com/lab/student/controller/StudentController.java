package com.lab.student.controller;

import com.lab.student.dto.StudentDTO;
import com.lab.student.model.Student;
import com.lab.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity<Student> addStudent(StudentDTO studentDTO) {
        Student student = studentService.addStudent(studentDTO);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<Integer> deleteStudentById(@PathVariable int id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.ok(id);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(StudentDTO studentDTO) {
        Student student = studentService.updateStudent(studentDTO);
        return ResponseEntity.ok(student);
    }
}
