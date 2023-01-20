package com.sanedge.ecommercesimple.service;

import com.sanedge.ecommercesimple.payload.request.OrderRequest;
import com.sanedge.ecommercesimple.payload.response.MessageResponse;

public interface OrderService {

    public MessageResponse findAll();

    public MessageResponse findById(long id);

    public MessageResponse createOrder(OrderRequest request);

    public MessageResponse delete(long id);

}
