package com.eduardoscheffer.oopjavawebservice.repositories;

import com.eduardoscheffer.oopjavawebservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
