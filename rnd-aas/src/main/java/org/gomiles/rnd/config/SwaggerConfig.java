package org.gomiles.rnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("org.gomiles.rnd.web")).build().apiInfo(apiInfo());
	}

	ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("GoMiles").description("Track Ur Miles").license("©2019")
				.licenseUrl("http://www.google.com").termsOfServiceUrl("").version("1.1")
				.contact(new Contact("", "", "atulkumarsharma1986@gmail.com")).build();
	}
}
