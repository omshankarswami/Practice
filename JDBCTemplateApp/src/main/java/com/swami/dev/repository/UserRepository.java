package com.swami.dev.repository;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public List<String> getAllUserNames() {
		
		List<String> list =new ArrayList<String>();
		
		list.addAll(
		jdbcTemplate.queryForList("SELECT username FROM test.user;", String.class));
	
		return list;
	}

}
