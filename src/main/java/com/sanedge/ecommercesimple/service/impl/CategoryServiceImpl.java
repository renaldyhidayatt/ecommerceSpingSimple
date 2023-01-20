package com.sanedge.ecommercesimple.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanedge.ecommercesimple.exception.ResourceNotFoundException;
import com.sanedge.ecommercesimple.models.Category;
import com.sanedge.ecommercesimple.payload.request.CategoryRequest;
import com.sanedge.ecommercesimple.payload.response.CategoryResponse;
import com.sanedge.ecommercesimple.payload.response.MessageResponse;
import com.sanedge.ecommercesimple.repository.CategoryRepository;
import com.sanedge.ecommercesimple.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public MessageResponse findAll() {
        List<Category> categories = this.categoryRepository.findAll();

        List<CategoryResponse> categoryResponses = new ArrayList<>();

        for (Category category : categories) {
            CategoryResponse categoryResponse = new CategoryResponse(
                    category.getId(), category.getName(), category.getColor(),
                    category.getIcon(), category.getCreatedAt(), category.getUpdatedAt());

            categoryResponses.add(categoryResponse);
        }

        return MessageResponse.builder().data(categoryResponses).build();
    }

    public MessageResponse findById(long id) {
        Category _category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Not found Category id"));

        CategoryResponse dto = new CategoryResponse(_category.getId(), _category.getName(), _category.getColor(),
                _category.getIcon(), _category.getCreatedAt(), _category.getUpdatedAt());

        return MessageResponse.builder().message("Berhasil mendapatkan data").data(dto).statusCode(200).build();
    }

    public MessageResponse create(CategoryRequest categoryRequest) {
        Category _category = new Category();
        _category.setName(categoryRequest.getName());
        _category.setIcon(categoryRequest.getIcon());
        _category.setColor(categoryRequest.getColor());

        this.categoryRepository.save(_category);

        return MessageResponse.builder().message("Success create category").data(_category).statusCode(200).build();
    }

    public MessageResponse update(long id, CategoryRequest categoryRequest) {
        Category _category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Not found Category id"));
        _category.setName(categoryRequest.getName());
        _category.setIcon(categoryRequest.getIcon());
        _category.setColor(categoryRequest.getColor());

        this.categoryRepository.save(_category);

        return MessageResponse.builder().message("Success update category").build();
    }

    public MessageResponse delete(long id) {
        Category _category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Not found Category id"));

        this.categoryRepository.delete(_category);

        return MessageResponse.builder().message("Success update category").build();
    }

}
