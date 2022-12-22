package com.sanedge.ecommercesimple.payload.response;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
    private String icon;
    private String color;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
