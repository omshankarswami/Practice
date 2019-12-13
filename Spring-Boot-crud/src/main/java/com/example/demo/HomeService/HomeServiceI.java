package com.example.demo.HomeService;

import java.util.List;

import com.example.demo.model.Employee;

public interface HomeServiceI {

	public int registerData(Employee s);

	public List<Employee> getData();

	public void deleteData(int id);

	public Employee EditData(int id);

	public Employee UpdateData(Employee s);
	
	public List<Employee>checkLogin(String username,String password);

}
