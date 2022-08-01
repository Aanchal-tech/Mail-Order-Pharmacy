package com.cts.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class DrugDetails {

    @Id
    private String drugId;
    private String drugName;
    private String manufacturer;
    private LocalDate manufactureDate;
    private LocalDate expiryDate;
    @OneToMany(mappedBy = "drugDetails")
    private List<DrugLocationDetails> druglocationQuantities;

}
