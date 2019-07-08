package com.springrestapi.boilerplate.auth;

import com.springrestapi.boilerplate.common.exception.BadRequestException;
import com.springrestapi.boilerplate.emailVerification.VerificationTokenService;
import com.springrestapi.boilerplate.user.User;
import com.springrestapi.boilerplate.common.ApiResponse;
import com.springrestapi.boilerplate.user.UserMapper;
import com.springrestapi.boilerplate.security.TokenProvider;
import com.springrestapi.boilerplate.user.UserService;
import com.springrestapi.boilerplate.user.dto.UserResponse;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @PostMapping("/login")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication);
        User user = userService.findByEmail(loginRequest.getEmail());
        AuthResponse authResponse = authService.buildAuthResponse(user, token);

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userService.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        User user = authService.createUser(signUpRequest);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signUpRequest.getEmail(),
                        signUpRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication);
        AuthResponse authResponse = authService.buildAuthResponse(user, token);

        return ResponseEntity.ok(authResponse);
    }

    @GetMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        ResponseEntity<String> responseEntity = verificationTokenService.verifyEmail(token);

        return responseEntity;
    }

}