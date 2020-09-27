package com.ayushman.springweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ayushman.springweb.controller"))
				.paths(PathSelectors.regex("/products.*"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Product API")
				.description("Product CRUD Operations")
				.termsOfServiceUrl("Open Source")
				.license("Ayushman's License")
				.version("2.0")
				.build();
	}
	
}
