package com.sanedge.ecommercesimple.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanedge.ecommercesimple.payload.response.UserResponse;
import com.sanedge.ecommercesimple.enums.ERole;
import com.sanedge.ecommercesimple.exception.ResourceNotFoundException;
import com.sanedge.ecommercesimple.models.Role;
import com.sanedge.ecommercesimple.models.User;
import com.sanedge.ecommercesimple.payload.request.RegisterRequest;
import com.sanedge.ecommercesimple.payload.response.MessageResponse;
import com.sanedge.ecommercesimple.repository.RoleRepository;
import com.sanedge.ecommercesimple.repository.UserRepository;

@Service
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public MessageResponse create(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        Set<String> strRoles = registerRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        this.userRepository.save(user);

        return MessageResponse.builder().message("Successs create user").data(user).statusCode(200).build();

    }

    public MessageResponse getUser(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found User"));

        return MessageResponse.builder().message("Berhasil mendapatkan data").data(user).statusCode(200).build();
    }

    @Transactional(readOnly = true)
    public MessageResponse getAllUsers() {
        List<User> _users = this.userRepository.findAll();
        List<UserResponse> usersResponse = new ArrayList<>();

        for (User user : _users) {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setUsername(user.getUsername());
            userResponse.setEmail(user.getEmail());
            userResponse.setRoles(user.getRoles());

            usersResponse.add(userResponse);

        }

        return MessageResponse.builder().message("Berhasil mendapatkan data").data(usersResponse).statusCode(200)
                .build();
    }

    public MessageResponse updateById(long id, RegisterRequest registerRequest) {
        User _user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No found user"));
        _user.setUsername(registerRequest.getUsername());
        _user.setEmail(registerRequest.getEmail());
        _user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        Set<String> strRoles = registerRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        _user.setRoles(roles);
        this.userRepository.save(_user);

        return MessageResponse.builder().message("Successs update user").data(_user).statusCode(200).build();
    }

    public MessageResponse deleteById(long id) {
        User _user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found is user "));

        this.userRepository.delete(_user);

        return MessageResponse.builder().message("Success delete user ").statusCode(200).build();
    }
}
