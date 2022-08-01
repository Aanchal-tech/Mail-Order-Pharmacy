package com.cts.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cts.entity.PrescriptionDetails;
import com.cts.entity.SubscriptionDetails;
import com.cts.exception.InvalidTokenException;

/** Interface for service class */

public interface SubscriptionService {
/**
 * @param prescriptionDetails
 * @param token
 * @return
 * @throws InvalidTokenException
 */
    ResponseEntity<String> subscribe(PrescriptionDetails prescriptionDetails,String token) throws InvalidTokenException;
/**
 * @param mId
 * @param sId
 * @param token
 * @return
 * @throws InvalidTokenException
 */
    ResponseEntity<String> unsubscribe(String mId,Long sId,String token) throws InvalidTokenException;
/**
 * @param mId
 * @param token
 * @return
 * @throws InvalidTokenException
 */
    List<SubscriptionDetails> getAllSubscriptions(String mId,String token) throws InvalidTokenException;
/**
 * @param sId
 * @param token
 * @return
 * @throws InvalidTokenException
 */
    ResponseEntity<String> getDrugNameBySubscriptionId(Long sId, String token) throws InvalidTokenException;
    
}
