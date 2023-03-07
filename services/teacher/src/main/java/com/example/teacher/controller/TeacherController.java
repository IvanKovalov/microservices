package com.example.teacher.controller;

import com.example.teacher.models.TeacherDTO;
import com.example.teacher.models.TeacherEntity;
import com.example.teacher.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeacherController {

    private final Logger logger = LoggerFactory.getLogger("Logger");

    @Autowired
    TeacherService teacherService;


    //@Operation(summary = "Add new teacher")

    @PostMapping("/teacher")
    public ResponseEntity<TeacherEntity> addTeacher (@RequestBody TeacherDTO teacherDTO) {
        logger.info("Received POST request on creating new teacher");
        TeacherEntity teacherEntity = teacherService.addTeacher(teacherDTO);
        logger.info("New teacher saved");
        return ResponseEntity.ok(teacherEntity);
    }

    //@Operation(summary = "Get schedule by id")
    @GetMapping("/teacher/{id}")
    public ResponseEntity<TeacherEntity> getTeacherById (@PathVariable int id) {
        logger.info("Received GET request on getting teacher with id: {}", id);
        TeacherEntity teacherEntity = teacherService.getTeacherById(id);
        logger.info("Returned teacher with id: {}", id);
        return ResponseEntity.ok(teacherEntity);
    }

    //@Operation(summary = "Delete schedule by id")
    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<Integer> deleteTeacherById (@PathVariable int id) {
        logger.info("Received DELETE request on deleting teacher with id: {}", id);
        teacherService.deleteTeacherById(id);
        logger.info("Deleted teacher with id: {}", id);
        return ResponseEntity.ok(id);
    }

    //@Operation(summary = "Update schedule")
    @PutMapping
    public ResponseEntity<TeacherEntity> updateTeacher (TeacherDTO teacherDTO) {
        logger.info("Received PUT request on updating teacher with id: {}", teacherDTO.getId());
        TeacherEntity teacherEntity = teacherService.updateTeacher(teacherDTO);
        logger.info("Updated teacher with id: {}", teacherDTO.getId());
        return ResponseEntity.ok(teacherEntity);
    }






}
