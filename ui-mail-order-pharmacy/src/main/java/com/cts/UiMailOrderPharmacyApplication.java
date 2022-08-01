package com.cts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class UiMailOrderPharmacyApplication {

	public static void main(String[] args) {
		SpringApplication.run(UiMailOrderPharmacyApplication.class, args);
	}
	@GetMapping(value="/{path:[^\\.]*}")
	public String redirect(){
		return "forward:/";
	}
}
