package com.sanedge.ecommercesimple.convert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sanedge.ecommercesimple.models.Order;
import com.sanedge.ecommercesimple.payload.response.OrderItemResponse;
import com.sanedge.ecommercesimple.payload.response.OrderResponse;

@Component
public class OrderToDto {
    public List<OrderResponse> mapOrderToDtos(List<Order> _order) {
        return _order.stream()
                .map(e -> new OrderResponse(e.getId(), e.getShippingAddress1(), e.getShippingAddress2(), e.getCity(),
                        e.getZip(), e.getCountry(), e.getPhone(), e.getStatus(),
                        e.getOrderItems().stream()
                                .map(c -> new OrderItemResponse(c.getId(), c.getQuantity(), c.getProduct().getId()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public OrderResponse mapOrderToDto(Order _order) {
        return mapOrderToDtos(Arrays.asList(_order)).get(0);
    }

}
