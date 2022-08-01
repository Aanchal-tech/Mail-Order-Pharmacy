package com.cts.service;

import java.util.List;
import java.util.stream.Collectors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.authorization.AuthFeign;
import com.cts.entity.DrugDetails;
import com.cts.entity.DrugLocationDetails;
import com.cts.entity.Stock;
import com.cts.entity.SuccessResponse;
import com.cts.entity.TokenValid;
import com.cts.exception.DrugNotFoundException;
import com.cts.exception.InvalidTokenException;
import com.cts.exception.StockNotFoundException;
import com.cts.repository.DrugDetailsRepo;
import com.cts.repository.DrugLocationRepo;




@Service
public class DrugDetailsServiceImpl implements DrugDetailsService {
    @Autowired
    private DrugDetailsRepo drugRepo;
    @Autowired
    private DrugLocationRepo locationRepo;
    @Autowired
    private AuthFeign authFeign;
    
	private static final Logger log = LoggerFactory.getLogger(DrugDetailsServiceImpl.class);

    @Override
    public DrugDetails getDrugById(final String id, final String token) throws InvalidTokenException, DrugNotFoundException {
        DrugDetails drugDetails = null;
        if (((TokenValid)this.authFeign.getValidity(token).getBody()).isValid()) {
            drugDetails = this.drugRepo.findById(id).orElseThrow(() -> new DrugNotFoundException("Drug Not Found"));
            return drugDetails;
        }
        throw new InvalidTokenException("Invalid Credentials"); 
    }
    
    @Override
    public DrugDetails getDrugByName(final String name, final String token) throws InvalidTokenException, DrugNotFoundException {
    	log.info("start--serviceimpl--getDrugByName");
    	if (((TokenValid)this.authFeign.getValidity(token).getBody()).isValid()) {
            try {
                return this.drugRepo.findBydrugName(name).get();
            }
            catch (Exception e) {
                throw new DrugNotFoundException("Drug Not Found");
            }
        }
    	else
    		throw new InvalidTokenException("Invalid Credentials"); 
    }
    
    @Override
    public Stock getDispatchableDrugStock(final String id, final String location, final String token) throws InvalidTokenException, StockNotFoundException, DrugNotFoundException {
        if (!((TokenValid)this.authFeign.getValidity(token).getBody()).isValid()) {
            throw new InvalidTokenException("Invalid Credentials");
        } 
        DrugDetails details = null;
        try {
            details = this.drugRepo.findById(id).get();
        }
        catch (Exception e) {
            throw new DrugNotFoundException("Drug Not Found");
        }
        Stock stock = null;
        for (final DrugLocationDetails dld : details.getDruglocationQuantities()) {
            if (dld.getLocation().equals(location)) {
                stock = new Stock(id, details.getDrugName(), details.getExpiryDate(), dld.getQuantity());
            }
        }
        if (stock == null) {
            throw new StockNotFoundException("Stock Unavailable at your location");
        }
        return stock;
    }
    
    @Override
    public ResponseEntity<SuccessResponse> updateQuantity(final String drugName, final String location, final int quantity, final String token) throws InvalidTokenException, DrugNotFoundException, StockNotFoundException {
       
    
        log.info("start--serviceimpl-- updateQuantity");
		if (authFeign.getValidity(token).getBody().isValid()) {
			DrugDetails details = new DrugDetails();
			try {
				details = this.drugRepo.findBydrugName(drugName).get();
			} catch (Exception e) {

				throw new DrugNotFoundException("Drug Not Found");
			}
			List<DrugLocationDetails> dummy = details.getDruglocationQuantities().stream()
					.filter(x -> 
					x.getLocation().equalsIgnoreCase(location))
					.collect(Collectors.toList());

			if (dummy.isEmpty()) {
				throw new StockNotFoundException("Stock Unavailable at your location");
			}

			else if (dummy.get(0).getQuantity() >= quantity && quantity >0) {

				DrugLocationDetails allDetails = locationRepo.findByserialId(dummy.get(0).getSerialId()).get(0);
				int val = allDetails.getQuantity() - quantity;
				allDetails.setQuantity(val);
				this.locationRepo.save(allDetails);
				log.info("End--serviceimpl-- updateQuantity");
				return (ResponseEntity<SuccessResponse>)new ResponseEntity((Object)new SuccessResponse("Refill Done Successfully"), HttpStatus.OK);
			} else
				throw new StockNotFoundException("Stock Unavailable at your location");
		}
		throw new InvalidTokenException("Invalid Token Received"); 
   }
    
    @Override
    public List<DrugDetails> getAllDrugs() {
        final List<DrugDetails> drugList = (List<DrugDetails>)this.drugRepo.findAll();
        return drugList;
    }
}
