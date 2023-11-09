package com.eduardoscheffer.oopjavawebservice;

import com.eduardoscheffer.oopjavawebservice.entities.User;
import com.eduardoscheffer.oopjavawebservice.repositories.UserRepository;
import com.eduardoscheffer.oopjavawebservice.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class) // utilizado para usar o Mockito na classe
public class UserServiceTest {

    @InjectMocks // normalmente a classe que está sendo testada - nesse caso a UserService = deve ser anotada com @InjectMocks
    UserService userService;

    @Mock // as dependencias da classe que estamos testando - nesse caso a UserRepository - deve ser anotada com @Mock
    UserRepository userRepository;
    @Test
    @DisplayName("Testando metodo findAll()")
    public void testfindAllUsers() {
        List<User> users = new ArrayList<>();

        users.add(getUser(1L, "user1",
                "user1@gmail.com"));
        users.add(getUser(2L, "user2",
                "user2@gmail.com"));

        // no mock indicamos exatamente o que deve acontecer quando o método findAll é chamado.
        // assim testamos apenas o comportamento do metodo que queremos testar e não suas dependências
        Mockito.when(userRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(users); // quando o metodo findAll for invocado retorna um mock de users;

        List<User> usersREturned = userService.findAll();
        Assertions.assertEquals(2, usersREturned.size()); // verifica se a lista retornada possui o tamanho esperado
        Assertions.assertEquals("user1", usersREturned.get(0).getName());
        Assertions.assertEquals("user2", usersREturned.get(1).getName());
    }

    @Test
    @DisplayName("Testando metodo insertUser()")
    public void testInsertUser() {

    }

    public static User getUser(Long id, String nome, String email) {
        User user = new User();
        user.setId(id);
        user.setName(nome);
        user.setEmail(email);
        user.setPhone("99887755");
        user.setPassword("123456");
        return user;
    }


}
