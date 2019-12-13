package com.swami.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swami.dev.model.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	
	

}
