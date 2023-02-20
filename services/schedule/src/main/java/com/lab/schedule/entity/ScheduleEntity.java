package com.lab.schedule.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int teacherId;
    private int studentId;
    private int classId;
    private String subject;
    @Basic
    private LocalDateTime meetingTime;


    public ScheduleEntity(int teacherId, int studentId, int classId, String subject, LocalDateTime meetingTime) {
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.classId = classId;
        this.subject = subject;
        this.meetingTime = meetingTime;
    }

    public ScheduleEntity() {

    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setMeetingTime(LocalDateTime meetingTime) {
        this.meetingTime = meetingTime;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getSubject() {
        return subject;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public LocalDateTime getMeetingTime() {
        return meetingTime;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getClassId() {
        return classId;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleEntity that = (ScheduleEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
