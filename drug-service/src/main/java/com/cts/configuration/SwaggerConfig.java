package com.cts.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2

public class SwaggerConfig
{
    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(this.apiInfo()).select().build();
    }
    
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder().title("Drug Detail Service").description("Drug Detail API for Mail-order-Pharmacy").version("1.0").build();
//    }
    
    public ApiInfo apiInfo() {
		 return new ApiInfoBuilder().title("Drug Detail Service")
				 .description("Drug Detail API for Mail-order-Pharmacy")
				 .version("1.0")
				 .build();
	 }
}