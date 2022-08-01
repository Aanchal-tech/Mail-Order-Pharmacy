package com.cts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.cts.dao.UserDAO;
import com.cts.entity.AuthResponse;
import com.cts.entity.UserData;
import com.cts.service.CustomerDetailsService;
import com.cts.service.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController

public class AuthController {
	


	@Autowired
	private JwtUtil jwtutil;
	@Autowired
	private CustomerDetailsService custdetailservice;
	@Autowired
	private UserDAO userservice;

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

	@ApiOperation(value = "AWS health check", response = ResponseEntity.class)
	@GetMapping(path = "/health")
	public ResponseEntity<Object> healthCheckup() {
		
		LOGGER.info("AWS Health Check ");
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	@CrossOrigin
	@ApiOperation(value = "Verify credentials and generate JWT Token", response = ResponseEntity.class)
	@PostMapping(value = "/login")
	public ResponseEntity<Object> login(@RequestBody UserData userlogincredentials) {
		//Generates token for login
		final UserDetails userdetails = custdetailservice.loadUserByUsername(userlogincredentials.getUserid());
		String uid = "";
		String generateToken = "";
		if (userdetails.getPassword().equals(userlogincredentials.getUpassword())) {
			uid = userlogincredentials.getUserid();
			generateToken = jwtutil.generateToken(userdetails);
			
			return new ResponseEntity<>(new UserData(uid, null, null, generateToken), HttpStatus.OK);
		} else {
			LOGGER.info("At Login : ");
			LOGGER.error("Not Accesible");
			return new ResponseEntity<>("Not Accesible", HttpStatus.FORBIDDEN);
		}
	}
	@CrossOrigin
	@ApiOperation(value = "Validate JWT Token", response = ResponseEntity.class)
	@GetMapping(value = "/validate")
	public ResponseEntity<Object> getValidity(@RequestHeader("Authorization") final String token) {
		//Returns response after Validating received token
		String token1 = token.substring(7);
		AuthResponse res = new AuthResponse();
		if (Boolean.TRUE.equals(jwtutil.validateToken(token1))) {
			res.setUid(jwtutil.extractUsername(token1));
			res.setValid(true);
			res.setName(userservice.findById(jwtutil.extractUsername(token1)).get().getUname());
		} else {
			res.setValid(false);
			LOGGER.info("At Validity : ");
			LOGGER.error("Token Has Expired");
		}
		return new ResponseEntity<>(res, HttpStatus.OK);

	}

}
