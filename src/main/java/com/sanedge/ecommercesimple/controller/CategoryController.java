package com.sanedge.ecommercesimple.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanedge.ecommercesimple.payload.request.CategoryRequest;
import com.sanedge.ecommercesimple.payload.response.MessageResponse;
import com.sanedge.ecommercesimple.service.impl.CategoryServiceImpl;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryServiceImpl categoryServiceImpl;

    @PostMapping
    public ResponseEntity<MessageResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        MessageResponse createMessageResponse = this.categoryServiceImpl.create(categoryRequest);

        return new ResponseEntity<>(createMessageResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<MessageResponse> findAll() {
        MessageResponse categoryResponse = this.categoryServiceImpl.findAll();

        return new ResponseEntity<MessageResponse>(categoryResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> findById(@PathVariable Long id) {
        MessageResponse categoryResponse = this.categoryServiceImpl.findById(id);

        return new ResponseEntity<MessageResponse>(categoryResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateById(@PathVariable Long id,
            @Valid @RequestBody CategoryRequest categoryRequest) {
        MessageResponse updatMessageResponse = this.categoryServiceImpl.update(id, categoryRequest);

        return new ResponseEntity<MessageResponse>(updatMessageResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable Long id) {
        MessageResponse deletMessageResponse = this.categoryServiceImpl.delete(id);

        return new ResponseEntity<>(deletMessageResponse, HttpStatus.OK);
    }
}
