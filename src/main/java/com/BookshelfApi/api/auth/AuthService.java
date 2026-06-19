package com.BookshelfApi.api.auth;

import com.BookshelfApi.api.Models.Enum.Role;
import com.BookshelfApi.api.Models.User;
import com.BookshelfApi.api.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final com.BookshelfApi.api.auth.JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {

        log.info("Register request received");
        var user= buildUser(request,passwordEncoder);

        userRepo.save(user);

        var Jwt= jwtService.generateToken(user);

        return AuthResponse.builder().token(Jwt).build();

    }



    public AuthResponse authenticate(AuthenticvationRequest authRequest) {
        log.info("Authentication request received checking to Authenticate user");
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
        var jwt = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwt).build();
    }


    private User buildUser(RegisterRequest request,PasswordEncoder passwordEncoder) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
    }

}
