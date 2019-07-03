package com.machpay.socialauthentication.controller;

import com.machpay.socialauthentication.exception.ResourceNotFoundException;
import com.machpay.socialauthentication.model.User;
import com.machpay.socialauthentication.repository.UserRepository;
import com.machpay.socialauthentication.security.CurrentUser;
import com.machpay.socialauthentication.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}