package com.swami.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swami.HomeRepository.StudentRepository;
import com.swami.model.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
   StudentRepository studentRepository;
	@Override
	public Student save(Student student) {
		return studentRepository.save(student);
	}
	
	public Student findById(int id) {

		Optional<Student> op=studentRepository.findById(id);
		if(op.isPresent())
		{
			return op.get();
		}
		return null;
	}

}
