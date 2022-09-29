package com.cts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Enter the below URL
// http://localhost:8048/ui-service/loginModule
@Controller
@SpringBootApplication
public class UiMailOrderPharmacyApplication {

	// run maven build command - clean spring-boot:run
	public static void main(String[] args) {
		SpringApplication.run(UiMailOrderPharmacyApplication.class, args);
	}
	@GetMapping(value="/{path:[^\\.]*}")
	public String redirect(){
		return "forward:/";
	}
}
