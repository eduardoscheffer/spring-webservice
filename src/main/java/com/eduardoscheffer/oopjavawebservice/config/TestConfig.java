package com.eduardoscheffer.oopjavawebservice.config;

import com.eduardoscheffer.oopjavawebservice.entities.User;
import com.eduardoscheffer.oopjavawebservice.repositories.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test") // nome do perfil de teste. Vai rodar a configuracao so quando eu estiver no perfil de teste
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRespository userRespository;


    @Override
    public void run(String... args) throws Exception { // metodo do CommandLineRunner que tudo que estiver aqui vai ser executado quando a aplicacao for iniciada

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        userRespository.saveAll(Arrays.asList(u1, u2)); // salva no Banco de Dados

    }
}
