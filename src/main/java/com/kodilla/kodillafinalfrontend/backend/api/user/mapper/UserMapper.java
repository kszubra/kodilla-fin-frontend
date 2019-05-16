package com.kodilla.kodillafinalfrontend.backend.api.user.mapper;

import com.kodilla.kodillafinalfrontend.User;
import com.kodilla.kodillafinalfrontend.backend.api.user.domain.dto.UserDto;
import com.kodilla.kodillafinalfrontend.backend.api.user.domain.dto.UserListDto;
import com.kodilla.kodillafinalfrontend.backend.api.user.domain.dto.UserRegistrationDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class UserMapper {

    public List<User> mapToUserListFromUserListDto(UserListDto dto) {
        return this.mapToUserList(dto.getUsers());
    }

    public User mapToUser(UserDto dto) {
        return User.builder()
                .id( dto.getId().toString() )
                .email( dto.getEmail() )
                .name( dto.getName() )
                .surname( dto.getSurname() )
                .securePassword( dto.getSecurePassword() )
                .registered( dto.getRegistered() )
                .notificationIds( dto.getNotificationIds().stream()
                        .map(e -> e.toString())
                        .collect(Collectors.toSet())
                        )
                .build();
    }

    public List<User> mapToUserList(List<UserDto> dtoList) {
        return dtoList.stream()
                .map(this::mapToUser)
                .collect(Collectors.toList());
    }

    public UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id( Long.parseLong( user.getId() ) )
                .email( user.getEmail() )
                .name( user.getName() )
                .surname( user.getSurname() )
                .securePassword( user.getSecurePassword() )
                .registered( user.getRegistered() )
                .notificationIds( user.getNotificationIds().stream()
                        .map(Long::parseLong)
                        .collect(Collectors.toSet())
                )
                .build();
    }

    public UserRegistrationDto mapToRegistrationDto(User user) {
        return UserRegistrationDto.builder()
                .email( user.getEmail() )
                .name( user.getName() )
                .surname( user.getSurname() )
                .securePassword( user.getSecurePassword() )
                .build();
    }


}
