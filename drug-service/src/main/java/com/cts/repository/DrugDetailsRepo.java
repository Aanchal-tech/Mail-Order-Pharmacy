package com.cts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.entity.DrugDetails;

@Repository 
public interface DrugDetailsRepo extends JpaRepository<DrugDetails, String> {
    Optional<DrugDetails> findById(final String id);
    
    Optional<DrugDetails> findBydrugName(final String name);
}
