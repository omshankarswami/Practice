package com.optional;

//Java program to show NullPointerException 

public class Example { 
	public static void main(String[] args) 
	{ 

		// Create a String of size 10 
		String[] a = new String[10]; 

		// The String is empty 
		// So a[1] will have null at present 
		String upcase = a[1].toUpperCase(); 

		System.out.print(upcase); 
	} 
} 
