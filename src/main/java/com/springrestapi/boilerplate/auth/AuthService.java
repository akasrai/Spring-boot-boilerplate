package com.springrestapi.boilerplate.auth;

import com.springrestapi.boilerplate.emailVerification.VerificationTokenService;
import com.springrestapi.boilerplate.user.User;
import com.springrestapi.boilerplate.user.UserService;
import com.springrestapi.boilerplate.user.dto.AddressResponse;
import com.springrestapi.boilerplate.user.dto.BasicInfoResponse;
import com.springrestapi.boilerplate.user.dto.StatusResponse;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenService verificationTokenService;

    public User createUser( SignUpRequest signUpRequest) {
        User user = new User();
        user.setFirstName(signUpRequest.getFirstName());
        user.setMiddleName(signUpRequest.getMiddleName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User result = userService.save(user);

        verificationTokenService.createVerification(signUpRequest.getEmail());

        return result;
    }

    public AuthResponse buildAuthResponse(User user, String accessToken) {
        AuthMapper userMapper = Mappers.getMapper(AuthMapper.class);

        AuthResponse authResponse = userMapper.toAuthResponse(user);
        StatusResponse statusResponse = userMapper.toStatusResponse(user);
        AddressResponse addressResponse = userMapper.toAddressResponse(user);
        BasicInfoResponse basicInfoResponse = userMapper.toBasicInfoResponse(user);

        basicInfoResponse.setAddress (addressResponse);
        authResponse.setStatus(statusResponse);
        authResponse.setUser(basicInfoResponse);
        authResponse.setAccessToken(accessToken);

        return authResponse;
    }

}
