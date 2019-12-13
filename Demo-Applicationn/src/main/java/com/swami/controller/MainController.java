package com.swami.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swami.model.Student;
import com.swami.service.StudentServiceImpl;

@RestController
public class MainController {
	
	@Autowired
	StudentServiceImpl ser;
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String greeting()
	{
		return "Hello Spring Boot...";
	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.POST)
	public String greeting2()
	{
		return "Hello Spring Boot...POST";
	}
	

	@RequestMapping(value = "/student", method = RequestMethod.POST )
	public Student save(@RequestBody Student student)
	{
		return ser.save(student);

	}
	
	@RequestMapping(value = "/student", method = RequestMethod.GET )
	public ResponseEntity<Student> fetchStudent(@RequestParam int id)
	{
		Student student=ser.findById(id);
		if(student==null) {
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(student);
		}
	}
	

}
