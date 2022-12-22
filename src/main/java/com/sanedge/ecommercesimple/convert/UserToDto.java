package com.sanedge.ecommercesimple.convert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sanedge.ecommercesimple.models.User;
import com.sanedge.ecommercesimple.payload.response.UserResponse;

@Component
public class UserToDto {
    public List<UserResponse> mapUserToDtos(List<User> _users) {
        return _users.stream()
                .map(e -> new UserResponse(e.getId(), e.getUsername(), e.getEmail(), e.getRoles()))
                .collect(Collectors.toList());
    }

    public UserResponse mapUserToDto(User _user) {
        return mapUserToDtos(Arrays.asList(_user)).get(0);
    }
}
