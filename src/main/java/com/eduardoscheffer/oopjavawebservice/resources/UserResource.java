package com.eduardoscheffer.oopjavawebservice.resources;

import com.eduardoscheffer.oopjavawebservice.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserResource {
    @GetMapping
    public ResponseEntity<User> findAll() {
         User user = new User(1L, "Maria", "maria@gmail.com", "00000", "12345");
         return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
