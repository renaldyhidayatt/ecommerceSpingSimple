package com.sanedge.ecommercesimple.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sanedge.ecommercesimple.convert.ProductToDto;
import com.sanedge.ecommercesimple.exception.ResourceNotFoundException;
import com.sanedge.ecommercesimple.models.Category;
import com.sanedge.ecommercesimple.models.Product;
import com.sanedge.ecommercesimple.payload.request.ProductRequest;
import com.sanedge.ecommercesimple.payload.response.MessageResponse;
import com.sanedge.ecommercesimple.payload.response.ProductResponse;
import com.sanedge.ecommercesimple.repository.CategoryRepository;
import com.sanedge.ecommercesimple.repository.ProductRepository;
import com.sanedge.ecommercesimple.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductToDto productToDto;
    private final FileStorageImpl fileStorageImplService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
            ProductToDto productToDto, FileStorageImpl fileStorageImplService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productToDto = productToDto;
        this.fileStorageImplService = fileStorageImplService;
    }

    public List<ProductResponse> findAll() {
        List<Product> products = this.productRepository.findAll();

        return this.productToDto.mapProductToDtos(products);
    }

    public MessageResponse createProduct(ProductRequest product, MultipartFile file) {
        Product _product = new Product();
        String filename = this.fileStorageImplService.storeFile(file);

        _product.setName(product.getName());
        _product.setDescription(product.getDescription());
        _product.setRichDescription(product.getDescription());
        _product.setImage(filename);
        _product.setBrand(product.getBrand());
        _product.setPrice(product.getPrice());
        _product.setCountInStock(product.getCountInStock());
        _product.setRating(product.getRating());
        _product.setNumReviews(product.getNumReviews());
        _product.setIsFeatured(product.getIsFeatured());

        Category _category = this.categoryRepository.findById(product.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found category"));

        _product.setCategory(_category);

        this.productRepository.save(_product);

        return MessageResponse.builder().message("SUccess create product").build();
    }

    public ProductResponse getProductById(long id) {
        Product _product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Not found Product id"));

        return this.productToDto.mapProductToDto(_product);

    }

    public Product updateById(long id, ProductRequest productRequest, MultipartFile file) {

        Product _product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Not found Product id"));

        this.fileStorageImplService.deleteByName(_product.getImage());

        String filename = fileStorageImplService.storeFile(file);

        _product.setName(productRequest.getName());
        _product.setDescription(productRequest.getDescription());
        _product.setRichDescription(productRequest.getDescription());
        _product.setImage(filename);
        _product.setBrand(productRequest.getBrand());
        _product.setPrice(productRequest.getPrice());
        _product.setCountInStock(productRequest.getCountInStock());
        _product.setRating(productRequest.getRating());
        _product.setNumReviews(productRequest.getNumReviews());
        _product.setIsFeatured(productRequest.getIsFeatured());

        Category _category = this.categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found category"));
        _product.setCategory(_category);

        this.productRepository.save(_product);

        return _product;

    }

    public MessageResponse delete(long id) {
        Product _product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Not found Product id"));

        this.fileStorageImplService.deleteByName(_product.getImage());
        this.fileStorageImplService.deleteByNames(_product.getImages());

        this.productRepository.delete(_product);

        return MessageResponse.builder().message("Success delete product").build();
    }
}
