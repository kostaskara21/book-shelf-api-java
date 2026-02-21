package com.BookshelfApi.api.auth;


import com.BookshelfApi.api.constants.AuthEndpointsConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(AuthEndpointsConstants.BASIC_URL)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;



    @PostMapping(AuthEndpointsConstants.REGISTER)
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest requests){
        return ResponseEntity.ok(authService.register(requests));
    }

    @PostMapping(AuthEndpointsConstants.AUTHENTICATE)
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthenticvationRequest requests){
        try {
            return ResponseEntity.ok(authService.authenticate(requests)); // 200 OK
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401
        }

    }

}
