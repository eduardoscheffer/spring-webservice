package com.eduardoscheffer.oopjavawebservice.services;

import com.eduardoscheffer.oopjavawebservice.controllers.utils.URL;
import com.eduardoscheffer.oopjavawebservice.entities.User;
import com.eduardoscheffer.oopjavawebservice.exceptions.DatabaseException;
import com.eduardoscheffer.oopjavawebservice.exceptions.ResourceNotFoundException;
import com.eduardoscheffer.oopjavawebservice.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    public UserService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    @Autowired
    private UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public List<User> findAll () {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name")); // retorna uma lista ordenada por nome (ordem alfabética) usando Sort.by do Spring Data
    }

    public User findUserById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insertUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword())); // criptografia da senha
        return repository.save(user);
    }

    public void deleteUser(Long id) {
        try {
            if (!repository.existsById(id)) throw new ResourceNotFoundException(id); // se nao existir o User na DB lanca uma excecao peronalizada
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public User updateUser(Long id, User user) {

        if (!repository.existsById(id)) throw new ResourceNotFoundException(id); // se nao existir o User na DB lanca uma excecao peronalizada

        try {
            return repository.findById(id).map(existingUser -> {
                URL.copyNonNullProperties(user, existingUser);
                return insertUser(existingUser);
            }).orElse(null);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional
    public List<User> findByName (String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

}
