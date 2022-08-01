package com.cts.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.entity.TokenValid;

/**Interface to connect with 
 * authentication service **/

@FeignClient(name = "authapp", url = "http://localhost:8090/authapp")
public interface AuthFeign {

	
	@GetMapping(value = "/validate")
	public ResponseEntity<TokenValid> getValidity(@RequestHeader("Authorization") final String token);
	
}
