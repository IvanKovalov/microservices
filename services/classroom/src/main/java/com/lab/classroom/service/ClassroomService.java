package com.lab.classroom.service;

import com.lab.classroom.dto.ClassroomDTO;
import com.lab.classroom.entity.ClassroomEntity;
import com.lab.classroom.repository.ClassroomRepository;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService {

    @Autowired
    ClassroomRepository classroomRepository;

    private final Logger logger = (Logger) LoggerFactory.getLogger(ClassroomService.class);

    public ClassroomEntity addClassroom(ClassroomDTO classroomDTO) {
        ClassroomEntity classroomEntity = new ClassroomEntity(classroomDTO.getId(), classroomDTO.getBuildingNum(),
                classroomDTO.getRoomNum());
        logger.info("Created new entity with id {}", classroomDTO.getId());
        classroomRepository.save(classroomEntity);
        logger.info("Saving new entity in db");
        return classroomEntity;
    }

    public ClassroomEntity getClassroomById(int id) {
        logger.info("Try to get classroom by id: {}", id);
        ClassroomEntity classroomEntity = classroomRepository.findById(id).get();
        logger.info("Got classroom with id: {}", id);
        return classroomEntity;
    }

    public void deleteClassroomById(int id) {
        logger.info("Deleting classroom with id: {}", id);
        classroomRepository.deleteById(id);
    }

    public ClassroomEntity updateClassroom(ClassroomDTO classroomDTO) {
        ClassroomEntity classroomEntity = classroomRepository.findById(classroomDTO.getId()).get();
        logger.info("Found classroom with id: {}", classroomDTO.getId());
        classroomEntity.setId(classroomDTO.getId());
        logger.info("Updated classroom building number with building number: {}", classroomDTO.getBuildingNum());
        classroomEntity.setBuildingNum(classroomDTO.getBuildingNum());
        logger.info("Updated classroom room number with room number: {}", classroomDTO.getRoomNum());
        classroomEntity.setRoomNum(classroomDTO.getRoomNum());
        classroomRepository.save(classroomEntity);
        logger.info("Updated classroomEntity with id {}", classroomDTO.getId());
        return classroomEntity;
    }
}