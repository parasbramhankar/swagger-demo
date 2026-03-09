package com.example.swagger_demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@OpenAPIDefinition(
		info = @Info(
				title="swagger demo",
				version = "1.0",
				description ="Api to understand the swagger"
		)
)
public class SwaggerDemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(SwaggerDemoApplication.class, args);
	}

}
