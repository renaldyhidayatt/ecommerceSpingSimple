package com.sanedge.ecommercesimple.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanedge.ecommercesimple.convert.CategoryToDto;
import com.sanedge.ecommercesimple.exception.ResourceNotFoundException;
import com.sanedge.ecommercesimple.models.Category;
import com.sanedge.ecommercesimple.payload.request.CategoryRequest;
import com.sanedge.ecommercesimple.payload.response.CategoryResponse;
import com.sanedge.ecommercesimple.payload.response.MessageResponse;
import com.sanedge.ecommercesimple.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryToDto categoryToDto;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryToDto categoryToDto) {
        this.categoryRepository = categoryRepository;
        this.categoryToDto = categoryToDto;
    }

    public List<CategoryResponse> findAll() {
        List<Category> category = this.categoryRepository.findAll();

        return this.categoryToDto.mapCategoryToDtos(category);
    }

    public CategoryResponse findById(long id) {
        Category _category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Not found Category id"));

        return this.categoryToDto.mapCategoryToDto(_category);
    }

    public MessageResponse create(CategoryRequest categoryRequest) {
        Category _category = new Category();
        _category.setName(categoryRequest.getName());
        _category.setIcon(categoryRequest.getIcon());
        _category.setColor(categoryRequest.getColor());

        this.categoryRepository.save(_category);

        return MessageResponse.builder().message("Success create category").build();
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