package com.lab.classroom.controller;

import com.lab.classroom.dto.ClassroomDTO;
import com.lab.classroom.entity.ClassroomEntity;
import com.lab.classroom.service.ClassroomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public  class ClassroomController {
    private final Logger logger = LoggerFactory.getLogger("Logger");
    @Autowired
    ClassroomService classroomService;

    @PostMapping("/clroom")
    public ResponseEntity<ClassroomEntity> addClassroom (@RequestBody ClassroomDTO classroomDTO) {
        logger.info("Received POST request on creating a new classroom");
        ClassroomEntity classroomEntity = classroomService.addClassroom(classroomDTO);
        logger.info("New classroom saved");
        return ResponseEntity.ok(classroomEntity);
    }

    @GetMapping("/clroom/{id}")
    public ResponseEntity<ClassroomEntity> getClassroomById (@PathVariable int id) {
        logger.info("Received GET request on getting a classroom with id: {}", id);
        ClassroomEntity classroomEntity = classroomService.getClassroomById(id);
        logger.info("Returned classroom with id: {}", id);
        return ResponseEntity.ok(classroomEntity);
    }
//aa
    @DeleteMapping("/clroom/{id}")
    public ResponseEntity<Integer> deleteClassroomById (@PathVariable int id) {
        logger.info("Received DELETE request on deleting a classroom with id: {}", id);
        classroomService.deleteClassroomById(id);
        logger.info("Deleted classroom with id: {}", id);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/clroom")
    public ResponseEntity<ClassroomEntity> updateClassroom (@RequestBody ClassroomDTO classroomDTO) {
        logger.info("Received PUT request on updating a classroom with id: {}", classroomDTO.getId());
        ClassroomEntity classroomEntity = classroomService.updateClassroom(classroomDTO);
        logger.info("Updated classroom with id: {}", classroomDTO.getId());
        return ResponseEntity.ok(classroomEntity);
    }

    @PostMapping(value = "/clroom", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<ClassroomEntity> addClassroomByTelegram (ClassroomDTO classroomDTO) {
        logger.info("Received POST request on creating a new classroom");
        ClassroomEntity classroomEntity = classroomService.addClassroom(classroomDTO);
        logger.info("New classroom saved");
        return ResponseEntity.ok(classroomEntity);
    }
}