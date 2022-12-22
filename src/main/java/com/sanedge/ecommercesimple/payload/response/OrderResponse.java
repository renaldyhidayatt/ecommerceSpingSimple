package com.sanedge.ecommercesimple.payload.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Long id;

    private String shippingAddress1;

    private String shippingAddress2;

    private String city;

    private String zip;

    private String country;

    private String phone;

    private String status;

    private List<OrderItemResponse> orderItems;
}
