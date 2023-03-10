package com.lab.schedule.service;


import com.lab.schedule.dto.ScheduleDTO;
import com.lab.schedule.entity.ScheduleEntity;
import com.lab.schedule.repository.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;
    private final Logger logger = LoggerFactory.getLogger("Logger");

    public ScheduleEntity addSchedule(ScheduleDTO scheduleDTO) {
        ScheduleEntity scheduleEntity = new ScheduleEntity(scheduleDTO.getTeacherId(),
                scheduleDTO.getStudentId(), scheduleDTO.getClassId(), scheduleDTO.getSubject(),
                scheduleDTO.getMeetingTime());
        logger.info("Created new entity with id {}", scheduleEntity.getId());
        scheduleRepository.save(scheduleEntity);
        logger.info("Saving new entity in db");
        return scheduleEntity;
    }
    
    public ScheduleEntity getScheduleById(int id) {
        logger.info("Try to get schedule by id: {}", id);
        ScheduleEntity scheduleEntity = scheduleRepository.findById(id).get();
        logger.info("Got schedule with id: {}", id);
        return scheduleEntity;
    }

    public void deleteScheduleById(int id) {
        logger.info("Deleting schedule with id: {}", id);
        scheduleRepository.deleteById(id);
    }

    public ScheduleEntity updateSchedule(ScheduleDTO scheduleDTO) {
        ScheduleEntity scheduleEntity = scheduleRepository.findById(scheduleDTO.getId()).get();
        logger.info("Found schedule with id: {}", scheduleDTO.getId());
        scheduleEntity.setTeacherId(scheduleDTO.getTeacherId());
        logger.info("Updated schedule's teacher id with id: {}", scheduleDTO.getTeacherId());
        scheduleEntity.setClassId(scheduleDTO.getClassId());
        logger.info("Updated schedule's classId with id: {}", scheduleDTO.getClassId());
        scheduleEntity.setMeetingTime(scheduleDTO.getMeetingTime());
        logger.info("Updated schedule's meetingTime by new time: {}", scheduleDTO.getMeetingTime());
        scheduleEntity.setStudentId(scheduleDTO.getStudentId());
        logger.info("Updated schedule's student id with id: {}", scheduleDTO.getStudentId());
        scheduleRepository.save(scheduleEntity);
        logger.info("Updated scheduleEntity with id {}", scheduleDTO.getId());
        return scheduleEntity;
    }
}
