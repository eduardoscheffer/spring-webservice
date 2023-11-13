package com.eduardoscheffer.oopjavawebservice.services;

import com.eduardoscheffer.oopjavawebservice.entities.User;
import com.eduardoscheffer.oopjavawebservice.exceptions.DatabaseException;
import com.eduardoscheffer.oopjavawebservice.exceptions.ResourceNotFoundException;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

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
        when(userRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(users); // quando o metodo findAll for invocado retorna um mock de users;

        List<User> usersREturned = userService.findAll();
        Assertions.assertEquals(2, usersREturned.size()); // verifica se a lista retornada possui o tamanho esperado
        Assertions.assertEquals("user1", usersREturned.get(0).getName());
        Assertions.assertEquals("user2", usersREturned.get(1).getName());
    }

    @Test
    @DisplayName("Testando metodo insertUser()")
    public void testInsertUser() {

        // Criando um usuario de teste:
        User user = new User();
        user.setName("Testildo");
        user.setPassword("@testand0123");

        when(userRepository.save(Mockito.any())).thenReturn(user);

        // chama o metodo a ser testado:
        User userTested = userService.insertUser(user);

        Assertions.assertNotNull(userTested); // verifica se o teste retornou um usuario nao nulo
        Assertions.assertEquals("Testildo", userTested.getName());

        Mockito.verify(userRepository, Mockito.times(1)).save(user); // verifica se o metodo save do userRepository foi chamado 1x

    }

    @Test
    @DisplayName("Testando update User com sucesso")
    public void testUpdateUserSuccess() {

        User user = getUser(1L, "Testando", "testando@gmail.com");

        // configurando o comportamento simuladodo userRepository:
        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(Mockito.any())).thenReturn(user);

        user.setEmail("novotestildo@gmail.com");

        // metodo a ser testado:
        User userTested = userService.updateUser(1L, user);

        // verificacoes:
        Assertions.assertNotNull(userTested);
        Assertions.assertEquals("Testando", userTested.getName());
        Assertions.assertEquals("novotestildo@gmail.com", userTested.getEmail());

    }

    @Test
    @DisplayName("Testando update User sem sucesso")
    public void testUpdateUserFail() {
        when(userRepository.existsById(1L)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class,
                () -> userService.updateUser(1L, getUser(1L, "Testaldo", "teste@gmail.com")));
    }

    @Test
    @DisplayName("Testando findUserById()")
    public void testFindUserById() {
        User user = getUser(1L, "Testaldo", "testaldo@gmail.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User userTested = userService.findUserById(1L);

        Assertions.assertNotNull(userTested);
        Assertions.assertEquals("Testaldo", userTested.getName());
        Assertions.assertEquals("testaldo@gmail.com", userTested.getEmail());
    }

    @Test
    @DisplayName("Testando deleteUser com sucesso")
    public void testDeleteUser() {

        when(userRepository.existsById(1L)).thenReturn(true);

        Mockito.verify(userRepository, Mockito.times(1)).deleteById(1L);

    }

    @Test
    @DisplayName("Teste deleteUser not Found")
    public void testDeleteUserNotFound() {
        // Configuração do comportamento simulado do userRepository para um usuário que não existe
        when(userRepository.existsById(2L)).thenReturn(false);

        // Testa se o método lança a exceção ResourceNotFoundException quando o usuário não é encontrado
        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(2L));
    }

    @Test
    @DisplayName("Testando deleteUser com exception DatabaseException")
    public void testDeleteUserWithDatabaseException() {
        when(userRepository.existsById(1L)).thenReturn(true);
        doThrow(DataIntegrityViolationException.class).when(userRepository).deleteById(1L);

        assertThrows(DatabaseException.class, () -> userService.deleteUser(1L));
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
