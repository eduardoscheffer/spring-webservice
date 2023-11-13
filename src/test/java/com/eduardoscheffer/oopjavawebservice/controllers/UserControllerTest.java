package com.eduardoscheffer.oopjavawebservice.controllers;

import com.eduardoscheffer.oopjavawebservice.entities.User;
import com.eduardoscheffer.oopjavawebservice.services.UserService;
import com.eduardoscheffer.oopjavawebservice.services.UserServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    private MockMvc mockMvc; // classe que simula uma chamada REST para um serviço

    // TODO testes aqui:

    @Test
    @DisplayName("Testando metodo findAll")
    public void testFindAll() {
        User user1 = UserServiceTest.getUser(1L, "Testaldo1", "testaldo1@gmail.com");
        User user2 = UserServiceTest.getUser(2L, "Testaldo2", "testaldo2@gmail.com");

        List<User> userList = new ArrayList<>(Arrays.asList(user1, user2));

        when(userService.findAll()).thenReturn(userList);

        // chamando o metodo a ser testado:
        ResponseEntity<List<User>> responseEntity = userController.findAll();

        // Verificacoes:
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userList, responseEntity.getBody());

        verify(userService, times(1)).findAll();

    }

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

}
