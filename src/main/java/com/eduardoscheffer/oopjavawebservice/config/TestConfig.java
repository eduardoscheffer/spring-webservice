package com.eduardoscheffer.oopjavawebservice.config;

import com.eduardoscheffer.oopjavawebservice.entities.Order;
import com.eduardoscheffer.oopjavawebservice.entities.User;
import com.eduardoscheffer.oopjavawebservice.repositories.OrderRepository;
import com.eduardoscheffer.oopjavawebservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test") // nome do perfil de teste. Vai rodar a configuracao so quando eu estiver no perfil de teste
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public void run(String... args) throws Exception { // metodo do CommandLineRunner que tudo que estiver aqui vai ser executado quando a aplicacao for iniciada

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
        userRepository.saveAll(Arrays.asList(u1, u2)); // salva no Banco de Dados

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1);

        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

    }
}
