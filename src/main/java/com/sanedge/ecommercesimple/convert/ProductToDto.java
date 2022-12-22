package com.sanedge.ecommercesimple.convert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sanedge.ecommercesimple.models.Product;
import com.sanedge.ecommercesimple.payload.response.CategoryResponse;
import com.sanedge.ecommercesimple.payload.response.ProductResponse;

@Component
public class ProductToDto {
    public List<ProductResponse> mapProductToDtos(List<Product> _product) {

        return _product.stream()
                .map(e -> new ProductResponse(e.getId(), e.getName(), e.getDescription(), e.getRichDescription(),
                        e.getImage(), e.getImages(), e.getBrand(), e.getPrice(),
                        new CategoryResponse(e.getCategory().getId(), e.getCategory().getName(),
                                e.getCategory().getIcon(), e.getCategory().getColor(), e.getCategory().getCreatedAt(),
                                e.getCategory().getUpdatedAt()),
                        e.getCountInStock(), e.getRating(), e.getNumReviews(), e.getIsFeatured(), e.getCreatedAt(),
                        e.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    public ProductResponse mapProductToDto(Product _product) {
        return mapProductToDtos(Arrays.asList(_product)).get(0);
    }
}
