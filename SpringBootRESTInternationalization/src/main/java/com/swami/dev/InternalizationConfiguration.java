package com.swami.dev;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

//@Configuration
public class InternalizationConfiguration extends WebMvcConfigurerAdapter {

	

	
	/*
	 * @Bean public ResourceBundleMessageSource messageSource() {
	 * ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
	 * rs.setBasename("messages"); rs.setUseCodeAsDefaultMessage(true); return rs; }
	 * 
	 * @RequestMapping(value = "/", method = RequestMethod.GET) public String
	 * getSource(@RequestHeader("Accept.Langauge") String locale) { return
	 * messageSource().getMessage("hello.txt", null, new Locale(locale)); }
	 */
}