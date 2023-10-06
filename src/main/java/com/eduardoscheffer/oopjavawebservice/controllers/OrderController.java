package com.eduardoscheffer.oopjavawebservice.controllers;

import com.eduardoscheffer.oopjavawebservice.entities.Order;
import com.eduardoscheffer.oopjavawebservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {

        List<Order> list = service.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @GetMapping("{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Order Order = service.findById(id);
        return new ResponseEntity<>(Order, HttpStatus.OK);
    }



}
