package com.BookshelfApi.api.auth;

import com.BookshelfApi.api.Models.Enum.Role;
import com.BookshelfApi.api.Models.User;
import com.BookshelfApi.api.Repository.UserRepo;
import com.BookshelfApi.api.config.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        System.out.println("firstName: " + request.getFirstName());
        System.out.println("lastName: " + request.getLastName());
        System.out.println("Email: " + request.getEmail());
        System.out.println("Password: " + request.getPassword());

        var user= User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepo.save(user);
        var Jwt= jwtService.generateToken(user);
        return AuthResponse.builder().token(Jwt).build();

    }


    public AuthResponse authenticate(AuthenticvationRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword())
            );
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw e;
        }
        var user = userRepo.findByEmail(authRequest.getEmail()).orElseThrow();
        var Jwt = jwtService.generateToken(user);
        return AuthResponse.builder().token(Jwt).build();
    }

}
