package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.HomeService.HomeServiceI;
import com.example.demo.model.Employee;
import com.example.demo.repository.HomeRepository;

@RestController

public class HomeController {
	
	@Autowired
    HomeRepository repository;
	
	// Get All Emplyee
	@GetMapping("/Employees")
	public List<Employee> getAllNotes() {
	    return repository.findAll();
	}
	
	
	// Create a new Employee
	@PostMapping("/CreateEmployees")
	public Employee createNote( @RequestBody Employee employee) {
	    return repository.save(employee);
	}
	

	 
	// Get a Single Employee
	@GetMapping("/Employees/{id}")
	public Employee getNoteById(@PathVariable(value = "id") int  id) {
	    return repository.findById(id).orElse(null);
	} 
	
	
	// Update a Employee
	@PutMapping("/Employees/{id}")
	public Employee updateNote(@PathVariable(value = "id") int  id,
	                                         @RequestBody Employee empDetails) {
 System.out.println(id);
		Employee emp = repository.findById(id).orElse(null);
	    emp.setEmail(empDetails.getEmail());
	    emp.setName(empDetails.getName());

	    Employee updated = repository.save(emp);
	    return updated;
	}
	
	 
	// Delete a Employee
	@DeleteMapping("/Employees/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") int id) {
		Employee emp = repository.findById(id).orElse(null);
		repository.delete(emp);

	    return ResponseEntity.ok().build();
	}

}

