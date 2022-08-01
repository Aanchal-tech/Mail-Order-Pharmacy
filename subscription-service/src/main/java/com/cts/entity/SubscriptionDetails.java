package com.cts.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@ToString
public class SubscriptionDetails {

	public SubscriptionDetails(final Long prescriptionId, final int refillCycle, final int quantity,
			final String memberId, final LocalDate subscriptionDate, final String memberLocation,
			final String subscriptionStatus, final String drugName) {
		super();
		this.prescriptionId = prescriptionId;
		this.memberId = memberId;
		this.subscriptionDate = subscriptionDate;
		this.memberLocation = memberLocation;
		this.subscriptionStatus = subscriptionStatus;
		this.refillCycle = refillCycle;
		this.quantity = quantity;
		this.drugName = drugName;
	}

	// subscription id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subscriptionId;

	// prescription id
	private Long prescriptionId;

	// drug name
	private String drugName;

	// refill cycle
	private int refillCycle;

	// drug quantity
	private int quantity;

	// member id
	private String memberId;

	// subscription date
	private LocalDate subscriptionDate;

	// member location
	private String memberLocation;

	// subscription status
	private String subscriptionStatus;

	
	
}
