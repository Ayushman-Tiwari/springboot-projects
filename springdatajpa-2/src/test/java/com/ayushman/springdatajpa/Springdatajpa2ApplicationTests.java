package com.ayushman.springdatajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ayushman.springdatajpa.entity.Student;
import com.ayushman.springdatajpa.repos.StudentRepository;

import static org.junit.Assert.*;

@SpringBootTest
class Springdatajpa2ApplicationTests {

	@Autowired
	private StudentRepository repo;

	@Test
	void testSaveStudent() {
		Student student = new Student();
		student.setId(1L);
		student.setName("Ayushman");
		student.setTestScore(100);
		repo.save(student);

		Student saveStudent = repo.findById(1L).get();
		assertNotNull(saveStudent);

	}

}
