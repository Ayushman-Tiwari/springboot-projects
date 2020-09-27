package com.ayushman.springboot.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ayushman.springboot.thymeleaf.model.Student;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}

	/*
	 * @RequestMapping("/sendData") public ModelAndView sendData() {
	 * 
	 * ModelAndView mav = new ModelAndView(); mav.setViewName("data");
	 * mav.addObject("message", "Take up one idea and make it your life"); return
	 * mav;
	 * 
	 * }
	 */

	@RequestMapping("/sendData")
	public String sendData(ModelMap model) {

		model.addAttribute("message", "Take up one idea and make it your life");
		return "data";

	}

	@RequestMapping("/student")
	public String getStudent(ModelMap model) {

		Student student = new Student();
		student.setName("Ayushman");
		student.setScore(100);

		Student student2 = new Student();
		student2.setName("Aniruddh");
		student2.setScore(90);

		Student student3 = new Student();
		student3.setName("Nitish");
		student3.setScore(92);

		Student student4 = new Student();
		student4.setName("Rohit");
		student4.setScore(90);

		List<Student> list = new ArrayList<>();
		list.add(student);
		list.add(student2);
		list.add(student3);
		list.add(student4);

		model.addAttribute("students", list);

		return "studentList";

	}

	@RequestMapping("/studentForm")
	public String displayStudentForm(ModelMap model) {
		model.addAttribute("student", new Student());
		return "studentForm";
	}

	@RequestMapping("/saveStudent")
	public String saveStudentData(@ModelAttribute Student student, ModelMap model) {
		System.out.println(student.getName());
		System.out.println(student.getScore());
		model.addAttribute("studentSaved", student);
		return "studentForm";
	}

}
