package com.swami.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swami.dev.model.Customer;
import com.swami.dev.repository.CustomerRepository;

public class CustomerServiceImpl implements CustomerService{

	
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Customer save(Customer customer) {
		
		return customerRepository.save(customer);
	}

}
