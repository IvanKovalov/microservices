package com.example.teacher.service;

import ch.qos.logback.classic.Logger;
import com.example.teacher.models.TeacherDTO;
import com.example.teacher.models.TeacherEntity;
import com.example.teacher.repository.TeacherRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;
    private final Logger logger = (Logger) LoggerFactory.getLogger(TeacherService.class);

    public TeacherEntity addTeacher(TeacherDTO teacherDTO) {
        TeacherEntity teacherEntity = new TeacherEntity(teacherDTO.getId(),
                teacherDTO.getName(), teacherDTO.getSurname(), teacherDTO.getFatherName(),
                teacherDTO.getPosada());
        logger.info("Created new entity with id {}", teacherDTO.getId());
        teacherRepository.save(teacherEntity);
        logger.info("Saving new entity in db");
        return teacherEntity;
    }

    public TeacherEntity getTeacherById(int id) {
        logger.info("Try to get teacher by id: {}", id);
        TeacherEntity teacherEntity = teacherRepository.findById(id).get();
        logger.info("Got teacher with id: {}", id);
        return teacherEntity;
    }

    public void deleteTeacherById(int id) {
        logger.info("Deleting teacher with id: {}", id);
        teacherRepository.deleteById(id);
    }

    public TeacherEntity updateTeacher(TeacherDTO teacherDTO) {
        TeacherEntity teacherEntity = teacherRepository.findById(teacherDTO.getId()).get();

        logger.info("Found teacher with id: {}", teacherDTO.getId());
        teacherEntity.setId(teacherDTO.getId());

        logger.info("Updated teacher's name with name: {}", teacherDTO.getName());
        teacherEntity.setName(teacherDTO.getName());

        logger.info("Updated teacher's surname with surname: {}", teacherDTO.getSurname());
        teacherEntity.setSurname(teacherDTO.getSurname());

        logger.info("Updated teacher's father name by father name: {}", teacherDTO.getFatherName());
        teacherEntity.setFatherName(teacherDTO.getFatherName());

        logger.info("Updated teacher's posada by posada: {}", teacherDTO.getPosada());
        teacherEntity.setPosada(teacherDTO.getPosada());


        teacherRepository.save(teacherEntity);
        logger.info("Updated teacherEntity with id {}", teacherDTO.getId());
        return teacherEntity;
    }


}
