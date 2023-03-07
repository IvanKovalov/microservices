package com.example.tbot.models;

import java.util.Objects;

public class TeacherDto {
    private int id;//вчитель

    private String name;
    private String surname;
    private String fatherName;
    private String posada;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getPosada() {
        return posada;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setPosada(String posada) {
        this.posada = posada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherDto that = (TeacherDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
