package com.lab.schedule.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

public class ScheduleDTO {

    private int id;
    @NotNull
    @Min(value = 0, message = "Value must be > 0")
    private int teacherId;
    @NotNull
    @Min(value = 0, message = "Value must be > 0")
    private int studentId;
    @NotNull
    @Min(value = 0, message = "Value must be > 0")
    private int classId;
    @NotBlank(message = "Subject must have name")
    @Size(max = 255, message = "Max size 255")
    private String subject;
    @NotBlank(message = "U must to choose date")
    private LocalDateTime meetingTime;

    public int getId() {
        return id;
    }

    public int getClassId() {
        return classId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public LocalDateTime getMeetingTime() {
        return meetingTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void setMeetingTime(CharSequence meetingTime) {
        this.meetingTime = LocalDateTime.parse(meetingTime);
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleDTO that = (ScheduleDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
