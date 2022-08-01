package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entity.PrescriptionDetails;

//

public interface PrescriptionRepository extends JpaRepository<PrescriptionDetails,Long> {

}
