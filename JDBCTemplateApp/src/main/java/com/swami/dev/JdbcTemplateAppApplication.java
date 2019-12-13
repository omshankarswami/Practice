package com.swami.dev;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class JdbcTemplateAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(JdbcTemplateAppApplication.class, args);
		System.out.println("Hello");
	}
	
	/*
	 * @Bean public JdbcTemplate jdbcTemplate(DataSource dataSource) { return new
	 * JdbcTemplate(dataSource); }
	 */

}
