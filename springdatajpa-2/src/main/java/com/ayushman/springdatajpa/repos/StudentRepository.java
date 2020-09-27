package com.ayushman.springdatajpa.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayushman.springdatajpa.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
