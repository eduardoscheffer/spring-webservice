package com.eduardoscheffer.oopjavawebservice.services;

import com.eduardoscheffer.oopjavawebservice.entities.User;
import com.eduardoscheffer.oopjavawebservice.exceptions.DatabaseException;
import com.eduardoscheffer.oopjavawebservice.exceptions.ResourceNotFoundException;
import com.eduardoscheffer.oopjavawebservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll () {
        return repository.findAll();
    }

    public User findUserById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insertUser(User user) {
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
        return repository.findById(id).map(existingUser -> {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());
            return existingUser;
        }).orElse(null);
    }

}
