package com.eduardoscheffer.oopjavawebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) // usado pra nao pedir autenticacao dos endpoints - TEMPORARIO
public class OopJavaWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OopJavaWebServiceApplication.class, args);
	}

}
