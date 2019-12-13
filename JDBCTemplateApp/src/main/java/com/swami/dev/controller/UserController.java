package com.swami.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swami.dev.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	
	@RequestMapping(value = "/User")
	public String check()
	{
		return  "Hey... ";
	}

	@RequestMapping(value = "/UserList")
	public List<String> getAllUserNames()
	{
		return userRepository.getAllUserNames();
	}
	
}
