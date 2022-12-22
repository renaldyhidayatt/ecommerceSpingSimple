package com.sanedge.ecommercesimple.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String richDescription;

    @NotBlank
    private String brand;

    @NotBlank
    private Double price;

    @NotBlank
    private Long categoryId;

    @NotBlank
    private Integer countInStock;

    @NotBlank
    private Integer numReviews;

    @NotBlank
    private Double rating;

    @NotBlank
    private Boolean isFeatured;
}
