package com.cts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.entity.DrugLocationDetails;

@Repository 
public interface DrugLocationRepo extends JpaRepository<DrugLocationDetails, String> {
    List<DrugLocationDetails> findByserialId(final String string);
}
