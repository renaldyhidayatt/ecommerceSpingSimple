package com.sanedge.ecommercesimple.payload.request;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {
    @NotEmpty
    private Integer quantity;

    @NotEmpty
    private Long productId;

}
