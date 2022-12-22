package com.sanedge.ecommercesimple.payload.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    @NotEmpty
    private String shippingAddress1;

    @NotEmpty
    private String shippingAddress2;

    @NotEmpty
    private String city;

    @NotEmpty
    private String zip;

    @NotEmpty
    private String country;

    @NotEmpty
    private String phone;

    @NotEmpty
    private String status;

    @NotEmpty
    private List<OrderItemRequest> orderItems;

}
