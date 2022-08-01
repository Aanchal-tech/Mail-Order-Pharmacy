package com.cts.service;

import java.text.ParseException;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.exception.DrugQuantityNotAvailable;
import com.cts.exception.InvalidTokenException;
import com.cts.exception.SubscriptionIDNotFoundException;
import com.cts.model.RefillOrder;
import com.cts.model.RefillOrderLine;

import feign.FeignException;


@Service
public interface IRefillOrder {
	
	/**
	 * @param subID
	 * @return
	 * @throws SubscriptionIDNotFoundException 
	 * @throws InvalidTokenException 
	 */
	public List<RefillOrder> getStatus(long subID,String token) throws SubscriptionIDNotFoundException, InvalidTokenException;
	
	/**
	 * @param memberId
	 * @param date
	 * @return
	 * @throws InvalidTokenException 
	 */
	public List<RefillOrderLine> getRefillDuesAsOfDate(String memberId, int date,String token) throws InvalidTokenException;
	
	/**
	 * @param subId
	 * @param payStatus
	 * @param quantity
	 * @param location
	 * @return
	 * @throws ParseException 
	 * @throws DrugQuantityNotAvailable 
	 * @throws InvalidTokenException 
	 */
	public RefillOrder requestAdhocRefill(long policyId, long subID, String memberID, String location,int quantity,String token) throws ParseException, DrugQuantityNotAvailable, InvalidTokenException,FeignException;
	
	
	/**
	 * @param order
	 * @return
	 * @throws ParseException 
	 * @throws InvalidTokenException 
	 */
	//public RefillOrder requestRefill(RefillOrder order,String token) throws ParseException, InvalidTokenException;
	
	/**
	 * @param subId
	 * @param quantity
	 * @param memberId
	 * @param token
	 * @return RefillOrder
	 * @throws ParseException
	 * @throws InvalidTokenException
	 */
	public RefillOrder requestRefill(long subId, int quantity, String memberId, String token)
			throws ParseException, InvalidTokenException;
	
	
	
	/**
	 * @return
	 * @throws InvalidTokenException 
	 */
	public String updateRefill(String token) throws InvalidTokenException;
	

	
	/**
	 * @param subID
	 * @return
	 * @throws InvalidTokenException 
	 */
	public boolean getRefillPaymentDues(long subID,String token) throws InvalidTokenException;
	

}
