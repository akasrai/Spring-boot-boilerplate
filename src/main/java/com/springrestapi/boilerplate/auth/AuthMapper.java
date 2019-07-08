package com.springrestapi.boilerplate.auth;

import com.springrestapi.boilerplate.user.User;
import com.springrestapi.boilerplate.user.dto.AddressResponse;
import com.springrestapi.boilerplate.user.dto.BasicInfoResponse;
import com.springrestapi.boilerplate.user.dto.StatusResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    AuthResponse toAuthResponse(User user);

    BasicInfoResponse toBasicInfoResponse(User user);

    AddressResponse toAddressResponse(User user);

    StatusResponse toStatusResponse(User user);
}