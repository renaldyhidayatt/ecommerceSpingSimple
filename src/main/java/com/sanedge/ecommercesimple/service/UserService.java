package com.sanedge.ecommercesimple.service;

import java.util.List;

import com.sanedge.ecommercesimple.payload.response.UserResponse;
import com.sanedge.ecommercesimple.payload.request.RegisterRequest;
import com.sanedge.ecommercesimple.payload.response.MessageResponse;

public interface UserService {

    public MessageResponse create(RegisterRequest registerRequest);

    public UserResponse getUser(Long id);

    public List<UserResponse> getAllUsers();

    public MessageResponse updateById(long id, RegisterRequest registerRequest);

    public MessageResponse deleteById(long id);
}
