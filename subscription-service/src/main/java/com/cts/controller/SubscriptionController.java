package com.cts.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.entity.PrescriptionDetails;
import com.cts.entity.SubscriptionDetails;
import com.cts.exception.DrugNotFoundException;
import com.cts.exception.InvalidTokenException;
import com.cts.exception.MicroServiceNotAvailable;
import com.cts.exception.StockNotFoundException;
import com.cts.service.SubscriptionService;

import feign.FeignException;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@RestController

public class SubscriptionController {

	private static final Logger log = LoggerFactory.getLogger(SubscriptionController.class);
	
	// autowired subscription service
	@Autowired
	private SubscriptionService subscriptionService;

	String msg = "Not subscribed";

	/** Subscribe Service */
	

	@CrossOrigin

	@PostMapping("/subscribe")
	public ResponseEntity<String> subscribe(@RequestHeader("Authorization") String token,
			@RequestBody PrescriptionDetails prescriptionDetails) throws InvalidTokenException,
	DrugNotFoundException,StockNotFoundException,MicroServiceNotAvailable{
		
		
		log.info("Start--Controller--subscription");
		try {
			log.info("Inside subscribe controller method");
			return subscriptionService.subscribe( prescriptionDetails,token);
		} catch (InvalidTokenException tokenException) {
			log.info("Catch--Controller--subscription");
			throw new InvalidTokenException("Invalid Token!!");
		}

		catch(FeignException e)
		{
			if(e.getMessage().contains("\"messge\":\"Drug Not Found\""))
			{
			 throw new DrugNotFoundException("Drug not found!!");
			}
		
			else if(e.getMessage().contains("\"messge\":\"Stock Unavailable at your location\""))
			{
				
				System.out.println("(((((((((((((((((((((((((((((((("+e.getMessage());
				throw new StockNotFoundException("Stock Unavailable at your location");
			}
			else
			{
				throw new MicroServiceNotAvailable("MicroService Not available");
			}
			
		}
		
	}
	

	
	@CrossOrigin

	@PostMapping("/unsubscribe/{mId}/{sId}")
	public ResponseEntity<String> unsubscribe(@RequestHeader("Authorization") String token,
			@PathVariable("mId") String memberId, @PathVariable("sId") Long subscriptionId)
			 throws InvalidTokenException {
		try {
			log.info("Inside unsubscribe method");
			return subscriptionService.unsubscribe(memberId, subscriptionId, token);
		} catch (InvalidTokenException e){
			throw new InvalidTokenException(msg);
		}
	}


	@CrossOrigin

	@GetMapping("/getAllSubscriptions/{mId}")
	public List<SubscriptionDetails> getAllSubscriptionsforMember(@RequestHeader("Authorization") String token,
			@PathVariable("mId") String mId) throws InvalidTokenException{
		try {
			return subscriptionService.getAllSubscriptions(mId, token);
		}catch (InvalidTokenException e){
			throw new InvalidTokenException(msg);
		}
	}
	
	
	@CrossOrigin

	@GetMapping("/getDrugName/{sId}")
	public ResponseEntity<String> getDrugNameBySubscriptionId(@RequestHeader("Authorization") String token,
			@PathVariable("sId") Long sId) throws InvalidTokenException{
		try {
			return subscriptionService.getDrugNameBySubscriptionId(sId, token);
		}catch (Exception e){
			throw new InvalidTokenException(msg);
		}
	}
	
}
