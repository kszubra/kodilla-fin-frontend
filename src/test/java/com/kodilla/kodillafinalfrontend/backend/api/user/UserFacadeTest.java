package com.kodilla.kodillafinalfrontend.backend.api.user;

import com.kodilla.kodillafinalfrontend.domain.User;
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
        User testUser = userFacade.getUserById(11L);

        //Then
        System.out.println(testUser);
    }

    @Test
    public void testRegisterUser() {
        //Given
        User user = User.builder()
                .name("Anakin")
                .surname("Skywalker")
                .email("ani@ani.com")
                .securePassword("1234567890")
                .build();
        int result = userFacade.registerUser(user);

        //Then
        assertEquals(200, result);
    }

    @Test
    public void testGetAllUsers() {
        //Given
        List<User> userDtos = userFacade.getAllUsers();

        //Then
        userDtos.forEach(System.out::println);
    }

    @Test
    public void testUpdateUser() {
        //Given
        User testUser = userFacade.getUserById(11L);

        testUser.setName("Barbarossa");

        //When
        User result = userFacade.updateUser(testUser);

        //Then
        assertEquals("Barbarossa", result.getName());
    }

    @Test
    public void testDeleteingUser() {
        //Given
        userFacade.deleteUser(15L);
    }
}