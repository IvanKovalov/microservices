package com.lab.schedule.controller;


import com.lab.schedule.dto.ScheduleDTO;
import com.lab.schedule.entity.ScheduleEntity;
import com.lab.schedule.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ScheduleController {

    private final Logger logger = LoggerFactory.getLogger("Logger");

    @Autowired
    ScheduleService scheduleService;


    @Operation(summary = "Add new schedule")
    @PostMapping("/schedule")
    public ResponseEntity<ScheduleEntity> addSchedule (@RequestBody ScheduleDTO scheduleDTO) {
        logger.info("Received POST request on creating new schedule");
        ScheduleEntity scheduleEntity = scheduleService.addSchedule(scheduleDTO);
        logger.info("New schedule saved");
        return ResponseEntity.ok(scheduleEntity);
    }

    @Operation(summary = "Get schedule by id")
    @GetMapping("/schedule/{id}")
    public ResponseEntity<ScheduleEntity> getScheduleById (@PathVariable int id) {
        logger.info("Received GET request on getting schedule with id: {}", id);
        ScheduleEntity scheduleEntity = scheduleService.getScheduleById(id);
        logger.info("Returned schedule with id: {}", id);
        return ResponseEntity.ok(scheduleEntity);
    }

    @Operation(summary = "Delete schedule by id")
    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<Integer> deleteScheduleById (@PathVariable int id) {
        logger.info("Received DELETE request on deleting schedule with id: {}", id);
        scheduleService.deleteScheduleById(id);
        logger.info("Deleted schedule with id: {}", id);
        return ResponseEntity.ok(id);
    }

    @Operation(summary = "Update schedule")
    @PutMapping("/schedule")
    public ResponseEntity<ScheduleEntity> updateSchedule (@RequestBody ScheduleDTO scheduleDTO) {
        logger.info("Received PUT request on updating schedule with id: {}", scheduleDTO.getId());
        ScheduleEntity scheduleEntity = scheduleService.updateSchedule(scheduleDTO);
        logger.info("Updated schedule with id: {}", scheduleDTO.getId());
        return ResponseEntity.ok(scheduleEntity);
    }

    @PostMapping(value = "/schedule", consumes = "application/x-www-form-urlencoded")
    public  ResponseEntity<ScheduleEntity> addScheduleByTelegram (ScheduleDTO scheduleDTO) {
        logger.info("Received POST request on creating new schedule");
        ScheduleEntity scheduleEntity = scheduleService.addSchedule(scheduleDTO);
        logger.info("New schedule saved");
        return ResponseEntity.ok(scheduleEntity);
    }


}
