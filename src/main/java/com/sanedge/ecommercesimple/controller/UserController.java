package com.sanedge.ecommercesimple.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanedge.ecommercesimple.payload.request.RegisterRequest;
import com.sanedge.ecommercesimple.payload.response.MessageResponse;
import com.sanedge.ecommercesimple.service.impl.UserServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping
    public ResponseEntity<MessageResponse> createUser(@Valid @RequestBody RegisterRequest registerRequest) {
        MessageResponse creatMessageResponse = this.userServiceImpl.create(registerRequest);

        return new ResponseEntity<MessageResponse>(creatMessageResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> getUserById(@PathVariable Long id) {
        MessageResponse _user = this.userServiceImpl.getUser(id);

        return new ResponseEntity<MessageResponse>(_user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateUser(@PathVariable Long id,
            @Valid @RequestBody RegisterRequest registerRequest) {
        MessageResponse updatMessageResponse = this.userServiceImpl.updateById(id, registerRequest);

        return new ResponseEntity<MessageResponse>(updatMessageResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable Long id) {
        MessageResponse deletMessageResponse = this.userServiceImpl.deleteById(id);

        return new ResponseEntity<MessageResponse>(deletMessageResponse, HttpStatus.OK);
    }
}
