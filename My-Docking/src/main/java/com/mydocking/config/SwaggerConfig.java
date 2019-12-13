package com.mydocking.config;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicate;

import springfox.documentation.builders.AlternateTypeBuilder;
import springfox.documentation.builders.AlternateTypePropertyBuilder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private final TypeResolver typeResolver;
	private final RepositoryRestConfiguration restConfiguration;

	public SwaggerConfig(TypeResolver typeResolver, RepositoryRestConfiguration restConfiguration) {
		this.typeResolver = typeResolver;
		this.restConfiguration = restConfiguration;
	}

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.alternateTypeRules(newRule(typeResolver.resolve(Pageable.class), pageableMixin(restConfiguration),
						Ordered.HIGHEST_PRECEDENCE))
				.groupName("MY-DOCKING-API").apiInfo(apiInfo()).select().paths(postPaths())
				.apis(RequestHandlerSelectors.basePackage("com.mydocking.controller")).build()		
		  .useDefaultResponseMessages(false) .globalResponseMessage(RequestMethod.GET,
		  responseForGetOrDeleteMessageList())
		  .globalResponseMessage(RequestMethod.DELETE,
		  responseForDeleteMessageList())
		  .globalResponseMessage(RequestMethod.POST,
		  responseForPostOrUpdateMessageList())
		  .globalResponseMessage(RequestMethod.PUT,
		  responseForPostOrUpdateMessageList());
		 
	}

	private Predicate<String> postPaths() {
		return regex("/.*");
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("MY-DOCKING API").description("MY-DOCKING API reference for developers")
				.licenseUrl("http://MY-DOCKING.com").license("MY-DOCKING.com").version("1.0").build();
	}

	private Type pageableMixin(RepositoryRestConfiguration restConfiguration) {
		return new AlternateTypeBuilder()
				.fullyQualifiedClassName(String.format("%s.generated.%s", Pageable.class.getPackage().getName(),
						Pageable.class.getSimpleName()))
				.withProperties(Arrays.asList(property(Integer.class, restConfiguration.getPageParamName()),
						property(Integer.class, restConfiguration.getLimitParamName()),
						property(String.class, restConfiguration.getSortParamName())))
				.build();
	}

	private AlternateTypePropertyBuilder property(Class<?> type, String name) {
		return new AlternateTypePropertyBuilder().withName(name).withType(type).withCanRead(true).withCanWrite(true);
	}

	private List<ResponseMessage> responseForPostOrUpdateMessageList() {
		return newArrayList(
				new ResponseMessageBuilder().code(500).message("Server Error").responseModel(new ModelRef("string"))
						.build(),
				new ResponseMessageBuilder().code(400).message("Bad Request").build(),
				new ResponseMessageBuilder().code(409).message("Conflict | Duplicate Value").build(),
				new ResponseMessageBuilder().code(404).message("Not Found").build(),
				new ResponseMessageBuilder().code(204).message("No Content").build(),
				new ResponseMessageBuilder().code(401).message("Unauthorized").build());
	}

	private List<ResponseMessage> responseForGetOrDeleteMessageList() {
		return newArrayList(
				new ResponseMessageBuilder().code(500).message("Server Error").responseModel(new ModelRef("string"))
						.build(),
				new ResponseMessageBuilder().code(400).message("Bad Request").build(),
				new ResponseMessageBuilder().code(404).message("Not Found").build(),
				new ResponseMessageBuilder().code(401).message("Unauthorized").build());
	}
	
	private List<ResponseMessage> responseForDeleteMessageList() {
		return newArrayList(
				new ResponseMessageBuilder().code(500).message("Server Error").responseModel(new ModelRef("string"))
						.build(),
				new ResponseMessageBuilder().code(400).message("Bad Request").build(),
				new ResponseMessageBuilder().code(404).message("Not Found").build(),
				new ResponseMessageBuilder().code(204).message("No Content").build(),
				new ResponseMessageBuilder().code(401).message("Unauthorized").build());
	}	
}