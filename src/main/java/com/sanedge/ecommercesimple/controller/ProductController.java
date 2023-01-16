package com.sanedge.ecommercesimple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sanedge.ecommercesimple.models.Product;
import com.sanedge.ecommercesimple.payload.request.ProductRequest;
import com.sanedge.ecommercesimple.payload.response.MessageResponse;
import com.sanedge.ecommercesimple.payload.response.ProductResponse;
import com.sanedge.ecommercesimple.service.impl.ProductServiceImpl;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductServiceImpl productServiceImpl;

    @PostMapping
    public ResponseEntity<MessageResponse> createProduct(ProductRequest productRequest, MultipartFile file) {

        MessageResponse productResponse = this.productServiceImpl.createProduct(productRequest, file);

        return new ResponseEntity<MessageResponse>(productResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        List<ProductResponse> productResponse = this.productServiceImpl.findAll();

        return new ResponseEntity<List<ProductResponse>>(productResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateById(@PathVariable long id, ProductRequest productRequest,
            MultipartFile file) {
        Product productResponse = this.productServiceImpl.updateById(id, productRequest, file);

        return new ResponseEntity<Product>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable long id) {
        ProductResponse productResponse = this.productServiceImpl.getProductById(id);

        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable long id) {
        MessageResponse productResponse = this.productServiceImpl.delete(id);

        return new ResponseEntity<MessageResponse>(productResponse, HttpStatus.OK);
    }
}
