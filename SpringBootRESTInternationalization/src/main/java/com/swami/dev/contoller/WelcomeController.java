package com.swami.dev.contoller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
	
	
	 @RequestMapping("/")
	    public String hello() {
	        return "hello.html";
	    }
	 
	 
	/*
	 * @RequestMapping("/hello") public String
	 * getSource(@RequestHeader("Accept.Langauge") String locale) { return
	 * messageSource().getMessage("hello.txt", null, new Locale(locale)); }
	 */

}
