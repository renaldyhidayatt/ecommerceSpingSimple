package com.sanedge.ecommercesimple.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanedge.ecommercesimple.payload.request.OrderRequest;
import com.sanedge.ecommercesimple.payload.response.MessageResponse;
import com.sanedge.ecommercesimple.payload.response.OrderResponse;
import com.sanedge.ecommercesimple.service.impl.OrderServiceImpl;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    OrderServiceImpl orderServiceImpl;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        List<OrderResponse> orderResponse = this.orderServiceImpl.findAll();

        return new ResponseEntity<List<OrderResponse>>(orderResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderRequest request) {
        OrderResponse orderResponse = this.orderServiceImpl.createOrder(request);

        return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteByid(@PathVariable Long id) {
        MessageResponse orderResponse = this.orderServiceImpl.delete(id);

        return new ResponseEntity<MessageResponse>(orderResponse, HttpStatus.OK);
    }
}
