package com.sanedge.ecommercesimple.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanedge.ecommercesimple.convert.OrderToDto;
import com.sanedge.ecommercesimple.exception.ResourceNotFoundException;
import com.sanedge.ecommercesimple.models.Order;
import com.sanedge.ecommercesimple.models.OrderItem;
import com.sanedge.ecommercesimple.models.Product;
import com.sanedge.ecommercesimple.payload.request.OrderItemRequest;
import com.sanedge.ecommercesimple.payload.request.OrderRequest;
import com.sanedge.ecommercesimple.payload.response.MessageResponse;
import com.sanedge.ecommercesimple.payload.response.OrderResponse;
import com.sanedge.ecommercesimple.repository.OrderRepository;
import com.sanedge.ecommercesimple.repository.OrderitemRepository;
import com.sanedge.ecommercesimple.repository.ProductRepository;
import com.sanedge.ecommercesimple.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final AuthServiceImpl authImplService;
    private final ProductRepository productRepository;
    private final OrderitemRepository orderitemRepository;
    private final OrderToDto orderToDto;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, AuthServiceImpl authImplService,
            ProductRepository productRepository, OrderitemRepository orderitemRepository, OrderToDto orderToDto) {
        this.orderRepository = orderRepository;
        this.authImplService = authImplService;
        this.productRepository = productRepository;
        this.orderitemRepository = orderitemRepository;
        this.orderToDto = orderToDto;
    }

    public List<OrderResponse> findAll() {
        List<Order> orderAll = this.orderRepository.findAll();

        return this.orderToDto.mapOrderToDtos(orderAll);
    }

    public OrderResponse findById(long id) {
        Order orderById = this.orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found order by id"));

        return this.orderToDto.mapOrderToDto(orderById);
    }

    public OrderResponse createOrder(OrderRequest request) {
        Order _order = new Order();
        List<OrderItem> orderItems = new ArrayList<>();

        _order.setShippingAddress1(request.getShippingAddress1());
        _order.setShippingAddress2(request.getShippingAddress2());
        _order.setCity(request.getCity());
        _order.setZip(request.getZip());
        _order.setPhone(request.getPhone());
        _order.setStatus(request.getStatus());

        _order.setUser(this.authImplService.getCurrentUser());

        this.orderRepository.save(_order);

        for (OrderItemRequest orderItemRequest : request.getOrderItems()) {
            OrderItem item = new OrderItem();
            Order order = this.orderRepository.findById(_order.getId()).get();
            Product product = this.productRepository.findById(orderItemRequest.getProductId()).get();

            Double totalPrice = (Double) (product.getPrice() * orderItemRequest.getQuantity());

            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(orderItemRequest.getQuantity());

            this.orderitemRepository.save(item);

            orderItems.add(item);

            _order.setTotalPrice(totalPrice);
        }

        _order.setOrderItems(orderItems);
        this.orderRepository.save(_order);

        return this.orderToDto.mapOrderToDto(_order);

    }

    public MessageResponse delete(long id) {
        Order orderById = this.orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found order by id"));

        for (OrderItem orderItem : orderById.getOrderItems()) {
            this.orderitemRepository.delete(orderItem);
        }
        this.orderRepository.delete(orderById);

        return MessageResponse.builder().message("Delete success ").build();
    }
}
