package com.sanedge.ecommercesimple.service;

import java.util.List;

import com.sanedge.ecommercesimple.payload.request.OrderRequest;
import com.sanedge.ecommercesimple.payload.response.MessageResponse;
import com.sanedge.ecommercesimple.payload.response.OrderResponse;

public interface OrderService {

    public List<OrderResponse> findAll();

    public OrderResponse findById(long id);

    public OrderResponse createOrder(OrderRequest request);

    public MessageResponse delete(long id);

}
