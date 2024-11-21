package com.example.webservice;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String orderNumber;
    private double totalPrice;
    private List<Long> productIds;
}
