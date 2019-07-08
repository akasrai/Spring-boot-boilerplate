package com.springrestapi.boilerplate.user;

import com.springrestapi.boilerplate.security.CurrentUser;
import com.springrestapi.boilerplate.security.UserPrincipal;
import com.springrestapi.boilerplate.user.dto.UserResponse;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        User user= userService.findById(userPrincipal.getId());
        UserResponse userResponse = userService.buildUserResponse(user);

        return ResponseEntity.ok(userResponse);
    }
}