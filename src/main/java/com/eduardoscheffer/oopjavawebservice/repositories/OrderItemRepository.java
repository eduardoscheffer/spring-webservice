package com.eduardoscheffer.oopjavawebservice.repositories;

import com.eduardoscheffer.oopjavawebservice.entities.OrderItem;
import com.eduardoscheffer.oopjavawebservice.entities.pk.OrderItemPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPk> {
}
