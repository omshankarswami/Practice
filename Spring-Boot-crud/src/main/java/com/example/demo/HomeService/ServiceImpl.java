package com.example.demo.HomeService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.HomeRepository;

@Service
public class ServiceImpl  implements HomeServiceI
{
	@Autowired
	HomeRepository hr;

	@Override
	public int registerData(Employee s)
	{
		hr.save(s);
		return 1;
	}

	@Override
	public List<Employee> getData() {
		List<Employee>list=(List<Employee>) hr.findAll();
		return list;
	}

	@Override
	public void deleteData(int id) {
		hr.deleteById(id);
	}

	@Override
	public Employee EditData(int id){
		Optional<Employee> st=hr.findById(id);
		Employee s=st.get();
		return s;
		
	}

	@Override
	public Employee UpdateData(Employee s) {
		// TODO Auto-generated method stub
		Employee updata=hr.save(s);
		return updata;
	}

	@Override
	public List<Employee> checkLogin(String username, String password) {
		//List<Employee>list=hr.findAllByUsernameAndPassword(username, password);
		return null;
	}


}
