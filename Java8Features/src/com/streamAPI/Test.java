package com.streamAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
	
	
	
	public static void main(String[] args) {
		
		
		List<Integer> l=new ArrayList<Integer>();
		
		
		l.add(0);
		l.add(5);
		l.add(10);
		l.add(15);
		l.add(20);
		
		System.out.println(l);
		
		
		List<Integer> list=l.stream().filter(i->i%2==0).collect(Collectors.toList());
	
		System.out.println(list);
		
		
		List<Integer> list1=l.stream().map(i->i+5).collect(Collectors.toList());
		System.out.println(list1);
	}

}
