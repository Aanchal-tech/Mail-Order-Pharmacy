package com.cts.entity;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    private String drugId;
    private String drugName;
    private LocalDate expiryDate;
    private int stock;
}
