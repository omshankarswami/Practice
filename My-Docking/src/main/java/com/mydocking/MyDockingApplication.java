package com.mydocking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.mydocking.model.FileStorageProperties;

@EnableConfigurationProperties({
    FileStorageProperties.class
})
@EnableJpaAuditing
@SpringBootApplication
public class MyDockingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyDockingApplication.class, args);
	}
	
	/*
	 * 
	 * @Bean public FilterRegistrationBean filterRegistrationBean() {
	 * FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	 * CharacterEncodingFilter characterEncodingFilter = new
	 * CharacterEncodingFilter(); characterEncodingFilter.setForceEncoding(true);
	 * characterEncodingFilter.setEncoding("UTF-8");
	 * registrationBean.setFilter(characterEncodingFilter); return registrationBean;
	 * }
	 */

}


