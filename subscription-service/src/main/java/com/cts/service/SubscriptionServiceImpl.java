package com.cts.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.client.AuthFeign;
import com.cts.client.DrugDetailClient;
import com.cts.client.RefillClient;
import com.cts.entity.DrugDetails;
import com.cts.entity.PrescriptionDetails;
import com.cts.entity.SubscriptionDetails;
import com.cts.entity.TokenValid;
import com.cts.exception.InvalidTokenException;
import com.cts.exception.SubscriptionListEmptyException;
import com.cts.repository.PrescriptionRepository;
import com.cts.repository.SubscriptionRepository;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

/** Service Implementation class */

@Slf4j
@Service
public class SubscriptionServiceImpl implements SubscriptionService {
	
	

	//drug detail client
	@Autowired
	private DrugDetailClient drugDetailClient;
	
	// refill client
	@Autowired
	private RefillClient refillClient;

	//auth feign
	@Autowired
	private AuthFeign authFeign;

	//Prescription repository object
	@Autowired
	PrescriptionRepository prescriptionRepo;
	
	//Subscription repository object
	@Autowired
	SubscriptionRepository subscriptionRepo;


	String msg = "Invalid Credentials";

	/**
	 * @param prescriptionDetails
	 * @param token
	 * @return
	 * @throws InvalidTokenException
	 */
	@Override
	public ResponseEntity<String> subscribe(PrescriptionDetails prescriptionDetail, String token)
			throws InvalidTokenException, FeignException {
	
		
		
		
		log.info("Start--ServiceImplementation--subscribe");
		
		TokenValid response = authFeign.getValidity(token).getBody();
		DrugDetails drugDetails = null;
		if (response == null)
		{
			throw new NullPointerException("Token is null");// i can add a custom exception
		}
			

		if (!response.isValid())
		{
			throw new InvalidTokenException(msg);
		}
		 drugDetails = drugDetailClient.getDrugByName(token, prescriptionDetail.getDrugName());

		 drugDetailClient.updateQuantity(token, prescriptionDetail.getDrugName()
				, prescriptionDetail.getMemberLocation(), prescriptionDetail.getQuantity());
		
		PrescriptionDetails prescriptionDetails = prescriptionRepo.save(prescriptionDetail);
		
		log.info("prescription saved");
		
		SubscriptionDetails subscriptionDetail = new SubscriptionDetails(prescriptionDetails.getPrescriptionId(),
				prescriptionDetails.getCourseDuration(), prescriptionDetails.getQuantity(),
				prescriptionDetails.getMemberId(), LocalDate.now(), prescriptionDetails.getMemberLocation(),
				"active", prescriptionDetails.getDrugName());
		log.info("subs obj created");

		SubscriptionDetails subscriptionDetails = subscriptionRepo.save(subscriptionDetail);
		// inform refill service to start refilling for this subscription
		refillClient.requestRefillSubscription(token, subscriptionDetails.getSubscriptionId(),
				subscriptionDetails.getMemberId(), subscriptionDetails.getQuantity(),
				subscriptionDetails.getRefillCycle());

		log.info("subs obj saved");
		
		
		log.info("End--ServiceImplementation--subscribe");
		return new ResponseEntity<>("You have successfully subscribed to " + prescriptionDetail.getDrugName(), HttpStatus.OK);

		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	/**
	 * @param mId
	 * @param sId
	 * @param token
	 * @return
	 * @throws InvalidTokenException
	 */
	@Override
	public ResponseEntity<String> unsubscribe(String mId, Long sId, String token)
			throws InvalidTokenException, FeignException {

		if(authFeign.getValidity(token).getBody() !=null) {
			TokenValid tokenValid = authFeign.getValidity(token).getBody();
			if (tokenValid != null && tokenValid.isValid()) {
				if (!refillClient.isPendingPaymentDues(token, sId)) {
					log.info("payment not clear");
					return new ResponseEntity<>("You have to clear your payment dues first.", HttpStatus.OK);
				}
				subscriptionRepo.deleteById(sId);
				log.info("deleted ");
				// inform refill service to stop refilling for this sId
				refillClient.deleteRefillData(token, sId);
				log.info("delete refill success");
			} else
				throw new InvalidTokenException(msg);
		}

		return new ResponseEntity<>("You have succesfully Unsubscribed", HttpStatus.OK);
	}

	/**
	 * @param mId
	 * @param token
	 * @return
	 * @throws InvalidTokenException
	 */
	@Override
	public List<SubscriptionDetails> getAllSubscriptions(String mId, String token)
			throws InvalidTokenException, SubscriptionListEmptyException {
		// gets a list  of subscriptions for a given member id
		log.info("get subscription for ");

			if (authFeign.getValidity(token).getBody().isValid()) {
				if (subscriptionRepo.findByMemberId(mId).isEmpty())
					throw new SubscriptionListEmptyException("Currently you do not have any subscriptions");
				return subscriptionRepo.findByMemberId(mId);
			} else
				throw new InvalidTokenException(msg);


	}

	/**
	 * @param sId
	 * @param token
	 * @return
	 * @throws InvalidTokenException
	 */
	@Override
	public ResponseEntity<String> getDrugNameBySubscriptionId(Long sId, String token) throws InvalidTokenException {
		// extracts Drug name for given subscription Id
		log.info("getDrugNameBySubscriptionId");
		String drugName = null;
		if(authFeign.getValidity(token).getBody() !=null) {
			TokenValid tokenValid = authFeign.getValidity(token).getBody();
			if (tokenValid != null && tokenValid.isValid()) {
				drugName = subscriptionRepo.findById(sId)
						.orElseThrow(() -> new SubscriptionListEmptyException("DrugNotFound")).getDrugName();
			}
			else
				throw new InvalidTokenException(msg);
		}

		return new ResponseEntity<>(drugName, HttpStatus.OK);
	}
	
}
