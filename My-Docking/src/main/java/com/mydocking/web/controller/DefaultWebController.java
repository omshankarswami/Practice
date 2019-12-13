package com.mydocking.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DefaultWebController {
	
	@RequestMapping({"/"}) 
	public String index() { 
		return "redirect:/web/adminhome"; 
	}
	
	@RequestMapping({"/web/adminhome"}) 
	public String homePage() { 
		return "adminhome"; 
	}
}