package com.lab.classroom.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class ClassroomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int buildingNum;
    private int roomNum;

    public int getId() {
        return id;
    }

    public int getBuildingNum() {
        return buildingNum;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setBuildingNum(int buildingNum) {
        this.buildingNum = buildingNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClassroomEntity(int id, int buildingNum, int roomNum){
        this.buildingNum = buildingNum;
        this.roomNum = roomNum;
        this.id = id;
    }

    public ClassroomEntity() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassroomEntity that = (ClassroomEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}