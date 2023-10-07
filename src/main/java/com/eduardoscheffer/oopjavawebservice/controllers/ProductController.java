package com.eduardoscheffer.oopjavawebservice.controllers;

import com.eduardoscheffer.oopjavawebservice.entities.Product;
import com.eduardoscheffer.oopjavawebservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {

        List<Product> list = service.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product Product = service.findById(id);
        return new ResponseEntity<>(Product, HttpStatus.OK);
    }



}
