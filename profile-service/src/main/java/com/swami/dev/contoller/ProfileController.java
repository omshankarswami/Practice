package com.swami.dev.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swami.dev.model.Customer;
import com.swami.dev.repository.CustomerRepository;
import com.swami.dev.service.CustomerServiceImpl;

@RestController
@RequestMapping(value="/services")
public class ProfileController {
	
	@Autowired
	CustomerServiceImpl customerServiceImpl;
	public Customer save(@RequestBody Customer customer)
	{
		return customerServiceImpl.save(customer);
	}
	
	
	
	
	

}