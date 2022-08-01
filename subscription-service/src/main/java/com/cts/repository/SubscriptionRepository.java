package com.cts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.entity.SubscriptionDetails;


public interface SubscriptionRepository extends JpaRepository<SubscriptionDetails, Long> {


	@Query(value = "SELECT u FROM SubscriptionDetails u WHERE MEMBER_ID = ?1")
	 List<SubscriptionDetails> findByMemberId(String mId);
}
