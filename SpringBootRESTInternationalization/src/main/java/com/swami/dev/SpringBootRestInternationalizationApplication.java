package com.swami.dev;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
@RestController
public class SpringBootRestInternationalizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestInternationalizationApplication.class, args);
	}
	
	@Bean 
	public ResourceBundleMessageSource messageSource() {
		  ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
		  rs.setBasename("messages"); rs.setUseCodeAsDefaultMessage(true); return rs; }
	


	@Bean
	public org.springframework.web.servlet.LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		localeChangeInterceptor.setIgnoreInvalidLocale(true);
		return localeChangeInterceptor;
	}

	private void addIntercepter(InterceptorRegistry interceptorRegistry) {
		interceptorRegistry.addInterceptor(localeChangeInterceptor());
	}
	
}