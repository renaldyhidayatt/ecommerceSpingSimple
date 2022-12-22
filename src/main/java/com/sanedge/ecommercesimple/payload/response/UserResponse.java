package com.sanedge.ecommercesimple.payload.response;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sanedge.ecommercesimple.models.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private Set<Role> roles;
}
