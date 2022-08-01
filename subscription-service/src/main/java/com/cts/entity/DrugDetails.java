package com.cts.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrugDetails {

	// drug id
	@Id
	private String drugId;
	
	//drug name
	private String drugName;
	
	//manufacturer name
	private String manufacturer;
	
	// manufacturing date
	private Date manufactureDate;
	
	//expiry date
	private Date expiryDate;

	/* List of drug locations and quantities in location */
	@OneToMany(mappedBy="drugDetails")
	private List<DrugLocationDetails> druglocationQuantities = new ArrayList<>();

	
}

