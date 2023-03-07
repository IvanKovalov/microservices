package com.example.teacher.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class TeacherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;//вчитель

    //@NotBlank(message = "Subject must have name")
    // @Size(max = 255, message = "Max size 255")
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


    public TeacherEntity(int id, String name, String surname, String fathername, String posada){
        this.id=id;
        this.name =name;
        this.surname =surname;
        this.fatherName =fathername;
        this.posada =posada;
    }

    public TeacherEntity(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherEntity that = (TeacherEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
