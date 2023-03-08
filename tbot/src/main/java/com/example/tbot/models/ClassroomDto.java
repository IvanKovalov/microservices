package com.example.tbot.models;

import java.util.Objects;

public class ClassroomDto {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassroomDto that = (ClassroomDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
