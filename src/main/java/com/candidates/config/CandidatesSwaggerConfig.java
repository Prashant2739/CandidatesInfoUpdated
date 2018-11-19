package com.candidates.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.candidates.util.CandidatesConstants;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class CandidatesSwaggerConfig {

	@Bean
	public Docket candidateMobileApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors
						.basePackage(CandidatesConstants.BASE_PACKAGE))
				.build().apiInfo(metaData());
	}

	private ApiInfo metaData() {
		return  new ApiInfo(
				CandidatesConstants.CANDIDATES_MOBILE_API,CandidatesConstants.APP_VERSION,
				CandidatesConstants.APP_DESC, "", new Contact("","",""), CandidatesConstants.APP_HOST, CandidatesConstants.BASE_PATH);
		
	}

}
