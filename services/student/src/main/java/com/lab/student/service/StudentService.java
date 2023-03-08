package com.lab.student.service;

import com.lab.student.dto.StudentDTO;
import com.lab.student.model.Student;
import com.lab.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Student addStudent(StudentDTO studentDTO) {
        Student student = new Student(
                studentDTO.getId(),
                studentDTO.getFirstName(),
                studentDTO.getFirstName(),
                studentDTO.getPatronymic(),
                studentDTO.getGroup());

        studentRepository.save(student);
        return student;
    }

    public Student getStudentById(int id) {
        Student student = studentRepository.findById(id).get();
        return student;
    }

    public void deleteStudentById(int id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(StudentDTO studentDTO) {
        Student student = studentRepository.findById(studentDTO.getId()).get();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setPatronymic(studentDTO.getPatronymic());
        student.setGroup(studentDTO.getGroup());
        studentRepository.save(student);
        return student;
    }
}
