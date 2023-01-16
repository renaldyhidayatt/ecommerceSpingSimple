package com.sanedge.ecommercesimple.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sanedge.ecommercesimple.models.Product;
import com.sanedge.ecommercesimple.payload.request.ProductRequest;
import com.sanedge.ecommercesimple.payload.response.MessageResponse;
import com.sanedge.ecommercesimple.payload.response.ProductResponse;

public interface ProductService {

    public List<ProductResponse> findAll();

    public MessageResponse createProduct(ProductRequest product, MultipartFile file);

    public ProductResponse getProductById(long id);

    public Product updateById(long id, ProductRequest productRequest, MultipartFile file);

    public MessageResponse delete(long id);

}
