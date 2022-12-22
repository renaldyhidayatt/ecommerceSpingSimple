package com.sanedge.ecommercesimple.convert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sanedge.ecommercesimple.models.Category;
import com.sanedge.ecommercesimple.payload.response.CategoryResponse;

@Component
public class CategoryToDto {
    public List<CategoryResponse> mapCategoryToDtos(List<Category> _category) {
        return _category.stream()
                .map(e -> new CategoryResponse(e.getId(), e.getName(), e.getIcon(), e.getColor(), e.getCreatedAt(),
                        e.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    public CategoryResponse mapCategoryToDto(Category _category) {
        return mapCategoryToDtos(Arrays.asList(_category)).get(0);
    }
}
