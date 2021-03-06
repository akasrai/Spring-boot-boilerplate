package com.springrestapi.boilerplate.user;

import com.springrestapi.boilerplate.user.dto.AddressResponse;
import com.springrestapi.boilerplate.user.dto.BasicInfoResponse;
import com.springrestapi.boilerplate.user.dto.StatusResponse;
import com.springrestapi.boilerplate.user.dto.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);

    BasicInfoResponse toBasicInfoResponse(User user);

    AddressResponse toAddressResponse(User user);

    StatusResponse toStatusResponse(User user);
}