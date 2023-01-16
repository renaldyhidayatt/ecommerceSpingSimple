package com.sanedge.ecommercesimple.service;

import java.util.List;

import com.sanedge.ecommercesimple.payload.request.CategoryRequest;
import com.sanedge.ecommercesimple.payload.response.CategoryResponse;
import com.sanedge.ecommercesimple.payload.response.MessageResponse;

public interface CategoryService {

    public List<CategoryResponse> findAll();

    public CategoryResponse findById(long id);

    public MessageResponse create(CategoryRequest categoryRequest);

    public MessageResponse update(long id, CategoryRequest categoryRequest);

    public MessageResponse delete(long id);
}