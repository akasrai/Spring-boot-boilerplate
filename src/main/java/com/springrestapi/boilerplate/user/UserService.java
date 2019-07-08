package com.springrestapi.boilerplate.user;

import com.springrestapi.boilerplate.common.exception.ResourceNotFoundException;
import com.springrestapi.boilerplate.user.dto.AddressResponse;
import com.springrestapi.boilerplate.user.dto.BasicInfoResponse;
import com.springrestapi.boilerplate.user.dto.StatusResponse;
import com.springrestapi.boilerplate.user.dto.UserResponse;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        user.setFullName(createFullName(user));

        return user;
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", email));

        user.setFullName(createFullName(user));

        return user;
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User save(User user) {

        User result = userRepository.save(user);
        result.setFullName(createFullName(user));

        return result;
    }

    private String createFullName(User user) {
        return new StringBuilder()
                .append(user.getFirstName())
                .append(" ")
                .append(
                        user.getMiddleName()!=null
                                ?user.getMiddleName()+" "
                                :""
                )
                .append(user.getLastName())
                .toString();
    }

    public UserResponse buildUserResponse(User user) {
        UserMapper mapper = Mappers.getMapper(UserMapper.class);

        UserResponse userResponse = mapper.toUserResponse(user);
        StatusResponse statusResponse = mapper.toStatusResponse(user);
        AddressResponse addressResponse = mapper.toAddressResponse(user);
        BasicInfoResponse basicInfoResponse = mapper.toBasicInfoResponse(user);

        basicInfoResponse.setAddress (addressResponse);
        userResponse.setStatus(statusResponse);
        userResponse.setUser(basicInfoResponse);

        return userResponse;
    }
}
