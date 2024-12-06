package com.proyecto.finanzasPersonales.service.impl;

import com.proyecto.finanzasPersonales.controller.dto.UserDTO;
import com.proyecto.finanzasPersonales.entity.User;
import com.proyecto.finanzasPersonales.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;



import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserRepository userRepositoryMock;

    @Autowired
    private UserServiceImpl userServiceImpl;

    private User userSample;

    @BeforeEach
    void init(){
        userSample = User.builder()
                .name("Matias Stocki")
                .email("cstocki128@gmail.com")
                .build();
    }


    @Test
    @DisplayName("UserService.Get() - Test")
    void get() throws Exception {
        //GIVEN
        Long id = 1L;
        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(userSample));

        //WHEN
        UserDTO userDTO = userServiceImpl.get(id);

        //THEN
        assertEquals("Matias Stocki", userDTO.getName());
        assertEquals("cstocki128@gmail.com", userDTO.getEmail());
        verify(userRepositoryMock, atMostOnce()).findById(id);
    }

    @Test
    @DisplayName("UserService.create() - TEST")
    void create() throws Exception {
        //GIVEN
        UserDTO userDTO = UserDTO.builder().name("Matias").email("email@email.com").build();
        UserDTO userNotInitialized = null;

        //WHEN
        when(userRepositoryMock.save(userSample)).thenReturn(userSample);
        userServiceImpl.create(userDTO);

        //THEN
        verify(userRepositoryMock,atMostOnce()).save(userSample);
    }

}