package com.cts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RefillServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RefillServiceApplication.class, args);
	}

}
