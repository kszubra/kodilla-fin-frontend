package com.kodilla.kodillafinalfrontend.backend.api.user;

import com.kodilla.kodillafinalfrontend.backend.api.user.domain.dto.UserDto;
import com.kodilla.kodillafinalfrontend.backend.api.user.domain.dto.UserRegistrationDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserFacadeTest {
    @Autowired
    private UserFacade userFacade;

    @Test
    public void testGetUserById() {
        //Given
        UserDto testUserDto = userFacade.getUserDtoById(11L);

        //Then
        System.out.println(testUserDto);
    }

    @Test
    public void testRegisterUser() {
        //Given
        UserRegistrationDto dto = UserRegistrationDto.builder()
                .name("Anakin")
                .surname("Skywalker")
                .email("ani@ani.com")
                .securePassword("1234567890")
                .build();
        int result = userFacade.registerUser(dto);

        //Then
        assertEquals(200, result);
    }

    @Test
    public void testGetAllUsers() {
        //Given
        List<UserDto> userDtos = userFacade.getAllUsers();

        //Then
        userDtos.forEach(System.out::println);
    }

    @Test
    public void testUpdateUser() {
        //Given
        UserDto testUserDto = userFacade.getUserDtoById(11L);

        testUserDto.setName("Barbarossa");

        //When
        UserDto result = userFacade.updateUser(testUserDto);

        //Then
        assertEquals("Barbarossa", result.getName());
    }

    @Test
    public void testDeleteingUser() {
        //Given
        userFacade.deleteUser(15L);
    }
}