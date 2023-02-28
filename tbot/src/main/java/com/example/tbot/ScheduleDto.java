package com.example.tbot;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleDto {

    private int teacherId;

    private int studentId;

    private int classId;

    private String subject;

    //private LocalDateTime meetingTime;


}
