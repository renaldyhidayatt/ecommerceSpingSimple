package com.sanedge.ecommercesimple.payload.response;

import java.time.ZonedDateTime;
import java.util.List;

import com.sanedge.ecommercesimple.models.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private String richDescription;
    private String image;
    private List<String> images;
    private String brand;
    private double price;
    private Category category;
    private int countInStock;
    private double rating;
    private int numReviews;
    private boolean isFeatured;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
