package com.sanedge.ecommercesimple.service;

import com.sanedge.ecommercesimple.models.User;
import com.sanedge.ecommercesimple.payload.request.LoginRequest;
import com.sanedge.ecommercesimple.payload.request.RegisterRequest;
import com.sanedge.ecommercesimple.payload.response.AuthenticationResponse;
import com.sanedge.ecommercesimple.payload.response.MessageResponse;

public interface AuthService {
    public AuthenticationResponse login(LoginRequest loginRequest);

    public MessageResponse register(RegisterRequest registerRequest);

    public User getCurrentUser();
}