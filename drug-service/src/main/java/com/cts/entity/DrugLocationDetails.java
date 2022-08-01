package com.cts.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class DrugLocationDetails {
    @Id
    private String serialId;
    private String location;
    private int quantity;
    @ManyToOne(cascade = { CascadeType.ALL })
    @JsonIgnore
    @JoinColumn(name = "drugId")
    private DrugDetails drugDetails;

}
