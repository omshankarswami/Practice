package com.optional;

import java.util.Optional;

public class Demo {

	
	
	public static void main(String[] args) {
		
	
	
	String nullName = "xcvxz";
	String name = Optional.ofNullable(nullName).orElseThrow(NullPointerException::new);
	
	
	System.out.println(name);
	}
}
