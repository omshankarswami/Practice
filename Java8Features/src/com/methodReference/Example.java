package com.methodReference;




@FunctionalInterface 
interface MyInterface{  
    void display(int i);  
}  


public class Example {  
    public void myMethod(int i ){  
    	
	System.out.println("Instance Method");  
	
    }  

    public static void main(String[] args) {  
	Example obj = new Example();   
	// Method reference using the object of the class
	MyInterface ref = obj::myMethod;  
	// Calling the method of functional interface  
	ref.display(0);  
    }  
}
