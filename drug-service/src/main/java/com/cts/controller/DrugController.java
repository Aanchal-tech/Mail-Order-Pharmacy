package com.cts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.entity.DrugDetails;
import com.cts.entity.Stock;
import com.cts.entity.SuccessResponse;
import com.cts.exception.DrugNotFoundException;
import com.cts.exception.InvalidTokenException;
import com.cts.exception.StockNotFoundException;
import com.cts.service.DrugDetailsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(produces = "application/json", value = "Manages drugs")
public class DrugController {
    
	private static final Logger log = LoggerFactory.getLogger(DrugController.class);

	
    @Autowired
    DrugDetailsService drugDetailsService;
    
    /**
     * 
     * http://localhost:8081/drugdetailapp/getAllDrugs
     */
    @CrossOrigin
    @ApiOperation(value = "Get all drugs", response = List.class)
    @GetMapping({ "/getAllDrugs" })
    public List<DrugDetails> getAllDrugs() {
        return (List<DrugDetails>)this.drugDetailsService.getAllDrugs();
    }
    /**
     * http://localhost:8081/drugdetailapp/searchDrugsById/{id}
 
     */
    @CrossOrigin
    @ApiOperation(value = "Search drug by id", response = DrugDetails.class)
    @GetMapping({ "/searchDrugsById/{id}" })
    public DrugDetails getDrugById(@RequestHeader("Authorization") final String token, @PathVariable("id") final String id) throws InvalidTokenException, DrugNotFoundException {
    	return this.drugDetailsService.getDrugById(id, token);
        
    }
    
    /**
     * http://localhost:8081/drugdetailapp/searchDrugsByName/{name}
     * @param token
     * @param name
     * @return
     * @throws InvalidTokenException
     * @throws DrugNotFoundException
     */
    @CrossOrigin
    @ApiOperation(value = "Search drug by name", response = DrugDetails.class)
    @GetMapping({ "/searchDrugsByName/{name}" })
    public DrugDetails getDrugByName(@RequestHeader("Authorization") final String token, @PathVariable("name") final String name) throws InvalidTokenException, DrugNotFoundException {
    	try {
			log.info("start--Controller--getDrugByName");
			return drugDetailsService.getDrugByName(name, token);
		}catch (DrugNotFoundException d){
			log.info("Catch--Controller--getDrugByName");
			throw new DrugNotFoundException("Drug Not Found");
		}
    }
    /**
     * http://localhost:8081/drugdetailapp//getDispatchableDrugStock/{id}/{location}
 
     */
    @CrossOrigin
    @ApiOperation(value = "Search stock by id", response = Stock.class)
    @GetMapping({ "/getDispatchableDrugStock/{id}/{location}" })
    public Stock getDispatchableDrugStock(@RequestHeader("Authorization") final String token, @PathVariable("id") final String id, @PathVariable("location") final String location) throws InvalidTokenException, StockNotFoundException, DrugNotFoundException {
        return this.drugDetailsService.getDispatchableDrugStock(id, location, token);
    }
 
  @CrossOrigin
  @ApiOperation(value = "Update quantity by stock", response = ResponseEntity.class)
  @PutMapping({ "/updateDispatchableDrugStock/{name}/{location}/{quantity}" })
    public ResponseEntity<SuccessResponse> updateQuantity(@RequestHeader("Authorization") final String token, @PathVariable("name") final String name, @PathVariable("location") final String location, @PathVariable("quantity") final int quantity) throws InvalidTokenException, DrugNotFoundException, StockNotFoundException {
        try {
			log.info("start--Controller--updateQuantity");
			return (ResponseEntity<SuccessResponse>)this.drugDetailsService.updateQuantity(name, location, quantity, token);
		}catch (DrugNotFoundException d){
			log.info("Catch--Controller--updateQuantity");
			throw new DrugNotFoundException("Drug Not Found");
		}
    }

}
