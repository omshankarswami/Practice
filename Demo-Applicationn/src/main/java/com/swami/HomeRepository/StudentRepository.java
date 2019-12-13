package com.swami.HomeRepository;
import com.swami.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	//Student save(Student student);

}
