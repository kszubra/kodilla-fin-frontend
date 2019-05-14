package com.kodilla.kodillafinalfrontend.backend.api.user;

import com.kodilla.kodillafinalfrontend.backend.api.user.domain.dto.UserDto;

import com.kodilla.kodillafinalfrontend.backend.api.user.domain.dto.UserRegistrationDto;
import com.kodilla.kodillafinalfrontend.backend.api.user.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserFacade {
    private final UserClient userClient;
    private final UserMapper userMapper;

    public UserDto getUserDtoById(final Long id) {
        return userClient.getUserById(id) ;
    }

    public Integer registerUser(final UserRegistrationDto dto) {
        return userClient.registerUser(dto);
    }

    public List<UserDto> getAllUsers() {
        return userMapper.mapToUserListFromUserListDto( userClient.getAllUsers() );
    }

    public UserDto updateUser(final UserDto dto) {
        return userClient.updateUser(dto);
    }

    public void deleteUser(final Long id) {
        userClient.deleteUser(id);
    }
}
