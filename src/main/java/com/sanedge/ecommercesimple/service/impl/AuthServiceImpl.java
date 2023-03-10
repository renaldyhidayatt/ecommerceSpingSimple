package com.sanedge.ecommercesimple.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sanedge.ecommercesimple.enums.ERole;
import com.sanedge.ecommercesimple.exception.ResourceNotFoundException;
import com.sanedge.ecommercesimple.models.Role;
import com.sanedge.ecommercesimple.models.User;
import com.sanedge.ecommercesimple.payload.request.LoginRequest;
import com.sanedge.ecommercesimple.payload.request.RegisterRequest;
import com.sanedge.ecommercesimple.payload.response.AuthenticationResponse;
import com.sanedge.ecommercesimple.payload.response.MessageResponse;
import com.sanedge.ecommercesimple.repository.RoleRepository;
import com.sanedge.ecommercesimple.repository.UserRepository;
import com.sanedge.ecommercesimple.security.JwtProvider;
import com.sanedge.ecommercesimple.security.UserDetailsImpl;
import com.sanedge.ecommercesimple.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
            RoleRepository roleRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateAccessToken(authentication);

        long expiresAt = jwtProvider.getjwtExpirationMs();
        Date date = new Date();
        date.setTime(expiresAt);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return AuthenticationResponse.builder().message("Berhasil login").authenticationToken(jwt)
                .expiresAt(date.toString())
                .username(userDetails.getUsername()).statusCode(200).build();
    }

    public MessageResponse register(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

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
        user.setRoles(roles);
        this.userRepository.save(user);

        return MessageResponse.builder().message("Successs create user").data(user).statusCode(200).build();

    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return this.userRepository.findByUsername(authentication.getName())
                .orElseThrow(
                        () -> new UsernameNotFoundException("User name not found - " + authentication.getName()));
    }
}
