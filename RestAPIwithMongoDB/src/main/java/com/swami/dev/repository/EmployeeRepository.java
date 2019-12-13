package com.swami.dev.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.swami.dev.model.Employee;
@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String>{
	
	
	

}