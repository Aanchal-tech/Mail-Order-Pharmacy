package com.cts.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cts.entity.DrugDetails;
import com.cts.entity.Stock;
import com.cts.entity.SuccessResponse;
import com.cts.exception.DrugNotFoundException;
import com.cts.exception.InvalidTokenException;
import com.cts.exception.StockNotFoundException;

public interface DrugDetailsService {
    DrugDetails getDrugById(final String id, final String token) throws InvalidTokenException, DrugNotFoundException;
    
    DrugDetails getDrugByName(final String name, final String token) throws InvalidTokenException, DrugNotFoundException;
    
    Stock getDispatchableDrugStock(final String id, final String location, final String token) throws InvalidTokenException, StockNotFoundException, DrugNotFoundException;
    
    ResponseEntity<SuccessResponse> updateQuantity(final String id, final String location, final int quantity, final String token) throws InvalidTokenException, DrugNotFoundException, StockNotFoundException;
    
    List<DrugDetails> getAllDrugs();
}
