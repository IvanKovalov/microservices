package com.lab.classroom.repository;

import com.lab.classroom.entity.ClassroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<ClassroomEntity, Integer> {

}