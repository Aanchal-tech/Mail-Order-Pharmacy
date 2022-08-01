package com.cts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenValid {
    private String uid;
    private String name;
    private boolean isValid;
}
