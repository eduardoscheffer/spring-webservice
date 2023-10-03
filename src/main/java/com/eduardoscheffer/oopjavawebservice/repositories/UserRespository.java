package com.eduardoscheffer.oopjavawebservice.repositories;

import com.eduardoscheffer.oopjavawebservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User, Long> {
}
