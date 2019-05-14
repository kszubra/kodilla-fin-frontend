package com.kodilla.kodillafinalfrontend.backend.api.user.mapper;

import com.kodilla.kodillafinalfrontend.backend.api.user.domain.dto.UserDto;
import com.kodilla.kodillafinalfrontend.backend.api.user.domain.dto.UserListDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class UserMapper {

    public List<UserDto> mapToUserListFromUserListDto(UserListDto dto) {
        return dto.getUsers();
    }


}
