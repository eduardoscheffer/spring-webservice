package com.eduardoscheffer.oopjavawebservice.repositories;

import com.eduardoscheffer.oopjavawebservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNameContainingIgnoreCase (String name);

}
